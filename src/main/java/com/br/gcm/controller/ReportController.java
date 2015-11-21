package com.br.gcm.controller;

import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.MovimentoFinanceiroDao;
import com.br.gcm.dao.PessoaDao;
import com.br.gcm.dao.PlanoContasDao;
import com.br.gcm.model.Pais;
import com.br.gcm.model.MovimentoFinanceiro;
import com.br.gcm.model.filtros.Filtro_MovimentoFinanceiro;
import com.br.gcm.model.Pessoa;
import com.br.gcm.model.PlanoContas;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 15/06/15
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/report")
public class ReportController {

    @Inject private JavaMailSender mailSender;
    @Inject private PaisDao paisDao;
    @Inject private MovimentoFinanceiroDao movimentoFinanceiroDao;
    @Inject private PessoaDao pessoaDao;
    @Inject private PlanoContasDao planoContasDao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/pais_report/{tipo}")
    public String paisreport(Model model, @PathVariable("tipo") String tipo) {
        List<Pais> paises = paisDao.Pais_lista();
        JRDataSource datasource = new JRBeanCollectionDataSource(paises);
        model.addAttribute("datasource", datasource);
        model.addAttribute("format", tipo);
        return "pais_report";
    }

    @RequestMapping(value = "/movimentofinanceiro_programacao_report/{tipomovimento}", method = RequestMethod.POST)
    public String imprimirprogramacao(@ModelAttribute Filtro_MovimentoFinanceiro filtros, @PathVariable("tipomovimento") String tipo, Model model) {
        List<MovimentoFinanceiro> mov = movimentoFinanceiroDao.selectAll(filtros);
        JRDataSource datasource = new JRBeanCollectionDataSource(mov);

        if (mov.isEmpty()){
            model.addAttribute("mensagem", "Filtros sem informações para exibir.");
            return "mensagemerro";
        }

        model.addAttribute("datasource", datasource);
        model.addAttribute("format", filtros.getTipoReport());

        if (tipo.equals("C")){
            model.addAttribute("P_NOMEREPORT", "Movimento Financeiro de Crédito (Programados)");
        } else {
            model.addAttribute("P_NOMEREPORT", "Movimento Financeiro de Débito (Programados)");
        };

        String p_pessoa = "Sem Filtros";
        if (filtros.getId_Pessoa() > 0){
            Pessoa pessoa = pessoaDao.selectById(filtros.getId_Pessoa());
            p_pessoa = pessoa.getRazaoSocial();
        }
        model.addAttribute("P_PESSOA", p_pessoa);

        String p_conta = "Sem Filtros";
        if (filtros.getId_PlanoContas() > 0){
            PlanoContas planoContas = planoContasDao.selectById(filtros.getId_PlanoContas());
            p_conta = planoContas.getCodigoConta() +" - "+planoContas.getDescricao();
        }
        model.addAttribute("P_CONTA", p_conta);

        String p_vencimento = "Sem Filtros";
        if (filtros.getDataVencimentoIni() != null && !filtros.getDataVencimentoIni().equals("") && filtros.getDataVencimentoFim() != null && !filtros.getDataVencimentoFim().equals("")) {
            p_vencimento = filtros.getDataVencimentoIni().toString() +" até "+ filtros.getDataVencimentoFim().toString();
        }
        model.addAttribute("P_VENCIMENTO", p_vencimento);

        String p_competencia = "Sem Filtros";
        if (filtros.getDataCompetenciaIni() != null && filtros.getDataCompetenciaFim() != null) {
            p_competencia = filtros.getDataCompetenciaIni().toString() +" até "+ filtros.getDataCompetenciaFim().toString();
        }
        model.addAttribute("P_COMPETENCIA", p_competencia);

        String relatorio = "";
        if (filtros.getAgrupamentoReport().equals("0")){
            relatorio = "movimentofinanceiro_programacao_report";
        }
        if (filtros.getAgrupamentoReport().equals("1")){
            relatorio = "movimentofinanceiro_programacao_vencimento_report";
        }
        if (filtros.getAgrupamentoReport().equals("2")){
            relatorio = "movimentofinanceiro_programacao_pessoa_report";
        }
        if (filtros.getAgrupamentoReport().equals("3")){
            relatorio = "movimentofinanceiro_programacao_conta_report";
        }

        return relatorio;
    }
}

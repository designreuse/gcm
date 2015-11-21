package com.br.gcm.controller;

import com.br.gcm.dao.MovimentoFinanceiroDao;
import com.br.gcm.dao.BancoDao;
import com.br.gcm.model.MovimentoFinanceiro;
import com.br.gcm.model.filtros.Filtro_MovimentoFinanceiro;
import com.br.gcm.service.MovimentoFinanceiroService;
import com.br.gcm.tag.Pagina;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.br.gcm.model.Usuario;
import com.br.gcm.model.Empresa;
import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.util.Rotinas;

import javax.inject.Inject;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 08/06/15
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class MovimentoFinanceiroController {

    @Inject private Rotinas rotinas;
    @Inject private EmpresaDao empresaDao;
    @Inject private MovimentoFinanceiroDao movimentoFinanceiroDao;
    @Inject private MovimentoFinanceiroService movimentoFinanceiroService;
    @Inject private BancoDao bancoDao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/movimentofinanceiro_lista/{tipomovimento}")
    public String lista(@PathVariable("tipomovimento") String tipomovimento, @PageableDefault(size = 10) Pageable pageable,  Model model) {
        String codtransacaopai = "";
        if (tipomovimento.equals("C")){
            codtransacaopai = "5201";
        } else {
            codtransacaopai = "5101";
        }

        Usuario usuario = rotinas.usuarioLogado();
        Boolean listajsp = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai);
        if (listajsp != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Filtro_MovimentoFinanceiro filtros = new Filtro_MovimentoFinanceiro();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        if (!listaEmpresa.isEmpty()){
            filtros.setId_Empresa(listaEmpresa.get(0).getId_Empresa());
        };

        filtros.setTipoMovimento(tipomovimento);

        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"01");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"02");
        Boolean cancelar = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"03");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"04");
        Boolean liquidar = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"05");
        Boolean estornar = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"06");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("cancelar", cancelar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("liquidar", liquidar);
        model.addAttribute("estornar", estornar);

        model.addAttribute("filtros", filtros);
        model.addAttribute("tipomovimento", tipomovimento);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", movimentoFinanceiroDao.selectAll(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, movimentoFinanceiroDao.count(filtros, pageable)));
        return "movimentofinanceiro_lista";
    }

    @RequestMapping(value = "/movimentofinanceiro_lista/{tipomovimento}", method = RequestMethod.POST)
    public String filtros(@PathVariable("tipomovimento") String tipomovimento, @ModelAttribute Filtro_MovimentoFinanceiro filtros, @PageableDefault(size = 10) Pageable pageable, Model model) {
        String codtransacaopai = "";
        if (tipomovimento.equals("C")){
            codtransacaopai = "5201";
        } else {
            codtransacaopai = "5101";
        }

        Usuario usuario = rotinas.usuarioLogado();
        Boolean listajsp = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai);
        if (listajsp != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"01");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"02");
        Boolean cancelar = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"03");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"04");
        Boolean liquidar = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"05");
        Boolean estornar = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai+"06");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("cancelar", cancelar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("liquidar", liquidar);
        model.addAttribute("estornar", estornar);

        model.addAttribute("filtros", filtros);
        model.addAttribute("tipomovimento", tipomovimento);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", movimentoFinanceiroDao.selectAll(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, movimentoFinanceiroDao.count(filtros, pageable)));
        return "movimentofinanceiro_lista";
    }

    //Nova
    @RequestMapping(value = "/movimentofinanceiro_novo/{tipomovimento}", method = RequestMethod.GET)
    public String novo(@PathVariable("tipomovimento") String tipomovimento, ModelMap model) {
        String codtransacaopai = "";
        if (tipomovimento.equals("C")){
            codtransacaopai = "5201";
        } else {
            codtransacaopai = "5101";
        }

        Usuario usuario = rotinas.usuarioLogado();
        Boolean listajsp = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai);
        if (listajsp != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        Date d = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(d);

        MovimentoFinanceiro movimentoFinanceiro = new MovimentoFinanceiro();
        movimentoFinanceiro.setTipoMovimento(tipomovimento);
        movimentoFinanceiro.setDataMovimento(d);

        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("listabancos", bancoDao.selectAll());
        model.addAttribute("movfinanceiro", movimentoFinanceiro);
        model.addAttribute("tipomovimento", tipomovimento);
        return "movimentofinanceiro_novo";
    }

    //Insert
    @RequestMapping(value = "/movimentofinanceiro_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute MovimentoFinanceiro movimentoFinanceiro, Model model) {

        if (movimentoFinanceiro.getValorAcrescimos() == null){movimentoFinanceiro.setValorAcrescimos(0);}
        if (movimentoFinanceiro.getValorDescontos() == null){movimentoFinanceiro.setValorDescontos(0);}
        if (movimentoFinanceiro.getValorMulta() == null) {movimentoFinanceiro.setValorMulta(0);}
        if (movimentoFinanceiro.getAliquotaJuros() == null) {movimentoFinanceiro.setAliquotaJuros(0);}

        try{
            movimentoFinanceiroService.insert(movimentoFinanceiro);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }

        return "redirect:/movimentofinanceiro_editar/"+movimentoFinanceiro.getId_MovimentoFinanceiro();
    }

    //Editar
    @RequestMapping(value = "/movimentofinanceiro_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        MovimentoFinanceiro movimentoFinanceiro = movimentoFinanceiroDao.selectById(id);

        String codtransacaopai = "";
        if (movimentoFinanceiro.getTipoMovimento().equals("C")){
            codtransacaopai = "5201";
        } else {
            codtransacaopai = "5101";
        }

        Usuario usuario = rotinas.usuarioLogado();
        Boolean listajsp = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai);
        if (listajsp != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("listabancos", bancoDao.selectAll());
        model.addAttribute("movimentofinanceiro", movimentoFinanceiro);
        model.addAttribute("tipomovimento", movimentoFinanceiro.getTipoMovimento());
        return "movimentofinanceiro_editar";
    }

    //Update
    @RequestMapping(value = "/movimentofinanceiro_update", method = RequestMethod.POST)
    public String update(@ModelAttribute MovimentoFinanceiro movimentoFinanceiro, Model model) {
        try{
            movimentoFinanceiroService.update(movimentoFinanceiro);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }

        return "redirect:/movimentofinanceiro_editar/"+movimentoFinanceiro.getId_MovimentoFinanceiro();
    }

    @RequestMapping(value = "/movimentofinanceiro_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        MovimentoFinanceiro movimentoFinanceiro = movimentoFinanceiroDao.selectById(id);

        String codtransacaopai = "";
        if (movimentoFinanceiro.getTipoMovimento().equals("C")){
            codtransacaopai = "5201";
        } else {
            codtransacaopai = "5101";
        }
        Usuario usuario = rotinas.usuarioLogado();
        Boolean listajsp = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai);
        if (listajsp != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("listabancos", bancoDao.selectAll());
        model.addAttribute("movimentofinanceiro", movimentoFinanceiro);
        model.addAttribute("tipomovimento", movimentoFinanceiro.getTipoMovimento());
        return "movimentofinanceiro_detalhes";
    }

    @RequestMapping(value = "/movimentofinanceiro_imprimirprogramacao/{tipomovimento}")
    public String imprimirprogramacao(@PathVariable("tipomovimento") String tipomovimento, Model model) {
        String codtransacaopai = "";
        if (tipomovimento.equals("C")){
            codtransacaopai = "710101";
        } else {
            codtransacaopai = "710201";
        }

        Usuario usuario = rotinas.usuarioLogado();
        Boolean listajsp = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codtransacaopai);
        if (listajsp != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Filtro_MovimentoFinanceiro filtros = new Filtro_MovimentoFinanceiro();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        if (!listaEmpresa.isEmpty()){
            filtros.setId_Empresa(listaEmpresa.get(0).getId_Empresa());
        };

        filtros.setTipoMovimento(tipomovimento);

        model.addAttribute("filtros", filtros);
        model.addAttribute("tipomovimento", tipomovimento);
        model.addAttribute("listaempresa", listaEmpresa);
        return "movimentofinanceiro_imprimirprogramacao";
    }
}

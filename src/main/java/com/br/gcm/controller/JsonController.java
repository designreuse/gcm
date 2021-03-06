package com.br.gcm.controller;

import com.br.gcm.dao.MunicipioDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.dao.PessoaDao;
import com.br.gcm.dao.ContaCorrenteDao;
import com.br.gcm.dao.RelacaoCFOPDao;
import com.br.gcm.dao.ProdutoDao;
import com.br.gcm.dao.ProdutoUnidadeDao;
import com.br.gcm.dao.ProdutoLoteDao;
import com.br.gcm.dao.VWEstoqueProdutoLoteDao;
import com.br.gcm.dao.GrupoTransacaoDao;
import com.br.gcm.dao.CentroCustoDao;
import com.br.gcm.dao.PlanoContasDao;
import com.br.gcm.dao.MovimentoFinanceiroDao;

import com.br.gcm.model.MovimentoFinanceiro;
import com.br.gcm.model.GrupoTransacao;
import com.br.gcm.model.Municipio;
import com.br.gcm.model.Uf;
import com.br.gcm.model.Pessoa;
import com.br.gcm.model.ContaCorrente;
import com.br.gcm.model.Produto;
import com.br.gcm.model.ProdutoLote;
import com.br.gcm.model.ProdutoUnidade;
import com.br.gcm.model.VWEstoqueProdutoLote;
import com.br.gcm.model.filtros.Filtro_Produto;
import com.br.gcm.model.CentroCusto;
import com.br.gcm.model.PlanoContas;
import com.br.gcm.service.GrupoUsuarioService;
import com.br.gcm.service.CFOPService;
import com.br.gcm.service.ProdutoLoteService;
import com.br.gcm.util.Rotinas;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.br.gcm.service.MovimentoFinanceiroService;

import javax.inject.Inject;
import javax.swing.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 27/04/15
 * Time: 10:12
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class JsonController {
    @Inject private UfDao ufDao;
    @Inject private MunicipioDao municipioDao;
    @Inject private PessoaDao pessoaDao;
    @Inject private GrupoUsuarioService grupoUsuarioService;
    @Inject private RelacaoCFOPDao relacaoCFOPDao;
    @Inject private CFOPService cfopService;
    @Inject private ProdutoDao produtoDao;
    @Inject private ProdutoUnidadeDao produtoUnidadeDao;
    @Inject private ProdutoLoteDao produtoLoteDao;
    @Inject private ProdutoLoteService produtoLoteService;
    @Inject private GrupoTransacaoDao grupoTransacaoDao;
    @Inject private Rotinas rotinas;
    @Inject private VWEstoqueProdutoLoteDao vwEstoqueProdutoLoteDao;
    @Inject private CentroCustoDao centroCustoDao;
    @Inject private PlanoContasDao planoContasDao;
    @Inject private ContaCorrenteDao contaCorrenteDao;
    @Inject private MovimentoFinanceiroDao movimentoFinanceiroDao;
    @Inject private MovimentoFinanceiroService movimentoFinanceiroService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value="/listatransacoesusuario",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<GrupoTransacao> listatransacoesusuario() {
        List<GrupoTransacao> lista = grupoTransacaoDao.selectAllUsuario(rotinas.usuarioLogado().getId_usuario());
        return lista;
    }

    //Pesquisa pela descriçao
    @RequestMapping(value="/carregauf-pais/{id_pais}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Uf> lista_uf(@PathVariable("id_pais") Integer id_pais) {
        List<Uf> lista = ufDao.selectByPais(id_pais);
        return lista;
    }

    //Pesquisa pela descriçao
    @RequestMapping(value="/carregamunicipio-uf/{id_uf}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Municipio> lista_municipio(@PathVariable("id_uf") Integer id_uf) {
        List<Municipio> lista = municipioDao.selectByUf(id_uf);
        return lista;
    }

    //Insere Usuario do Grupo via JSON
    @RequestMapping(value="/inserir_usuariodogrupo/{id_grupousuario}/{id_usuario}/{pertence}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insert_usuariodogrupo(@PathVariable("id_grupousuario") Integer id_grupousuario, @PathVariable("id_usuario") Integer id_usuario, @PathVariable("pertence") boolean pertence) {
        try{
            grupoUsuarioService.insert_usuariodogrupo_byid(id_grupousuario, id_usuario, pertence);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Insere Grupo Transacao via JSON
    @RequestMapping(value="/inserir_grupotransacao/{id_grupousuario}/{id_transacao}/{pertence}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insert_grupotransacao(@PathVariable("id_grupousuario") Integer id_grupousuario, @PathVariable("id_transacao") Integer id_transacao, @PathVariable("pertence") boolean pertence) {
        try{
            grupoUsuarioService.insert_grupotransacao(id_grupousuario, id_transacao, pertence);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Insere Grupo Empresa via JSON
    @RequestMapping(value="/inserir_empresagrupo/{id_grupousuario}/{id_empresa}/{pertence}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insert_empresagrupo(@PathVariable("id_grupousuario") Integer id_grupousuario, @PathVariable("id_empresa") Integer id_empresa, @PathVariable("pertence") boolean pertence) {
        try{
            grupoUsuarioService.insert_empresagrupo(id_grupousuario, id_empresa, pertence);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    //Pesquisa Pessoa CpfCnpj
    @RequestMapping(value="/pessoabycpfcnpj/{cpfcnpj}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Pessoa pessoabycpfcnpj(@PathVariable("cpfcnpj") String cpfcnpj) {
        Pessoa pessoa = pessoaDao.selectByCpfCnpj(cpfcnpj);
        return pessoa;
    }

    @RequestMapping(value="/pessoabyid/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Pessoa pessoabyid(@PathVariable("id") int id) {
        Pessoa pessoa = pessoaDao.selectById(id);
        return pessoa;
    }

    //lista_pessoa
    @RequestMapping(value="/lista_pessoa/{tipo}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Pessoa> lista_pessoa(@PathVariable("tipo") String tipo) {
        Pessoa filtros = new Pessoa();
        List<Pessoa> lista = pessoaDao.selectAll(tipo, filtros);
        return lista;
    }

    @RequestMapping(value="/pessoabyfiltros/{campos}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Pessoa> pessoabyfiltros(@PathVariable String[] campos) {
        Pessoa filtros = new Pessoa();
        String tipo = campos[0];
        filtros.setCpfCnpj(campos[1]);
        filtros.setRazaoSocial(campos[2]);
        List<Pessoa> lista = pessoaDao.selectAll(tipo, filtros);
        return lista;
    }

    @RequestMapping(value="/lista_centrocusto/{campos}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<CentroCusto> lista_centrocusto(@PathVariable String[] campos) {
        CentroCusto filtros = new CentroCusto();
        filtros.setId_Empresa(Integer.parseInt(campos[0]));
        filtros.setSigla(campos[1]);
        filtros.setDescricao(campos[2]);

        List<CentroCusto> lista = centroCustoDao.selectAll(filtros);
        return lista;
    }

    @RequestMapping(value="/centrocustobysigla/{campos}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CentroCusto centrocustobysigla(@PathVariable String[] campos) {
        CentroCusto lista = centroCustoDao.selectbySigla(Integer.parseInt(campos[0]), campos[1]);
        return lista;
    }

    @RequestMapping(value="/centrocustobyid/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    CentroCusto centrocustobysigla(@PathVariable("id") int id) {
        CentroCusto lista = centroCustoDao.selectById(id);
        return lista;
    }

    @RequestMapping(value="/lista_planocontas/{campos}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<PlanoContas> lista_planocontas(@PathVariable String[] campos) {
        PlanoContas filtros = new PlanoContas();
        filtros.setId_Empresa(Integer.parseInt(campos[0]));
        filtros.setTipoConta(campos[1]);
        filtros.setCodigoConta(campos[2]);
        filtros.setDescricao(campos[3]);

        List<PlanoContas> lista = planoContasDao.selectAll(filtros);
        return lista;
    }

    @RequestMapping(value="/planocontasbyid/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PlanoContas planocontasbyid(@PathVariable("id") int id) {
        PlanoContas lista = planoContasDao.selectById(id);
        return lista;
    }

    @RequestMapping(value="/planocontasbycodigo/{campos}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    PlanoContas planocontasbycodigo(@PathVariable String[] campos) {
        PlanoContas filtros = new PlanoContas();
        filtros.setId_Empresa(Integer.parseInt(campos[0]));
        filtros.setTipoConta(campos[1]);
        filtros.setCodigoConta(campos[2]);

        PlanoContas lista = planoContasDao.selectByCodigo(filtros);
        return lista;
    }

    @RequestMapping(value="/relacaocfop_insert/{id_cfop}/{id_cfoprelacao}/{pertence}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void relacaocfop_insert(@PathVariable("id_cfop") int id_cfop, @PathVariable("id_cfoprelacao") int id_cfoprelacao, @PathVariable("pertence") boolean pertence) {
        try{
            cfopService.insertRelacao(id_cfop, id_cfoprelacao, pertence);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @RequestMapping(value="/produtobyid/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Produto produtobyid(@PathVariable("id") int id) {
        return produtoDao.selectByIdRes(id);
    }

    @RequestMapping(value="/produtobyfiltros/{filtros}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<Produto> produtobyfiltros(@PathVariable("filtros") String filtros) {
        return produtoDao.selectAllResFiltros(filtros);
    }

    @RequestMapping(value="/estoqueprodutobylote/{id_empresa}/{id_deposito}/{id_cfop}/{id_produtolote}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<VWEstoqueProdutoLote> estoqueprodutobylote(@PathVariable("id_empresa") int id_empresa, @PathVariable("id_deposito") int id_deposito, @PathVariable("id_cfop") int id_cfop, @PathVariable("id_produtolote") int id_produtolote) {
        Filtro_Produto filtro_produto = new Filtro_Produto();
        filtro_produto.setId_Empresa(id_empresa);
        filtro_produto.setId_Deposito(id_deposito);
        filtro_produto.setId_CFOP(id_cfop);
        filtro_produto.setId_ProdutoLote(id_produtolote);
        filtro_produto.setId_Produto(0);
        filtro_produto.setDescricao("");
        filtro_produto.setNumeroLote("");

        return vwEstoqueProdutoLoteDao.AllByFiltros(filtro_produto);
    }

    @RequestMapping(value="/estoqueproduto/{id_empresa}/{id_deposito}/{id_cfop}/{id_produto}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<VWEstoqueProdutoLote> estoqueprodutobyidproduto(@PathVariable("id_empresa") int id_empresa, @PathVariable("id_deposito") int id_deposito, @PathVariable("id_cfop") int id_cfop, @PathVariable("id_produto") int id_produto) {
        Filtro_Produto filtro_produto = new Filtro_Produto();
        filtro_produto.setId_Empresa(id_empresa);
        filtro_produto.setId_Deposito(id_deposito);
        filtro_produto.setId_CFOP(id_cfop);
        filtro_produto.setId_Produto(id_produto);
        filtro_produto.setDescricao("");
        filtro_produto.setNumeroLote("");

        return vwEstoqueProdutoLoteDao.AllByFiltros(filtro_produto);
    }

    @RequestMapping(value="/lista_produtounidade/{id_produto}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<ProdutoUnidade> lista_produtounidade(@PathVariable("id_produto") int id) {
        return produtoUnidadeDao.selectAll(id);
    }

    @RequestMapping(value="/produtolotebynumerolote/{id_Produto}/{NumeroLote}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    ProdutoLote produtolotebynumero(@PathVariable("id_Produto") int id_Produto, @PathVariable("NumeroLote") String numerolote) {
        return produtoLoteDao.selectByNumeroLote(id_Produto, numerolote);
    }

    //Insere Usuario do Grupo via JSON
    @RequestMapping(value="/insert_produtolote/{id_Produto}/{numerolote}/{fabricacao}/{validade}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void insert_produtolote(@PathVariable("id_Produto") int id_Produto, @PathVariable("numerolote") String numerolote, @PathVariable("fabricacao") String fabricacao, @PathVariable("validade") String validade) throws ParseException {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = format.parse(fabricacao);
        Date date2 = format.parse(validade);

        ProdutoLote produtoLote = new ProdutoLote();
        produtoLote.setId_Produto(id_Produto);
        produtoLote.setNumeroLote(numerolote);
        produtoLote.setDataFabricacao(date1);
        produtoLote.setDataValidade(date2);
        try{
            produtoLoteDao.insert(produtoLote);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @RequestMapping(value="/lista_contacorrente/{campos}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<ContaCorrente> lista_contacorrente(@PathVariable int[] campos) {
        ContaCorrente filtros = new ContaCorrente();
        filtros.setId_Empresa(campos[0]);
        filtros.setId_Banco(campos[1]);

        return contaCorrenteDao.selectAll(filtros);
    }

    @RequestMapping(value="/pesquisa_movimentofinanceiro/{id}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    MovimentoFinanceiro pesquisa_movimentofinanceiro(@PathVariable int id) {

        return movimentoFinanceiroDao.selectById(id);
    }

    @RequestMapping(value="/cancelar_movimentofinanceiro/{campos}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void cancelar_movimentofinanceiro(@PathVariable String[] campos) {

        MovimentoFinanceiro movimentoFinanceiro = movimentoFinanceiroDao.selectById(Integer.parseInt(campos[0]));
        movimentoFinanceiro.setMotivoCancelamento(campos[1]);
        movimentoFinanceiro.setStatusMovimento("C");

        try{
            movimentoFinanceiroService.update(movimentoFinanceiro);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @RequestMapping(value="/liquidar_movimentofinanceiro/{campos}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void liquidar_movimentofinanceiro(@PathVariable String[] campos) {

        MovimentoFinanceiro movimentoFinanceiro = movimentoFinanceiroDao.selectById(Integer.parseInt(campos[0]));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String data = campos[1];
        Date date = null;
        try {
            date = formatter.parse(data);
        } catch (ParseException e) {
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }

        movimentoFinanceiro.setDataLiquidacao(date);
        movimentoFinanceiro.setConciliado(false);
        if (campos[2].equals("S")){
            movimentoFinanceiro.setConciliado(true);
        }
        String operacao = campos[3];

        try{
            if (operacao.equals("L")){
                movimentoFinanceiroService.liquidar(movimentoFinanceiro);
            } else{
                movimentoFinanceiroService.estornar(movimentoFinanceiro);
            }
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

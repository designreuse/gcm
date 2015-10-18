package com.br.gcm.controller;

import com.br.gcm.dao.LancamentoEstoqueDao;
import com.br.gcm.dao.LancamentoEstoqueItemDao;
import com.br.gcm.dao.CFOPDao;
import com.br.gcm.dao.ProdutoDao;
import com.br.gcm.dao.ProdutoLoteDao;
import com.br.gcm.dao.DepositoDao;
import com.br.gcm.model.LancamentoEstoque;
import com.br.gcm.model.LancamentoEstoqueItem;
import com.br.gcm.model.CFOP;
import com.br.gcm.model.ProdutoLote;
import com.br.gcm.model.filtros.Filtro_Produto;
import com.br.gcm.service.LancamentoEstoqueService;
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
import java.util.Date;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 09/05/15
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class LancamentoEstoqueController {
    @Inject private LancamentoEstoqueDao lancamentoEstoqueDao;
    @Inject private LancamentoEstoqueItemDao lancamentoEstoqueItemDao;
    @Inject private LancamentoEstoqueService lancamentoEstoqueService;
    @Inject private Rotinas rotinas;
    @Inject private EmpresaDao empresaDao;
    @Inject private CFOPDao cfopDao;
    @Inject private ProdutoDao produtoDao;
    @Inject private ProdutoLoteDao produtoLoteDao;
    @Inject private DepositoDao depositoDao;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    //Filtros
    @RequestMapping(value = "/lancamentoestoque_filtro/{TipoEntSai}/{id_empresa}")
    public String lista(@PathVariable("TipoEntSai") String tipoEntSai, @PathVariable("id_empresa") Integer id_empresa, @PageableDefault(size = 10) Pageable pageable, Model model) {

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("filtro", id_empresa);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", lancamentoEstoqueDao.selectAll_paginado(tipoEntSai, id_empresa , pageable));
        model.addAttribute("pagina", new Pagina(pageable, lancamentoEstoqueDao.count(tipoEntSai, id_empresa)));
        return "lancamentoestoque_lista";
    }

    //Listar
    @RequestMapping(value = "/lancamentoestoque_lista/{TipoEntSai}")
    public String lista(@PathVariable("TipoEntSai") String tipoEntSai, @PageableDefault(size = 10) Pageable pageable, Model model) {

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        Empresa empresa = listaEmpresa.get(0);

        model.addAttribute("filtro", empresa.getId_Empresa());
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", lancamentoEstoqueDao.selectAll_paginado(tipoEntSai, empresa.getId_Empresa() , pageable));
        model.addAttribute("pagina", new Pagina(pageable, lancamentoEstoqueDao.count(tipoEntSai, empresa.getId_Empresa())));
        return "lancamentoestoque_lista";
    }

    //Deletar
    @RequestMapping(value = "/lancamentoestoque_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            lancamentoEstoqueService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/lancamentoestoque_lista";
    }

    //Nova
    @RequestMapping(value = "/lancamentoestoque_novo/{TipoEntSai}", method = RequestMethod.GET)
    public String novo(@PathVariable("TipoEntSai") String tipoEntSai, ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        CFOP filtros = new CFOP();
        filtros.setTipo(tipoEntSai);
        filtros.setAjuste(Boolean.TRUE);

        LancamentoEstoque lancamentoEstoque = new LancamentoEstoque();
        model.addAttribute("listacfop", cfopDao.selectAll(filtros));
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lancamentoestoque", lancamentoEstoque);
        return "lancamentoestoque_novo";
    }

    //Insert
    @RequestMapping(value = "/lancamentoestoque_insert/{TipoEntSai}", method = RequestMethod.POST)
    public ModelAndView insert(@PathVariable("TipoEntSai") String tipoEntSai, @ModelAttribute LancamentoEstoque lancamentoEstoque, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            lancamentoEstoqueService.insert(lancamentoEstoque);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", lancamentoEstoqueDao.selectAll_paginado(tipoEntSai, lancamentoEstoque.getId_Empresa(), pageable));
        mav.addObject("pagina", new Pagina(pageable, lancamentoEstoqueDao.count(tipoEntSai, lancamentoEstoque.getId_Empresa())));
        mav.setViewName("redirect:/lancamentoestoque_editar/"+tipoEntSai+"/"+lancamentoEstoque.getId_LancamentoEstoque());
        return mav;
    }

    //Editar
    @RequestMapping(value = "/lancamentoestoque_editar/{TipoEntSai}/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("TipoEntSai") String tipoEntSai, @PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        CFOP filtros = new CFOP();
        filtros.setTipo(tipoEntSai);
        filtros.setAjuste(Boolean.TRUE);

        LancamentoEstoque lancamentoEstoque = lancamentoEstoqueDao.selectById(id);

        model.addAttribute("id_lancamentoestoque", id);
        model.addAttribute("listacfop", cfopDao.selectAll(filtros));
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("listaitens", lancamentoEstoqueItemDao.selectAll(id));
        model.addAttribute("lancamentoestoque", lancamentoEstoque);
        return "lancamentoestoque_editar";
    }

    //Update
    @RequestMapping(value = "/lancamentoestoque_update/{TipoEntSai}", method = RequestMethod.PUT)
    public ModelAndView update(@PathVariable("TipoEntSai") String tipoEntSai, @ModelAttribute LancamentoEstoque lancamentoEstoque, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            lancamentoEstoqueService.update(lancamentoEstoque);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", lancamentoEstoqueDao.selectAll_paginado(tipoEntSai, lancamentoEstoque.getId_Empresa() ,pageable));
        mav.addObject("pagina", new Pagina(pageable, lancamentoEstoqueDao.count(tipoEntSai, lancamentoEstoque.getId_Empresa())));
        mav.setViewName("redirect:/lancamentoestoque_lista/"+tipoEntSai);
        return mav;
    }

    //Novo Item
    @RequestMapping(value = "/lancamentoestoqueitem_novo/{TipoEntSai}/{id_lancamentoestoque}", method = RequestMethod.GET)
    public String novoitem(@PathVariable("TipoEntSai") String tipoEntSai, @PathVariable("id_lancamentoestoque") int id_lancamentoestoque, ModelMap model) {

        LancamentoEstoqueItem lancamentoEstoqueItem = new LancamentoEstoqueItem();
        LancamentoEstoque lancamentoEstoque = lancamentoEstoqueDao.selectById(id_lancamentoestoque);
        ProdutoLote produtoLote = new ProdutoLote();
        lancamentoEstoqueItem.setId_LancamentoEstoque(id_lancamentoestoque);

        model.addAttribute("lancamentoestoque", lancamentoEstoque);
        model.addAttribute("listadeposito", depositoDao.selectAll(lancamentoEstoque.getId_Empresa()));
        model.addAttribute("lancamentoestoqueitem", lancamentoEstoqueItem);
        model.addAttribute("produtolote", produtoLote);

        String retorno;
        if (tipoEntSai.compareTo("E") == 0)
        {
            retorno="lancamentoestoqueitem_ent_novo";
        }else{
            retorno="lancamentoestoqueitem_sai_novo";
        }

        return retorno;
    }

    //INSERT ITEM
    @RequestMapping(value = "/lancamentoestoqueitem_insert/{TipoEntSai}", method = RequestMethod.POST)
    public ModelAndView insertitem(@PathVariable("TipoEntSai") String tipoEntSai, @ModelAttribute LancamentoEstoqueItem lancamentoEstoqueItem, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            lancamentoEstoqueService.insertitem(lancamentoEstoqueItem);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        LancamentoEstoque lancamentoEstoque = lancamentoEstoqueDao.selectById(lancamentoEstoqueItem.getId_LancamentoEstoque());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", lancamentoEstoqueDao.selectAll_paginado(tipoEntSai, lancamentoEstoque.getId_Empresa(), pageable));
        mav.addObject("pagina", new Pagina(pageable, lancamentoEstoqueDao.count(tipoEntSai, lancamentoEstoque.getId_Empresa())));
        mav.setViewName("redirect:/lancamentoestoque_editar/"+tipoEntSai+"/"+lancamentoEstoque.getId_LancamentoEstoque());
        return mav;
    }

    @RequestMapping(value = "/lancamentoestoqueitem_delete/{TipoEntSai}/{id_lancamento}/{id_item}")
    public ModelAndView deleteitem(@PathVariable("TipoEntSai") String tipoEntSai, @PathVariable("id_lancamento") int id_lancamento, @PathVariable("id_item") int id_item, @PageableDefault(size = 10) Pageable pageable) {
        try{
            lancamentoEstoqueService.deleteitem(id_item);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        LancamentoEstoque lancamentoEstoque = lancamentoEstoqueDao.selectById(id_lancamento);

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", lancamentoEstoqueDao.selectAll_paginado(tipoEntSai, lancamentoEstoque.getId_Empresa(), pageable));
        mav.addObject("pagina", new Pagina(pageable, lancamentoEstoqueDao.count(tipoEntSai, lancamentoEstoque.getId_Empresa())));
        mav.setViewName("redirect:/lancamentoestoque_editar/"+tipoEntSai+"/"+lancamentoEstoque.getId_LancamentoEstoque());
        return mav;
    }

    //Editar Item
    @RequestMapping(value = "/lancamentoestoqueitem_editar/{TipoEntSai}/{id_lancamentoestoque}/{id_item}", method = RequestMethod.GET)
    public String editaritem(@PathVariable("TipoEntSai") String tipoEntSai, @PathVariable("id_lancamentoestoque") int id_lancamentoestoque, @PathVariable("id_item") int id_item, ModelMap model) {

        LancamentoEstoqueItem lancamentoEstoqueItem = lancamentoEstoqueItemDao.selectById(id_item);
        LancamentoEstoque lancamentoEstoque = lancamentoEstoqueDao.selectById(id_lancamentoestoque);

        model.addAttribute("id_lancamentoestoque", id_lancamentoestoque);
        model.addAttribute("lancamentoestoqueitem", lancamentoEstoqueItem);
        model.addAttribute("lancamentoestoque", lancamentoEstoque);
        model.addAttribute("produtolote", produtoLoteDao.selectById(lancamentoEstoqueItem.getId_ProdutoLote()));
        model.addAttribute("produto", produtoDao.selectByIdRes(lancamentoEstoqueItem.getProdutoLote().getId_Produto()));
        model.addAttribute("listadeposito", depositoDao.selectAll(lancamentoEstoque.getId_Empresa()));

        String retorno;
        if (tipoEntSai.compareTo("E") == 0)
        {
            retorno="lancamentoestoqueitem_ent_editar";
        }else{
            retorno="lancamentoestoqueitem_sai_editar";
        }

        return retorno;
    }

    @RequestMapping(value = "/lancamentoestoqueitem_update/{TipoEntSai}", method = RequestMethod.PUT)
    public ModelAndView updateitem(@PathVariable("TipoEntSai") String tipoEntSai, @ModelAttribute LancamentoEstoqueItem lancamentoEstoqueItem, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            lancamentoEstoqueService.updateitem(lancamentoEstoqueItem);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        LancamentoEstoque lancamentoEstoque = lancamentoEstoqueDao.selectById(lancamentoEstoqueItem.getId_LancamentoEstoque());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", lancamentoEstoqueDao.selectAll_paginado(tipoEntSai, lancamentoEstoque.getId_Empresa(), pageable));
        mav.addObject("pagina", new Pagina(pageable, lancamentoEstoqueDao.count(tipoEntSai, lancamentoEstoque.getId_Empresa())));
        mav.setViewName("redirect:/lancamentoestoque_editar/"+tipoEntSai+"/"+lancamentoEstoque.getId_LancamentoEstoque());
        return mav;
    }
}

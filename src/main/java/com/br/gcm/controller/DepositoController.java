package com.br.gcm.controller;

import com.br.gcm.dao.DepositoDao;
import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.model.Deposito;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Empresa;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.DepositoService;
import com.br.gcm.tag.Pagina;
import com.br.gcm.util.Rotinas;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 19/04/15
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class DepositoController {
    @Inject private DepositoDao depositoDao;
    @Inject private DepositoService depositoService;
    @Inject private Rotinas rotinas;
    @Inject private EmpresaDao empresaDao;

    //Listar
    @RequestMapping(value = "/deposito_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1105");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        Deposito filtros = new Deposito();

        if (!listaEmpresa.isEmpty()){
            filtros.setId_Empresa(listaEmpresa.get(0).getId_Empresa());
        };

        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110501");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110502");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110503");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110504");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("filtros", filtros);
        model.addAttribute("deposito_lista", depositoDao.selectAll_paginado(filtros, pageable));
        model.addAttribute("lista_empresa", listaEmpresa);
        model.addAttribute("pagina", new Pagina(pageable, depositoDao.count(filtros)));

        return "deposito_lista";
    }

    @RequestMapping(value = "/deposito_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute Deposito filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1105");
        if (lista != true) {
            mav.addObject("mensagem", "AVISO: Transação não permitida.");
            mav.setViewName("mensagemerro");
            return mav;
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110501");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110502");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110503");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110504");

        mav.addObject("novo", novo);
        mav.addObject("editar", editar);
        mav.addObject("deletar", deletar);
        mav.addObject("detalhes", detalhes);
        mav.addObject("filtros", filtros);
        mav.addObject("deposito_lista", depositoDao.selectAll_paginado(filtros, pageable));
        mav.addObject("lista_empresa", listaEmpresa);
        mav.addObject("pagina", new Pagina(pageable, depositoDao.count(filtros)));

        mav.setViewName("deposito_lista");
        return mav;
    }

    //Deletar
    @RequestMapping(value = "/deposito_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110503");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            depositoService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/deposito_lista";
    }

    //Nova
    @RequestMapping(value = "/deposito_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110501");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        Deposito deposito = new Deposito();
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("deposito", deposito);
        return "deposito_novo";
    }

    //Insert
    @RequestMapping(value = "/deposito_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute Deposito deposito, Model model) {
        try{
            depositoService.insert(deposito);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }

        return "redirect:/deposito_lista";
    }

    //Editar
    @RequestMapping(value = "/deposito_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110502");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("deposito", depositoDao.selectById(id));
        return "deposito_editar";
    }

    //Update
    @RequestMapping(value = "/deposito_update", method = RequestMethod.POST)
    public String update(@ModelAttribute Deposito deposito, Model model) {
        try{
            depositoService.update(deposito);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/deposito_lista";
    }

    @RequestMapping(value = "/deposito_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110504");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("deposito", depositoDao.selectById(id));
        return "deposito_detalhes";
    }
}

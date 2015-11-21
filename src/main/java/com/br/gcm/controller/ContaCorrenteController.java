package com.br.gcm.controller;

import com.br.gcm.dao.BancoDao;
import com.br.gcm.dao.ContaCorrenteDao;
import com.br.gcm.model.ContaCorrente;
import com.br.gcm.service.ContaCorrenteService;
import com.br.gcm.tag.Pagina;
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

import com.br.gcm.model.Usuario;
import com.br.gcm.model.Empresa;
import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.util.Rotinas;

import javax.inject.Inject;
import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 06/05/15
 * Time: 08:38
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class ContaCorrenteController {
    @Inject private ContaCorrenteDao contaCorrenteDao;
    @Inject private ContaCorrenteService contaCorrenteService;
    @Inject private Rotinas rotinas;
    @Inject private EmpresaDao empresaDao;
    @Inject private BancoDao bancoDao;

    //Listar
    @RequestMapping(value = "/contacorrente_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1504");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ContaCorrente filtros = new ContaCorrente();
        if (!listaEmpresa.isEmpty()){
            filtros.setId_Empresa(listaEmpresa.get(0).getId_Empresa());
        };

        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150401");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150402");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150403");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150404");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("filtros", filtros);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", contaCorrenteDao.selectAll(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, contaCorrenteDao.count(filtros)));
        return "contacorrente_lista";
    }

    //Filtros
    @RequestMapping(value = "/contacorrente_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute ContaCorrente filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1504");
        if (lista != true) {
            mav.addObject("mensagem", "AVISO: Transação não permitida.");
            mav.setViewName("mensagemerro");
            return mav;
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150401");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150402");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150403");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150404");

        mav.addObject("novo", novo);
        mav.addObject("editar", editar);
        mav.addObject("deletar", deletar);
        mav.addObject("detalhes", detalhes);
        mav.addObject("filtros", filtros);
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", contaCorrenteDao.selectAll(filtros, pageable));
        mav.addObject("pagina", new Pagina(pageable, contaCorrenteDao.count(filtros)));
        mav.setViewName("contacorrente_lista");
        return mav;
    }

    //Deletar
    @RequestMapping(value = "/contacorrente_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150403");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            contaCorrenteService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/contacorrente_lista";
    }

    //Nova
    @RequestMapping(value = "/contacorrente_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150401");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        ContaCorrente contaCorrente = new ContaCorrente();
        model.addAttribute("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        model.addAttribute("listabanco", bancoDao.selectAll());
        model.addAttribute("contacorrente", contaCorrente);
        return "contacorrente_novo";
    }

    //Insert
    @RequestMapping(value = "/contacorrente_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute ContaCorrente contaCorrente, Model model) {
        try{
            contaCorrente.setSaldoConta(0);
            contaCorrenteService.insert(contaCorrente);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/contacorrente_lista";
    }

    //Editar
    @RequestMapping(value = "/contacorrente_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150402");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        model.addAttribute("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        model.addAttribute("listabanco", bancoDao.selectAll());
        model.addAttribute("contacorrente", contaCorrenteDao.selectById(id));
        return "contacorrente_editar";
    }

    //Update
    @RequestMapping(value = "/contacorrente_update", method = RequestMethod.POST)
    public String update(@ModelAttribute ContaCorrente contaCorrente, Model model) {
        try{
            contaCorrenteService.update(contaCorrente);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/contacorrente_lista";
    }

    @RequestMapping(value = "/contacorrente_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150404");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        model.addAttribute("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        model.addAttribute("listabanco", bancoDao.selectAll());
        model.addAttribute("contacorrente", contaCorrenteDao.selectById(id));
        return "contacorrente_detalhes";
    }
}

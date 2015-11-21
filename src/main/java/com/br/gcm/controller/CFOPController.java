package com.br.gcm.controller;

import com.br.gcm.dao.CFOPDao;
import com.br.gcm.dao.RelacaoCFOPDao;
import com.br.gcm.model.CFOP;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.CFOPService;
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

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 30/04/15
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class CFOPController {
    @Inject private CFOPDao cfopDao;
    @Inject private CFOPService cfopService;
    @Inject private RelacaoCFOPDao relacaoCFOPDao;
    @Inject private Rotinas rotinas;

    //Listar
    @RequestMapping(value = "/cfop_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        CFOP filtros = new CFOP();
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1403");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140301");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140302");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140303");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140304");


        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);

        model.addAttribute("filtros", filtros);
        model.addAttribute("lista", cfopDao.selectAll(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, cfopDao.count(filtros)));
        return "cfop_lista";
    }

    @RequestMapping(value = "/cfop_lista", method = RequestMethod.POST)
    public String filtros(@ModelAttribute CFOP filtros, @PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1403");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140301");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140302");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140303");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140304");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);

        model.addAttribute("filtros", filtros);
        model.addAttribute("lista", cfopDao.selectAll(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, cfopDao.count(filtros)));
        return "cfop_lista";
    }

    //Listar  Relacao
    @RequestMapping(value = "/relacaocfop_lista/{id_CFOP}")
    public String listareferencia(@PathVariable("id_CFOP") Integer id_CFOP, Model model) {
        model.addAttribute("lista", relacaoCFOPDao.selectAll(id_CFOP));
        model.addAttribute("cfop", cfopDao.selectById(id_CFOP));
        return "relacaocfop_lista";
    }

    //Deletar
    @RequestMapping(value = "/cfop_deletar/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140303");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            cfopService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/cfop_lista";
    }

    //Nova
    @RequestMapping(value = "/cfop_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140301");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        CFOP cfop = new CFOP();
        model.addAttribute("cfop", cfop);
        return "cfop_novo";
    }

    //Insert
    @RequestMapping(value = "/cfop_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute CFOP cfop, Model model) {
        try{
            cfopService.insert(cfop);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/cfop_lista";
    }

    //Editar
    @RequestMapping(value = "/cfop_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140302");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        CFOP cfop = cfopDao.selectById(id);
        model.addAttribute("cfop", cfop);
        return "cfop_editar";
    }

    //Update
    @RequestMapping(value = "/cfop_update", method = RequestMethod.POST)
    public String update(@ModelAttribute CFOP cfop, Model model) {
        try{
            cfopService.update(cfop);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/cfop_lista";
    }

    @RequestMapping(value = "/cfop_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "140304");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        CFOP cfop = cfopDao.selectById(id);
        model.addAttribute("cfop", cfop);
        return "cfop_detalhes";
    }
}

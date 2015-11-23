package com.br.gcm.controller;

import com.br.gcm.dao.BancoDao;
import com.br.gcm.model.Banco;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.BancoService;
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
 * Date: 05/05/15
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class BancoController {
    @Inject private BancoDao bancoDao;
    @Inject private BancoService bancoService;
    @Inject private Rotinas rotinas;

    @RequestMapping(value = "/mensagemerro/{mensagem}", method = RequestMethod.GET)
    public String erro(@PathVariable String[] mensagem, Model model) {
        model.addAttribute("mensagem", mensagem);
        return "mensagemerro";
    }

    //Listar
    @RequestMapping(value = "/banco_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        // Valida Transação do Usuário
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1503");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150301");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150302");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150303");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150304");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("lista", bancoDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, bancoDao.count()));
        return "banco_lista";
    }

    //Deletar
    @RequestMapping(value = "/banco_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        // Valida Transação do Usuário
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150303");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            bancoService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/banco_lista";
    }

    //Nova
    @RequestMapping(value = "/banco_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        // Valida Transação do Usuário
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150301");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Banco banco = new Banco();
        model.addAttribute("banco", banco);
        return "banco_novo";
    }

    //Insert
    @RequestMapping(value = "/banco_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute Banco banco, Model model) {
        try{
            bancoService.insert(banco);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/banco_lista";
    }

    //Editar
    @RequestMapping(value = "/banco_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        // Valida Transação do Usuário
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150302");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Banco banco = bancoDao.selectById(id);
        model.addAttribute("banco", banco);
        return "banco_editar";
    }

    //Update
    @RequestMapping(value = "/banco_update", method = RequestMethod.POST)
    public String update(@ModelAttribute Banco banco, Model model) {
        try{
            bancoService.update(banco);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/banco_lista";
    }

    @RequestMapping(value = "/banco_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        // Valida Transação do Usuário
        Usuario usuario = rotinas.usuarioLogado();
        if (!rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150304")) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Banco banco = bancoDao.selectById(id);
        model.addAttribute("banco", banco);
        return "banco_detalhes";
    }
}

package com.br.gcm.controller;

import com.br.gcm.dao.GrupoProdutoDao;
import com.br.gcm.model.GrupoProduto;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.GrupoProdutoService;
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
 * Date: 18/04/15
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class GrupoProdutoController {
    @Inject private GrupoProdutoDao grupoProdutoDao;
    @Inject private GrupoProdutoService grupoProdutoService;
    @Inject private Rotinas rotinas;

     //Listar
    @RequestMapping(value = "/grupoproduto_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1101");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110101");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110102");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110103");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110104");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("grupoproduto_lista", grupoProdutoDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, grupoProdutoDao.count()));

        return "grupoproduto_lista";
    }

    //Deletar
    @RequestMapping(value = "/grupoproduto_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1103");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            grupoProdutoService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/grupoproduto_lista";
    }

    //Nova
    @RequestMapping(value = "/grupoproduto_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110101");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        GrupoProduto grupoProduto = new GrupoProduto();
        model.addAttribute("grupoproduto", grupoProduto);
        return "grupoproduto_novo";
    }

    //Insert
    @RequestMapping(value = "/grupoproduto_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute GrupoProduto grupoProduto, Model model) {
        try{
            grupoProdutoService.insert(grupoProduto);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }

        return "redirect:/grupoproduto_lista";
    }

    //Editar
    @RequestMapping(value = "/grupoproduto_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110102");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        GrupoProduto grupoProduto = grupoProdutoDao.selectById(id);
        model.addAttribute("grupoproduto", grupoProduto);
        return "grupoproduto_editar";
    }

    //Update
    @RequestMapping(value = "/grupoproduto_update", method = RequestMethod.POST)
    public String update(@ModelAttribute GrupoProduto grupoProduto, Model model) {
        try{
            grupoProdutoService.update(grupoProduto);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }

        return "redirect:/grupoproduto_lista";
    }

    @RequestMapping(value = "/grupoproduto_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110104");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        GrupoProduto grupoProduto = grupoProdutoDao.selectById(id);
        model.addAttribute("grupoproduto", grupoProduto);
        return "grupoproduto_detalhes";
    }
}

package com.br.gcm.controller;

import com.br.gcm.dao.MarcaProdutoDao;
import com.br.gcm.model.MarcaProduto;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.MarcaProdutoService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 18/04/15
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class MarcaProdutoController {
    @Inject private MarcaProdutoDao marcaProdutoDao;
    @Inject private MarcaProdutoService marcaProdutoService;
    @Inject private Rotinas rotinas;

    //Listar
    @RequestMapping(value = "/marcaproduto_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        MarcaProduto filtros = new MarcaProduto();

        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1103");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110301");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110302");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110303");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110304");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);

        model.addAttribute("filtros", filtros);
        model.addAttribute("marcaproduto_lista",  marcaProdutoDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, marcaProdutoDao.count()));
        return "marcaproduto_lista";
    }

    @RequestMapping(value = "/marcaproduto_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute MarcaProduto filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();

        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1103");
        if (lista != true) {
            mav.addObject("mensagem", "AVISO: Transação não permitida.");
            mav.setViewName("mensagemerro");
            return mav;
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110301");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110302");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110303");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110304");

        mav.addObject("novo", novo);
        mav.addObject("editar", editar);
        mav.addObject("deletar", deletar);
        mav.addObject("detalhes", detalhes);
        mav.addObject("filtros", filtros);
        mav.addObject("marcaproduto_lista",  marcaProdutoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, marcaProdutoDao.count()));

        mav.setViewName("deposito_lista");
        return mav;
    }

    //Deletar
    @RequestMapping(value = "/marcaproduto_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110303");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            marcaProdutoService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/marcaproduto_lista";
    }

    //Nova
    @RequestMapping(value = "/marcaproduto_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110301");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        MarcaProduto marcaProduto = new MarcaProduto();
        model.addAttribute("marcaproduto", marcaProduto);
        return "marcaproduto_novo";
    }

    //Insert
    @RequestMapping(value = "/marcaproduto_gravar", method = RequestMethod.POST)
    public String insert(@ModelAttribute MarcaProduto marcaProduto, Model model) {
        try{
            marcaProdutoService.insert(marcaProduto);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/marcaproduto_lista";
    }

    //Editar
    @RequestMapping(value = "/marcaproduto_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110302");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        MarcaProduto marcaProduto = marcaProdutoDao.selectById(id);
        model.addAttribute("marcaproduto", marcaProduto);
        return "marcaproduto_editar";
    }

    //Update
    @RequestMapping(value = "/marcaproduto_update", method = RequestMethod.POST)
    public String update(@ModelAttribute MarcaProduto marcaProduto, Model model) {
        try{
            marcaProdutoService.update(marcaProduto);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/marcaproduto_lista";
    }

    @RequestMapping(value = "/marcaproduto_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1104");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        MarcaProduto marcaProduto = marcaProdutoDao.selectById(id);
        model.addAttribute("marcaproduto", marcaProduto);
        return "marcaproduto_detalhes";
    }
}

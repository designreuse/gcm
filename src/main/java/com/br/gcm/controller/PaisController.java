package com.br.gcm.controller;

import com.br.gcm.dao.PaisDao;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Pais;
import com.br.gcm.model.Usuario;
import com.br.gcm.model.filtros.Filtro_Pais;
import com.br.gcm.service.PaisService;
import com.br.gcm.tag.Pagina;
import com.br.gcm.util.Rotinas;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.swing.*;

@Controller
public class PaisController {
    @Inject private PaisDao paisDao;
    @Inject private PaisService paisService;
    @Inject private Rotinas rotinas;

    @RequestMapping(value = "/pais_lista")
    public String pais_lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1001");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100101");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100102");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100103");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100104");

        Filtro_Pais filtros = new Filtro_Pais();

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("pais_lista", paisDao.Pais_Paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, paisDao.count(filtros)));
        model.addAttribute("filtros", filtros);

        return "pais_lista";
    }

    //Filtros
    @RequestMapping(value = "/pais_lista", method = RequestMethod.POST)
    public String filtros(@ModelAttribute Filtro_Pais filtros, Model model, @PageableDefault(size = 10) Pageable pageable) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1001");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100101");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100102");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100103");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100104");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("pais_lista", paisDao.Pais_Paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, paisDao.count(filtros)));
        model.addAttribute("filtros", filtros);

        return "pais_lista";
    }

    //Deleta Pais
    @RequestMapping(value = "/pais_deleta/{id_pais}")
    public String deletar(@PathVariable("id_pais") Integer id_pais, Model model) throws ServletException {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100103");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            paisService.delete(id_pais);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pais_lista";
    }

    //Novo Pais
    @RequestMapping(value = "/pais_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100101");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Pais pais = new Pais();
        model.addAttribute("pais", pais);
        return "pais_novo";
    }

    //Gravar
    @RequestMapping(value = "/pais_gravar", method = RequestMethod.POST)
    public String insert(@ModelAttribute Pais pais, Model model) {
        try{
            paisService.insert(pais);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pais_lista";
    }

    @RequestMapping(value = "/editar_pais/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100102");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Pais pais = paisDao.selectById(id);
        model.addAttribute("pais", pais);
        return "pais_editar";
    }

    @RequestMapping(value = "/alterar_pais", method = RequestMethod.POST)
    public String update(@ModelAttribute Pais pais, Model model) {
        try{
            paisService.update(pais);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pais_lista";
    }

    @RequestMapping(value = "/pais_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100104");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Pais pais = paisDao.selectById(id);
        model.addAttribute("pais", pais);
        return "pais_detalhes";
    }
}

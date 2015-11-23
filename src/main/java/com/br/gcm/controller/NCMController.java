package com.br.gcm.controller;

import com.br.gcm.dao.NCMDao;
import com.br.gcm.dao.SituacaoTributariaPISCOFINSDao;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.NCM;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.NCMService;
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
 * Date: 19/04/15
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class NCMController {
    @Inject private NCMDao ncmDao;
    @Inject private NCMService ncmService;
    @Inject private SituacaoTributariaPISCOFINSDao situacaoTributariaPISCOFINSDao;
    @Inject private Rotinas rotinas;

    //Listar
    @RequestMapping(value = "/ncm_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1104");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110401");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110402");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110403");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110404");

        NCM filtros = new NCM();

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("filtros", filtros);
        model.addAttribute("ncm_lista", ncmDao.selectAll_paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, ncmDao.count(filtros)));

        return "ncm_lista";
    }

    @RequestMapping(value = "/ncm_lista", method = RequestMethod.POST)
    public String filtros(@ModelAttribute NCM filtros, Model model, @PageableDefault(size = 10) Pageable pageable) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1104");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110401");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110402");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110403");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110404");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("ncm_lista", ncmDao.selectAll_paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, ncmDao.count(filtros)));
        model.addAttribute("filtros", filtros);

        return "ncm_lista";
    }

    //Deletar
    @RequestMapping(value = "/ncm_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110403");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            ncmService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/ncm_lista";
    }

    //Nova
    @RequestMapping(value = "/ncm_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110401");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        NCM ncm = new NCM();
        model.addAttribute("ncm", ncm);
        model.addAttribute("lista_cst", situacaoTributariaPISCOFINSDao.selectAll());
        return "ncm_novo";
    }

    //Insert
    @RequestMapping(value = "/ncm_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute NCM ncm, Model model) {
        try{
            ncmService.insert(ncm);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/ncm_lista";
    }

    //Editar
    @RequestMapping(value = "/ncm_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110402");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        NCM ncm = ncmDao.selectById(id);
        model.addAttribute("ncm", ncm);
        model.addAttribute("lista_cst", situacaoTributariaPISCOFINSDao.selectAll());
        return "ncm_editar";
    }

    //Update
    @RequestMapping(value = "/ncm_update", method = RequestMethod.POST)
    public String update(@ModelAttribute NCM ncm, Model model) {
        try{
            ncmService.update(ncm);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/ncm_lista";
    }

    //Editar
    @RequestMapping(value = "/ncm_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "110404");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        NCM ncm = ncmDao.selectById(id);
        model.addAttribute("ncm", ncm);
        model.addAttribute("lista_cst", situacaoTributariaPISCOFINSDao.selectAll());
        return "ncm_detalhes";
    }
}

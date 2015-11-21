package com.br.gcm.controller;

import com.br.gcm.dao.GrupoUsuarioDao;
import com.br.gcm.dao.UsuariodoGrupoDao;
import com.br.gcm.dao.GrupoTransacaoDao;
import com.br.gcm.dao.EmpresaGrupoDao;
import com.br.gcm.model.*;
import com.br.gcm.service.GrupoUsuarioService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 16/04/15
 * Time: 08:48
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class GrupoUsuarioController {
    @Inject private GrupoUsuarioDao grupoUsuarioDao;
    @Inject private GrupoUsuarioService grupoUsuarioService;
    @Inject private UsuariodoGrupoDao usuariodoGrupoDao;
    @Inject private GrupoTransacaoDao grupoTransacaoDao;
    @Inject private EmpresaGrupoDao empresaGrupoDao;
    @Inject private Rotinas rotinas;

    //Listar
    @RequestMapping(value = "/grupousuario_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "8103");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810301");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810302");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810303");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810304");
        Boolean usuarios = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810305");
        Boolean transacoes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810306");
        Boolean empresas = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810307");


        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("transacoes", transacoes);
        model.addAttribute("empresas", empresas);
        model.addAttribute("grupousuario_lista", grupoUsuarioDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, grupoUsuarioDao.count()));
        return "grupousuario_lista";
    }

    //Deletar
    @RequestMapping(value = "/grupousuario_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810303");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            grupoUsuarioService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/grupousuario_lista";
    }

    //Nova
    @RequestMapping(value = "/grupousuario_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        GrupoUsuario grupoUsuario = new GrupoUsuario();
        model.addAttribute("grupousuario", grupoUsuario);
        return "grupousuario_novo";
    }

    //Insert
    @RequestMapping(value = "/grupousuario_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute GrupoUsuario grupoUsuario, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810301");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            grupoUsuarioService.insert(grupoUsuario);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/grupousuario_lista";
    }

    //Editar
    @RequestMapping(value = "/grupousuario_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810302");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        GrupoUsuario grupoUsuario = grupoUsuarioDao.selectById(id);
        model.addAttribute("grupousuario", grupoUsuario);
        return "grupousuario_editar";
    }

    //Update
    @RequestMapping(value = "/grupousuario_update", method = RequestMethod.POST)
    public String update(@ModelAttribute GrupoUsuario grupoUsuario, Model model) {
        try{
            grupoUsuarioService.update(grupoUsuario);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/grupousuario_lista";
    }

    @RequestMapping(value = "/grupousuario_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810304");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        GrupoUsuario grupoUsuario = grupoUsuarioDao.selectById(id);
        model.addAttribute("grupousuario", grupoUsuario);
        return "grupousuario_detalhes";
    }

    //Novo Usuario do Grupo
    @RequestMapping(value = "/usuariodogrupo_novo/{id}", method = RequestMethod.GET)
    public String novo_usuariodogrupo(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean listatransacao = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810305");
        if (listatransacao != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<UsuariodoGrupo> lista = usuariodoGrupoDao.selectAll(id);
        GrupoUsuario grupoUsuario = grupoUsuarioDao.selectById(id);
        model.addAttribute("lista", lista);
        model.addAttribute("grupousuario", grupoUsuario);
        return "usuariodogrupo_novo";
    }

    //Novo Transacao do Grupo
    @RequestMapping(value = "/grupotransacao_novo/{id}", method = RequestMethod.GET)
    public String novo_grupotransacao(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean listatransacao = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810306");
        if (listatransacao != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<GrupoTransacao> lista = grupoTransacaoDao.selectAll(id);
        GrupoUsuario grupoUsuario = grupoUsuarioDao.selectById(id);
        model.addAttribute("lista", lista);
        model.addAttribute("grupousuario", grupoUsuario);
        return "grupotransacao_novo";
    }

    //Novo Empresa do Grupo
    @RequestMapping(value = "/empresagrupo_novo/{id}", method = RequestMethod.GET)
    public String novo_empresagrupo(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean listatransacao = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "810307");
        if (listatransacao != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        List<EmpresaGrupo> lista = empresaGrupoDao.selectAll(id);
        GrupoUsuario grupoUsuario = grupoUsuarioDao.selectById(id);
        model.addAttribute("lista", lista);
        model.addAttribute("grupousuario", grupoUsuario);
        return "empresagrupo_novo";
    }
}

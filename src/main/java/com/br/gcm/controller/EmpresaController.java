package com.br.gcm.controller;

import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.dao.MunicipioDao;
import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.model.Empresa;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.EmpresaService;
import com.br.gcm.tag.Pagina;
import com.br.gcm.util.Rotinas;
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

import javax.inject.Inject;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 29/12/13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class EmpresaController {
    @Inject private EmpresaDao empresaDao;
    @Inject private MunicipioDao MunicipioDao;
    @Inject private PaisDao paisDao;
    @Inject private UfDao ufDao;
    @Inject private EmpresaService empresaService;
    @Inject private Rotinas rotinas;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/empresa_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1004");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100401");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100402");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100403");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100404");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("empresa_lista", empresaDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, empresaDao.count()));

        return "empresa_lista";
    }

    //Deleta
    @RequestMapping(value = "/empresa_deleta/{id_empresa}")
    public String deletar(@PathVariable("id_empresa") Integer id_empresa, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100403");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            empresaService.delete(id_empresa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/empresa_lista";
    }

    //Nova
    @RequestMapping(value = "/empresa_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100401");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Empresa empresa = new Empresa();
        model.addAttribute("empresa", empresa);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "empresa_novo";
    }

    //Insert
    @RequestMapping(value = "/empresa_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute Empresa empresa, Model model) {
        try{
            empresaService.insert(empresa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }

        return "redirect:/empresa_lista";
    }

    //Editar
    @RequestMapping(value = "/empresa_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100402");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Empresa empresa = empresaDao.selectById(id);
        model.addAttribute("empresa", empresa);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "empresa_editar";
    }

    //Update
    @RequestMapping(value = "/empresa_update", method = RequestMethod.POST)
    public String update(@ModelAttribute Empresa empresa, Model model) {
        try{
            empresaService.update(empresa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }

        return "redirect:/empresa_lista";
    }

    //Editar
    @RequestMapping(value = "/empresa_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100404");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Empresa empresa = empresaDao.selectById(id);
        model.addAttribute("empresa", empresa);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "empresa_detalhes";
    }

}

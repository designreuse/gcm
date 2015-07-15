package com.br.gcm.controller;

import com.br.gcm.dao.GrupoUsuarioDao;
import com.br.gcm.dao.UsuariodoGrupoDao;
import com.br.gcm.dao.GrupoTransacaoDao;
import com.br.gcm.dao.EmpresaGrupoDao;
import com.br.gcm.model.GrupoUsuario;
import com.br.gcm.model.UsuariodoGrupo;
import com.br.gcm.model.GrupoTransacao;
import com.br.gcm.model.EmpresaGrupo;
import com.br.gcm.service.GrupoUsuarioService;
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

    //Listar
    @RequestMapping(value = "/grupousuario_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("grupousuario_lista", grupoUsuarioDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, grupoUsuarioDao.count()));
        return "grupousuario_lista";
    }

    //Deletar
    @RequestMapping(value = "/grupousuario_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            grupoUsuarioService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
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
    public ModelAndView insert(@ModelAttribute GrupoUsuario grupoUsuario, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            grupoUsuarioService.insert(grupoUsuario);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", grupoUsuarioDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, grupoUsuarioDao.count()));
        mav.setViewName("redirect:/grupousuario_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/grupousuario_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        GrupoUsuario grupoUsuario = grupoUsuarioDao.selectById(id);
        model.addAttribute("grupousuario", grupoUsuario);
        return "grupousuario_editar";
    }

    //Update
    @RequestMapping(value = "/grupousuario_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute GrupoUsuario grupoUsuario, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            grupoUsuarioService.update(grupoUsuario);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", grupoUsuarioDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, grupoUsuarioDao.count()));
        mav.setViewName("redirect:/grupousuario_lista");
        return mav;
    }

    //Novo Usuario do Grupo
    @RequestMapping(value = "/usuariodogrupo_novo/{id}", method = RequestMethod.GET)
    public String novo_usuariodogrupo(@PathVariable("id") Integer id, Model model) {
        List<UsuariodoGrupo> lista = usuariodoGrupoDao.selectAll(id);
        model.addAttribute("lista", lista);
        return "usuariodogrupo_novo";
    }

    //Insert Usuario do grupo
    @RequestMapping(value = "/usuariodogrupo_insert", method = RequestMethod.PUT)
    public ModelAndView insert_usuariodogrupo(@ModelAttribute List<UsuariodoGrupo> lista, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            grupoUsuarioService.insert_usuariodogrupo(lista);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", grupoUsuarioDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, grupoUsuarioDao.count()));
        mav.setViewName("redirect:/grupousuario_lista");
        return mav;
    }

    //Novo Transacao do Grupo
    @RequestMapping(value = "/grupotransacao_novo/{id}", method = RequestMethod.GET)
    public String novo_grupotransacao(@PathVariable("id") Integer id, Model model) {
        List<GrupoTransacao> lista = grupoTransacaoDao.selectAll(id);
        model.addAttribute("lista", lista);
        return "grupotransacao_novo";
    }

    //Novo Empresa do Grupo
    @RequestMapping(value = "/empresagrupo_novo/{id}", method = RequestMethod.GET)
    public String novo_empresagrupo(@PathVariable("id") Integer id, Model model) {
        List<EmpresaGrupo> lista = empresaGrupoDao.selectAll(id);
        model.addAttribute("lista", lista);
        return "empresagrupo_novo";
    }
}

package com.br.gcm.controller;

import com.br.gcm.dao.DepositoDao;
import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.model.Deposito;
import com.br.gcm.model.Empresa;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.DepositoService;
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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 19/04/15
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class DepositoController {
    @Inject private DepositoDao depositoDao;
    @Inject private DepositoService depositoService;
    @Inject private Rotinas rotinas;
    @Inject private EmpresaDao empresaDao;

    @RequestMapping(value = "/deposito_filtro/{id_empresa}")
    public String filtros(@PathVariable("id_empresa") Integer id_empresa, @PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("filtro", id_empresa);
        model.addAttribute("deposito_lista", depositoDao.selectAll_paginado(id_empresa, pageable));
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("pagina", new Pagina(pageable, depositoDao.count(id_empresa)));
        return "deposito_lista";
    }

    //Listar
    @RequestMapping(value = "/deposito_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        Empresa empresa = listaEmpresa.get(0);

        model.addAttribute("filtro", empresa.getId_Empresa());
        model.addAttribute("deposito_lista", depositoDao.selectAll_paginado(empresa.getId_Empresa(), pageable));
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("pagina", new Pagina(pageable, depositoDao.count(empresa.getId_Empresa())));
        return "deposito_lista";
    }

    //Deletar
    @RequestMapping(value = "/deposito_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            depositoService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/deposito_lista";
    }

    //Nova
    @RequestMapping(value = "/deposito_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        Deposito deposito = new Deposito();

        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("deposito", deposito);
        return "deposito_novo";
    }

    //Insert
    @RequestMapping(value = "/deposito_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute Deposito deposito, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            depositoService.insert(deposito);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", depositoDao.selectAll_paginado(deposito.getId_Empresa(), pageable));
        mav.addObject("pagina", new Pagina(pageable, depositoDao.count(deposito.getId_Empresa())));
        mav.setViewName("redirect:/deposito_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/deposito_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("deposito", depositoDao.selectById(id));
        return "deposito_editar";
    }

    //Update
    @RequestMapping(value = "/deposito_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute Deposito deposito, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            depositoService.update(deposito);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", depositoDao.selectAll_paginado(deposito.getId_Empresa(), pageable));
        mav.addObject("pagina", new Pagina(pageable, depositoDao.count(deposito.getId_Empresa())));
        mav.setViewName("redirect:/deposito_lista");
        return mav;
    }
}

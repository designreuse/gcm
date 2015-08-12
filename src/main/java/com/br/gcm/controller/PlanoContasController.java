package com.br.gcm.controller;

import com.br.gcm.dao.PlanoContasDao;
import com.br.gcm.model.PlanoContas;
import com.br.gcm.service.PlanoContasService;
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

import com.br.gcm.model.Usuario;
import com.br.gcm.model.Empresa;
import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.util.Rotinas;

import javax.inject.Inject;
import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 05/05/15
 * Time: 16:51
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class PlanoContasController {
    @Inject private PlanoContasDao planoContasDao;
    @Inject private PlanoContasService planoContasService;
    @Inject private Rotinas rotinas;
    @Inject private EmpresaDao empresaDao;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    //Filtros
    @RequestMapping(value = "/planocontas_filtro/{id_empresa}")
    public String lista(@PathVariable("id_empresa") Integer id_empresa, @PageableDefault(size = 10) Pageable pageable, Model model) {

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("filtro", id_empresa);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", planoContasDao.selectAll_paginado(id_empresa , pageable));
        model.addAttribute("pagina", new Pagina(pageable, planoContasDao.count(id_empresa)));
        return "planocontas_lista";
    }

    //Listar
    @RequestMapping(value = "/planocontas_lista")
    public String lista(@PageableDefault(size = 8) Pageable pageable, Model model) {

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        Empresa empresa = listaEmpresa.get(0);

        model.addAttribute("filtro", empresa.getId_Empresa());
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", planoContasDao.selectAll_paginado(empresa.getId_Empresa() , pageable));
        model.addAttribute("pagina", new Pagina(pageable, planoContasDao.count(empresa.getId_Empresa())));
        return "planocontas_lista";
    }

    //Deletar
    @RequestMapping(value = "/planocontas_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            planoContasService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/planocontas_lista";
    }

    //Nova
    @RequestMapping(value = "/planocontas_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        PlanoContas planoContas = new PlanoContas();
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("planocontas", planoContas);
        return "planocontas_novo";
    }

    //Insert
    @RequestMapping(value = "/planocontas_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute PlanoContas planoContas, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            planoContasService.insert(planoContas);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", planoContasDao.selectAll_paginado(planoContas.getId_Empresa(), pageable));
        mav.addObject("pagina", new Pagina(pageable, planoContasDao.count(planoContas.getId_Empresa())));
        mav.setViewName("redirect:/planocontas_filtro/"+planoContas.getId_Empresa());
        return mav;
    }

    //Editar
    @RequestMapping(value = "/planocontas_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        //List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        model.addAttribute("planocontas", planoContasDao.selectById(id));
        return "planocontas_editar";
    }

    //Update
    @RequestMapping(value = "/planocontas_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute PlanoContas planoContas, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            planoContasService.update(planoContas);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", planoContasDao.selectAll_paginado(planoContas.getId_Empresa() ,pageable));
        mav.addObject("pagina", new Pagina(pageable, planoContasDao.count(planoContas.getId_Empresa())));
        mav.setViewName("redirect:/planocontas_filtro/"+planoContas.getId_Empresa());
        return mav;
    }
}

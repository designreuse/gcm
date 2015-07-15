package com.br.gcm.controller;

import com.br.gcm.dao.BancoDao;
import com.br.gcm.dao.ContaCorrenteDao;
import com.br.gcm.model.ContaCorrente;
import com.br.gcm.service.ContaCorrenteService;
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
 * Date: 06/05/15
 * Time: 08:38
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class ContaCorrenteController {
    @Inject private ContaCorrenteDao contaCorrenteDao;
    @Inject private ContaCorrenteService contaCorrenteService;
    @Inject private Rotinas rotinas;
    @Inject private EmpresaDao empresaDao;
    @Inject private BancoDao bancoDao;

    //Filtros
    @RequestMapping(value = "/contacorrente_filtro/{id_empresa}")
    public String filtros(@PathVariable("id_empresa") Integer id_empresa, @PageableDefault(size = 10) Pageable pageable, Model model) {

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("filtro", id_empresa);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", contaCorrenteDao.selectAll_paginado(id_empresa , pageable));
        model.addAttribute("pagina", new Pagina(pageable, contaCorrenteDao.count(id_empresa)));
        return "contacorrente_lista";
    }

    //Listar
    @RequestMapping(value = "/contacorrente_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        Empresa empresa = listaEmpresa.get(0);

        model.addAttribute("filtro", empresa.getId_Empresa());
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", contaCorrenteDao.selectAll_paginado(empresa.getId_Empresa() , pageable));
        model.addAttribute("pagina", new Pagina(pageable, contaCorrenteDao.count(empresa.getId_Empresa())));
        return "contacorrente_lista";
    }

    //Deletar
    @RequestMapping(value = "/contacorrente_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            contaCorrenteService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/contacorrente_lista";
    }

    //Nova
    @RequestMapping(value = "/contacorrente_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();

        ContaCorrente contaCorrente = new ContaCorrente();
        model.addAttribute("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        model.addAttribute("listabanco", bancoDao.selectAll());
        model.addAttribute("contacorrente", contaCorrente);
        return "contacorrente_novo";
    }

    //Insert
    @RequestMapping(value = "/contacorrente_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute ContaCorrente contaCorrente, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            contaCorrenteService.insert(contaCorrente);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("listabanco", bancoDao.selectAll());
        mav.addObject("lista", contaCorrenteDao.selectAll_paginado(contaCorrente.getId_Empresa(), pageable));
        mav.addObject("pagina", new Pagina(pageable, contaCorrenteDao.count(contaCorrente.getId_Empresa())));
        mav.setViewName("redirect:/contacorrente_filtro/"+contaCorrente.getId_Empresa());
        return mav;
    }

    //Editar
    @RequestMapping(value = "/contacorrente_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();

        model.addAttribute("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        model.addAttribute("listabanco", bancoDao.selectAll());
        model.addAttribute("contacorrente", contaCorrenteDao.selectById(id));
        return "contacorrente_editar";
    }

    //Update
    @RequestMapping(value = "/contacorrente_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute ContaCorrente contaCorrente, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            contaCorrenteService.update(contaCorrente);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        mav.addObject("listabanco", bancoDao.selectAll());
        mav.addObject("lista", contaCorrenteDao.selectAll_paginado(contaCorrente.getId_Empresa() ,pageable));
        mav.addObject("pagina", new Pagina(pageable, contaCorrenteDao.count(contaCorrente.getId_Empresa())));
        mav.setViewName("redirect:/contacorrente_filtro/"+contaCorrente.getId_Empresa());
        return mav;
    }
}

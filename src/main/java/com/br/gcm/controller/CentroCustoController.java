package com.br.gcm.controller;

import com.br.gcm.dao.CentroCustoDao;
import com.br.gcm.model.CentroCusto;
import com.br.gcm.service.CentroCustoService;
import com.br.gcm.tag.Pagina;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
 * Date: 02/05/15
 * Time: 09:34
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class CentroCustoController {
    @Inject private CentroCustoDao centroCustoDao;
    @Inject private CentroCustoService centroCustoService;
    @Inject private Rotinas rotinas;
    @Inject private EmpresaDao empresaDao;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    //Filtros
    @RequestMapping(value = "/centrocusto_filtro/{id_empresa}")
    public String lista(@PathVariable("id_empresa") Integer id_empresa, @PageableDefault(size = 10) Pageable pageable, Model model) {

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("filtro", id_empresa);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", centroCustoDao.selectAll_paginado(id_empresa , pageable));
        model.addAttribute("pagina", new Pagina(pageable, centroCustoDao.count(id_empresa)));
        return "centrocusto_lista";
    }

    //Listar
    @RequestMapping(value = "/centrocusto_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        Empresa empresa = listaEmpresa.get(0);

        model.addAttribute("filtro", empresa.getId_Empresa());
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", centroCustoDao.selectAll_paginado(empresa.getId_Empresa() , pageable));
        model.addAttribute("pagina", new Pagina(pageable, centroCustoDao.count(empresa.getId_Empresa())));
        return "centrocusto_lista";
    }

    //Deletar
    @RequestMapping(value = "/centrocusto_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            centroCustoService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/centrocusto_lista";
    }

    //Nova
    @RequestMapping(value = "/centrocusto_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        CentroCusto centroCusto = new CentroCusto();
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("centrocusto", centroCusto);
        return "centrocusto_novo";
    }

    //Insert
    @RequestMapping(value = "/centrocusto_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute CentroCusto centroCusto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            centroCustoService.insert(centroCusto);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", centroCustoDao.selectAll_paginado(centroCusto.getId_Empresa(), pageable));
        mav.addObject("pagina", new Pagina(pageable, centroCustoDao.count(centroCusto.getId_Empresa())));
        mav.setViewName("redirect:/centrocusto_filtro/"+centroCusto.getId_Empresa());
        return mav;
    }

    //Editar
    @RequestMapping(value = "/centrocusto_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", listaEmpresa);
        CentroCusto centroCusto = centroCustoDao.selectById(id);
        model.addAttribute("centrocusto", centroCusto);
        return "centrocusto_editar";
    }

    //Update
    @RequestMapping(value = "/centrocusto_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute CentroCusto centroCusto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            centroCustoService.update(centroCusto);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        ModelAndView mav = new ModelAndView();
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", centroCustoDao.selectAll_paginado(centroCusto.getId_Empresa() ,pageable));
        mav.addObject("pagina", new Pagina(pageable, centroCustoDao.count(centroCusto.getId_Empresa())));
        mav.setViewName("redirect:/centrocusto_filtro/"+centroCusto.getId_Empresa());
        return mav;
    }
}

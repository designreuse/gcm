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

    //Filtros
    @RequestMapping(value = "/centrocusto_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute CentroCusto filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        if (!listaEmpresa.isEmpty()){
            filtros.setId_Empresa(listaEmpresa.get(0).getId_Empresa());
        };

        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1501");
        if (lista != true) {
            mav.addObject("mensagem", "AVISO: Transação não permitida.");
            mav.setViewName("mensagemerro");
            return mav;
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150101");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150102");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150103");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150104");


        mav.addObject("novo", novo);
        mav.addObject("editar", editar);
        mav.addObject("deletar", deletar);
        mav.addObject("detalhes", detalhes);

        mav.addObject("filtros", filtros);
        mav.addObject("listaempresa", listaEmpresa);
        mav.addObject("lista", centroCustoDao.selectAll_paginado(filtros.getId_Empresa() , pageable));
        mav.addObject("pagina", new Pagina(pageable, centroCustoDao.count(filtros.getId_Empresa())));
        mav.setViewName("centrocusto_lista");
        return mav;
    }

    //Listar
    @RequestMapping(value = "/centrocusto_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        CentroCusto filtros = new CentroCusto();

        if (!listaEmpresa.isEmpty()){
            filtros.setId_Empresa(listaEmpresa.get(0).getId_Empresa());
        }

        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1501");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150101");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150102");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150103");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150104");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);

        model.addAttribute("filtros", filtros);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", centroCustoDao.selectAll_paginado(filtros.getId_Empresa() , pageable));
        model.addAttribute("pagina", new Pagina(pageable, centroCustoDao.count(filtros.getId_Empresa())));
        return "centrocusto_lista";
    }

    //Deletar
    @RequestMapping(value = "/centrocusto_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150103");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            centroCustoService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/centrocusto_lista";
    }

    //Nova
    @RequestMapping(value = "/centrocusto_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150101");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        CentroCusto centroCusto = new CentroCusto();
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("centrocusto", centroCusto);
        return "centrocusto_novo";
    }

    //Insert
    @RequestMapping(value = "/centrocusto_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute CentroCusto centroCusto, Model model) {
        try{
            centroCustoService.insert(centroCusto);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/centrocusto_lista";
    }

    //Editar
    @RequestMapping(value = "/centrocusto_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150102");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", listaEmpresa);
        CentroCusto centroCusto = centroCustoDao.selectById(id);
        model.addAttribute("centrocusto", centroCusto);
        return "centrocusto_editar";
    }

    //Update
    @RequestMapping(value = "/centrocusto_update", method = RequestMethod.POST)
    public String update(@ModelAttribute CentroCusto centroCusto, Model model) {
        try{
            centroCustoService.update(centroCusto);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/centrocusto_lista";
    }

    @RequestMapping(value = "/centrocusto_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "150104");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", listaEmpresa);
        CentroCusto centroCusto = centroCustoDao.selectById(id);
        model.addAttribute("centrocusto", centroCusto);
        return "centrocusto_detalhes";
    }
}

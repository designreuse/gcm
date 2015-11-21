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

    //Filtros
    @RequestMapping(value = "/planocontas_lista", method = RequestMethod.POST)
    public String filtros(@ModelAttribute PlanoContas filtros, @PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("filtros", filtros);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", planoContasDao.selectAll(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, planoContasDao.count(filtros)));
        return "planocontas_lista";
    }

    //Listar
    @RequestMapping(value = "/planocontas_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {

        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        PlanoContas filtros = new PlanoContas();

        if (!listaEmpresa.isEmpty()){
            filtros.setId_Empresa(listaEmpresa.get(0).getId_Empresa());
        }

        model.addAttribute("filtros", filtros);
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("lista", planoContasDao.selectAll(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, planoContasDao.count(filtros)));
        return "planocontas_lista";
    }

    //Deletar
    @RequestMapping(value = "/planocontas_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        try{
            planoContasService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/planocontas_lista";
    }

    //Nova
    @RequestMapping(value = "/planocontas_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();

        PlanoContas planoContas = new PlanoContas();
        model.addAttribute("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        model.addAttribute("planocontas", planoContas);
        return "planocontas_novo";
    }

    //Insert
    @RequestMapping(value = "/planocontas_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute PlanoContas planoContas, Model model) {
        try{
            planoContasService.insert(planoContas);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/planocontas_lista";
    }

    //Editar
    @RequestMapping(value = "/planocontas_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();

        model.addAttribute("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        model.addAttribute("planocontas", planoContasDao.selectById(id));
        return "planocontas_editar";
    }

    //Update
    @RequestMapping(value = "/planocontas_update", method = RequestMethod.POST)
    public String update(@ModelAttribute PlanoContas planoContas, Model model) {
        try{
            planoContasService.update(planoContas);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/planocontas_lista";
    }

    @RequestMapping(value = "/planocontas_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();

        model.addAttribute("listaempresa", empresaDao.selectEmpresasUsuario(usuario.getId_usuario()));
        model.addAttribute("planocontas", planoContasDao.selectById(id));
        return "planocontas_detalhes";
    }
}

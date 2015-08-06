package com.br.gcm.controller;

import com.br.gcm.dao.SubGrupoProdutoDao;
import com.br.gcm.dao.GrupoProdutoDao;
import com.br.gcm.model.SubGrupoProduto;
import com.br.gcm.model.GrupoProduto;
import com.br.gcm.service.SubGrupoProdutoService;
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


/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 18/04/15
 * Time: 16:58
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class SubGrupoProdutoController {
    @Inject private SubGrupoProdutoDao subGrupoProdutoDao;
    @Inject private GrupoProdutoDao grupoProdutoDao;
    @Inject private SubGrupoProdutoService subGrupoProdutoService;

    //Listar
    @RequestMapping(value = "/subgrupoproduto_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        SubGrupoProduto filtros = new SubGrupoProduto();
        model.addAttribute("lista_grupoproduto", grupoProdutoDao.selectAll());
        model.addAttribute("filtros", filtros);
        model.addAttribute("subgrupoproduto_lista", subGrupoProdutoDao.selectAll_paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, subGrupoProdutoDao.count(filtros)));
        return "subgrupoproduto_lista";
    }

    @RequestMapping(value = "/subgrupoproduto_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute SubGrupoProduto filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista_grupoproduto", grupoProdutoDao.selectAll());
        mav.addObject("subgrupoproduto_lista", subGrupoProdutoDao.selectAll_paginado(filtros, pageable));
        mav.addObject("pagina", new Pagina(pageable, subGrupoProdutoDao.count(filtros)));
        mav.addObject("filtros", filtros);

        mav.setViewName("subgrupoproduto_lista");
        return mav;
    }

    //Deletar
    @RequestMapping(value = "/subgrupoproduto_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            subGrupoProdutoService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/subgrupoproduto_lista";
    }

    //Nova
    @RequestMapping(value = "/subgrupoproduto_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        SubGrupoProduto subGrupoProduto = new SubGrupoProduto();
        model.addAttribute("subgrupoproduto", subGrupoProduto);
        model.addAttribute("listagrupoproduto", grupoProdutoDao.selectAll());
        return "subgrupoproduto_novo";
    }

    //Insert
    @RequestMapping(value = "/subgrupoproduto_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute SubGrupoProduto subGrupoProduto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            subGrupoProdutoService.insert(subGrupoProduto);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        SubGrupoProduto filtros = new SubGrupoProduto();
        filtros.setId_GrupoProduto(subGrupoProduto.getId_GrupoProduto());

        mav.addObject("lista_grupoproduto", grupoProdutoDao.selectAll());
        mav.addObject("subgrupoproduto_lista", subGrupoProdutoDao.selectAll_paginado(filtros, pageable));
        mav.addObject("pagina", new Pagina(pageable, subGrupoProdutoDao.count(filtros)));
        mav.addObject("filtros", filtros);
        mav.setViewName("redirect:/subgrupoproduto_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/subgrupoproduto_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        SubGrupoProduto subGrupoProduto = subGrupoProdutoDao.selectById(id);
        model.addAttribute("subgrupoproduto", subGrupoProduto);
        model.addAttribute("listagrupoproduto", grupoProdutoDao.selectAll());
        return "subgrupoproduto_editar";
    }

    //Update
    @RequestMapping(value = "/subgrupoproduto_update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute SubGrupoProduto subGrupoProduto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            subGrupoProdutoService.update(subGrupoProduto);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", subGrupoProdutoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, subGrupoProdutoDao.count()));
        mav.setViewName("redirect:/subgrupoproduto_lista");
        return mav;
    }

    @RequestMapping(value = "/subgrupoproduto_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        SubGrupoProduto subGrupoProduto = subGrupoProdutoDao.selectById(id);
        model.addAttribute("subgrupoproduto", subGrupoProduto);
        model.addAttribute("listagrupoproduto", grupoProdutoDao.selectAll());
        return "subgrupoproduto_detalhes";
    }
}


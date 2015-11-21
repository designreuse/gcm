package com.br.gcm.controller;

import com.br.gcm.dao.UnidadeDao;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Unidade;
import com.br.gcm.service.UnidadeService;
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
 * Date: 21/04/15
 * Time: 20:21
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class UnidadeController {
    @Inject private UnidadeDao unidadeDao;
    @Inject private UnidadeService unidadeService;

    //Listar
    @RequestMapping(value = "/unidade_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Unidade filtros = new Unidade();

        model.addAttribute("unidade_lista", unidadeDao.selectAll_paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, unidadeDao.count(filtros)));
        model.addAttribute("filtros", filtros);

        return "unidade_lista";
    }

    //Listar
    @RequestMapping(value = "/unidade_lista", method = RequestMethod.POST)
    public String filtros(@ModelAttribute Unidade filtros, @PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("unidade_lista", unidadeDao.selectAll_paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, unidadeDao.count(filtros)));
        model.addAttribute("filtros", filtros);

        return "unidade_lista";
    }

    //Deletar
    @RequestMapping(value = "/unidade_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        try{
            unidadeService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/unidade_lista";
    }

    //Nova
    @RequestMapping(value = "/unidade_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Unidade unidade = new Unidade();
        model.addAttribute("unidade", unidade);
        return "unidade_novo";
    }

    //Insert
    @RequestMapping(value = "/unidade_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute Unidade unidade, Model model) {
        try{
            unidadeService.insert(unidade);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/unidade_lista";
    }

    //Editar
    @RequestMapping(value = "/unidade_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Unidade unidade = unidadeDao.selectById(id);
        model.addAttribute("unidade", unidade);
        return "unidade_editar";
    }

    //Update
    @RequestMapping(value = "/unidade_update", method = RequestMethod.POST)
    public String update(@ModelAttribute Unidade unidade, Model model) {
        try{
            unidadeService.update(unidade);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/unidade_lista";
    }

    //Editar
    @RequestMapping(value = "/unidade_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Unidade unidade = unidadeDao.selectById(id);
        model.addAttribute("unidade", unidade);
        return "unidade_detalhes";
    }
}

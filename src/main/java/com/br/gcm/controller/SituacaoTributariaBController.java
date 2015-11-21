package com.br.gcm.controller;

import com.br.gcm.dao.SituacaoTributariaBDao;
import com.br.gcm.model.SituacaoTributariaB;
import com.br.gcm.service.SituacaoTributariaBService;
import com.br.gcm.tag.Pagina;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 22/04/15
 * Time: 09:37
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class SituacaoTributariaBController {
    @Inject private SituacaoTributariaBDao situacaoTributariaBDao;
    @Inject private SituacaoTributariaBService situacaoTributariaBService;

    //Listar
    @RequestMapping(value = "/stb_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("stb_lista", situacaoTributariaBDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, situacaoTributariaBDao.count()));
        return "stb_lista";
    }

    //Deletar
    @RequestMapping(value = "/stb_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id, Model model) {
        try{
            situacaoTributariaBService.delete(id);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/stb_lista";
    }

    //Nova
    @RequestMapping(value = "/stb_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        SituacaoTributariaB situacaoTributariaB = new SituacaoTributariaB();
        model.addAttribute("stb", situacaoTributariaB);
        return "stb_novo";
    }

    //Insert
    @RequestMapping(value = "/stb_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute SituacaoTributariaB situacaoTributariaB, Model model) {
        try{
            situacaoTributariaBService.insert(situacaoTributariaB);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/stb_lista";
    }

    //Editar
    @RequestMapping(value = "/stb_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        SituacaoTributariaB situacaoTributariaB = situacaoTributariaBDao.selectById(id);
        model.addAttribute("stb", situacaoTributariaB);
        return "stb_editar";
    }

    //Update
    @RequestMapping(value = "/stb_update", method = RequestMethod.POST)
    public String update(@ModelAttribute SituacaoTributariaB situacaoTributariaB, Model model) {
        try{
            situacaoTributariaBService.update(situacaoTributariaB);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/stb_lista";
    }

    @RequestMapping(value = "/stb_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        SituacaoTributariaB situacaoTributariaB = situacaoTributariaBDao.selectById(id);
        model.addAttribute("stb", situacaoTributariaB);
        return "stb_detalhes";
    }
}

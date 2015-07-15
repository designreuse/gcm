package com.br.gcm.controller;

import com.br.gcm.dao.SituacaoTributariaADao;
import com.br.gcm.model.SituacaoTributariaA;
import com.br.gcm.service.SituacaoTributariaAService;
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
 * Date: 21/04/15
 * Time: 22:52
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class SituacaoTributariaAController {
    @Inject private SituacaoTributariaADao situacaoTributariaADao;
    @Inject private SituacaoTributariaAService situacaoTributariaAService;

    //Listar
    @RequestMapping(value = "/sta_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("sta_lista", situacaoTributariaADao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, situacaoTributariaADao.count()));
        return "sta_lista";
    }

    //Deletar
    @RequestMapping(value = "/sta_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            situacaoTributariaAService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/sta_lista";
    }

    //Nova
    @RequestMapping(value = "/sta_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        SituacaoTributariaA situacaoTributariaA = new SituacaoTributariaA();
        model.addAttribute("sta", situacaoTributariaA);
        return "sta_novo";
    }

    //Insert
    @RequestMapping(value = "/sta_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute SituacaoTributariaA situacaoTributariaA, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            situacaoTributariaAService.insert(situacaoTributariaA);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", situacaoTributariaADao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, situacaoTributariaADao.count()));
        mav.setViewName("redirect:/sta_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/sta_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        SituacaoTributariaA situacaoTributariaA = situacaoTributariaADao.selectById(id);
        model.addAttribute("sta", situacaoTributariaA);
        return "sta_editar";
    }

    //Update
    @RequestMapping(value = "/sta_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute SituacaoTributariaA situacaoTributariaA, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            situacaoTributariaAService.update(situacaoTributariaA);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", situacaoTributariaADao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, situacaoTributariaADao.count()));
        mav.setViewName("redirect:/sta_lista");
        return mav;
    }
}

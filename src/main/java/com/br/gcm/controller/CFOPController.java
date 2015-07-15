package com.br.gcm.controller;

import com.br.gcm.dao.CFOPDao;
import com.br.gcm.dao.RelacaoCFOPDao;
import com.br.gcm.model.CFOP;
import com.br.gcm.service.CFOPService;
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
 * Date: 30/04/15
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class CFOPController {
    @Inject private CFOPDao cfopDao;
    @Inject private CFOPService cfopService;
    @Inject private RelacaoCFOPDao relacaoCFOPDao;

    //Listar
    @RequestMapping(value = "/cfop_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("lista", cfopDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, cfopDao.count()));
        return "cfop_lista";
    }

    //Listar  Relacao
    @RequestMapping(value = "/relacaocfop_lista/{id_CFOP}")
    public String listareferencia(@PathVariable("id_CFOP") Integer id_CFOP, Model model) {
        model.addAttribute("lista", relacaoCFOPDao.selectAll(id_CFOP));
        model.addAttribute("cfop", cfopDao.selectById(id_CFOP));
        return "relacaocfop_lista";
    }

    //Deletar
    @RequestMapping(value = "/cfop_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            cfopService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/cfop_lista";
    }

    //Nova
    @RequestMapping(value = "/cfop_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        CFOP cfop = new CFOP();
        model.addAttribute("cfop", cfop);
        return "cfop_novo";
    }

    //Insert
    @RequestMapping(value = "/cfop_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute CFOP cfop, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            cfopService.insert(cfop);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", cfopDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, cfopDao.count()));
        mav.setViewName("redirect:/cfop_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/cfop_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        CFOP cfop = cfopDao.selectById(id);
        model.addAttribute("cfop", cfop);
        return "cfop_editar";
    }

    //Update
    @RequestMapping(value = "/cfop_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute CFOP cfop, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            cfopService.update(cfop);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", cfopDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, cfopDao.count()));
        mav.setViewName("redirect:/cfop_lista");
        return mav;
    }
}

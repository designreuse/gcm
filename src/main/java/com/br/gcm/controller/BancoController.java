package com.br.gcm.controller;

import com.br.gcm.dao.BancoDao;
import com.br.gcm.model.Banco;
import com.br.gcm.service.BancoService;
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
 * Date: 05/05/15
 * Time: 21:55
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class BancoController {
    @Inject private BancoDao bancoDao;
    @Inject private BancoService bancoService;

    //Listar
    @RequestMapping(value = "/banco_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("lista", bancoDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, bancoDao.count()));
        return "banco_lista";
    }

    //Deletar
    @RequestMapping(value = "/banco_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            bancoService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/banco_lista";
    }

    //Nova
    @RequestMapping(value = "/banco_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Banco banco = new Banco();
        model.addAttribute("banco", banco);
        return "banco_novo";
    }

    //Insert
    @RequestMapping(value = "/banco_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute Banco banco, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            bancoService.insert(banco);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", bancoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, bancoDao.count()));
        mav.setViewName("redirect:/banco_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/banco_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Banco banco = bancoDao.selectById(id);
        model.addAttribute("banco", banco);
        return "banco_editar";
    }

    //Update
    @RequestMapping(value = "/banco_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute Banco banco, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            bancoService.update(banco);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", bancoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, bancoDao.count()));
        mav.setViewName("redirect:/banco_lista");
        return mav;
    }
}

package com.br.gcm.controller;

import com.br.gcm.dao.SituacaoTributariaPISCOFINSDao;
import com.br.gcm.model.SituacaoTributariaPISCOFINS;
import com.br.gcm.service.SituacaoTributariaPISCOFINSService;
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
 * Date: 23/04/15
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class SituacaoTributariaPISCOFINSController {
    @Inject private SituacaoTributariaPISCOFINSDao situacaoTributariaPISCOFINSDao;
    @Inject private SituacaoTributariaPISCOFINSService situacaoTributariaPISCOFINSService;

    //Listar
    @RequestMapping(value = "/situacaotributariapiscofins_lista")
    public String lista(@PageableDefault(size = 8) Pageable pageable, Model model) {
        model.addAttribute("lista", situacaoTributariaPISCOFINSDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, situacaoTributariaPISCOFINSDao.count()));
        return "situacaotributariapiscofins_lista";
    }

    //Deletar
    @RequestMapping(value = "/situacaotributariapiscofins_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            situacaoTributariaPISCOFINSService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/situacaotributariapiscofins_lista";
    }

    //Nova
    @RequestMapping(value = "/situacaotributariapiscofins_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS = new SituacaoTributariaPISCOFINS();
        model.addAttribute("stPISCOFINS", situacaoTributariaPISCOFINS);
        return "situacaotributariapiscofins_novo";
    }

    //Insert
    @RequestMapping(value = "/situacaotributariapiscofins_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        try{
            situacaoTributariaPISCOFINSService.insert(situacaoTributariaPISCOFINS);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", situacaoTributariaPISCOFINSDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, situacaoTributariaPISCOFINSDao.count()));
        mav.setViewName("redirect:/situacaotributariapiscofins_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/situacaotributariapiscofins_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS = situacaoTributariaPISCOFINSDao.selectById(id);
        model.addAttribute("stPISCOFINS", situacaoTributariaPISCOFINS);
        return "situacaotributariapiscofins_editar";
    }

    //Update
    @RequestMapping(value = "/situacaotributariapiscofins_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        try{
            situacaoTributariaPISCOFINSService.update(situacaoTributariaPISCOFINS);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", situacaoTributariaPISCOFINSDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, situacaoTributariaPISCOFINSDao.count()));
        mav.setViewName("redirect:/situacaotributariapiscofins_lista");
        return mav;
    }
}

package com.br.gcm.controller;

import com.br.gcm.dao.NCMDao;
import com.br.gcm.dao.SituacaoTributariaPISCOFINSDao;
import com.br.gcm.model.NCM;
import com.br.gcm.service.NCMService;
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
 * Date: 19/04/15
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class NCMController {
    @Inject private NCMDao ncmDao;
    @Inject private NCMService ncmService;
    @Inject private SituacaoTributariaPISCOFINSDao situacaoTributariaPISCOFINSDao;

    //Listar
    @RequestMapping(value = "/ncm_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("ncm_lista", ncmDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, ncmDao.count()));
        return "ncm_lista";
    }

    //Deletar
    @RequestMapping(value = "/ncm_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            ncmService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/ncm_lista";
    }

    //Nova
    @RequestMapping(value = "/ncm_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        NCM ncm = new NCM();
        model.addAttribute("ncm", ncm);
        model.addAttribute("lista_cst", situacaoTributariaPISCOFINSDao.selectAll());
        return "ncm_novo";
    }

    //Insert
    @RequestMapping(value = "/ncm_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute NCM ncm, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            ncmService.insert(ncm);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", ncmDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, ncmDao.count()));
        mav.setViewName("redirect:/ncm_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/ncm_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        NCM ncm = ncmDao.selectById(id);
        model.addAttribute("ncm", ncm);
        model.addAttribute("lista_cst", situacaoTributariaPISCOFINSDao.selectAll());
        return "ncm_editar";
    }

    //Update
    @RequestMapping(value = "/ncm_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute NCM ncm, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            ncmService.update(ncm);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", ncmDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, ncmDao.count()));
        mav.setViewName("redirect:/ncm_lista");
        return mav;
    }
}

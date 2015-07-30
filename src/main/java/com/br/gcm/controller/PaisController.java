package com.br.gcm.controller;

import com.br.gcm.dao.PaisDao;
import com.br.gcm.model.Pais;
import com.br.gcm.model.filtros.Filtro_Pais;
import com.br.gcm.service.PaisService;
import com.br.gcm.tag.Pagina;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.swing.*;

@Controller
public class PaisController {
    @Inject private PaisDao paisDao;
    @Inject private PaisService paisService;

    @RequestMapping(value = "/pais_lista")
    public String pais_lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Filtro_Pais filtros = new Filtro_Pais();
        model.addAttribute("pais_lista", paisDao.Pais_Paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, paisDao.count()));
        model.addAttribute("filtros", filtros);
        return "pais_lista";
    }

    //Filtros
    @RequestMapping(value = "/pais_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute Filtro_Pais filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pais_lista", paisDao.Pais_Paginado(filtros, pageable));
        mav.addObject("pagina", new Pagina(pageable, paisDao.count_Paginado(filtros)));
        mav.addObject("filtros", filtros);
        mav.setViewName("pais_lista");
        return mav;
    }

    //Deleta Pais
    @RequestMapping(value = "/pais_deleta/{id_pais}")
    public String deletar(@PathVariable("id_pais") Integer id_pais) throws ServletException {
        try{
            paisService.delete(id_pais);
        }catch(Exception e){
            //JOptionPane JOptinPane = new JOptionPane();
            //JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
            JOptionPane.showMessageDialog(null,e.getCause().toString());
            //throw new ServletException(e.getCause().toString());
        }
        return "redirect:/pais_lista";
    }

    //Novo Pais
    @RequestMapping(value = "/pais_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Pais pais = new Pais();
        model.addAttribute("pais", pais);
        return "pais_novo";
    }

    //Gravar
    @RequestMapping(value = "/pais_gravar", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute Pais pais, @PageableDefault(size = 10) Pageable pageable, BindingResult resultt) {
        try{
            paisService.insert(pais);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        Filtro_Pais filtros = new Filtro_Pais();
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", paisDao.Pais_Paginado(filtros, pageable));
        mav.setViewName("redirect:/pais_lista");
        return mav;
    }

    @RequestMapping(value = "/editar_pais/{operacao}/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("operacao") String operacao, @PathVariable("id") Integer id, Model model) {
        Pais pais = paisDao.selectById(id);
        model.addAttribute("pais", pais);
        model.addAttribute("operacao", operacao);
        return "pais_editar";
    }

    @RequestMapping(value = "/alterar_pais", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute Pais pais, BindingResult resultt) {
        try{
            paisService.update(pais);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", paisDao.Pais_lista());
        mav.setViewName("redirect:/pais_lista");
        return mav;
    }
}

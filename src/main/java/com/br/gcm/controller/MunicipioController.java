package com.br.gcm.controller;

import com.br.gcm.dao.MunicipioDao;
import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.model.Municipio;
import com.br.gcm.service.MunicipioService;
import com.br.gcm.tag.Pagina;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 20/12/13
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class MunicipioController {
    @Inject private MunicipioDao MunicipioDao;
    @Inject private PaisDao paisDao;
    @Inject private UfDao ufDao;
    @Inject private MunicipioService municipioService;

    @RequestMapping(value = "/municipio_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("municipio_lista", MunicipioDao.selectAll_Paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, MunicipioDao.count()));
        return "municipio_lista";
    }

    //Deleta municipio
    @RequestMapping(value = "/municipio_deleta/{id_municipio}")
    public String deletar(@PathVariable("id_municipio") Integer id_municipio) {
        try{
            municipioService.delete(id_municipio);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/municipio_lista";
    }

    //novo
    @RequestMapping(value = "/municipio_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Municipio municipio = new Municipio();
        model.addAttribute("municipio", municipio);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "municipio_novo";
    }

    //Insert
    @RequestMapping(value = "/municipio_gravar", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute Municipio municipio, @PageableDefault(size = 10) Pageable pageable, BindingResult resultt) {
        try{
            municipioService.insert(municipio);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", MunicipioDao.selectAll_Paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, MunicipioDao.count()));
        mav.setViewName("redirect:/municipio_lista");
        return mav;
    }

    //Editar municipio
    @RequestMapping(value = "/editar_municipio/{id}", method = RequestMethod.GET)
    public String edita(@PathVariable("id") Integer id, Model model) {
        Municipio municipio = MunicipioDao.selectById(id);
        model.addAttribute("municipio", municipio);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "municipio_editar";
    }

    //Update_municipio
    @RequestMapping(value = "/alterar_municipio", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute Municipio municipio, @PageableDefault(size = 10) Pageable pageable, BindingResult resultt) {
        try{
            municipioService.update(municipio);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", MunicipioDao.selectAll_Paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, MunicipioDao.count()));
        mav.setViewName("redirect:/municipio_lista");
        return mav;
    }


}

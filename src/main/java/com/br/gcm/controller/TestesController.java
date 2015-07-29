package com.br.gcm.controller;

import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.model.Uf;
import com.br.gcm.service.UfService;
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
import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 19/12/13
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class TestesController {
    @Inject private UfDao ufDao;
    @Inject private PaisDao paisDao;
    @Inject private UfService ufService;

    @RequestMapping(value = "/testelayout")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("uf_lista", ufDao.selectAll_Paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, ufDao.count()));
        return "teste_layout";
    }




}

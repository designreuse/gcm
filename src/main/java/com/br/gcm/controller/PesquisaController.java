package com.br.gcm.controller;

import com.br.gcm.dao.PessoaDao;
import com.br.gcm.model.filtros.Filtro_Pessoa;
import com.br.gcm.tag.Pagina;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.br.gcm.model.Usuario;
import com.br.gcm.model.Empresa;
import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.util.Rotinas;

import javax.inject.Inject;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 21/06/15
 * Time: 10:28
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class PesquisaController {
    @Inject private PessoaDao pessoaDao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/frmpesqpessoa_filtros/{tipo}")
    public String lista(@PathVariable("tipo") String tipo, @PageableDefault(size = 8) Pageable pageable, Model model) {
        Filtro_Pessoa filtro_pessoa = new Filtro_Pessoa();
        model.addAttribute("filtros",filtro_pessoa);
        model.addAttribute("lista", pessoaDao.selectAll_paginado(tipo, pageable));
        model.addAttribute("pagina", new Pagina(pageable, pessoaDao.count(tipo)));
        return "frmpesq_pessoa";
    }
}

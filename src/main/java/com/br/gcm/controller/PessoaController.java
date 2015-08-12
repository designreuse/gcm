package com.br.gcm.controller;

import com.br.gcm.dao.PessoaDao;
import com.br.gcm.dao.MunicipioDao;
import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.model.Pessoa;
import com.br.gcm.model.filtros.Filtro_Pessoa;
import com.br.gcm.service.PessoaService;
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

import javax.inject.Inject;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 27/04/15
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class PessoaController {
    @Inject private PessoaDao pessoaDao;
    @Inject private MunicipioDao municipioDao;
    @Inject private PaisDao paisDao;
    @Inject private UfDao ufDao;
    @Inject private PessoaService pessoaService;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/pessoa_lista/{tipo}")
    public String lista(@PathVariable("tipo") String tipo, @PageableDefault(size = 8) Pageable pageable, Model model) {
        Filtro_Pessoa filtro_pessoa = new Filtro_Pessoa();
        model.addAttribute("filtros",filtro_pessoa);
        model.addAttribute("lista", pessoaDao.selectAll_paginado(tipo, pageable));
        model.addAttribute("pagina", new Pagina(pageable, pessoaDao.count(tipo)));
        return "pessoa_lista";
    }

    @RequestMapping(value = "/pessoa_listafiltros/{tipo}", method = RequestMethod.POST)
    public String listafiltros(@PathVariable("tipo") String tipo, @PageableDefault(size = 8) Pageable pageable, @ModelAttribute Filtro_Pessoa filtro_pessoa, Model model) {
        model.addAttribute("filtros",filtro_pessoa);
        model.addAttribute("lista", pessoaDao.selectAll_FiltrosPaginado(tipo, filtro_pessoa, pageable));
        model.addAttribute("pagina", new Pagina(pageable, pessoaDao.count(tipo)));
        return "pessoa_lista";
    }

    //Inativar
    @RequestMapping(value = "/pessoa_inativar/{tipo}/{id_pessoa}")
    public String deletar(@PathVariable("tipo") String tipo, @PathVariable("id_pessoa") Integer id_pessoa) {
        try{
            pessoaService.inativar(id_pessoa);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/pessoa_lista/"+tipo;
    }

    //Nova
    @RequestMapping(value = "/pessoa_novo/{tipo}", method = RequestMethod.GET)
    public String novo(@PathVariable("tipo") String tipo, ModelMap model) {
        Pessoa pessoa = new Pessoa();
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("tipo", tipo);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "pessoa_novo";
    }

    //Insert
    @RequestMapping(value = "/pessoa_insert/{tipo}", method = RequestMethod.POST)
    public ModelAndView insert(@PathVariable("tipo") String tipo, @ModelAttribute Pessoa pessoa, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        try{
            pessoaService.insert(pessoa);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", pessoaDao.selectAll_paginado(tipo, pageable));
        mav.addObject("pagina", new Pagina(pageable, pessoaDao.count(tipo)));
        mav.setViewName("redirect:/pessoa_lista/"+tipo);
        return mav;
    }

    //Editar
    @RequestMapping(value = "/pessoa_editar/{tipo}/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("tipo") String tipo, @PathVariable("id") Integer id, Model model) {
        Pessoa pessoa = pessoaDao.selectById(id);
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("tipo", tipo);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "pessoa_editar";
    }

    //Update
    @RequestMapping(value = "/pessoa_update/{tipo}", method = RequestMethod.PUT)
    public ModelAndView update(@PathVariable("tipo") String tipo, @ModelAttribute Pessoa pessoa, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        try{
            pessoaService.update(pessoa);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", pessoaDao.selectAll_paginado(tipo, pageable));
        mav.addObject("pagina", new Pagina(pageable, pessoaDao.count(tipo)));
        mav.setViewName("redirect:/pessoa_lista/"+tipo);
        return mav;
    }
}

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/pessoa_lista/{tipo}")
    public String lista(@PathVariable("tipo") String tipo, @PageableDefault(size = 10) Pageable pageable, Model model) {
        Pessoa filtros = new Pessoa();
        model.addAttribute("filtros",filtros);
        model.addAttribute("lista", pessoaDao.selectAll(tipo, filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, pessoaDao.count(tipo, filtros)));
        return "pessoa_lista";
    }

    @RequestMapping(value = "/pessoa_lista/{tipo}", method = RequestMethod.POST)
    public String filtros(@PathVariable("tipo") String tipo, @PageableDefault(size = 10) Pageable pageable, @ModelAttribute Pessoa filtros, Model model) {
        model.addAttribute("filtros",filtros);
        model.addAttribute("lista", pessoaDao.selectAll(tipo, filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, pessoaDao.count(tipo, filtros)));
        return "pessoa_lista";
    }

    //Inativar
    @RequestMapping(value = "/pessoa_inativar/{tipo}/{id_pessoa}")
    public String inativar(@PathVariable("tipo") String tipo, @PathVariable("id_pessoa") Integer id_pessoa, Model model) {
        try{
            pessoaService.inativar(id_pessoa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pessoa_lista/"+tipo;
    }

    @RequestMapping(value = "/pessoa_ativar/{tipo}/{id_pessoa}")
    public String ativar(@PathVariable("tipo") String tipo, @PathVariable("id_pessoa") Integer id_pessoa, Model model) {
        try{
            pessoaService.ativar(id_pessoa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
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
    public String insert(@PathVariable("tipo") String tipo, @ModelAttribute Pessoa pessoa, Model model) {
        try{
            pessoaService.insert(pessoa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pessoa_lista/"+tipo;
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
    @RequestMapping(value = "/pessoa_update/{tipo}", method = RequestMethod.POST)
    public String update(@PathVariable("tipo") String tipo, @ModelAttribute Pessoa pessoa, Model model) {
        try{
            pessoaService.update(pessoa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pessoa_lista/"+tipo;
    }

    @RequestMapping(value = "/pessoa_detalhes/{tipo}/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("tipo") String tipo, @PathVariable("id") Integer id, Model model) {
        Pessoa pessoa = pessoaDao.selectById(id);
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("tipo", tipo);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "pessoa_detalhes";
    }

    @RequestMapping(value = "/pessoa_pesquisa/{tipo}", method = RequestMethod.GET)
    public String pesquisa(@PathVariable("tipo") String tipo, Model model) {
        Pessoa pessoa = new Pessoa();
        model.addAttribute("filtros", pessoa);
        model.addAttribute("tipo", tipo);
        return "pessoa_pesquisa";
    }
}

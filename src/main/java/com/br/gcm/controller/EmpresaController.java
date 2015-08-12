package com.br.gcm.controller;

import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.dao.MunicipioDao;
import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.model.Empresa;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.service.EmpresaService;
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
 * Date: 29/12/13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class EmpresaController {
    @Inject private EmpresaDao empresaDao;
    @Inject private MunicipioDao MunicipioDao;
    @Inject private PaisDao paisDao;
    @Inject private UfDao ufDao;
    @Inject private EmpresaService empresaService;

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

    @RequestMapping(value = "/empresa_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        model.addAttribute("mensagem", mensagemTransacao);
        model.addAttribute("empresa_lista", empresaDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, empresaDao.count()));

        limparmensagem();
        return "empresa_lista";
    }

    //Deleta municipio
    @RequestMapping(value = "/empresa_deleta/{id_empresa}")
    public String deletar(@PathVariable("id_empresa") Integer id_empresa) {
        try{
            empresaService.delete(id_empresa);
            tipo = 0;
            mensagem = "Registro deletado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/empresa_lista";
    }

    //Nova
    @RequestMapping(value = "/empresa_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Empresa empresa = new Empresa();
        model.addAttribute("empresa", empresa);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "empresa_novo";
    }

    //Insert
    @RequestMapping(value = "/empresa_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute Empresa empresa, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            empresaService.insert(empresa);
            tipo = 0;
            mensagem = "Registro Inserido com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", empresaDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, empresaDao.count()));
        mav.setViewName("redirect:/empresa_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/empresa_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Empresa empresa = empresaDao.selectById(id);
        model.addAttribute("empresa", empresa);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "empresa_editar";
    }

    //Update
    @RequestMapping(value = "/empresa_update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute Empresa empresa, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            empresaService.update(empresa);
            tipo = 0;
            mensagem = "Registro Alterado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", empresaDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, empresaDao.count()));
        mav.setViewName("redirect:/empresa_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/empresa_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Empresa empresa = empresaDao.selectById(id);
        model.addAttribute("empresa", empresa);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "empresa_detalhes";
    }

}

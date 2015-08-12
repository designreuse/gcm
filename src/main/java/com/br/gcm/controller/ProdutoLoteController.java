package com.br.gcm.controller;

import com.br.gcm.dao.ProdutoLoteDao;
import com.br.gcm.dao.ProdutoDao;
import com.br.gcm.model.ProdutoLote;
import com.br.gcm.service.ProdutoLoteService;
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
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 26/04/15
 * Time: 11:23
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class ProdutoLoteController {
    @Inject private ProdutoLoteDao produtoLoteDao;
    @Inject private ProdutoDao produtoDao;
    @Inject private ProdutoLoteService produtoLoteService;

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

    //Listar
    @RequestMapping(value = "/produtolote_lista/{id_produto}")
    public String lista(@PathVariable("id_produto") int id_Produto, @PageableDefault(size = 8) Pageable pageable, Model model) {
        model.addAttribute("lista", produtoLoteDao.selectAll_paginado(id_Produto, pageable));
        model.addAttribute("produto", produtoDao.selectById(id_Produto));
        model.addAttribute("pagina", new Pagina(pageable, produtoLoteDao.count(id_Produto)));
        return "produtolote_lista";
    }

    //Deletar
    @RequestMapping(value = "/produtolote_deleta/{id}/{id_produto}")
    public String deletar(@PathVariable("id") Integer id, @PathVariable("id_produto") int id_Produto) {
        try{
            produtoLoteService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/produtolote_lista/"+id_Produto;
    }

    //Nova
    @RequestMapping(value = "/produtolote_novo/{id_produto}", method = RequestMethod.GET)
    public String novo(@PathVariable("id_produto") int id_Produto, ModelMap model) {
        ProdutoLote produtoLote = new ProdutoLote();
        model.addAttribute("produto", produtoDao.selectById(id_Produto));
        model.addAttribute("produtolote", produtoLote);
        return "produtolote_novo";
    }

    //Insert
    @RequestMapping(value = "/produtolote_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute ProdutoLote produtoLote, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        try{
            produtoLoteService.insert(produtoLote);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", produtoLoteDao.selectAll_paginado(produtoLote.getId_Produto() ,pageable));
        mav.addObject("pagina", new Pagina(pageable, produtoLoteDao.count(produtoLote.getId_Produto())));
        mav.setViewName("redirect:/produtolote_lista/"+produtoLote.getId_Produto());
        return mav;
    }

    //Editar
    @RequestMapping(value = "/produtolote_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        ProdutoLote produtoLote = produtoLoteDao.selectById(id);
        model.addAttribute("produto", produtoDao.selectById(produtoLote.getId_Produto()));
        model.addAttribute("produtolote", produtoLote);
        return "produtolote_editar";
    }

    //Update
    @RequestMapping(value = "/produtolote_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute ProdutoLote produtoLote, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        try{
            produtoLoteService.update(produtoLote);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", produtoLoteDao.selectAll_paginado(produtoLote.getId_Produto(), pageable));
        mav.addObject("pagina", new Pagina(pageable, produtoLoteDao.count(produtoLote.getId_Produto())));
        mav.setViewName("redirect:/produtolote_lista/"+produtoLote.getId_Produto());
        return mav;
    }
}

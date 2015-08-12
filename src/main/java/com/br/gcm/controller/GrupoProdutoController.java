package com.br.gcm.controller;

import com.br.gcm.dao.GrupoProdutoDao;
import com.br.gcm.model.GrupoProduto;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.service.GrupoProdutoService;
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
 * Date: 18/04/15
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class GrupoProdutoController {
    @Inject private GrupoProdutoDao grupoProdutoDao;
    @Inject private GrupoProdutoService grupoProdutoService;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    //Listar
    @RequestMapping(value = "/grupoproduto_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        model.addAttribute("mensagem", mensagemTransacao);
        model.addAttribute("grupoproduto_lista", grupoProdutoDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, grupoProdutoDao.count()));

        limparmensagem();
        return "grupoproduto_lista";
    }

    //Deletar
    @RequestMapping(value = "/grupoproduto_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            grupoProdutoService.delete(id);
            tipo = 0;
            mensagem = "Registro deletado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/grupoproduto_lista";
    }

    //Nova
    @RequestMapping(value = "/grupoproduto_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        GrupoProduto grupoProduto = new GrupoProduto();
        model.addAttribute("grupoproduto", grupoProduto);
        return "grupoproduto_novo";
    }

    //Insert
    @RequestMapping(value = "/grupoproduto_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute GrupoProduto grupoProduto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            grupoProdutoService.insert(grupoProduto);
            tipo = 0;
            mensagem = "Registro Inserido com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", grupoProdutoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, grupoProdutoDao.count()));
        mav.setViewName("redirect:/grupoproduto_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/grupoproduto_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        GrupoProduto grupoProduto = grupoProdutoDao.selectById(id);
        model.addAttribute("grupoproduto", grupoProduto);
        return "grupoproduto_editar";
    }

    //Update
    @RequestMapping(value = "/grupoproduto_update", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute GrupoProduto grupoProduto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            grupoProdutoService.update(grupoProduto);
            tipo = 0;
            mensagem = "Registro Alterado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", grupoProdutoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, grupoProdutoDao.count()));
        mav.setViewName("redirect:/grupoproduto_lista");
        return mav;
    }

    @RequestMapping(value = "/grupoproduto_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        GrupoProduto grupoProduto = grupoProdutoDao.selectById(id);
        model.addAttribute("grupoproduto", grupoProduto);
        return "grupoproduto_detalhes";
    }
}

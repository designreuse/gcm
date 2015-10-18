package com.br.gcm.controller;

import com.br.gcm.dao.MarcaProdutoDao;
import com.br.gcm.model.MarcaProduto;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.service.MarcaProdutoService;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 18/04/15
 * Time: 19:52
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class MarcaProdutoController {
    @Inject private MarcaProdutoDao marcaProdutoDao;
    @Inject private MarcaProdutoService marcaProdutoService;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    //Listar
    @RequestMapping(value = "/marcaproduto_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        MarcaProduto filtros = new MarcaProduto();

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        model.addAttribute("mensagem", mensagemTransacao);
        model.addAttribute("filtros", filtros);
        model.addAttribute("marcaproduto_lista",  marcaProdutoDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, marcaProdutoDao.count()));
        limparmensagem();
        return "marcaproduto_lista";
    }

    @RequestMapping(value = "/marcaproduto_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute MarcaProduto filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        mav.addObject("mensagem", mensagemTransacao);
        mav.addObject("filtros", filtros);
        mav.addObject("marcaproduto_lista",  marcaProdutoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, marcaProdutoDao.count()));
        limparmensagem();

        mav.setViewName("deposito_lista");
        return mav;
    }

    //Deletar
    @RequestMapping(value = "/marcaproduto_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            marcaProdutoService.delete(id);
            tipo = 0;
            mensagem = "Registro deletado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/marcaproduto_lista";
    }

    //Nova
    @RequestMapping(value = "/marcaproduto_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        MarcaProduto marcaProduto = new MarcaProduto();
        model.addAttribute("marcaproduto", marcaProduto);
        return "marcaproduto_novo";
    }

    //Insert
    @RequestMapping(value = "/marcaproduto_gravar", method = RequestMethod.POST)
    public String insert(@ModelAttribute MarcaProduto marcaProduto) {
        try{
            marcaProdutoService.insert(marcaProduto);
            tipo = 0;
            mensagem = "Registro Inserido com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/marcaproduto_lista";
    }

    //Editar
    @RequestMapping(value = "/marcaproduto_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        MarcaProduto marcaProduto = marcaProdutoDao.selectById(id);
        model.addAttribute("marcaproduto", marcaProduto);
        return "marcaproduto_editar";
    }

    //Update
    @RequestMapping(value = "/marcaproduto_update", method = RequestMethod.POST)
    public String update(@ModelAttribute MarcaProduto marcaProduto) {
        try{
            marcaProdutoService.update(marcaProduto);
            tipo = 0;
            mensagem = "Registro Alterado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/marcaproduto_lista";
    }

    @RequestMapping(value = "/marcaproduto_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        MarcaProduto marcaProduto = marcaProdutoDao.selectById(id);
        model.addAttribute("marcaproduto", marcaProduto);
        return "marcaproduto_detalhes";
    }
}

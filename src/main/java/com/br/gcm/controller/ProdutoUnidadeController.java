package com.br.gcm.controller;

import com.br.gcm.dao.ProdutoUnidadeDao;
import com.br.gcm.dao.ProdutoDao;
import com.br.gcm.dao.UnidadeDao;
import com.br.gcm.model.ProdutoUnidade;
import com.br.gcm.service.ProdutoUnidadeService;
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
 * Date: 25/04/15
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class ProdutoUnidadeController {
    @Inject private ProdutoUnidadeDao produtoUnidadeDao;
    @Inject private ProdutoDao produtoDao;
    @Inject private UnidadeDao unidadeDao;
    @Inject private ProdutoUnidadeService produtoUnidadeService;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    //Listar
    @RequestMapping(value = "/produtounidade_lista/{id_produto}")
    public String lista(@PathVariable("id_produto") int id_Produto, @PageableDefault(size = 8) Pageable pageable, Model model) {
        model.addAttribute("lista", produtoUnidadeDao.selectAll_paginado(id_Produto, pageable));
        model.addAttribute("produto", produtoDao.selectById(id_Produto));
        model.addAttribute("pagina", new Pagina(pageable, produtoUnidadeDao.count(id_Produto)));
        return "produtounidade_lista";
    }

    //Deletar
    @RequestMapping(value = "/produtounidade_deleta/{id}/{id_produto}")
    public String deletar(@PathVariable("id") Integer id, @PathVariable("id_produto") int id_Produto) {
        try{
            produtoUnidadeService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/produtounidade_lista/"+id_Produto;
    }

    //Nova
    @RequestMapping(value = "/produtounidade_novo/{id_produto}", method = RequestMethod.GET)
    public String novo(@PathVariable("id_produto") int id_Produto, ModelMap model) {
        ProdutoUnidade produtoUnidade = new ProdutoUnidade();
        model.addAttribute("produto", produtoDao.selectById(id_Produto));
        model.addAttribute("lista_unidade", unidadeDao.selectAll());
        model.addAttribute("produtounidade", produtoUnidade);
        return "produtounidade_novo";
    }

    //Insert
    @RequestMapping(value = "/produtounidade_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute ProdutoUnidade produtoUnidade, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        produtoUnidade.setUnidadePrincipal(false);
        try{
            produtoUnidadeService.insert(produtoUnidade);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", produtoUnidadeDao.selectAll_paginado(produtoUnidade.getId_Produto() ,pageable));
        mav.addObject("pagina", new Pagina(pageable, produtoUnidadeDao.count(produtoUnidade.getId_Produto())));
        mav.setViewName("redirect:/produtounidade_lista/"+produtoUnidade.getId_Produto());
        return mav;
    }

    //Editar
    @RequestMapping(value = "/produtounidade_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        ProdutoUnidade produtoUnidade = produtoUnidadeDao.selectById(id);
        model.addAttribute("produto", produtoDao.selectById(produtoUnidade.getId_Produto()));
        model.addAttribute("unidade", unidadeDao.selectById(produtoUnidade.getId_Unidade()));
        model.addAttribute("produtounidade", produtoUnidade);
        return "produtounidade_editar";
    }

    //Update
    @RequestMapping(value = "/produtounidade_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute ProdutoUnidade produtoUnidade, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        try{
            produtoUnidadeService.update(produtoUnidade);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", produtoUnidadeDao.selectAll_paginado(produtoUnidade.getId_Produto(), pageable));
        mav.addObject("pagina", new Pagina(pageable, produtoUnidadeDao.count(produtoUnidade.getId_Produto())));
        mav.setViewName("redirect:/produtounidade_lista/"+produtoUnidade.getId_Produto());
        return mav;
    }
}

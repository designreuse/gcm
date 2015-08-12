package com.br.gcm.controller;

import com.br.gcm.dao.MarcaProdutoDao;
import com.br.gcm.model.MarcaProduto;
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
        List<MarcaProduto> lista = marcaProdutoDao.selectAll_paginado(pageable);

        model.addAttribute("marcaproduto_lista", lista);
        model.addAttribute("pagina", new Pagina(pageable, marcaProdutoDao.count()));
        return "marcaproduto_lista";
    }

    //Deletar
    @RequestMapping(value = "/marcaproduto_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            marcaProdutoService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
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
    public ModelAndView insert(@ModelAttribute MarcaProduto marcaProduto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            marcaProdutoService.insert(marcaProduto);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", marcaProdutoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, marcaProdutoDao.count()));
        mav.setViewName("redirect:/marcaproduto_lista");
        return mav;
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
    public ModelAndView update(@ModelAttribute MarcaProduto marcaProduto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            marcaProdutoService.update(marcaProduto);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", marcaProdutoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, marcaProdutoDao.count()));
        mav.setViewName("redirect:/marcaproduto_lista");
        return mav;
    }
}

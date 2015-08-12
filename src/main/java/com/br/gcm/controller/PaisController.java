package com.br.gcm.controller;

import com.br.gcm.dao.PaisDao;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Pais;
import com.br.gcm.model.filtros.Filtro_Pais;
import com.br.gcm.service.PaisService;
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
import javax.servlet.ServletException;
import javax.swing.*;

@Controller
public class PaisController {
    @Inject private PaisDao paisDao;
    @Inject private PaisService paisService;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    @RequestMapping(value = "/pais_lista")
    public String pais_lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Filtro_Pais filtros = new Filtro_Pais();

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);
        model.addAttribute("mensagem", mensagemTransacao);

        model.addAttribute("pais_lista", paisDao.Pais_Paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, paisDao.count(filtros)));
        model.addAttribute("filtros", filtros);

        limparmensagem();
        return "pais_lista";
    }

    //Filtros
    @RequestMapping(value = "/pais_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute Filtro_Pais filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        mav.addObject("mensagem", mensagemTransacao);
        mav.addObject("pais_lista", paisDao.Pais_Paginado(filtros, pageable));
        mav.addObject("pagina", new Pagina(pageable, paisDao.count(filtros)));
        mav.addObject("filtros", filtros);
        mav.setViewName("pais_lista");

        limparmensagem();
        return mav;
    }

    //Deleta Pais
    @RequestMapping(value = "/pais_deleta/{id_pais}")
    public String deletar(@PathVariable("id_pais") Integer id_pais) throws ServletException {
        try{
            paisService.delete(id_pais);
            tipo = 0;
            mensagem = "Registro deletado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/pais_lista";
    }

    //Novo Pais
    @RequestMapping(value = "/pais_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Pais pais = new Pais();
        model.addAttribute("pais", pais);
        return "pais_novo";
    }

    //Gravar
    @RequestMapping(value = "/pais_gravar", method = RequestMethod.POST)
    public String insert(@ModelAttribute Pais pais, @PageableDefault(size = 10) Pageable pageable, BindingResult resultt) {
        try{
            paisService.insert(pais);
            tipo = 0;
            mensagem = "Registro Inserido com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/pais_lista";
    }

    @RequestMapping(value = "/editar_pais/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Pais pais = paisDao.selectById(id);
        model.addAttribute("pais", pais);
        return "pais_editar";
    }

    @RequestMapping(value = "/alterar_pais", method = RequestMethod.POST)
    public String update(@ModelAttribute Pais pais, BindingResult result) {
        try{
            paisService.update(pais);
            tipo = 0;
            mensagem = "Registro Alterado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/pais_lista";
    }

    @RequestMapping(value = "/pais_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Pais pais = paisDao.selectById(id);
        model.addAttribute("pais", pais);
        return "pais_detalhes";
    }
}

package com.br.gcm.controller;

import com.br.gcm.dao.NCMDao;
import com.br.gcm.dao.SituacaoTributariaPISCOFINSDao;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.NCM;
import com.br.gcm.service.NCMService;
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
 * Date: 19/04/15
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class NCMController {
    @Inject private NCMDao ncmDao;
    @Inject private NCMService ncmService;
    @Inject private SituacaoTributariaPISCOFINSDao situacaoTributariaPISCOFINSDao;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    //Listar
    @RequestMapping(value = "/ncm_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        NCM filtros = new NCM();

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        model.addAttribute("mensagem", mensagemTransacao);
        model.addAttribute("filtros", filtros);
        model.addAttribute("ncm_lista", ncmDao.selectAll_paginado(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, ncmDao.count(filtros)));

        limparmensagem();
        return "ncm_lista";
    }

    @RequestMapping(value = "/ncm_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute NCM filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        mav.addObject("mensagem", mensagemTransacao);
        mav.addObject("ncm_lista", ncmDao.selectAll_paginado(filtros, pageable));
        mav.addObject("pagina", new Pagina(pageable, ncmDao.count(filtros)));
        mav.addObject("filtros", filtros);
        mav.setViewName("ncm_lista");

        limparmensagem();
        return mav;
    }

    //Deletar
    @RequestMapping(value = "/ncm_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            ncmService.delete(id);
            tipo = 0;
            mensagem = "Registro deletado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/ncm_lista";
    }

    //Nova
    @RequestMapping(value = "/ncm_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        NCM ncm = new NCM();
        model.addAttribute("ncm", ncm);
        model.addAttribute("lista_cst", situacaoTributariaPISCOFINSDao.selectAll());
        return "ncm_novo";
    }

    //Insert
    @RequestMapping(value = "/ncm_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute NCM ncm, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            ncmService.insert(ncm);
            tipo = 0;
            mensagem = "Registro Inserido com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/ncm_lista";
    }

    //Editar
    @RequestMapping(value = "/ncm_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        NCM ncm = ncmDao.selectById(id);
        model.addAttribute("ncm", ncm);
        model.addAttribute("lista_cst", situacaoTributariaPISCOFINSDao.selectAll());
        return "ncm_editar";
    }

    //Update
    @RequestMapping(value = "/ncm_update", method = RequestMethod.POST)
    public String update(@ModelAttribute NCM ncm, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            ncmService.update(ncm);
            tipo = 0;
            mensagem = "Registro Alterado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/ncm_lista";
    }

    //Editar
    @RequestMapping(value = "/ncm_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        NCM ncm = ncmDao.selectById(id);
        model.addAttribute("ncm", ncm);
        model.addAttribute("lista_cst", situacaoTributariaPISCOFINSDao.selectAll());
        return "ncm_detalhes";
    }
}

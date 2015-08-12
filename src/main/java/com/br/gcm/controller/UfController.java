package com.br.gcm.controller;

import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Pais;
import com.br.gcm.model.Uf;
import com.br.gcm.service.UfService;
import com.br.gcm.tag.Pagina;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.swing.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 19/12/13
 * Time: 15:12
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class UfController {
    @Inject private UfDao ufDao;
    @Inject private PaisDao paisDao;
    @Inject private UfService ufService;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    @RequestMapping(value = "/uf_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {

        List<Pais> listaPais = paisDao.Pais_lista();
        Uf filtros = new Uf();
        if (!listaPais.isEmpty()) {
            filtros.setId_Pais(listaPais.get(0).getId_pais());
        }

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);
        model.addAttribute("mensagem", mensagemTransacao);

        model.addAttribute("lista_pais", listaPais);
        model.addAttribute("uf_lista", ufDao.selectAll(filtros, pageable));
        model.addAttribute("filtros", filtros);
        model.addAttribute("pagina", new Pagina(pageable, ufDao.count(filtros)));

        limparmensagem();
        return "uf_lista";
    }

    //Filtros
    @RequestMapping(value = "/uf_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute Uf filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        mav.addObject("mensagem", mensagemTransacao);
        mav.addObject("lista_pais", paisDao.Pais_lista());
        mav.addObject("uf_lista", ufDao.selectAll(filtros, pageable));
        mav.addObject("pagina", new Pagina(pageable, ufDao.count(filtros)));
        mav.addObject("filtros", filtros);
        mav.setViewName("uf_lista");

        limparmensagem();
        return mav;
    }

    //Deleta uf
    @RequestMapping(value = "/uf_deleta/{id_uf}")
    public String deletar(@PathVariable("id_uf") Integer id_uf) {
        try{
            ufService.delete(id_uf);
            tipo = 0;
            mensagem = "Registro deletado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/uf_lista";
    }

    //Novo UF
    @RequestMapping(value = "/uf_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Uf uf = new Uf();

        model.addAttribute("uf", uf);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "uf_novo";
    }

    //Gravar
    @RequestMapping(value = "/uf_gravar", method = RequestMethod.POST)
    public String insert(@ModelAttribute Uf uf, @PageableDefault(size = 10) Pageable pageable, BindingResult resultt) {
        try{
            ufService.insert(uf);
            tipo = 0;
            mensagem = "Registro Inserido com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/uf_lista";
    }

    //Editar uf
    @RequestMapping(value = "/editar_uf/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Uf uf = ufDao.selectById(id);
        model.addAttribute("uf", uf);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "uf_editar";
    }

    //Alterar_uf
    @RequestMapping(value = "/alterar_uf", method = RequestMethod.POST)
    public String update(@ModelAttribute Uf uf, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            ufService.update(uf);
            tipo = 0;
            mensagem = "Registro Alterado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/uf_lista";
    }

    @RequestMapping(value = "/uf_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Uf uf = ufDao.selectById(id);
        model.addAttribute("uf", uf);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "uf_detalhes";
    }
}

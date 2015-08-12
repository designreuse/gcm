package com.br.gcm.controller;

import com.br.gcm.dao.MunicipioDao;
import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Municipio;
import com.br.gcm.model.Pais;
import com.br.gcm.service.MunicipioService;
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
 * Date: 20/12/13
 * Time: 16:25
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class MunicipioController {
    @Inject private MunicipioDao MunicipioDao;
    @Inject private PaisDao paisDao;
    @Inject private UfDao ufDao;
    @Inject private MunicipioService municipioService;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    @RequestMapping(value = "/municipio_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Municipio filtros = new Municipio();
        List<Pais> listaPais = paisDao.Pais_lista();
        filtros.setId_pais(listaPais.get(0).getId_pais());

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);
        model.addAttribute("mensagem", mensagemTransacao);

        model.addAttribute("filtros", filtros);
        model.addAttribute("municipio_lista", MunicipioDao.selectAll(filtros, pageable));
        model.addAttribute("lista_pais", listaPais);
        model.addAttribute("pagina", new Pagina(pageable, MunicipioDao.count(filtros)));

        limparmensagem();
        return "municipio_lista";
    }

    //Filtros
    @RequestMapping(value = "/municipio_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute Municipio filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        mav.addObject("mensagem", mensagemTransacao);
        mav.addObject("lista_pais", paisDao.Pais_lista());
        mav.addObject("municipio_lista", MunicipioDao.selectAll(filtros, pageable));
        mav.addObject("pagina", new Pagina(pageable, MunicipioDao.count(filtros)));
        mav.addObject("filtros", filtros);
        mav.setViewName("municipio_lista");

        limparmensagem();
        return mav;
    }

    //Deleta municipio
    @RequestMapping(value = "/municipio_deleta/{id_municipio}")
    public String deletar(@PathVariable("id_municipio") Integer id_municipio) {
        try{
            municipioService.delete(id_municipio);
            tipo = 0;
            mensagem = "Registro deletado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/municipio_lista";
    }

    //novo
    @RequestMapping(value = "/municipio_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Municipio municipio = new Municipio();
        model.addAttribute("municipio", municipio);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "municipio_novo";
    }

    //Insert
    @RequestMapping(value = "/municipio_gravar", method = RequestMethod.POST)
    public String insert(@ModelAttribute Municipio municipio, @PageableDefault(size = 10) Pageable pageable, BindingResult resultt) {
        try{
            municipioService.insert(municipio);
            tipo = 0;
            mensagem = "Registro Inserido com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/municipio_lista";
    }

    //Editar municipio
    @RequestMapping(value = "/editar_municipio/{id}", method = RequestMethod.GET)
    public String edita(@PathVariable("id") Integer id, Model model) {
        Municipio municipio = MunicipioDao.selectById(id);
        model.addAttribute("municipio", municipio);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "municipio_editar";
    }

    //Update_municipio
    @RequestMapping(value = "/alterar_municipio", method = RequestMethod.POST)
    public ModelAndView update(@ModelAttribute Municipio municipio, @PageableDefault(size = 10) Pageable pageable, BindingResult resultt) {
        try{
            municipioService.update(municipio);
            tipo = 0;
            mensagem = "Registro Alterado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", MunicipioDao.selectAll(pageable));
        mav.addObject("pagina", new Pagina(pageable, MunicipioDao.count()));
        mav.setViewName("redirect:/municipio_lista");
        return mav;
    }

    //Editar municipio
    @RequestMapping(value = "/detalhes_municipio/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Municipio municipio = MunicipioDao.selectById(id);
        model.addAttribute("municipio", municipio);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "municipio_detalhes";
    }
}

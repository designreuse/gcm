package com.br.gcm.controller;

import com.br.gcm.dao.MunicipioDao;
import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Municipio;
import com.br.gcm.model.Pais;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.MunicipioService;
import com.br.gcm.tag.Pagina;
import com.br.gcm.util.Rotinas;
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
    @Inject private Rotinas rotinas;

    @RequestMapping(value = "/municipio_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1003");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100301");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100302");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100303");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100304");

        Municipio filtros = new Municipio();
        List<Pais> listaPais = paisDao.Pais_lista();
        filtros.setId_pais(listaPais.get(0).getId_pais());

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("filtros", filtros);
        model.addAttribute("municipio_lista", MunicipioDao.selectAll(filtros, pageable));
        model.addAttribute("lista_pais", listaPais);
        model.addAttribute("pagina", new Pagina(pageable, MunicipioDao.count(filtros)));

        return "municipio_lista";
    }

    //Filtros
    @RequestMapping(value = "/municipio_lista", method = RequestMethod.POST)
    public String filtros(@ModelAttribute Municipio filtros, Model model, @PageableDefault(size = 10) Pageable pageable) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "1003");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }
        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100301");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100302");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100303");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100304");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        model.addAttribute("municipio_lista", MunicipioDao.selectAll(filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, MunicipioDao.count(filtros)));
        model.addAttribute("filtros", filtros);

        return "municipio_lista";
    }

    //Deleta municipio
    @RequestMapping(value = "/municipio_deleta/{id_municipio}")
    public String deletar(@PathVariable("id_municipio") Integer id_municipio, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100303");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            municipioService.delete(id_municipio);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/municipio_lista";
    }

    //novo
    @RequestMapping(value = "/municipio_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100301");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Municipio municipio = new Municipio();
        model.addAttribute("municipio", municipio);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "municipio_novo";
    }

    //Insert
    @RequestMapping(value = "/municipio_gravar", method = RequestMethod.POST)
    public String insert(@ModelAttribute Municipio municipio, Model model) {
        try{
            municipioService.insert(municipio);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/municipio_lista";
    }

    //Editar municipio
    @RequestMapping(value = "/editar_municipio/{id}", method = RequestMethod.GET)
    public String edita(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100302");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Municipio municipio = MunicipioDao.selectById(id);
        model.addAttribute("municipio", municipio);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "municipio_editar";
    }

    //Update_municipio
    @RequestMapping(value = "/alterar_municipio", method = RequestMethod.POST)
    public String update(@ModelAttribute Municipio municipio, Model model) {
        try{
            municipioService.update(municipio);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }

        return "redirect:/municipio_lista";
    }

    //Editar municipio
    @RequestMapping(value = "/detalhes_municipio/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), "100304");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Municipio municipio = MunicipioDao.selectById(id);
        model.addAttribute("municipio", municipio);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "municipio_detalhes";
    }
}

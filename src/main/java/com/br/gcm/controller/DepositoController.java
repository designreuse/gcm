package com.br.gcm.controller;

import com.br.gcm.dao.DepositoDao;
import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.model.Deposito;
import com.br.gcm.model.MensagemTransacao;
import com.br.gcm.model.Empresa;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.DepositoService;
import com.br.gcm.tag.Pagina;
import com.br.gcm.util.Rotinas;
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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 19/04/15
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class DepositoController {
    @Inject private DepositoDao depositoDao;
    @Inject private DepositoService depositoService;
    @Inject private Rotinas rotinas;
    @Inject private EmpresaDao empresaDao;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    //Listar
    @RequestMapping(value = "/deposito_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());
        Deposito filtros = new Deposito();

        if (!listaEmpresa.isEmpty()){
            filtros.setId_Empresa(listaEmpresa.get(0).getId_Empresa());
        };

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        model.addAttribute("mensagem", mensagemTransacao);
        model.addAttribute("filtros", filtros);
        model.addAttribute("deposito_lista", depositoDao.selectAll_paginado(filtros, pageable));
        model.addAttribute("lista_empresa", listaEmpresa);
        model.addAttribute("pagina", new Pagina(pageable, depositoDao.count(filtros)));

        limparmensagem();
        return "deposito_lista";
    }

    @RequestMapping(value = "/deposito_lista", method = RequestMethod.POST)
    public ModelAndView filtros(@ModelAttribute Deposito filtros, @PageableDefault(size = 10) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        MensagemTransacao mensagemTransacao = new MensagemTransacao();
        mensagemTransacao.setTipo(tipo);
        mensagemTransacao.setMensagem(mensagem);

        mav.addObject("mensagem", mensagemTransacao);
        mav.addObject("filtros", filtros);
        mav.addObject("deposito_lista", depositoDao.selectAll_paginado(filtros, pageable));
        mav.addObject("lista_empresa", listaEmpresa);
        mav.addObject("pagina", new Pagina(pageable, depositoDao.count(filtros)));
        limparmensagem();

        mav.setViewName("deposito_lista");
        return mav;
    }

    //Deletar
    @RequestMapping(value = "/deposito_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            depositoService.delete(id);
            tipo = 0;
            mensagem = "Registro deletado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/deposito_lista";
    }

    //Nova
    @RequestMapping(value = "/deposito_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        Deposito deposito = new Deposito();
        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("deposito", deposito);
        return "deposito_novo";
    }

    //Insert
    @RequestMapping(value = "/deposito_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute Deposito deposito, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            depositoService.insert(deposito);
            tipo = 0;
            mensagem = "Registro Inserido com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }

        return "redirect:/deposito_lista";
    }

    //Editar
    @RequestMapping(value = "/deposito_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        List<Empresa> listaEmpresa = empresaDao.selectEmpresasUsuario(usuario.getId_usuario());

        model.addAttribute("listaempresa", listaEmpresa);
        model.addAttribute("deposito", depositoDao.selectById(id));
        return "deposito_editar";
    }

    //Update
    @RequestMapping(value = "/deposito_update", method = RequestMethod.PUT)
    public String update(@ModelAttribute Deposito deposito, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            depositoService.update(deposito);
            tipo = 0;
            mensagem = "Registro Alterado com sucesso.";
        }catch(Exception e){
            tipo = 1;
            mensagem = e.getCause().toString();
        }
        return "redirect:/deposito_lista";
    }
}

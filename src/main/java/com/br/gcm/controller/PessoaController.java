package com.br.gcm.controller;

import com.br.gcm.dao.PessoaDao;
import com.br.gcm.dao.MunicipioDao;
import com.br.gcm.dao.PaisDao;
import com.br.gcm.dao.UfDao;
import com.br.gcm.model.Pessoa;
import com.br.gcm.model.Usuario;
import com.br.gcm.model.filtros.Filtro_Pessoa;
import com.br.gcm.service.PessoaService;
import com.br.gcm.tag.Pagina;
import com.br.gcm.util.Rotinas;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 27/04/15
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class PessoaController {
    @Inject private PessoaDao pessoaDao;
    @Inject private MunicipioDao municipioDao;
    @Inject private PaisDao paisDao;
    @Inject private UfDao ufDao;
    @Inject private PessoaService pessoaService;
    @Inject private Rotinas rotinas;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/pessoa_lista/{tipo}")
    public String lista(@PathVariable("tipo") String tipo, @PageableDefault(size = 10) Pageable pageable, Model model) {
        Usuario usuario = rotinas.usuarioLogado();

        String codigopai = "";
        if (tipo.equals("CLI")){codigopai = "1201";}
        if (tipo.equals("FOR")){codigopai = "1202";}
        if (tipo.equals("VEN")){codigopai = "1203";}
        if (tipo.equals("FUN")){codigopai = "1204";}
        if (tipo.equals("TRA")){codigopai = "1205";}
        if (tipo.equals("CON")){codigopai = "1206";}
        if (tipo.equals("HOS")){codigopai = "1207";}
        if (tipo.equals("MED")){codigopai = "1208";}
        if (tipo.equals("ENF")){codigopai = "1209";}
        if (tipo.equals("PAC")){codigopai = "1210";}

        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai);
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"01");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"02");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"03");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"04");

        Pessoa filtros = new Pessoa();
        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("filtros",filtros);
        model.addAttribute("lista", pessoaDao.selectAll(tipo, filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, pessoaDao.count(tipo, filtros)));
        return "pessoa_lista";
    }

    @RequestMapping(value = "/pessoa_lista/{tipo}", method = RequestMethod.POST)
    public String filtros(@PathVariable("tipo") String tipo, @PageableDefault(size = 10) Pageable pageable, @ModelAttribute Pessoa filtros, Model model) {
        Usuario usuario = rotinas.usuarioLogado();

        String codigopai = "";
        if (tipo.equals("CLI")){codigopai = "1201";}
        if (tipo.equals("FOR")){codigopai = "1202";}
        if (tipo.equals("VEN")){codigopai = "1203";}
        if (tipo.equals("FUN")){codigopai = "1204";}
        if (tipo.equals("TRA")){codigopai = "1205";}
        if (tipo.equals("CON")){codigopai = "1206";}
        if (tipo.equals("HOS")){codigopai = "1207";}
        if (tipo.equals("MED")){codigopai = "1208";}
        if (tipo.equals("ENF")){codigopai = "1209";}
        if (tipo.equals("PAC")){codigopai = "1210";}

        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai);
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Boolean novo     = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"01");
        Boolean editar   = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"02");
        Boolean deletar  = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"03");
        Boolean detalhes = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"04");

        model.addAttribute("novo", novo);
        model.addAttribute("editar", editar);
        model.addAttribute("deletar", deletar);
        model.addAttribute("detalhes", detalhes);
        model.addAttribute("filtros",filtros);
        model.addAttribute("lista", pessoaDao.selectAll(tipo, filtros, pageable));
        model.addAttribute("pagina", new Pagina(pageable, pessoaDao.count(tipo, filtros)));
        return "pessoa_lista";
    }

    //Inativar
    @RequestMapping(value = "/pessoa_inativar/{tipo}/{id_pessoa}")
    public String inativar(@PathVariable("tipo") String tipo, @PathVariable("id_pessoa") Integer id_pessoa, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        String codigopai = "";
        if (tipo.equals("CLI")){codigopai = "1201";}
        if (tipo.equals("FOR")){codigopai = "1202";}
        if (tipo.equals("VEN")){codigopai = "1203";}
        if (tipo.equals("FUN")){codigopai = "1204";}
        if (tipo.equals("TRA")){codigopai = "1205";}
        if (tipo.equals("CON")){codigopai = "1206";}
        if (tipo.equals("HOS")){codigopai = "1207";}
        if (tipo.equals("MED")){codigopai = "1208";}
        if (tipo.equals("ENF")){codigopai = "1209";}
        if (tipo.equals("PAC")){codigopai = "1210";}

        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"03");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            pessoaService.inativar(id_pessoa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pessoa_lista/"+tipo;
    }

    @RequestMapping(value = "/pessoa_ativar/{tipo}/{id_pessoa}")
    public String ativar(@PathVariable("tipo") String tipo, @PathVariable("id_pessoa") Integer id_pessoa, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        String codigopai = "";
        if (tipo.equals("CLI")){codigopai = "1201";}
        if (tipo.equals("FOR")){codigopai = "1202";}
        if (tipo.equals("VEN")){codigopai = "1203";}
        if (tipo.equals("FUN")){codigopai = "1204";}
        if (tipo.equals("TRA")){codigopai = "1205";}
        if (tipo.equals("CON")){codigopai = "1206";}
        if (tipo.equals("HOS")){codigopai = "1207";}
        if (tipo.equals("MED")){codigopai = "1208";}
        if (tipo.equals("ENF")){codigopai = "1209";}
        if (tipo.equals("PAC")){codigopai = "1210";}

        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"03");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        try{
            pessoaService.ativar(id_pessoa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pessoa_lista/"+tipo;
    }

    //Nova
    @RequestMapping(value = "/pessoa_novo/{tipo}", method = RequestMethod.GET)
    public String novo(@PathVariable("tipo") String tipo, ModelMap model) {
        Usuario usuario = rotinas.usuarioLogado();
        String codigopai = "";
        if (tipo.equals("CLI")){codigopai = "1201";}
        if (tipo.equals("FOR")){codigopai = "1202";}
        if (tipo.equals("VEN")){codigopai = "1203";}
        if (tipo.equals("FUN")){codigopai = "1204";}
        if (tipo.equals("TRA")){codigopai = "1205";}
        if (tipo.equals("CON")){codigopai = "1206";}
        if (tipo.equals("HOS")){codigopai = "1207";}
        if (tipo.equals("MED")){codigopai = "1208";}
        if (tipo.equals("ENF")){codigopai = "1209";}
        if (tipo.equals("PAC")){codigopai = "1210";}

        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"01");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Pessoa pessoa = new Pessoa();
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("tipo", tipo);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "pessoa_novo";
    }

    //Insert
    @RequestMapping(value = "/pessoa_insert/{tipo}", method = RequestMethod.POST)
    public String insert(@PathVariable("tipo") String tipo, @ModelAttribute Pessoa pessoa, Model model) {
        try{
            pessoaService.insert(pessoa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pessoa_lista/"+tipo;
    }

    //Editar
    @RequestMapping(value = "/pessoa_editar/{tipo}/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("tipo") String tipo, @PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        String codigopai = "";
        if (tipo.equals("CLI")){codigopai = "1201";}
        if (tipo.equals("FOR")){codigopai = "1202";}
        if (tipo.equals("VEN")){codigopai = "1203";}
        if (tipo.equals("FUN")){codigopai = "1204";}
        if (tipo.equals("TRA")){codigopai = "1205";}
        if (tipo.equals("CON")){codigopai = "1206";}
        if (tipo.equals("HOS")){codigopai = "1207";}
        if (tipo.equals("MED")){codigopai = "1208";}
        if (tipo.equals("ENF")){codigopai = "1209";}
        if (tipo.equals("PAC")){codigopai = "1210";}

        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"02");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Pessoa pessoa = pessoaDao.selectById(id);
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("tipo", tipo);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "pessoa_editar";
    }

    //Update
    @RequestMapping(value = "/pessoa_update/{tipo}", method = RequestMethod.POST)
    public String update(@PathVariable("tipo") String tipo, @ModelAttribute Pessoa pessoa, Model model) {
        try{
            pessoaService.update(pessoa);
        }catch(Exception e){
            model.addAttribute("mensagem", e.getCause().getMessage().toString());
            return "mensagemerro";
        }
        return "redirect:/pessoa_lista/"+tipo;
    }

    @RequestMapping(value = "/pessoa_detalhes/{tipo}/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("tipo") String tipo, @PathVariable("id") Integer id, Model model) {
        Usuario usuario = rotinas.usuarioLogado();
        String codigopai = "";
        if (tipo.equals("CLI")){codigopai = "1201";}
        if (tipo.equals("FOR")){codigopai = "1202";}
        if (tipo.equals("VEN")){codigopai = "1203";}
        if (tipo.equals("FUN")){codigopai = "1204";}
        if (tipo.equals("TRA")){codigopai = "1205";}
        if (tipo.equals("CON")){codigopai = "1206";}
        if (tipo.equals("HOS")){codigopai = "1207";}
        if (tipo.equals("MED")){codigopai = "1208";}
        if (tipo.equals("ENF")){codigopai = "1209";}
        if (tipo.equals("PAC")){codigopai = "1210";}

        Boolean lista = rotinas.validaTransacaoUsuario(usuario.getId_usuario(), codigopai+"04");
        if (lista != true) {
            model.addAttribute("mensagem", "AVISO: Transação não permitida.");
            return "mensagemerro";
        }

        Pessoa pessoa = pessoaDao.selectById(id);
        model.addAttribute("pessoa", pessoa);
        model.addAttribute("tipo", tipo);
        model.addAttribute("lista_pais", paisDao.Pais_lista());
        return "pessoa_detalhes";
    }
}

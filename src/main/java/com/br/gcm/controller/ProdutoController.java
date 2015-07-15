package com.br.gcm.controller;

import com.br.gcm.dao.ProdutoDao;
import com.br.gcm.dao.GrupoProdutoDao;
import com.br.gcm.dao.SubGrupoProdutoDao;
import com.br.gcm.dao.SituacaoTributariaADao;
import com.br.gcm.dao.SituacaoTributariaBDao;
import com.br.gcm.dao.NCMDao;
import com.br.gcm.dao.MarcaProdutoDao;
import com.br.gcm.dao.UnidadeDao;
import com.br.gcm.model.Produto;
import com.br.gcm.model.SubGrupoProduto;
import com.br.gcm.service.ProdutoService;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 22/04/15
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class ProdutoController {
    @Inject private ProdutoDao produtoDao;
    @Inject private ProdutoService produtoService;
    @Inject private GrupoProdutoDao grupoProdutoDao;
    @Inject private SubGrupoProdutoDao subGrupoProdutoDao;
    @Inject private SituacaoTributariaADao situacaoTributariaADao;
    @Inject private SituacaoTributariaBDao situacaoTributariaBDao;
    @Inject private NCMDao ncmDao;
    @Inject private MarcaProdutoDao marcaProdutoDao;
    @Inject private UnidadeDao unidadeDao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    //Listar
    @RequestMapping(value = "/produto_lista")
    public String lista(@PageableDefault(size = 10) Pageable pageable, Model model) {
        model.addAttribute("produto_lista", produtoDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, produtoDao.count()));
        return "produto_lista";
    }

    //Deletar
    @RequestMapping(value = "/produto_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            produtoService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/produto_lista";
    }

    //Nova
    @RequestMapping(value = "/produto_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Produto produto = new Produto();
        model.addAttribute("lista_GrupoProduto", grupoProdutoDao.selectAll());
        model.addAttribute("lista_STA", situacaoTributariaADao.selectAll());
        model.addAttribute("lista_STB", situacaoTributariaBDao.selectAll());
        model.addAttribute("lista_NCM", ncmDao.selectAll());
        model.addAttribute("lista_Marca", marcaProdutoDao.selectAll());
        model.addAttribute("lista_Unidade", unidadeDao.selectAll());
        model.addAttribute("produto", produto);
        return "produto_novo";
    }

    //Insert
    @RequestMapping(value = "/produto_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute Produto produto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            produtoService.insert(produto);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", produtoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, produtoDao.count()));
        mav.setViewName("redirect:/produto_lista");
        return mav;
    }

    //Editar
    @RequestMapping(value = "/produto_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        Produto produto = produtoDao.selectById(id);
        model.addAttribute("lista_GrupoProduto", grupoProdutoDao.selectAll());
        model.addAttribute("lista_STA", situacaoTributariaADao.selectAll());
        model.addAttribute("lista_STB", situacaoTributariaBDao.selectAll());
        model.addAttribute("lista_NCM", ncmDao.selectAll());
        model.addAttribute("lista_Marca", marcaProdutoDao.selectAll());
        model.addAttribute("lista_Unidade", unidadeDao.selectAll());
        model.addAttribute("produto", produto);
        return "produto_editar";
    }

    //Update
    @RequestMapping(value = "/produto_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute Produto produto, @PageableDefault(size = 10) Pageable pageable, BindingResult result) {
        try{
            produtoService.update(produto);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", produtoDao.selectAll_paginado(pageable));
        mav.addObject("pagina", new Pagina(pageable, produtoDao.count()));
        mav.setViewName("redirect:/produto_lista");
        return mav;
    }

    //Pesquisa pela descriçao
    @RequestMapping(value="/json_listasubgrupoproduto/{id_grupoproduto}",method=RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<SubGrupoProduto> json_listasubgrupoproduto(@PathVariable("id_grupoproduto") Integer id) {
        List<SubGrupoProduto> lista = subGrupoProdutoDao.selectByid_Grupo(id);
        return lista;
    }
}

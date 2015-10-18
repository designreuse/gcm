package com.br.gcm.controller;

import com.br.gcm.dao.SituacaoTributariaPISCOFINSDao;
import com.br.gcm.model.SituacaoTributariaPISCOFINS;
import com.br.gcm.service.SituacaoTributariaPISCOFINSService;
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
 * Date: 23/04/15
 * Time: 23:48
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class SituacaoTributariaPISCOFINSController {
    @Inject private SituacaoTributariaPISCOFINSDao situacaoTributariaPISCOFINSDao;
    @Inject private SituacaoTributariaPISCOFINSService situacaoTributariaPISCOFINSService;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    //Listar
    @RequestMapping(value = "/stpiscofins_lista")
    public String lista(@PageableDefault(size = 8) Pageable pageable, Model model) {
        model.addAttribute("lista", situacaoTributariaPISCOFINSDao.selectAll_paginado(pageable));
        model.addAttribute("pagina", new Pagina(pageable, situacaoTributariaPISCOFINSDao.count()));
        return "stpiscofins_lista";
    }

    //Deletar
    @RequestMapping(value = "/stpiscofins_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            situacaoTributariaPISCOFINSService.delete(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/stpiscofins_lista";
    }

    //Nova
    @RequestMapping(value = "/stpiscofins_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS = new SituacaoTributariaPISCOFINS();
        model.addAttribute("stpiscofins", situacaoTributariaPISCOFINS);
        return "stpiscofins_novo";
    }

    //Insert
    @RequestMapping(value = "/stpiscofins_insert", method = RequestMethod.POST)
    public String insert(@ModelAttribute SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        try{
            situacaoTributariaPISCOFINSService.insert(situacaoTributariaPISCOFINS);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/stpiscofins_lista";
    }

    //Editar
    @RequestMapping(value = "/stpiscofins_editar/{id}", method = RequestMethod.GET)
    public String editar(@PathVariable("id") Integer id, Model model) {
        SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS = situacaoTributariaPISCOFINSDao.selectById(id);
        model.addAttribute("stpiscofins", situacaoTributariaPISCOFINS);
        return "stpiscofins_editar";
    }

    //Update
    @RequestMapping(value = "/stpiscofins_update", method = RequestMethod.POST)
    public String update(@ModelAttribute SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS, @PageableDefault(size = 8) Pageable pageable, BindingResult result) {
        try{
            situacaoTributariaPISCOFINSService.update(situacaoTributariaPISCOFINS);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/stpiscofins_lista";
    }

    @RequestMapping(value = "/stpiscofins_detalhes/{id}", method = RequestMethod.GET)
    public String detalhes(@PathVariable("id") Integer id, Model model) {
        SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS = situacaoTributariaPISCOFINSDao.selectById(id);
        model.addAttribute("stpiscofins", situacaoTributariaPISCOFINS);
        return "stpiscofins_detalhes";
    }
}

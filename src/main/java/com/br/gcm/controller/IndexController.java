package com.br.gcm.controller;

import com.br.gcm.util.Rotinas;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

@Controller
public class IndexController {

    @Inject private Rotinas  rotinas;

    @RequestMapping(value = {"", "/", "/index"})
    public String index(Model model) {
        // Consulta
        model.addAttribute("usuarioAtual", rotinas.getCurrentUser().getUsername());
        return "index";
    }

}

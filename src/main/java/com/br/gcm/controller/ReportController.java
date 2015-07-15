package com.br.gcm.controller;

import com.br.gcm.dao.PaisDao;
import com.br.gcm.model.Pais;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 15/06/15
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */

@Controller
@RequestMapping("/report")
public class ReportController {

    @Inject private PaisDao paisDao;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @RequestMapping(value = "/pais_report/{tipo}")
    public String paisreport(Model model, @PathVariable("tipo") String tipo) {
        List<Pais> paises = paisDao.Pais_lista();
        JRDataSource datasource = new JRBeanCollectionDataSource(paises);
        model.addAttribute("datasource", datasource);
        model.addAttribute("format", tipo);
        return "pais_report";
    }
}

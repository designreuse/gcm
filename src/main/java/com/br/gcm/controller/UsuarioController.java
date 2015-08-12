package com.br.gcm.controller;

import com.br.gcm.dao.UsuarioDao;
import com.br.gcm.model.Usuario;
import com.br.gcm.service.UsuarioService;
import com.br.gcm.tag.Pagina;
import com.br.gcm.util.Rotinas;
import freemarker.template.TemplateException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.swing.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 09/04/14
 * Time: 14:16
 * To change this template use File | Settings | File Templates.
 */

@Controller
public class UsuarioController {
    @Inject private UsuarioDao usuarioDao;
    @Inject private UsuarioService usuarioService;

    @Inject private FreeMarkerConfig freeMarkerConfig;
    @Inject private JavaMailSender mailSender;
    @Inject private Rotinas rotinas;

    private String mensagem = "";
    private int tipo = 9;

    private  void limparmensagem(){
        mensagem = "";
        tipo = 9;
    }

    @RequestMapping(value = "/usuario_lista")
    public String lista(@PageableDefault(size = 20) Pageable pageable, Model model) {
        model.addAttribute("lista", usuarioDao.all(pageable));
        model.addAttribute("pagina", new Pagina(pageable, usuarioDao.count()));
        return "usuario_lista";
    }

    @RequestMapping(value = "/usuario_deleta/{id}")
    public String deletar(@PathVariable("id") Integer id) {
        try{
            usuarioService.deleteUsuario(id);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        return "redirect:/usuario_lista";
    }

    @RequestMapping(value = "/usuario_novo", method = RequestMethod.GET)
    public String novo(ModelMap model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "usuario_novo";
    }

    public void EmailnovoUsuario(Usuario usuario) throws IOException {
        Map<String, Object> model = new HashMap<>();
        model.put("user", usuario);
        String html = null;

        if (usuario.getEmail() == null){
            return;
        }

        try{
            html = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerConfig.getConfiguration().getTemplate("novo-usuario.ftl"), model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        MimeMessage email = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {
            helper = new MimeMessageHelper(email, true, "UTF-8");
            helper.setFrom("joaocarlosdv@gmail.com", "Sistema de Gestão - Cadastro de Usuário"); //${mail.host}
            helper.addTo(usuario.getEmail(), usuario.getNome());
            helper.setSubject("Sistema de Gestão - Cadastro de Novo Usuário");
            helper.setSentDate(new Date());
            helper.setText(html, true);

            mailSender.send(email);
        }catch (MessagingException e) {
            return;
        }
    }

    @RequestMapping(value = "/usuario_insert", method = RequestMethod.POST)
    public ModelAndView insert(@ModelAttribute Usuario usuario) {

        try{
            usuarioService.insertUsuario(usuario);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", usuarioDao.all());
        mav.setViewName("redirect:/usuario_lista");

        try {
            EmailnovoUsuario(usuario);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mav;
    }

    @RequestMapping(value = "/usuario_editar/{id}", method = RequestMethod.GET)
    public String edita(@PathVariable("id") Integer id, Model model) {
        Usuario usuario = usuarioDao.byId(id);
        model.addAttribute("usuario", usuario);
        return "usuario_editar";
    }

    @RequestMapping(value = "/usuario_update", method = RequestMethod.PUT)
    public ModelAndView update(@ModelAttribute Usuario usuario) {

        try{
            usuarioService.updateUsuario(usuario);
        }catch(Exception e){
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,e.getCause().toString(),"Alerta", JOptionPane.INFORMATION_MESSAGE);
        }
        ModelAndView mav = new ModelAndView();
        mav.addObject("lista", usuarioDao.all());
        mav.setViewName("redirect:/usuario_lista");
        return mav;
    }

    @RequestMapping(value = "/usuario_novasenha/{id}")
    public String novasenha(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioDao.byId(id);
        String novasenha = rotinas.gerarSenhaAleatoria();
        String novasenhaMD5 = rotinas.md5(novasenha, Charset.defaultCharset());

        usuario.setSenha(novasenhaMD5);
        usuarioService.updateUsuario(usuario);

        //Seta a senha pura para enviar o e-mail
        usuario.setSenha(novasenha);
        try {
            EmailAlterarSenhaUsuario(usuario);
            JOptionPane JOptinPane = new JOptionPane();
            JOptinPane.showMessageDialog(null,"Senha encaminhada por E-Mail","Alerta", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "redirect:/usuario_lista";
    }

    public void EmailAlterarSenhaUsuario(Usuario usuario) throws IOException {
        Map<String, Object> model = new HashMap<>();
        model.put("user", usuario);
        String html = null;

        if (usuario.getEmail() == null){
            return;
        }

        try{
            html = FreeMarkerTemplateUtils.processTemplateIntoString(freeMarkerConfig.getConfiguration().getTemplate("novasenha-usuario.ftl"), model);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }

        MimeMessage email = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;

        try {
            helper = new MimeMessageHelper(email, true, "UTF-8");
            helper.setFrom("joaocarlosdv@gmail.com", "Sistema de Gestão - Nova Senha"); //${mail.host}
            helper.addTo(usuario.getEmail(), usuario.getNome());
            helper.setSubject("Sistema de Gestão -Nova Senha");
            helper.setSentDate(new Date());
            helper.setText(html, true);

            mailSender.send(email);
        }catch (MessagingException e) {
            return;
        }
    }
}

package com.br.gcm.security;

import com.br.gcm.dao.UsuarioDao;
import com.br.gcm.util.Rotinas;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

    @Inject private Rotinas rotinas;
    @Inject private UsuarioDao usuarioDao;

    public CustomLogoutSuccessHandler() {
        super();
        setDefaultTargetUrl("/");
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (authentication != null) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            logger.info("Usuário '"+userDetails.getUsername().toUpperCase(rotinas.getPT_BR())+"' saiu do sistema");
        }
        super.onLogoutSuccess(request, response, authentication);
    }
}

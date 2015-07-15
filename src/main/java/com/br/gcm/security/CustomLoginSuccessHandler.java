package com.br.gcm.security;

import com.br.gcm.dao.UsuarioDao;
import com.br.gcm.util.Rotinas;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Inject private Rotinas rotinas;
    @Inject private UsuarioDao usuarioDao;

    public CustomLoginSuccessHandler() {
        super();
        setAlwaysUseDefaultTargetUrl(false);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        logger.info("'"+userDetails.getUsername().toUpperCase(rotinas.getPT_BR()) + "' logou");
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
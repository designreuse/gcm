package com.br.gcm.security;

import com.br.gcm.dao.UsuarioDao;
import com.br.gcm.model.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Inject private UsuarioDao usuarioDao;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info(exception.getMessage());
        request.getSession().setAttribute("error", exception.getMessage());
        setDefaultFailureUrl("/");
        super.onAuthenticationFailure(request, response, exception);

        Authentication auth = exception.getAuthentication();
        if (auth != null) {
            String nome = (String) auth.getPrincipal();
            // verifica se usuário existe
            Usuario usuario = usuarioDao.byNome(nome);
            if (usuario == null) {             //
                String msgComplementar = exception.getMessage().equals("Erro ao entrar no sistema") ? "" :  "Detalhe do erro: " + exception.getMessage();
            }
        }

    }

}
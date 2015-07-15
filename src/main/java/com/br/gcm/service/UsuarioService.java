package com.br.gcm.service;

import com.br.gcm.dao.UsuarioDao;
import com.br.gcm.model.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class UsuarioService {

    private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    @Inject private UsuarioDao usuarioDao;

    @Transactional
    public void updateUsuario(Usuario usuario) {
        usuarioDao.update(usuario);
    }

    @Transactional
    public void insertUsuario(Usuario usuario) {
        usuarioDao.insert(usuario);
    }

    @Transactional
    public void deleteUsuario(Integer id) {
        try {
            usuarioDao.delete(id);
        }catch(Exception e) {
            throw new Error("Erro ao Deletar: "+e);
        }
    }
}

package com.br.gcm.service;


import com.br.gcm.dao.GrupoUsuarioDao;
import com.br.gcm.dao.UsuariodoGrupoDao;
import com.br.gcm.dao.GrupoTransacaoDao;
import com.br.gcm.dao.EmpresaGrupoDao;
import com.br.gcm.model.GrupoUsuario;
import com.br.gcm.model.UsuariodoGrupo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 12/04/15
 * Time: 11:08
 * To change this template use File | Settings | File Templates.
 */

@Service
public class GrupoUsuarioService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoUsuarioService.class);

    @Inject private GrupoUsuarioDao grupoUsuarioDao;
    @Inject private UsuariodoGrupoDao usuariodoGrupoDao;
    @Inject private GrupoTransacaoDao grupoTransacaoDao;
    @Inject private EmpresaGrupoDao empresaGrupoDao;

    @Transactional
    public void insert(GrupoUsuario grupo) {
        grupoUsuarioDao.insert(grupo);
    }

    @Transactional
    public void insert_usuariodogrupo(List<UsuariodoGrupo> lista) {
        usuariodoGrupoDao.insert(lista);
    }

    @Transactional
    public void insert_usuariodogrupo_byid(int id_grupousuario, int id_usuario, boolean pertence) {
        usuariodoGrupoDao.insert(id_grupousuario, id_usuario, pertence);
    }

    @Transactional
    public void insert_grupotransacao(int id_grupousuario, int id_transacao, boolean pertence) {
        grupoTransacaoDao.insert(id_grupousuario, id_transacao, pertence);
    }

    @Transactional
    public void insert_empresagrupo(int id_grupousuario, int id_empresa, boolean pertence) {
        empresaGrupoDao.insert(id_grupousuario, id_empresa, pertence);
    }

    @Transactional
    public void update(GrupoUsuario grupo) {
        grupoUsuarioDao.update(grupo);
    }

    @Transactional
    public void delete(Integer id) {
        grupoUsuarioDao.deleteById(id);
    }
}

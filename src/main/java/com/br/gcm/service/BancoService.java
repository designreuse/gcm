package com.br.gcm.service;

import com.br.gcm.dao.BancoDao;
import com.br.gcm.model.Banco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 05/05/15
 * Time: 21:52
 * To change this template use File | Settings | File Templates.
 */

@Service
public class BancoService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private BancoDao bancoDao;

    @Transactional
    public void insert(Banco banco) {
        bancoDao.insert(banco);
    }

    @Transactional
    public void update(Banco banco) {
        bancoDao.update(banco);
    }

    @Transactional
    public void delete(int id) {
        bancoDao.deleteById(id);
    }
}

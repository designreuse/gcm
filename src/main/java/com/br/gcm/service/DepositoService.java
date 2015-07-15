package com.br.gcm.service;

import com.br.gcm.dao.DepositoDao;
import com.br.gcm.model.Deposito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 19/04/15
 * Time: 22:50
 * To change this template use File | Settings | File Templates.
 */

@Service
public class DepositoService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private DepositoDao depositoDao;

    @Transactional
    public void insert(Deposito deposito) {
        depositoDao.insert(deposito);
    }

    @Transactional
    public void update(Deposito deposito) {
        depositoDao.update(deposito);
    }

    @Transactional
    public void delete(int id) {
        depositoDao.deleteById(id);
    }
}

package com.br.gcm.service;

import com.br.gcm.dao.ContaCorrenteDao;
import com.br.gcm.model.ContaCorrente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 06/05/15
 * Time: 08:36
 * To change this template use File | Settings | File Templates.
 */

@Service
public class ContaCorrenteService {
    private static final Logger logger = LoggerFactory.getLogger(ContaCorrenteService.class);

    @Inject private ContaCorrenteDao contaCorrenteDao;

    @Transactional
    public void insert(ContaCorrente contaCorrente) {
        contaCorrenteDao.insert(contaCorrente);
    }

    @Transactional
    public void update(ContaCorrente contaCorrente) {
        contaCorrenteDao.update(contaCorrente);
    }

    @Transactional
    public void delete(int id) {
        contaCorrenteDao.deleteById(id);
    }
}

package com.br.gcm.service;

import com.br.gcm.dao.PlanoContasDao;
import com.br.gcm.model.PlanoContas;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 05/05/15
 * Time: 16:47
 * To change this template use File | Settings | File Templates.
 */

@Service
public class PlanoContasService {
    private static final Logger logger = LoggerFactory.getLogger(PlanoContasService.class);

    @Inject private PlanoContasDao planoContasDao;

    @Transactional
    public void insert(PlanoContas planoContas) {
        planoContasDao.insert(planoContas);
    }

    @Transactional
    public void update(PlanoContas planoContas) {
        planoContasDao.update(planoContas);
    }

    @Transactional
    public void delete(int id) {
        planoContasDao.deleteById(id);
    }
}

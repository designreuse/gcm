package com.br.gcm.service;

import com.br.gcm.dao.CentroCustoDao;
import com.br.gcm.model.CentroCusto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 02/05/15
 * Time: 09:26
 * To change this template use File | Settings | File Templates.
 */

@Service
public class CentroCustoService {
    private static final Logger logger = LoggerFactory.getLogger(CentroCustoService.class);

    @Inject private CentroCustoDao centroCustoDao;

    @Transactional
    public void insert(CentroCusto centroCusto) {
        centroCustoDao.insert(centroCusto);
    }

    @Transactional
    public void update(CentroCusto centroCusto) {
        centroCustoDao.update(centroCusto);
    }

    @Transactional
    public void delete(int id) {
        centroCustoDao.deleteById(id);
    }
}

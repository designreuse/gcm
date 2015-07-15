package com.br.gcm.service;

import com.br.gcm.dao.PaisDao;
import com.br.gcm.model.Pais;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 19/03/14
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */

@Service
public class PaisService {
    private static final Logger logger = LoggerFactory.getLogger(PaisService.class);

    @Inject private PaisDao paisDao;

    @Transactional
    public void insert(Pais pais) {
        paisDao.insert(pais);
    }

    @Transactional
    public void update(Pais pais) {
        paisDao.update(pais);
    }

    @Transactional
    public void delete(Integer id) {
        paisDao.deleteById(id);
    }
}

package com.br.gcm.service;

import com.br.gcm.dao.MunicipioDao;
import com.br.gcm.model.Municipio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 19/03/14
 * Time: 11:35
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MunicipioService {
    private static final Logger logger = LoggerFactory.getLogger(MunicipioService.class);

    @Inject private MunicipioDao municipioDao;

    @Transactional
    public void insert(Municipio municipio) {
        municipioDao.insert(municipio);
    }

    @Transactional
    public void update(Municipio municipio) {
        municipioDao.update(municipio);
    }

    @Transactional
    public void delete(Integer id) {
        municipioDao.deleteById(id);
    }
}

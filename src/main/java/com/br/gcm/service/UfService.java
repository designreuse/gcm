package com.br.gcm.service;

import com.br.gcm.dao.UfDao;
import com.br.gcm.model.Uf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 19/03/14
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UfService {
    private static final Logger logger = LoggerFactory.getLogger(UfService.class);

    @Inject private UfDao ufDao;

    @Transactional
    public void insert(Uf uf) {
        ufDao.insert(uf);
    }

    @Transactional
    public void update(Uf uf) {
        ufDao.update(uf);
    }

    @Transactional
    public void delete(Integer id) {
        ufDao.deleteById(id);
    }
}

package com.br.gcm.service;

import com.br.gcm.dao.NCMDao;
import com.br.gcm.model.NCM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 19/04/15
 * Time: 14:09
 * To change this template use File | Settings | File Templates.
 */

@Service
public class NCMService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private NCMDao ncmDao;

    @Transactional
    public void insert(NCM ncm) {
        ncmDao.insert(ncm);
    }

    @Transactional
    public void update(NCM ncm) {
        ncmDao.update(ncm);
    }

    @Transactional
    public void delete(int id) {
        ncmDao.deleteById(id);
    }
}

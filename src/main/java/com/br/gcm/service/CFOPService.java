package com.br.gcm.service;

import com.br.gcm.dao.CFOPDao;
import com.br.gcm.dao.RelacaoCFOPDao;
import com.br.gcm.model.CFOP;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 30/04/15
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */

@Service
public class CFOPService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private CFOPDao cfopDao;
    @Inject private RelacaoCFOPDao relacaoCFOPDao;

    @Transactional
    public void insert(CFOP cfop) {
        cfopDao.insert(cfop);
    }

    @Transactional
    public void update(CFOP cfop) {
        cfopDao.update(cfop);
    }

    @Transactional
    public void delete(int id) {
        cfopDao.deleteById(id);
    }

    @Transactional
    public void insertRelacao(int id_cfop, int id_cfoprelacao, boolean pertence) {
        relacaoCFOPDao.insert(id_cfop, id_cfoprelacao, pertence);
    }

}

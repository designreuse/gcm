package com.br.gcm.service;

import com.br.gcm.dao.SituacaoTributariaADao;
import com.br.gcm.model.SituacaoTributariaA;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 21/04/15
 * Time: 22:48
 * To change this template use File | Settings | File Templates.
 */

@Service
public class SituacaoTributariaAService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private SituacaoTributariaADao situacaoTributariaADao;

    @Transactional
    public void insert(SituacaoTributariaA situacaoTributariaA) {
        situacaoTributariaADao.insert(situacaoTributariaA);
    }

    @Transactional
    public void update(SituacaoTributariaA situacaoTributariaA) {
        situacaoTributariaADao.update(situacaoTributariaA);
    }

    @Transactional
    public void delete(int id) {
        situacaoTributariaADao.deleteById(id);
    }
}

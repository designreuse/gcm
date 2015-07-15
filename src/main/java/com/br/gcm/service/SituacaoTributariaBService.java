package com.br.gcm.service;

import com.br.gcm.dao.SituacaoTributariaBDao;
import com.br.gcm.model.SituacaoTributariaB;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 22/04/15
 * Time: 09:34
 * To change this template use File | Settings | File Templates.
 */

@Service
public class SituacaoTributariaBService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private SituacaoTributariaBDao situacaoTributariaBDao;

    @Transactional
    public void insert(SituacaoTributariaB situacaoTributariaB) {
        situacaoTributariaBDao.insert(situacaoTributariaB);
    }

    @Transactional
    public void update(SituacaoTributariaB situacaoTributariaB) {
        situacaoTributariaBDao.update(situacaoTributariaB);
    }

    @Transactional
    public void delete(int id) {
        situacaoTributariaBDao.deleteById(id);
    }
}

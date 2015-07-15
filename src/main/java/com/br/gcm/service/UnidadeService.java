package com.br.gcm.service;

import com.br.gcm.dao.UnidadeDao;
import com.br.gcm.model.Unidade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 21/04/15
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */

@Service
public class UnidadeService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private UnidadeDao unidadeDao;

    @Transactional
    public void insert(Unidade unidade) {
        unidadeDao.insert(unidade);
    }

    @Transactional
    public void update(Unidade unidade) {
        unidadeDao.update(unidade);
    }

    @Transactional
    public void delete(int id) {
        unidadeDao.deleteById(id);
    }
}

package com.br.gcm.service;

import com.br.gcm.dao.ProdutoUnidadeDao;
import com.br.gcm.model.ProdutoUnidade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 25/04/15
 * Time: 12:46
 * To change this template use File | Settings | File Templates.
 */

@Service
public class ProdutoUnidadeService {
    private static final Logger logger = LoggerFactory.getLogger(ProdutoUnidadeService.class);

    @Inject private ProdutoUnidadeDao produtoUnidadeDao;

    @Transactional
    public void insert(ProdutoUnidade produtoUnidade) {
        produtoUnidadeDao.insert(produtoUnidade);
    }

    @Transactional
    public void update(ProdutoUnidade produtoUnidade) {
        produtoUnidadeDao.update(produtoUnidade);
    }

    @Transactional
    public void delete(int id) {
        produtoUnidadeDao.deleteById(id);
    }
}

package com.br.gcm.service;

import com.br.gcm.dao.ProdutoLoteDao;
import com.br.gcm.model.ProdutoLote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 26/04/15
 * Time: 11:17
 * To change this template use File | Settings | File Templates.
 */

@Service
public class ProdutoLoteService {
    private static final Logger logger = LoggerFactory.getLogger(ProdutoLoteService.class);

    @Inject private ProdutoLoteDao produtoLoteDao;

    @Transactional
    public void insert(ProdutoLote produtoLote) {
        produtoLoteDao.insert(produtoLote);
    }

    @Transactional
    public void update(ProdutoLote produtoLote) {
        produtoLoteDao.update(produtoLote);
    }

    @Transactional
    public void delete(int id) {
        produtoLoteDao.deleteById(id);
    }
}

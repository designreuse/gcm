package com.br.gcm.service;

import com.br.gcm.dao.ProdutoDao;
import com.br.gcm.model.Produto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 22/04/15
 * Time: 15:26
 * To change this template use File | Settings | File Templates.
 */

@Service
public class ProdutoService {
    private static final Logger logger = LoggerFactory.getLogger(ProdutoService.class);

    @Inject private ProdutoDao produtoDao;

    @Transactional
    public void insert(Produto produto) {
        produtoDao.insert(produto);
    }

    @Transactional
    public void update(Produto produto) {
        produtoDao.update(produto);
    }

    @Transactional
    public void delete(int id) {
        produtoDao.deleteById(id);
    }
}

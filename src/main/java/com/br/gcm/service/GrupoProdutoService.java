package com.br.gcm.service;

import com.br.gcm.dao.GrupoProdutoDao;
import com.br.gcm.model.GrupoProduto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 18/04/15
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */

@Service
public class GrupoProdutoService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private GrupoProdutoDao grupoProdutoDao;

    @Transactional
    public void insert(GrupoProduto grupoProduto) {
        grupoProdutoDao.insert(grupoProduto);
    }

    @Transactional
    public void update(GrupoProduto grupoProduto) {
        grupoProdutoDao.update(grupoProduto);
    }

    @Transactional
    public void delete(int id) {
        grupoProdutoDao.deleteById(id);
    }
}

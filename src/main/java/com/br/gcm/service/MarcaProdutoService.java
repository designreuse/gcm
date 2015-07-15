package com.br.gcm.service;

import com.br.gcm.dao.MarcaProdutoDao;
import com.br.gcm.model.MarcaProduto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 18/04/15
 * Time: 19:49
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MarcaProdutoService {
    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private MarcaProdutoDao marcaProdutoDao;

    @Transactional
    public void insert(MarcaProduto marcaProduto) {
        marcaProdutoDao.insert(marcaProduto);
    }

    @Transactional
    public void update(MarcaProduto marcaProduto) {
        marcaProdutoDao.update(marcaProduto);
    }

    @Transactional
    public void delete(int id) {
        marcaProdutoDao.deleteById(id);
    }
}

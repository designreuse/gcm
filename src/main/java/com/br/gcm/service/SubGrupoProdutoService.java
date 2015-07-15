package com.br.gcm.service;

import com.br.gcm.dao.SubGrupoProdutoDao;
import com.br.gcm.model.SubGrupoProduto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;


/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 18/04/15
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
@Service
public class SubGrupoProdutoService {
    private static final Logger logger = LoggerFactory.getLogger(SubGrupoProdutoService.class);

    @Inject private SubGrupoProdutoDao subGrupoProdutoDao;

    @Transactional
    public void insert(SubGrupoProduto subGrupoProduto) {
        subGrupoProdutoDao.insert(subGrupoProduto);
    }

    @Transactional
    public void update(SubGrupoProduto subGrupoProduto) {
        subGrupoProdutoDao.update(subGrupoProduto);
    }

    @Transactional
    public void delete(int id) {
        subGrupoProdutoDao.deleteById(id);
    }
}

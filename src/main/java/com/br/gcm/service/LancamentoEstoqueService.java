package com.br.gcm.service;

import com.br.gcm.dao.LancamentoEstoqueDao;
import com.br.gcm.dao.LancamentoEstoqueItemDao;
import com.br.gcm.model.LancamentoEstoque;
import com.br.gcm.model.LancamentoEstoqueItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 09/05/15
 * Time: 12:31
 * To change this template use File | Settings | File Templates.
 */

@Service
public class LancamentoEstoqueService {
    private static final Logger logger = LoggerFactory.getLogger(LancamentoEstoqueService.class);

    @Inject private LancamentoEstoqueDao lancamentoEstoqueDao;
    @Inject private LancamentoEstoqueItemDao lancamentoEstoqueItemDao;

    @Transactional
    public void insert(LancamentoEstoque lancamentoEstoque) {
        lancamentoEstoqueDao.insert(lancamentoEstoque);
    }

    @Transactional
    public void update(LancamentoEstoque lancamentoEstoque) {
        lancamentoEstoqueDao.update(lancamentoEstoque);
    }

    @Transactional
    public void delete(int id) {
        lancamentoEstoqueDao.deleteById(id);
    }

    @Transactional
    public void insertitem(LancamentoEstoqueItem lancamentoEstoqueItem) {
        lancamentoEstoqueItemDao.insert(lancamentoEstoqueItem);
    }

    @Transactional
    public void updateitem(LancamentoEstoqueItem lancamentoEstoqueItem) {
        lancamentoEstoqueItemDao.update(lancamentoEstoqueItem);
    }

    @Transactional
    public void deleteitem(int id) {
        lancamentoEstoqueItemDao.deleteById(id);
    }
}

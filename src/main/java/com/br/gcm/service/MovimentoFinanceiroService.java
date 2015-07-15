package com.br.gcm.service;

import com.br.gcm.dao.MovimentoFinanceiroDao;
import com.br.gcm.model.MovimentoFinanceiro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 04/06/15
 * Time: 22:42
 * To change this template use File | Settings | File Templates.
 */

@Service
public class MovimentoFinanceiroService {

    private static final Logger logger = LoggerFactory.getLogger(GrupoProdutoService.class);

    @Inject private MovimentoFinanceiroDao movimentoFinanceiroDao;

    @Transactional
    public void insert(MovimentoFinanceiro mov) {
        movimentoFinanceiroDao.insert(mov);
    }

    @Transactional
    public void update(MovimentoFinanceiro mov) {
        movimentoFinanceiroDao.update(mov);
    }
}

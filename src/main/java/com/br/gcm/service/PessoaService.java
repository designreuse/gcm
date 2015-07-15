package com.br.gcm.service;

import com.br.gcm.dao.PessoaDao;
import com.br.gcm.model.Pessoa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 27/04/15
 * Time: 09:05
 * To change this template use File | Settings | File Templates.
 */

@Service
public class PessoaService {
    private static final Logger logger = LoggerFactory.getLogger(PessoaService.class);

    @Inject private PessoaDao pessoaDao;

    @Transactional
    public void insert(Pessoa pessoa) {
        pessoaDao.insert(pessoa);
    }

    @Transactional
    public void update(Pessoa pessoa) {
        pessoaDao.update(pessoa);
    }

    @Transactional
    public void inativar(int id) {
        pessoaDao.InativarById(id);
    }
}

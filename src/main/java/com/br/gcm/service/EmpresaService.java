package com.br.gcm.service;

import com.br.gcm.dao.EmpresaDao;
import com.br.gcm.model.Empresa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 19/03/14
 * Time: 12:42
 * To change this template use File | Settings | File Templates.
 */

@Service
public class EmpresaService {
    private static final Logger logger = LoggerFactory.getLogger(EmpresaService.class);

    @Inject private EmpresaDao empresaDao;

    @Transactional
    public void insert(Empresa empresa) {
        empresaDao.insert(empresa);
    }

    @Transactional
    public void update(Empresa empresa) {
        empresaDao.update(empresa);
    }

    @Transactional
    public void delete(Integer id) {
        empresaDao.deleteById(id);
    }
}

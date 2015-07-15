package com.br.gcm.service;

import com.br.gcm.dao.SituacaoTributariaPISCOFINSDao;
import com.br.gcm.model.SituacaoTributariaPISCOFINS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 23/04/15
 * Time: 23:44
 * To change this template use File | Settings | File Templates.
 */

@Service
public class SituacaoTributariaPISCOFINSService {
    private static final Logger logger = LoggerFactory.getLogger(SituacaoTributariaPISCOFINSService.class);

    @Inject private SituacaoTributariaPISCOFINSDao situacaoTributariaPISCOFINSDao;

    @Transactional
    public void insert(SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS) {
        situacaoTributariaPISCOFINSDao.insert(situacaoTributariaPISCOFINS);
    }

    @Transactional
    public void update(SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS) {
        situacaoTributariaPISCOFINSDao.update(situacaoTributariaPISCOFINS);
    }

    @Transactional
    public void delete(int id) {
        situacaoTributariaPISCOFINSDao.deleteById(id);
    }
}

package com.br.gcm.dao;

import com.br.gcm.model.CFOP;
import com.br.gcm.model.RelacaoCFOP;
import com.br.gcm.util.Rotinas;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 03/05/15
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class RelacaoCFOPDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;
    @Inject private CFOPDao cfopDao;

    public void insert(int id_CFOP, int id_RelacaoCFOP, boolean pertence) {
        db.update("delete from  RelacaoCFOP where id_CFOP=? and id_CFOPRelacao=?", id_CFOP, id_RelacaoCFOP);

        if (pertence == true){
            db.update("insert into RelacaoCFOP (id_CFOP, id_CFOPRelacao) values(?,?)",
                    id_CFOP,
                    id_RelacaoCFOP);
        }
    }

    public List<RelacaoCFOP> selectAll(int id_CFOP) {
        return db.query("select " +
                id_CFOP+" as id_CFOP, " +
                "cfop.id_cfop as Id_CFOPRelacao, " +
                " case " +
                " when (select count(id_cfop) from relacaocfop where relacaocfop.id_CFOPRelacao = cfop.id_cfop and relacaocfop.id_cfop = ?) > 0 then true " +
                " else false " +
                " end as pertence " +
                " from cfop " +
                " Where cfop.id_cfop <> "+id_CFOP +
                " And cfop.tipo <> (select tipo from cfop where id_cfop = "+id_CFOP+")",
                listaRelacaoCFOP, id_CFOP);
    }

    //mappers
    private RowMapper<RelacaoCFOP> listaRelacaoCFOP = new RowMapper<RelacaoCFOP>() {
        @Override
        public RelacaoCFOP mapRow(ResultSet rs, int i) throws SQLException{
            RelacaoCFOP relacaoCFOP = new RelacaoCFOP();
            relacaoCFOP.setId_CFOPRelacao(rs.getInt("Id_CFOPRelacao"));
            relacaoCFOP.setId_CFOP(rs.getInt("Id_CFOP"));
            relacaoCFOP.setPertence(rs.getBoolean("pertence"));

            relacaoCFOP.setCfop(cfopDao.selectById(rs.getInt("Id_CFOP")));
            relacaoCFOP.setRelacaoCFOP(cfopDao.selectById(rs.getInt("Id_CFOPRelacao")));

            return relacaoCFOP;
        }
    };
}

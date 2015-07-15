package com.br.gcm.dao;

import com.br.gcm.model.SituacaoTributariaA;
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
 * Date: 21/04/15
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class SituacaoTributariaADao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(SituacaoTributariaA situacaoTributariaA) {
        db.update("insert into SituacaoTributariaA (CodigoSta, descricao) values(?,?)",
                situacaoTributariaA.getCodigoSTA(),
                situacaoTributariaA.getDescricao());
    }

    public void update(SituacaoTributariaA situacaoTributariaA){
        db.update("update SituacaoTributariaA set CodigoSta=?, descricao=? where id_STA = ?",
                situacaoTributariaA.getCodigoSTA(),
                situacaoTributariaA.getDescricao(),
                situacaoTributariaA.getId_STA());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM SituacaoTributariaA Where id_STA=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM SituacaoTributariaA", Long.class);
    }

    public SituacaoTributariaA selectById(Integer id) {
        return db.queryForObject("Select * from SituacaoTributariaA Where id_STA=?", listaSituacaoTributariaA, id);
    }

    public List<SituacaoTributariaA> selectAll() {
        return db.query("Select Id_STA, CodigoSTA, CodigoSTA||' - '||Descricao as Descricao from SituacaoTributariaA", listaSituacaoTributariaA);
    }

    public List<SituacaoTributariaA> selectAll_paginado(Pageable p) {
        return db.query("Select * from  SituacaoTributariaA Order By CodigoSTA " +
                "LIMIT ? OFFSET ? ",
                listaSituacaoTributariaA,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<SituacaoTributariaA> listaSituacaoTributariaA = new RowMapper<SituacaoTributariaA>() {
        @Override
        public SituacaoTributariaA mapRow(ResultSet rs, int i) throws SQLException{
            SituacaoTributariaA situacaoTributariaA = new SituacaoTributariaA();
            situacaoTributariaA.setId_STA(rs.getInt("Id_STA"));
            situacaoTributariaA.setCodigoSTA(rs.getString("CodigoSTA"));
            situacaoTributariaA.setDescricao(rs.getString("Descricao"));

            return situacaoTributariaA;
        }
    };
}

package com.br.gcm.dao;

import com.br.gcm.model.SituacaoTributariaB;
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
 * Date: 22/04/15
 * Time: 09:25
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class SituacaoTributariaBDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(SituacaoTributariaB situacaoTributariaB) {
        db.update("insert into SituacaoTributariaB (CodigoStb, descricao) values(?,?)",
                situacaoTributariaB.getCodigoSTB(),
                situacaoTributariaB.getDescricao());
    }

    public void update(SituacaoTributariaB situacaoTributariaB){
        db.update("update SituacaoTributariaB set CodigoStb=?, descricao=? where id_STB = ?",
                situacaoTributariaB.getCodigoSTB(),
                situacaoTributariaB.getDescricao(),
                situacaoTributariaB.getId_STB());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM SituacaoTributariaB Where id_STB=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM SituacaoTributariaB", Long.class);
    }

    public SituacaoTributariaB selectById(Integer id) {
        return db.queryForObject("Select * from SituacaoTributariaB Where id_STB=?", listaSituacaoTributariaB, id);
    }

    public List<SituacaoTributariaB> selectAll() {
        return db.query("Select Id_STB, CodigoSTB, CodigoSTB||' - '||Descricao as Descricao from SituacaoTributariaB", listaSituacaoTributariaB);
    }

    public List<SituacaoTributariaB> selectAll_paginado(Pageable p) {
        return db.query("Select * from  SituacaoTributariaB Order By CodigoSTB " +
                "LIMIT ? OFFSET ? ",
                listaSituacaoTributariaB,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<SituacaoTributariaB> listaSituacaoTributariaB = new RowMapper<SituacaoTributariaB>() {
        @Override
        public SituacaoTributariaB mapRow(ResultSet rs, int i) throws SQLException{
            SituacaoTributariaB situacaoTributariaB = new SituacaoTributariaB();
            situacaoTributariaB.setId_STB(rs.getInt("Id_STB"));
            situacaoTributariaB.setCodigoSTB(rs.getString("CodigoSTB"));
            situacaoTributariaB.setDescricao(rs.getString("Descricao"));

            return situacaoTributariaB;
        }
    };
}

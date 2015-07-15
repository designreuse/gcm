package com.br.gcm.dao;

import com.br.gcm.model.CFOP;
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
 * Date: 30/04/15
 * Time: 14:40
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class CFOPDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(CFOP cfop) {
        db.update("insert into CFOP (CodigoCFOP, Descricao,Tipo, ajuste, ExigeRetorno, DiasRetorno) values(?,?,?,?,?,?)",
                cfop.getCodigoCFOP(),
                cfop.getDescricao(),
                cfop.getTipo(),
                cfop.getAjuste(),
                cfop.getExigeRetorno(),
                cfop.getDiasRetorno());
    }

    public void update(CFOP cfop) {
        db.update("update CFOP set CodigoCFOP=?, Descricao=?, Tipo=?, ajuste=?, ExigeRetorno=?, DiasRetorno=? " +
                "Where id_CFOP=? ",
                cfop.getCodigoCFOP(),
                cfop.getDescricao(),
                cfop.getTipo(),
                cfop.getAjuste(),
                cfop.getExigeRetorno(),
                cfop.getDiasRetorno(),
                cfop.getId_CFOP());
    }

    public void deleteById(int id) {
        db.update("Delete from  CFOP Where id_CFOP=? ", id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM CFOP", Long.class);
    }

    public CFOP selectById(Integer id) {
        return db.queryForObject("Select * from CFOP Where id_CFOP=?", listaCFOP, id);
    }

    public List<CFOP> selectAll() {
        return db.query("Select * from CFOP", listaCFOP);
    }

    public List<CFOP> selectAll_Filtros(CFOP filtros) {
        return db.query("Select * from CFOP Where Tipo=? and Ajuste=? ", listaCFOP, filtros.getTipo(), filtros.getAjuste());
    }

    public List<CFOP> selectAll_paginado(Pageable p) {
        return db.query("Select * from CFOP Order By CodigoCFOP " +
                "LIMIT ? OFFSET ? ",
                listaCFOP,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<CFOP> listaCFOP = new RowMapper<CFOP>() {
        @Override
        public CFOP mapRow(ResultSet rs, int i) throws SQLException{
            CFOP cfop = new CFOP();
            cfop.setId_CFOP(rs.getInt("Id_CFOP"));
            cfop.setCodigoCFOP(rs.getString("CodigoCFOP"));
            cfop.setDescricao(rs.getString("Descricao"));
            cfop.setTipo(rs.getString("Tipo"));
            cfop.setAjuste(rs.getBoolean("Ajuste"));
            cfop.setExigeRetorno(rs.getBoolean("ExigeRetorno"));
            cfop.setDiasRetorno(rs.getInt("DiasRetorno"));

            return cfop;
        }
    };
}

package com.br.gcm.dao;

import com.br.gcm.model.Pais;
import com.br.gcm.model.filtros.Filtro_Pais;
import com.br.gcm.util.Rotinas;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 10/12/13
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class PaisDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(Pais pais) {
        db.update("insert into pais (siglapais, descricao, ibge)"
                + "values (?,?,?)",
                pais.getSiglapais().toUpperCase(rotinas.getPT_BR()),
                pais.getDescricao().toUpperCase(rotinas.getPT_BR()),
                pais.getibge());
    }

    public void update(Pais pais) {
        String updateStr = "update pais set " +
                "siglapais=?," +
                "descricao=?," +
                "ibge=?" +
                "where id_pais=?";
        db.update(updateStr,
                pais.getSiglapais().toUpperCase(rotinas.getPT_BR()),
                pais.getDescricao().toUpperCase(rotinas.getPT_BR()),
                pais.getibge(),
                pais.getId_pais());
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM pais", Long.class);
    }

    public Pais selectById(Integer id) {
        return db.queryForObject("select * from pais where id_pais=?", todosPaises, id);
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM Pais Where id_pais=?",id);
    }

    public List<Pais> Pais_lista() {
        return db.query("Select * from Pais order by siglapais", todosPaises);
    }

    public List<Pais> Pais_Paginado(Filtro_Pais f, Pageable p) {
        String sql;
        sql = "Select * from Pais Where 1=1 ";
        if (f.getId_pais() != 0) {sql = sql + " And id_pais = "+f.getId_pais();}
        if (f.getSiglapais() != null && f.getSiglapais().trim() != "") {sql = sql + " And siglapais like '%"+f.getSiglapais().toUpperCase()+"%'";}
        if (f.getDescricao() != null && f.getDescricao().trim() != "") {sql = sql + " And descricao like '%"+f.getDescricao().toUpperCase()+"%'";}
        if (f.getibge() != null && f.getibge().trim() != "") {sql = sql + " And ibge = "+f.getibge().toUpperCase();}
        sql = sql + " order by siglapais LIMIT ? OFFSET ? ";

        return db.query(sql,
                todosPaises,
                p.getPageSize(),
                p.getOffset());
    }

    public Long count_Paginado(Filtro_Pais f) {
        String sql = "SELECT COUNT(*) FROM pais Where 1=1 ";
        if (f.getId_pais() != 0) {sql = sql + " And id_pais = "+f.getId_pais();}
        if (f.getSiglapais() != null && f.getSiglapais().trim() != "") {sql = sql + " And siglapais ilike '%"+f.getSiglapais().toUpperCase()+"%'";}
        if (f.getDescricao() != null && f.getDescricao().trim() != "") {sql = sql + " And descricao ilike '%"+f.getDescricao().toUpperCase()+"%'";}
        if (f.getibge() != null && f.getibge().trim() != "") {sql = sql + " And ibge = "+f.getibge().toUpperCase();}

        return db.queryForObject(sql, Long.class);
    }

    //mappers
    private RowMapper<Pais> todosPaises = new RowMapper<Pais>() {
        @Override
        public Pais mapRow(ResultSet rs, int i) throws SQLException{
            Pais p = new Pais();
            p.setId_pais(rs.getInt("id_pais"));
            p.setSiglapais(rs.getString("siglapais"));
            p.setDescricao(rs.getString("descricao"));
            p.setibge(rs.getString("ibge"));

            return p;
        }
    };

}




package com.br.gcm.dao;

import com.br.gcm.model.Municipio;
import com.br.gcm.util.Rotinas;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 20/12/13
 * Time: 15:56
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MunicipioDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(Municipio municipio) {

        db.update("insert into municipio (id_uf, descricao, ibge)"
                + "values (?,?,?)",
                municipio.getId_uf(),
                municipio.getDescricao().toUpperCase(rotinas.getPT_BR()),
                municipio.getIbge());
    }

    public void update(Municipio municipio) {
        String updateStr = "update municipio set " +
                "id_uf=?," +
                "descricao=?," +
                "ibge=? " +
                "where id_municipio=?";
        db.update(updateStr,
                municipio.getId_uf(),
                municipio.getDescricao().toUpperCase(rotinas.getPT_BR()),
                municipio.getIbge(),
                municipio.getId_municipio());
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM Municipio", Long.class);
    }

    public Long count(Municipio filtros) {
        List arr = new ArrayList<>();
        String sql = "SELECT COUNT(*) FROM Municipio "+
                "inner join uf on municipio.id_uf = uf.id_uf " +
                "inner join pais on uf.id_pais = pais.id_pais " +
                "WHERE 1=1";

        if (filtros.getId_pais() > 0) {
            sql = sql + " And pais.id_pais = ? ";
            arr.add(filtros.getId_pais());
        };
        if (filtros.getId_uf() > 0){
            sql = sql + " And municipio.id_uf = ? ";
            arr.add(filtros.getId_uf());
        };
        if (filtros.getId_municipio() > 0) {
            sql = sql + " And municipio.id_municipio = ? ";
            arr.add(filtros.getId_municipio());
        };
        if (filtros.getDescricao() != null && filtros.getDescricao() != "") {
            sql = sql + " And municipio.descricao like '%?%' ";
            arr.add(filtros.getId_municipio());
        };

        return db.queryForObject(sql, Long.class, arr.toArray());
    }

    public Municipio selectById(Integer id) {
        return db.queryForObject("select Municipio.*, uf.siglauf, uf.id_pais, pais.siglapais from Municipio inner join uf on municipio.id_uf = uf.id_uf inner join pais on uf.id_pais = pais.id_pais where municipio.id_municipio=?", todosMunicipios, id);
    }

    public List<Municipio> selectAll() {
        return db.query("Select Municipio.*, uf.siglauf, uf.id_pais, pais.siglapais from Municipio inner join uf on municipio.id_uf = uf.id_uf inner join pais on uf.id_pais = pais.id_pais order by id_pais, sigla_uf, nome_municipio", todosMunicipios);
    }

    public List<Municipio> selectAll(Municipio filtros, Pageable p) {
        List arr = new ArrayList<>();
        String sql = "Select Municipio.*, uf.siglauf, uf.id_pais, pais.siglapais " +
                "from Municipio " +
                "inner join uf on municipio.id_uf = uf.id_uf " +
                "inner join pais on uf.id_pais = pais.id_pais " +
                " Where 1=1 ";

        if (filtros.getId_pais() > 0) {
            sql = sql + " And pais.id_pais = ? ";
            arr.add(filtros.getId_pais());
        };
        if (filtros.getId_uf() > 0){
            sql = sql + " And municipio.id_uf = ? ";
            arr.add(filtros.getId_uf());
        };
        if (filtros.getId_municipio() > 0) {
            sql = sql + " And municipio.id_municipio = ? ";
            arr.add(filtros.getId_municipio());
        };
        if (filtros.getDescricao() != null && filtros.getDescricao() != "") {
            sql = sql + " And municipio.descricao like '%?%' ";
            arr.add(filtros.getId_municipio());
        };

        sql = sql + "order by pais.id_pais, uf.siglauf, municipio.descricao LIMIT ? OFFSET ?";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.query(sql,
                todosMunicipios,
                arr.toArray());
    }

    public List<Municipio> selectAll(Pageable p) {
        return db.query("Select Municipio.*, uf.siglauf, uf.id_pais, pais.siglapais " +
                "from Municipio " +
                "inner join uf on municipio.id_uf = uf.id_uf " +
                "inner join pais on uf.id_pais = pais.id_pais " +
                "order by pais.id_pais, uf.siglauf, municipio.descricao " +
                "LIMIT ? OFFSET ?",
                todosMunicipios,
                p.getPageSize(),
                p.getOffset());
    }

    public List<Municipio> selectByUf(Integer id_uf) {
        return db.query ("Select Municipio.*, uf.siglauf, uf.id_pais, pais.siglapais from Municipio inner join uf on municipio.id_uf = uf.id_uf inner join pais on uf.id_pais = pais.id_pais where municipio.id_uf=? order by id_pais, siglauf, municipio.descricao", todosMunicipios, id_uf);
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM municipio Where id_municipio=?",id);
    }

    //mappers
    private RowMapper<Municipio> todosMunicipios = new RowMapper<Municipio>() {
        @Override
        public Municipio mapRow(ResultSet rs, int i) throws SQLException{
            Municipio mun = new Municipio();
            mun.setId_municipio(rs.getInt("id_municipio"));
            mun.setId_uf(rs.getInt("id_uf"));
            mun.setDescricao(rs.getString("descricao"));
            mun.setIbge(rs.getString("ibge"));

            mun.setId_pais(rs.getInt("id_pais"));
            mun.setSiglapais(rs.getString("siglapais"));
            mun.setSiglauf(rs.getString("siglauf"));

            return mun;
        }
    };
}

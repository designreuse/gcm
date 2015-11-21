package com.br.gcm.dao;

import com.br.gcm.model.CentroCusto;
import com.br.gcm.util.Rotinas;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 02/05/15
 * Time: 09:06
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class CentroCustoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(CentroCusto centroCusto) {
        db.update("insert into CentroCusto (id_Empresa, Sigla, Descricao) values(?,?,?)",
                centroCusto.getId_Empresa(),
                centroCusto.getSigla().toUpperCase(rotinas.getPT_BR()),
                centroCusto.getDescricao());
    }

    public void update(CentroCusto centroCusto) {
        db.update("update CentroCusto set id_Empresa=?, Sigla=?, Descricao=? Where id_CentroCusto=? ",
                centroCusto.getId_Empresa(),
                centroCusto.getSigla().toUpperCase(rotinas.getPT_BR()),
                centroCusto.getDescricao(),
                centroCusto.getId_CentroCusto());
    }

    public void deleteById(int id) {
        db.update("Delete from CentroCusto Where id_CentroCusto=? ", id);
    }

    public Long count(int id_Empresa) {
        return db.queryForObject("SELECT COUNT(*) FROM CentroCusto Where id_Empresa=?", Long.class, id_Empresa);
    }

    public CentroCusto selectById(Integer id) {
        return db.queryForObject("Select * from CentroCusto Where id_CentroCusto=?", listaCentroCusto, id);
    }

    public CentroCusto selectbySigla(int id_Empresa, String sigla) {
        return db.queryForObject("Select * from CentroCusto Where id_Empresa=? and sigla=? ", listaCentroCusto, id_Empresa, sigla.toUpperCase());
    }

    public List<CentroCusto> selectAll(int id_Empresa) {
        return db.query("Select * from CentroCusto Where id_Empresa=?", listaCentroCusto, id_Empresa);
    }

    public List<CentroCusto> selectAll(CentroCusto filtros) {
        List arr = new ArrayList<>();
        String sql = "Select * from CentroCusto Where 1=1 ";

        if (filtros.getId_Empresa() > 0){
            sql = sql + "And id_Empresa = ?";
            arr.add(filtros.getId_Empresa());
        }

        if (!filtros.getSigla().equals("") && filtros.getSigla() != null){
            sql = sql + " And Sigla = ?";
            arr.add(filtros.getSigla());
        }

        if (!filtros.getDescricao().equals("") && filtros.getDescricao() != null){
            sql = sql + " And Descricao like ?";
            arr.add("%"+filtros.getDescricao()+"%");
        }

        return db.query(sql, listaCentroCusto, arr.toArray());
    }



    public List<CentroCusto> selectAll_paginado(int id_Empresa, Pageable p) {
        return db.query("Select * from CentroCusto Where id_Empresa=? " +
                "LIMIT ? OFFSET ? ",
                listaCentroCusto,
                id_Empresa,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<CentroCusto> listaCentroCusto = new RowMapper<CentroCusto>() {
        @Override
        public CentroCusto mapRow(ResultSet rs, int i) throws SQLException{
            CentroCusto centroCusto = new CentroCusto();
            centroCusto.setId_CentroCusto(rs.getInt("Id_CentroCusto"));
            centroCusto.setId_Empresa(rs.getInt("id_Empresa"));
            centroCusto.setDescricao(rs.getString("Descricao"));
            centroCusto.setSigla(rs.getString("Sigla"));

            return centroCusto;
        }
    };
}

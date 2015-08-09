package com.br.gcm.dao;

import com.br.gcm.model.Unidade;
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
 * Date: 21/04/15
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class UnidadeDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(Unidade unidade) {
        db.update("insert into Unidade (sigla, descricao) values(?,?)",
                unidade.getSigla().toUpperCase(rotinas.getPT_BR()),
                unidade.getDescricao().toUpperCase(rotinas.getPT_BR()));
    }

    public void update(Unidade unidade){
        db.update("update Unidade set sigla=?, descricao=? where id_Unidade=?",
                unidade.getSigla().toUpperCase(rotinas.getPT_BR()),
                unidade.getDescricao().toUpperCase(rotinas.getPT_BR()),
                unidade.getId_Unidade());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM Unidade Where id_Unidade=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM Unidade", Long.class);
    }

    public Long count(Unidade filtros) {
        List arr = new ArrayList();
        String sql = "SELECT COUNT(*) FROM Unidade Where 1=1 ";

        if (filtros.getSigla() != null && filtros.getSigla() != ""){
            sql = sql + " And sigla = ? ";
            arr.add(filtros.getSigla());
        }
        if (filtros.getDescricao() != null && filtros.getDescricao() != ""){
            sql = sql + " And descricao = ? ";
            arr.add(filtros.getDescricao());
        }

        return db.queryForObject(sql, Long.class, arr.toArray());
    }

    public Unidade selectById(Integer id) {
        return db.queryForObject("Select * from Unidade Where id_Unidade=?", listaUnidade, id);
    }

    public List<Unidade> selectAll() {
        return db.query("Select * from Unidade", listaUnidade);
    }

    public List<Unidade> selectAll_paginado(Pageable p) {
        return db.query("Select * from  Unidade Order By Descricao " +
                "LIMIT ? OFFSET ? ",
                listaUnidade,
                p.getPageSize(),
                p.getOffset());
    }

    public List<Unidade> selectAll_paginado(Unidade filtros, Pageable p) {
        List arr = new ArrayList();
        String sql = "Select * from  Unidade Where 1=1 ";

        if (filtros.getSigla() != null && filtros.getSigla() != ""){
            sql = sql + " And sigla = ? ";
            arr.add(filtros.getSigla().toUpperCase());
        }
        if (filtros.getDescricao() != null && filtros.getDescricao() != ""){
            sql = sql + " And descricao like ? ";
            arr.add("%"+filtros.getDescricao().toUpperCase()+"%");
        }

        sql = sql + " Order By Descricao LIMIT ? OFFSET ? ";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.query(sql,
                listaUnidade,
                arr.toArray());
    }

    //mappers
    private RowMapper<Unidade> listaUnidade = new RowMapper<Unidade>() {
        @Override
        public Unidade mapRow(ResultSet rs, int i) throws SQLException{
            Unidade unidade = new Unidade();
            unidade.setId_Unidade(rs.getInt("Id_Unidade"));
            unidade.setSigla(rs.getString("Sigla"));
            unidade.setDescricao(rs.getString("Descricao"));

            return unidade;
        }
    };
}

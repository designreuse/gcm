package com.br.gcm.dao;

import com.br.gcm.model.GrupoProduto;
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
 * Date: 18/04/15
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class GrupoProdutoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(GrupoProduto grupoProduto) {
        db.update("insert into GrupoProduto (descricao) values(?)",
                grupoProduto.getDescricao());
    }

    public void update(GrupoProduto grupoProduto){
        db.update("update GrupoProduto set descricao=? where id_GrupoProduto = ?",
                grupoProduto.getDescricao(),
                grupoProduto.getId_GrupoProduto());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM GrupoProduto Where id_GrupoProduto=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM GrupoProduto", Long.class);
    }

    public GrupoProduto selectById(Integer id) {
        return db.queryForObject("Select * from GrupoProduto Where id_GrupoProduto=?", listaGrupoProduto, id);
    }

    public List<GrupoProduto> selectAll() {
        return db.query("Select * from GrupoProduto", listaGrupoProduto);
    }

    public List<GrupoProduto> selectAll_paginado(Pageable p) {
        return db.query("Select * from  GrupoProduto Order By Descricao " +
                "LIMIT ? OFFSET ? ",
                listaGrupoProduto,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<GrupoProduto> listaGrupoProduto = new RowMapper<GrupoProduto>() {
        @Override
        public GrupoProduto mapRow(ResultSet rs, int i) throws SQLException{
            GrupoProduto grupoProduto = new GrupoProduto();
            grupoProduto.setId_GrupoProduto(rs.getInt("Id_GrupoProduto"));
            grupoProduto.setDescricao(rs.getString("Descricao"));

            return grupoProduto;
        }
    };
}

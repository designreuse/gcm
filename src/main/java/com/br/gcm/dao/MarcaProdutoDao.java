package com.br.gcm.dao;

import com.br.gcm.model.MarcaProduto;
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
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class MarcaProdutoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(MarcaProduto marcaProduto) {
        db.update("insert into MarcaProduto (descricao) values(?)",
                marcaProduto.getDescricao());
    }

    public void update(MarcaProduto marcaProduto){
        db.update("update MarcaProduto set descricao=? where id_MarcaProduto = ?",
                marcaProduto.getDescricao(),
                marcaProduto.getId_MarcaProduto());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM MarcaProduto Where id_MarcaProduto=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM MarcaProduto", Long.class);
    }

    public MarcaProduto selectById(Integer id) {
        return db.queryForObject("Select * from MarcaProduto Where id_MarcaProduto=?", listaMarcaProduto, id);
    }

    public List<MarcaProduto> selectAll() {
        return db.query("Select * from MarcaProduto", listaMarcaProduto);
    }

    public List<MarcaProduto> selectAll_paginado(Pageable p) {
        return db.query("Select * from  MarcaProduto Order By Descricao " +
                "LIMIT ? OFFSET ? ",
                listaMarcaProduto,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<MarcaProduto> listaMarcaProduto = new RowMapper<MarcaProduto>() {
        @Override
        public MarcaProduto mapRow(ResultSet rs, int i) throws SQLException{
            MarcaProduto marcaProduto = new MarcaProduto();
            marcaProduto.setId_MarcaProduto(rs.getInt("Id_MarcaProduto"));
            marcaProduto.setDescricao(rs.getString("Descricao"));

            return marcaProduto;
        }
    };
}

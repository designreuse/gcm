package com.br.gcm.dao;

import com.br.gcm.model.ProdutoLote;
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
 * Date: 26/04/15
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class ProdutoLoteDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;
    @Inject private ProdutoDao produtoDao;

    public void insert(ProdutoLote produtoLote) {
        db.update("insert into ProdutoLote (" +
                " id_Produto," +
                " NumeroLote," +
                " DataFabricacao," +
                " DataValidade) " +
                " Values(?,?,?,?)",
                produtoLote.getId_Produto(),
                produtoLote.getNumeroLote(),
                produtoLote.getDataFabricacao(),
                produtoLote.getDataValidade());
    }

    public void update(ProdutoLote produtoLote) {
        db.update("update ProdutoLote set " +
                " id_Produto=?," +
                " NumeroLote=?," +
                " DataFabricacao=?," +
                " DataValidade=? " +
                " Where id_ProdutoLote=? ",
                produtoLote.getId_Produto(),
                produtoLote.getNumeroLote(),
                produtoLote.getDataFabricacao(),
                produtoLote.getDataValidade(),
                produtoLote.getId_ProdutoLote());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM ProdutoLote Where id_ProdutoLote=?",id);
    }

    public Long count(Integer id_Produto) {
        return db.queryForObject("SELECT COUNT(*) FROM ProdutoLote Where id_Produto = ?", Long.class, id_Produto);
    }

    public ProdutoLote selectById(Integer id) {
        return db.queryForObject("Select * from ProdutoLote Where id_ProdutoLote=?", listaProdutoLote, id);
    }

    public ProdutoLote selectByNumeroLote(int id_Produto, String NumeroLote) {
        return db.queryForObject("Select * from ProdutoLote Where id_Produto=? and NumeroLote=?" , listaProdutoLote, id_Produto, NumeroLote);
    }

    public List<ProdutoLote> selectAll(Integer id_Produto) {
        return db.query("Select * from ProdutoLote Where id_Produto = ? Order by NumeroLote ", listaProdutoLote, id_Produto);
    }

    public List<ProdutoLote> selectAll_paginado(Integer id_Produto, Pageable p) {
        return db.query("Select * from ProdutoLote Where id_Produto = ? Order By NumeroLote " +
                "LIMIT ? OFFSET ? ",
                listaProdutoLote,
                id_Produto,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<ProdutoLote> listaProdutoLote = new RowMapper<ProdutoLote>() {
        @Override
        public ProdutoLote mapRow(ResultSet rs, int i) throws SQLException{
            ProdutoLote produtoLote = new ProdutoLote();
            produtoLote.setId_ProdutoLote(rs.getInt("Id_ProdutoLote"));
            produtoLote.setId_Produto(rs.getInt("Id_Produto"));
            produtoLote.setNumeroLote(rs.getString("NumeroLote"));
            produtoLote.setDataFabricacao(rs.getDate("DataFabricacao"));
            produtoLote.setDataValidade(rs.getDate("DataValidade"));

            return produtoLote;
        }
    };
}

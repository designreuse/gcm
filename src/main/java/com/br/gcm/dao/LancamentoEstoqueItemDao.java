package com.br.gcm.dao;

import com.br.gcm.model.LancamentoEstoqueItem;
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
 * Date: 11/05/15
 * Time: 15:09
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class LancamentoEstoqueItemDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;
    @Inject private ProdutoLoteDao produtoLoteDao;
    @Inject private ProdutoDao produtoDao;
    @Inject private ProdutoUnidadeDao produtoUnidadeDao;
    @Inject private UnidadeDao unidadeDao;

    public void insert(LancamentoEstoqueItem lancamentoEstoqueItem) {
        db.update("Insert Into LancamentoEstoqueItem (id_LancamentoEstoque,id_Deposito,id_ProdutoLote,id_ProdutoUnidade,Quantidade) Values(?,?,?,?,?) ",
                lancamentoEstoqueItem.getId_LancamentoEstoque(),
                lancamentoEstoqueItem.getId_Deposito(),
                lancamentoEstoqueItem.getId_ProdutoLote(),
                lancamentoEstoqueItem.getId_ProdutoUnidade(),
                lancamentoEstoqueItem.getQuantidade());
    }

    public void update(LancamentoEstoqueItem lancamentoEstoqueItem) {
        db.update("Update LancamentoEstoqueItem set id_LancamentoEstoque=?,id_Deposito=?,id_ProdutoLote=?,id_ProdutoUnidade=?,Quantidade=? Where id_LancamentoEstoqueItem=? ",
                lancamentoEstoqueItem.getId_LancamentoEstoque(),
                lancamentoEstoqueItem.getId_Deposito(),
                lancamentoEstoqueItem.getId_ProdutoLote(),
                lancamentoEstoqueItem.getId_ProdutoUnidade(),
                lancamentoEstoqueItem.getQuantidade(),
                lancamentoEstoqueItem.getId_LancamentoEstoqueItem());
    }

    public void deleteById(int id) {
        db.update("Delete from LancamentoEstoqueItem Where id_LancamentoEstoqueItem=? ", id);
    }

    public void deleteByLancamentoEstoque(int id_LancamentoEstoque) {
        db.update("Delete from LancamentoEstoqueItem Where id_LancamentoEstoque=? ", id_LancamentoEstoque);
    }

    public Long count(int id_LancamentoEstoque) {
        return db.queryForObject("SELECT COUNT(*) FROM LancamentoEstoqueItem " +
                "Where id_LancamentoEstoque=? ", Long.class, id_LancamentoEstoque);
    }

    public LancamentoEstoqueItem selectById(Integer id) {
        return db.queryForObject("Select * from LancamentoEstoqueItem Where id_LancamentoEstoqueItem=?", listaLancamentoEstoqueItem, id);
    }

    public List<LancamentoEstoqueItem> selectAll(int id_LancamentoEstoque) {
        return db.query("Select * from LancamentoEstoqueItem " +
                " Inner Join ProdutoLote On LancamentoEstoqueItem.id_ProdutoLote = ProdutoLote.id_ProdutoLote " +
                "Where id_LancamentoEstoque=? Order By ProdutoLote.id_Produto, ProdutoLote.NumeroLote, LancamentoEstoqueItem.id_Deposito " ,
                listaLancamentoEstoqueItem, id_LancamentoEstoque);
    }

    public List<LancamentoEstoqueItem> selectAll_paginado(int id_LancamentoEstoque, Pageable p) {
        return db.query("Select * from LancamentoEstoqueItem " +
                "Where id_LancamentoEstoque=? " +
                "LIMIT ? OFFSET ? ",
                listaLancamentoEstoqueItem,
                id_LancamentoEstoque,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<LancamentoEstoqueItem> listaLancamentoEstoqueItem = new RowMapper<LancamentoEstoqueItem>() {
        @Override
        public LancamentoEstoqueItem mapRow(ResultSet rs, int i) throws SQLException{
            LancamentoEstoqueItem lancamentoEstoqueItem = new LancamentoEstoqueItem();
            lancamentoEstoqueItem.setId_LancamentoEstoqueItem(rs.getInt("Id_LancamentoEstoqueItem"));
            lancamentoEstoqueItem.setId_LancamentoEstoque(rs.getInt("Id_LancamentoEstoque"));
            lancamentoEstoqueItem.setId_Deposito(rs.getInt("Id_Deposito"));
            lancamentoEstoqueItem.setId_ProdutoLote(rs.getInt("id_ProdutoLote"));
            lancamentoEstoqueItem.setId_ProdutoUnidade(rs.getInt("id_ProdutoUnidade"));
            lancamentoEstoqueItem.setQuantidade(rs.getFloat("Quantidade"));

            lancamentoEstoqueItem.setProdutoLote(produtoLoteDao.selectById(rs.getInt("id_ProdutoLote")));
            lancamentoEstoqueItem.setProduto(produtoDao.selectByIdRes(lancamentoEstoqueItem.getProdutoLote().getId_Produto()));
            lancamentoEstoqueItem.setProdutoUnidade(produtoUnidadeDao.selectByIdRes(rs.getInt("id_ProdutoUnidade")));
            lancamentoEstoqueItem.setUnidade(unidadeDao.selectById(lancamentoEstoqueItem.getProdutoUnidade().getId_Unidade()));

            return lancamentoEstoqueItem;
        }
    };
}

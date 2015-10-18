package com.br.gcm.dao;

import com.br.gcm.model.ProdutoUnidade;
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
 * Date: 25/04/15
 * Time: 12:05
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class ProdutoUnidadeDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;
    @Inject private UnidadeDao unidadeDao;

    public void insert(ProdutoUnidade produtoUnidade) {
        db.update("insert into ProdutoUnidade (" +
                " id_Produto," +
                " id_Unidade," +
                " CodigoBarras," +
                " FatorConversao, " +
                " PesoBruto, " +
                " PesoLiquido, " +
                " UnidadePrincipal)" +
                " Values(?,?,?,?,?,?,?)",
                produtoUnidade.getId_Produto(),
                produtoUnidade.getId_Unidade(),
                produtoUnidade.getCodigoBarras(),
                produtoUnidade.getFatorConversao(),
                produtoUnidade.getPesoBruto(),
                produtoUnidade.getPesoLiquido(),
                produtoUnidade.getUnidadePrincipal());
    }

    public void update(ProdutoUnidade produtoUnidade) {
        db.update("update ProdutoUnidade set " +
                " id_Produto=?," +
                " id_Unidade=?," +
                " CodigoBarras=?," +
                " FatorConversao=?, " +
                " PesoBruto=?, " +
                " PesoLiquido=? " +
                " Where id_ProdutoUnidade=? ",
                produtoUnidade.getId_Produto(),
                produtoUnidade.getId_Unidade(),
                produtoUnidade.getCodigoBarras(),
                produtoUnidade.getFatorConversao(),
                produtoUnidade.getPesoBruto(),
                produtoUnidade.getPesoLiquido(),
                produtoUnidade.getId_ProdutoUnidade());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM ProdutoUnidade Where id_ProdutoUnidade=?",id);
    }

    public Long count(Integer id_Produto) {
        return db.queryForObject("SELECT COUNT(*) FROM ProdutoUnidade Where id_Produto = ?", Long.class, id_Produto);
    }

    public ProdutoUnidade selectById(Integer id) {
        return db.queryForObject("Select * from ProdutoUnidade Where id_ProdutoUnidade=?", listaProdutoUnidade, id);
    }

    public List<ProdutoUnidade> selectAll(Integer id_Produto) {
        return db.query("Select * from ProdutoUnidade Where id_Produto = ? Order by UnidadePrincipal desc ", listaProdutoUnidade, id_Produto);
    }

    public List<ProdutoUnidade> selectAll_paginado(Integer id_Produto, Pageable p) {
        return db.query("Select * from ProdutoUnidade Where id_Produto = ? Order By UnidadePrincipal desc " +
                "LIMIT ? OFFSET ? ",
                listaProdutoUnidade,
                id_Produto,
                p.getPageSize(),
                p.getOffset());
    }

    public ProdutoUnidade selectByIdRes(Integer id) {
        return db.queryForObject("Select * from ProdutoUnidade Where id_ProdutoUnidade=?", listaProdutoUnidadeRes, id);
    }

    //mappers
    private RowMapper<ProdutoUnidade> listaProdutoUnidade = new RowMapper<ProdutoUnidade>() {
        @Override
        public ProdutoUnidade mapRow(ResultSet rs, int i) throws SQLException{
            ProdutoUnidade produtoUnidade = new ProdutoUnidade();
            produtoUnidade.setId_ProdutoUnidade(rs.getInt("Id_ProdutoUnidade"));
            produtoUnidade.setId_Produto(rs.getInt("Id_Produto"));
            produtoUnidade.setId_Unidade(rs.getInt("Id_Unidade"));
            produtoUnidade.setCodigoBarras(rs.getString("CodigoBarras"));
            produtoUnidade.setFatorConversao(rs.getLong("FatorConversao"));
            produtoUnidade.setPesoBruto(rs.getLong("PesoBruto"));
            produtoUnidade.setPesoLiquido(rs.getLong("PesoLiquido"));
            produtoUnidade.setUnidadePrincipal(rs.getBoolean("UnidadePrincipal"));
            produtoUnidade.setUnidade(unidadeDao.selectById(rs.getInt("Id_Unidade")));

            return produtoUnidade;
        }
    };

    //mappers
    private RowMapper<ProdutoUnidade> listaProdutoUnidadeRes = new RowMapper<ProdutoUnidade>() {
        @Override
        public ProdutoUnidade mapRow(ResultSet rs, int i) throws SQLException{
            ProdutoUnidade produtoUnidade = new ProdutoUnidade();
            produtoUnidade.setId_ProdutoUnidade(rs.getInt("Id_ProdutoUnidade"));
            produtoUnidade.setId_Produto(rs.getInt("Id_Produto"));
            produtoUnidade.setId_Unidade(rs.getInt("Id_Unidade"));
            produtoUnidade.setCodigoBarras(rs.getString("CodigoBarras"));
            produtoUnidade.setFatorConversao(rs.getLong("FatorConversao"));
            produtoUnidade.setPesoBruto(rs.getLong("PesoBruto"));
            produtoUnidade.setPesoLiquido(rs.getLong("PesoLiquido"));
            produtoUnidade.setUnidadePrincipal(rs.getBoolean("UnidadePrincipal"));

            return produtoUnidade;
        }
    };
}

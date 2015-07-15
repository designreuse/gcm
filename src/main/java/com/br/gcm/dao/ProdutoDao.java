package com.br.gcm.dao;

import com.br.gcm.model.Produto;
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
 * Date: 22/04/15
 * Time: 11:00
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class ProdutoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;
    @Inject private SubGrupoProdutoDao subGrupoProdutoDao;
    @Inject private GrupoProdutoDao grupoProdutoDao;
    @Inject private NCMDao ncmDao;
    @Inject private SituacaoTributariaADao situacaoTributariaADao;
    @Inject private SituacaoTributariaBDao situacaoTributariaBDao;
    @Inject private MarcaProdutoDao marcaProdutoDao;
    @Inject private UnidadeDao unidadeDao;

    public void insert(Produto produto) {
        db.update("insert into Produto (" +
                " Referencia," +
                " Descricao," +
                " id_SubGrupoProduto," +
                " id_MarcaProduto," +
                " id_NCM," +
                " id_STA," +
                " id_STB," +
                " id_UnidadePadrao," +
                " PesoBruto," +
                " PesoLiquido," +
                " NumeroAnvisa," +
                " ValidadeAnvisa," +
                " ControlaLote," +
                " AtivoCompra, " +
                " AtivoVenda) " +
                " Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                produto.getReferencia(),
                produto.getDescricao(),
                produto.getId_SubGrupoProduto(),
                produto.getId_MarcaProduto(),
                produto.getId_NCM(),
                produto.getId_STA(),
                produto.getId_STB(),
                produto.getId_UnidadePadrao(),
                produto.getPesoBruto(),
                produto.getPesoLiquido(),
                produto.getNumeroAnvisa(),
                produto.getValidadeAnvisa(),
                produto.isControlaLote(),
                produto.isAtivoCompra(),
                produto.isAtivoVenda());
    }

    public void update(Produto produto) {
        db.update("Update Produto set " +
                " Referencia=?," +
                " Descricao=?," +
                " id_SubGrupoProduto=?," +
                " id_MarcaProduto=?," +
                " id_NCM=?," +
                " id_STA=?," +
                " id_STB=?," +
                " PesoBruto=?," +
                " PesoLiquido=?," +
                " NumeroAnvisa=?," +
                " ValidadeAnvisa=?," +
                " ControlaLote=?," +
                " AtivoCompra=?, " +
                " AtivoVenda=? " +
                " Where id_Produto=? ",
                produto.getReferencia(),
                produto.getDescricao(),
                produto.getId_SubGrupoProduto(),
                produto.getId_MarcaProduto(),
                produto.getId_NCM(),
                produto.getId_STA(),
                produto.getId_STB(),
                produto.getPesoBruto(),
                produto.getPesoLiquido(),
                produto.getNumeroAnvisa(),
                produto.getValidadeAnvisa(),
                produto.isControlaLote(),
                produto.isAtivoCompra(),
                produto.isAtivoVenda(),
                produto.getId_Produto());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM Produto Where id_Produto=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM Produto", Long.class);
    }

    public Produto selectById(Integer id) {
        return db.queryForObject("Select * from Produto Where id_Produto=?", listaProduto, id);
    }

    public List<Produto> selectAll() {
        return db.query("Select * from Produto", listaProduto);
    }

    public List<Produto> selectAll_paginado(Pageable p) {
        return db.query("Select * from  Produto Order By Descricao " +
                "LIMIT ? OFFSET ? ",
                listaProduto,
                p.getPageSize(),
                p.getOffset());
    }

    public List<Produto> selectAllRes() {
        return db.query("Select * from Produto", listaProdutoRes);
    }

    public List<Produto> selectAllResFiltros(String filtros) {
        String sql = "Select * from Produto Where 1=1 ";
        if (filtros.length() > 0){
            sql = sql + " And Referencia='"+filtros+"' or Descricao like '%"+filtros+"%' or Id_Produto = "+filtros;
        }

        sql=sql + " Order by Descricao ";

        return db.query(sql, listaProdutoRes);
    }

    public Produto selectByIdRes(Integer id) {
        return db.queryForObject("Select * from Produto Where id_Produto=?", listaProdutoRes, id);
    }

    //mappers
    private RowMapper<Produto> listaProduto = new RowMapper<Produto>() {
        @Override
        public Produto mapRow(ResultSet rs, int i) throws SQLException{
            Produto produto = new Produto();
            produto.setId_Produto(rs.getInt("Id_Produto"));
            produto.setReferencia(rs.getString("Referencia"));
            produto.setDescricao(rs.getString("Descricao"));
            produto.setId_SubGrupoProduto(rs.getInt("id_SubGrupoProduto"));
            produto.setId_MarcaProduto(rs.getInt("id_MarcaProduto"));
            produto.setId_NCM(rs.getInt("id_NCM"));
            produto.setId_STA(rs.getInt("id_STA"));
            produto.setId_STB(rs.getInt("id_STB"));
            produto.setId_UnidadePadrao(rs.getInt("id_UnidadePadrao"));
            produto.setPesoBruto(rs.getDouble("PesoBruto"));
            produto.setPesoLiquido(rs.getDouble("PesoLiquido"));
            produto.setNumeroAnvisa(rs.getString("NumeroAnvisa"));
            produto.setValidadeAnvisa(rs.getDate("ValidadeAnvisa"));
            produto.setControlaLote(rs.getBoolean("ControlaLote"));
            produto.setAtivoCompra(rs.getBoolean("AtivoCompra"));
            produto.setAtivoVenda(rs.getBoolean("AtivoVenda"));

            produto.setSubGrupoProduto(subGrupoProdutoDao.selectById(rs.getInt("id_SubGrupoProduto")));
            produto.setGrupoProduto(grupoProdutoDao.selectById(produto.getSubGrupoProduto().getId_GrupoProduto()));
            produto.setNcm(ncmDao.selectById(rs.getInt("id_NCM")));
            produto.setSituacaoTributariaA(situacaoTributariaADao.selectById(rs.getInt("id_STA")));
            produto.setSituacaoTributariaB(situacaoTributariaBDao.selectById(rs.getInt("id_STB")));
            produto.setMarcaProduto(marcaProdutoDao.selectById(rs.getInt("id_MarcaProduto")));
            produto.setUnidadePadrao(unidadeDao.selectById(rs.getInt("id_UnidadePadrao")));

            return produto;
        }
    };

    //mappers
    private RowMapper<Produto> listaProdutoRes = new RowMapper<Produto>() {
        @Override
        public Produto mapRow(ResultSet rs, int i) throws SQLException{
            Produto produto = new Produto();
            produto.setId_Produto(rs.getInt("Id_Produto"));
            produto.setReferencia(rs.getString("Referencia"));
            produto.setDescricao(rs.getString("Descricao"));
            produto.setId_SubGrupoProduto(rs.getInt("id_SubGrupoProduto"));
            produto.setId_MarcaProduto(rs.getInt("id_MarcaProduto"));
            produto.setId_NCM(rs.getInt("id_NCM"));
            produto.setId_STA(rs.getInt("id_STA"));
            produto.setId_STB(rs.getInt("id_STB"));
            produto.setId_UnidadePadrao(rs.getInt("id_UnidadePadrao"));
            produto.setPesoBruto(rs.getDouble("PesoBruto"));
            produto.setPesoLiquido(rs.getDouble("PesoLiquido"));
            produto.setNumeroAnvisa(rs.getString("NumeroAnvisa"));
            produto.setValidadeAnvisa(rs.getDate("ValidadeAnvisa"));
            produto.setControlaLote(rs.getBoolean("ControlaLote"));
            produto.setAtivoCompra(rs.getBoolean("AtivoCompra"));
            produto.setAtivoVenda(rs.getBoolean("AtivoVenda"));

            return produto;
        }
    };
}

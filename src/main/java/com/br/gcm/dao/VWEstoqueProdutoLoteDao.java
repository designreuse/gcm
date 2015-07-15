package com.br.gcm.dao;

import com.br.gcm.model.VWEstoqueProdutoLote;
import com.br.gcm.model.filtros.Filtro_Produto;
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
 * Date: 26/05/15
 * Time: 16:21
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class VWEstoqueProdutoLoteDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;
    @Inject private ProdutoLoteDao produtoLoteDao;
    @Inject private ProdutoDao produtoDao;
    @Inject private CFOPDao cfopDao;
    @Inject private DepositoDao depositoDao;
    @Inject private EmpresaDao empresaDao;

    public List<VWEstoqueProdutoLote> AllByFiltros(Filtro_Produto filtros) {
        String sql = "Select " +
                " VWEstoqueProdutoLote.* " +
                " from VWEstoqueProdutoLote " +
                " Inner Join CFOP On Cfop.id_Cfop = VWEstoqueProdutoLote.id_CFOP " +
                " Inner Join ProdutoLote on ProdutoLote.id_ProdutoLote = VWEstoqueProdutoLote.id_ProdutoLote " +
                " Inner Join Produto on ProdutoLote.id_Produto = Produto.id_Produto " +
                " Where 1=1 ";

        if (filtros.isEstoqueZerado() == false){
            sql = sql + " And Disponivel > 0 ";
        }
        if (filtros.getId_Empresa() > 0){
            sql = sql + " And VWEstoqueProdutoLote.id_Empresa="+filtros.getId_Empresa();
        }
        if (filtros.getId_Deposito() > 0){
            sql = sql + " And VWEstoqueProdutoLote.id_Deposito="+filtros.getId_Deposito();
        }
        if (filtros.getId_ProdutoLote() > 0){
            sql = sql + " And VWEstoqueProdutoLote.Id_ProdutoLote="+filtros.getId_ProdutoLote();
        }
        if (filtros.getId_Produto() > 0){
            sql = sql + " And Produto.id_Produto="+filtros.getId_Produto();
        }
        if (filtros.getId_CFOP() > 0){
            sql = sql + " And coalesce((Select count(*) from RelacaoCFOP Where RelacaoCFOP.id_CFOP = VWEstoqueProdutoLote.id_CFOP and RelacaoCFOP.id_cfoprelacao = "+filtros.getId_CFOP()+"),0) > 0 ";
        }
        if (filtros.getDescricao().length() > 0){
            sql = sql + " And Produto.Descricao like '%"+filtros.getDescricao()+"%'";
        }

        if (filtros.getNumeroLote().length() > 0){
            sql = sql + " And ProdutoLote.NumeroLote = "+filtros.getNumeroLote();
        }

        sql=sql + " order by CFOP.Descricao, Produto.Descricao, ProdutoLote.NumeroLote   ";

        return db.query(sql, listaEstoqueProduto);
    }


    public List<VWEstoqueProdutoLote> AllbyIdProduto(int id) {
        String sql = "Select " +
                "VWEstoqueProdutoLote.* " +
                "from VWEstoqueProdutoLote " +
                "Inner Join CFOP On Cfop.id_Cfop = VWEstoqueProdutoLote.id_CFOP " +
                "Inner Join ProdutoLote on ProdutoLote.id_ProdutoLote = VWEstoqueProdutoLote.id_ProdutoLote " +
                "Inner Join Produto on ProdutoLote.id_Produto = Produto.id_Produto " +
                "Where Disponivel > 0 And Produto.id_Produto=? " +
                "order by CFOP.Descricao, Produto.Descricao, ProdutoLote.NumeroLote ";

        return db.query(sql, listaEstoqueProduto, id);
    }

    //mappers
    private RowMapper<VWEstoqueProdutoLote> listaEstoqueProduto = new RowMapper<VWEstoqueProdutoLote>() {
        @Override
        public VWEstoqueProdutoLote mapRow(ResultSet rs, int i) throws SQLException{
            VWEstoqueProdutoLote vwEstoqueProdutoLote = new VWEstoqueProdutoLote();

            vwEstoqueProdutoLote.setId_Empresa(rs.getInt("id_Empresa"));
            vwEstoqueProdutoLote.setId_Deposito(rs.getInt("id_Deposito"));
            vwEstoqueProdutoLote.setId_EstoqueProduto(rs.getInt("id_EstoqueProduto"));
            vwEstoqueProdutoLote.setId_CFOP(rs.getInt("id_CFOP"));
            vwEstoqueProdutoLote.setId_ProdutoLote(rs.getInt("id_ProdutoLote"));
            vwEstoqueProdutoLote.setQuantidadeEstoque(rs.getDouble("QuantidadeEstoque"));
            vwEstoqueProdutoLote.setDisponivel(rs.getDouble("Disponivel"));
            vwEstoqueProdutoLote.setReservas(rs.getDouble("Reservas"));

            vwEstoqueProdutoLote.setEmpresa(empresaDao.selectById(rs.getInt("id_Empresa")));
            vwEstoqueProdutoLote.setDeposito(depositoDao.selectById(rs.getInt("id_Deposito")));
            vwEstoqueProdutoLote.setCfop(cfopDao.selectById(rs.getInt("id_CFOP")));
            vwEstoqueProdutoLote.setProdutoLote(produtoLoteDao.selectById(rs.getInt("id_ProdutoLote")));
            vwEstoqueProdutoLote.setProduto(produtoDao.selectByIdRes(vwEstoqueProdutoLote.getProdutoLote().getId_Produto()));

            return vwEstoqueProdutoLote;
        }
    };
}

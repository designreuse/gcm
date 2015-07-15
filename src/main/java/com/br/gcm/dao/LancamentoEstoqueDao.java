package com.br.gcm.dao;

import com.br.gcm.model.LancamentoEstoque;
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
 * Date: 09/05/15
 * Time: 12:09
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class LancamentoEstoqueDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;
    @Inject private CFOPDao cfopDao;

    public void insert(LancamentoEstoque lancamentoEstoque) {
        int id = db.queryForObject("insert into LancamentoEstoque (id_Empresa, id_CFOP, Observacoes) values(?,?,?) RETURNING id_LancamentoEstoque",
                Integer.class,
                lancamentoEstoque.getId_Empresa(),
                lancamentoEstoque.getId_CFOP(),
                lancamentoEstoque.getObservacoes());

        lancamentoEstoque.setId_LancamentoEstoque(id);
    }

    public void update(LancamentoEstoque lancamentoEstoque) {
        db.update("Update LancamentoEstoque set id_Empresa=?, id_CFOP=?, Observacoes=? Where id_LancamentoEstoque=? ",
                lancamentoEstoque.getId_Empresa(),
                lancamentoEstoque.getId_CFOP(),
                lancamentoEstoque.getObservacoes(),
                lancamentoEstoque.getId_LancamentoEstoque());
    }

    public void deleteById(int id) {
        db.update("Delete from LancamentoEstoque Where id_LancamentoEstoque=? ", id);
    }

    public Long count(String tipo, int id_Empresa) {
        return db.queryForObject("SELECT COUNT(*) FROM LancamentoEstoque " +
                "Inner Join CFOP on CFOP.id_CFOP = LancamentoEstoque.id_CFOP " +
                "Where id_Empresa=? " +
                "And CFOP.Tipo=? ", Long.class, id_Empresa, tipo);
    }

    public LancamentoEstoque selectById(Integer id) {
        return db.queryForObject("Select * from LancamentoEstoque Where id_LancamentoEstoque=?", listaLancamentoEstoque, id);
    }

    public List<LancamentoEstoque> selectAll(String tipo, int id_Empresa) {
        return db.query("Select * from LancamentoEstoque " +
                "Inner Join CFOP on CFOP.id_CFOP = LancamentoEstoque.id_CFOP " +
                "Where id_Empresa=? " +
                "And CFOP.Tipo=? " +
                "Order by DataLancamento desc ",
                listaLancamentoEstoque, id_Empresa, tipo);
    }

    public List<LancamentoEstoque> selectAll_paginado(String tipo, int id_Empresa, Pageable p) {
        return db.query("Select * from LancamentoEstoque " +
                "Inner Join CFOP on CFOP.id_CFOP = LancamentoEstoque.id_CFOP " +
                "Where id_Empresa=? " +
                "And CFOP.Tipo=? " +
                "Order by DataLancamento desc " +
                "LIMIT ? OFFSET ? ",
                listaLancamentoEstoque,
                id_Empresa,
                tipo,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<LancamentoEstoque> listaLancamentoEstoque = new RowMapper<LancamentoEstoque>() {
        @Override
        public LancamentoEstoque mapRow(ResultSet rs, int i) throws SQLException{
            LancamentoEstoque lancamentoEstoque = new LancamentoEstoque();
            lancamentoEstoque.setId_LancamentoEstoque(rs.getInt("Id_LancamentoEstoque"));
            lancamentoEstoque.setId_Empresa(rs.getInt("id_Empresa"));
            lancamentoEstoque.setId_CFOP(rs.getInt("Id_CFOP"));
            lancamentoEstoque.setDataLancamento(rs.getDate("DataLancamento"));
            lancamentoEstoque.setAtualizado(rs.getBoolean("Atualizado"));
            lancamentoEstoque.setCfop(cfopDao.selectById(rs.getInt("Id_CFOP")));
            lancamentoEstoque.setObservacoes(rs.getString("Observacoes"));

            return lancamentoEstoque;
        }
    };
}

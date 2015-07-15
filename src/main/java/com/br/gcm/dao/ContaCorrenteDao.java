package com.br.gcm.dao;

import com.br.gcm.model.ContaCorrente;
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
 * Date: 06/05/15
 * Time: 08:24
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class ContaCorrenteDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;
    @Inject private BancoDao bancoDao;

    public void insert(ContaCorrente contaCorrente) {
        db.update("insert into ContaCorrente (id_Empresa, id_Banco, NumeroAgencia, NumeroContaCorrente, LimiteCredito, SaldoConta) " +
                "values(?,?,?,?,?,?)",
                contaCorrente.getId_Empresa(),
                contaCorrente.getId_Banco(),
                contaCorrente.getNumeroAgencia(),
                contaCorrente.getNumeroContaCorrente(),
                contaCorrente.getLimiteCredito(),
                contaCorrente.getSaldoConta());
    }

    public void update(ContaCorrente contaCorrente) {
        db.update("update ContaCorrente set id_Empresa=?, id_Banco=?, NumeroAgencia=?, NumeroContaCorrente=?, LimiteCredito=?, SaldoConta=? " +
                "where id_ContaCorrente=? ",
                contaCorrente.getId_Empresa(),
                contaCorrente.getId_Banco(),
                contaCorrente.getNumeroAgencia(),
                contaCorrente.getNumeroContaCorrente(),
                contaCorrente.getLimiteCredito(),
                contaCorrente.getSaldoConta(),
                contaCorrente.getId_ContaCorrente());
    }

    public void deleteById(int id) {
        db.update("Delete from ContaCorrente Where id_ContaCorrente=? ", id);
    }

    public Long count(int id_Empresa) {
        return db.queryForObject("SELECT COUNT(*) FROM ContaCorrente Where id_Empresa=?", Long.class, id_Empresa);
    }

    public ContaCorrente selectById(Integer id) {
        return db.queryForObject("Select * from ContaCorrente Where id_ContaCorrente=?", listaContaCorrente, id);
    }

    public List<ContaCorrente> selectAll(int id_Empresa) {
        return db.query("Select * from ContaCorrente Where id_Empresa=? order by id_Banco, NumeroAgencia, NumeroContaCorrente ", listaContaCorrente, id_Empresa);
    }

    public List<ContaCorrente> selectAllByBanco(int id_Empresa, int id_Banco) {
        return db.query("Select * from ContaCorrente Where id_Empresa=? and id_Banco=? order by id_Banco, NumeroAgencia, NumeroContaCorrente ", listaContaCorrente, id_Empresa, id_Banco);
    }

    public List<ContaCorrente> selectAll_paginado(int id_Empresa, Pageable p) {
        return db.query("Select * from ContaCorrente Where id_Empresa=? order by id_Banco, NumeroAgencia, NumeroContaCorrente " +
                "LIMIT ? OFFSET ? ",
                listaContaCorrente,
                id_Empresa,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<ContaCorrente> listaContaCorrente = new RowMapper<ContaCorrente>() {
        @Override
        public ContaCorrente mapRow(ResultSet rs, int i) throws SQLException{
            ContaCorrente contaCorrente = new ContaCorrente();
            contaCorrente.setId_ContaCorrente(rs.getInt("Id_ContaCorrente"));
            contaCorrente.setId_Empresa(rs.getInt("id_Empresa"));
            contaCorrente.setId_Banco(rs.getInt("id_Banco"));
            contaCorrente.setNumeroAgencia(rs.getString("NumeroAgencia"));
            contaCorrente.setNumeroContaCorrente(rs.getString("NumeroContaCorrente"));
            contaCorrente.setLimiteCredito(rs.getFloat("LimiteCredito"));
            contaCorrente.setSaldoConta(rs.getFloat("SaldoConta"));

            contaCorrente.setBanco(bancoDao.selectById(rs.getInt("id_Banco")));

            return contaCorrente;
        }
    };
}

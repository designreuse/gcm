package com.br.gcm.dao;

import com.br.gcm.model.ContaCorrente;
import com.br.gcm.model.Banco;
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
        db.update("update ContaCorrente set id_Empresa=?, id_Banco=?, NumeroAgencia=?, NumeroContaCorrente=?, LimiteCredito=? " +
                "where id_ContaCorrente=? ",
                contaCorrente.getId_Empresa(),
                contaCorrente.getId_Banco(),
                contaCorrente.getNumeroAgencia(),
                contaCorrente.getNumeroContaCorrente(),
                contaCorrente.getLimiteCredito(),
                contaCorrente.getId_ContaCorrente());
    }

    public void deleteById(int id) {
        db.update("Delete from ContaCorrente Where id_ContaCorrente=? ", id);
    }

    public Long count(ContaCorrente filtros) {
        List arr = new ArrayList<>();
        String sql = "SELECT COUNT(*) FROM ContaCorrente Where 1=1 ";

        if (filtros.getId_Empresa() > 0){
            sql = sql + " And Id_Empresa = ?";
            arr.add(filtros.getId_Empresa());
        }

        if (filtros.getId_Banco() > 0){
            sql = sql + " And Id_Banco = ?";
            arr.add(filtros.getId_Banco());
        }

        if (filtros.getId_ContaCorrente() > 0){
            sql = sql + " And Id_ContaCorrente = ?";
            arr.add(filtros.getId_ContaCorrente());
        }

        return db.queryForObject(sql, Long.class, arr.toArray());
    }

    public ContaCorrente selectById(Integer id) {
        return db.queryForObject("Select ContaCorrente.*, Banco.NumeroBanco, Banco.Descricao from ContaCorrente " +
                "Inner Join Banco on ContaCorrente.id_Banco = Banco.id_Banco Where id_ContaCorrente=?", listaContaCorrente, id);
    }

    public List<ContaCorrente> selectAll(ContaCorrente filtros, Pageable p) {
        List arr = new ArrayList<>();
        String sql = "Select ContaCorrente.*, Banco.NumeroBanco, Banco.Descricao from ContaCorrente " +
                     "Inner Join Banco on ContaCorrente.id_Banco = Banco.id_Banco Where 1=1 ";

        if (filtros.getId_Empresa() > 0){
            sql = sql + " And Id_Empresa = ?";
            arr.add(filtros.getId_Empresa());
        }

        if (filtros.getId_Banco() > 0){
            sql = sql + " And ContaCorrente.Id_Banco = ?";
            arr.add(filtros.getId_Banco());
        }

        if (filtros.getId_ContaCorrente() > 0){
            sql = sql + " And Id_ContaCorrente = ?";
            arr.add(filtros.getId_ContaCorrente());
        }

        sql = sql + " order by id_Banco, NumeroAgencia, NumeroContaCorrente LIMIT ? OFFSET ? ";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.query(sql,
                listaContaCorrente,
                arr.toArray());
    }

    public List<ContaCorrente> selectAll(ContaCorrente filtros) {
        List arr = new ArrayList<>();
        String sql = "Select ContaCorrente.*, Banco.NumeroBanco, Banco.Descricao from ContaCorrente " +
                "Inner Join Banco on ContaCorrente.id_Banco = Banco.id_Banco Where 1=1 ";

        if (filtros.getId_Empresa() > 0){
            sql = sql + " And Id_Empresa = ?";
            arr.add(filtros.getId_Empresa());
        }

        if (filtros.getId_Banco() > 0){
            sql = sql + " And ContaCorrente.Id_Banco = ?";
            arr.add(filtros.getId_Banco());
        }

        if (filtros.getId_ContaCorrente() > 0){
            sql = sql + " And Id_ContaCorrente = ?";
            arr.add(filtros.getId_ContaCorrente());
        }

        sql = sql + " order by id_Banco, NumeroAgencia, NumeroContaCorrente ";

        return db.query(sql,
                listaContaCorrente,
                arr.toArray());
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

            contaCorrente.setNumeroBanco(rs.getString("NumeroBanco"));
            contaCorrente.setDescricao(rs.getString("Descricao"));


            return contaCorrente;
        }
    };
}

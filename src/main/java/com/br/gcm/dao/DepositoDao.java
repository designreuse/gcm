package com.br.gcm.dao;

import com.br.gcm.model.Deposito;
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
 * Date: 19/04/15
 * Time: 22:35
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class DepositoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(Deposito deposito) {
        db.update("insert into Deposito (id_Empresa, Descricao, Endereco) values(?,?,?)",
                deposito.getId_Empresa(),
                deposito.getDescricao(),
                deposito.getEndereco());
    }

    public void update(Deposito deposito) {
        db.update("Update Deposito set id_Empresa=?, Descricao=?, Endereco=? Where id_Deposito=?",
                deposito.getId_Empresa(),
                deposito.getDescricao(),
                deposito.getEndereco(),
                deposito.getId_Deposito());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM Deposito Where id_Deposito=?",id);
    }

    public Long count(int id_Empresa) {
        return db.queryForObject("SELECT COUNT(*) FROM Deposito Where id_Empresa=?", Long.class, id_Empresa);
    }

    public Long count(Deposito filtros) {
        List arr = new ArrayList<>();
        String sql = "Select COUNT(*) from Deposito Where 1=1 ";

        if (filtros.getId_Empresa() > 0){
            sql = sql + " And id_Empresa = ? ";
            arr.add(filtros.getId_Empresa());
        }
        if (filtros.getDescricao() != null && filtros.getDescricao() !=""){
            sql = sql + " And descricao like ? ";
            arr.add("%"+filtros.getDescricao()+"%");
        }

        return db.queryForObject(sql, Long.class, arr.toArray());
    }

    public Deposito selectById(Integer id) {
        return db.queryForObject("Select * from Deposito Where id_Deposito=?", listaDeposito, id);
    }

    public List<Deposito> selectAll(int id_Empresa) {
        return db.query("Select * from Deposito Where id_Empresa=?", listaDeposito, id_Empresa);
    }

    public List<Deposito> selectAll_paginado(int id_Empresa, Pageable p) {
        return db.query("Select * from Deposito Where id_Empresa=? Order By Descricao " +
                "LIMIT ? OFFSET ? ",
                listaDeposito,
                id_Empresa,
                p.getPageSize(),
                p.getOffset());
    }

    public List<Deposito> selectAll_paginado(Deposito filtros, Pageable p) {
        List arr = new ArrayList<>();
        String sql = "Select * from Deposito Where 1=1 ";

        if (filtros.getId_Empresa() > 0){
            sql = sql + " And id_Empresa = ? ";
            arr.add(filtros.getId_Empresa());
        }
        if (filtros.getDescricao() != null && filtros.getDescricao() !=""){
            sql = sql + " And descricao like ? ";
            arr.add("%"+filtros.getDescricao()+"%");
        }

        sql = sql + " Order By Descricao LIMIT ? OFFSET ? ";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.query(sql,
                listaDeposito,
                arr.toArray());
    }

    //mappers
    private RowMapper<Deposito> listaDeposito = new RowMapper<Deposito>() {
        @Override
        public Deposito mapRow(ResultSet rs, int i) throws SQLException{
            Deposito deposito = new Deposito();
            deposito.setId_Deposito(rs.getInt("Id_Deposito"));
            deposito.setId_Empresa(rs.getInt("id_Empresa"));
            deposito.setDescricao(rs.getString("Descricao"));
            deposito.setEndereco(rs.getString("Endereco"));

            return deposito;
        }
    };
}

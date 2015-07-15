package com.br.gcm.dao;

import com.br.gcm.model.PlanoContas;
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
 * Date: 05/05/15
 * Time: 16:38
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class PlanoContasDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(PlanoContas planoContas) {
        db.update("insert into PlanoContas (id_Empresa, CodigoConta, Descricao, TipoConta, Agrupamento) values(?,?,?,?,?)",
                planoContas.getId_Empresa(),
                planoContas.getCodigoConta(),
                planoContas.getDescricao(),
                planoContas.getTipoConta(),
                planoContas.getAgrupamento());
    }

    public void update(PlanoContas planoContas) {
        db.update("update PlanoContas set id_Empresa=?, CodigoConta=?, Descricao=?, TipoConta=?, Agrupamento=? Where id_PlanoContas=? ",
                planoContas.getId_Empresa(),
                planoContas.getCodigoConta(),
                planoContas.getDescricao(),
                planoContas.getTipoConta(),
                planoContas.getAgrupamento(),
                planoContas.getId_PlanoContas());
    }

    public void deleteById(int id) {
        db.update("Delete from PlanoContas Where id_PlanoContas=? ", id);
    }

    public Long count(int id_Empresa) {
        return db.queryForObject("SELECT COUNT(*) FROM PlanoContas Where id_Empresa=?", Long.class, id_Empresa);
    }

    public PlanoContas selectById(Integer id) {
        return db.queryForObject("Select * from PlanoContas Where id_PlanoContas=?", listaPlanoContas, id);
    }

    public List<PlanoContas> selectAll(int id_Empresa) {
        return db.query("Select * from PlanoContas Where id_Empresa=? Order By CodigoConta", listaPlanoContas, id_Empresa);
    }

    public List<PlanoContas> selectAllbyFiltros(int id_Empresa, String tipoconta, String filtros) {
        List arr = new ArrayList<>();
        String sql = "Select * from PlanoContas Where 1=1 ";
        if (id_Empresa > 0){sql = sql + " And id_Empresa=?"; arr.add(id_Empresa);}
        if (tipoconta != "" && tipoconta != null) {sql = sql + " And TipoConta = ? "; arr.add(tipoconta);}
        if (filtros != "" && filtros != null) {sql = sql + " And (CodigoConta like ? or Descricao like ?) "; arr.add("%"+filtros+"%"); arr.add("%"+filtros+"%");}
        sql = sql + " Order By CodigoConta ";

        return db.query(sql, listaPlanoContas, arr.toArray());
    }

    public PlanoContas selectAllbyCodigoConta(int id_Empresa, String tipoconta, String codigo) {
        return db.queryForObject("Select * from PlanoContas Where id_Empresa=? And TipoConta = ? " +
                " And replace(CodigoConta,'.','')=? And Agrupamento='A' " +
                " Order By CodigoConta", listaPlanoContas, id_Empresa, tipoconta, codigo);
    }

    public List<PlanoContas> selectAll_paginado(int id_Empresa, Pageable p) {
        return db.query("Select * from PlanoContas Where id_Empresa=? Order By CodigoConta " +
                "LIMIT ? OFFSET ? ",
                listaPlanoContas,
                id_Empresa,
                p.getPageSize(),
                p.getOffset());
    }

    private RowMapper<PlanoContas> listaPlanoContas = new RowMapper<PlanoContas>() {
        @Override
        public PlanoContas mapRow(ResultSet rs, int i) throws SQLException{
            PlanoContas planoContas = new PlanoContas();
            planoContas.setId_PlanoContas(rs.getInt("Id_PlanoContas"));
            planoContas.setId_Empresa(rs.getInt("id_Empresa"));
            planoContas.setCodigoConta(rs.getString("CodigoConta"));
            planoContas.setDescricao(rs.getString("Descricao"));
            planoContas.setTipoConta(rs.getString("TipoConta"));
            planoContas.setAgrupamento(rs.getString("Agrupamento"));

            return planoContas;
        }
    };
}

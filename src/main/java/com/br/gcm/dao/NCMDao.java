package com.br.gcm.dao;

import com.br.gcm.model.NCM;
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
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class NCMDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(NCM ncm) {
        db.update("insert into NCM (CodigoNcm, Descricao, AliquotaIPI, AliquotaII, id_CSTPIS, id_CSTCOFINS) values(?,?,?,?,?,?)",
                ncm.getCodigoNCM(),
                ncm.getDescricao(),
                ncm.getAliquotaIPI(),
                ncm.getAliquotaII(),
                ncm.getId_CSTPIS(),
                ncm.getId_CSTCOFINS());
    }

    public void update(NCM ncm) {
        db.update("Update NCM set CodigoNcm=?, Descricao=?, AliquotaIPI=?, AliquotaII=?, Id_CSTPIS=?, Id_CSTCOFINS=?  Where id_NCM=?",
                ncm.getCodigoNCM(),
                ncm.getDescricao(),
                ncm.getAliquotaIPI(),
                ncm.getAliquotaII(),
                ncm.getId_CSTPIS(),
                ncm.getId_CSTCOFINS(),
                ncm.getId_NCM());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM NCM Where id_NCM=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM NCM", Long.class);
    }

    public Long count(NCM filtros) {
        List arr = new ArrayList<>();
        String sql = "SELECT COUNT(*) FROM NCM Where 1=1";

        if (filtros.getId_NCM() > 0){
            sql = sql + " And id_ncm = ? ";
            arr.add(filtros.getId_NCM());
        }
        if (filtros.getCodigoNCM() != null && filtros.getCodigoNCM() != ""){
            sql = sql + " And CodigoNCM like ? ";
            arr.add("%"+filtros.getCodigoNCM()+"%");
        }
        if (filtros.getDescricao() != null && filtros.getDescricao() != ""){
            sql = sql + " And Descricao like ? ";
            arr.add("%"+filtros.getDescricao()+"%");
        }

        return db.queryForObject(sql, Long.class, arr.toArray());
    }

    public NCM selectById(Integer id) {
        return db.queryForObject("Select * from NCM Where id_NCM=?", listaNCM, id);
    }

    public List<NCM> selectAll() {
        return db.query("Select * from NCM", listaNCM);
    }

    public List<NCM> selectAll_paginado(Pageable p) {
        return db.query("Select * from NCM Order By CodigoNCM " +
                "LIMIT ? OFFSET ? ",
                listaNCM,
                p.getPageSize(),
                p.getOffset());
    }

    public List<NCM> selectAll_paginado(NCM filtros, Pageable p) {
        List arr = new ArrayList<>();
        String sql = "Select * from Ncm Where 1=1 ";

        if (filtros.getId_NCM() > 0){
            sql = sql + " And id_ncm = ? ";
            arr.add(filtros.getId_NCM());
        }
        if (filtros.getCodigoNCM() != null && filtros.getCodigoNCM() != ""){
            sql = sql + " And CodigoNCM like ? ";
            arr.add("%"+filtros.getCodigoNCM()+"%");
        }
        if (filtros.getDescricao() != null && filtros.getDescricao() != ""){
            sql = sql + " And Descricao like ? ";
            arr.add("%"+filtros.getDescricao()+"%");
        }

        sql = sql + "Order By CodigoNCM LIMIT ? OFFSET ? ";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.query(sql,
                listaNCM,
                arr.toArray());
    }

    //mappers
    private RowMapper<NCM> listaNCM = new RowMapper<NCM>() {
        @Override
        public NCM mapRow(ResultSet rs, int i) throws SQLException{
            NCM ncm = new NCM();
            ncm.setId_NCM(rs.getInt("Id_NCM"));
            ncm.setCodigoNCM(rs.getString("CodigoNCM"));
            ncm.setDescricao(rs.getString("Descricao"));
            ncm.setAliquotaIPI(rs.getLong("AliquotaIPI"));
            ncm.setAliquotaII(rs.getLong("AliquotaII"));
            ncm.setId_CSTPIS(rs.getInt("Id_CSTPIS"));
            ncm.setId_CSTCOFINS(rs.getInt("Id_CSTCOFINS"));

            return ncm;
        }
    };
}

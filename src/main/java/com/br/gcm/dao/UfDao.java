package com.br.gcm.dao;

import com.br.gcm.model.Uf;
import com.br.gcm.util.Rotinas;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 19/12/13
 * Time: 14:46
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class UfDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(Uf uf) {
        //int id  = 0;
        //SqlRowSet rs = db.queryForRowSet("Select NEXTVAL('Uf_seq') as id ");
        //rs.next();
        //id = rs.getInt("id");

        db.update("insert into UF (id_pais, siglauf, descricao, ibge)"
                + "values (?,?,?,?)",
                //id,
                uf.getId_Pais(),
                uf.getSiglaUf().toUpperCase(rotinas.getPT_BR()),
                uf.getDescricao().toUpperCase(rotinas.getPT_BR()),
                uf.getIbge());
    }

    public void update(Uf uf) {
        String updateStr = "update uf set " +
                "id_pais=?," +
                "siglauf=?," +
                "descricao=?," +
                "ibge=? " +
                "where id_uf=?";
        db.update(updateStr,
                uf.getId_Pais(),
                uf.getSiglaUf().toUpperCase(rotinas.getPT_BR()),
                uf.getDescricao().toUpperCase(rotinas.getPT_BR()),
                uf.getIbge(),
                uf.getId_Uf());
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM uf", Long.class);
    }

    public Uf selectById(Integer id) {
        return db.queryForObject("Select uf.*, pais.siglapais from Uf inner join pais on uf.id_pais = pais.id_pais where id_uf=?", todasUf, id);
    }

    public List<Uf> selectByPais(Integer id_pais) {
        return db.query ("Select uf.*, pais.siglapais from Uf inner join pais on uf.id_pais = pais.id_pais where uf.id_pais=?", todasUf, id_pais);
    }

    public List<Uf> selectAll() {
        return db.query("Select uf.*, pais.siglapais from Uf inner join pais on uf.id_pais = pais.id_pais order by siglapais, siglauf", todasUf);
    }

    public List<Uf> selectAll_Paginado(Pageable p) {
        return db.query("Select uf.*, pais.siglapais " +
                        "from Uf inner join pais on uf.id_pais = pais.id_pais " +
                        "order by siglapais, siglauf " +
                        "LIMIT ? OFFSET ? ",
                        todasUf,
                        p.getPageSize(),
                        p.getOffset());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM Uf Where id_uf=?",id);
    }

    //mappers
    private RowMapper<Uf> todasUf = new RowMapper<Uf>() {
        @Override
        public Uf mapRow(ResultSet rs, int i) throws SQLException{
            Uf uf = new Uf();
            uf.setId_Uf(rs.getInt("id_Uf"));
            uf.setId_Pais(rs.getInt("id_Pais"));
            uf.setSiglaPais(rs.getString("siglaPais"));
            uf.setSiglaUf(rs.getString("siglaUf"));
            uf.setDescricao(rs.getString("descricao"));
            uf.setIbge(rs.getString("ibge"));

            return uf;
        }
    };
}
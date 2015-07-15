package com.br.gcm.dao;

import com.br.gcm.model.GrupoUsuario;
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
 * Date: 12/04/15
 * Time: 10:46
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class GrupoUsuarioDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(GrupoUsuario grupo) {

        db.update("insert into GrupoUsuario (descricao) " +
                "values (?)",
                grupo.getDescricao());
    }

    public void update(GrupoUsuario grupo) {
        String updateStr = "update GrupoUsuario set " +
                "descricao=? "+
                "Where id_GrupoUsuario=?";

        db.update(updateStr,
                grupo.getDescricao(),
                grupo.getId_GrupoUsuario());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM GrupoUsuario Where id_GrupoUsuario=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM GrupoUsuario", Long.class);
    }

    public GrupoUsuario selectById(Integer id) {
        return db.queryForObject("Select * from GrupoUsuario where id_GrupoUsuario=?", listaGrupos, id);
    }

    public List<GrupoUsuario> selectAll() {
        return db.query("Select * from GrupoUsuario order by descricao", listaGrupos);
    }

    public List<GrupoUsuario> selectAll_paginado(Pageable p) {
        return db.query("Select * from GrupoUsuario " +
                "order by descricao " +
                "LIMIT ? OFFSET ? ",
                listaGrupos,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<GrupoUsuario> listaGrupos = new RowMapper<GrupoUsuario>() {
        @Override
        public GrupoUsuario mapRow(ResultSet rs, int i) throws SQLException{
            GrupoUsuario grupo = new GrupoUsuario();
            grupo.setId_GrupoUsuario(rs.getInt("Id_GrupoUsuario"));
            grupo.setDescricao(rs.getString("Descricao"));

            return grupo;
        }
    };
}

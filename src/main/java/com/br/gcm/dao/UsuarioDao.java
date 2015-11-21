package com.br.gcm.dao;

import com.br.gcm.model.Usuario;
import com.br.gcm.util.Rotinas;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UsuarioDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;


    public void insert(Usuario usuario) {

        db.update("INSERT INTO usuario (nome, login, senha, ativo, email) " +
                "VALUES (?,?,?,?,?)",
                usuario.getNome().toUpperCase(rotinas.getPT_BR()),
                usuario.getLogin().toUpperCase(rotinas.getPT_BR()),
                rotinas.md5(usuario.getSenha(), Charset.defaultCharset()),
                usuario.isAtivo(),
                usuario.getEmail().toLowerCase(rotinas.getPT_BR()));
    }


    public void update(Usuario usuario) {
        db.update("UPDATE usuario SET " +
                "nome=?, login=?, senha=?, ativo=?, email=?  WHERE id_usuario=?",
                usuario.getNome().toUpperCase(rotinas.getPT_BR()),
                usuario.getLogin().toUpperCase(rotinas.getPT_BR()),
                usuario.getSenha(),
                usuario.isAtivo(),
                usuario.getEmail().toLowerCase(rotinas.getPT_BR()),
                usuario.getId_usuario());
    }


    public void delete(Integer id) {
        db.update("DELETE FROM usuario WHERE id_usuario=?", id);
    }


    @Transactional(readOnly = true)
    public List<Usuario> all() {
        try {
            return db.query("SELECT * FROM usuario ", mapperUsuario);
        } catch (EmptyResultDataAccessException e) {
        //    logger.debug("all(): {}", e.getMessage());
            return new ArrayList<>(0);
        }
    }

   // @Transactional(readOnly = true)
    public List<Usuario> all(Pageable pageable) {
        return db.query("SELECT * FROM usuario ORDER BY nome LIMIT ? OFFSET ?",
                mapperUsuario,
                pageable.getPageSize(),
                pageable.getOffset());
    }


    @Transactional(readOnly = true)
    public Usuario byId(Integer id) {
        try {
            return db.queryForObject("SELECT * FROM usuario WHERE id_usuario=?", mapperUsuario, id);
        } catch (IncorrectResultSizeDataAccessException e) {
        //    logger.debug("byId({}): {}", id, e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Usuario byNome(String nome) {
        try {
            return db.queryForObject("SELECT * FROM usuario WHERE UPPER(login)=?", mapperUsuario, nome.toUpperCase(rotinas.getPT_BR()));
        } catch (IncorrectResultSizeDataAccessException e) {
        //    logger.debug("byNome(nome: {}): {}", nome, e.getMessage());
            return null;
        }
    }

    @Transactional(readOnly = true)
    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM usuario ", Long.class);
    }

    @Transactional(readOnly = true)
    public Boolean transacaousuario(Integer id_usuario, String codigotransacao){
        List arr = new ArrayList<>();
        String sql = "Select ";
        sql = sql + "case when (select count(*) from grupotransacao ";
        sql = sql + "inner join transacao on grupotransacao.id_transacao = transacao.id_transacao ";
        sql = sql + "inner join usuariodogrupo on usuariodogrupo.id_grupousuario = grupotransacao.id_grupousuario ";
        sql = sql + "where replace(transacao.codigotransacao,'.','') = ? ";
        sql = sql + "and usuariodogrupo.id_Usuario = ?) > 0 then true else false end as permissao ";

        arr.add(codigotransacao.replace(".","").replace(".","").replace(".","").replace(".","").replace(".",""));
        arr.add(id_usuario);

        return db.queryForObject(sql, Boolean.class, arr.toArray());
    }

    private RowMapper<Usuario> mapperUsuario = new RowMapper<Usuario>() {
        @Override
        public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Usuario u = new Usuario();
            u.setId_usuario(rs.getInt("id_usuario"));
            u.setLogin(rs.getString("login"));
            u.setNome(rs.getString("nome"));
            u.setEmail(rs.getString("email"));
            u.setSenha(rs.getString("senha"));
            u.setAtivo(rs.getBoolean("ativo"));
            return u;
        }
    };

}
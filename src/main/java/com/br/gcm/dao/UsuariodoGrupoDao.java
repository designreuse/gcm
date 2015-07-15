package com.br.gcm.dao;

import com.br.gcm.model.UsuariodoGrupo;
import com.br.gcm.util.Rotinas;
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
 * Date: 16/04/15
 * Time: 10:22
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class UsuariodoGrupoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(List<UsuariodoGrupo> usuarios) {

        db.update("DELETE FROM UsuariodoGrupo Where id_GrupoUsuario=?",usuarios.get(0).getId_GrupoUsuario());

        for(int i = 0; i < usuarios.size(); i++){
            if (usuarios.get(i).getPertence()== true){
                db.update("insert into UsuariodoGrupo (id_GrupoUsuario, id_Usuario) " +
                        "values (?,?)",
                        usuarios.get(i).getId_GrupoUsuario(),
                        usuarios.get(i).getId_Usuario());
            }
        }
    }

    public void insert(int id_grupo, int id_usuario, boolean pertence) {

        db.update("DELETE FROM UsuariodoGrupo Where id_GrupoUsuario=? and id_Usuario =?",id_grupo, id_usuario);

        if (pertence == true){
            db.update("insert into usuariodogrupo (id_grupousuario, id_usuario) " +
                    "values (?,?)",
                    id_grupo,
                    id_usuario);
        }
    }

    public List<UsuariodoGrupo> selectAll(int id_GrupoUsuario) {
        return db.query("Select coalesce(UsuariodoGrupo.id_UsuariodoGrupo, 0) as id_UsuariodoGrupo, " +
                "usuario.id_Usuario, " +
                "usuario.nome as NomeUsuario, " +
                id_GrupoUsuario+" as id_GrupoUsuario, " +
                "(select descricao from GrupoUsuario where id_GrupoUsuario = "+id_GrupoUsuario+") as DescricaoGrupo, " +
                "Case "+
                "  when (select count(*) from usuariodogrupo ug where ug.id_usuario = usuario.id_usuario and ug.id_grupousuario = "+id_GrupoUsuario+")>0 Then True " +
                "  else false "+
                "end Pertence "+
                "from usuario "+
                "left outer join UsuariodoGrupo on UsuariodoGrupo.id_Usuario = Usuario.id_Usuario "+
                "where usuario.id_usuario not in (select id_usuario from usuariodogrupo where id_grupousuario <> "+id_GrupoUsuario+") "+
                "order by usuario.nome  ", listaUsuariosGrupos);
    }

    //mappers
    private RowMapper<UsuariodoGrupo> listaUsuariosGrupos = new RowMapper<UsuariodoGrupo>() {
        @Override
        public UsuariodoGrupo mapRow(ResultSet rs, int i) throws SQLException{
            UsuariodoGrupo usuarios = new UsuariodoGrupo();
            usuarios.setId_UsuariodoGrupo(rs.getInt("Id_UsuariodoGrupo"));
            usuarios.setId_GrupoUsuario(rs.getInt("Id_GrupoUsuario"));
            usuarios.setId_Usuario(rs.getInt("Id_Usuario"));
            usuarios.setDescricaoGrupo(rs.getString("DescricaoGrupo"));
            usuarios.setNomeUsuario(rs.getString("NomeUsuario"));
            usuarios.setPertence(rs.getBoolean("Pertence"));

            return usuarios;
        }
    };
}

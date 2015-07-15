package com.br.gcm.dao;

import com.br.gcm.model.GrupoTransacao;
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
 * Date: 17/04/15
 * Time: 09:33
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class GrupoTransacaoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(int id_grupo, int id_transacao, boolean pertence) {

        db.update("DELETE FROM GrupoTransacao Where id_GrupoUsuario=? and id_Transacao =?",id_grupo, id_transacao);

        if (pertence == true){
            db.update("insert into GrupoTransacao (id_grupousuario, id_Transacao) " +
                    "values (?,?)",
                    id_grupo,
                    id_transacao);
        }
    }

    public List<GrupoTransacao> selectAll(int id_GrupoUsuario) {
        return db.query("Select 0 as id_GrupoTransacao, " +
                "Transacao.id_Transacao, " +
                "Transacao.CodigoTransacao, "+
                "Transacao.Descricao as DescricaoTransacao, " +
                id_GrupoUsuario+" as id_GrupoUsuario, " +
                "(select descricao from GrupoUsuario where id_GrupoUsuario = "+id_GrupoUsuario+") as DescricaoGrupo, " +
                "Case "+
                "  when (select count(*) from GrupoTransacao gt where gt.id_transacao = Transacao.id_Transacao and gt.id_grupousuario = "+id_GrupoUsuario+")>0 Then True " +
                "  else false "+
                "end Pertence "+
                "from Transacao "+
                "order by Transacao.CodigoTransacao  ", listaGrupoTransacao);
    }

    public List<GrupoTransacao> selectAllUsuario(int id_Usuario) {
        return db.query("Select " +
                "GrupoTransacao.id_GrupoTransacao, " +
                "GrupoTransacao.id_Transacao, " +
                "GrupoTransacao.id_GrupoUsuario, " +
                "Transacao.CodigoTransacao, "+
                "Transacao.Descricao as DescricaoTransacao, " +
                "GrupoUsuario.Descricao as DescricaoGrupo, "+
                "true as Pertence "+
                "from GrupoTransacao " +
                "Inner Join Transacao on GrupoTransacao.id_Transacao = Transacao.id_Transacao " +
                "Inner Join GrupoUsuario on GrupoTransacao.id_GrupoUsuario = GrupoUsuario.id_GrupoUsuario " +
                "Inner Join UsuariodoGrupo on GrupoTransacao.id_GrupoUsuario = UsuariodoGrupo.id_GrupoUsuario " +
                "Where UsuariodoGrupo.id_Usuario = ?"+
                "order by Transacao.CodigoTransacao  ", listaGrupoTransacao, id_Usuario);
    }

    //mappers
    private RowMapper<GrupoTransacao> listaGrupoTransacao = new RowMapper<GrupoTransacao>() {
        @Override
        public GrupoTransacao mapRow(ResultSet rs, int i) throws SQLException{
            GrupoTransacao grupoTransacao = new GrupoTransacao();
            grupoTransacao.setId_GrupoTransacao(rs.getInt("Id_GrupoTransacao"));
            grupoTransacao.setId_GrupoUsuario(rs.getInt("Id_GrupoUsuario"));
            grupoTransacao.setId_Transacao(rs.getInt("Id_Transacao"));
            grupoTransacao.setDescricaoGrupo(rs.getString("DescricaoGrupo"));
            grupoTransacao.setCodigoTransacao(rs.getInt("CodigoTransacao"));
            grupoTransacao.setDescricaoTransacao(rs.getString("DescricaoTransacao"));
            grupoTransacao.setPertence(rs.getBoolean("Pertence"));

            return grupoTransacao;
        }
    };
}

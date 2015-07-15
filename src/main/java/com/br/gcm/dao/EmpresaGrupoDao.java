package com.br.gcm.dao;

import com.br.gcm.model.EmpresaGrupo;
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
 * Time: 22:30
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class EmpresaGrupoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(int id_grupo, int id_empresa, boolean pertence) {

        db.update("DELETE FROM EmpresaGrupo Where id_GrupoUsuario=? and id_Empresa =?",id_grupo, id_empresa);

        if (pertence == true){
            db.update("insert into EmpresaGrupo (id_GrupoUsuario, id_Empresa) " +
                    "values (?,?)",
                    id_grupo,
                    id_empresa);
        }
    }

    public List<EmpresaGrupo> selectAll(int id_GrupoUsuario) {
        return db.query("Select 0 as id_EmpresaGrupo, " +
                "Empresa.id_Empresa, " +
                "Empresa.RazaoSocial, " +
                id_GrupoUsuario+" as id_GrupoUsuario, " +
                "(select descricao from GrupoUsuario where id_GrupoUsuario = "+id_GrupoUsuario+") as DescricaoGrupo, " +
                "Case "+
                "  when (select count(*) from EmpresaGrupo eg where eg.id_empresa = Empresa.id_empresa and eg.id_grupousuario = "+id_GrupoUsuario+")>0 Then True " +
                "  else false "+
                "end Pertence "+
                "from Empresa "+
                "order by Empresa.RazaoSocial  ", listaEmpresaGrupo);
    }

    //mappers
    private RowMapper<EmpresaGrupo> listaEmpresaGrupo = new RowMapper<EmpresaGrupo>() {
        @Override
        public EmpresaGrupo mapRow(ResultSet rs, int i) throws SQLException{
            EmpresaGrupo empresaGrupo = new EmpresaGrupo();
            empresaGrupo.setId_EmpresaGrupo(rs.getInt("Id_EmpresaGrupo"));
            empresaGrupo.setId_GrupoUsuario(rs.getInt("Id_GrupoUsuario"));
            empresaGrupo.setId_Empresa(rs.getInt("Id_Empresa"));
            empresaGrupo.setDescricaoGrupo(rs.getString("DescricaoGrupo"));
            empresaGrupo.setRazaoSocial(rs.getString("RazaoSocial"));
            empresaGrupo.setPertence(rs.getBoolean("Pertence"));

            return empresaGrupo;
        }
    };
}

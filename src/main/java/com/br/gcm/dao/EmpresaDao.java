package com.br.gcm.dao;

import com.br.gcm.model.Empresa;
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
 * Date: 29/12/13
 * Time: 09:10
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class EmpresaDao{
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(Empresa empresa) {
        String Cnpj = empresa.getCnpj().replace(".","").replace("/","").replace("-","");
        String Cep = empresa.getCep().replace("-","");

        db.update("insert into Empresa (" +
                "Cnpj,"+
                "RazaoSocial,"+
                "NomeFantasia,"+
                "InscricaoEstadual,"+
                "InscricaoMunicipal,"+
                "Endereco,"+
                "Numero,"+
                "Bairro,"+
                "Cep,"+
                "id_Municipio,"+
                "Telefone1,"+
                "Telefone2,"+
                "Fax,"+
                "Email,"+
                "Url)"+
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                Cnpj,
                empresa.getRazaoSocial().toUpperCase(rotinas.getPT_BR()),
                empresa.getNomeFantasia().toUpperCase(rotinas.getPT_BR()),
                empresa.getInscricaoEstadual().toUpperCase(rotinas.getPT_BR()),
                empresa.getInscricaoMunicipal().toUpperCase(rotinas.getPT_BR()),
                empresa.getEndereco().toUpperCase(rotinas.getPT_BR()),
                empresa.getNumero().toUpperCase(rotinas.getPT_BR()),
                empresa.getBairro().toUpperCase(rotinas.getPT_BR()),
                Cep,
                empresa.getId_Municipio(),
                empresa.getTelefone1(),
                empresa.getTelefone2(),
                empresa.getFax(),
                empresa.getEmail().toLowerCase(rotinas.getPT_BR()),
                empresa.getUrl().toLowerCase(rotinas.getPT_BR()));
    }

    public void update(Empresa empresa) {
        String updateStr = "update Empresa set " +
                "Cnpj=?,"+
                "RazaoSocial=?,"+
                "NomeFantasia=?,"+
                "InscricaoEstadual=?,"+
                "InscricaoMunicipal=?,"+
                "Endereco=?,"+
                "Numero=?,"+
                "Bairro=?,"+
                "Cep=?,"+
                "id_Municipio=?,"+
                "Telefone1=?,"+
                "Telefone2=?,"+
                "Fax=?,"+
                "Email=?,"+
                "Url=?"+
                "Where id_Empresa=?";
        db.update(updateStr,
                empresa.getCnpj(),
                empresa.getRazaoSocial().toUpperCase(rotinas.getPT_BR()),
                empresa.getNomeFantasia().toUpperCase(rotinas.getPT_BR()),
                empresa.getInscricaoEstadual().toUpperCase(rotinas.getPT_BR()),
                empresa.getInscricaoMunicipal().toUpperCase(rotinas.getPT_BR()),
                empresa.getEndereco().toUpperCase(rotinas.getPT_BR()),
                empresa.getNumero().toUpperCase(rotinas.getPT_BR()),
                empresa.getBairro().toUpperCase(rotinas.getPT_BR()),
                empresa.getCep(),
                empresa.getId_Municipio(),
                empresa.getTelefone1(),
                empresa.getTelefone2(),
                empresa.getFax(),
                empresa.getEmail().toLowerCase(rotinas.getPT_BR()),
                empresa.getUrl().toLowerCase(rotinas.getPT_BR()),
                empresa.getId_Empresa());
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM Empresa", Long.class);
    }

    public Empresa selectById(Integer id) {
        return db.queryForObject("Select Empresa.*, municipio.descricao, uf.id_uf, uf.siglauf, uf.id_pais, pais.siglapais from Empresa left outer join municipio on Empresa.id_municipio = municipio.id_municipio left outer join uf on municipio.id_uf = uf.id_uf left outer join pais on uf.id_pais = pais.id_pais where Empresa.id_Empresa=?", todasEmpresas, id);
    }

    public List<Empresa> selectAll() {
        return db.query("Select Empresa.*, municipio.descricao, uf.id_uf, uf.siglauf, uf.id_pais, pais.siglapais from Empresa left outer join municipio on Empresa.id_municipio = municipio.id_municipio left outer join uf on municipio.id_uf = uf.id_uf left outer join pais on uf.id_pais = pais.id_pais order by RazaoSocial", todasEmpresas);
    }

    public List<Empresa> selectEmpresasUsuario(int id_Usuario) {
        return db.query("Select Empresa.*, municipio.descricao, uf.id_uf, uf.siglauf, uf.id_pais, pais.siglapais from Empresa " +
                "left outer join municipio on Empresa.id_municipio = municipio.id_municipio " +
                "left outer join uf on municipio.id_uf = uf.id_uf " +
                "left outer join pais on uf.id_pais = pais.id_pais " +
                "Inner Join EmpresaGrupo on EmpresaGrupo.id_Empresa = Empresa.id_Empresa " +
                "Inner Join UsuariodoGrupo on EmpresaGrupo.id_GrupoUsuario = EmpresaGrupo.id_GrupoUsuario " +
                "Where UsuariodoGrupo.id_Usuario = ?"+
                "order by RazaoSocial ", todasEmpresas, id_Usuario);
    }

    public List<Empresa> selectAll_paginado(Pageable p) {
        return db.query("Select Empresa.*, municipio.descricao, uf.id_uf, uf.siglauf, uf.id_pais, pais.siglapais " +
                "from Empresa " +
                "left outer join municipio on Empresa.id_municipio = municipio.id_municipio " +
                "left outer join uf on municipio.id_uf = uf.id_uf " +
                "left outer join pais on uf.id_pais = pais.id_pais " +
                "order by RazaoSocial " +
                "LIMIT ? OFFSET ? ",
                todasEmpresas,
                p.getPageSize(),
                p.getOffset());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM Empresa Where id_Empresa=?",id);
    }

    //mappers
    private RowMapper<Empresa> todasEmpresas = new RowMapper<Empresa>() {
        @Override
        public Empresa mapRow(ResultSet rs, int i) throws SQLException{
            Empresa emp = new Empresa();
            emp.setId_Empresa(rs.getInt("Id_Empresa"));
            emp.setCnpj(rs.getString("Cnpj"));
            emp.setInscricaoEstadual(rs.getString("InscricaoEstadual"));
            emp.setInscricaoMunicipal(rs.getString("InscricaoMunicipal"));
            emp.setNomeFantasia(rs.getString("NomeFantasia"));
            emp.setRazaoSocial(rs.getString("RazaoSocial"));
            emp.setEndereco(rs.getString("Endereco"));
            emp.setNumero(rs.getString("Numero"));
            emp.setBairro(rs.getString("Bairro"));
            emp.setCep(rs.getString("Cep"));
            emp.setId_Municipio(rs.getInt("Id_Municipio"));
            emp.setTelefone1(rs.getString("Telefone1"));
            emp.setTelefone2(rs.getString("Telefone2"));
            emp.setFax(rs.getString("Fax"));
            emp.setEmail(rs.getString("Email"));
            emp.setUrl(rs.getString("Url"));

            emp.setId_Uf(rs.getInt("Id_Uf"));
            emp.setSiglaUf(rs.getString("SiglaUf"));
            emp.setId_Pais(rs.getInt("Id_Pais"));
            emp.setSiglaPais(rs.getString("SiglaPais"));

            return emp;
        }
    };
}


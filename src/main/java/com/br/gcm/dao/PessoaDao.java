package com.br.gcm.dao;

import com.br.gcm.model.Pessoa;
import com.br.gcm.model.filtros.Filtro_Pessoa;
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
 * Date: 26/04/15
 * Time: 22:37
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class PessoaDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;
    @Inject private MunicipioDao municipioDao;

    public void insert(Pessoa pessoa) {
        int id = db.queryForObject("insert into Pessoa (" +
                "CpfCnpj, " +
                "RazaoSocial, " +
                "NomeFantasia, " +
                "InscricaoEstadual, " +
                "InscricaoMunicipal, " +
                "Rg, " +
                "DataNascimento, " +
                "Endereco, " +
                "Numero, " +
                "Cep, " +
                "Bairro, " +
                "id_Municipio, " +
                "Telefone1, " +
                "Telefone2, " +
                "Fax, " +
                "Email,"+
                "Ativo)"+
                "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id_Pessoa",
                Integer.class,
                pessoa.getCpfCnpj(),
                pessoa.getRazaoSocial(),
                pessoa.getNomeFantasia(),
                pessoa.getInscricaoEstadual(),
                pessoa.getInscricaoMunicipal(),
                pessoa.getRg(),
                pessoa.getDataNascimento(),
                pessoa.getEndereco(),
                pessoa.getNumero(),
                pessoa.getCep(),
                pessoa.getBairro(),
                pessoa.getId_Municipio(),
                pessoa.getTelefone1(),
                pessoa.getTelefone2(),
                pessoa.getFax(),
                pessoa.getEmail().toLowerCase(rotinas.getPT_BR()),
                pessoa.getAtivo());

        db.update("Delete from PessoaTipo Where id_Pessoa=?", pessoa.getId_Pessoa());

        pessoa.setId_Pessoa(id);
        if (pessoa.getTipoCliente()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "CLI");
        }
        if (pessoa.getTipoFornecedor()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "FOR");
        }
        if (pessoa.getTipoVendedor()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "VEN");
        }
        if (pessoa.getTipoFuncionario()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "FUN");
        }
        if (pessoa.getTipoTransportador()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "TRA");
        }
        if (pessoa.getTipoConvenio()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "CON");
        }
        if (pessoa.getTipoHospital()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "HOS");
        }
        if (pessoa.getTipoMedico()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "MED");
        }
        if (pessoa.getTipoEnfermeiro()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "ENF");
        }
        if (pessoa.getTipoPaciente()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "PAC");
        }
    }

    public void update(Pessoa pessoa) {
        db.update("update Pessoa set " +
                "RazaoSocial=?, " +
                "NomeFantasia=?, " +
                "InscricaoEstadual=?, " +
                "InscricaoMunicipal=?, " +
                "Rg=?, " +
                "DataNascimento=?, " +
                "Endereco=?, " +
                "Numero=?, " +
                "Cep=?, " +
                "Bairro=?, " +
                "id_Municipio=?, " +
                "Telefone1=?, " +
                "Telefone2=?, " +
                "Fax=?, " +
                "Email=?,"+
                "Ativo=? " +
                "Where id_Pessoa=? ",
                pessoa.getRazaoSocial(),
                pessoa.getNomeFantasia(),
                pessoa.getInscricaoEstadual(),
                pessoa.getInscricaoMunicipal(),
                pessoa.getRg(),
                pessoa.getDataNascimento(),
                pessoa.getEndereco(),
                pessoa.getNumero(),
                pessoa.getCep(),
                pessoa.getBairro(),
                pessoa.getId_Municipio(),
                pessoa.getTelefone1(),
                pessoa.getTelefone2(),
                pessoa.getFax(),
                pessoa.getEmail().toLowerCase(rotinas.getPT_BR()),
                pessoa.getAtivo(),
                pessoa.getId_Pessoa());

        db.update("Delete from PessoaTipo Where id_Pessoa=?", pessoa.getId_Pessoa());

        if (pessoa.getTipoCliente()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "CLI");
        }
        if (pessoa.getTipoFornecedor()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "FOR");
        }
        if (pessoa.getTipoVendedor()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "VEN");
        }
        if (pessoa.getTipoFuncionario()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "FUN");
        }
        if (pessoa.getTipoTransportador()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "TRA");
        }
        if (pessoa.getTipoConvenio()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "CON");
        }
        if (pessoa.getTipoHospital()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "HOS");
        }
        if (pessoa.getTipoMedico()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "MED");
        }
        if (pessoa.getTipoEnfermeiro()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "ENF");
        }
        if (pessoa.getTipoPaciente()==true){
            db.update("Insert into PessoaTipo(id_Pessoa, TipoPessoa) Values(?,?)",
                    pessoa.getId_Pessoa(), "PAC");
        }
    }

    public void InativarById(Integer id) {
        db.update("Update Pessoa Set Ativo=False Where id_Pessoa=?",id);
    }

    public void AtivarById(Integer id) {
        db.update("Update Pessoa Set Ativo=True Where id_Pessoa=?",id);
    }

    public Long count(String tipo) {
        return db.queryForObject("SELECT COUNT(*) FROM Pessoa " +
                "Inner Join PessoaTipo on Pessoa.id_Pessoa = PessoaTipo.id_Pessoa " +
                "Where PessoaTipo.TipoPessoa=? ", Long.class, tipo);
    }

    public Long count(String tipo, Pessoa filtros) {
        List arr = new ArrayList<>();
        String sql = "SELECT COUNT(*) FROM Pessoa " +
                "Inner Join PessoaTipo on Pessoa.id_Pessoa = PessoaTipo.id_Pessoa " +
                "Where PessoaTipo.TipoPessoa=? ";
        arr.add(tipo);

        if (filtros.getId_Pessoa() > 0){
            sql = sql + " And Pessoa.id_Pessoa = ?";
            arr.add(filtros.getId_Pessoa());
        };
        if (filtros.getCpfCnpj() != "" && filtros.getCpfCnpj() != null){
            sql = sql + " And Pessoa.CpfCnpj = ?";
            arr.add(filtros.getCpfCnpj());
        };
        if (filtros.getRazaoSocial() != "" && filtros.getRazaoSocial() != null){
            sql = sql + " And Pessoa.RazaoSocial like ?";
            arr.add("%"+filtros.getRazaoSocial()+"%");
        };
        if (filtros.getAtivo() != null){
            sql = sql + " And Pessoa.Ativo = ?";
            arr.add(filtros.getAtivo());
        };

        return db.queryForObject(sql, Long.class, arr.toArray());
    }

    public Pessoa selectById(Integer id) {
        return db.queryForObject("Select Pessoa.*, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CLI' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoCliente, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FOR' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFornecedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='VEN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoVendedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FUN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFuncionario, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='TRA' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoTransportador, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CON' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoConvenio, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='HOS' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoHospital, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='MED' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoMedico, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='ENF' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoEnfermeiro, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='PAC' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoPaciente " +
                " from Pessoa " +
                " where Pessoa.id_Pessoa=? ", ListaPessoa, id);
    }

    public Pessoa selectByIdSimp(Integer id) {
        return db.queryForObject("Select Pessoa.*, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CLI' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoCliente, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FOR' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFornecedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='VEN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoVendedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FUN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFuncionario, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='TRA' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoTransportador, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CON' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoConvenio, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='HOS' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoHospital, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='MED' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoMedico, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='ENF' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoEnfermeiro, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='PAC' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoPaciente " +
                " from Pessoa " +
                " where Pessoa.id_Pessoa=? ", ListaPessoaSimp, id);
    }

    public Pessoa selectByCpfCnpj(String cpfcnpj) {
        return db.queryForObject("Select Pessoa.*, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CLI' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoCliente, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FOR' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFornecedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='VEN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoVendedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FUN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFuncionario, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='TRA' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoTransportador, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CON' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoConvenio, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='HOS' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoHospital, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='MED' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoMedico, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='ENF' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoEnfermeiro, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='PAC' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoPaciente " +
                " from Pessoa " +
                " where Pessoa.cpfcnpj=? ", ListaPessoa, cpfcnpj);
    }

    public List<Pessoa> selectAll(String tipo, Pessoa filtros) {
        List arr = new ArrayList<>();
        String sql = "Select distinct Pessoa.*, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CLI' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoCliente, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FOR' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFornecedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='VEN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoVendedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FUN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFuncionario, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='TRA' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoTransportador, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CON' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoConvenio, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='HOS' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoHospital, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='MED' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoMedico, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='ENF' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoEnfermeiro, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='PAC' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoPaciente " +
                " from Pessoa " +
                " Inner Join PessoaTipo on Pessoa.id_Pessoa = PessoaTipo.id_Pessoa " +
                " Where PessoaTipo.TipoPessoa=? And Pessoa.Ativo = true ";

        arr.add(tipo);

        if (filtros.getId_Pessoa() > 0){
            sql = sql + " And Pessoa.id_Pessoa = ?";
            arr.add(filtros.getId_Pessoa());
        };
        if (!filtros.getCpfCnpj().trim().equals("") && filtros.getCpfCnpj() != null){
            sql = sql + " And Pessoa.CpfCnpj = ?";
            arr.add(filtros.getCpfCnpj());
        };
        if (!filtros.getRazaoSocial().trim().equals("") && filtros.getRazaoSocial() != null){
            sql = sql + " And Pessoa.RazaoSocial like ?";
            arr.add("%"+filtros.getRazaoSocial()+"%");
        };
        if (filtros.getAtivo() != null){
            sql = sql + " And Pessoa.Ativo = ?";
            arr.add(filtros.getAtivo());
        };
        sql = sql + " Order By RazaoSocial ";

        return db.query(sql, ListaPessoa, arr.toArray());
    }

    public List<Pessoa> selectAll(String tipo, Pessoa filtros, Pageable p) {
        List arr = new ArrayList<>();
        String sql = " Select distinct Pessoa.*, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CLI' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoCliente, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FOR' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFornecedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='VEN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoVendedor, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='FUN' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoFuncionario, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='TRA' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoTransportador, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='CON' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoConvenio, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='HOS' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoHospital, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='MED' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoMedico, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='ENF' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoEnfermeiro, " +
                " case when (select count(*) from pessoatipo where pessoatipo.tipopessoa='PAC' and pessoa.id_pessoa = pessoatipo.id_pessoa)>0 then true else false " +
                " end as TipoPaciente " +
                " from Pessoa " +
                " Inner Join PessoaTipo on Pessoa.id_Pessoa = PessoaTipo.id_Pessoa " +
                " Where PessoaTipo.TipoPessoa=? ";

        arr.add(tipo);

        if (filtros.getId_Pessoa() > 0){
            sql = sql + " And Pessoa.id_Pessoa = ?";
            arr.add(filtros.getId_Pessoa());
        };
        if (filtros.getCpfCnpj() != "" && filtros.getCpfCnpj() != null){
            sql = sql + " And Pessoa.CpfCnpj = ?";
            arr.add(filtros.getCpfCnpj());
        };
        if (filtros.getRazaoSocial() != "" && filtros.getRazaoSocial() != null){
            sql = sql + " And Pessoa.RazaoSocial like ?";
            arr.add("%"+filtros.getRazaoSocial()+"%");
        };
        if (filtros.getAtivo() != null){
            sql = sql + " And Pessoa.Ativo = ?";
            arr.add(filtros.getAtivo());
        };

        sql = sql + " Order By RazaoSocial LIMIT ? OFFSET ? ";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.query(sql,ListaPessoa,arr.toArray());
    }

    //mappers
    private RowMapper<Pessoa> ListaPessoa = new RowMapper<Pessoa>() {
        @Override
        public Pessoa mapRow(ResultSet rs, int i) throws SQLException{
            Pessoa pessoa = new Pessoa();
            pessoa.setId_Pessoa(rs.getInt("Id_Pessoa"));
            pessoa.setCpfCnpj(rs.getString("CpfCnpj"));
            pessoa.setRazaoSocial(rs.getString("RazaoSocial"));
            pessoa.setNomeFantasia(rs.getString("NomeFantasia"));
            pessoa.setInscricaoEstadual(rs.getString("InscricaoEstadual"));
            pessoa.setInscricaoMunicipal(rs.getString("InscricaoMunicipal"));
            pessoa.setRg(rs.getString("Rg"));
            pessoa.setDataNascimento(rs.getDate("DataNascimento"));
            pessoa.setEndereco(rs.getString("Endereco"));
            pessoa.setNumero(rs.getString("Numero"));
            pessoa.setBairro(rs.getString("Bairro"));
            pessoa.setCep(rs.getString("Cep"));
            pessoa.setId_Municipio(rs.getInt("id_Municipio"));
            pessoa.setTelefone1(rs.getString("Telefone1"));
            pessoa.setTelefone2(rs.getString("Telefone2"));
            pessoa.setFax(rs.getString("Fax"));
            pessoa.setEmail(rs.getString("Email"));
            pessoa.setAtivo(rs.getBoolean("Ativo"));

            pessoa.setTipoCliente(rs.getBoolean("TipoCliente"));
            pessoa.setTipoFornecedor(rs.getBoolean("TipoFornecedor"));
            pessoa.setTipoFuncionario(rs.getBoolean("TipoFuncionario"));
            pessoa.setTipoVendedor(rs.getBoolean("TipoVendedor"));
            pessoa.setTipoTransportador(rs.getBoolean("TipoTransportador"));
            pessoa.setTipoConvenio(rs.getBoolean("TipoConvenio"));
            pessoa.setTipoHospital(rs.getBoolean("TipoHospital"));
            pessoa.setTipoMedico(rs.getBoolean("TipoMedico"));
            pessoa.setTipoEnfermeiro(rs.getBoolean("TipoEnfermeiro"));
            pessoa.setTipoPaciente(rs.getBoolean("TipoPaciente"));

            if (pessoa.getId_Municipio() > 0){
                pessoa.setMunicipio(municipioDao.selectById(pessoa.getId_Municipio()));
            }

            return pessoa;
        }
    };

    //mappers
    private RowMapper<Pessoa> ListaPessoaSimp = new RowMapper<Pessoa>() {
        @Override
        public Pessoa mapRow(ResultSet rs, int i) throws SQLException{
            Pessoa pessoa = new Pessoa();
            pessoa.setId_Pessoa(rs.getInt("Id_Pessoa"));
            pessoa.setCpfCnpj(rs.getString("CpfCnpj"));
            pessoa.setRazaoSocial(rs.getString("RazaoSocial"));
            pessoa.setNomeFantasia(rs.getString("NomeFantasia"));
            pessoa.setInscricaoEstadual(rs.getString("InscricaoEstadual"));
            pessoa.setInscricaoMunicipal(rs.getString("InscricaoMunicipal"));
            pessoa.setRg(rs.getString("Rg"));
            pessoa.setDataNascimento(rs.getDate("DataNascimento"));
            pessoa.setEndereco(rs.getString("Endereco"));
            pessoa.setNumero(rs.getString("Numero"));
            pessoa.setBairro(rs.getString("Bairro"));
            pessoa.setCep(rs.getString("Cep"));
            pessoa.setId_Municipio(rs.getInt("id_Municipio"));
            pessoa.setTelefone1(rs.getString("Telefone1"));
            pessoa.setTelefone2(rs.getString("Telefone2"));
            pessoa.setFax(rs.getString("Fax"));
            pessoa.setEmail(rs.getString("Email"));
            pessoa.setAtivo(rs.getBoolean("Ativo"));

            pessoa.setTipoCliente(rs.getBoolean("TipoCliente"));
            pessoa.setTipoFornecedor(rs.getBoolean("TipoFornecedor"));
            pessoa.setTipoFuncionario(rs.getBoolean("TipoFuncionario"));
            pessoa.setTipoVendedor(rs.getBoolean("TipoVendedor"));
            pessoa.setTipoTransportador(rs.getBoolean("TipoTransportador"));
            pessoa.setTipoConvenio(rs.getBoolean("TipoConvenio"));
            pessoa.setTipoHospital(rs.getBoolean("TipoHospital"));
            pessoa.setTipoMedico(rs.getBoolean("TipoMedico"));
            pessoa.setTipoEnfermeiro(rs.getBoolean("TipoEnfermeiro"));
            pessoa.setTipoPaciente(rs.getBoolean("TipoPaciente"));

            if (pessoa.getId_Municipio() > 0){
                pessoa.setMunicipio(municipioDao.selectById(pessoa.getId_Municipio()));
            }

            return pessoa;
        }
    };
}

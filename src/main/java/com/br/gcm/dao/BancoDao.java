package com.br.gcm.dao;

import com.br.gcm.model.Banco;
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
 * Date: 05/05/15
 * Time: 21:43
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class BancoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(Banco banco) {
        db.update("insert into Banco (NumeroBanco, Descricao) values(?,?)",
                banco.getNumeroBanco(),
                banco.getDescricao());
    }

    public void update(Banco banco) {
        db.update("update Banco set NumeroBanco=?, Descricao=? Where id_Banco=?",
                banco.getNumeroBanco(),
                banco.getDescricao(),
                banco.getId_Banco());
    }

    public void deleteById(int id) {
        db.update("Delete from  Banco Where id_Banco=? ", id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM Banco", Long.class);
    }

    public Banco selectById(Integer id) {
        return db.queryForObject("Select * from Banco Where id_Banco=?", listaBanco, id);
    }

    public List<Banco> selectAll() {
        return db.query("Select * from Banco", listaBanco);
    }

    public List<Banco> selectAll_paginado(Pageable p) {
        return db.query("Select * from Banco Order By NumeroBanco " +
                "LIMIT ? OFFSET ? ",
                listaBanco,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<Banco> listaBanco = new RowMapper<Banco>() {
        @Override
        public Banco mapRow(ResultSet rs, int i) throws SQLException{
            Banco banco = new Banco();
            banco.setId_Banco(rs.getInt("Id_Banco"));
            banco.setNumeroBanco(rs.getString("NumeroBanco"));
            banco.setDescricao(rs.getString("Descricao"));

            return banco;
        }
    };
}

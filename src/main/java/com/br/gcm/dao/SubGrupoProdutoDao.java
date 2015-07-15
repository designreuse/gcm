package com.br.gcm.dao;

import com.br.gcm.model.SubGrupoProduto;
import com.br.gcm.model.GrupoProduto;
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
 * Date: 18/04/15
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class SubGrupoProdutoDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    @Inject GrupoProdutoDao grupoProdutoDao;

    public void insert(SubGrupoProduto subGrupoProduto) {
        db.update("insert into SubGrupoProduto (id_GrupoProduto, Descricao) values(?,?)",
                subGrupoProduto.getGrupoProduto().getId_GrupoProduto(),
                subGrupoProduto.getDescricao());
    }

    public void update(SubGrupoProduto subGrupoProduto){
        db.update("update SubGrupoProduto set id_grupoproduto=?, descricao=? where id_SubGrupoProduto = ?",
                subGrupoProduto.getId_GrupoProduto(),
                subGrupoProduto.getGrupoProduto().getDescricao(),
                subGrupoProduto.getId_SubGrupoProduto());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM SubGrupoProduto Where id_SubGrupoProduto=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM SubGrupoProduto", Long.class);
    }

    public SubGrupoProduto selectById(Integer id) {
        return db.queryForObject("Select SubGrupoProduto.*, GrupoProduto.descricao as DescricaoGrupo from SubGrupoProduto " +
                "Inner Join GrupoProduto on SubGrupoProduto.id_GrupoProduto = GrupoProduto.id_GrupoProduto " +
                "Where id_SubGrupoProduto=? ", listaSubGrupoProduto, id);
    }

    public List<SubGrupoProduto> selectAll() {
        return db.query("Select SubGrupoProduto.*, GrupoProduto.descricao as DescricaoGrupo from  SubGrupoProduto " +
                "Inner Join GrupoProduto on SubGrupoProduto.id_GrupoProduto = GrupoProduto.id_GrupoProduto " +
                "Order By GrupoProduto.descricao, SubGrupoProduto.descricao ",
                listaSubGrupoProduto);
    }

    public List<SubGrupoProduto> selectByid_Grupo(int id_GrupoProduto) {
        return db.query("Select SubGrupoProduto.*, GrupoProduto.descricao as DescricaoGrupo from  SubGrupoProduto " +
                "Inner Join GrupoProduto on SubGrupoProduto.id_GrupoProduto = GrupoProduto.id_GrupoProduto " +
                "Where SubGrupoProduto.id_GrupoProduto=?"+
                "Order By GrupoProduto.descricao, SubGrupoProduto.descricao ",
                listaSubGrupoProduto,
                id_GrupoProduto);
    }

    public List<SubGrupoProduto> selectAll_paginado(Pageable p) {
        return db.query("Select SubGrupoProduto.*, GrupoProduto.descricao as DescricaoGrupo from  SubGrupoProduto " +
                "Inner Join GrupoProduto on SubGrupoProduto.id_GrupoProduto = GrupoProduto.id_GrupoProduto " +
                "Order By GrupoProduto.descricao, SubGrupoProduto.descricao " +
                "LIMIT ? OFFSET ? ",
                listaSubGrupoProduto,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<SubGrupoProduto> listaSubGrupoProduto = new RowMapper<SubGrupoProduto>() {
        @Override
        public SubGrupoProduto mapRow(ResultSet rs, int i) throws SQLException{
            SubGrupoProduto subGrupoProduto = new SubGrupoProduto();

            subGrupoProduto.setId_SubGrupoProduto(rs.getInt("Id_SubGrupoProduto"));
            subGrupoProduto.setId_GrupoProduto(rs.getInt("Id_GrupoProduto"));
            subGrupoProduto.setDescricao(rs.getString("Descricao"));
            subGrupoProduto.setDescricaoGrupo(rs.getString("DescricaoGrupo"));

            //subGrupoProduto.setGrupoProduto(grupoProdutoDao.selectById(rs.getInt("Id_GrupoProduto")));

            return subGrupoProduto;
        }
    };
}

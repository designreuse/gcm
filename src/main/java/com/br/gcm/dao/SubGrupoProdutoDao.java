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
import java.util.ArrayList;
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
        int id = db.queryForObject("insert into SubGrupoProduto (id_GrupoProduto, Descricao) values(?,?) RETURNING id_SubGrupoProduto",
                Integer.class,
                subGrupoProduto.getId_GrupoProduto(),
                subGrupoProduto.getDescricao());

        subGrupoProduto.setId_SubGrupoProduto(id);
    }

    public void update(SubGrupoProduto subGrupoProduto){
        db.update("update SubGrupoProduto set id_grupoproduto=?, descricao=? where id_SubGrupoProduto = ?",
                subGrupoProduto.getId_GrupoProduto(),
                subGrupoProduto.getDescricao(),
                subGrupoProduto.getId_SubGrupoProduto());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM SubGrupoProduto Where id_SubGrupoProduto=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM SubGrupoProduto", Long.class);
    }

    public Long count(SubGrupoProduto filtros) {
        List arr = new ArrayList<>();
        String sql = "SELECT COUNT(*) FROM SubGrupoProduto Where 1=1 ";

        if (filtros.getId_SubGrupoProduto() > 0){
            sql = sql + " And Id_SubGrupoProduto = ? ";
            arr.add(filtros.getId_SubGrupoProduto());
        };

        if (filtros.getId_GrupoProduto() > 0){
            sql = sql + " And Id_GrupoProduto = ?";
            arr.add(filtros.getId_GrupoProduto());
        };

        if (filtros.getDescricao() != null && filtros.getDescricao() != ""){
            sql = sql + " And Descricao like ? ";
            arr.add("%"+filtros.getDescricao()+"%");
        };

        return db.queryForObject(sql, Long.class, arr.toArray());
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

    public List<SubGrupoProduto> selectAll_paginado(SubGrupoProduto filtros, Pageable p) {
        List arr = new ArrayList<>();
        String sql =  "Select SubGrupoProduto.*, GrupoProduto.descricao as DescricaoGrupo from  SubGrupoProduto " +
                      " Inner Join GrupoProduto on SubGrupoProduto.id_GrupoProduto = GrupoProduto.id_GrupoProduto Where 1=1 ";

        if (filtros.getId_SubGrupoProduto() > 0){
            sql = sql + " And SubGrupoProduto.Id_SubGrupoProduto = ? ";
            arr.add(filtros.getId_SubGrupoProduto());
        };

        if (filtros.getId_GrupoProduto() > 0){
            sql = sql + " And SubGrupoProduto.Id_GrupoProduto = ? ";
            arr.add(filtros.getId_GrupoProduto());
        };

        if (filtros.getDescricao() != null && filtros.getDescricao() != ""){
            sql = sql + " And SubGrupoProduto.Descricao like ? ";
            arr.add("%"+filtros.getDescricao()+"%");
        };

        sql = sql + " Order By GrupoProduto.descricao, SubGrupoProduto.descricao " +
                " LIMIT ? OFFSET ? ";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.query(sql, listaSubGrupoProduto, arr.toArray());
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

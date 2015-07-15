package com.br.gcm.dao;

import com.br.gcm.model.SituacaoTributariaPISCOFINS;
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
 * Date: 23/04/15
 * Time: 23:31
 * To change this template use File | Settings | File Templates.
 */

@Repository
public class SituacaoTributariaPISCOFINSDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    public void insert(SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS) {
        db.update("insert into SituacaoTributariaPISCOFINS (CodigoSTPISCOFINS, descricao, IsentoAliquotaZero) values(?,?)",
                situacaoTributariaPISCOFINS.getCodigoSTPISCOFINS(),
                situacaoTributariaPISCOFINS.getDescricao(),
                situacaoTributariaPISCOFINS.getIsentoAliquotaZero());
    }

    public void update(SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS) {
        db.update("update SituacaoTributariaPISCOFINS set CodigoSTPISCOFINS=?, descricao=?, IsentoAliquotaZero=? Where id_CSTPISCOFINS=?",
                situacaoTributariaPISCOFINS.getCodigoSTPISCOFINS(),
                situacaoTributariaPISCOFINS.getDescricao(),
                situacaoTributariaPISCOFINS.getIsentoAliquotaZero(),
                situacaoTributariaPISCOFINS.getId_CSTPISCOFINS());
    }

    public void deleteById(Integer id) {
        db.update("DELETE FROM SituacaoTributariaPISCOFINS Where id_CSTPISCOFINS=?",id);
    }

    public Long count() {
        return db.queryForObject("SELECT COUNT(*) FROM SituacaoTributariaPISCOFINS", Long.class);
    }

    public SituacaoTributariaPISCOFINS selectById(Integer id) {
        return db.queryForObject("Select * from SituacaoTributariaPISCOFINS Where Id_CSTPISCOFINS=?", listaSituacaoTributariaPISCOFINS, id);
    }

    public List<SituacaoTributariaPISCOFINS> selectAll() {
        return db.query("Select Id_CSTPISCOFINS, CodigoSTPISCOFINS||' - '||Descricao as Descricao, CodigoSTPISCOFINS, IsentoAliquotaZero from SituacaoTributariaPISCOFINS Order By CodigoSTPISCOFINS", listaSituacaoTributariaPISCOFINS);
    }

    public List<SituacaoTributariaPISCOFINS> selectAll_paginado(Pageable p) {
        return db.query("Select * from  SituacaoTributariaPISCOFINS Order By CodigoSTPISCOFINS " +
                "LIMIT ? OFFSET ? ",
                listaSituacaoTributariaPISCOFINS,
                p.getPageSize(),
                p.getOffset());
    }

    //mappers
    private RowMapper<SituacaoTributariaPISCOFINS> listaSituacaoTributariaPISCOFINS = new RowMapper<SituacaoTributariaPISCOFINS>() {
        @Override
        public SituacaoTributariaPISCOFINS mapRow(ResultSet rs, int i) throws SQLException{
            SituacaoTributariaPISCOFINS situacaoTributariaPISCOFINS = new SituacaoTributariaPISCOFINS();
            situacaoTributariaPISCOFINS.setId_CSTPISCOFINS(rs.getInt("Id_CSTPISCOFINS"));
            situacaoTributariaPISCOFINS.setDescricao(rs.getString("Descricao"));
            situacaoTributariaPISCOFINS.setCodigoSTPISCOFINS(rs.getString("CodigoSTPISCOFINS"));
            situacaoTributariaPISCOFINS.setIsentoAliquotaZero(rs.getBoolean("IsentoAliquotaZero"));

            return situacaoTributariaPISCOFINS;
        }
    };
}

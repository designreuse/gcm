package com.br.gcm.dao;

import com.br.gcm.model.MovimentoFinanceiro;
import com.br.gcm.model.filtros.Filtro_MovimentoFinanceiro;
import com.br.gcm.util.Rotinas;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 04/06/15
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class MovimentoFinanceiroDao {
    @Inject private JdbcTemplate db;
    @Inject private Rotinas rotinas;

    @Inject private PessoaDao pessoaDao;
    @Inject private CentroCustoDao centroCustoDao;
    @Inject private PlanoContasDao planoContasDao;
    @Inject private ContaCorrenteDao contaCorrenteDao;

    public void insert(MovimentoFinanceiro movimentoFinanceiro) {

        int id = db.queryForObject("insert into MovimentoFinanceiro (" +
                "id_Empresa, id_Pessoa, id_CentroCusto, id_PlanoContas, id_ContaCorrente, " +
                "StatusMovimento, TipoMovimento, DataVencimento, " +
                "DataCompetenciaIni, DataCompetenciaFim, " +
                "ValorVencimento, ValorAcrescimos, ValorMulta, ValorDescontos, AliquotaJuros, Observacoes) " +
                "Values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING id_MovimentoFinanceiro ",
                Integer.class,
                movimentoFinanceiro.getId_Empresa(),
                movimentoFinanceiro.getId_Pessoa(),
                movimentoFinanceiro.getId_CentroCusto(),
                movimentoFinanceiro.getId_PlanoContas(),
                movimentoFinanceiro.getId_ContaCorrente(),
                movimentoFinanceiro.getStatusMovimento(),
                movimentoFinanceiro.getTipoMovimento(),
                movimentoFinanceiro.getDataVencimento(),
                movimentoFinanceiro.getDataCompetenciaIni(),
                movimentoFinanceiro.getDataCompetenciaFim(),
                movimentoFinanceiro.getValorVencimento(),
                movimentoFinanceiro.getValorAcrescimos(),
                movimentoFinanceiro.getValorMulta(),
                movimentoFinanceiro.getValorDescontos(),
                movimentoFinanceiro.getAliquotaJuros(),
                movimentoFinanceiro.getObservacoes());

        movimentoFinanceiro.setId_MovimentoFinanceiro(id);
    }

    public void update(MovimentoFinanceiro movimentoFinanceiro) {
        db.update("update MovimentoFinanceiro set " +
                "id_Empresa=?, id_Pessoa=?, id_CentroCusto=?, id_PlanoContas=?, id_ContaCorrente=?, " +
                "StatusMovimento=?, TipoMovimento=?, DataVencimento=?, " +
                "DataCompetenciaIni=?, DataCompetenciaFim=?, ValorDescontos=?," +
                "ValorVencimento=?, ValorAcrescimos=?, ValorMulta=?, AliquotaJuros=?, Observacoes=? " +
                "Where id_MovimentoFinanceiro = ?",
                movimentoFinanceiro.getId_Empresa(),
                movimentoFinanceiro.getId_Pessoa(),
                movimentoFinanceiro.getId_CentroCusto(),
                movimentoFinanceiro.getId_PlanoContas(),
                movimentoFinanceiro.getId_ContaCorrente(),
                movimentoFinanceiro.getStatusMovimento(),
                movimentoFinanceiro.getTipoMovimento(),
                movimentoFinanceiro.getDataVencimento(),
                movimentoFinanceiro.getDataCompetenciaIni(),
                movimentoFinanceiro.getDataCompetenciaFim(),
                movimentoFinanceiro.getValorDescontos(),
                movimentoFinanceiro.getValorVencimento(),
                movimentoFinanceiro.getValorAcrescimos(),
                movimentoFinanceiro.getValorMulta(),
                movimentoFinanceiro.getAliquotaJuros(),
                movimentoFinanceiro.getObservacoes(),
                movimentoFinanceiro.getId_MovimentoFinanceiro());
    }

    public Long count(int id_Empresa) {
        return db.queryForObject("SELECT COUNT(*) FROM MovimentoFinanceiro Where id_Empresa = ?", Long.class, id_Empresa);
    }

    public MovimentoFinanceiro selectById(Integer id) {
        return db.queryForObject("Select MovimentoFinanceiro.*, " +
                " DATE_PART('day', current_timestamp::date) - DATE_PART('day', datavencimento::date) as QtdDias, " +
                " ValorLiqMovFinanceiro(id_MovimentoFinanceiro) as ValorLiquido " +
                "from MovimentoFinanceiro Where id_MovimentoFinanceiro=?", listaMovimentoFinanceiro, id);
    }

    public List<MovimentoFinanceiro> selectAll(Filtro_MovimentoFinanceiro filtros, Pageable p) {
        List arr = new ArrayList<>();
        String sql = " Select MovimentoFinanceiro.*, " +
                " DATE_PART('day', current_timestamp::date) - DATE_PART('day', datavencimento::date) as QtdDias, " +
                " ValorLiqMovFinanceiro(id_MovimentoFinanceiro) as ValorLiquido " +
                " from MovimentoFinanceiro Where 1=1 ";

        if (filtros.getId_MovimentoFinanceiro() > 0){ sql = sql + " And Id_MovimentoFinanceiro = ?"; arr.add(filtros.getId_MovimentoFinanceiro());}
        if (filtros.getId_Empresa() > 0){ sql = sql + " And Id_Empresa = ?"; arr.add(filtros.getId_Empresa());}
        if (filtros.getId_CentroCusto() > 0){ sql = sql + " And Id_CentroCusto = ?"; arr.add(filtros.getId_CentroCusto());}
        if (filtros.getId_PlanoContas() > 0) { sql = sql + " And Id_PlanoContas = ?"; arr.add(filtros.getId_PlanoContas());}
        if (filtros.getId_Pessoa() > 0) {sql = sql + " And Id_Pessoa = ?"; arr.add(filtros.getId_Pessoa());}
        if (filtros.getTipoMovimento() != "") {sql = sql + " And TipoMovimento = ?"; arr.add(filtros.getTipoMovimento());}
        if (filtros.getStatusMovimento() != "") {sql = sql + " And StatusMovimento = ?"; arr.add(filtros.getStatusMovimento());}
        if (filtros.getDataMovimentoIni() != null && filtros.getDataMovimentoIni() != "") { sql = sql + " And DataMovimento >= '"+filtros.getDataMovimentoIni()+"' ";}
        if (filtros.getDataMovimentoFim() != null && filtros.getDataMovimentoFim() != "") { sql = sql + " And DataMovimento <= '"+filtros.getDataMovimentoFim()+"' ";}
        if (filtros.getDataCompetenciaIni() != null && filtros.getDataCompetenciaIni() != "") { sql = sql + " And DataCompetenciaIni >= '"+filtros.getDataCompetenciaIni()+"' "; }
        if (filtros.getDataCompetenciaFim() != null && filtros.getDataCompetenciaFim() != "") { sql = sql + " And DataCompetenciaFim <= '"+filtros.getDataCompetenciaFim()+"' "; }
        if (filtros.getDataVencimentoIni() != null && filtros.getDataVencimentoIni() != "") { sql = sql + " And DataVencimento >= '"+filtros.getDataVencimentoIni()+"'";}
        if (filtros.getDataVencimentoFim() != null && filtros.getDataVencimentoFim() != "") { sql = sql + " And DataVencimento <= '"+filtros.getDataVencimentoFim()+"'";}
        if (filtros.getDataLiquidacaoIni() != null && filtros.getDataLiquidacaoIni() != "") { sql = sql + " And DataLiquidacao >= '"+filtros.getDataLiquidacaoIni()+"'";}
        if (filtros.getDataLiquidacaoFim() != null && filtros.getDataLiquidacaoFim() != "") { sql = sql + " And DataLiquidacao <= '"+filtros.getDataLiquidacaoFim()+"'";}
        if (filtros.getValorVencimentoIni() != null) { sql = sql + " And ValorVencimento >= ?"; arr.add(filtros.getValorVencimentoIni());}
        if (filtros.getValorVencimentoFim() != null) { sql = sql + " And ValorVencimento <= ?"; arr.add(filtros.getValorVencimentoFim());}

        sql = sql + " Order by Id_Empresa, Id_MovimentoFinanceiro desc LIMIT ? OFFSET ? ";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.query(sql, listaMovimentoFinanceiro, arr.toArray());
    }

    private RowMapper<MovimentoFinanceiro> listaMovimentoFinanceiro = new RowMapper<MovimentoFinanceiro>() {
        @Override
        public MovimentoFinanceiro mapRow(ResultSet rs, int i) throws SQLException{
            MovimentoFinanceiro movimentoFinanceiro = new MovimentoFinanceiro();

            movimentoFinanceiro.setId_MovimentoFinanceiro(rs.getInt("Id_MovimentoFinanceiro"));
            movimentoFinanceiro.setId_Empresa(rs.getInt("Id_Empresa"));
            movimentoFinanceiro.setId_Pessoa(rs.getInt("Id_Pessoa"));
            movimentoFinanceiro.setId_CentroCusto(rs.getInt("Id_CentroCusto"));
            movimentoFinanceiro.setId_PlanoContas(rs.getInt("Id_PlanoContas"));
            movimentoFinanceiro.setId_ContaCorrente(rs.getInt("Id_ContaCorrente"));
            movimentoFinanceiro.setStatusMovimento(rs.getString("StatusMovimento"));
            movimentoFinanceiro.setTipoMovimento(rs.getString("TipoMovimento"));
            movimentoFinanceiro.setDataMovimento(rs.getDate("DataMovimento"));
            movimentoFinanceiro.setDataVencimento(rs.getDate("DataVencimento"));
            movimentoFinanceiro.setDataLiquidacao(rs.getDate("DataLiquidacao"));
            movimentoFinanceiro.setDataCancelamento(rs.getDate("DataCancelamento"));
            movimentoFinanceiro.setDataBaixa(rs.getDate("DataBaixa"));
            movimentoFinanceiro.setDataCompetenciaIni(rs.getDate("DataCompetenciaIni"));
            movimentoFinanceiro.setDataCompetenciaFim(rs.getDate("DataCompetenciaFim"));
            movimentoFinanceiro.setValorVencimento(rs.getDouble("ValorVencimento"));
            movimentoFinanceiro.setValorLiquidacao(rs.getDouble("ValorLiquidacao"));
            movimentoFinanceiro.setValorAcrescimos(rs.getDouble("ValorAcrescimos"));
            movimentoFinanceiro.setValorDescontos(rs.getDouble("ValorDescontos"));
            movimentoFinanceiro.setValorMulta(rs.getDouble("ValorMulta"));
            movimentoFinanceiro.setAliquotaJuros(rs.getDouble("AliquotaJuros"));
            movimentoFinanceiro.setObservacoes(rs.getString("Observacoes"));
            movimentoFinanceiro.setQtdDias(rs.getInt("QtdDias"));
            movimentoFinanceiro.setValorLiquido(rs.getDouble("ValorLiquido"));

            movimentoFinanceiro.setCentroCusto(centroCustoDao.selectById(movimentoFinanceiro.getId_CentroCusto()));
            movimentoFinanceiro.setPlanoContas(planoContasDao.selectById(movimentoFinanceiro.getId_PlanoContas()));
            //movimentoFinanceiro.setContaCorrente(contaCorrenteDao.selectById(movimentoFinanceiro.getId_ContaCorrente()));
            movimentoFinanceiro.setPessoa(pessoaDao.selectByIdSimp(movimentoFinanceiro.getId_Pessoa()));

            return movimentoFinanceiro;
        }
    };
}

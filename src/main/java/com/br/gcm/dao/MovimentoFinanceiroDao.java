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
import java.util.Calendar;
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

        int cont = 1;
        while (cont <= movimentoFinanceiro.getQtdLancamentos()){
            List arr = new ArrayList<>();
            String sql = "Insert into MovimentoFinanceiro(";

            sql = sql + "id_Empresa, id_Pessoa, id_CentroCusto, id_PlanoContas, DataVencimento, " +
                        "DataCompetenciaIni, DataCompetenciaFim, ValorVencimento,";
            if (movimentoFinanceiro.getId_ContaCorrente() > 0) {
                sql = sql + " Id_ContaCorrente, ";
            }
            if (movimentoFinanceiro.getValorAcrescimos() != null) {
                sql = sql + " ValorAcrescimos, ";
            }
            if (movimentoFinanceiro.getValorDescontos() != null) {
                sql = sql + " ValorDescontos,";
            }
            if (movimentoFinanceiro.getValorMulta() != null) {
                sql = sql + " ValorMulta, ";
            }
            if (movimentoFinanceiro.getAliquotaJuros() != null) {
                sql = sql + " AliquotaJuros, ";
            }
            if (movimentoFinanceiro.getObservacoes() != null && !movimentoFinanceiro.getObservacoes().equals("")) {
                sql = sql + " Observacoes, ";
            }
            if (movimentoFinanceiro.getDocumentoOrigem() != null) {
                sql = sql + " DocumentoOrigem, ";
            }

            sql = sql + " TipoMovimento, StatusMovimento) Values(?,?,?,?,?,?,?,?,";

            arr.add(movimentoFinanceiro.getId_Empresa());
            arr.add(movimentoFinanceiro.getId_Pessoa());
            arr.add(movimentoFinanceiro.getId_CentroCusto());
            arr.add(movimentoFinanceiro.getId_PlanoContas());
            arr.add(movimentoFinanceiro.getDataVencimento());
            arr.add(movimentoFinanceiro.getDataCompetenciaIni());
            arr.add(movimentoFinanceiro.getDataCompetenciaFim());
            arr.add(movimentoFinanceiro.getValorVencimento());

            if (movimentoFinanceiro.getId_ContaCorrente() > 0) {
                sql = sql + "?, ";
                arr.add(movimentoFinanceiro.getId_ContaCorrente());
            }
            if (movimentoFinanceiro.getValorAcrescimos() != null) {
                sql = sql + "?, ";
                arr.add(movimentoFinanceiro.getValorAcrescimos());
            }
            if (movimentoFinanceiro.getValorDescontos() != null) {
                sql = sql + "?, ";
                arr.add(movimentoFinanceiro.getValorDescontos());
            }
            if (movimentoFinanceiro.getValorMulta() != null) {
                sql = sql + "?, ";
                arr.add(movimentoFinanceiro.getValorMulta());
            }
            if (movimentoFinanceiro.getAliquotaJuros() != null) {
                sql = sql + "?, ";
                arr.add(movimentoFinanceiro.getAliquotaJuros());
            }
            if (movimentoFinanceiro.getObservacoes() != null && !movimentoFinanceiro.getObservacoes().equals("")) {
                sql = sql + "?, ";
                arr.add(movimentoFinanceiro.getObservacoes());
            }
            if (movimentoFinanceiro.getDocumentoOrigem() != null) {
                sql = sql + " ?, ";
                arr.add(movimentoFinanceiro.getDocumentoOrigem());
            }

            sql = sql + "?,?) RETURNING id_MovimentoFinanceiro ";
            arr.add(movimentoFinanceiro.getTipoMovimento());
            arr.add(movimentoFinanceiro.getStatusMovimento());


            int id = db.queryForObject(sql, Integer.class, arr.toArray());
            movimentoFinanceiro.setId_MovimentoFinanceiro(id);

            cont++;
            if (cont <= movimentoFinanceiro.getQtdLancamentos()){
                Calendar vencimento = Calendar.getInstance();
                vencimento.setTime(movimentoFinanceiro.getDataVencimento());
                if (movimentoFinanceiro.getPeriodicidade().equals("M")){
                    vencimento.set(Calendar.MONTH, vencimento.get(Calendar.MONTH) + 1);
                } else {
                    vencimento.set(Calendar.YEAR, vencimento.get(Calendar.YEAR) + 1);
                };
                movimentoFinanceiro.setDataVencimento(vencimento.getTime());

                Calendar compini = Calendar.getInstance();
                compini.setTime(movimentoFinanceiro.getDataCompetenciaIni());
                if (movimentoFinanceiro.getPeriodicidade().equals("M")){
                    compini.set(Calendar.MONTH, compini.get(Calendar.MONTH) + 1);
                } else {
                    compini.set(Calendar.YEAR, compini.get(Calendar.YEAR) + 1);
                };
                movimentoFinanceiro.setDataCompetenciaIni(compini.getTime());

                Calendar compfim = Calendar.getInstance();
                compfim.setTime(movimentoFinanceiro.getDataCompetenciaFim());
                if (movimentoFinanceiro.getPeriodicidade().equals("M")){
                    compfim.set(Calendar.MONTH, compfim.get(Calendar.MONTH) + 1);
                } else {
                    compfim.set(Calendar.YEAR, compfim.get(Calendar.YEAR) + 1);
                };
                movimentoFinanceiro.setDataCompetenciaFim(compfim.getTime());
            };
        };
    }

    public void update(MovimentoFinanceiro movimentoFinanceiro) {
        List arr = new ArrayList<>();
        String sql = "update MovimentoFinanceiro set";

        if (movimentoFinanceiro.getId_Empresa() > 0){
            sql = sql + " Id_Empresa = ?, ";
            arr.add(movimentoFinanceiro.getId_Empresa());
        }
        if (movimentoFinanceiro.getId_Pessoa() > 0) {
            sql = sql + " Id_Pessoa = ?, ";
            arr.add(movimentoFinanceiro.getId_Pessoa());
        }
        if (movimentoFinanceiro.getId_CentroCusto() > 0){
            sql = sql + " Id_CentroCusto = ?, ";
            arr.add(movimentoFinanceiro.getId_CentroCusto());
        }
        if (movimentoFinanceiro.getId_PlanoContas() > 0) {
            sql = sql + " Id_PlanoContas = ?, ";
            arr.add(movimentoFinanceiro.getId_PlanoContas());
        }
        if (movimentoFinanceiro.getId_ContaCorrente() > 0) {
            sql = sql + " Id_ContaCorrente = ?, ";
            arr.add(movimentoFinanceiro.getId_ContaCorrente());
        }
        if (movimentoFinanceiro.getStatusMovimento() != null && !movimentoFinanceiro.getStatusMovimento().equals("")) {
            sql = sql + " StatusMovimento = ?, ";
            arr.add(movimentoFinanceiro.getStatusMovimento());
        }
        if (movimentoFinanceiro.getDataCompetenciaIni() != null && !movimentoFinanceiro.getDataCompetenciaIni().equals("")) {
            sql = sql + " DataCompetenciaIni = ?, ";
            arr.add(movimentoFinanceiro.getDataCompetenciaIni());
        }
        if (movimentoFinanceiro.getDataCompetenciaFim() != null && !movimentoFinanceiro.getDataCompetenciaFim().equals("")) {
            sql = sql + " DataCompetenciaFim = ?, ";
            arr.add(movimentoFinanceiro.getDataCompetenciaFim());
        }
        if (movimentoFinanceiro.getDataVencimento() != null && !movimentoFinanceiro.getDataVencimento().equals("")) {
            sql = sql + " DataVencimento = ?, ";
            arr.add(movimentoFinanceiro.getDataVencimento());
        }
        if (movimentoFinanceiro.getValorVencimento() != null) {
            sql = sql + " ValorVencimento = ?, ";
            arr.add(movimentoFinanceiro.getValorVencimento());
        }
        if (movimentoFinanceiro.getValorAcrescimos() != null) {
            sql = sql + " ValorAcrescimos = ?, ";
            arr.add(movimentoFinanceiro.getValorAcrescimos());
        }
        if (movimentoFinanceiro.getValorDescontos() != null) {
            sql = sql + " ValorDescontos = ?, ";
            arr.add(movimentoFinanceiro.getValorDescontos());
        }
        if (movimentoFinanceiro.getValorMulta() != null) {
            sql = sql + " ValorMulta = ?, ";
            arr.add(movimentoFinanceiro.getValorMulta());
        }
        if (movimentoFinanceiro.getAliquotaJuros() != null) {
            sql = sql + " AliquotaJuros = ?, ";
            arr.add(movimentoFinanceiro.getAliquotaJuros());
        }
        if (movimentoFinanceiro.getObservacoes() != null && !movimentoFinanceiro.getObservacoes().equals("")) {
            sql = sql + " Observacoes = ?, ";
            arr.add(movimentoFinanceiro.getObservacoes());
        }
        if (movimentoFinanceiro.getMotivoCancelamento() != null && !movimentoFinanceiro.getMotivoCancelamento().equals("")) {
            sql = sql + " MotivoCancelamento = ?, ";
            arr.add(movimentoFinanceiro.getMotivoCancelamento());
        }
        if (movimentoFinanceiro.getDocumentoOrigem() != null) {
            sql = sql + " DocumentoOrigem = ?, ";
            arr.add(movimentoFinanceiro.getDocumentoOrigem());
        }
        sql = sql + " TipoMovimento = TipoMovimento ";
        sql = sql + " Where id_MovimentoFinanceiro = ? ";
        arr.add(movimentoFinanceiro.getId_MovimentoFinanceiro());

        db.update(sql, arr.toArray());
    }

    public void liquidar(MovimentoFinanceiro movimentoFinanceiro) {

        String Conc;
        if (movimentoFinanceiro.getConciliado() == true){
            Conc = "S";
        }else{
            Conc = "N";
        };

        String sql = "Select liquidarmovimentofinanceiro("+movimentoFinanceiro.getId_MovimentoFinanceiro()+","+
                     "'L',"+
                     "'"+Conc+"',"+
                     "'"+movimentoFinanceiro.getDataLiquidacao().toString()+"',"+
                     movimentoFinanceiro.getValorLiquido()+")";

        db.queryForObject(sql, Long.class);
    };

    public void estornar(MovimentoFinanceiro movimentoFinanceiro) {

        String Conc;
        if (movimentoFinanceiro.getConciliado() == true){
            Conc = "S";
        }else{
            Conc = "N";
        };

        String sql = "Select liquidarmovimentofinanceiro("+movimentoFinanceiro.getId_MovimentoFinanceiro()+","+
                "'E',"+
                "'"+Conc+"',"+
                "'"+movimentoFinanceiro.getDataLiquidacao().toString()+"',"+
                movimentoFinanceiro.getValorLiquido()+")";

        db.queryForObject(sql, Long.class);
    };

    public Long count(int id_Empresa, Pageable p) {
        return db.queryForObject("SELECT COUNT(*) FROM MovimentoFinanceiro Where id_Empresa = ? LIMIT ? OFFSET ?", Long.class, id_Empresa, p.getPageSize(), p.getOffset());
    }

    public Long count(Filtro_MovimentoFinanceiro filtros, Pageable p) {
        List arr = new ArrayList<>();
        String sql = " Select count(*) from MovimentoFinanceiro Where 1=1";

        if (filtros.getId_MovimentoFinanceiro() > 0){ sql = sql + " And Id_MovimentoFinanceiro = ?"; arr.add(filtros.getId_MovimentoFinanceiro());}
        if (filtros.getId_Empresa() > 0){ sql = sql + " And Id_Empresa = ?"; arr.add(filtros.getId_Empresa());}
        if (filtros.getId_CentroCusto() > 0){ sql = sql + " And Id_CentroCusto = ?"; arr.add(filtros.getId_CentroCusto());}
        if (filtros.getId_PlanoContas() > 0) { sql = sql + " And Id_PlanoContas = ?"; arr.add(filtros.getId_PlanoContas());}
        if (filtros.getId_Pessoa() > 0) {sql = sql + " And MovimentoFinanceiro.Id_Pessoa = ?"; arr.add(filtros.getId_Pessoa());}
        if (filtros.getTipoMovimento() != null && !filtros.getTipoMovimento().equals("")) {sql = sql + " And TipoMovimento = ?"; arr.add(filtros.getTipoMovimento());}
        if (filtros.getStatusMovimento() != null && !filtros.getStatusMovimento().equals("")) {sql = sql + " And StatusMovimento = ?"; arr.add(filtros.getStatusMovimento());}
        if (filtros.getDataMovimentoIni() != null && !filtros.getDataMovimentoIni().equals("")) { sql = sql + " And DataMovimento >= '"+filtros.getDataMovimentoIni()+"' ";}
        if (filtros.getDataMovimentoFim() != null && !filtros.getDataMovimentoFim().equals("")) { sql = sql + " And DataMovimento <= '"+filtros.getDataMovimentoFim()+"' ";}
        if (filtros.getDataCompetenciaIni() != null && !filtros.getDataCompetenciaIni().equals("")) { sql = sql + " And DataCompetenciaIni >= '"+filtros.getDataCompetenciaIni()+"' "; }
        if (filtros.getDataCompetenciaFim() != null && !filtros.getDataCompetenciaFim().equals("")) { sql = sql + " And DataCompetenciaFim <= '"+filtros.getDataCompetenciaFim()+"' "; }
        if (filtros.getDataVencimentoIni() != null && !filtros.getDataVencimentoIni().equals("")) { sql = sql + " And DataVencimento >= '"+filtros.getDataVencimentoIni()+"'";}
        if (filtros.getDataVencimentoFim() != null && !filtros.getDataVencimentoFim().equals("")) { sql = sql + " And DataVencimento <= '"+filtros.getDataVencimentoFim()+"'";}
        if (filtros.getDataLiquidacaoIni() != null && !filtros.getDataLiquidacaoIni().equals("")) { sql = sql + " And DataLiquidacao >= '"+filtros.getDataLiquidacaoIni()+"'";}
        if (filtros.getDataLiquidacaoFim() != null && !filtros.getDataLiquidacaoFim().equals("")) { sql = sql + " And DataLiquidacao <= '"+filtros.getDataLiquidacaoFim()+"'";}
        if (filtros.getValorVencimentoIni() != null) { sql = sql + " And ValorVencimento >= ?"; arr.add(filtros.getValorVencimentoIni());}
        if (filtros.getValorVencimentoFim() != null) { sql = sql + " And ValorVencimento <= ?"; arr.add(filtros.getValorVencimentoFim());}

        sql = sql + " LIMIT ? OFFSET ? ";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.queryForObject(sql, Long.class, arr.toArray());
    }

    public MovimentoFinanceiro selectById(Integer id) {
        return db.queryForObject("Select MovimentoFinanceiro.*, Pessoa.RazaoSocial, Empresa.RazaoSocial as RazaoSocialEmpresa, " +
                " PlanoContas.CodigoConta, PlanoContas.Descricao as DescricaoConta, " +
                " 0 as QtdDias, " +
                " ValorLiqMovFinanceiro(id_MovimentoFinanceiro) as ValorLiquido " +
                "from MovimentoFinanceiro " +
                "Inner Join Pessoa on MovimentoFinanceiro.id_Pessoa = Pessoa.id_Pessoa " +
                "Inner Join Empresa on MovimentoFinanceiro.id_Empresa = Empresa.id_Empresa "+
                " Inner Join PlanoContas on MovimentoFinanceiro.id_PlanoContas = PlanoContas.id_PlanoContas "+
                "Where id_MovimentoFinanceiro=?", listaMovimentoFinanceiro, id);
    }

    public List<MovimentoFinanceiro> selectAll(Filtro_MovimentoFinanceiro filtros, Pageable p) {
        List arr = new ArrayList<>();
        String sql = " Select MovimentoFinanceiro.*, Pessoa.RazaoSocial, Empresa.RazaoSocial as RazaoSocialEmpresa, " +
                " PlanoContas.CodigoConta, PlanoContas.Descricao as DescricaoConta, " +
                " 0 as QtdDias, " +
                " ValorLiqMovFinanceiro(id_MovimentoFinanceiro) as ValorLiquido " +
                " from MovimentoFinanceiro " +
                " Inner Join Pessoa on MovimentoFinanceiro.id_Pessoa = Pessoa.id_Pessoa " +
                " Inner Join Empresa on MovimentoFinanceiro.id_Empresa = Empresa.id_Empresa "+
                " Inner Join PlanoContas on MovimentoFinanceiro.id_PlanoContas = PlanoContas.id_PlanoContas "+
                " Where 1=1 ";

        if (filtros.getId_MovimentoFinanceiro() > 0){
            sql = sql + " And Id_MovimentoFinanceiro = ?";
            arr.add(filtros.getId_MovimentoFinanceiro());
        }
        if (filtros.getId_Empresa() > 0){
            sql = sql + " And MovimentoFinanceiro.Id_Empresa = ?";
            arr.add(filtros.getId_Empresa());
        }
        if (filtros.getId_CentroCusto() > 0){
            sql = sql + " And Id_CentroCusto = ?";
            arr.add(filtros.getId_CentroCusto());
        }
        if (filtros.getId_PlanoContas() > 0) {
            sql = sql + " And MovimentoFinanceiro.Id_PlanoContas = ?";
            arr.add(filtros.getId_PlanoContas());
        }
        if (filtros.getId_Pessoa() > 0) {
            sql = sql + " And MovimentoFinanceiro.Id_Pessoa = ?";
            arr.add(filtros.getId_Pessoa());
        }
        if (filtros.getTipoMovimento() != null && !filtros.getTipoMovimento().equals("")) {
            sql = sql + " And TipoMovimento = ?";
            arr.add(filtros.getTipoMovimento());
        }
        if (filtros.getStatusMovimento() != null && !filtros.getStatusMovimento().equals("")) {
            sql = sql + " And StatusMovimento = ?";
            arr.add(filtros.getStatusMovimento());
        }
        if (filtros.getDataMovimentoIni() != null && !filtros.getDataMovimentoIni().equals("")) {
            sql = sql + " And DataMovimento >= '"+filtros.getDataMovimentoIni()+"' ";
        }
        if (filtros.getDataMovimentoFim() != null && !filtros.getDataMovimentoFim().equals("")) {
            sql = sql + " And DataMovimento <= '"+filtros.getDataMovimentoFim()+"' ";
        }
        if (filtros.getDataCompetenciaIni() != null && !filtros.getDataCompetenciaIni().equals("")) {
            sql = sql + " And DataCompetenciaIni >= '"+filtros.getDataCompetenciaIni()+"' ";
        }
        if (filtros.getDataCompetenciaFim() != null && !filtros.getDataCompetenciaFim().equals("")) {
            sql = sql + " And DataCompetenciaFim <= '"+filtros.getDataCompetenciaFim()+"' ";
        }
        if (filtros.getDataVencimentoIni() != null && !filtros.getDataVencimentoIni().equals("")) {
            sql = sql + " And DataVencimento >= '"+filtros.getDataVencimentoIni()+"'";
        }
        if (filtros.getDataVencimentoFim() != null && !filtros.getDataVencimentoFim().equals("")) {
            sql = sql + " And DataVencimento <= '"+filtros.getDataVencimentoFim()+"'";
        }
        if (filtros.getDataLiquidacaoIni() != null && !filtros.getDataLiquidacaoIni().equals("")) {
            sql = sql + " And DataLiquidacao >= '"+filtros.getDataLiquidacaoIni()+"'";
        }
        if (filtros.getDataLiquidacaoFim() != null && !filtros.getDataLiquidacaoFim().equals("")) {
            sql = sql + " And DataLiquidacao <= '"+filtros.getDataLiquidacaoFim()+"'";
        }
        if (filtros.getValorVencimentoIni() != null && !filtros.getValorVencimentoIni().equals(0)) {
            sql = sql + " And ValorVencimento >= ?";
            arr.add(filtros.getValorVencimentoIni());
        }
        if (filtros.getValorVencimentoFim() != null && !filtros.getValorVencimentoFim().equals(0)) {
            sql = sql + " And ValorVencimento <= ?";
            arr.add(filtros.getValorVencimentoFim());
        }

        sql = sql + " Order by Id_Empresa, Id_MovimentoFinanceiro desc LIMIT ? OFFSET ? ";
        arr.add(p.getPageSize());
        arr.add(p.getOffset());

        return db.query(sql, listaMovimentoFinanceiro, arr.toArray());
    }

    public List<MovimentoFinanceiro> selectAll(Filtro_MovimentoFinanceiro filtros) {
        List arr = new ArrayList<>();
        String sql = " Select MovimentoFinanceiro.*, Pessoa.RazaoSocial, Empresa.RazaoSocial as RazaoSocialEmpresa, " +
                " PlanoContas.CodigoConta, PlanoContas.Descricao as DescricaoConta, " +
                " 0 as QtdDias, " +
                " ValorLiqMovFinanceiro(id_MovimentoFinanceiro) as ValorLiquido " +
                " from MovimentoFinanceiro " +
                " Inner Join Pessoa on MovimentoFinanceiro.id_Pessoa = Pessoa.id_Pessoa " +
                " Inner Join Empresa on MovimentoFinanceiro.id_Empresa = Empresa.id_Empresa "+
                " Inner Join PlanoContas on MovimentoFinanceiro.id_PlanoContas = PlanoContas.id_PlanoContas "+
                " Where 1=1 ";

        if (filtros.getId_MovimentoFinanceiro() > 0){
            sql = sql + " And Id_MovimentoFinanceiro = ?";
            arr.add(filtros.getId_MovimentoFinanceiro());
        }
        if (filtros.getId_Empresa() > 0){
            sql = sql + " And MovimentoFinanceiro.Id_Empresa = ?";
            arr.add(filtros.getId_Empresa());
        }
        if (filtros.getId_CentroCusto() > 0){
            sql = sql + " And Id_CentroCusto = ?";
            arr.add(filtros.getId_CentroCusto());
        }
        if (filtros.getId_PlanoContas() > 0) {
            sql = sql + " And MovimentoFinanceiro.Id_PlanoContas = ?";
            arr.add(filtros.getId_PlanoContas());
        }
        if (filtros.getId_Pessoa() > 0) {
            sql = sql + " And MovimentoFinanceiro.Id_Pessoa = ?";
            arr.add(filtros.getId_Pessoa());
        }
        if (filtros.getTipoMovimento() != null && !filtros.getTipoMovimento().equals("")) {
            sql = sql + " And TipoMovimento = ?";
            arr.add(filtros.getTipoMovimento());
        }
        if (filtros.getStatusMovimento() != null && !filtros.getStatusMovimento().equals("")) {
            sql = sql + " And StatusMovimento = ?";
            arr.add(filtros.getStatusMovimento());
        }
        if (filtros.getDataMovimentoIni() != null && !filtros.getDataMovimentoIni().equals("")) {
            sql = sql + " And DataMovimento >= '"+filtros.getDataMovimentoIni()+"' ";
        }
        if (filtros.getDataMovimentoFim() != null && !filtros.getDataMovimentoFim().equals("")) {
            sql = sql + " And DataMovimento <= '"+filtros.getDataMovimentoFim()+"' ";
        }
        if (filtros.getDataCompetenciaIni() != null && !filtros.getDataCompetenciaIni().equals("")) {
            sql = sql + " And DataCompetenciaIni >= '"+filtros.getDataCompetenciaIni()+"' ";
        }
        if (filtros.getDataCompetenciaFim() != null && !filtros.getDataCompetenciaFim().equals("")) {
            sql = sql + " And DataCompetenciaFim <= '"+filtros.getDataCompetenciaFim()+"' ";
        }
        if (filtros.getDataVencimentoIni() != null && !filtros.getDataVencimentoIni().equals("")) {
            sql = sql + " And DataVencimento >= '"+filtros.getDataVencimentoIni()+"'";
        }
        if (filtros.getDataVencimentoFim() != null && !filtros.getDataVencimentoFim().equals("")) {
            sql = sql + " And DataVencimento <= '"+filtros.getDataVencimentoFim()+"'";
        }
        if (filtros.getDataLiquidacaoIni() != null && !filtros.getDataLiquidacaoIni().equals("")) {
            sql = sql + " And DataLiquidacao >= '"+filtros.getDataLiquidacaoIni()+"'";
        }
        if (filtros.getDataLiquidacaoFim() != null && !filtros.getDataLiquidacaoFim().equals("")) {
            sql = sql + " And DataLiquidacao <= '"+filtros.getDataLiquidacaoFim()+"'";
        }
        if (filtros.getValorVencimentoIni() != null && !filtros.getValorVencimentoIni().equals(0)) {
            sql = sql + " And ValorVencimento >= ?";
            arr.add(filtros.getValorVencimentoIni());
        }
        if (filtros.getValorVencimentoFim() != null && !filtros.getValorVencimentoFim().equals(0)) {
            sql = sql + " And ValorVencimento <= ?";
            arr.add(filtros.getValorVencimentoFim());
        }

        sql = sql + " Order by Id_Empresa, Id_MovimentoFinanceiro desc ";

        return db.query(sql, listaMovimentoFinanceiro, arr.toArray());
    }

    private RowMapper<MovimentoFinanceiro> listaMovimentoFinanceiro = new RowMapper<MovimentoFinanceiro>() {
        @Override
        public MovimentoFinanceiro mapRow(ResultSet rs, int i) throws SQLException{
            MovimentoFinanceiro movimentoFinanceiro = new MovimentoFinanceiro();

            movimentoFinanceiro.setId_MovimentoFinanceiro(rs.getInt("Id_MovimentoFinanceiro"));
            movimentoFinanceiro.setId_Empresa(rs.getInt("Id_Empresa"));
            movimentoFinanceiro.setId_Pessoa(rs.getInt("Id_Pessoa"));
            movimentoFinanceiro.setRazaoSocial(rs.getString("RazaoSocial"));
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
            movimentoFinanceiro.setDocumentoOrigem(rs.getDouble("DocumentoOrigem"));
            movimentoFinanceiro.setMotivoCancelamento(rs.getString("MotivoCancelamento"));
            movimentoFinanceiro.setRazaoSocialEmpresa(rs.getString("RazaoSocialEmpresa"));
            movimentoFinanceiro.setCodigoConta(rs.getString("CodigoConta"));
            movimentoFinanceiro.setDescricaoConta(rs.getString("DescricaoConta"));

            //movimentoFinanceiro.setCentroCusto(centroCustoDao.selectById(movimentoFinanceiro.getId_CentroCusto()));
            //movimentoFinanceiro.setPlanoContas(planoContasDao.selectById(movimentoFinanceiro.getId_PlanoContas()));
            //movimentoFinanceiro.setContaCorrente(contaCorrenteDao.selectById(movimentoFinanceiro.getId_ContaCorrente()));
            //movimentoFinanceiro.setPessoa(pessoaDao.selectByIdSimp(movimentoFinanceiro.getId_Pessoa()));

            return movimentoFinanceiro;
        }
    };
}

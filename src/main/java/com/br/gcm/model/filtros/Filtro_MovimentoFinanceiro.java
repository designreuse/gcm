package com.br.gcm.model.filtros;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 04/06/15
 * Time: 15:36
 * To change this template use File | Settings | File Templates.
 */
public class Filtro_MovimentoFinanceiro {
    private int id_Empresa;
    private int id_Pessoa;
    private int id_CentroCusto;
    private int id_PlanoContas;
    private int id_ContaCorrente;
    private int id_MovimentoFinanceiro;
    private String StatusMovimento;
    private String TipoMovimento;
    private String DataPrevisaoIni;
    private String DataPrevisaoFim;
    private String DataMovimentoIni;
    private String DataMovimentoFim;
    private String DataVencimentoIni;
    private String DataVencimentoFim;
    private String DataLiquidacaoIni;
    private String DataLiquidacaoFim;
    private String DataCancelamentoIni;
    private String DataCancelamentoFim;
    private String DataBaixaIni;
    private String DataBaixaFim;
    private String DataCompetenciaIni;
    private String DataCompetenciaFim;
    private Number ValorVencimentoIni;
    private Number ValorVencimentoFim;
    private Number ValorLiquidacaoIni;
    private Number ValorLiquidacaoFim;

    public int getId_MovimentoFinanceiro() {
        return id_MovimentoFinanceiro;
    }

    public void setId_MovimentoFinanceiro(int id_MovimentoFinanceiro) {
        this.id_MovimentoFinanceiro = id_MovimentoFinanceiro;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public int getId_Pessoa() {
        return id_Pessoa;
    }

    public void setId_Pessoa(int id_Pessoa) {
        this.id_Pessoa = id_Pessoa;
    }

    public int getId_CentroCusto() {
        return id_CentroCusto;
    }

    public void setId_CentroCusto(int id_CentroCusto) {
        this.id_CentroCusto = id_CentroCusto;
    }

    public int getId_PlanoContas() {
        return id_PlanoContas;
    }

    public void setId_PlanoContas(int id_PlanoContas) {
        this.id_PlanoContas = id_PlanoContas;
    }

    public int getId_ContaCorrente() {
        return id_ContaCorrente;
    }

    public void setId_ContaCorrente(int id_ContaCorrente) {
        this.id_ContaCorrente = id_ContaCorrente;
    }

    public String getStatusMovimento() {
        return StatusMovimento;
    }

    public void setStatusMovimento(String statusMovimento) {
        StatusMovimento = statusMovimento;
    }

    public String getTipoMovimento() {
        return TipoMovimento;
    }

    public void setTipoMovimento(String tipoMovimento) {
        TipoMovimento = tipoMovimento;
    }

    public String getDataPrevisaoIni() {
        return DataPrevisaoIni;
    }

    public void setDataPrevisaoIni(String dataPrevisaoIni) {
        DataPrevisaoIni = dataPrevisaoIni;
    }

    public String getDataPrevisaoFim() {
        return DataPrevisaoFim;
    }

    public void setDataPrevisaoFim(String dataPrevisaoFim) {
        DataPrevisaoFim = dataPrevisaoFim;
    }

    public String getDataMovimentoIni() {
        return DataMovimentoIni;
    }

    public void setDataMovimentoIni(String dataMovimentoIni) {
        DataMovimentoIni = dataMovimentoIni;
    }

    public String getDataMovimentoFim() {
        return DataMovimentoFim;
    }

    public void setDataMovimentoFim(String dataMovimentoFim) {
        DataMovimentoFim = dataMovimentoFim;
    }

    public String getDataVencimentoIni() {
        return DataVencimentoIni;
    }

    public void setDataVencimentoIni(String dataVencimentoIni) {
        DataVencimentoIni = dataVencimentoIni;
    }

    public String getDataVencimentoFim() {
        return DataVencimentoFim;
    }

    public void setDataVencimentoFim(String dataVencimentoFim) {
        DataVencimentoFim = dataVencimentoFim;
    }

    public String getDataLiquidacaoIni() {
        return DataLiquidacaoIni;
    }

    public void setDataLiquidacaoIni(String dataLiquidacaoIni) {
        DataLiquidacaoIni = dataLiquidacaoIni;
    }

    public String getDataLiquidacaoFim() {
        return DataLiquidacaoFim;
    }

    public void setDataLiquidacaoFim(String dataLiquidacaoFim) {
        DataLiquidacaoFim = dataLiquidacaoFim;
    }

    public String getDataCancelamentoIni() {
        return DataCancelamentoIni;
    }

    public void setDataCancelamentoIni(String dataCancelamentoIni) {
        DataCancelamentoIni = dataCancelamentoIni;
    }

    public String getDataCancelamentoFim() {
        return DataCancelamentoFim;
    }

    public void setDataCancelamentoFim(String dataCancelamentoFim) {
        DataCancelamentoFim = dataCancelamentoFim;
    }

    public String getDataBaixaIni() {
        return DataBaixaIni;
    }

    public void setDataBaixaIni(String dataBaixaIni) {
        DataBaixaIni = dataBaixaIni;
    }

    public String getDataBaixaFim() {
        return DataBaixaFim;
    }

    public void setDataBaixaFim(String dataBaixaFim) {
        DataBaixaFim = dataBaixaFim;
    }

    public String getDataCompetenciaIni() {
        return DataCompetenciaIni;
    }

    public void setDataCompetenciaIni(String dataCompetenciaIni) {
        DataCompetenciaIni = dataCompetenciaIni;
    }

    public String getDataCompetenciaFim() {
        return DataCompetenciaFim;
    }

    public void setDataCompetenciaFim(String dataCompetenciaFim) {
        DataCompetenciaFim = dataCompetenciaFim;
    }

    public Number getValorVencimentoIni() {
        return ValorVencimentoIni;
    }

    public void setValorVencimentoIni(Number valorVencimentoIni) {
        ValorVencimentoIni = valorVencimentoIni;
    }

    public Number getValorVencimentoFim() {
        return ValorVencimentoFim;
    }

    public void setValorVencimentoFim(Number valorVencimentoFim) {
        ValorVencimentoFim = valorVencimentoFim;
    }

    public Number getValorLiquidacaoIni() {
        return ValorLiquidacaoIni;
    }

    public void setValorLiquidacaoIni(Number valorLiquidacaoIni) {
        ValorLiquidacaoIni = valorLiquidacaoIni;
    }

    public Number getValorLiquidacaoFim() {
        return ValorLiquidacaoFim;
    }

    public void setValorLiquidacaoFim(Number valorLiquidacaoFim) {
        ValorLiquidacaoFim = valorLiquidacaoFim;
    }


}

package com.br.gcm.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 04/06/15
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class MovimentoFinanceiro {
    private int id_MovimentoFinanceiro;
    private int id_Empresa;
    private int id_Pessoa;
    private int id_CentroCusto;
    private int id_PlanoContas;
    private int id_ContaCorrente;
    private String StatusMovimento;
    private String TipoMovimento;
    private Date DataPrevisao;
    private Date DataMovimento;
    private Date DataVencimento;
    private Date DataLiquidacao;
    private Date DataCancelamento;
    private Date DataBaixa;
    private Date DataCompetenciaIni;
    private Date DataCompetenciaFim;
    private Number ValorVencimento;
    private Number ValorLiquidacao;
    private Number ValorAcrescimos;
    private Number ValorMulta;
    private Number  AliquotaJuros;
    private String Observacoes;
    private int QtdDias;
    private Number ValorDescontos;
    private Number ValorLiquido;

    private CentroCusto centroCusto;
    private PlanoContas planoContas;
    private ContaCorrente contaCorrente;
    private Pessoa pessoa;

    public Number getValorLiquido() {
        return ValorLiquido;
    }

    public void setValorLiquido(Number valorLiquido) {
        ValorLiquido = valorLiquido;
    }

    public int getQtdDias() {
        return QtdDias;
    }

    public void setQtdDias(int iQtdDias) {
        this.QtdDias = iQtdDias;
    }

    public Number getValorDescontos() {
        return ValorDescontos;
    }

    public void setValorDescontos(Number valorDescontos) {
        ValorDescontos = valorDescontos;
    }

    public CentroCusto getCentroCusto() {
        return centroCusto;
    }

    public void setCentroCusto(CentroCusto centroCusto) {
        this.centroCusto = centroCusto;
    }

    public PlanoContas getPlanoContas() {
        return planoContas;
    }

    public void setPlanoContas(PlanoContas planoContas) {
        this.planoContas = planoContas;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

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

    public Date getDataPrevisao() {
        return DataPrevisao;
    }

    public void setDataPrevisao(Date dataPrevisao) {
        DataPrevisao = dataPrevisao;
    }

    public Date getDataMovimento() {
        return DataMovimento;
    }

    public void setDataMovimento(Date dataMovimento) {
        DataMovimento = dataMovimento;
    }

    public Date getDataVencimento() {
        return DataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        DataVencimento = dataVencimento;
    }

    public Date getDataLiquidacao() {
        return DataLiquidacao;
    }

    public void setDataLiquidacao(Date dataLiquidacao) {
        DataLiquidacao = dataLiquidacao;
    }

    public Date getDataCancelamento() {
        return DataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        DataCancelamento = dataCancelamento;
    }

    public Date getDataBaixa() {
        return DataBaixa;
    }

    public void setDataBaixa(Date dataBaixa) {
        DataBaixa = dataBaixa;
    }

    public Date getDataCompetenciaIni() {
        return DataCompetenciaIni;
    }

    public void setDataCompetenciaIni(Date dataCompetenciaIni) {
        DataCompetenciaIni = dataCompetenciaIni;
    }

    public Date getDataCompetenciaFim() {
        return DataCompetenciaFim;
    }

    public void setDataCompetenciaFim(Date dataCompetenciaFim) {
        DataCompetenciaFim = dataCompetenciaFim;
    }

    public Number getValorVencimento() {
        return ValorVencimento;
    }

    public void setValorVencimento(Number valorVencimento) {
        ValorVencimento = valorVencimento;
    }

    public Number getValorLiquidacao() {
        return ValorLiquidacao;
    }

    public void setValorLiquidacao(Number valorLiquidacao) {
        ValorLiquidacao = valorLiquidacao;
    }

    public Number getValorAcrescimos() {
        return ValorAcrescimos;
    }

    public void setValorAcrescimos(Number valorAcrescimos) {
        ValorAcrescimos = valorAcrescimos;
    }

    public Number getValorMulta() {
        return ValorMulta;
    }

    public void setValorMulta(Number valorMulta) {
        ValorMulta = valorMulta;
    }

    public Number getAliquotaJuros() {
        return AliquotaJuros;
    }

    public void setAliquotaJuros(Number aliquotaJuros) {
        AliquotaJuros = aliquotaJuros;
    }

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String observacoes) {
        Observacoes = observacoes;
    }
}

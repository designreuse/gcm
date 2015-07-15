package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 06/05/15
 * Time: 08:22
 * To change this template use File | Settings | File Templates.
 */
public class ContaCorrente {
    private int id_ContaCorrente;
    private int id_Empresa;
    private int id_Banco;
    private String NumeroAgencia;
    private String NumeroContaCorrente;
    private Number LimiteCredito;
    private Number SaldoConta;
    private Banco banco;

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public int getId_ContaCorrente() {
        return id_ContaCorrente;
    }

    public void setId_ContaCorrente(int id_ContaCorrente) {
        this.id_ContaCorrente = id_ContaCorrente;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public int getId_Banco() {
        return id_Banco;
    }

    public void setId_Banco(int id_Banco) {
        this.id_Banco = id_Banco;
    }

    public String getNumeroAgencia() {
        return NumeroAgencia;
    }

    public void setNumeroAgencia(String numeroAgencia) {
        NumeroAgencia = numeroAgencia;
    }

    public String getNumeroContaCorrente() {
        return NumeroContaCorrente;
    }

    public void setNumeroContaCorrente(String numeroContaCorrente) {
        NumeroContaCorrente = numeroContaCorrente;
    }

    public Number getLimiteCredito() {
        return LimiteCredito;
    }

    public void setLimiteCredito(Number limiteCredito) {
        LimiteCredito = limiteCredito;
    }

    public Number getSaldoConta() {
        return SaldoConta;
    }

    public void setSaldoConta(Number saldoConta) {
        SaldoConta = saldoConta;
    }
}

package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 05/05/15
 * Time: 16:36
 * To change this template use File | Settings | File Templates.
 */
public class PlanoContas {
    private int id_PlanoContas;
    private int id_Empresa;
    private String CodigoConta;
    private String Descricao;
    private String TipoConta;
    private String Agrupamento;

    public int getId_PlanoContas() {
        return id_PlanoContas;
    }

    public void setId_PlanoContas(int id_PlanoContas) {
        this.id_PlanoContas = id_PlanoContas;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public String getCodigoConta() {
        return CodigoConta;
    }

    public void setCodigoConta(String codigoConta) {
        CodigoConta = codigoConta;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getTipoConta() {
        return TipoConta;
    }

    public void setTipoConta(String tipoConta) {
        TipoConta = tipoConta;
    }

    public String getAgrupamento() {
        return Agrupamento;
    }

    public void setAgrupamento(String agrupamento) {
        Agrupamento = agrupamento;
    }
}

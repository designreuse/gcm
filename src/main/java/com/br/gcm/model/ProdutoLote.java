package com.br.gcm.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 26/04/15
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
public class ProdutoLote {
    private int id_ProdutoLote;
    private int id_Produto;
    private String NumeroLote;
    private Date DataFabricacao;
    private Date DataValidade;

    public int getId_ProdutoLote() {
        return id_ProdutoLote;
    }

    public void setId_ProdutoLote(int id_ProdutoLote) {
        this.id_ProdutoLote = id_ProdutoLote;
    }

    public int getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(int id_Produto) {
        this.id_Produto = id_Produto;
    }

    public String getNumeroLote() {
        return NumeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        NumeroLote = numeroLote;
    }

    public Date getDataFabricacao() {
        return DataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        DataFabricacao = dataFabricacao;
    }

    public Date getDataValidade() {
        return DataValidade;
    }

    public void setDataValidade(Date dataValidade) {
        DataValidade = dataValidade;
    }
}

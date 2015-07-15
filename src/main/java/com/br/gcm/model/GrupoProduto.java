package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 18/04/15
 * Time: 14:36
 * To change this template use File | Settings | File Templates.
 */

public class GrupoProduto {
    private int id_GrupoProduto;
    private String Descricao;

    public int getId_GrupoProduto() {
        return id_GrupoProduto;
    }

    public void setId_GrupoProduto(int id_GrupoProduto) {
        this.id_GrupoProduto = id_GrupoProduto;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}

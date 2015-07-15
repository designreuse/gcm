package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 18/04/15
 * Time: 19:39
 * To change this template use File | Settings | File Templates.
 */
public class MarcaProduto {
    private int id_MarcaProduto;
    private String Descricao;

    public int getId_MarcaProduto() {
        return id_MarcaProduto;
    }

    public void setId_MarcaProduto(int id_MarcaProduto) {
        this.id_MarcaProduto = id_MarcaProduto;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}

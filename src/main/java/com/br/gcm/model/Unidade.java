package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 21/04/15
 * Time: 20:08
 * To change this template use File | Settings | File Templates.
 */
public class Unidade {
    private int id_Unidade;
    private String Sigla;
    private String Descricao;

    public int getId_Unidade() {
        return id_Unidade;
    }

    public void setId_Unidade(int id_Unidade) {
        this.id_Unidade = id_Unidade;
    }

    public String getSigla() {
        return Sigla;
    }

    public void setSigla(String sigla) {
        Sigla = sigla;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}

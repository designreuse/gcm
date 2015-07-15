package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 05/05/15
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public class Banco {
    private int id_Banco;
    private String NumeroBanco;
    private String Descricao;

    public int getId_Banco() {
        return id_Banco;
    }

    public void setId_Banco(int id_Banco) {
        this.id_Banco = id_Banco;
    }

    public String getNumeroBanco() {
        return NumeroBanco;
    }

    public void setNumeroBanco(String numeroBanco) {
        NumeroBanco = numeroBanco;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}

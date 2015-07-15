package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 22/04/15
 * Time: 09:03
 * To change this template use File | Settings | File Templates.
 */
public class SituacaoTributariaB {
    private int id_STB;
    private String CodigoSTB;
    private String Descricao;

    public int getId_STB() {
        return id_STB;
    }

    public void setId_STB(int id_STB) {
        this.id_STB = id_STB;
    }

    public String getCodigoSTB() {
        return CodigoSTB;
    }

    public void setCodigoSTB(String codigoSTB) {
        CodigoSTB = codigoSTB;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}

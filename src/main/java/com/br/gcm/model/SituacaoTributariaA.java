package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 21/04/15
 * Time: 22:26
 * To change this template use File | Settings | File Templates.
 */
public class SituacaoTributariaA {
    private int id_STA;
    private String CodigoSTA;
    private String Descricao;

    public int getId_STA() {
        return id_STA;
    }

    public void setId_STA(int id_STA) {
        this.id_STA = id_STA;
    }

    public String getCodigoSTA() {
        return CodigoSTA;
    }

    public void setCodigoSTA(String codigoSTA) {
        CodigoSTA = codigoSTA;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}

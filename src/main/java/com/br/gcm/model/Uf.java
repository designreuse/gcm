package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 19/12/13
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */
public class Uf {
    private int id_Uf;
    private int id_Pais;
    private String siglaUf;
    private String descricao;
    private String ibge;

    private String siglaPais;

    public String getSiglaPais() {
        return siglaPais;
    }

    public void setSiglaPais(String siglaPais) {
        this.siglaPais = siglaPais;
    }

    public int getId_Uf() {
        return id_Uf;
    }

    public void setId_Uf(int id_Uf) {
        this.id_Uf = id_Uf;
    }

    public int getId_Pais() {
        return id_Pais;
    }

    public void setId_Pais(int id_Pais) {
        this.id_Pais = id_Pais;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String sigla_Uf) {
        this.siglaUf = sigla_Uf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String nome_Uf) {
        this.descricao = nome_Uf;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String cod_ibge) {
        this.ibge = cod_ibge;
    }
}

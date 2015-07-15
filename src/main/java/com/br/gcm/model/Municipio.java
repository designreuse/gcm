package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 20/12/13
 * Time: 11:45
 * To change this template use File | Settings | File Templates.
 */
public class Municipio {

    private int id_municipio;
    private int id_uf;
    private String descricao;
    private String ibge;

    private int id_pais;
    private String siglapais;
    private String siglauf;

    public int getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(int id_municipio) {
        this.id_municipio = id_municipio;
    }

    public int getId_uf() {
        return id_uf;
    }

    public void setId_uf(int id_uf) {
        this.id_uf = id_uf;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String nome_municipio) {
        this.descricao = nome_municipio;
    }

    public String getIbge() {
        return ibge;
    }

    public void setIbge(String cod_ibge) {
        this.ibge = cod_ibge;
    }

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getSiglapais() {
        return siglapais;
    }

    public void setSiglapais(String sigla_pais) {
        this.siglapais = sigla_pais;
    }

    public String getSiglauf() {
        return siglauf;
    }

    public void setSiglauf(String sigla_uf) {
        this.siglauf = sigla_uf;
    }
}

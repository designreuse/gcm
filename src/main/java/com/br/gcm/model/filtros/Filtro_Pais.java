package com.br.gcm.model.filtros;

/**
 * Created with IntelliJ IDEA.
 * User: joao.carlos
 * Date: 01/04/14
 * Time: 09:39
 * To change this template use File | Settings | File Templates.
 */
public class Filtro_Pais {
    private int id_pais;
    private String siglapais;
    private String descricao;
    private String ibge;

    public int getId_pais() {
        return id_pais;
    }

    public void setId_pais(int id_pais) {
        this.id_pais = id_pais;
    }

    public String getSiglapais() {
        return siglapais;
    }

    public void setSiglapais(String siglapais) {
        this.siglapais = siglapais;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getibge() {
        return ibge;
    }

    public void setibge(String ibge) {
        this.ibge = ibge;
    }
}

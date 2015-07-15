package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 02/05/15
 * Time: 09:05
 * To change this template use File | Settings | File Templates.
 */
public class CentroCusto {
    private int id_CentroCusto;
    private int id_Empresa;
    private String Sigla;
    private String Descricao;

    public int getId_CentroCusto() {
        return id_CentroCusto;
    }

    public void setId_CentroCusto(int id_CentroCusto) {
        this.id_CentroCusto = id_CentroCusto;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
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

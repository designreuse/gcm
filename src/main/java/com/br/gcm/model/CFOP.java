package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 30/04/15
 * Time: 14:31
 * To change this template use File | Settings | File Templates.
 */
public class CFOP {
    private int id_CFOP;
    private String CodigoCFOP;
    private String Descricao;
    private String Tipo;
    private Boolean Ajuste;
    private Boolean ExigeRetorno;
    private int DiasRetorno;

    public Boolean getAjuste() {
        return Ajuste;
    }

    public void setAjuste(Boolean ajuste) {
        Ajuste = ajuste;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public int getId_CFOP() {
        return id_CFOP;
    }

    public void setId_CFOP(int id_CFOP) {
        this.id_CFOP = id_CFOP;
    }

    public String getCodigoCFOP() {
        return CodigoCFOP;
    }

    public void setCodigoCFOP(String codigoCFOP) {
        CodigoCFOP = codigoCFOP;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Boolean getExigeRetorno() {
        return ExigeRetorno;
    }

    public void setExigeRetorno(Boolean exigeRetorno) {
        ExigeRetorno = exigeRetorno;
    }

    public int getDiasRetorno() {
        return DiasRetorno;
    }

    public void setDiasRetorno(int diasRetorno) {
        DiasRetorno = diasRetorno;
    }
}

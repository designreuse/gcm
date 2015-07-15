package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 03/05/15
 * Time: 09:46
 * To change this template use File | Settings | File Templates.
 */
public class RelacaoCFOP {
    private int id_RelacaoCFOP;
    private int id_CFOP;
    private int id_CFOPRelacao;
    private Boolean Pertence;
    private CFOP cfop;
    private CFOP RelacaoCFOP;

    public Boolean getPertence() {
        return Pertence;
    }

    public void setPertence(Boolean pertence) {
        Pertence = pertence;
    }

    public int getId_RelacaoCFOP() {
        return id_RelacaoCFOP;
    }

    public void setId_RelacaoCFOP(int id_RelacaoCFOP) {
        this.id_RelacaoCFOP = id_RelacaoCFOP;
    }

    public int getId_CFOP() {
        return id_CFOP;
    }

    public void setId_CFOP(int id_CFOP) {
        this.id_CFOP = id_CFOP;
    }

    public int getId_CFOPRelacao() {
        return id_CFOPRelacao;
    }

    public void setId_CFOPRelacao(int id_CFOPRelacao) {
        this.id_CFOPRelacao = id_CFOPRelacao;
    }

    public CFOP getCfop() {
        return cfop;
    }

    public void setCfop(CFOP cfop) {
        this.cfop = cfop;
    }

    public CFOP getRelacaoCFOP() {
        return RelacaoCFOP;
    }

    public void setRelacaoCFOP(CFOP relacaoCFOP) {
        RelacaoCFOP = relacaoCFOP;
    }
}

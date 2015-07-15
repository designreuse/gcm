package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 19/04/15
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */
public class NCM {
    private int id_NCM;
    private String CodigoNCM;
    private String Descricao;
    private Number AliquotaIPI;
    private Number AliquotaII;
    private int id_CSTPIS;
    private int id_CSTCOFINS;

    public int getId_CSTPIS() {
        return id_CSTPIS;
    }

    public void setId_CSTPIS(int id_CSTPIS) {
        this.id_CSTPIS = id_CSTPIS;
    }

    public int getId_CSTCOFINS() {
        return id_CSTCOFINS;
    }

    public void setId_CSTCOFINS(int id_CSTCOFINS) {
        this.id_CSTCOFINS = id_CSTCOFINS;
    }

    public int getId_NCM() {
        return id_NCM;
    }

    public void setId_NCM(int id_NCM) {
        this.id_NCM = id_NCM;
    }

    public String getCodigoNCM() {
        return CodigoNCM;
    }

    public void setCodigoNCM(String codigoNCM) {
        CodigoNCM = codigoNCM;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public Number getAliquotaIPI() {
        return AliquotaIPI;
    }

    public void setAliquotaIPI(Number aliquotaIPI) {
        AliquotaIPI = aliquotaIPI;
    }

    public Number getAliquotaII() {
        return AliquotaII;
    }

    public void setAliquotaII(Number aliquotaII) {
        AliquotaII = aliquotaII;
    }
}

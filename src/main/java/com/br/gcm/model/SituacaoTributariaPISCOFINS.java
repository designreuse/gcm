package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 23/04/15
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
public class SituacaoTributariaPISCOFINS {
    private int id_CSTPISCOFINS;
    private String CodigoSTPISCOFINS;
    private String Descricao;
    private Boolean IsentoAliquotaZero;

    public Boolean getIsentoAliquotaZero() {
        return IsentoAliquotaZero;
    }

    public void setIsentoAliquotaZero(Boolean isentoAliquotaZero) {
        IsentoAliquotaZero = isentoAliquotaZero;
    }

    public int getId_CSTPISCOFINS() {
        return id_CSTPISCOFINS;
    }

    public void setId_CSTPISCOFINS(int id_CSTPISCOFINS) {
        this.id_CSTPISCOFINS = id_CSTPISCOFINS;
    }

    public String getCodigoSTPISCOFINS() {
        return CodigoSTPISCOFINS;
    }

    public void setCodigoSTPISCOFINS(String codigoSTPISCOFINS) {
        CodigoSTPISCOFINS = codigoSTPISCOFINS;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}

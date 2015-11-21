package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 17/04/15
 * Time: 09:28
 * To change this template use File | Settings | File Templates.
 */
public class GrupoTransacao {
    private int id_GrupoTransacao;
    private int id_GrupoUsuario;
    private int id_Transacao;
    private String CodigoTransacao;
    private String DescricaoTransacao;
    private String DescricaoGrupo;
    private boolean Pertence;

    public int getId_GrupoTransacao() {
        return id_GrupoTransacao;
    }

    public void setId_GrupoTransacao(int id_GrupoTransacao) {
        this.id_GrupoTransacao = id_GrupoTransacao;
    }

    public int getId_GrupoUsuario() {
        return id_GrupoUsuario;
    }

    public void setId_GrupoUsuario(int id_GrupoUsuario) {
        this.id_GrupoUsuario = id_GrupoUsuario;
    }

    public int getId_Transacao() {
        return id_Transacao;
    }

    public void setId_Transacao(int id_Transacao) {
        this.id_Transacao = id_Transacao;
    }

    public String getCodigoTransacao() {
        return CodigoTransacao;
    }

    public void setCodigoTransacao(String codigoTransacao) {
        CodigoTransacao = codigoTransacao;
    }

    public String getDescricaoTransacao() {
        return DescricaoTransacao;
    }

    public void setDescricaoTransacao(String descricaoTransacao) {
        DescricaoTransacao = descricaoTransacao;
    }

    public String getDescricaoGrupo() {
        return DescricaoGrupo;
    }

    public void setDescricaoGrupo(String descricaoGrupo) {
        DescricaoGrupo = descricaoGrupo;
    }

    public boolean isPertence() {
        return Pertence;
    }

    public void setPertence(boolean pertence) {
        Pertence = pertence;
    }
}

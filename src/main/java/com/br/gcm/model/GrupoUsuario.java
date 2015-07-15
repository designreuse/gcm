package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 12/04/15
 * Time: 10:42
 * To change this template use File | Settings | File Templates.
 */
public class GrupoUsuario {
    private int id_GrupoUsuario;
    private String Descricao;

    public int getId_GrupoUsuario() {
        return id_GrupoUsuario;
    }

    public void setId_GrupoUsuario(int id_GrupoUsuario) {
        this.id_GrupoUsuario = id_GrupoUsuario;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }
}

package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 16/04/15
 * Time: 10:16
 * To change this template use File | Settings | File Templates.
 */
public class UsuariodoGrupo {
    private int id_UsuariodoGrupo;
    private int id_GrupoUsuario;
    private int id_Usuario;
    private String DescricaoGrupo;
    private String NomeUsuario;
    private Boolean Pertence;

    public int getId_UsuariodoGrupo() {
        return id_UsuariodoGrupo;
    }

    public void setId_UsuariodoGrupo(int id_UsuariodoGrupo) {
        this.id_UsuariodoGrupo = id_UsuariodoGrupo;
    }

    public int getId_GrupoUsuario() {
        return id_GrupoUsuario;
    }

    public void setId_GrupoUsuario(int id_GrupoUsuario) {
        this.id_GrupoUsuario = id_GrupoUsuario;
    }

    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public String getDescricaoGrupo() {
        return DescricaoGrupo;
    }

    public void setDescricaoGrupo(String descricao_Grupo) {
        DescricaoGrupo = descricao_Grupo;
    }

    public String getNomeUsuario() {
        return NomeUsuario;
    }

    public void setNomeUsuario(String nome_Usuario) {
        NomeUsuario = nome_Usuario;
    }

    public Boolean getPertence() {
        return Pertence;
    }

    public void setPertence(Boolean pertence) {
        Pertence = pertence;
    }


}

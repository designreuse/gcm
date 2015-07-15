package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 17/04/15
 * Time: 22:27
 * To change this template use File | Settings | File Templates.
 */
public class EmpresaGrupo {
    private int id_EmpresaGrupo;
    private int id_Empresa;
    private int id_GrupoUsuario;
    private String DescricaoGrupo;
    private String RazaoSocial;
    private Boolean Pertence;

    public Boolean getPertence() {
        return Pertence;
    }

    public void setPertence(Boolean pertence) {
        Pertence = pertence;
    }

    public int getId_EmpresaGrupo() {
        return id_EmpresaGrupo;
    }

    public void setId_EmpresaGrupo(int id_EmpresaGrupo) {
        this.id_EmpresaGrupo = id_EmpresaGrupo;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public int getId_GrupoUsuario() {
        return id_GrupoUsuario;
    }

    public void setId_GrupoUsuario(int id_GrupoUsuario) {
        this.id_GrupoUsuario = id_GrupoUsuario;
    }

    public String getDescricaoGrupo() {
        return DescricaoGrupo;
    }

    public void setDescricaoGrupo(String descricaoGrupo) {
        DescricaoGrupo = descricaoGrupo;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        RazaoSocial = razaoSocial;
    }
}

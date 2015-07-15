package com.br.gcm.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class Usuario implements Serializable {

    @NotNull(message = "ID é requerido")
    private Integer id_usuario;

    @Length(min = 3, max = 60, message = "Usuário dever ter entre 3 e 60 caracteres")
    @NotEmpty(message = "Nome é requerido")
    private String nome;

    @Length(min = 1, max = 25, message = "Login dever ter entre 1 e 60 caracteres")
    @NotEmpty(message = "Login é requerido")
    private String login;
    private String senha;
    private boolean ativo;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

  /*  @Override
    public String toString() {
        return "Usuario{" +
                "id_usuario=" + id_usuario +
                ", nome='" + nome + '\'' +
                ", login='"+ login + '\'' +
                ", senha='" + senha + '\'' +
                ", ativo=" + ativo +
                '}';
    } */
}
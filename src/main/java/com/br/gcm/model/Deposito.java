package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 19/04/15
 * Time: 22:32
 * To change this template use File | Settings | File Templates.
 */
public class Deposito {
    private int id_Deposito;
    private int id_Empresa;
    private String Descricao;
    private String Endereco;

    public int getId_Deposito() {
        return id_Deposito;
    }

    public void setId_Deposito(int id_Deposito) {
        this.id_Deposito = id_Deposito;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }
}

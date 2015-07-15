package com.br.gcm.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 29/12/13
 * Time: 09:04
 * To change this template use File | Settings | File Templates.
 */
public class Empresa {
    private int id_Empresa;
    private String Cnpj;
    private String RazaoSocial;
    private String NomeFantasia;
    private String InscricaoEstadual;
    private String InscricaoMunicipal;
    private Date DataAbertura;
    private String Endereco;
    private String Numero;
    private String Bairro;
    private String Cep;
    private int id_Municipio;
    private String Telefone1;
    private String Telefone2;
    private String Fax;
    private String Email;
    private String Url;

    private int id_Uf;
    private int id_Pais;
    private String siglaUf;
    private String siglaPais;

    public int getId_Uf() {
        return id_Uf;
    }

    public void setId_Uf(int id_Uf) {
        this.id_Uf = id_Uf;
    }

    public int getId_Pais() {
        return id_Pais;
    }

    public void setId_Pais(int id_Pais) {
        this.id_Pais = id_Pais;
    }

    public String getSiglaUf() {
        return siglaUf;
    }

    public void setSiglaUf(String sigla_Uf) {
        this.siglaUf = sigla_Uf;
    }

    public String getSiglaPais() {
        return siglaPais;
    }

    public void setSiglaPais(String sigla_Pais) {
        this.siglaPais = sigla_Pais;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public String getCnpj() {
        return Cnpj;
    }

    public void setCnpj(String cnpj) {
        Cnpj = cnpj;
    }

    public String getRazaoSocial() {
        return RazaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        RazaoSocial = razaoSocial;
    }

    public String getNomeFantasia() {
        return NomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        NomeFantasia = nomeFantasia;
    }

    public String getInscricaoEstadual() {
        return InscricaoEstadual;
    }

    public void setInscricaoEstadual(String insc_Estadual) {
        InscricaoEstadual = insc_Estadual;
    }

    public String getInscricaoMunicipal() {
        return InscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String insc_Municipal) {
        InscricaoMunicipal = insc_Municipal;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String endereco) {
        Endereco = endereco;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
    }

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public int getId_Municipio() {
        return id_Municipio;
    }

    public void setId_Municipio(int id_Municipio) {
        this.id_Municipio = id_Municipio;
    }

    public String getTelefone1() {
        return Telefone1;
    }

    public void setTelefone1(String telefone1) {
        Telefone1 = telefone1;
    }

    public String getTelefone2() {
        return Telefone2;
    }

    public void setTelefone2(String telefone2) {
        Telefone2 = telefone2;
    }

    public String getFax() {
        return Fax;
    }

    public void setFax(String fax) {
        Fax = fax;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }


    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Date getDataAbertura() {
        return DataAbertura;
    }

    public void setDataAbertura(Date dataAbertura) {
        DataAbertura = dataAbertura;
    }
}

package com.br.gcm.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 26/04/15
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
public class Pessoa {
    private int id_Pessoa;
    private String CpfCnpj;
    private String RazaoSocial;
    private String NomeFantasia;
    private String InscricaoEstadual;
    private String InscricaoMunicipal;
    private String Rg;
    private Date DataNascimento;
    private String Endereco;
    private String Numero;
    private String Cep;
    private String Bairro;
    private int id_Municipio;
    private String Telefone1;
    private String Telefone2;
    private String Fax;
    private String Email;
    private Boolean Ativo;

    private Boolean TipoCliente;
    private Boolean TipoFornecedor;
    private Boolean TipoVendedor;
    private Boolean TipoFuncionario;
    private Boolean TipoTransportador;
    private Boolean TipoConvenio;
    private Boolean TipoHospital;
    private Boolean TipoMedico;
    private Boolean TipoEnfermeiro;
    private Boolean TipoPaciente;

    private Municipio municipio;

    public Municipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipio municipio) {
        this.municipio = municipio;
    }

    public Boolean getTipoCliente() {
        return TipoCliente;
    }

    public void setTipoCliente(Boolean tipoCliente) {
        TipoCliente = tipoCliente;
    }

    public Boolean getTipoFornecedor() {
        return TipoFornecedor;
    }

    public void setTipoFornecedor(Boolean tipoFornecedor) {
        TipoFornecedor = tipoFornecedor;
    }

    public Boolean getTipoVendedor() {
        return TipoVendedor;
    }

    public void setTipoVendedor(Boolean tipoVendedor) {
        TipoVendedor = tipoVendedor;
    }

    public Boolean getTipoFuncionario() {
        return TipoFuncionario;
    }

    public void setTipoFuncionario(Boolean tipoFuncionario) {
        TipoFuncionario = tipoFuncionario;
    }

    public Boolean getTipoTransportador() {
        return TipoTransportador;
    }

    public void setTipoTransportador(Boolean tipoTransportador) {
        TipoTransportador = tipoTransportador;
    }

    public Boolean getTipoConvenio() {
        return TipoConvenio;
    }

    public void setTipoConvenio(Boolean tipoConvenio) {
        TipoConvenio = tipoConvenio;
    }

    public Boolean getTipoHospital() {
        return TipoHospital;
    }

    public void setTipoHospital(Boolean tipoHospital) {
        TipoHospital = tipoHospital;
    }

    public Boolean getTipoMedico() {
        return TipoMedico;
    }

    public void setTipoMedico(Boolean tipoMedico) {
        TipoMedico = tipoMedico;
    }

    public Boolean getTipoEnfermeiro() {
        return TipoEnfermeiro;
    }

    public void setTipoEnfermeiro(Boolean tipoEnfermeiro) {
        TipoEnfermeiro = tipoEnfermeiro;
    }

    public Boolean getTipoPaciente() {
        return TipoPaciente;
    }

    public void setTipoPaciente(Boolean tipoPaciente) {
        TipoPaciente = tipoPaciente;
    }

    public int getId_Pessoa() {
        return id_Pessoa;
    }

    public void setId_Pessoa(int id_Pessoa) {
        this.id_Pessoa = id_Pessoa;
    }

    public String getCpfCnpj() {
        return CpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        CpfCnpj = cpfCnpj;
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

    public void setInscricaoEstadual(String inscricaoEstadual) {
        InscricaoEstadual = inscricaoEstadual;
    }

    public String getInscricaoMunicipal() {
        return InscricaoMunicipal;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        InscricaoMunicipal = inscricaoMunicipal;
    }

    public String getRg() {
        return Rg;
    }

    public void setRg(String rg) {
        Rg = rg;
    }

    public Date getDataNascimento() {
        return DataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        DataNascimento = dataNascimento;
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

    public String getCep() {
        return Cep;
    }

    public void setCep(String cep) {
        Cep = cep;
    }

    public String getBairro() {
        return Bairro;
    }

    public void setBairro(String bairro) {
        Bairro = bairro;
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

    public Boolean getAtivo() {
        return Ativo;
    }

    public void setAtivo(Boolean ativo) {
        Ativo = ativo;
    }
}

package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 26/05/15
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class VWEstoqueProdutoLote {
    private int id_EstoqueProduto;
    private int id_Empresa;
    private int id_Deposito;
    private int id_ProdutoLote;
    private int id_CFOP;
    private Number QuantidadeEstoque;
    private Number Reservas;
    private Number Disponivel;

    private Empresa empresa;
    private Deposito deposito;
    private ProdutoLote produtoLote;
    private CFOP cfop;
    private Produto produto;

    public int getId_EstoqueProduto() {
        return id_EstoqueProduto;
    }

    public void setId_EstoqueProduto(int id_EstoqueProduto) {
        this.id_EstoqueProduto = id_EstoqueProduto;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public int getId_Deposito() {
        return id_Deposito;
    }

    public void setId_Deposito(int id_Deposito) {
        this.id_Deposito = id_Deposito;
    }

    public int getId_ProdutoLote() {
        return id_ProdutoLote;
    }

    public void setId_ProdutoLote(int id_ProdutoLote) {
        this.id_ProdutoLote = id_ProdutoLote;
    }

    public int getId_CFOP() {
        return id_CFOP;
    }

    public void setId_CFOP(int id_CFOP) {
        this.id_CFOP = id_CFOP;
    }

    public Number getQuantidadeEstoque() {
        return QuantidadeEstoque;
    }

    public void setQuantidadeEstoque(Number quantidadeEstoque) {
        QuantidadeEstoque = quantidadeEstoque;
    }

    public Number getReservas() {
        return Reservas;
    }

    public void setReservas(Number reservas) {
        Reservas = reservas;
    }

    public Number getDisponivel() {
        return Disponivel;
    }

    public void setDisponivel(Number disponivel) {
        Disponivel = disponivel;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }

    public ProdutoLote getProdutoLote() {
        return produtoLote;
    }

    public void setProdutoLote(ProdutoLote produtoLote) {
        this.produtoLote = produtoLote;
    }

    public CFOP getCfop() {
        return cfop;
    }

    public void setCfop(CFOP cfop) {
        this.cfop = cfop;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}

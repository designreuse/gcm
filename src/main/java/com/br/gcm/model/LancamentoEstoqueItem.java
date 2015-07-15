package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 11/05/15
 * Time: 15:06
 * To change this template use File | Settings | File Templates.
 */
public class LancamentoEstoqueItem {
    private int id_LancamentoEstoqueItem;
    private int id_LancamentoEstoque;
    private int id_Deposito;
    private int id_ProdutoLote;
    private int id_ProdutoUnidade;
    private Number Quantidade;

    private ProdutoLote ProdutoLote;
    private Produto produto;
    private ProdutoUnidade produtoUnidade;
    private Unidade unidade;

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public ProdutoUnidade getProdutoUnidade() {
        return produtoUnidade;
    }

    public void setProdutoUnidade(ProdutoUnidade produtoUnidade) {
        this.produtoUnidade = produtoUnidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public ProdutoLote getProdutoLote() {
        return ProdutoLote;
    }

    public void setProdutoLote(ProdutoLote produtoLote) {
        ProdutoLote = produtoLote;
    }

    public int getId_LancamentoEstoqueItem() {
        return id_LancamentoEstoqueItem;
    }

    public void setId_LancamentoEstoqueItem(int id_LancamentoEstoqueItem) {
        this.id_LancamentoEstoqueItem = id_LancamentoEstoqueItem;
    }

    public int getId_LancamentoEstoque() {
        return id_LancamentoEstoque;
    }

    public void setId_LancamentoEstoque(int id_LancamentoEstoque) {
        this.id_LancamentoEstoque = id_LancamentoEstoque;
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

    public int getId_ProdutoUnidade() {
        return id_ProdutoUnidade;
    }

    public void setId_ProdutoUnidade(int id_ProdutoUnidade) {
        this.id_ProdutoUnidade = id_ProdutoUnidade;
    }

    public Number getQuantidade() {
        return Quantidade;
    }

    public void setQuantidade(Number quantidade) {
        Quantidade = quantidade;
    }
}

package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 25/04/15
 * Time: 12:01
 * To change this template use File | Settings | File Templates.
 */
public class ProdutoUnidade {
    private int id_ProdutoUnidade;
    private int id_Produto;
    private int id_Unidade;
    private String CodigoBarras;
    private Number FatorConversao;
    private Number PesoBruto;
    private Number PesoLiquido;
    private Boolean UnidadePrincipal;
    private Unidade unidade;

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public int getId_ProdutoUnidade() {
        return id_ProdutoUnidade;
    }

    public void setId_ProdutoUnidade(int id_ProdutoUnidade) {
        this.id_ProdutoUnidade = id_ProdutoUnidade;
    }

    public int getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(int id_Produto) {
        this.id_Produto = id_Produto;
    }

    public int getId_Unidade() {
        return id_Unidade;
    }

    public void setId_Unidade(int id_Unidade) {
        this.id_Unidade = id_Unidade;
    }

    public String getCodigoBarras() {
        return CodigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        CodigoBarras = codigoBarras;
    }

    public Number getFatorConversao() {
        return FatorConversao;
    }

    public void setFatorConversao(Number fatorConversao) {
        FatorConversao = fatorConversao;
    }

    public Number getPesoBruto() {
        return PesoBruto;
    }

    public void setPesoBruto(Number pesoBruto) {
        PesoBruto = pesoBruto;
    }

    public Number getPesoLiquido() {
        return PesoLiquido;
    }

    public void setPesoLiquido(Number pesoLiquido) {
        PesoLiquido = pesoLiquido;
    }

    public Boolean getUnidadePrincipal() {
        return UnidadePrincipal;
    }

    public void setUnidadePrincipal(Boolean unidadePrincipal) {
        UnidadePrincipal = unidadePrincipal;
    }
}

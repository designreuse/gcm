package com.br.gcm.model.filtros;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 28/05/15
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public class Filtro_Produto {
    private int id_Produto;
    private String Referencia;
    private String Descricao;
    private String NumeroLote;
    private boolean EstoqueZerado;
    private int id_Empresa;
    private int id_Deposito;
    private int id_CFOP;
    private int id_ProdutoLote;

    public int getId_ProdutoLote() {
        return id_ProdutoLote;
    }

    public void setId_ProdutoLote(int id_ProdutoLote) {
        this.id_ProdutoLote = id_ProdutoLote;
    }

    public boolean isEstoqueZerado() {
        return EstoqueZerado;
    }

    public void setEstoqueZerado(boolean estoqueZerado) {
        EstoqueZerado = estoqueZerado;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public int getId_Produto() {
        return id_Produto;
    }

    public void setId_Produto(int id_Produto) {
        this.id_Produto = id_Produto;
    }

    public String getReferencia() {
        return Referencia;
    }

    public void setReferencia(String referencia) {
        Referencia = referencia;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getNumeroLote() {
        return NumeroLote;
    }

    public void setNumeroLote(String numeroLote) {
        NumeroLote = numeroLote;
    }

    public int getId_Deposito() {
        return id_Deposito;
    }

    public void setId_Deposito(int id_Deposito) {
        this.id_Deposito = id_Deposito;
    }

    public int getId_CFOP() {
        return id_CFOP;
    }

    public void setId_CFOP(int id_CFOP) {
        this.id_CFOP = id_CFOP;
    }
}

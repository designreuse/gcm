package com.br.gcm.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 22/04/15
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class Produto {
    private int id_Produto;
    private String Referencia;
    private String Descricao;
    private int id_SubGrupoProduto;
    private int id_MarcaProduto;
    private int id_NCM;
    private int id_STA;
    private int id_STB;
    private int id_UnidadePadrao;
    private Number PesoBruto;
    private Number PesoLiquido;
    private String NumeroAnvisa;
    private Date ValidadeAnvisa;
    private boolean ControlaLote;
    private boolean AtivoCompra;
    private boolean AtivoVenda;

    private GrupoProduto grupoProduto;
    private SubGrupoProduto subGrupoProduto;
    private NCM ncm;
    private SituacaoTributariaA situacaoTributariaA;
    private SituacaoTributariaB situacaoTributariaB;
    private MarcaProduto marcaProduto;
    private Unidade unidadePadrao;

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public SubGrupoProduto getSubGrupoProduto() {
        return subGrupoProduto;
    }

    public void setSubGrupoProduto(SubGrupoProduto subGrupoProduto) {
        this.subGrupoProduto = subGrupoProduto;
    }

    public NCM getNcm() {
        return ncm;
    }

    public void setNcm(NCM ncm) {
        this.ncm = ncm;
    }

    public SituacaoTributariaA getSituacaoTributariaA() {
        return situacaoTributariaA;
    }

    public void setSituacaoTributariaA(SituacaoTributariaA situacaoTributariaA) {
        this.situacaoTributariaA = situacaoTributariaA;
    }

    public SituacaoTributariaB getSituacaoTributariaB() {
        return situacaoTributariaB;
    }

    public void setSituacaoTributariaB(SituacaoTributariaB situacaoTributariaB) {
        this.situacaoTributariaB = situacaoTributariaB;
    }

    public MarcaProduto getMarcaProduto() {
        return marcaProduto;
    }

    public void setMarcaProduto(MarcaProduto marcaProduto) {
        this.marcaProduto = marcaProduto;
    }

    public Unidade getUnidadePadrao() {
        return unidadePadrao;
    }

    public void setUnidadePadrao(Unidade unidadePadrao) {
        this.unidadePadrao = unidadePadrao;
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

    public int getId_SubGrupoProduto() {
        return id_SubGrupoProduto;
    }

    public void setId_SubGrupoProduto(int id_SubGrupoProduto) {
        this.id_SubGrupoProduto = id_SubGrupoProduto;
    }

    public int getId_MarcaProduto() {
        return id_MarcaProduto;
    }

    public void setId_MarcaProduto(int id_MarcaProduto) {
        this.id_MarcaProduto = id_MarcaProduto;
    }

    public int getId_NCM() {
        return id_NCM;
    }

    public void setId_NCM(int id_NCM) {
        this.id_NCM = id_NCM;
    }

    public int getId_STA() {
        return id_STA;
    }

    public void setId_STA(int id_STA) {
        this.id_STA = id_STA;
    }

    public int getId_STB() {
        return id_STB;
    }

    public void setId_STB(int id_STB) {
        this.id_STB = id_STB;
    }

    public int getId_UnidadePadrao() {
        return id_UnidadePadrao;
    }

    public void setId_UnidadePadrao(int id_UnidadePadrao) {
        this.id_UnidadePadrao = id_UnidadePadrao;
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

    public String getNumeroAnvisa() {
        return NumeroAnvisa;
    }

    public void setNumeroAnvisa(String numeroAnvisa) {
        NumeroAnvisa = numeroAnvisa;
    }

    public Date getValidadeAnvisa() {
        return ValidadeAnvisa;
    }

    public void setValidadeAnvisa(Date validadeAnvisa) {
        ValidadeAnvisa = validadeAnvisa;
    }

    public boolean isControlaLote() {
        return ControlaLote;
    }

    public void setControlaLote(boolean controlaLote) {
        ControlaLote = controlaLote;
    }

    public boolean isAtivoCompra() {
        return AtivoCompra;
    }

    public void setAtivoCompra(boolean ativoCompra) {
        AtivoCompra = ativoCompra;
    }

    public boolean isAtivoVenda() {
        return AtivoVenda;
    }

    public void setAtivoVenda(boolean ativoVenda) {
        AtivoVenda = ativoVenda;
    }
}

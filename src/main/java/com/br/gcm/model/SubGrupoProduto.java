package com.br.gcm.model;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 18/04/15
 * Time: 16:34
 * To change this template use File | Settings | File Templates.
 */
public class SubGrupoProduto {
    private int id_SubGrupoProduto;
    private int id_GrupoProduto;
    private String Descricao;
    private String DescricaoGrupo;

    private GrupoProduto grupoProduto;

    public GrupoProduto getGrupoProduto() {
        return grupoProduto;
    }

    public void setGrupoProduto(GrupoProduto grupoProduto) {
        this.grupoProduto = grupoProduto;
    }

    public int getId_SubGrupoProduto() {
        return id_SubGrupoProduto;
    }

    public void setId_SubGrupoProduto(int id_SubGrupoProduto) {
        this.id_SubGrupoProduto = id_SubGrupoProduto;
    }

    public int getId_GrupoProduto() {
        return id_GrupoProduto;
    }

    public void setId_GrupoProduto(int id_GrupoProduto) {
        this.id_GrupoProduto = id_GrupoProduto;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String descricao) {
        Descricao = descricao;
    }

    public String getDescricaoGrupo() {
        return DescricaoGrupo;
    }

    public void setDescricaoGrupo(String descricaoGrupo) {
        DescricaoGrupo = descricaoGrupo;
    }
}

package com.br.gcm.model;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: JoãoCarlos
 * Date: 09/05/15
 * Time: 11:59
 * To change this template use File | Settings | File Templates.
 */

public class LancamentoEstoque {
    private int id_LancamentoEstoque;
    private int id_Empresa;
    private int id_CFOP;
    private Date DataLancamento;
    private boolean Atualizado;
    private CFOP cfop;
    private String Observacoes;

    public String getObservacoes() {
        return Observacoes;
    }

    public void setObservacoes(String observacoes) {
        Observacoes = observacoes;
    }

    public CFOP getCfop() {
        return cfop;
    }

    public void setCfop(CFOP cfop) {
        this.cfop = cfop;
    }

    public int getId_LancamentoEstoque() {
        return id_LancamentoEstoque;
    }

    public void setId_LancamentoEstoque(int id_LancamentoEstoque) {
        this.id_LancamentoEstoque = id_LancamentoEstoque;
    }

    public int getId_Empresa() {
        return id_Empresa;
    }

    public void setId_Empresa(int id_Empresa) {
        this.id_Empresa = id_Empresa;
    }

    public int getId_CFOP() {
        return id_CFOP;
    }

    public void setId_CFOP(int id_CFOP) {
        this.id_CFOP = id_CFOP;
    }

    public Date getDataLancamento() {
        return DataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        DataLancamento = dataLancamento;
    }

    public boolean isAtualizado() {
        return Atualizado;
    }

    public void setAtualizado(boolean atualizado) {
        Atualizado = atualizado;
    }
}

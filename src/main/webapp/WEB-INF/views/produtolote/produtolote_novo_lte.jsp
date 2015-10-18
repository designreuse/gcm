<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Nova Lote do Produto</h1>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <label class="col-md-1 control-label" for="id_Produto" style="margin-top: 8px;">Produto</label>
                    <div class="col-md-1">
                        <input type="text" maxlength="10" value="${produto.id_Produto}" readonly="true"
                               class="form-control"/>
                    </div>
                    <div class="col-md-2">
                        <input type="text" maxlength="10" style="width:100%;" readonly="true" value="${produto.referencia}"
                               class="form-control" placeholder="Referência" name="referencia" id="referencia"/>
                    </div>
                    <div class="col-md-6">
                        <input type="text" maxlength="50" style="width:100%;" readonly="true" value="${produto.descricao}"
                               class="form-control" placeholder="Descrição" name="descricao" id="descricao"/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <form action="/produtolote_insert" method="post">
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="numeroLote" style="margin-top: 5px;">Lote</label>
                                        <div class="col-md-2">
                                            <input type="hidden" value="${produto.id_Produto}"
                                                   class="form-control" placeholder="Barras" name="id_Produto" id="id_Produto"/>

                                            <input type="text" style="width:100%;" required="true" maxlength="20"
                                                   class="form-control" placeholder="Barras" name="numeroLote" id="numeroLote"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="dataFabricacao" style="margin-top: 5px;">Fabricação</label>
                                        <div class="col-md-2">
                                            <input type="date" style="width:100%;" required="true"
                                                   class="form-control" placeholder="Fabricação" name="dataFabricacao" id="dataFabricacao"/>
                                        </div>
                                        <label class="col-md-2 control-label" for="dataValidade" style="margin-top: 5px;">Validade</label>
                                        <div class="col-md-2">
                                            <input type="date" style="width:100%;" required="true"
                                                   class="form-control" placeholder="Fator" name="dataValidade" id="dataValidade"/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <div class="col-sm-12" style="margin-top: 15px; text-align: center;">
                                <button class="btn btn-primary" type="submit" style="width: 80px;">Salvar</button>
                                <a style="width: 80px" href="<c:url value="/produtolote_lista/${produto.id_Produto}"/>" class="btn btn-primary" title="Cancelar">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>


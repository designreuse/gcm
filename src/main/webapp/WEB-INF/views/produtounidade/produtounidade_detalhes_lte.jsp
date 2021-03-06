<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Detalhes da Unidade do Produto</h1>
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

    <form action="/produtounidade_update" method="post">
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="id_Unidade" style="margin-top: 5px;">Unidade</label>
                                        <div class="col-md-6">
                                            <input type="hidden" value="${produtounidade.id_Produto}"
                                                   class="form-control" placeholder="Barras" name="id_Produto" id="id_Produto"/>
                                            <input type="hidden" value="${produtounidade.id_ProdutoUnidade}"
                                                   class="form-control" placeholder="Barras" name="id_ProdutoUnidade" id="id_ProdutoUnidade"/>

                                            <select class="form-control" name="id_Unidade" id="id_Unidade" required="true" disabled="true">
                                                <option value=""></option>
                                                <c:forEach items="${lista_unidade}" var="p">
                                                    <c:choose>
                                                        <c:when test="${p.id_Unidade != produtounidade.id_Unidade}">
                                                            <option value="${p.id_Unidade}">${p.descricao}</option>
                                                        </c:when>
                                                        <c:when test="${p.id_Unidade == produtounidade.id_Unidade}">
                                                            <option value="${p.id_Unidade}" selected="selected">${p.descricao}</option>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="codigoBarras" style="margin-top: 5px;">Código Barras</label>
                                        <div class="col-md-2">
                                            <input type="text" style="width:100%;" readonly="true" maxlength="20" value="${produtounidade.codigoBarras}"
                                                   class="form-control" placeholder="Barras" name="codigoBarras" id="codigoBarras"/>
                                        </div>
                                        <label class="col-md-2 control-label" for="FatorConversao" style="margin-top: 5px;">Fator Conversão</label>
                                        <div class="col-md-2">
                                            <input type="number" style="width:100%;" readonly="true" step="0.01" value="${produtounidade.fatorConversao}"
                                                   class="form-control" placeholder="Fator" name="fatorConversao" id="fatorConversao"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="pesoBruto" style="margin-top: 5px;">P.Bruto</label>
                                        <div class="col-md-2">
                                            <input type="number" style="width:100%;" readonly="true" step="0.01" value="${produtounidade.pesoBruto}"
                                                   class="form-control" placeholder="P.Bruto" name="pesoBruto" id="pesoBruto"/>
                                        </div>
                                        <label class="col-md-2 control-label" for="pesoBruto" style="margin-top: 5px;">P.Liquido</label>
                                        <div class="col-md-2">
                                            <input type="number" style="width:100%;" readonly="true" step="0.01" value="${produtounidade.pesoLiquido}"
                                                   class="form-control" placeholder="P.Bruto" name="pesoLiquido" id="pesoLiquido"/>
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
                                <a style="width: 80px" href="<c:url value="/produtounidade_lista/${produto.id_Produto}"/>" class="btn btn-primary" title="Voltar">Voltar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>


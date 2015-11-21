<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Detalhes do Plano de Contas</h1>
</div>

<div class="content body">
    <form action="#" method="post" >
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="id_Empresa" style="margin-top: 5px;">Empresa</label>
                                        <div class="col-md-8">
                                            <input type="hidden" id="id_PlanoContas" name="id_PlanoContas" value="${planocontas.id_PlanoContas}" >
                                            <select class="form-control" name="id_Empresa" id="id_Empresa" disabled="true">
                                                <c:forEach items="${listaempresa}" var="p">
                                                    <c:choose>
                                                        <c:when test="${p.id_Empresa != planocontas.id_Empresa}">
                                                            <option value="${p.id_Empresa}">${p.razaoSocial}</option>
                                                        </c:when>
                                                        <c:when test="${p.id_Empresa == planocontas.id_Empresa}">
                                                            <option value="${p.id_Empresa}" selected="selected">${p.razaoSocial}</option>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="codigoConta" style="margin-top: 5px;">Código</label>
                                        <div class="col-md-2">
                                            <input type="text" maxlength="20" disabled="true" value="${planocontas.codigoConta}"
                                                   class="form-control maiusculo" placeholder="Código" name="codigoConta" id="codigoConta"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-8">
                                            <input type="text" maxlength="50" disabled="true" value="${planocontas.descricao}"
                                                   class="form-control" placeholder="Descrição" name="descricao" id="descricao"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="tipoConta" style="margin-top: 5px;">Tipo</label>
                                        <div class="col-md-3">
                                            <select class="form-control" name="tipoConta" id="tipoConta" disabled="true">
                                                <option value=""></option>
                                                <c:choose>
                                                    <c:when test="${planocontas.tipoConta == 'C'}">
                                                        <option value="C" selected>Crédito</option>
                                                        <option value="D">Débito</option>
                                                    </c:when>
                                                    <c:when test="${planocontas.tipoConta == 'D'}">
                                                        <option value="C">Crédito</option>
                                                        <option value="D" selected>Débito</option>
                                                    </c:when>
                                                </c:choose>
                                            </select>
                                        </div>
                                        <label class="col-md-2 control-label" for="agrupamento" style="margin-top: 5px;">Agrupamento</label>
                                        <div class="col-md-3">
                                            <select class="form-control" name="agrupamento" id="agrupamento" disabled="true">
                                                <option value=""></option>
                                                <c:choose>
                                                    <c:when test="${planocontas.agrupamento == 'A'}">
                                                        <option value="A" selected>Analítico</option>
                                                        <option value="S">Sintético</option>
                                                    </c:when>
                                                    <c:when test="${planocontas.agrupamento == 'S'}">
                                                        <option value="A">Analítico</option>
                                                        <option value="S" selected>Sintético</option>
                                                    </c:when>
                                                </c:choose>
                                            </select>
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
                                <a style="width: 80px" href="<c:url value="/planocontas_lista"/>" class="btn btn-primary" title="Voltar">Voltar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>
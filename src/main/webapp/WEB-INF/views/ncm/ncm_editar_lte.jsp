<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Novo NCM</h1>
</div>

<div class="content body">
    <form action="/ncm_update" method="post">
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="codigoNCM" style="margin-top: 5px;">Código</label>
                                        <div class="col-md-2">
                                            <input type="hidden" maxlength="10" value="${ncm.id_NCM}"
                                                   class="form-control" placeholder="id_NCM" name="id_NCM" id="id_NCM"/>

                                            <input type="text" maxlength="10" style="width:100%;" required="autofocus" value="${ncm.codigoNCM}"
                                                   class="form-control" placeholder="Código" name="codigoNCM" id="codigoNCM"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-6">
                                            <input type="text" maxlength="50" style="width:100%;" required="true" value="${ncm.descricao}"
                                                   class="form-control" placeholder="Nome" name="descricao" id="descricao"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="aliquotaIPI" style="margin-top: 5px;">Aliquota IPI</label>
                                        <div class="col-md-2">
                                            <input type="number" maxlength="10" style="width:100%;" required="true" min="0.00" step="0.01"
                                                   class="form-control" placeholder="IPI" name="aliquotaIPI" id="aliquotaIPI" value="${ncm.aliquotaIPI}"/>
                                        </div>

                                        <label class="col-md-2 control-label" for="aliquotaII" style="margin-top: 5px;">Aliquota II</label>
                                        <div class="col-md-2">
                                            <input type="number" maxlength="10" style="width:100%;" required="true" min="0.00" step="0.01"
                                                   class="form-control" placeholder="II" name="aliquotaII" id="aliquotaII" value="${ncm.aliquotaII}"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="id_CSTPIS" style="margin-top: 5px;">CST PIS</label>
                                        <div class="col-md-6">
                                            <select class="form-control" name="id_CSTPIS" id="id_CSTPIS" required="true">
                                                <option value=""></option>
                                                <c:forEach items="${lista_cst}" var="p">
                                                    <c:choose>
                                                        <c:when test="${p.id_CSTPISCOFINS != ncm.id_CSTPIS}">
                                                            <option value="${p.id_CSTPISCOFINS}">${p.descricao}</option>
                                                        </c:when>
                                                        <c:when test="${p.id_CSTPISCOFINS == ncm.id_CSTPIS}">
                                                            <option value="${p.id_CSTPISCOFINS}" selected="selected">${p.descricao}</option>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="id_CSTCOFINS" style="margin-top: 5px;">CST COFINS</label>
                                        <div class="col-md-6">
                                            <select class="form-control" name="id_CSTCOFINS" id="id_CSTCOFINS" required="true">
                                                <option value=""></option>
                                                <c:forEach items="${lista_cst}" var="p">
                                                    <c:choose>
                                                        <c:when test="${p.id_CSTPISCOFINS != ncm.id_CSTCOFINS}">
                                                            <option value="${p.id_CSTPISCOFINS}">${p.descricao}</option>
                                                        </c:when>
                                                        <c:when test="${p.id_CSTPISCOFINS == ncm.id_CSTCOFINS}">
                                                            <option value="${p.id_CSTPISCOFINS}" selected="selected">${p.descricao}</option>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
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
                                <button class="btn btn-primary" type="submit" style="width: 80px;">Salvar</button>
                                <a style="width: 80px" href="<c:url value="/ncm_lista"/>" class="btn btn-primary" title="Cancelar">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>
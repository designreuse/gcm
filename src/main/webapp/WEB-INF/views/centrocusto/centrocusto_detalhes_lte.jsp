<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Detalhes do Centro de Custo</h1>
</div>

<div class="content body">
    <form action="#">
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="id_Empresa" style="margin-top: 5px;">Empresa</label>
                                        <div class="col-md-6">
                                            <select class="form-control" name="id_Empresa" id="id_Empresa" disabled>
                                                <c:forEach items="${listaempresa}" var="p">
                                                    <c:choose>
                                                        <c:when test="${p.id_Empresa != centrocusto.id_Empresa}">
                                                            <option value="${p.id_Empresa}">${p.razaoSocial}</option>
                                                        </c:when>
                                                        <c:when test="${p.id_Empresa == centrocusto.id_Empresa}">
                                                            <option value="${p.id_Empresa}" selected="selected">${p.razaoSocial}</option>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="sigla" style="margin-top: 5px;">Sigla</label>
                                        <div class="col-md-1">
                                            <input type="hidden" value="${centrocusto.id_CentroCusto}" name="id_CentroCusto" id="id_CentroCusto"/>

                                            <input type="text" maxlength="5" readonly="true" value="${centrocusto.sigla}"
                                                   class="form-control maiusculo" placeholder="Sigla" name="sigla" id="sigla"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-6">
                                            <input type="text" maxlength="50" readonly="true" value="${centrocusto.descricao}"
                                                   class="form-control" placeholder="Descricao" name="descricao" id="descricao"/>
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
                                <a style="width: 80px" href="<c:url value="/centrocusto_lista"/>" class="btn btn-primary" title="Voltar">Voltar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>
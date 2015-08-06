<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Editar SubGrupo de Produto</h1>
</div>

<div class="content body">
    <form action="/subgrupoproduto_update" method="post">
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <input type="hidden" maxlength="50" style="width:100%;" value="${subgrupoproduto.id_SubGrupoProduto}"
                                               class="form-control" placeholder="Descrição" name="id_SubGrupoProduto" id="id_SubGrupoProduto"/>

                                        <label class="col-md-1 control-label" for="id_GrupoProduto" style="margin-top: 5px;">Grupo</label>
                                        <div class="col-md-6">
                                            <select class="form-control" name="id_GrupoProduto" id="id_GrupoProduto" required="autofocus">
                                                <c:forEach items="${listagrupoproduto}" var="p">
                                                    <c:choose>
                                                        <c:when test="${p.id_GrupoProduto != subgrupoproduto.id_GrupoProduto}">
                                                            <option value="${p.id_GrupoProduto}">${p.descricao}</option>
                                                        </c:when>
                                                        <c:when test="${p.id_GrupoProduto == subgrupoproduto.id_GrupoProduto}">
                                                            <option value="${p.id_GrupoProduto}" selected="selected">${p.descricao}</option>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-6">
                                            <input type="text" maxlength="50" style="width:100%;" required="true" value="${subgrupoproduto.descricao}"
                                                   class="form-control" placeholder="Descrição" name="descricao" id="descricao"/>
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
                                <a style="width: 80px" href="<c:url value="/subgrupoproduto_lista"/>" class="btn btn-primary" title="Cancelar">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>
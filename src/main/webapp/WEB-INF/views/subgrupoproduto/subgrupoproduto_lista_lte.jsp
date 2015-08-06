<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Lista de SubGrupo de Produto</h1>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Filtros</h3>
                </div>
                <div class="box-body">
                    <form class="form-horizontal" action="/subgrupoproduto_lista" method="post">
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-1 control-label" for="id_GrupoProduto">Grupo</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="id_GrupoProduto" id="id_GrupoProduto">
                                        <option value="0"></option>
                                        <c:forEach items="${lista_grupoproduto}" var="p">
                                            <c:choose>
                                                <c:when test="${p.id_GrupoProduto != filtros.id_GrupoProduto}">
                                                    <option value="${p.id_GrupoProduto}">${p.descricao}</option>
                                                </c:when>
                                                <c:when test="${p.id_GrupoProduto == filtros.id_GrupoProduto}">
                                                    <option value="${p.id_GrupoProduto}" selected="selected">${p.descricao}</option>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                <label class="col-md-1 control-label" for="descricao">Descrição</label>
                                <div class="col-md-3">
                                    <input type="text" maxlength="50" value="${filtros.descricao}"
                                           class="form-control" name="descricao" placeholder="Nome" id="descricao"/>
                                </div>
                                <button style="width: 80px" class="btn btn-primary" type="reset">Limpar</button>
                                <button class="btn btn-primary" type="submit" style="width: 80px;">Pesquisar</button>
                            </div>
                            <fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="height: 320px; overflow:auto;">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <table class="table table-bordered  table-hover table-striped">
                        <thead style="background-color:silver">
                        <tr>
                            <th style="width:100px; text-align:center;">Operações</th>
                            <th style="width:350px;">Grupo</th>
                            <th>Descrição</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${subgrupoproduto_lista}" var="p">
                            <tr>
                                <td>
                                    <a href="/subgrupoproduto_deleta/${p.id_SubGrupoProduto}" class="btn btn-default btn-xs" title="Deletar">
                                        <i class="fa fa-trash-o"></i>
                                    </a>
                                    <a href="/subgrupoproduto_editar/${p.id_SubGrupoProduto}" class="btn btn-default btn-xs" title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a href="/subgrupoproduto_detalhes/${p.id_SubGrupoProduto}" class="btn btn-default btn-xs" title="Detalhes">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </td>
                                <td>${p.descricaoGrupo}</td>
                                <td>${p.descricao}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <div class="col-sm-9">
                        <vls:paginador pagina="${pagina}"/>
                    </div>
                    <div class="col-sm-3" style="margin-top: 15px;">
                        <a style="width: 80px" href="<c:url value="/subgrupoproduto_novo"/>" class="btn btn-primary" title="Novo">
                            <i class="fa fa-plus"></i> Novo
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Lista de Países</h1>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Filtros</h3>
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                    </div>
                </div>
                <div class="box-body">
                    <form class="form-horizontal" action="/pais_lista" method="post">
                    <fieldset>
                        <div class="form-group">
                            <label class="col-md-1 control-label" for="siglapais">Sigla</label>
                            <div class="col-md-2">
                                <input type="text" maxlength="5" value="${filtros.siglapais}"
                                       class="form-control maiusculo" name="siglapais" placeholder="Sigla" id="siglapais"/>
                            </div>

                            <label class="col-md-1 control-label" for="descricao">Nome</label>
                            <div class="col-md-4">
                                <input type="text" maxlength="50" value="${filtros.descricao}"
                                       class="form-control maiusculo" name="descricao" placeholder="Nome" id="descricao"/>
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
                            <th style="width:80px;">Sigla</th>
                            <th>Nome</th>
                            <th style="width:100px;">IBGE</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pais_lista}" var="p">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${deletar == 'true'}">
                                            <a href="/pais_deleta/${p.id_pais}" class="btn btn-default btn-xs" title="Deletar">
                                                <i class="fa fa-trash-o"></i>
                                            </a>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${editar == 'true'}">
                                            <a href="/editar_pais/${p.id_pais}" class="btn btn-default btn-xs" title="Editar">
                                                <i class="fa fa-pencil"></i>
                                            </a>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${detalhes == 'true'}">
                                            <a href="/pais_detalhes/${p.id_pais}" class="btn btn-default btn-xs" title="Detalhes">
                                                <i class="fa fa-eye"></i>
                                            </a>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${p.siglapais}</td>
                                <td>${p.descricao}</td>
                                <td>${p.ibge}</td>
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
                        <c:choose>
                            <c:when test="${novo == 'true'}">
                                <a style="width: 80px" href="<c:url value="/pais_novo"/>" class="btn btn-primary" title="Novo">
                                    <i class="fa fa-plus"></i> Novo
                                </a>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
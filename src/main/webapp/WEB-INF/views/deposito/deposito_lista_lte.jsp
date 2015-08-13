<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Lista de Depósitos</h1>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Filtros</h3>
                </div>
                <div class="box-body">
                    <form class="form-horizontal" action="/deposito_lista" method="post">
                        <fieldset>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="id_Empresa">Empresa</label>
                                    <div class="col-md-7">
                                        <select class="form-control" name="id_Empresa" id="id_Empresa">
                                            <option value="0"></option>
                                            <c:forEach items="${lista_empresa}" var="p">
                                                <c:choose>
                                                    <c:when test="${p.id_Empresa != filtros.id_Empresa}">
                                                        <option value="${p.id_Empresa}">${p.razaoSocial}</option>
                                                    </c:when>
                                                    <c:when test="${p.id_Empresa == filtros.id_Empresa}">
                                                        <option value="${p.id_Empresa}" selected="selected">${p.razaoSocial}</option>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>
                                    <button style="width: 80px" class="btn btn-primary" type="reset">Limpar</button>
                                    <button class="btn btn-primary" type="submit" style="width: 80px;">Pesquisar</button>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="height: 280px; overflow:auto;">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <table class="table table-bordered  table-hover table-striped">
                        <thead style="background-color:silver">
                        <tr>
                            <th style="width:100px; text-align:center;">Operações</th>
                            <th style="width:80px;">Empresa</th>
                            <th style="width:80px;">ID</th>
                            <th>Descrição</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${deposito_lista}" var="p">
                            <tr>
                                <td>
                                    <a href="/deposito_deleta/${p.id_Deposito}" class="btn btn-default btn-xs" title="Deletar">
                                        <i class="fa fa-trash-o"></i>
                                    </a>
                                    <a href="/deposito_editar/${p.id_Deposito}" class="btn btn-default btn-xs" title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a href="/deposito_detalhes/${p.id_Deposito}" class="btn btn-default btn-xs" title="Detalhes">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </td>
                                <td>${p.id_Empresa}</td>
                                <td>${p.id_Deposito}</td>
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
                        <a style="width: 80px" href="<c:url value="/deposito_novo"/>" class="btn btn-primary" title="Novo">
                            <i class="fa fa-plus"></i> Novo
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


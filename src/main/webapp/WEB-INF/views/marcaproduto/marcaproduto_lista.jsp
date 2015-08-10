<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>


<div class="content-header">
    <h1>Lista de Marcas</h1>
</div>

<div class="content body">
    <!-- Inicio do Box de Filtro -->
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Filtro</h3>
                </div>
                <div class="box-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-1 control-label" for="id_marcaProduto">ID</label>
                            <div class="col-md-2">
                                <input id="id_marcaProduto" class="form-control" placeholder="ID" type="number" />
                            </div>

                            <label class="col-md-1 control-label" for="descricao">Descricao</label>
                            <div class="col-md-4">
                                <input id="descricao" class="form-control" placeholder="Descrição..." type="text" />
                            </div>
                            <button class="btn btn-primary" type="reset">Limpar</button>
                            <button class="btn btn-primary" type="submit">Pesquisar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Fim do Box de Filtro -->

    <div class="row" style="height: 280px; overflow:auto;">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <table class="table table-bordered  table-hover table-striped">
                        <thead>
                        <tr>
                            <th style="width:10%; text-align:center;">Operações</th>
                            <th style="width:10%; text-align:right;">ID</th>
                            <th>Descrição</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%--marcaproduto_lista--%>
                        <c:forEach items="${marcaproduto_lista}" var="m">
                            <tr>
                                <td align="center">
                                    <a href="/marcaproduto_deleta/${m.id_MarcaProduto}" class="btn btn-default btn-xs" title="Deletar">
                                        <i class="fa fa-trash-o"></i>
                                    </a>
                                    <a href="/marcaproduto_editar/${m.id_MarcaProduto}" class="btn btn-default btn-xs" title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                </td>
                                <td align="right">${m.id_MarcaProduto}</td>
                                <td>${m.descricao}</td>
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
                        <a style="width: 80px" href="<c:url value="/marcaproduto_novo"/>" class="btn btn-primary" title="Novo">
                            <i class="fa fa-plus"></i> Novo
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
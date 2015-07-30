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
                    <h3 class="box-title">Filtro</h3>
                </div>
                <div class="box-body">
                    <form class="form-horizontal">
                    <c:url value="/pais_lista" var="filtros"/>
                    <form:form class="form-signin" modelAttribute="filtros" id="FormCadastro" action="${filtros}" method="POST">
                        <div class="form-group">
                            <label class="col-md-1 control-label" for="Sigla">Sigla</label>
                            <div class="col-md-2">
                                <form:input path="siglapais" type="text" maxlength="5"
                                            class="form-control maiusculo" name="Id" placeholder="Sigla"  id="Sigla"/>
                            </div>

                            <label class="col-md-1 control-label" for="Nome">Nome</label>
                            <div class="col-md-4">
                                <form:input path="descricao" type="text" maxlength="50"
                                            class="form-control maiusculo" name="Id" placeholder="Nome" id="Nome"/>
                            </div>
                            <button style="width: 80px" class="btn btn-primary" type="reset">Limpar</button>
                            <form:button class="btn btn-primary" type="submit" style="width: 80px;">Pesquisar</form:button>
                        </div>
                    </form:form>
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
                                    <a href="/pais_deleta/${p.id_pais}" class="btn btn-default btn-xs" title="Deletar">
                                        <i class="fa fa-trash-o"></i>
                                    </a>
                                    <a href="/editar_pais/E/${p.id_pais}" class="btn btn-default btn-xs" title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a href="/editar_pais/D/${p.id_pais}" class="btn btn-default btn-xs" title="Detalhes">
                                        <i class="fa fa-eye"></i>
                                    </a>
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
        <div class="box box-primary">
            <div class="box-body">
                <div class="col-sm-9">
                    <vls:paginador pagina="${pagina}"/>
                </div>
                <div class="col-sm-3" style="margin-top: 15px;">
                    <a style="width: 80px" href="<c:url value="/pais_novo"/>" class="btn btn-primary" title="Novo">
                        <i class="fa fa-plus"></i> Novo
                    </a>
                    <a style="width: 100px" href="<c:url value="/report/pais_report/pdf"/>" class="btn btn-primary" title="Imprimir">
                        <i class="fa fa-print"></i> Imprimir
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
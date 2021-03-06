<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Lista de Unidades</h1>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Filtros</h3>
                </div>
                <div class="box-body">
                    <form class="form-horizontal" action="/unidade_lista" method="post">
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-1 control-label" for="sigla">Sigla</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="10" value="${filtros.sigla}"
                                           class="form-control maiusculo" name="sigla" placeholder="Sigla" id="sigla"/>
                                </div>

                                <label class="col-md-1 control-label" for="descricao">Descrição</label>
                                <div class="col-md-4">
                                    <input type="text" maxlength="50" value="${filtros.descricao}"
                                           class="form-control maiusculo" name="descricao" placeholder="Descrição" id="descricao"/>
                                </div>

                                <button style="width: 80px" class="btn btn-primary" type="reset">Limpar</button>
                                <button class="btn btn-primary" type="submit" style="width: 80px;">Pesquisar</button>
                            </div>
                        </fieldset>
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
                            <th>Descrição</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${unidade_lista}" var="p">
                            <tr>
                                <td>
                                    <a href="/unidade_deleta/${p.id_Unidade}" class="btn btn-default btn-xs" title="Deletar">
                                        <i class="fa fa-trash-o"></i>
                                    </a>
                                    <a href="/unidade_editar/${p.id_Unidade}" class="btn btn-default btn-xs" title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a href="/unidade_detalhes/${p.id_Unidade}" class="btn btn-default btn-xs" title="Detalhes">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </td>
                                <td>${p.sigla}</td>
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
                    <div class="col-sm-3" style="margin-top: 15px; text-align: right;">
                        <a style="width: 80px" href="<c:url value="/unidade_novo"/>" class="btn btn-primary" title="Novo">
                            <i class="fa fa-plus"></i> Novo
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
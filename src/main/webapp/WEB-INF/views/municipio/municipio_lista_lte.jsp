<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Lista de Municípios</h1>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Filtros</h3>
                </div>

                <div class="box-body">
                    <form class="form-horizontal" action="/municipio_lista" method="post">
                        <fieldset>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="id_pais">País</label>
                                    <div class="col-md-2">
                                        <select class="form-control" id="id_pais" name="id_pais">
                                            <c:forEach items="${lista_pais}" var="p">
                                                <c:choose>
                                                    <c:when test="${p.id_pais != filtros.id_pais}">
                                                        <option value="${p.id_pais}">${p.descricao}</option>
                                                    </c:when>
                                                    <c:when test="${p.id_pais == filtros.id_pais}">
                                                        <option value="${p.id_pais}" selected="selected">${p.descricao}</option>
                                                    </c:when>
                                                </c:choose>
                                            </c:forEach>
                                        </select>
                                    </div>

                                    <label class="col-md-1 control-label" for="id_Uf">UF</label>
                                    <div class="col-md-2">
                                        <select class="form-control" name="id_Uf" id="id_Uf" name="id_Uf">
                                        </select>
                                    </div>

                                    <label class="col-md-1 control-label" for="descricao">Nome</label>
                                    <div class="col-md-3">
                                        <input type="text" maxlength="50"
                                               class="form-control maiusculo" name="descricao" placeholder="Nome" id="descricao" name="descricao"/>
                                    </div>
                                    <button style="width: 80px" class="btn btn-primary" type="reset">Limpar</button>
                                    <button class="btn btn-primary" type="submit" style="width: 80px;">Pesquisar</button>
                                </div>
                            </div>
                        <fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="height: 250px; overflow:auto;">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <table class="table table-bordered  table-hover table-striped">
                        <thead style="background-color:silver">
                        <tr>
                            <th style="width:100px; text-align:center;">Operações</th>
                            <th style="width:80px;">País</th>
                            <th style="width:80px;">UF</th>
                            <th>Nome</th>
                            <th style="width:100px;">IBGE</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${municipio_lista}" var="p">
                            <tr>
                                <td>
                                    <a href="/municipio_deleta/${p.id_municipio}" class="btn btn-default btn-xs" title="Deletar">
                                        <i class="fa fa-trash-o"></i>
                                    </a>
                                    <a href="/editar_municipio/${p.id_municipio}" class="btn btn-default btn-xs" title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a href="/municipio_detalhes/${p.id_municipio}" class="btn btn-default btn-xs" title="Detalhes">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </td>
                                <td>${p.siglapais}</td>
                                <td>${p.siglauf}</td>
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
                        <a style="width: 80px" href="<c:url value="/municipio_novo"/>" class="btn btn-primary" title="Novo">
                            <i class="fa fa-plus"></i> Novo
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $("#id_pais").change();

    $("#id_pais").change(function(){
        var id_pais = $(this).val();
        if( id_pais.length <= 0 ) return;
        $.getJSON("${pageContext.request.contextPath}/carregauf-pais/"+id_pais,
                function(result){
                    $('#id_Uf').empty();
                    $.each(result, function(i){
                        $("#id_Uf").append('<option value="' + result[i].id_Uf + '">' + result[i].siglaUf + '</option>');
                    });
                });
    });

</script>
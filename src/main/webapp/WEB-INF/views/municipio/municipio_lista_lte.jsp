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
                    <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus"></i></button>
                    </div>
                </div>

                <div class="box-body">
                    <form class="form-horizontal" action="/municipio_lista" method="post">
                        <fieldset>
                            <div class="row">
                                <div class="form-group">
                                    <label class="col-md-1 control-label" for="id_pais">País</label>
                                    <div class="col-md-2">
                                        <select class="form-control" id="id_pais" name="id_pais">
                                            <option value="0"></option>
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

                                    <label class="col-md-1 control-label" for="id_uf">UF</label>
                                    <div class="col-md-2">
                                        <select class="form-control" name="id_uf" id="id_uf">
                                        </select>
                                    </div>

                                    <label class="col-md-1 control-label" for="descricao">Nome</label>
                                    <div class="col-md-2">
                                        <input type="text" maxlength="50" value="${filtros.descricao}"
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

    <div class="row" style="height: 300px; overflow:auto;">
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
                                    <c:choose>
                                        <c:when test="${deletar == 'true'}">
                                            <a href="/municipio_deleta/${p.id_municipio}" class="btn btn-default btn-xs" title="Deletar">
                                                <i class="fa fa-trash-o"></i>
                                            </a>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${editar == 'true'}">
                                            <a href="/editar_municipio/${p.id_municipio}" class="btn btn-default btn-xs" title="Editar">
                                                <i class="fa fa-pencil"></i>
                                            </a>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${detalhes == 'true'}">
                                            <a href="/detalhes_municipio/${p.id_municipio}" class="btn btn-default btn-xs" title="Detalhes">
                                                <i class="fa fa-eye"></i>
                                            </a>
                                        </c:when>
                                    </c:choose>
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
                        <c:choose>
                            <c:when test="${novo == 'true'}">
                                <a style="width: 80px" href="<c:url value="/municipio_novo"/>" class="btn btn-primary" title="Novo">
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

<script>
    $(window).load(function() {
        carrega_uf($("#id_pais").val());
    });

    $("#id_pais").change(function(){
        carrega_uf($(this).val());
    });

    function carrega_uf(id){
        var id_pais = id;
        if( id_pais.length <= 0 ) return;
        $.getJSON("${pageContext.request.contextPath}/carregauf-pais/"+id_pais,
                function(result){
                    $('#id_uf').empty();
                    $("#id_uf").append('<option value="0"></option>');
                    $.each(result, function(i){
                        if (result[i].id_Uf != ${filtros.id_uf}){
                            $("#id_uf").append('<option value="' + result[i].id_Uf + '">' + result[i].siglaUf + '</option>');
                        } else {
                            $("#id_uf").append('<option value="' + result[i].id_Uf + '" selected="selected">' + result[i].siglaUf + '</option>');
                        }

                    });
                });
    };

</script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Detalhes Município</h1>
</div>

<div class="content body">

    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="row">
                                <input type="hidden" maxlength="3" style="width:60px;"
                                       class="form-control maiusculo" name="id_municipio" id="id_municipio" value="${municipio.id_municipio}"/>

                                <label class="col-md-1 control-label" for="id_pais" style="margin-top: 5px;">Pais</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="id_pais" id="id_pais" required="autofocus" disabled="true">
                                        <c:forEach items="${lista_pais}" var="p">
                                            <c:choose>
                                                <c:when test="${p.id_pais != municipio.id_pais}">
                                                    <option value="${p.id_pais}">${p.descricao}</option>
                                                </c:when>
                                                <c:when test="${p.id_pais == municipio.id_pais}">
                                                    <option value="${p.id_pais}" selected="selected">${p.descricao}</option>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                <label class="col-md-1 control-label" for="id_uf" style="margin-top: 5px;">UF</label>
                                <div class="col-md-4">
                                    <select class="form-control" name="id_uf" id="id_uf" required="true" disabled="true">
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-1 control-label" for="descricao" style="margin-top: 5px;">Nome</label>
                                <div class="col-md-6">
                                    <input type="text" maxlength="50" style="width:100%;" required="true" value="${municipio.descricao}"
                                           class="form-control maiusculo" placeholder="Nome" name="descricao" id="descricao" readonly="true"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-1 control-label" for="ibge" style="margin-top: 5px;">Ibge</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="5" style="width:100%;" value="${municipio.ibge}" readonly="true"
                                           class="form-control maiusculo" placeholder="ibge" name="ibge" id="ibge"/>
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
                        <a style="width: 80px" href="<c:url value="/municipio_lista"/>" class="btn btn-primary" title="Voltar">Voltar</a>
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
                    $.each(result, function(i){
                        if (result[i].id_Uf != ${municipio.id_uf}) {
                            $("#id_uf").append('<option value="' + result[i].id_Uf + '">' + result[i].descricao + '</option>');
                        } else {
                            $("#id_uf").append('<option value="' + result[i].id_Uf + '" selected="selected">' + result[i].descricao + '</option>');
                        };
                    });
                });
    };

</script>
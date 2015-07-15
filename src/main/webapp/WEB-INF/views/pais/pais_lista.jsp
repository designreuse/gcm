<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="brand" style="font: bold; margin-left: 350px;">Lista de Países</a>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="navbar-inner" style="height: 350px; overflow:auto;">
        <br>
        <div id="div_filtros" class="container-fluid navbar navbar-inner">
            <c:url value="/pais_lista" var="filtros"/>
            <form:form class="form-signin" modelAttribute="filtros" id="FormCadastro" action="${filtros}" method="POST">
                <div class="row-fluid">
                    <div class="span1" style="text-align: left; padding-top: 5px;">
                        <label style="font-size: 14px; margin-top:5px;">Sigla</label>
                    </div>
                    <div class="span2" style="text-align: left; padding-top: 5px;">
                        <form:input path="siglapais" type="text" maxlength="5"
                                    class="form-control maiusculo" name="Id" placeholder="Sigla"
                                    style="width:80px;" id="Sigla"/>
                    </div>
                    <div class="span1" style="text-align: left; padding-top: 5px;">
                        <label style="font-size: 14px; margin-top:5px;">Nome</label>
                    </div>
                    <div class="span4" style="text-align: left; padding-top: 5px;">
                        <form:input path="descricao" type="text" maxlength="50"
                                    class="form-control maiusculo" name="Id" placeholder="Nome"
                                    style="width:450px;" id="Nome"/>
                    </div>
                </div>
                <div class="row-fluid" style="height: 20px;">
                    <div class="span12">
                        <form:button class="btn btn-lg btn-primary" type="submit" style="width: 90px;"><span class="icon-search"></span> Filtrar</form:button>
                        <button class="btn btn-lg btn-primary limpar" type="submit" style="width: 90px;"><span class="icon-remove"></span> Limpar</button>
                    </div>
                </div>
            </form:form>
        </div>

        <table class="table table-striped table-bordered table-hover table-condensed active tabelaPaginada" id="example">
            <thead style="background-color:silver">
                <tr>
                    <th style="width:60px;"></th>
                    <th style="width:80px;">Sigla</th>
                    <th>Nome</th>
                    <th style="width:100px;">IBGE</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pais_lista}" var="p">
                <tr>
                    <td>
                        <a href="/pais_deleta/${p.id_pais}" class="btn btn-mini apagar" title="Deletar">
                            <i class="icon-trash"></i>
                        </a>
                        <a href="/editar_pais/${p.id_pais}" class="btn btn-mini" title="Editar">
                            <i class="icon-pencil"></i>
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
<br>
<div class="container">
    <div class="navbar-inner">
        <div class="row-fluid">
            <div class="span6">
                <vls:paginador pagina="${pagina}"/>
            </div>
            <div class="span6" style="text-align: right;" >
                <br>
                <a href="<c:url value="/pais_novo"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
                <a target="_blank" href="<c:url value="/report/pais_report/pdf"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-list-alt"></span> Imprimir</a>
            </div>
        </div>
    </div>
</div>

<script>
    $(".Filtros").click(function(){
        if ($("#div_filtros").is(':visible'))
        {
            $("#div_filtros").hide();
        }
        else
        {
            filt = 0;
            $("#div_filtros").show();
        }
    });

    $(".limpar").click(function(){
        $("#Nome").val("");
        $("#Sigla").val("");
    });
</script>


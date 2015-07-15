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
                <a class="brand" style="font: bold; margin-left: 300px;">Lista de Plano de Contas</a>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="navbar-inner" style="overflow:auto; height: 350px;">
        <br>
        <div class="row-fluid">
            <div class="span1" style="text-align: left;">
                <label style="font-size: 16px; margin-top:5px;">Empresa</label>
            </div>
            <div class="span11" style="text-align: left;">
                <select id="Empresas" style="width: 820px;">
                    <c:forEach var="listaempresa" items="${listaempresa}">
                        <c:choose>
                            <c:when test="${filtro == listaempresa.id_Empresa}">
                                <option value="${listaempresa.id_Empresa}" selected="selected">${listaempresa.razaoSocial}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${listaempresa.id_Empresa}">${listaempresa.razaoSocial}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
        </div>
        <table class="table table-striped table-bordered table-hover table-condensed active tabelaPaginada" id="example">
            <thead style="background-color:silver">
            <tr>
                <th style="width:60px;"></th>
                <th style="width:100px;">Código</th>
                <th>Descrição</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${lista}" var="p">
                <tr>
                    <td>
                        <a href="/planocontas_deleta/${p.id_PlanoContas}" class="btn btn-mini apagar" title="Deletar">
                            <i class="icon-trash"></i>
                        </a>
                        <a href="/planocontas_editar/${p.id_PlanoContas}" class="btn btn-mini" title="Editar">
                            <i class="icon-pencil"></i>
                        </a>
                    </td>
                    <td>${p.codigoConta}</td>
                    <td>${p.descricao}</td>
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
                <a href="<c:url value="/planocontas_novo"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
            </div>
        </div>
    </div>
</div>

<script>
    jQuery(function($){
        $("#Empresas").change(function(){
            location.href="/planocontas_filtro/"+ $(this).val();
        });
    });
</script>

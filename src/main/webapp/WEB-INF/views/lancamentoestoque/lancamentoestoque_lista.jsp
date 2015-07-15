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
                <c:choose>
                    <c:when test="${TipoEntSai == 'E'}">
                        <a class="brand" style="font: bold; margin-left: 35%">Entradas por Ajuste</a>
                    </c:when>
                    <c:when test="${TipoEntSai == 'S'}">
                        <a class="brand" style="font: bold; margin-left: 35%;">Saídas por Ajuste</a>
                    </c:when>
                </c:choose>
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
                <th style="width:60px;">ID</th>
                <th>Operação</th>
                <th style="width:100px;">Data</th>
                <th style="width:80px;">Atualizado</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${lista}" var="p">
                <tr>
                    <td>
                        <a href="/lancamentoestoque_deleta/${p.id_LancamentoEstoque}" class="btn btn-mini apagar" title="Deletar">
                            <i class="icon-trash"></i>
                        </a>
                        <a href="/lancamentoestoque_editar/${TipoEntSai}/${p.id_LancamentoEstoque}" class="btn btn-mini" title="Editar">
                            <i class="icon-pencil"></i>
                        </a>
                    </td>
                    <td>${p.id_LancamentoEstoque}</td>
                    <td>${p.cfop.descricao}</td>
                    <td>${p.dataLancamento}</td>
                    <c:choose>
                        <c:when test="${p.atualizado == true}">
                            <td>Sim</td>
                        </c:when>
                        <c:when test="${p.atualizado == false}">
                            <td>Não</td>
                        </c:when>
                    </c:choose>
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
                <a href="<c:url value="/lancamentoestoque_novo/${TipoEntSai}"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
            </div>
        </div>
    </div>
</div>

<script>
    jQuery(function($){
        $("#Empresas").change(function(){
            location.href="/lancamentoestoque_filtro/${TipoEntSai}/"+ $(this).val();
        });
    });
</script>

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
                <a class="brand" style="font: bold; margin-left: 40%;">Lista de Empresas</a>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="navbar-inner" style="height: 350px; overflow:auto;">
        <br>
        <table class="table table-striped table-bordered table-hover table-condensed active tabelaPaginada" id="example">
            <thead style="background-color:silver">
                <tr>
                    <th style="width:80px;"></th>
                    <th style="width:80px;">ID</th>
                    <th style="width:150px;">Cnpj</th>
                    <th style="width:250px;">Razão Social</th>
                    <th>Nome Fantasia</th>
                </tr>
            </thead>
            <tbody>
            <c:forEach items="${empresa_lista}" var="emp">
                <tr>
                    <td>
                        <a href="/empresa_deleta/${emp.id_Empresa}" class="btn btn-mini apagar" title="Editar">
                            <i class="icon-trash"></i>
                        </a>
                        <a href="/empresa_editar/${emp.id_Empresa}" class="btn btn-mini" title="Editar">
                            <i class="icon-pencil"></i>
                        </a>
                    </td>
                    <td>${emp.id_Empresa}</td>
                    <td>${emp.cnpj}</td>
                    <td>${emp.razaoSocial}</td>
                    <td>${emp.nomeFantasia}</td>
                </tr>
            </c:forEach>
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
                <a href="<c:url value="/empresa_novo"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
            </div>
        </div>
    </div>
</div>


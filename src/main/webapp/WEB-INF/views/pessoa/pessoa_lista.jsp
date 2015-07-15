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
                    <c:when test="${tipo == 'CLI'}">
                        <a class="brand" style="font: bold; margin-left: 40%">Lista de Clientes</a>
                    </c:when>
                    <c:when test="${tipo == 'FOR'}">
                        <a class="brand" style="font: bold; margin-left: 35%;">Lista de Fornecedores</a>
                    </c:when>
                    <c:when test="${tipo == 'FUN'}">
                        <a class="brand" style="font: bold; margin-left: 35%;">Lista de Funcionários</a>
                    </c:when>
                    <c:when test="${tipo == 'VEN'}">
                        <a class="brand" style="font: bold; margin-left: 40%;">Lista de Vendedores</a>
                    </c:when>
                    <c:when test="${tipo == 'TRA'}">
                        <a class="brand" style="font: bold; margin-left: 35%;">Lista de Transportadores</a>
                    </c:when>
                    <c:when test="${tipo == 'CON'}">
                        <a class="brand" style="font: bold; margin-left: 40%;">Lista de Convênios</a>
                    </c:when>
                    <c:when test="${tipo == 'HOS'}">
                        <a class="brand" style="font: bold; margin-left: 40%;">Lista de Hospitais</a>
                    </c:when>
                    <c:when test="${tipo == 'MED'}">
                        <a class="brand" style="font: bold; margin-left: 40%;">Lista de Médicos</a>
                    </c:when>
                    <c:when test="${tipo == 'ENF'}">
                        <a class="brand" style="font: bold; margin-left: 40%;">Lista de Enfermeiros</a>
                    </c:when>
                    <c:when test="${tipo == 'PAC'}">
                        <a class="brand" style="font: bold; margin-left: 40%;">Lista de Paciêntes</a>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <c:url value="/pessoa_listafiltros/${tipo}" var="filtrar"/>
            <form:form class="form-signin" modelAttribute="filtros" id="Filtros" action="${filtrar}" method="POST">
                <div class="container-fluid">
                    <br>
                    <div class="row-fluid">
                        <div class="span1" style="text-align: left;">
                            <label style="font-size: 16px; margin-top:10px;">Filtros</label>
                        </div>
                        <div class="span9" style="text-align: left; margin-top:4px;">
                            <form:input path="filtro" type="text"  maxlength="50"
                                        class="form-control" name="filtro" placeholder="Digite o Cpf, Cnpj ou Razão Social"
                                        style="width:650px;" />
                        </div>
                        <div class="span2" align="right">
                            <form:button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 80px;"><span class="icon-search"></span> Filtrar</form:button>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>

<div class="container">
    <div class="navbar-inner" style="overflow:auto; height: 270px;">
        <br>
        <table class="table table-striped table-bordered table-hover table-condensed active tabelaPaginada" id="example">
            <thead style="background-color:silver">
            <tr>
                <th style="width:60px;"></th>
                <th style="width:40px;">Id</th>
                <th style="width:120px;">Cpf/Cnpj</th>
                <th>Razao Social</th>
                <th style="width:40px;">Ativo</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${lista}" var="p">
                <tr>
                    <td>
                        <a href="/pessoa_inativar/${tipo}/${p.id_Pessoa}" class="btn btn-mini apagar" title="Inativar">
                            <i class="icon-remove-sign"></i>
                        </a>
                        <a href="/pessoa_editar/${tipo}/${p.id_Pessoa}" class="btn btn-mini" title="Editar">
                            <i class="icon-pencil"></i>
                        </a>
                    </td>
                    <td>${p.id_Pessoa}</td>
                    <td>${p.cpfCnpj}</td>
                    <td>${p.razaoSocial}</td>
                    <td>${p.ativo}</td>
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
                <a href="<c:url value="/pessoa_novo/${tipo}"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
            </div>
        </div>
    </div>
</div>



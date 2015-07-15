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
                <a class="brand" style="font: bold; margin-left: 40%;">Unidade Federativa</a>
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
                    <th style="width:80px;">Pais</th>
                    <th style="width:80px;">UF</th>
                    <th>Nome</th>
                    <th style="width:100px;">Ibge</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${uf_lista}" var="u">
                    <tr>
                        <td>
                            <a href="/uf_deleta/${u.id_Uf}" class="btn btn-mini apagar" title="Deleta">
                                <i class="icon-trash"></i>
                            </a>
                            <a href="/editar_uf/${u.id_Uf}" class="btn btn-mini" title="Editar">
                                <i class="icon-pencil"></i>
                            </a>
                        </td>
                        <td>${u.siglaPais}</td>
                        <td>${u.siglaUf}</td>
                        <td>${u.descricao}</td>
                        <td>${u.ibge}</td>
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
                <a href="<c:url value="/uf_novo"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
            </div>
        </div>
    </div>
</div>







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
                <a class="brand" style="font: bold; margin-left: 30%;">Lista de Grupos de Usuários</a>
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
                <th>Descrição</th>
                <th style="width:100px;"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${grupousuario_lista}" var="lista">
            <tr>
                <td>
                    <a href="/grupousuario_deleta/${lista.id_GrupoUsuario}" class="btn btn-mini apagar" title="Excluir">
                        <i class="icon-trash"></i>
                    </a>
                    <a href="/grupousuario_editar/${lista.id_GrupoUsuario}" class="btn btn-mini" title="Editar">
                        <i class="icon-pencil"></i>
                    </a>
                </td>
                <td>${lista.id_GrupoUsuario}</td>
                <td>${lista.descricao}</td>
                <td>
                    <a href="/usuariodogrupo_novo/${lista.id_GrupoUsuario}" class="btn btn-mini" title="Usuarios do Grupo">
                        <i class="icon-user"></i>
                    </a>
                    <a href="/grupotransacao_novo/${lista.id_GrupoUsuario}" class="btn btn-mini" title="Permissoes do Grupo">
                        <i class="icon-ok-sign"></i>
                    </a>
                    <a href="/empresagrupo_novo/${lista.id_GrupoUsuario}" class="btn btn-mini" title="Empresas">
                        <i class="icon-folder-close"></i>
                    </a>
                </td>
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
                <a href="<c:url value="/grupousuario_novo"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
            </div>
        </div>
    </div>
</div>


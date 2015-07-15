<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--@elvariable id="active_menu" type="java.lang.String"--%>

<!-- ROLE_ADMIN -->
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <div class="well" style="max-width: 340px; padding: 8px 0;">
        <ul class="nav nav-list">
            <li class="nav-header" style="font-size: small; font-weight: bold">Geral</li>
            <li class="divider"></li>
            <li>
                <a href="<c:url value='/logout' />"><i class="fa fa-sign-out"></i> Sair</a>
            </li>
            <li class="nav-header" style="font-size: small; font-weight: bold">Cadastros</li>
            <li class="divider"></li>
            <li id="grupoBtn" <c:if test="${active_menu == 'grupo'}">class='active'</c:if>>
                <a href="<c:url value='/grupos' />"><i class="fa fa-building"></i> Grupos de Estabs.</a>
            </li>
            <li id="estabelecimentoAdminBtn" <c:if test="${active_menu == 'estab'}">class='active'</c:if>>
                <a href="<c:url value='/estabs' />"><i class="fa fa-building"></i> Estabelecimentos</a>
            </li>
            <li id="usuarioAdminBtn" <c:if test="${active_menu == 'usuar'}">class='active'</c:if>>
                <a href="<c:url value='/usuarios' />"><i class="fa fa-user"></i> Usuários</a>
            </li>
            <li id="documentosAdminBtn">
                <a href="<c:url value='/docs' />"><i class="fa fa-folder-open"></i> Documentos</a>
            </li>
        </ul>
    </div>
</sec:authorize>

<!-- ROLE_USER -->
<sec:authorize access="hasRole('ROLE_USER')">
    <div class="well" style="max-width: 340px; padding: 8px 0;">
        <ul class="nav nav-list">
            <li class="nav-header" style="font-size: small; font-weight: bold">Menu</li>
            <li class="divider"></li>
            <li id="documentosUserBtn">
                <a href="<c:url value='/docs' />"><i class="fa fa-folder-open"></i> Documentos</a>
            </li>
            <li>
                <a href="<c:url value='/logout' />"><i class="fa fa-sign-out"></i> Sair</a>
            </li>
    </ul>
</div>
</sec:authorize>

<!-- http://fontawesome.io/3.2.1/icons/ -->
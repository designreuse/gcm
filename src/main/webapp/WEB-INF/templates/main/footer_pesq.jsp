<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.apache.commons.lang3.text.WordUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%
    // pega as credenciais
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String nome;
    UserDetails userDetails = (UserDetails) principal;
    nome = userDetails.getUsername();
    nome = WordUtils.capitalizeFully(nome);
%>

<body>
<div class="navbar navbar-fixed-bottom navbar-inner">
    <div class="row-fluid">
        <div class="span4" style="text-align: left;">
            <a class="label label-info" style="font-size: 14px; margin-top:10px;">Usuário Logado: <%=nome%></a>
        </div>
    </div>
</div>
</body>
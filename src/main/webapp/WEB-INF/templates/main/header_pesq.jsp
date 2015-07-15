<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.apache.commons.lang3.text.WordUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--@elvariable id="erro" type="java.lang.String"--%>

<%
    // pega as credenciais
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String nome = "rod";
    UserDetails userDetails = (UserDetails) principal;
    nome = userDetails.getUsername();
    nome = WordUtils.capitalizeFully(nome);
%>

<header>

</header>



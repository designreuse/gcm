<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- meta -->
    <meta name="description" content="">
    <meta name="author" content="Rodrigo G. Lima">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link href="<c:url value='/static/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<c:url value='/static/css/font-awesome.min.css'/>" rel="stylesheet">

    <link href="<c:url value='/static/css/bootstrap.2.3.2.min.css'/>" rel="stylesheet">
    <script src="/static/js/jquery-1.10.2.min.js"></script>
    <script src="/static/js/jquery-ui.js"></script>

    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;

            background-image: url(/static/img/bkg.jpg);
        }
        .form-signin {
            max-width: 300px;
            padding: 19px 29px 29px;
            margin: 0 auto 20px;
            background-color: #fff;
            border: 1px solid #e5e5e5;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;
            -webkit-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            -moz-box-shadow: 0 1px 2px rgba(0,0,0,.05);
            box-shadow: 0 1px 2px rgba(0,0,0,.05);
        }
        .form-signin .form-signin-heading,
        .form-signin .checkbox {
            margin-bottom: 10px;
            font-size: 22px;
        }
        .form-signin input[type="text"],
        .form-signin input[type="password"] {
            font-size: 16px;
            height: auto;
            margin-bottom: 15px;
            padding: 7px 9px;
        }
    </style>

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="<c:url value='/static/js/html5shiv.js'/>"></script>
    <![endif]-->

    <!-- Favicon -->
    <c:url var="_favicon_ico" value="/static/img/favicon.ico" />
    <link rel="icon" type="image/x-icon" href="${_favicon_ico}" />
    <link rel="shortcut icon" type="image/x-icon" href="${_favicon_ico}" />
</head>

<body onload="document.form.username.focus();">


<!--h2 class="form-signin-heading" style="text-align: center">Sistema de Gestão</h2-->


<div class="container" style="padding-top: 100px;">

        <c:url value="/login" var="loginUrl" />
        <form action="${loginUrl}" method="post" class="form-signin" name="form" id="form">
            <h2 class="form-signin-heading">Login</h2>

            <!-- Erros -->
            <c:if test="${not empty error}">
                <div class="alert alert-error">
                    <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                        <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                    </c:if>
                </div>
            </c:if>
            <!-- Info -->
            <c:if test="${param.logout != null}">
                <div class="alert alert-success">
                    Você deslogou.
                </div>
            </c:if>

            <!-- Usuário -->
            <input type="text" class="input-block-level" id="username" name="username" placeholder="Usuário">

            <!-- Senha -->
            <input type="password" class="input-block-level" id="password" name="password" placeholder="Senha">
            <br>
            <button class="btn btn-large btn-primary" id="btnSubimit" type="submit">Entrar</button>
        </form>

</div> <!-- /container -->

<!-- Le javascript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value='/static/js/jquery-1.10.2.min.js'/>"></script>
<script src="<c:url value='/static/js/bootstrap.min.js'/>"></script>
</body>
</html>

<script>
    $(function() {
        // pega referências
        var username   = $('#username');
        var password   = $('#password');
        var btnSubimit = $('#btnSubimit');
        var form       = $('#form');

        // registra eventos
        btnSubimit.click(function(event) {
            event.preventDefault();
            // login inválido
            if (username.val() === '' || password.val() === '') {
                console.log('Login inválido');
            } else {
                form.submit();
            }
        });
    });
</script>
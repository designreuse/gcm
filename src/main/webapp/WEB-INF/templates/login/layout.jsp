<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>
<%@ page trimDirectiveWhitespaces="true" %>

<!DOCTYPE html>
<html lang="en">
<head class="main-header skin-blue">
    <!-- meta -->
    <meta name="description" content="Sistema de Gestão Comercial">
    <meta name="author" content="João Carlos Dantas Vieira">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- lte -->
    <link href="/static/lte/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/lte/fonts/font-awesome.min.css" rel="stylesheet" />

    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/lte/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/lte/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
    <script src="/static/lte/html5/html5shiv.min.js"></script>
    <script src="/static/lte/html5/respond.min.js"></script>



    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;

            /*background-image: url(/static/img/bkg.jpg);   */
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

<body class="skin-blue sidebar-mini" onload="document.form.username.focus();" style="background-color: steelblue">
<div class="container container-fluid" style="padding-top: 10%; margin-left: 35%;">
    <div class="col-md-4">
        <div class="box box-info">
            <div class="box-header with-border">
                <h3 class="box-title">Login</h3>
                <c:if test="${not empty error}">
                    <div class="alert alert-error">
                        <c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                            <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
                        </c:if>
                    </div>
                </c:if>
                <c:if test="${param.logout != null}">
                    <div class="alert alert-success">
                        Você deslogou.
                    </div>
                </c:if>
            </div>
            <form class="form-horizontal" action="/login" method="post">
                <div class="box-body">
                    <div class="form-group">
                        <label for="username" class="col-sm-2 control-label">Usuário</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="username" name="username" placeholder="Usuário">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">Senha</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" name="password" placeholder="Senha">
                        </div>
                    </div>
                </div>
                <div class="box-footer">
                    <button class="btn btn-primary" type="submit" style="width: 80px;">Entrar</button>
                </div>
            </form>
        </div>
    </div>
</div>

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
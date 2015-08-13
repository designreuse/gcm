<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE HTML>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>GCM - Gestão Comercial</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.4 -->
    <link href="/static/lte/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Font Awesome Icons -->
    <%--<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" ../fonts/../fonts/ />--%>
    <link href="/static/lte/fonts/font-awesome.min.css" rel="stylesheet" />

    <!-- Ionicons -->
    <%--<link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />--%>
    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="/static/lte/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
    <!-- AdminLTE Skins. We have chosen the skin-blue for this starter
          page. However, you can choose any other skin. Make sure you
          apply the skin class to the body tag so the changes take effect.
    -->
    <link href="/static/lte/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <!--<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>-->
    <%--<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>--%>
    <script src="/static/lte/html5/html5shiv.min.js"></script>
    <script src="/static/lte/html5/respond.min.js"></script>
    <![endif]-->

    <script src="/static/js/jquery-1.10.2.min.js"></script>
    <script src="/static/js/jquery-ui.js"></script>

    <style type="text/css">
        .maiusculo {
            TEXT-TRANSFORM: uppercase;
        }
        .minusculo{
            TEXT-TRANSFORM: lowercase;
        }
    </style>
</head>
<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<body class="skin-blue sidebar-mini">
    <div class="wrapper">

        <tiles:insertAttribute name="header"/>

        <tiles:insertAttribute name="menu"/>

        <div class="content-wrapper" >  <!--style="min-height: 326px;"-->
            <tiles:insertAttribute name="body"/>
        </div>



        <tiles:insertAttribute name="footer"/>

        <tiles:insertAttribute name="control-side"/>

        <!-- mensagens de sucesso ou erro -->
        <tiles:insertAttribute name="mensagens"/>

    </div>

            <!-- REQUIRED JS SCRIPTS -->

<!-- jQuery 2.1.4 -->
<script src="/static/lte/plugins/jQuery/jQuery-2.1.4.min.js" type="text/javascript"></script>
<!-- Bootstrap 3.3.2 JS -->
<script src="/static/lte/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<!-- AdminLTE App -->
<script src="/static/lte/dist/js/app.min.js" type="text/javascript"></script>

<script src="/static/lte/dist/js/demo.js" type="text/javascript"></script>


</body>
</html>
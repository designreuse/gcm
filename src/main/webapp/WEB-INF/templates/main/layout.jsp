<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE HTML>
<html lang="pt-br">
<head>
    <meta charset="utf-8">
    <title><tiles:insertAttribute name="title" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="João Carlos Dantas Vieira">

    <link href="<c:url value='/static/css/bootstrap.2.3.2.min.css'/>" rel="stylesheet">
    <script src="/static/js/jquery-1.10.2.min.js"></script>
    <script src="/static/js/jquery-ui.js"></script>
    <script src="/static/js/bootstrap-datepicker.js"></script>

    <script src="<c:url value='/static/js/bootstrap.min.js'/>"></script>
    <script src="<c:url value='/static/js/jquery.maskedinput.min.js'/>"></script>
    <script src="<c:url value='/static/js/date.js'/>"></script>

    <style type="text/css">
        .maiusculo {
            TEXT-TRANSFORM: uppercase;
        }
        .minusculo{
            TEXT-TRANSFORM: lowercase;
        }
    </style>

    <style type="text/css">
        body {
            background-image: url(/static/img/bkg.jpg);
            }
    </style>

    <script>
        $(function(){
            $('.apenasnumero').bind('keydown',soNums); // o "#input" é o input que vc quer aplicar a funcionalidade
        });

        function soNums(e){
            //teclas adicionais permitidas (tab,delete,backspace,setas direita e esquerda)
            keyCodesPermitidos = new Array(8,9,37,39,46);

            //numeros e 0 a 9 do teclado alfanumerico
            for(x=48;x<=57;x++){
                keyCodesPermitidos.push(x);
            }

            //numeros e 0 a 9 do teclado numerico
            for(x=96;x<=105;x++){
                keyCodesPermitidos.push(x);
            }

            //Pega a tecla digitada
            keyCode = e.which;

            //Verifica se a tecla digitada é permitida
            if ($.inArray(keyCode,keyCodesPermitidos) != -1){
                return true;
            }
            return false;
        }
    </script>

</head>
<body>

<tiles:insertAttribute name="header"/>
<div class="container-fluid">
    <div class="row-fluid">
        <br/>
        <br/>
        <br/>
    </div>
    <div class="row-fluid">
        <div class="span2">
            <tiles:insertAttribute name="menu"/>
        </div>
        <div class="3">
            <tiles:insertAttribute name="body"/>
            <footer>
                <tiles:insertAttribute name="footer"/>
            </footer>
        </div>
    </div>
</div>

</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<script language="javascript">
    function somente_numero(campo){
        var digits="0123456789"
        var campo_temp
        for (var i=0;i<campo.value.length;i++){
            campo_temp=campo.value.substring(i,i+1)
            if (digits.indexOf(campo_temp)==-1){
                campo.value = campo.value.substring(0,i);
            }
        }
    }

    function Uppercase(campo) {
        campo.value = campo.value.toUpperCase();
    }

    function LowerCase(campo) {
        campo.value = campo.value.toLowerCase();
    }
</script>

<div class="container">
    <div class="container-fluid">
        <c:url value="/usuariodogrupo_insert" var="insert"/>
        <form:form class="form-signin" modelAttribute="lista" id="FormCadastro" action="${insert}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 33%;">Editar Empresas do Grupo</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <table class="table table-striped table-bordered table-hover table-condensed active tabelaPaginada" id="example">
                    <thead style="background-color:silver">
                    <tr>
                        <th style="width:150px;">Grupo</th>
                        <th>Empresa</th>
                        <th style="width:50px;"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${lista}" var="lista">
                    <tr>
                        <td>${lista.descricaoGrupo}</td>
                        <td>${lista.razaoSocial}</td>
                        <td>
                            <c:choose>
                                <c:when test="${lista.pertence == true}">
                                    <input class="pertence" id="${lista.id_Empresa}" grupo="${lista.id_GrupoUsuario}" type="checkbox" checked="true"/>
                                </c:when>
                                <c:when test="${lista.pertence != true}">
                                    <input class="pertence" id="${lista.id_Empresa}" grupo="${lista.id_GrupoUsuario}" type="checkbox"/>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="span12">
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;  margin-left: 45%;" href="<c:url value="/grupousuario_lista"/>">Voltar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

<!-- Div apenas ocupar um espaço no fim da página-->
<div class="container">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
            </div>
        </div>
    </div>
</div>

<script type='text/javascript'>
    $(document).ready(function(){
        // Executa o evento CLICK
        $('.pertence').click(function(){
            //alert($(this).attr('id'));
            var  id_grupo = $(this).attr('grupo');
            var  id_empresa = $(this).attr('id');
            var  pertence = $(this).prop( "checked" );

            //alert($(this).prop( "checked" ));

            $.getJSON("${pageContext.request.contextPath}/inserir_empresagrupo/"+id_grupo+"/"+id_empresa+"/"+pertence);
        });
    });
</script>
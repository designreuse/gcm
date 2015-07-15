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

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Referências do CFOP</a>
                    </div>
                </div>
            </div>

                <div class="navbar">
                    <div class="navbar-inner">
                        <br>
                        <div class="row-fluid">
                            <div class="span1" style="text-align: left;">
                                <label style="font-size: 16px; margin-top:6px;">CFOP</label>
                            </div>
                            <div class="span1" style="text-align: left;">
                                <input id="id_CFOP" type="text" value="${cfop.codigoCFOP}"
                                       readonly="true" maxlength="50" class="form-control"
                                       name="ID" placeholder="ID" style="width:50px;"/>
                            </div>
                            <div class="span10" style="text-align: left;">
                                <input type="text" maxlength="150" readonly="true" value="${cfop.descricao}"
                                       class="form-control"  name="Descricao" placeholder="Descrição"
                                       style="width:698px;"/>
                            </div>
                        </div>
                    </div>
                </div>


            <div class="container-fluid navbar navbar-inner">
                <br>
                <table class="table table-striped table-bordered table-hover table-condensed active tabelaPaginada" id="example">
                    <thead style="background-color:silver">
                    <tr>
                        <th style="width:80px;">CFOP</th>
                        <th>Descrição</th>
                        <th style="width:50px;"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${lista}" var="lista">
                    <tr>
                        <td>${lista.relacaoCFOP.codigoCFOP}</td>
                        <td>${lista.relacaoCFOP.descricao}</td>
                        <td>
                            <c:choose>
                                <c:when test="${lista.pertence == true}">
                                    <input class="pertence" id="${cfop.id_CFOP}" relacao="${lista.id_CFOPRelacao}" type="checkbox" checked="true"/>
                                </c:when>
                                <c:when test="${lista.pertence != true}">
                                    <input class="pertence" id="${cfop.id_CFOP}" relacao="${lista.id_CFOPRelacao}" type="checkbox"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;  margin-left: 45%;" href="<c:url value="/cfop_lista"/>">Voltar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

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
            var  id_cfop = $(this).attr('id');
            var  id_cfoprelacao = $(this).attr('relacao');
            var  pertence = $(this).prop('checked');

            //alert(id_cfop+' '+id_cfoprelacao+' '+pertence);

            $.getJSON("${pageContext.request.contextPath}/relacaocfop_insert/"+id_cfop+"/"+id_cfoprelacao+"/"+pertence);
        });
    });
</script>
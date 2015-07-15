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
</script>

<div class="container">


    <div class="container-fluid">
        <c:url value="/municipio_gravar" var="gravarmunicipio"/>
        <form:form class="form-signin" modelAttribute="municipio" id="FormCadastro" action="${gravarmunicipio}" method="POST">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Novo Município</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Id</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="id_municipio" type="text" readonly="true"
                                    maxlength="50" class="form-control"  name="id"
                                    placeholder="id" style="width:80px;" />
                    </div>
                </div>

                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">País</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:select path="id_pais" multiple="false" id="listaPais" onchange="getval(this);" cssClass="form-control"
                                     items="${lista_pais}"
                                     itemLabel="siglapais"
                                     itemValue="id_pais" style="width:95px;"/>
                    </div>
                </div>

                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">UF</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:select  path="id_uf"  id="listaUf" style="width:95px;"  cssClass="form-control" onchange="getval(this);"
                                      itemLabel="siglauf"
                                      itemValue="id_uf"/>
                    </div>
                </div>

                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Nome</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="descricao" type="text" maxlength="50" class="form-control maiusculo"
                                    name="nome" placeholder="Nome" style="width:400px;" />
                    </div>
                </div>

                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Ibge</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="ibge" type="text" maxlength="5" class="form-control"
                                    name="Ibge" placeholder="IBGE" style="width:80px;"
                                    onKeyUp="javascript:somente_numero(this);" />
                    </div>
                </div>

            </div>

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="span6" align="right">
                                <form:button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 100px;">Salvar</form:button>
                            </div>
                            <div class="span6">
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/municipio_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

<script>
    $("#listaPais").change();

    jQuery(function($){
        $("#listaPais").change(function(){
            var id_pais = $(this).val();
            if( id_pais.length <= 0 ) return;
            $.getJSON("${pageContext.request.contextPath}/carregauf-pais/"+id_pais,
                    function(result){
                        $('#listaUf').empty();
                        $.each(result, function(i){
                            $("#listaUf").append('<option value="' + result[i].id_Uf + '">' + result[i].siglaUf + '</option>');
                        });
                    });
        });
    });
</script>
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
        <c:url value="/empresa_insert" var="insertempresa"/>
        <form:form class="form-signin" modelAttribute="empresa" id="FormCadastro" action="${insertempresa}" method="POST">
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Nova Empresa</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Cnpj</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input id="Cnpj" path="Cnpj" type="text"
                                    maxlength="50" class="form-control"  name="Cnpj"
                                    placeholder="Cnpj" style="width:160px;" required="true"/>
                    </div>
                </div>

                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Razão Social</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="RazaoSocial" type="text" maxlength="100"
                                    class="form-control maiusculo"  name="RazaoSocial" placeholder="Razão Social"
                                    style="width:625px;" required="true"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Nome Fantasia</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="NomeFantasia" type="text" maxlength="100"
                                    class="form-control maiusculo"  name="NomeFantasia" placeholder="Nome Fantasia"
                                    style="width:625px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Insc. Estadual</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:input path="InscricaoEstadual" type="text" maxlength="20"
                                    class="form-control"  name="InscricaoEstadual" placeholder="Insc Estadual"
                                    style="width:160px;" />
                    </div>
                    <div class="span3" style="text-align: right;">
                        <label style="font-size: 16px; margin-top:10px;">Insc. Municipal</label>
                    </div>
                    <div class="span3" style="text-align: right;">
                        <form:input path="InscricaoMunicipal" type="text" maxlength="20"
                                    class="form-control" name="InscricaoMunicipal" placeholder="Insc Municipal"
                                    style="width:160px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Endereço</label>
                    </div>
                    <div class="span6" style="text-align: left;">
                        <form:input path="Endereco" type="text" maxlength="50"
                                    class="form-control maiusculo"  name="Endereco" placeholder="Endereço"
                                    style="width:410px;"/>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Número</label>
                    </div>
                    <div class="span2" style="text-align: right;">
                        <form:input path="Numero" type="text" maxlength="5"
                                    class="form-control maiusculo"  name="Numero" placeholder="Número"
                                    style="width:80px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Bairro</label>
                    </div>
                    <div class="span6" style="text-align: left;">
                        <form:input path="Bairro" type="text" maxlength="50"
                                    class="form-control maiusculo"  name="Bairro" placeholder="Bairro"
                                    style="width:410px;"/>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Cep</label>
                    </div>
                    <div class="span2" style="text-align: right;">
                        <form:input id="Cep" path="Cep" type="text"
                                    maxlength="50" class="form-control"  name="Cep"
                                    placeholder="Cep" style="width:80px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">País</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:select path="siglaPais" multiple="false" id="listaPais"
                                     onchange="getval(this);" cssClass="form-control" required="true"
                                     items="${lista_pais}"
                                     itemLabel="siglapais"
                                     itemValue="id_pais" style="width:200px;"/>
                    </div>
                    <div class="span3" style="text-align: right;">
                        <label style="font-size: 16px; margin-top:10px;">UF</label>
                    </div>
                    <div class="span3" style="text-align: right;">
                        <form:select  path="id_Uf"  multiple="false" id="listaUf"
                                      style="width:200px;" cssClass="form-control" required="true"
                                      itemLabel="siglauf"
                                      itemValue="id_uf" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Município</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:select  path="id_Municipio"  id="listaMunicipio" style="width:640px;"
                                      cssClass="form-control" required="true" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Telefone 1</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input id="Telefone1" path="Telefone1" type="text"
                                    maxlength="15" class="form-control"  name="Telefone1"
                                    placeholder="Telefone 1" style="width:120px;" />
                    </div>
                    <div class="span2" style="text-align: right;">
                        <label style="font-size: 16px; margin-top:10px;">Telefone 2</label>
                    </div>
                    <div class="span2" style="text-align: right;">
                        <form:input id="Telefone2" path="Telefone2" type="text"
                                    maxlength="15" class="form-control"  name="Telefone2"
                                    placeholder="Telefone 2" style="width:120px;" />
                    </div>
                    <div class="span1" style="text-align: right;">
                        <label style="font-size: 16px; margin-top:10px;">Fax</label>
                    </div>
                    <div class="span2" style="text-align: right;">
                        <form:input id="Fax" path="Fax" type="text" maxlength="50"
                                    class="form-control"  name="Fax" placeholder="Fax" style="width:120px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">E-mail</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="Email" type="text" maxlength="100"
                                    class="form-control minusculo"  name="Email" placeholder="E-mail"
                                    style="width:625px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Url</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="Url" type="text" maxlength="100"
                                    class="form-control minusculo"  name="Url" placeholder="Url"
                                    style="width:625px;"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/empresa_lista"/>">Cancelar</a>
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

<script>
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

                        $('#listaMunicipio').empty();
                        $("#listaUf").trigger("change");
                    });
        });
    });

    jQuery(function($){
        $("#listaUf").change(function(){
            var id_uf = $(this).val();
            if( id_uf.length <= 0 ) return;
            $.getJSON("${pageContext.request.contextPath}/carregamunicipio-uf/"+id_uf,
                    function(result){
                        $('#listaMunicipio').empty();
                        $.each(result, function(i){
                            $("#listaMunicipio").append('<option value="' + result[i].id_municipio + '">' + result[i].descricao + '</option>');
                        });
                    });
        });
    });

    jQuery(function($){
        $("#Cnpj").mask("99.999.999/9999-99",{placeholder:" "});
        $("#Telefone1").mask("(999) 9999-9999",{placeholder:" "});
        $("#Telefone2").mask("(999) 9999-9999",{placeholder:" "});
        $("#Fax").mask("(999) 9999-9999",{placeholder:" "});
        $("#Cep").mask("99999-999",{placeholder:" "});
    });

</script>


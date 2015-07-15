<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/pessoa_update/${tipo}" var="gravar"/>
        <form:form class="form-signin" modelAttribute="pessoa" id="FormCadastro" action="${gravar}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <c:choose>
                            <c:when test="${tipo == 'CLI'}">
                                <a class="brand" id="Tipo" tipo="CLI" style="font: bold; margin-left: 40%">Editar Cliente</a>
                            </c:when>
                            <c:when test="${tipo == 'FOR'}">
                                <a class="brand" id="Tipo" tipo="FOR" style="font: bold; margin-left: 35%;">Editar Fornecedor</a>
                            </c:when>
                            <c:when test="${tipo == 'FUN'}">
                                <a class="brand" id="Tipo" tipo="FUN" style="font: bold; margin-left: 35%;">Editar Funcionário</a>
                            </c:when>
                            <c:when test="${tipo == 'VEN'}">
                                <a class="brand" id="Tipo" tipo="VEN" style="font: bold; margin-left: 40%;">Editar Vendedor</a>
                            </c:when>
                            <c:when test="${tipo == 'TRA'}">
                                <a class="brand" id="Tipo" tipo="TRA" style="font: bold; margin-left: 35%;">Editar Transportador</a>
                            </c:when>
                            <c:when test="${tipo == 'CON'}">
                                <a class="brand" id="Tipo" tipo="CON" style="font: bold; margin-left: 40%;">Editar Convênio</a>
                            </c:when>
                            <c:when test="${tipo == 'HOS'}">
                                <a class="brand" id="Tipo" tipo="HOS" style="font: bold; margin-left: 40%;">Editar Hospital</a>
                            </c:when>
                            <c:when test="${tipo == 'MED'}">
                                <a class="brand" id="Tipo" tipo="MED" style="font: bold; margin-left: 40%;">Editar Médico</a>
                            </c:when>
                            <c:when test="${tipo == 'ENF'}">
                                <a class="brand" id="Tipo" tipo="ENF" style="font: bold; margin-left: 40%;">Editar Enfermeiro</a>
                            </c:when>
                            <c:when test="${tipo == 'PAC'}">
                                <a class="brand" id="Tipo" tipo="PAC" style="font: bold; margin-left: 40%;">Editar Paciênte</a>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
            <br>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">ID</label>
                </div>
                <div class="span4" style="text-align: left;">
                    <form:input path="id_Pessoa" type="text"  maxlength="14" readonly="true"
                                class="form-control" name="id_Pessoa"
                                style="width:150px;" id="ID"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Cpf\Cnpj</label>
                </div>
                <div class="span4" style="text-align: left;">
                    <form:input path="cpfCnpj" type="text"  maxlength="14" required="autofocus"
                                class="form-control" name="cpfCnpj" placeholder="Cpf/Cnpj"
                                style="width:150px;" id="CpfCnpj"/>
                </div>
                <div class="span2" style="text-align: right;">
                    <label style="font-size: 16px; margin-top:5px;">Ativo</label>
                </div>
                <div class="span3" style="text-align: left;">
                    <form:checkbox path="ativo" checked="true" id="Ativo"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Razão Social</label>
                </div>
                <div class="span10" style="text-align: left;">
                    <form:input path="razaoSocial" type="text"  maxlength="150" required="true"
                                class="form-control" name="razaoSocial" placeholder="Razao Social"
                                style="width:697px;" id="RazaoSocial"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Nome Fantasia</label>
                </div>
                <div class="span10" style="text-align: left;">
                    <form:input path="nomeFantasia" type="text"  maxlength="150" id="NomeFantasia"
                                class="form-control" name="nomeFantasia" placeholder="Nome Fantasia"
                                style="width:697px;" />
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Insc. Estadual</label>
                </div>
                <div class="span5" style="text-align: left;">
                    <form:input path="inscricaoEstadual" type="text"  maxlength="20" id="InscricaoEstadual"
                                class="form-control" name="inscricaoEstadual" placeholder="Inscricao Estadual"
                                style="width:150px;" />
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Insc. Municipal</label>
                </div>
                <div class="span3" style="text-align: right;">
                    <form:input path="inscricaoMunicipal" type="text"  maxlength="20"
                                class="form-control" name="inscricaoMunicipal" placeholder="Inscricao Municipal"
                                style="width:150px;" id="InscricaoMunicipal"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">RG</label>
                </div>
                <div class="span5" style="text-align: left;">
                    <form:input path="rg" type="text"  maxlength="20" id="Rg"
                                class="form-control" name="rg" placeholder="RG"
                                style="width:150px;" />
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Data Nascimento</label>
                </div>
                <div class="span3" style="text-align: right;">
                    <form:input path="dataNascimento" type="text"  maxlength="10" id="DataNascimento"
                                class="form-control datepicker" name="dataNascimento" placeholder="Data Nascimento"
                                style="width:150px;" required="true"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Endereço</label>
                </div>
                <div class="span10" style="text-align: left;">
                    <form:input path="endereco" type="text"  maxlength="150" id="Endereco"
                                class="form-control" name="endereco" placeholder="Endereço"
                                style="width:697px;" />
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Número</label>
                </div>
                <div class="span5" style="text-align: left;">
                    <form:input path="numero" type="text"  maxlength="10" id="Numero"
                                class="form-control" name="Numero" placeholder="Número"
                                style="width:150px;" />
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">CEP</label>
                </div>
                <div class="span3" style="text-align: right;">
                    <form:input path="cep" type="text"  maxlength="8" id="Cep"
                                class="form-control" name="Cep" placeholder="CEP"
                                style="width:150px;" />
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Bairro</label>
                </div>
                <div class="span10" style="text-align: left;">
                    <form:input path="bairro" type="text"  maxlength="150" id="Bairro"
                                class="form-control" name="bairro" placeholder="Bairro"
                                style="width:697px;" />
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">País</label>
                </div>
                <div class="span5" style="text-align: left;">
                    <form:select path="municipio.id_pais" multiple="false" id="listaPais"
                                 style="width:165px;" cssClass="form-control" required="true"
                                 items="${lista_pais}"
                                 itemLabel="siglapais"
                                 itemValue="id_pais"/>
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">UF</label>
                </div>
                <div class="span3" style="text-align: right;">
                    <form:input path="municipio.id_uf" id="id_uf_imput" type="hidden" maxlength="50" class="form-control"/>

                    <form:select  path="municipio.id_uf" multiple="false" id="listaUf"
                                  style="width:165px;" cssClass="form-control"
                                  itemLabel="siglauf"
                                  itemValue="id_uf" required="true"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Município</label>
                </div>
                <div class="span10" style="text-align: left;">
                    <form:select  path="id_Municipio"  id="listaMunicipio" style="width:713px;"
                                  cssClass="form-control" required="true"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Telefone 1</label>
                </div>
                <div class="span5" style="text-align: left;">
                    <form:input path="telefone1" type="text"  maxlength="20" id="Telefone1"
                                class="form-control" name="Telefone1" placeholder="Telefone 1"
                                style="width:150px;" />
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Telefone 2</label>
                </div>
                <div class="span3" style="text-align: right;">
                    <form:input path="telefone2" type="text"  maxlength="20" id="Telefone2"
                                class="form-control" name="Telefone2" placeholder="Telefone 2"
                                style="width:150px;" />
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Fax</label>
                </div>
                <div class="span5" style="text-align: left;">
                    <form:input path="fax" type="text"  maxlength="20" id="Fax"
                                class="form-control" name="Fax" placeholder="Fax"
                                style="width:150px;" />
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">E-mail</label>
                </div>
                <div class="span5" style="text-align: left;">
                    <form:input path="email" type="text"  maxlength="20" id="Email"
                                class="form-control minusculo" name="Email" placeholder="Email"
                                style="width:697px;"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Cliente</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'CLI'}">
                            <form:checkbox path="tipoCliente" checked="true" id="TipoCliente"/>
                        </c:when>
                        <c:when test="${tipo != 'CLI'}">
                            <form:checkbox path="tipoCliente" id="TipoCliente"/>
                        </c:when>
                    </c:choose>
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Fornecedor</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'FOR'}">
                            <form:checkbox path="tipoFornecedor" checked="true" id="TipoFornecedor"/>
                        </c:when>
                        <c:when test="${tipo != 'FOR'}">
                            <form:checkbox path="tipoFornecedor" id="TipoFornecedor"/>
                        </c:when>
                    </c:choose>
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Vendedor</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'VEN'}">
                            <form:checkbox path="tipoVendedor" checked="true" id="TipoVendedor"/>
                        </c:when>
                        <c:when test="${tipo != 'VEN'}">
                            <form:checkbox path="tipoVendedor" id="TipoVendedor"/>
                        </c:when>
                    </c:choose>
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Funcionário</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'FUN'}">
                            <form:checkbox path="tipoFuncionario" checked="true" id="TipoFuncionario"/>
                        </c:when>
                        <c:when test="${tipo != 'FUN'}">
                            <form:checkbox path="tipoFuncionario" id="TipoFuncionario"/>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Convênio</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'CON'}">
                            <form:checkbox path="tipoConvenio" checked="true" id="TipoConvenio"/>
                        </c:when>
                        <c:when test="${tipo != 'CON'}">
                            <form:checkbox path="tipoConvenio" id="TipoConvenio"/>
                        </c:when>
                    </c:choose>
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Hospital</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'HOS'}">
                            <form:checkbox path="tipoHospital" checked="true" id="TipoHospital"/>
                        </c:when>
                        <c:when test="${tipo != 'HOS'}">
                            <form:checkbox path="tipoHospital" id="TipoHospital"/>
                        </c:when>
                    </c:choose>
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Enfermeiro</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'ENF'}">
                            <form:checkbox path="tipoEnfermeiro" checked="true" id="TipoEnfermeiro"/>
                        </c:when>
                        <c:when test="${tipo != 'ENF'}">
                            <form:checkbox path="tipoEnfermeiro" id="TipoEnfermeiro"/>
                        </c:when>
                    </c:choose>
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Paciente</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'PAC'}">
                            <form:checkbox path="tipoPaciente" checked="true" id="TipoPaciente"/>
                        </c:when>
                        <c:when test="${tipo != 'PAC'}">
                            <form:checkbox path="tipoPaciente" id="TipoPaciente"/>
                        </c:when>
                    </c:choose>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Transportador</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'TRA'}">
                            <form:checkbox path="tipoTransportador" checked="true" id="TipoTransportador"/>
                        </c:when>
                        <c:when test="${tipo != 'TRA'}">
                            <form:checkbox path="tipoTransportador" id="TipoTransportador"/>
                        </c:when>
                    </c:choose>
                </div>
                <div class="span2" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:5px;">Médico</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <c:choose>
                        <c:when test="${tipo == 'MED'}">
                            <form:checkbox path="tipoMedico" checked="true" id="TipoMedico"/>
                        </c:when>
                        <c:when test="${tipo != 'MED'}">
                            <form:checkbox path="tipoMedico" id="TipoMedico"/>
                        </c:when>
                    </c:choose>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/pessoa_lista/${tipo}"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
<br>
<br>


<script>
    jQuery(function($){
        $( document ).ready(function() {
            var id_pais = $("#listaPais").val();
            var id_uf = $("#id_uf_imput").val();
            var id_municipio = $("#id_municipio_imput").val();

            if( id_pais.length <= 0 ) return;
            $.getJSON("${pageContext.request.contextPath}/carregauf-pais/"+id_pais,
                    function(result){
                        $('#listaUf').empty();
                        $.each(result, function(i){
                            if (result[i].id_Uf == id_uf)
                            {
                                $("#listaUf").append('<option value="' + result[i].id_Uf + '" selected="selected">' + result[i].siglaUf + '</option>');
                            }
                            else
                            {
                                $("#listaUf").append('<option value="' + result[i].id_Uf + '">' + result[i].siglaUf + '</option>');
                            }
                        });
                    });

            if( id_uf.length <= 0 ) return;
            $.getJSON("${pageContext.request.contextPath}/carregamunicipio-uf/"+id_uf,
                    function(result){
                        $('#listaMunicipio').empty();
                        $.each(result, function(i){
                            if (result[i].id_municipio == id_municipio)
                            {
                                $("#listaMunicipio").append('<option value="' + result[i].id_municipio + '" selected="selected">' + result[i].descricao + '</option>');
                            }
                            else
                            {
                                $("#listaMunicipio").append('<option value="' + result[i].id_municipio + '">' + result[i].descricao + '</option>');
                            }
                        });
                    });
        });


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
        $("#CpfCnpj").change(function(){
            if ($(this).val() != ''){
                $.getJSON("${pageContext.request.contextPath}/pessoabycpfcnpj/"+$(this).val(),
                        function(result){
                            if (result != null){
                                location.href="/pessoa_editar/"+ $(Tipo).attr("tipo") +"/" + result.id_Pessoa;
                            }
                        });
            }
        });
    });

    jQuery(function($){
        $("#Telefone1").mask("(99) 9999-9999",{placeholder:" "});
        $("#Telefone2").mask("(99) 9999-9999",{placeholder:" "});
        $("#Fax").mask("(99) 9999-9999",{placeholder:" "});
        $("#Cep").mask("99999-999",{placeholder:" "});
    });

    $(document).ready(function () {
        $('.datepicker').datepicker({
            format: "dd/mm/yyyy",
            language: "pt-BR"
        });
    });
</script>


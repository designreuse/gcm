<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Editar Empresa</h1>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <div class="row">
                                <label class="col-md-2 control-label" for="id_Empresa" style="margin-top: 5px;">ID</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="13" style="width:100%;" readonly="true" value="${empresa.id_Empresa}"
                                           class="form-control maiusculo" placeholder="ID" name="id_Empresa" id="id_Empresa"/>
                                </div>

                                <label class="col-md-2 control-label" for="cnpj" style="margin-top: 5px;">CNPJ</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="13" style="width:100%;" readonly="true" value="${empresa.cnpj}"
                                           class="form-control maiusculo" placeholder="CNPJ" name="cnpj" id="cnpj"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="razaoSocial" style="margin-top: 5px;">Razão Social</label>
                                <div class="col-md-6">
                                    <input type="text" maxlength="100" style="width:100%;" readonly="true" value="${empresa.razaoSocial}"
                                           class="form-control maiusculo" placeholder="Razão Social" name="razaoSocial" id="razaoSocial"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="nomeFantasia" style="margin-top: 5px;">Nome Fantasia</label>
                                <div class="col-md-6">
                                    <input type="text" maxlength="100" style="width:100%;" readonly="true" value="${empresa.nomeFantasia}"
                                           class="form-control maiusculo" placeholder="Fantasia" name="nomeFantasia" id="nomeFantasia"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="inscricaoEstadual" style="margin-top: 5px;">Insc. Estadual</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="20" style="width:100%;" readonly="true" value="${empresa.inscricaoEstadual}"
                                           class="form-control maiusculo" placeholder="Insc. Estadual" name="inscricaoEstadual" id="inscricaoEstadual"/>
                                </div>

                                <label class="col-md-2 control-label" for="inscricaoMunicipal" style="margin-top: 5px;">Insc. Municipal</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="20" style="width:100%;" readonly="true" value="${empresa.inscricaoMunicipal}"
                                           class="form-control maiusculo" placeholder="Insc. Municipal" name="inscricaoMunicipal" id="inscricaoMunicipal"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="endereco" style="margin-top: 5px;">Endereço</label>
                                <div class="col-md-6">
                                    <input type="text" maxlength="50" style="width:100%;" readonly="true" value="${empresa.endereco}"
                                           class="form-control maiusculo" placeholder="Endereço" name="endereco" id="endereco"/>
                                </div>

                                <label class="col-md-1 control-label" for="numero" style="margin-top: 5px;">Número</label>
                                <div class="col-md-1">
                                    <input type="text" maxlength="5" style="width:100%;" readonly="true" value="${empresa.numero}"
                                           class="form-control maiusculo" placeholder="Número" name="numero" id="numero"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="bairro" style="margin-top: 5px;">Bairro</label>
                                <div class="col-md-6">
                                    <input type="text" maxlength="50" style="width:100%;" readonly="true" value="${empresa.bairro}"
                                           class="form-control maiusculo" placeholder="Bairro" name="bairro" id="bairro"/>
                                </div>

                                <label class="col-md-1 control-label" for="cep" style="margin-top: 5px;">CEP</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="9" style="width:100%;" readonly="true" value="${empresa.cep}"
                                           class="form-control maiusculo" placeholder="CEP" name="cep" id="cep"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="id_Pais" style="margin-top: 5px;">País</label>
                                <div class="col-md-2">
                                    <select class="form-control" name="id_Pais" id="id_Pais" disabled="true">
                                        <option></option>
                                        <c:forEach items="${lista_pais}" var="p">
                                            <c:choose>
                                                <c:when test="${p.id_pais != empresa.id_Pais}">
                                                    <option value="${p.id_pais}">${p.descricao}</option>
                                                </c:when>
                                                <c:when test="${p.id_pais == empresa.id_Pais}">
                                                    <option value="${p.id_pais}" selected="selected">${p.descricao}</option>
                                                </c:when>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>

                                <label class="col-md-2 control-label" for="id_Uf" style="margin-top: 5px;">UF</label>
                                <div class="col-md-2">
                                    <select class="form-control" name="id_Uf" id="id_Uf" disabled="true">
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="id_Municipio" style="margin-top: 5px;">Município</label>
                                <div class="col-md-6">
                                    <select class="form-control" name="id_Municipio" id="id_Municipio" disabled="true">
                                    </select>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="telefone1" style="margin-top: 5px;">Telefone 1</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="15" style="width:100%;" readonly="true" value="${empresa.telefone1}"
                                           class="form-control maiusculo" placeholder="Telefone 1" name="telefone1" id="telefone1"/>
                                </div>

                                <label class="col-md-2 control-label" for="telefone2" style="margin-top: 5px;">Telefone 2</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="15" style="width:100%;" readonly="true" value="${empresa.telefone2}"
                                           class="form-control maiusculo" placeholder="Telefone 2" name="telefone2" id="telefone2"/>
                                </div>

                                <label class="col-md-1 control-label" for="fax" style="margin-top: 5px;">FAX</label>
                                <div class="col-md-2">
                                    <input type="text" maxlength="15" style="width:100%;" readonly="true" value="${empresa.fax}"
                                           class="form-control maiusculo" placeholder="FAX" name="fax" id="fax"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="email" style="margin-top: 5px;">E-mail</label>
                                <div class="col-md-6">
                                    <input type="text" maxlength="100" style="width:100%;" readonly="true" value="${empresa.email}"
                                           class="form-control minusculo" placeholder="E-Mail" name="email" id="email"/>
                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <label class="col-md-2 control-label" for="url" style="margin-top: 5px;">Url</label>
                                <div class="col-md-6">
                                    <input type="text" maxlength="100" style="width:100%;" readonly="true" value="${empresa.url}"
                                           class="form-control minusculo" placeholder="Url" name="url" id="url"/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <div class="col-sm-12" style="margin-top: 15px; text-align: center;">
                        <a style="width: 80px" href="<c:url value="/empresa_lista"/>" class="btn btn-primary" title="Voltar">Voltar</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(window).load(function() {
        carrega_uf($("#id_Pais").val());
    });

    $("#id_Pais").change(function(){
        $('#id_Uf').empty();
        $('#id_Municipio').empty();
        carrega_uf($(this).val());
    });

    $("#id_Uf").change(function(){
        $('#id_Municipio').empty();
        carrega_municipio($(this).val());
    });

    function carrega_uf(id){
        var id_pais = id;
        if( id_pais.length <= 0 ) return;
        $.getJSON("${pageContext.request.contextPath}/carregauf-pais/"+id_pais,
                function(result){
                    $('#id_Uf').empty();
                    $("#id_Uf").append('<option></option>');
                    $.each(result, function(i){
                        if (result[i].id_Uf == ${empresa.id_Uf}){
                            $("#id_Uf").append('<option value="' + result[i].id_Uf + '" selected="selected">' + result[i].descricao + '</option>');
                            carrega_municipio(result[i].id_Uf);
                        } else {
                            $("#id_Uf").append('<option value="' + result[i].id_Uf + '">' + result[i].descricao + '</option>');
                        }
                    });
                });

    };

    function carrega_municipio(id){
        var id_uf = id;
        if( id_uf.length <= 0 ) return;
        $.getJSON("${pageContext.request.contextPath}/carregamunicipio-uf/"+id_uf,
                function(result){
                    $('#id_Municipio').empty();
                    $.each(result, function(i){
                        if (result[i].id_municipio == ${empresa.id_Municipio}){
                            $("#id_Municipio").append('<option value="' + result[i].id_municipio + '" selected="selected">' + result[i].descricao + '</option>');
                        } else {
                            $("#id_Municipio").append('<option value="' + result[i].id_municipio + '">' + result[i].descricao + '</option>');
                        }
                    });
                });
    };

</script>
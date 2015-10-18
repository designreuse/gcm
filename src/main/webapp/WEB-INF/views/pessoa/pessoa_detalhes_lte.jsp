    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    <%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

    <div class="content-header">
        <c:choose>
            <c:when test="${tipo == 'CLI'}">
                <h1>Detalhes do Cliente</h1>
            </c:when>
            <c:when test="${tipo == 'FOR'}">
                <h1>Detalhes do Fornecedor</h1>
            </c:when>
            <c:when test="${tipo == 'FUN'}">
                <h1>Detalhes do Funcionário</h1>
            </c:when>
            <c:when test="${tipo == 'VEN'}">
                <h1>Detalhes do Vendedor</h1>
            </c:when>
            <c:when test="${tipo == 'TRA'}">
                <h1>Detalhes do Transportador</h1>
            </c:when>
            <c:when test="${tipo == 'CON'}">
                <h1>Detalhes do Convênio</h1>
            </c:when>
            <c:when test="${tipo == 'HOS'}">
                <h1>Detalhes do Hospital</h1>
            </c:when>
            <c:when test="${tipo == 'MED'}">
                <h1>Detalhes do Médico</h1>
            </c:when>
            <c:when test="${tipo == 'ENF'}">
                <h1>Detalhes do Enfermeiro</h1>
            </c:when>
            <c:when test="${tipo == 'PAC'}">
                <h1>Detalhes do Paciênte</h1>
            </c:when>
        </c:choose>
    </div>

    <div class="content body">
    <form action="/pessoa_update/${tipo}" method="post">
    <fieldset>
    <div class="row">
    <div class="col-sm-12">
    <div class="box box-primary">
    <div class="box-body">
    <form class="form-horizontal">
    <div class="form-group">
    <div class="row">
        <label class="col-md-2 control-label" for="cpfCnpj" style="margin-top: 5px;">Id</label>
        <div class="col-md-2">
            <input type="text" maxlength="13" style="width:100%;" required="autofocus" value="${pessoa.id_Pessoa}"
                   class="form-control" placeholder="Id" name="id_Pessoa" id="id_Pessoa" readonly="true"/>
        </div>
        <label class="col-md-1 control-label" for="cpfCnpj" style="margin-top: 5px;">Cpf\Cnpj</label>
        <div class="col-md-2">
            <input type="text" maxlength="13" style="width:100%;" required="autofocus" value="${pessoa.cpfCnpj}"
                   class="form-control" placeholder="Cpf\Cnpj" name="cpfCnpj" id="cpfCnpj" readonly="true"/>
        </div>
        <label class="col-md-1 control-label" for="ckbativo" style="margin-top: 5px;">Ativo</label>
        <div class="col-md-2">
            <input type="hidden" value="True" readonly="True" name="ativo" id="ativo" value="${pessoa.ativo}"/>
            <c:choose>
                <c:when test="${pessoa.ativo == 'true'}">
                    <input type="checkbox" name="ckbativo" id="ckbativo" style="margin-top: 10px;"checked disabled/>
                </c:when>
                <c:when test="${pessoa.ativo == 'false'}">
                    <input type="checkbox" name="ckbativo" id="ckbativo" style="margin-top: 10px;" disabled/>
                </c:when>
            </c:choose>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="razaoSocial" style="margin-top: 5px;">Razão Social</label>
        <div class="col-md-8">
            <input type="text" maxlength="50" style="width:100%;" required="true" value="${pessoa.razaoSocial}"
                   class="form-control" placeholder="Razão Social" name="razaoSocial" id="razaoSocial" readonly="true"/>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="nomeFantasia" style="margin-top: 5px;">Nome Fantasia</label>
        <div class="col-md-8">
            <input type="text" maxlength="50" style="width:100%;" value="${pessoa.nomeFantasia}"
                   class="form-control" placeholder="Nome Fantasia" name="nomeFantasia" id="nomeFantasia" readonly="true"/>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="inscricaoEstadual" style="margin-top: 5px;">Insc. Estadual</label>
        <div class="col-md-3">
            <input type="text" maxlength="20" style="width:100%;" value="${pessoa.inscricaoEstadual}"
                   class="form-control" placeholder="Insc. Estadual" name="inscricaoEstadual" id="inscricaoEstadual" readonly="true"/>
        </div>
        <label class="col-md-2 control-label" for="inscricaoMunicipal" style="margin-top: 5px;">Insc. Municipal</label>
        <div class="col-md-3">
            <input type="text" maxlength="20" style="width:100%;" value="${pessoa.inscricaoMunicipal}" readonly="true"
                   class="form-control" placeholder="Insc. Municipal" name="inscricaoMunicipal" id="inscricaoMunicipal"/>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="rg" style="margin-top: 5px;">RG</label>
        <div class="col-md-3">
            <input type="text" maxlength="20" style="width:100%;" value="${pessoa.rg}" readonly="true"
                   class="form-control" placeholder="RG" name="rg" id="rg"/>
        </div>
        <label class="col-md-2 control-label" for="dataNascimento" style="margin-top: 5px;">Data Nascimento</label>
        <div class="col-md-3">
            <input type="date" style="width:100%;" required="true" value="${pessoa.dataNascimento}" readonly="true"
                   class="form-control" placeholder="Data Nascimento" name="dataNascimento" id="dataNascimento"/>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="endereco" style="margin-top: 5px;">Endereço</label>
        <div class="col-md-8">
            <input type="text" maxlength="50" style="width:100%;" value="${pessoa.endereco}"  readonly="true"
                   class="form-control" placeholder="Endereço" name="endereco" id="endereco"/>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="numero" style="margin-top: 5px;">Número</label>
        <div class="col-md-3">
            <input type="text" maxlength="10" style="width:100%;" value="${pessoa.numero}" readonly="true"
                   class="form-control" placeholder="Número" name="numero" id="numero"/>
        </div>
        <label class="col-md-2 control-label" for="cep" style="margin-top: 5px;">Cep</label>
        <div class="col-md-3">
            <input type="text" maxlength="8" style="width:100%;" value="${pessoa.cep}" readonly="true"
                   class="form-control" placeholder="Cep" name="cep" id="cep"/>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="bairro" style="margin-top: 5px;">Bairro</label>
        <div class="col-md-8">
            <input type="text" maxlength="50" style="width:100%;" value="${pessoa.bairro}" readonly="true"
                   class="form-control" placeholder="Bairro" name="bairro" id="bairro"/>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="id_pais" style="margin-top: 5px;">País</label>
        <div class="col-md-3">
            <select class="form-control" name="id_pais" id="id_pais" required="true" disabled="true">
                <option value=""></option>
                <c:forEach items="${lista_pais}" var="p">
                    <c:choose>
                        <c:when test="${p.id_pais == pessoa.municipio.id_pais}">
                            <option value="${p.id_pais}" selected="selected">${p.descricao}</option>
                        </c:when>
                        <c:when test="${p.id_pais != pessoa.municipio.id_pais}">
                            <option value="${p.id_pais}">${p.descricao}</option>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <label class="col-md-2 control-label" for="id_uf" style="margin-top: 5px;">UF</label>
        <div class="col-md-3">
            <select class="form-control" name="id_uf" id="id_uf" required="true" disabled="true">
                <option value=""></option>
            </select>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="id_Municipio" style="margin-top: 5px;">Município</label>
        <div class="col-md-8">
            <select class="form-control" name="id_Municipio" id="id_Municipio" required="true" disabled="true">
                <option value=""></option>
            </select>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="telefone1" style="margin-top: 5px;">Tel. 1</label>
        <div class="col-md-2">
            <input type="text" maxlength="15" style="width:100%;" value="${pessoa.telefone1}" readonly="true"
                   class="form-control" placeholder="Telefone 1" name="telefone1" id="telefone1"/>
        </div>
        <label class="col-md-1 control-label" for="telefone2" style="margin-top: 5px;">Tel. 2</label>
        <div class="col-md-2">
            <input type="text" maxlength="15" style="width:100%;" value="${pessoa.telefone2}" readonly="true"
                   class="form-control" placeholder="Telefone 2" name="telefone2" id="telefone2"/>
        </div>
        <label class="col-md-1 control-label" for="telefone2" style="margin-top: 5px;">Fax</label>
        <div class="col-md-2">
            <input type="text" maxlength="15" style="width:100%;" value="${pessoa.fax}" readonly="true"
                   class="form-control" placeholder="Fax" name="fax" id="fax"/>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="email" style="margin-top: 5px;">E-Mail</label>
        <div class="col-md-8">
            <input type="text" maxlength="50" style="width:100%;" value="${pessoa.email}" readonly="true"
                   class="form-control" placeholder="E-Mail" name="email" id="email"/>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="tipoCliente" style="margin-top: 5px;">Cliente</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'CLI'}">
                    <input type="checkbox" name="ckbCliente" id="ckbCliente" style="margin-top: 10px;" checked disabled/>
                    <input type="hidden" value="True" name="tipoCliente" id="tipoCliente" value="True"/>
                </c:when>
                <c:when test="${tipo != 'CLI'}">
                    <input type="hidden" value="True" name="tipoCliente" id="tipoCliente" value="${pessoa.tipoCliente}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoCliente == 'true'}">
                            <input type="checkbox" name="ckbCliente" id="ckbCliente" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoCliente == 'false'}">
                            <input type="checkbox" name="ckbCliente" id="ckbCliente" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
        <label class="col-md-1 control-label" for="ckbFornecedor" style="margin-top: 5px;">Fornecedor</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'FOR'}">
                    <input type="checkbox" name="ckbFornecedor" id="ckbFornecedor" style="margin-top: 10px;" checked disabled/>
                    <input type="hidden" value="True" name="tipoFornecedor" id="tipoFornecedor"/>
                </c:when>
                <c:when test="${tipo != 'FOR'}">
                    <input type="hidden" value="False" name="tipoFornecedor" id="tipoFornecedor" value="${pessoa.tipoFornecedor}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoFornecedor == 'true'}">
                            <input type="checkbox" name="ckbFornecedor" id="ckbFornecedor" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoFornecedor == 'false'}">
                            <input type="checkbox" name="ckbFornecedor" id="ckbFornecedor" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
        <label class="col-md-1 control-label" for="ckbVendedor" style="margin-top: 5px;">Vendedor</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'VEN'}">
                    <input type="checkbox" name="ckbVendedor" id="ckbVendedor" style="margin-top: 10px;" checked disabled/>
                    <input type="hidden" value="True" name="tipoVendedor" id="tipoVendedor"/>
                </c:when>
                <c:when test="${tipo != 'VEN'}">
                    <input type="hidden" value="False" name="tipoVendedor" id="tipoVendedor" value="${pessoa.tipoVendedor}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoVendedor == 'true'}">
                            <input type="checkbox" name="ckbVendedor" id="ckbVendedor" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoVendedor == 'false'}">
                            <input type="checkbox" name="ckbVendedor" id="ckbVendedor" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
        <label class="col-md-1 control-label" for="ckbFuncionario" style="margin-top: 5px;">Funcionário</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'FUN'}">
                    <input type="checkbox" name="ckbFuncionario" id="ckbFuncionario" style="margin-top: 10px;" checked disabled/>
                    <input type="hidden" value="True" name="tipoFuncionario" id="tipoFuncionario"/>
                </c:when>
                <c:when test="${tipo != 'FUN'}">
                    <input type="hidden" value="False" name="tipoFuncionario" id="tipoFuncionario" value="${pessoa.tipoFuncionario}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoFuncionario == 'true'}">
                            <input type="checkbox" name="ckbFuncionario" id="ckbFuncionario" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoFuncionario == 'false'}">
                            <input type="checkbox" name="ckbFuncionario" id="ckbFuncionario" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="ckbConvenio" style="margin-top: 5px;">Convênio</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'CON'}">
                    <input type="checkbox" name="ckbConvenio" id="ckbConvenio" style="margin-top: 10px;" checked disabled/>
                    <input type="hidden" value="True" name="tipoConvenio" id="tipoConvenio"/>
                </c:when>
                <c:when test="${tipo != 'CON'}">
                    <input type="hidden" value="False" name="tipoConvenio" id="tipoConvenio" value="${pessoa.tipoConvenio}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoConvenio == 'true'}">
                            <input type="checkbox" name="ckbConvenio" id="ckbConvenio" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoConvenio == 'false'}">
                            <input type="checkbox" name="ckbConvenio" id="ckbConvenio" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
        <label class="col-md-1 control-label" for="ckbHospital" style="margin-top: 5px;">Hospital</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'HOS'}">
                    <input type="checkbox" name="ckdHospital" id="ckbHospital" style="margin-top: 10px;" checked disabled/>
                    <input type="hidden" value="True" name="tipoHospital" id="tipoHospital"/>
                </c:when>
                <c:when test="${tipo != 'HOS'}">
                    <input type="hidden" value="False" name="tipoHospital" id="tipoHospital" value="${pessoa.tipoHospital}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoHospital == 'true'}">
                            <input type="checkbox" name="ckbHospital" id="ckbHospital" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoHospital == 'false'}">
                            <input type="checkbox" name="ckbHospital" id="ckbHospital" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
        <label class="col-md-1 control-label" for="ckbMedico" style="margin-top: 5px;">Médico</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'MED'}">
                    <input type="checkbox" name="ckbMedico" id="ckbMedico" style="margin-top: 10px;" checked disabled/>
                    <input type="hidden" value="True" name="tipoMedico" id="tipoMedico"/>
                </c:when>
                <c:when test="${tipo != 'MED'}">
                    <input type="hidden" value="False" name="tipoMedico" id="tipoMedico" value="${pessoa.tipoMedico}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoMedico == 'true'}">
                            <input type="checkbox" name="ckbMedico" id="ckbMedico" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoMedico == 'false'}">
                            <input type="checkbox" name="ckbMedico" id="ckbMedico" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>

        </div>
        <label class="col-md-1 control-label" for="ckbEnfermeiro" style="margin-top: 5px;">Enfermeiro</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'ENF'}">
                    <input type="checkbox" name="ckbEnfermeiro" id="ckbEnfermeiro" style="margin-top: 10px;" checked disabled/>
                    <input type="hidden" value="True" name="tipoEnfermeiro" id="tipoEnfermeiro"/>
                </c:when>
                <c:when test="${tipo != 'ENF'}">
                    <input type="hidden" value="False" name="tipoEnfermeiro" id="tipoEnfermeiro" value="${pessoa.tipoEnfermeiro}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoEnfermeiro == 'true'}">
                            <input type="checkbox" name="ckbEnfermeiro" id="ckbEnfermeiro" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoEnfermeiro == 'false'}">
                            <input type="checkbox" name="ckbEnfermeiro" id="ckbEnfermeiro" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
    </div>
    <br>
    <div class="row">
        <label class="col-md-2 control-label" for="ckbTransportador" style="margin-top: 5px;">Transportador</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'TRA'}">
                    <input type="checkbox" name="ckbTransportador" id="ckbTransportador" style="margin-top: 10px;" checked disabled/>
                    <input type="hidden" value="True" name="tipoTransportador" id="tipoTransportador"/>
                </c:when>
                <c:when test="${tipo != 'TRA'}">
                    <input type="hidden" value="False" name="tipoTransportador" id="tipoTransportador" value="${pessoa.tipoTransportador}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoTransportador == 'true'}">
                            <input type="checkbox"  name="ckbTransportador" id="ckbTransportador" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoTransportador == 'false'}">
                            <input type="checkbox"  name="ckbTransportador" id="ckbTransportador" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
        </div>
        <label class="col-md-1 control-label" for="ckbPaciente" style="margin-top: 5px;">Paciênte</label>
        <div class="col-md-1">
            <c:choose>
                <c:when test="${tipo == 'PAC'}">
                    <input type="checkbox" class="ckb" name="ckbPaciente" id="ckbPaciente" style="margin-top: 10px;"checked disabled/>
                    <input type="hidden" value="True" name="tipoPaciente" id="tipoPaciente"/>
                </c:when>
                <c:when test="${tipo != 'PAC'}">
                    <input type="hidden" value="False" name="tipoPaciente" id="tipoPaciente" value="${pessoa.tipoPaciente}"/>
                    <c:choose>
                        <c:when test="${pessoa.tipoPaciente == 'true'}">
                            <input type="checkbox" name="ckbPaciente" id="ckbPaciente" style="margin-top: 10px;" checked disabled/>
                        </c:when>
                        <c:when test="${pessoa.tipoPaciente == 'false'}">
                            <input type="checkbox" name="ckbPaciente" id="ckbPaciente" style="margin-top: 10px;" disabled/>
                        </c:when>
                    </c:choose>
                </c:when>
            </c:choose>
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
                        <a style="width: 80px" href="<c:url value="/pessoa_lista/${tipo}"/>" class="btn btn-primary" title="Voltar">Voltar</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </fieldset>
    </form>
    </div>


    <script>
        jQuery(function($){

            $(window).load(function() {
                $("#id_pais").change();
                $("#id_uf").change();
            });

            $("#id_pais").change(function(){
                var id_pais = $(this).val();
                $('#id_uf').empty();
                $('#id_Municipio').empty();

                if( id_pais.length <= 0 ) return;
                $.getJSON("${pageContext.request.contextPath}/carregauf-pais/"+id_pais,
                        function(result){
                            $.each(result, function(i){
                                if (result[i].id_Uf == ${pessoa.municipio.id_uf}){
                                    $("#id_uf").append('<option value="' + result[i].id_Uf + '" selected="selected">' + result[i].siglaUf + '</option>');
                                }else{
                                    $("#id_uf").append('<option value="' + result[i].id_Uf + '">' + result[i].siglaUf + '</option>');
                                }
                            });

                            $("#id_uf").trigger("change");
                        });
            });

            $("#id_uf").change(function(){
                var id_uf = $(this).val();
                $('#id_Municipio').empty();

                if( id_uf.length <= 0 ) return;
                $.getJSON("${pageContext.request.contextPath}/carregamunicipio-uf/"+id_uf,
                        function(result){
                            $.each(result, function(i){
                                if (result[i].id_municipio == ${pessoa.id_Municipio}){
                                    $("#id_Municipio").append('<option value="' + result[i].id_municipio + '" selected="selected">' + result[i].descricao + '</option>');
                                } else {
                                    $("#id_Municipio").append('<option value="' + result[i].id_municipio + '">' + result[i].descricao + '</option>');
                                }

                            });
                        });
            });

            $("#cpfCnpj").change(function(){
                if ($(this).val() != ''){
                    $.getJSON("${pageContext.request.contextPath}/pessoabycpfcnpj/"+$(this).val(),
                            function(result){
                                if (result != null){
                                    location.href="/pessoa_editar/"+ $(Tipo).attr("tipo") +"/" + result.id_Pessoa;
                                }
                            });
                }
            });

            $("#ckbCliente").change(function(){
                if($(this).is(':checked')){
                    $("#tipoCliente").val('true');
                }else{
                    $("#tipoCliente").val('false');
                }
            });

            $("#ckbFornecedor").change(function(){
                if($(this).is(':checked')){
                    $("#tipoFornecedor").val('true');
                }else{
                    $("#tipoFornecedor").val('false');
                }
            });

            $("#ckbVendedor").change(function(){
                if($(this).is(':checked')){
                    $("#tipoVendedor").val('true');
                }else{
                    $("#tipoVendedor").val('false');
                }
            });

            $("#ckbFuncionario").change(function(){
                if($(this).is(':checked')){
                    $("#tipoFuncionario").val('true');
                }else{
                    $("#tipoFuncionario").val('false');
                }
            });

            $("#ckbConvenio").change(function(){
                if($(this).is(':checked')){
                    $("#tipoConvenio").val('true');
                }else{
                    $("#tipoConvenio").val('false');
                }
            });

            $("#ckbHospital").change(function(){
                if($(this).is(':checked')){
                    $("#tipoHospital").val('true');
                }else{
                    $("#tipoHospital").val('false');
                }
            });

            $("#ckbMedico").change(function(){
                if($(this).is(':checked')){
                    $("#tipoMedico").val('true');
                }else{
                    $("#tipoMedico").val('false');
                }
            });

            $("#ckbEnfermeiro").change(function(){
                if($(this).is(':checked')){
                    $("#tipoEnfermeiro").val('true');
                }else{
                    $("#tipoEnfermeiro").val('false');
                }
            });

            $("#ckbTransportador").change(function(){
                if($(this).is(':checked')){
                    $("#tipoTransportador").val('true');
                }else{
                    $("#tipoTransportador").val('false');
                }
            });

            $("#ckbPaciente").change(function(){
                if($(this).is(':checked')){
                    $("#tipoPaciente").val('true');
                }else{
                    $("#tipoPaciente").val('false');
                }
            });
        });
    </script>
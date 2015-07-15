<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <div class="row-fluid">
                    <div class="span1" align="left">

                    </div>
                    <div class="span10">
                        <c:choose>
                            <c:when test="${tipomovimento == 'C'}">
                                <a class="brand" style="font: bold; margin-left: 26%;">Filtros Movimento Financeiro - Crédito</a>
                            </c:when>
                            <c:otherwise>
                                <a class="brand" style="font: bold; margin-left: 26%;">Filttros Movimento Financeiro - Débito</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<c:url value="/movimentofinanceiro_lista" var="filtrar"/>
<form:form class="form-signin" modelAttribute="filtros" id="FormCadastro" action="${filtrar}" method="POST">
    <div class="container">
        <div class="navbar-inner">
            <br>
            <form:input path="TipoMovimento" class="form-control" type="hidden" id="TipoMovimento" value="${tipomovimento}"/>
            <div class="row-fluid">
                <div class="span2">
                    <label style="font-size: 16px; margin-top:5px;">Empresa</label>
                </div>
                <div class="span10" style="text-align: left;">
                    <form:select path="id_Empresa" multiple="false" id="Empresa"
                                 cssClass="form-control"  required="autofocus"
                                 items="${listaempresa}"
                                 itemLabel="RazaoSocial"
                                 itemValue="id_Empresa" style="width:100%;"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2">
                    <c:choose>
                        <c:when test="${tipomovimento == 'C'}">
                            <label style="font-size: 16px; margin-top:5px;">Cliente</label>
                        </c:when>
                        <c:otherwise>
                            <label style="font-size: 16px; margin-top:5px;">Fornecedor</label>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="span10">
                    <form:input path="id_Pessoa" type="text"  maxlength="20"
                                class="form-control" placeholder="ID" id="id_Pessoa"
                                style="width:10%;" />
                    <a href="#PesquisaPessoa" class="btn btn-mini" data-toggle="modal" title="Pesquisa" style="margin-bottom: 10px;">
                        <i class="icon-search"></i>
                    </a>
                    <!-- a href="" class="btn btn-mini" id="btn_pesqpessoa" title="Pesquisa" style="margin-bottom: 10px;">
                        <i class="icon-search"></i>
                    </a -->
                    <form:input path="" type="text"  maxlength="200"
                                class="form-control" placeholder="Razão Social" id="RazaoSocial"
                                style="width:81%;" />
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2">
                    <label style="font-size: 16px; margin-top:5px;">C.Custo</label>
                </div>
                <div class="span10">
                    <form:input path="id_CentroCusto" class="form-control" type="hidden" id="id_CentroCusto"/>
                    <form:input path="" type="text"  maxlength="20"
                                class="form-control maiusculo" placeholder="Sigla" id="SiglaCCusto"
                                style="width:10%;" />
                    <a href="#PesquisaCCusto" class="btn btn-mini" data-toggle="modal" title="Pesquisa" style="margin-bottom: 10px;">
                        <i class="icon-search"></i>
                    </a>
                    <form:input path="" type="text"  maxlength="200" readonly="true"
                                class="form-control" placeholder="Descrição" id="DescricaoCCusto"
                                style="width:81%;" />
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2">
                    <label style="font-size: 16px; margin-top:5px;">P.Contas</label>
                </div>
                <div class="span10">
                    <form:input path="id_PlanoContas" class="form-control" type="hidden" id="id_PlanoContas"/>
                    <form:input path="" type="text"  maxlength="20"
                                class="form-control" placeholder="Código" id="CodigoConta"
                                style="width:10%;" />
                    <a href="#PesquisaPContas" class="btn btn-mini" data-toggle="modal" title="Pesquisa" style="margin-bottom: 10px;">
                        <i class="icon-search"></i>
                    </a>
                    <form:input path="" type="text"  maxlength="200" readonly="true"
                                class="form-control" placeholder="Descrição" id="DescricaoPContas"
                                style="width:81%;" />
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2">
                    <label style="font-size: 16px; margin-top:5px;">Competência de</label>
                </div>
                <div class="span2">
                    <form:input path="DataCompetenciaIni" type="text"  maxlength="20"
                                class="form-control datepicker" placeholder="Inicial" id="DataCompetenciaIni"
                                style="width:100%;" data-provide="datepicker"/>
                </div>
                <div class="span1">
                    <label style="font-size: 16px; margin-top:5px; text-align: center;"> até </label>
                </div>
                <div class="span2">
                    <form:input path="DataCompetenciaFim" type="text"  maxlength="20"
                                class="form-control datepicker" placeholder="Final" id="DataCompetenciaFim"
                                style="width:100%;" data-provide="datepicker"/>
                </div>
                <div class="span1">
                    <label style="font-size: 16px; margin-top:5px; text-align: center;">Status</label>
                </div>
                <div class="span2">
                    <form:select path="StatusMovimento" multiple="false" style="width:100%;">
                        <c:choose>
                            <c:when test="${filtros.statusMovimento == 'P'}">
                                <option value=""></option>
                                <option value="P" selected>Programado</option>
                                <option value="L">Liquidado</option>
                                <option value="C">Cancelado</option>
                            </c:when>
                            <c:when test="${filtros.statusMovimento == 'L'}">
                                <option value=""></option>
                                <option value="P">Programado</option>
                                <option value="L" selected>Liquidado</option>
                                <option value="C">Cancelado</option>
                            </c:when>
                            <c:when test="${filtros.statusMovimento == 'C'}">
                                <option value=""></option>
                                <option value="P">Programado</option>
                                <option value="L">Liquidado</option>
                                <option value="C" selected>Cancelado</option>
                            </c:when>
                            <c:when test="${filtros.statusMovimento == null}">
                                <option value="" selected></option>
                                <option value="P">Programado</option>
                                <option value="L">Liquidado</option>
                                <option value="C">Cancelado</option>
                            </c:when>
                            <c:when test="${filtros.statusMovimento == ''}">
                                <option value="" selected></option>
                                <option value="P">Programado</option>
                                <option value="L">Liquidado</option>
                                <option value="C">Cancelado</option>
                            </c:when>
                        </c:choose>
                    </form:select>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2">
                    <label style="font-size: 16px; margin-top:5px;">Vencimento de</label>
                </div>
                <div class="span2">
                    <form:input path="DataVencimentoIni" type="text"  maxlength="20"
                                class="form-control datepicker" placeholder="Inicial" id="DataVencimentoIni"
                                style="width:100%;" data-provide="datepicker"/>
                </div>
                <div class="span1">
                    <label style="font-size: 16px; margin-top:5px; text-align: center;"> até </label>
                </div>
                <div class="span2">
                    <form:input path="DataVencimentoFim" type="text"  maxlength="20"
                                class="form-control datepicker" placeholder="Final" id="DataVencimentoFim"
                                style="width:100%;" data-provide="datepicker"/>
                </div>
                <div class="span1">
                    <label style="font-size: 16px; margin-top:5px; text-align: center;">NSU</label>
                </div>
                <div class="span2">
                    <form:input path="id_MovimentoFinanceiro" type="text"  maxlength="10"
                                class="form-control" placeholder="NSU" id="id_MovimentoFinanceiro"
                                style="width:90%;"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2">
                    <label style="font-size: 16px; margin-top:5px;">Liquidação de</label>
                </div>
                <div class="span2">
                    <form:input path="DataLiquidacaoIni" type="text"  maxlength="20"
                                class="form-control datepicker" placeholder="Inicial" id="DataLiquidacaoIni"
                                style="width:100%;" data-provide="datepicker"/>
                </div>
                <div class="span1">
                    <label style="font-size: 16px; margin-top:5px; text-align: center;"> até </label>
                </div>
                <div class="span2">
                    <form:input path="DataLiquidacaoFim" type="text"  maxlength="20"
                                class="form-control datepicker" placeholder="Final" id="DataLiquidacaoFim"
                                style="width:100%;" data-provide="datepicker"/>
                </div>
            </div>
            <div class="row-fluid">
                <div class="span2">
                    <label style="font-size: 16px; margin-top:5px;">Valor Venc. de</label>
                </div>
                <div class="span2">
                    <form:input path="ValorVencimentoIni" type="number"  maxlength="20"
                                class="form-control" id="ValorVencimentoIni"  min="0.00" step="0.05"
                                style="width:100%;" />
                </div>
                <div class="span1">
                    <label style="font-size: 16px; margin-top:5px; text-align: center;"> até </label>
                </div>
                <div class="span2">
                    <form:input path="ValorVencimentoFim" type="number"  maxlength="20"
                                class="form-control" id="ValorVencimentoFim"  min="0.00" step="0.05"
                                style="width:100%;"/>
                </div>
                <div class="span5" align="right">

                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="container">
        <div class="navbar-inner">
            <div class="row-fluid">
                <div class="span9">
                </div>
                <div class="span1" style="text-align: right;" >
                    <br>
                    <form:button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 100px;">Filtrar</form:button>
                    <br>
                </div>
                <div class="span2" style="text-align: right;" >
                    <br>
                    <a href="<c:url value="/movimentofinanceiro_novo/${tipomovimento}"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
                    <br>
                </div>
            </div>
        </div>
    </div>
</form:form>
<br>
<br>


<!-- Modal de Pesquisa de Pessoa -->
<div id="PesquisaPessoa" class="modal fade" aria-hidden="true" style="width: 800px;">
    <div class="modal-header">
        <c:choose>
            <c:when test="${tipomovimento == 'C'}">
                <h3 id="myModalLabel">Pesquisa Clientes</h3>
            </c:when>
            <c:otherwise>
                <h3 id="myModalLabel">Pesquisa Fornecedores</h3>
            </c:otherwise>
        </c:choose>
    </div>
    <div class="modal-body">
        <div class="row-fluid">
            <div class="span2" style="text-align: left;">
                <label style="font-size: 16px; margin-top:5px;">Pesquisa</label>
            </div>
            <div class="span10" style="text-align: left;">
                <input multiple="false" id="inpfiltrospessoa" placeholder="Pesquisa"
                       cssClass="form-control"
                       style="width:580px;"/>
                <button class="btn btn-mini" id="btn_filtrarpessoa" title="Pesquisar">
                    <i class="icon-search"></i>
                </button>
            </div>
        </div>
        <br>
        <table class="table table-striped table-bordered table-hover table-condensed active" style="overflow:auto;">
            <thead style="background-color:silver">
            <tr>
                <th style="width:60px;">ID</th>
                <th style="width:80px;">CNPJ/CPF</th>
                <th>Razão Social</th>
                <th style="width:40px;"></th>
            </tr>
            </thead>
            <tbody id="tabpessoa">
            </tbody>
        </table>
    </div>
    <div class="modal-footer">
        <div class="row-fluid">
            <div class="span12" align="right">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
            </div>
        </div>
    </div>
</div>




<!-- Modal de Pesquisa Centro Custo -->
<div id="PesquisaCCusto" class="modal fade" aria-hidden="true" style="width: 800px;">
    <div class="modal-header">
        <h3 id="myModalLabel2">Pesquisa Centro de Custo</h3>
    </div>
    <div class="modal-body">
        <div class="row-fluid">
            <div class="span2" style="text-align: left;">
                <label style="font-size: 16px; margin-top:5px;">Pesquisa</label>
            </div>
            <div class="span10" style="text-align: left;">
                <input multiple="false" id="inpfiltrosccusto" placeholder="Pesquisa"
                       cssClass="form-control"
                       style="width:580px;"/>
                <button class="btn btn-mini" id="btn_filtrarccusto" title="Pesquisar">
                    <i class="icon-search"></i>
                </button>
            </div>
        </div>
        <br>
        <table class="table table-striped table-bordered table-hover table-condensed active" style="overflow:auto;">
            <thead style="background-color:silver">
            <tr>
                <th style="width:60px;">Sigla</th>
                <th>Descrição</th>
                <th style="width:40px;"></th>
            </tr>
            </thead>
            <tbody id="tabccusto">
            </tbody>
        </table>
    </div>
    <div class="modal-footer">
        <div class="row-fluid">
            <div class="span12" align="right">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
            </div>
        </div>
    </div>
</div>



<!-- Modal de Pesquisa de Pessoa -->
<div id="PesquisaPContas" class="modal fade" aria-hidden="true" style="width: 800px;">
    <div class="modal-header">
        <h3>Pesquisa Plano de Contas</h3>
    </div>
    <div class="modal-body">
        <div class="row-fluid">
            <div class="span2" style="text-align: left;">
                <label style="font-size: 16px; margin-top:5px;">Pesquisa</label>
            </div>
            <div class="span10" style="text-align: left;">
                <input multiple="false" id="inpfiltrospcontas" placeholder="Pesquisa"
                       cssClass="form-control"
                       style="width:580px;"/>
                <button class="btn btn-mini" id="btn_filtrarpcontas" title="Pesquisar">
                    <i class="icon-search"></i>
                </button>
            </div>
        </div>
        <br>
        <table class="table table-striped table-bordered table-hover table-condensed active" style="overflow:auto;">
            <thead style="background-color:silver">
            <tr>
                <th style="width:80px;">Código</th>
                <th>Descrição</th>
                <th style="width:40px;">Tipo</th>
                <th style="width:40px;"></th>
            </tr>
            </thead>
            <tbody id="tabpcontas">
            </tbody>
        </table>
    </div>
    <div class="modal-footer">
        <div class="row-fluid">
            <div class="span12" align="right">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Fechar</button>
            </div>
        </div>
    </div>
</div>



<script>
    $(document).ready(function () {
        $('.datepicker').datepicker({
            format: "dd/mm/yyyy",
            language: "pt-BR"
        });

        $("#id_Pessoa").focusout();
        planocontasbyid($("#id_PlanoContas").val());
        centrocustobyid($("#id_CentroCusto").val());
    });

    function planocontasbyid(id){
        if (id > 0){
            $.getJSON("${pageContext.request.contextPath}/planocontasbyid/"+id, function(result){
                $("#CodigoConta").val(result.codigoConta);
                $("#DescricaoPContas").val(result.descricao);
                $("#id_PlanoContas").val(result.id_PlanoContas);
            });
        }
    };

    function centrocustobyid(id){
        if (id > 0){
            $.getJSON("${pageContext.request.contextPath}/centrocustobyid/"+id, function(result){
                $("#id_CentroCusto").val(result.id_CentroCusto);
                $("#SiglaCCusto").val(result.sigla);
                $("#DescricaoCCusto").val(result.descricao);
            });
        }
    };

    function pessoabyid(id){
        var tipo = "";

        $("#id_Pessoa").val(0);
        $("#RazaoSocial").val("");

        if (${tipomovimento =='C'}){
            tipo = 'CLI';
        } else {
            tipo = 'FOR';
        }

        if( id.trim().length > 0 ) {
            $.getJSON("${pageContext.request.contextPath}/pessoabytipoid/"+tipo+"/"+id,
                    function(result){
                        $("#id_Pessoa").val(result.id_Pessoa);
                        $("#RazaoSocial").val(result.razaoSocial);
                    });
        }
    };




    $("#id_Pessoa").focusout(function(){
        pessoabyid($(this).val());
    });

    $("#SiglaCCusto").focusout(function(){
        var sigla = $("#SiglaCCusto").val();
        var empresa =  $("#Empresa").val();

        $("#id_CentroCusto").val(0);
        $("#SiglaCCusto").val("");
        $("#DescricaoCCusto").val("");

        if (sigla.trim().length > 0){
            var campos = [empresa.toString(), sigla];
            $.getJSON("${pageContext.request.contextPath}/centrocustobysigla/"+campos,
                    function(result){
                        $("#id_CentroCusto").val(result.id_CentroCusto);
                        $("#SiglaCCusto").val(result.sigla);
                        $("#DescricaoCCusto").val(result.descricao);
                    })
        }
    });

    $("#CodigoConta").focusout(function(){
        var codigo = $("#CodigoConta").val();
        var empresa =  $("#Empresa").val();
        var tipoconta = $("#TipoMovimento").val();

        $("#CodigoConta").val("");
        $("#DescricaoPContas").val("");
        $("#id_PlanoContas").val(0);

        if (codigo.trim().length > 0){
            codigo = codigo.replace(".","").replace(".","");
            var campos = [empresa.toString(), tipoconta.toString(), codigo.toString()];

            $.getJSON("${pageContext.request.contextPath}/planocontasbyconta/"+campos, function(result){
                $("#CodigoConta").val(result.codigoConta);
                $("#DescricaoPContas").val(result.descricao);
                $("#id_PlanoContas").val(result.id_PlanoContas);
            });
        }
    });


    $("#btn_filtrarpessoa").click(function(){

        //my_window = window.open ("/frmpesqpessoa_filtros/CLI","mywindow","status=1,width=800,height=700");
        //my_window.moveTo(300, 100);
        var filtros = "";
        var tipo = "";

        if (${tipomovimento =='C'}){
            tipo = 'CLI';
        } else {
            tipo = 'FOR';
        }

        if ($("#inpfiltrospessoa").val().trim().length > 0){
            filtros = $("#inpfiltrospessoa").val();
        }

        var campos = [tipo, filtros];
        $("#tabpessoa").empty();
        $.getJSON("${pageContext.request.contextPath}/pessoabyfiltros/"+campos, function(result){
            $.each(result, function(i){
                $("#tabpessoa").append('<tr>');
                $("#tabpessoa").append('<td>'+result[i].id_Pessoa+'</td>');
                $("#tabpessoa").append('<td>'+result[i].cpfCnpj+'</td>');
                $("#tabpessoa").append('<td>'+result[i].razaoSocial+'</td>');
                $("#tabpessoa").append('<td><button class="btn btn-mini" id="'+result[i].id_Pessoa+'" onclick="PessoaSelClick(this.id)" title="Selecionar" data-dismiss="modal" aria-hidden="true"><i class="icon-ok"></i></button></td>');
                $("#tabpessoa").append('</tr>');
            });
        });
    });


    $("#btn_filtrarccusto").click(function(){
        var filtros = "";
        var empresa = $("#Empresa").val();

        if ($("#inpfiltrosccusto").val().trim().length > 0){
            filtros = $("#inpfiltrosccusto").val();
        }

        var campos = [empresa, filtros];
        $("#tabccusto").empty();
        $.getJSON("${pageContext.request.contextPath}/lista_centrocusto/"+campos, function(result){
            $.each(result, function(i){
                $("#tabccusto").append('<tr>');
                $("#tabccusto").append('<td>'+result[i].sigla+'</td>');
                $("#tabccusto").append('<td>'+result[i].descricao+'</td>');
                $("#tabccusto").append('<td><button class="btn btn-mini" id="'+result[i].id_CentroCusto+'" onclick="CCustoSelClick(this.id)" title="Selecionar" data-dismiss="modal" aria-hidden="true"><i class="icon-ok"></i></button></td>');
                $("#tabccusto").append('</tr>');
            });
        });
    });

    $("#btn_filtrarpcontas").click(function(){
        var filtros = "";
        var empresa = $("#Empresa").val();
        var tipoconta = $("#TipoMovimento").val();

        if ($("#inpfiltrospcontas").val().trim().length > 0){
            filtros = $("#inpfiltrospcontas").val();
        }

        var campos = [empresa, tipoconta, filtros];
        $("#tabpcontas").empty();
        $.getJSON("${pageContext.request.contextPath}/lista_planocontas/"+campos, function(result){
            $.each(result, function(i){
                $("#tabpcontas").append('<tr>');
                $("#tabpcontas").append('<td>'+result[i].codigoConta+'</td>');
                $("#tabpcontas").append('<td>'+result[i].descricao+'</td>');
                $("#tabpcontas").append('<td>'+result[i].agrupamento+'</td>');
                if (result[i].agrupamento == 'A')
                {
                    $("#tabpcontas").append('<td><button class="btn btn-mini" id="'+result[i].id_PlanoContas+'" onclick="PContasSelClick(this.id)" title="Selecionar" data-dismiss="modal" aria-hidden="true"><i class="icon-ok"></i></button></td>');
                } else{
                    $("#tabpcontas").append('<td><button class="btn btn-mini" id="'+result[i].id_PlanoContas+'" title="Selecionar"><i class=" icon-ban-circle"></i></button></td>');
                }
                $("#tabpcontas").append('</tr>');
            });
        });
    });

    function PessoaSelClick(id){
        $("#id_Pessoa").val(id);
        $("#id_Pessoa").focusout();
    };

    function CCustoSelClick(id){
        centrocustobyid(id);
    };

    function PContasSelClick(id){
        planocontasbyid(id);
    };

</script>
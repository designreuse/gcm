<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/movimentofinanceiro_insert" var="gravar"/>
        <form:form class="form-signin" modelAttribute="movimentofinanceiro" id="FormCadastro" action="${gravar}" method="POST">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <c:choose>
                            <c:when test="${tipomovimento == 'C'}">
                                <a class="brand" style="font: bold; margin-left: 30%;">Novo Movimento Financeiro - Crédito</a>
                            </c:when>
                            <c:otherwise>
                                <a class="brand" style="font: bold; margin-left: 30%;">Novo Movimento Financeiro - Débito</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <form:input path="TipoMovimento" class="form-control" type="hidden" id="TipoMovimento" value="${tipomovimento}"/>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Empresa</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:select path="id_Empresa" multiple="false" id="Empresa"
                                     cssClass="form-control" required="autofocus"
                                     items="${listaempresa}"
                                     itemLabel="RazaoSocial"
                                     itemValue="id_Empresa" style="width:100%;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">NSU</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input path="id_MovimentoFinanceiro" type="text"  maxlength="5"  readonly="true"
                                    class="form-control maiusculo" id="id_MovimentoFinanceiro" placeholder="NSU"
                                    style="width:90px;" />
                    </div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Data Movimento</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input path="dataMovimento" type="text"  maxlength="5"  disabled="true"
                                    class="form-control datepicker" id="dataMovimento" placeholder="Data Movimento"
                                    style="width:90%;"/>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Status</label>
                    </div>
                    <div class="span2">
                        <form:input path="statusMovimento" class="form-control" type="hidden" value="P"/>
                        <form:input path="" type="text"  maxlength="5"  disabled="true" value="Programado"
                                    class="form-control datepicker" id="statusMovimento" placeholder="Status"
                                    style="width:90%;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
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
                        <form:input path="id_Pessoa" type="text"  maxlength="10" requerid="true"
                                    class="form-control" placeholder="ID" id="id_Pessoa"
                                    style="width:63%;" />
                        <a href="#PesquisaPessoa" class="btn btn-mini" data-toggle="modal" title="Pesquisa" style="margin-bottom: 15px;">
                            <i class="icon-search"></i>
                        </a>
                        <form:input path="" type="text"  maxlength="200" readonly="true"
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
                        <form:input path="" type="text"  maxlength="20" requerid="true"
                                    class="form-control maiusculo" placeholder="Sigla" id="SiglaCCusto"
                                    style="width:10%;" />
                        <a href="#PesquisaCCusto" class="btn btn-mini" data-toggle="modal" title="Pesquisa" style="margin-bottom: 15px;">
                            <i class="icon-search"></i>
                        </a>
                        <form:input path="" type="text"  maxlength="200" readonly="true"
                                    class="form-control" placeholder="Centro de Custo" id="DescricaoCCusto"
                                    style="width:81%;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">P.Contas</label>
                    </div>
                    <div class="span10">
                        <form:input path="id_PlanoContas" class="form-control" type="hidden" id="id_PlanoContas"/>
                        <form:input path="" type="text"  maxlength="20" requerid="true"
                                    class="form-control" placeholder="Código" id="CodigoConta"
                                    style="width:10%;" />
                        <a href="#PesquisaPContas" class="btn btn-mini" data-toggle="modal" title="Pesquisa" style="margin-bottom: 15px;">
                            <i class="icon-search"></i>
                        </a>
                        <form:input path="" type="text"  maxlength="200" readonly="true"
                                    class="form-control" placeholder="Plano de Contas" id="DescricaoPContas"
                                    style="width:81%;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Vencimento</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input path="dataVencimento" type="text"  maxlength="10" requerid="true"
                                    class="form-control datepicker" id="dataVencimento" placeholder="Data Vencimento"
                                    style="width:90%;"/>
                    </div>
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Comp. Inicial</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input path="dataCompetenciaIni" type="text"  maxlength="10" requerid="true"
                                    class="form-control datepicker" id="dataCompetenciaIni" placeholder="Inicial"
                                    style="width:90%;"/>
                    </div>
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Comp. Final</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input path="dataCompetenciaFim" type="text"  maxlength="10" requerid="true"
                                    class="form-control datepicker" id="dataCompetenciaFim" placeholder="Final"
                                    style="width:90%;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Valor Vencimento</label>
                    </div>
                    <div class="span2">
                        <form:input path="valorVencimento" type="number"  maxlength="20" requerid="true"
                                    class="form-control valliq" id="valorVencimento"  min="0.00" step="0.05"
                                    style="width:90%;"/>
                    </div>
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Valor Descontos</label>
                    </div>
                    <div class="span2">
                        <form:input path="valorDescontos" type="number"  maxlength="20"
                                    class="form-control valliq" id="valorDescontos"  min="0.00" step="0.05"
                                    style="width:90%;"/>
                    </div>
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Valor Acréscimos</label>
                    </div>
                    <div class="span2">
                        <form:input path="valorAcrescimos" type="number"  maxlength="20"
                                    class="form-control valliq" id="valorAcrescimos"  min="0.00" step="0.05"
                                    style="width:90%;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Valor Multa</label>
                    </div>
                    <div class="span2">
                        <form:input path="valorMulta" type="number"  maxlength="20"
                                    class="form-control valliq" id="valorMulta"  min="0.00" step="0.05"
                                    style="width:90%;"/>
                    </div>
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Juros (%)</label>
                    </div>
                    <div class="span2">
                        <form:input path="aliquotaJuros" type="number"  maxlength="20"
                                    class="form-control valliq" id="aliquotaJuros"  min="0.00" max="100.00" step="0.05"
                                    style="width:90%;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Banco</label>
                    </div>
                    <div class="span2">
                        <form:select path="" multiple="false" id="Bancos"
                                     cssClass="form-control"
                                     items="${listabancos}"
                                     itemLabel="descricao"
                                     itemValue="id_Banco" style="width:100%;"/>
                    </div>
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Conta Corrente</label>
                    </div>
                    <div class="span2">
                        <form:select path="id_ContaCorrente" multiple="false" id="id_ContaCorrente"
                                     cssClass="form-control" style="width:100%;"/>
                    </div>
                    <div class="span2">
                        <label style="font-size: 16px; margin-top:5px;">Valor Líquido</label>
                    </div>
                    <div class="span2">
                        <form:input path="valorLiquido" type="number"  maxlength="20"  disabled="true"
                                    class="form-control" id="valorLiquido"  min="0.00" step="0.05"
                                    style="width:90%;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Observações</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:textarea id="observacoes" path="observacoes" type="text"
                                       class="form-control" rows="3" cols="70"
                                       name="observacoes" placeholder="Observações" style="width:100%; height:40px"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/movimentofinanceiro_filtros/${tipomovimento}"/>">Cancelar</a>
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

        $("#Bancos").change();
    });

    $("#Bancos").change(function(){
        var empresa =  $("#Empresa").val();
        var Banco =  $("#Bancos").val();
        var campos = [empresa, Banco]

        $("#id_ContaCorrente").empty();
        $.getJSON("${pageContext.request.contextPath}/lista_contacorrente/"+campos, function(result){
            $.each(result, function(i){
                $("#id_ContaCorrente").append('<option value="' + result[i].id_ContaCorrente + '">' + result[i].numeroContaCorrente + '</option>');
            });
        });
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
        $("#Pessoa").val("");
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
        pessoabyid($("#id_Pessoa").val());
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
        pessoabyid(id);
    };

    function CCustoSelClick(id){
        centrocustobyid(id);
    };

    function PContasSelClick(id){
        planocontasbyid(id);
    };

    function CalcValorLiquido(){
        var ValLiq = 0;
        var ValVencimento = 0;
        var ValDescontos = 0;
        var ValAcrescimos = 0;
        var ValMulta = 0;
        var AliqJuros = 0;
        var ValJuros = 0;
        var QtdDias = 0;

        if ($("#valorVencimento").val() > 0){
            ValVencimento = $("#valorVencimento").val();
        }
        if ($("#valorDescontos").val() > 0){
            ValDescontos = $("#valorDescontos").val();
        };
        if ($("#valorAcrescimos").val() > 0){
            ValAcrescimos = $("#valorAcrescimos").val();
        };
        if ($("#valorMulta").val() > 0){
            ValMulta = $("#valorMulta").val();
        };
        if ($("#aliquotaJuros").val() > 0){
            AliqJuros = $("#aliquotaJuros").val();
        };

        ValLiq = ValVencimento + ValAcrescimos + ValMulta + ValJuros - ValDescontos;
        $("#valorLiquido").val(ValLiq);
    };

    $(".valliq").focusout(function(){
        //CalcValorLiquido();
    });
</script>


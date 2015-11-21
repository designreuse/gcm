<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="modal" id="pessoa_pesquisa">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                <c:choose>
                    <c:when test="${tipomovimento == 'C'}">
                        <h4 class="modal-title">Pesquisar Clientes</h4>
                    </c:when>
                    <c:when test="${tipomovimento == 'D'}">
                        <h4 class="modal-title">Pesquisar Fornecedor</h4>
                    </c:when>
                    <c:otherwise>
                        <h4 class="modal-title">Pesquisar Pessoa</h4>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="modal-body">
                <div class="box-header with-border">
                    <div class="form-group">
                        <div class="row">
                            <label class="col-md-3 control-label" for="CnpjCpf" style="margin-top: 5px;">Cnpj\Cpf</label>
                            <div class="col-md-4">
                                <input type="text" maxlength="14"
                                       class="form-control" placeholder="Cnpj\Cpf" id="CnpjCpf"/>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <label class="col-md-3 control-label" for="RazaoSocial" style="margin-top: 5px;">Razão Social</label>
                            <div class="col-md-7">
                                <input type="text" maxlength="14"
                                       class="form-control" placeholder="Razão Social" id="RazaoSocial"/>
                            </div>
                            <button type="button" class="btn btn-default" id="btnpesqpessoa">Filtrar</button>
                        </div>
                    </div>
                </div>
                <div class="box-body">

                    <div class="row" style="height: 250px; overflow:auto;">
                        <div class="col-sm-12">
                            <div class="box box-primary">
                                <div class="box-body">
                                    <table class="table table-bordered  table-hover table-striped">
                                        <thead style="background-color:silver;">
                                        <tr>
                                            <th style="width:60px;">ID</th>
                                            <th style="width:80px;">Cnpj\Cpf</th>
                                            <c:choose>
                                                <c:when test="${tipomovimento == 'C'}">
                                                    <th>Cliente</th>
                                                </c:when>
                                                <c:when test="${tipomovimento == 'D'}">
                                                    <th>Fornecedor</th>
                                                </c:when>
                                                <c:otherwise>
                                                    <th>Pessoa</th>
                                                </c:otherwise>
                                            </c:choose>
                                            <th style="width:40px;">Selecionar</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tabpessoa">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<div class="modal" id="centrocusto_pesquisa">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                <h4 class="modal-title">Pesquisar Centro de Custo</h4>
            </div>
            <div class="modal-body">
                <div class="box-header with-border">
                    <div class="form-group">
                        <div class="row">
                            <label class="col-md-3 control-label" for="sigla" style="margin-top: 5px;">Sigla</label>
                            <div class="col-md-4">
                                <input type="text" maxlength="5"
                                       class="form-control" placeholder="Sigla" id="sigla"/>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <label class="col-md-3 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                            <div class="col-md-7">
                                <input type="text" maxlength="14"
                                       class="form-control" placeholder="Descrição" id="descricao"/>
                            </div>
                            <button type="button" class="btn btn-default" id="btnpesqccusto">Filtrar</button>
                        </div>
                    </div>
                </div>
                <div class="box-body">

                    <div class="row" style="height: 250px; overflow:auto;">
                        <div class="col-sm-12">
                            <div class="box box-primary">
                                <div class="box-body">
                                    <table class="table table-bordered  table-hover table-striped">
                                        <thead style="background-color:silver;">
                                        <tr>
                                            <th style="width:60px;">Sigla</th>
                                            <th>Descrição</th>
                                            <th style="width:40px;">Selecionar</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tabcentrocusto">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<div class="modal" id="planocontas_pesquisa">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>

                <h4 class="modal-title">Pesquisar Plano de Contas</h4>
            </div>
            <div class="modal-body">
                <div class="box-header with-border">
                    <div class="form-group">
                        <div class="row">
                            <label class="col-md-3 control-label" for="codigo" style="margin-top: 5px;">Código</label>
                            <div class="col-md-4">
                                <input type="text" maxlength="5"
                                       class="form-control" placeholder="Código" id="codigo"/>
                            </div>
                        </div>
                        <br>
                        <div class="row">
                            <label class="col-md-3 control-label" for="descricaoPlanoContas" style="margin-top: 5px;">Descrição</label>
                            <div class="col-md-7">
                                <input type="text" maxlength="14"
                                       class="form-control" placeholder="Descrição" id="descricaoPlanoContas"/>
                            </div>
                            <button type="button" class="btn btn-default" id="btnpesqpcontas">Filtrar</button>
                        </div>
                    </div>
                </div>
                <div class="box-body">

                    <div class="row" style="height: 250px; overflow:auto;">
                        <div class="col-sm-12">
                            <div class="box box-primary">
                                <div class="box-body">
                                    <table class="table table-bordered  table-hover table-striped">
                                        <thead style="background-color:silver;">
                                        <tr>
                                            <th style="width:100px;">Código</th>
                                            <th>Descrição</th>
                                            <th style="width:40px;">Selecionar</th>
                                        </tr>
                                        </thead>
                                        <tbody id="tabplanocontas">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>


<script>
    //------------------------- Pessoa -------------------------//

    $("#btnpesqpessoa").click(function(){
        if (${tipomovimento =='C'}){
            tipo = 'CLI';
        } else {
            tipo = 'FOR';
        }

        campos = [tipo, $("#CnpjCpf").val(), $("#RazaoSocial").val()];
        $("#tabpessoa").empty();
        $.getJSON("${pageContext.request.contextPath}/pessoabyfiltros/"+campos, function(result){
            $.each(result, function(i){
                $("#tabpessoa").append('<tr>');
                $("#tabpessoa").append('<td>'+result[i].id_Pessoa+'</td>');
                $("#tabpessoa").append('<td>'+result[i].cpfCnpj+'</td>');
                $("#tabpessoa").append('<td>'+result[i].razaoSocial+'</td>');
                $("#tabpessoa").append('<td style="text-align: center;"><button class="btn btn-default" onclick="pessoabyid('+result[i].id_Pessoa+')" title="Selecionar" data-dismiss="modal" aria-hidden="true"><i class="fa fa-sign-in"></i></button></td>');
                $("#tabpessoa").append('</tr>');
            });
        });
    });

    function pessoabyid(id){
        $("#id_Pessoa").val(0);
        $("#razaoSocial").val("");

        if( id > 0 ) {
            $.getJSON("${pageContext.request.contextPath}/pessoabyid/"+id,
                    function(result){
                        $("#id_Pessoa").val(result.id_Pessoa);
                        $("#razaoSocial").val(result.razaoSocial);
                    });
        }
    };

    //------------------------- Centro de Custo -------------------------//

    $("#btnpesqccusto").click(function(){
        empresa = $("#id_Empresa").val();

        campos = [empresa, $("#sigla").val(), $("#descricao").val()];
        $("#tabcentrocusto").empty();
        $.getJSON("${pageContext.request.contextPath}/lista_centrocusto/"+campos, function(result){
            $.each(result, function(i){
                $("#tabcentrocusto").append('<tr>');
                $("#tabcentrocusto").append('<td>'+result[i].sigla+'</td>');
                $("#tabcentrocusto").append('<td>'+result[i].descricao+'</td>');
                $("#tabcentrocusto").append('<td style="text-align: center;"><button class="btn btn-default" onclick="ccustobyid('+result[i].id_CentroCusto+')" title="Selecionar" data-dismiss="modal" aria-hidden="true"><i class="fa fa-sign-in"></i></button></td>');
                $("#tabcentrocusto").append('</tr>');
            });
        });
    });

    function ccustobyid(id){
        $("#id_CentroCusto").val(0);
        $("#siglaccusto").val("");
        $("#descricaoCentroCusto").val("");

        if( id > 0 ) {
            $.getJSON("${pageContext.request.contextPath}/centrocustobyid/"+id,
                function(result){
                    $("#id_CentroCusto").val(result.id_CentroCusto);
                    $("#siglaccusto").val(result.sigla);
                    $("#descricaoCentroCusto").val(result.descricao);
                });
        }
    };

    function centrocustobysigla(sigla){
        $("#id_CentroCusto").val(0);
        $("#siglaccusto").val("");
        $("#descricaoCentroCusto").val("");

        if (!sigla.trim() == ""){
            empresa = $("#id_Empresa").val();
            campos = [empresa, sigla];
            $.getJSON("${pageContext.request.contextPath}/centrocustobysigla/"+campos,
                    function(result){
                        $("#id_CentroCusto").val(result.id_CentroCusto);
                        $("#siglaccusto").val(result.sigla);
                        $("#descricaoCentroCusto").val(result.descricao);
                    });
        }
    };

    //-------------------------Plano de Contas -------------------------//

    $("#btnpesqpcontas").click(function(){
        empresa = $("#id_Empresa").val();
        if (${tipomovimento =='C'}){
            tipo = 'C';
        } else {
            tipo = 'D';
        }
        codigo = $("#codigo").val().replace(".","").replace(".","").replace(".","");
        descricao = $("#descricaoPlanoContas").val();

        campos = [empresa, tipo, codigo, descricao];
        $("#tabplanocontas").empty();
        $.getJSON("${pageContext.request.contextPath}/lista_planocontas/"+campos, function(result){
            $.each(result, function(i){
                $("#tabplanocontas").append('<tr>');
                $("#tabplanocontas").append('<td>'+result[i].codigoConta+'</td>');
                $("#tabplanocontas").append('<td>'+result[i].descricao+'</td>');
                $("#tabplanocontas").append('<td style="text-align: center;"><button class="btn btn-default" onclick="planocontasbyid('+result[i].id_PlanoContas+')" title="Selecionar" data-dismiss="modal" aria-hidden="true"><i class="fa fa-sign-in"></i></button></td>');
                $("#tabplanocontas").append('</tr>');
            });
        });
    });

    function planocontasbyid(id){
        $("#id_PlanoContas").val(0);
        $("#codigoconta").val("");
        $("#descricaoPlanoContas").val("");

        if (id > 0){
            $.getJSON("${pageContext.request.contextPath}/planocontasbyid/"+id,
                    function(result){
                        $("#id_PlanoContas").val(result.id_PlanoContas);
                        $("#codigoconta").val(result.codigoConta);
                        $("#descricaoPlanoContas").val(result.descricao);
                    });
        }
    };

    function planocontasbycodigo(campos){
        $("#id_PlanoContas").val(0);
        $("#codigoconta").val("");
        $("#descricaoPlanoContas").val("");

        empresa = campos[0];
        tipo = campos[1];
        codigo = campos[2];

        if (!codigo.trim() == ""){
            campos = [empresa, tipo, codigo];
            $.getJSON("${pageContext.request.contextPath}/planocontasbycodigo/"+campos,
                    function(result){
                        $("#id_PlanoContas").val(result.id_PlanoContas);
                        $("#codigoconta").val(result.codigoConta);
                        $("#descricaoPlanoContas").val(result.descricao);
                    });
        }
    }
</script>
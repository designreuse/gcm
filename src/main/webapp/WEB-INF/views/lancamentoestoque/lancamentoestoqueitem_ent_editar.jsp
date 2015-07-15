<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/lancamentoestoqueitem_update/${TipoEntSai}" var="gravar"/>
        <form:form class="form-signin" modelAttribute="lancamentoestoqueitem" id="FormCadastro" action="${gravar}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 32%;">Entrada por Ajuste - Editar Item</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Depósito</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:input path="id_LancamentoEstoque" id="id_LancamentoEstoque" class="form-control" type="hidden"/>
                        <form:input path="id_LancamentoEstoqueItem" id="id_LancamentoEstoqueItem" class="form-control" type="hidden"/>

                        <form:select path="id_Deposito" multiple="false" id="Deposito"
                                     cssClass="form-control" required="autofocus"
                                     items="${listadeposito}"
                                     itemLabel="Descricao"
                                     itemValue="id_Deposito" style="width:710px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Produto</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input path="" type="text"  maxlength="50" required="true"
                                    class="form-control" id="id_Produto" placeholder="ID"
                                    style="width:80px;" />
                        <a href="#PesquisaProduto" class="btn btn-mini" data-toggle="modal" title="Pesquisa" style="margin-bottom: 15px;">
                            <i class="icon-search"></i>
                        </a>
                    </div>

                    <div class="span2" style="text-align: left;">
                        <form:input path="" type="text"  maxlength="50" required="false" readonly="true"
                                    class="form-control" id="Referencia" placeholder="Referência"
                                    style="width:100%;" />
                    </div>
                    <div class="span6" style="text-align: left;">
                        <form:input path="" type="text"  maxlength="50" required="false" readonly="true"
                                    class="form-control" id="Descricao" placeholder="Descrição"
                                    style="width:404px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Lote</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:input path="id_ProdutoLote" id="id_ProdutoLote" class="form-control" type="hidden"/>
                        <form:input path="" multiple="false" id="NumeroLote" placeholder="Lote"
                                    cssClass="form-control" required="true"  type="text"
                                    style="width:70%;"/>
                        <a href="" class="btn btn-mini" data-toggle="modal" id="btnnovolote" style="width: 20px; margin-bottom: 15px">
                            <img src="/static/img/Cute Ball - Stop.png" id="img_lote" placeholder="Lote não Cadastrado"></i>
                        </a>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Unidade</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:select path="id_ProdutoUnidade" multiple="false" id="ProdutoUnidade"
                                     cssClass="form-control" required="true"
                                     itemLabel="Sigla"
                                     itemValue="id_ProdutoUnidade" style="width:100%;"/>
                    </div>
                    <div class="span2" style="text-align: right;">
                        <label style="font-size: 16px; margin-top:5px;">Quantidade</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input path="quantidade" type="number"  maxlength="20" required="true"
                                    class="form-control" name="quantidade" placeholder="Quantidade"
                                    style="width:112px;" min="0.00" step="1.00"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/lancamentoestoque_editar/${TipoEntSai}/${id_lancamentoestoque}"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>


<!-- Modal de Pesquisa de Produto -->
<div id="PesquisaProduto" class="modal fade" aria-hidden="true" style="width: 800px;">
    <div class="modal-header">
        <h3 id="myModalLabel">Pesquisa Produtos</h3>
    </div>
    <div class="modal-body">
        <div class="row-fluid">
            <div class="span2" style="text-align: left;">
                <label style="font-size: 16px; margin-top:5px;">Pesquisa</label>
            </div>
            <div class="span10" style="text-align: left;">
                <input multiple="false" id="inpfiltros" placeholder="Pesquisa"
                       cssClass="form-control" required="true"
                       style="width:600px;"/>
                <button class="btn btn-mini" id="btnfiltros" title="Pesquisar">
                    <i class="icon-search"></i>
                </button>
            </div>
        </div>
        <br>
        <table class="table table-striped table-bordered table-hover table-condensed active" id="Produtos">
            <thead style="background-color:silver">
            <tr>
                <th style="width:60px;">ID</th>
                <th style="width:80px;">Referência</th>
                <th>Descrição</th>
                <th style="width:40px;"></th>
            </tr>
            </thead>
            <tbody id="tabprodutos" style="overflow:auto; height: 350px;">
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

<!-- Modal de Inclusão de Lote -->
<div id="ModalNovoLote" class="modal fade" aria-hidden="true" style="width: 800px;">
    <div class="modal-header">
        <h3>Salvar Lote</h3>
    </div>
    <div class="modal-body">
        <div class="row-fluid">
            <div class="span3" style="text-align: left;">
                <label style="font-size: 16px; margin-top:5px;">Data Fabricação</label>
            </div>
            <div class="span3" style="text-align: left;">
                <input multiple="false" id="DataFabricacao" placeholder="Data Fabricacao"
                       cssClass="form-control" required="true"  type="date"
                       style="width:70%;"/>
            </div>
            <div class="span3" style="text-align: left;">
                <label style="font-size: 16px; margin-top:5px;">Data Validade</label>
            </div>
            <div class="span3" style="text-align: left;">
                <input multiple="false" id="DataValidade" placeholder="Data Validade"
                       cssClass="form-control" required="true" type="date"
                       style="width:70%;"/>
            </div>
        </div>
    </div>
    <div class="modal-footer">
        <div class="row-fluid">
            <div class="span6" align="right">
                <button class="btn btn-lg btn-primary btn-block" style="width: 100px;" data-dismiss="modal" aria-hidden="true" id="salvar_lote">Salvar</button>
            </div>
            <div class="span6" align="left">
                <button class="btn btn-lg btn-primary btn-block" style="width: 100px;" data-dismiss="modal" aria-hidden="true">Cancelar</button>
            </div>
        </div>
    </div>
</div>

<script>
    $( document ).ready(function() {
        $("#id_Produto").val(${produto.id_Produto});
        $("#id_Produto").focusout();
        $("#id_Produto").val(${produto.id_Produto});
        $("#NumeroLote").val(${produtolote.numeroLote});
        $("#NumeroLote").focusout();
    });

    $("#NumeroLote").focusout(function(){
        var id_produto = $("#id_Produto").val();
        var numerolote = $("#NumeroLote").val();

        $("#id_ProdutoLote").val("");
        $("#img_lote").attr('src', '/static/img/Cute Ball - Stop.png');

        if ($("#NumeroLote").val().trim().length > 0){
            $("#btnnovolote").attr('href','#ModalNovoLote');
        }

        if (numerolote.trim().length > 0){
            $.getJSON("${pageContext.request.contextPath}/produtolotebynumerolote/"+id_produto+"/"+numerolote,
                    function(result){
                        $("#id_ProdutoLote").val("");
                        $("#id_ProdutoLote").val(result.id_ProdutoLote);
                        $("#img_lote").attr('src', '/static/img/Cute Ball - Go.png');
                        $("#btnnovolote").attr('href','');
                        $("#NumeroLoteNovo").attr('value', numerolote);
                    });
        }
    });

    $("#id_Produto").focusout(function(){
        var id_produto = $("#id_Produto").val();

        $("#id_Produto").val("");
        $("#Referencia").val("");
        $("#Descricao").val("");
        $('#ProdutoUnidade').empty();
        $("#NumeroLote").val("");

        $("#id_ProdutoLote").val("");
        $("#img_lote").attr('src', '/static/img/Cute Ball - Stop.png');
        $("#novo_lote").attr('href', '');

        if( id_produto.trim().length > 0 ) {
            $.getJSON("${pageContext.request.contextPath}/produtobyid/"+id_produto,
                    function(result){
                        $("#id_Produto").val(result.id_Produto);
                        $("#Referencia").val(result.referencia);
                        $("#Descricao").val(result.descricao);

                        if (result.id_Produto > 0){
                            $.getJSON("${pageContext.request.contextPath}/lista_produtounidade/"+result.id_Produto,
                                    function(result){
                                        $('#ProdutoUnidade').empty();
                                        $.each(result, function(i){
                                            if (result[i].id_ProdutoUnidade == ${lancamentoestoqueitem.id_ProdutoUnidade}){
                                                $("#ProdutoUnidade").append('<option value="' + result[i].id_ProdutoUnidade + '" selected="selected">' + result[i].unidade.sigla + '</option>');
                                            }else{
                                                $("#ProdutoUnidade").append('<option value="' + result[i].id_ProdutoUnidade + '">' + result[i].unidade.sigla + '</option>');
                                            }
                                        });
                                    });
                        }
                    });
        }
    });

    $("#btnfiltros").click(function(){
        var filtros = $("#inpfiltros").val();

        $("#tabprodutos").empty();
        $.getJSON("${pageContext.request.contextPath}/produtobyfiltros/"+filtros,
                function(result){
                    $.each(result, function(i){
                        $("#tabprodutos").append('<tr>');
                        $("#tabprodutos").append('<td>'+result[i].id_Produto+'</td>');
                        $("#tabprodutos").append('<td>'+result[i].referencia+'</td>');
                        $("#tabprodutos").append('<td>'+result[i].descricao+'</td>');
                        $("#tabprodutos").append('<td><button class="btn btn-mini" id="'+result[i].id_Produto+'" title="Selecionar" data-dismiss="modal" aria-hidden="true"><i class="icon-ok"></i></button></td>');
                        $("#tabprodutos").append('</tr>');

                        $("#"+result[i].id_Produto).trigger("SelClick"); <!-- onclick="SelClick(this.id)" -->
                    });
                });
    });

    function SelClick(id) {
        $("#id_Produto").val(id);
        $("#id_Produto").focusout();
    }

    $("#salvar_lote").click(function(){
        var id_produto = $("#id_Produto").val();
        var lote       = $("#NumeroLote").val();
        var fabricacao = $("#DataFabricacao").val();
        var validade   = $("#DataValidade").val();

        $.getJSON("${pageContext.request.contextPath}/insert_produtolote/"+id_produto+"/"+lote+"/"+fabricacao+"/"+validade,function(){});
        $("#NumeroLote").focusout();
    });
</script>



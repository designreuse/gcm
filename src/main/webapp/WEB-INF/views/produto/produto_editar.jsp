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
        <c:url value="/produto_update" var="update"/>
        <form:form class="form-signin" modelAttribute="produto" id="FormCadastro" action="${update}" method="PUT">
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Editar Produto</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Id</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input id="id_Produto" path="id_Produto" type="text"
                                    readonly="true" maxlength="50" class="form-control"
                                    name="ID" placeholder="ID" style="width:80px;" required="true"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Referência</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input id="referencia" path="referencia" type="text"
                                    maxlength="15" class="form-control"  name="Referencia"
                                    placeholder="Referência" style="width:188px;"/>
                    </div>
                </div>

                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Descrição</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="descricao" type="text" maxlength="150"
                                    class="form-control"  name="Descricao" placeholder="Descrição"
                                    style="width:625px;" required="true"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Marca</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:select path="id_MarcaProduto" multiple="false" id="lista_Marca"
                                     cssClass="form-control" required="true"
                                     items="${lista_Marca}"
                                     itemLabel="descricao"
                                     itemValue="id_MarcaProduto" style="width:640px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Grupo</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:select path="grupoProduto.id_GrupoProduto" multiple="false" id="listaGrupo"
                                     cssClass="form-control" required="true"
                                     items="${lista_GrupoProduto}"
                                     itemLabel="descricao"
                                     itemValue="id_GrupoProduto" style="width:200px;"/>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">SubGrupo</label>
                    </div>
                    <div class="span3" style="text-align: right;">
                        <form:input path="id_SubGrupoProduto" id="id_SubGrupoProduto_imput" type="hidden" maxlength="50" class="form-control" name="id_uf" placeholder="id_uf" style="width:80px;" />
                        <form:select  path="id_SubGrupoProduto"  multiple="false" id="listaSubGrupo"
                                      style="width:200px;" cssClass="form-control" required="true"
                                      itemLabel="id_SubGrupoProduto"
                                      itemValue="descricao" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Sit.Tributária A\B</label>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <form:select path="id_STA" multiple="false" id="listaSTA"
                                     cssClass="form-control" required="true"
                                     items="${lista_STA}"
                                     itemLabel="codigoSTA"
                                     itemValue="id_STA" style="width:60px;"/>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:select path="id_STB" multiple="false" id="listaSTB"
                                     cssClass="form-control" required="true"
                                     items="${lista_STB}"
                                     itemLabel="codigoSTB"
                                     itemValue="id_STB" style="width:60px;"/>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">NCM</label>
                    </div>
                    <div class="span3" style="text-align: right;">
                        <form:select path="id_NCM" multiple="false" id="listaNCM"
                                     cssClass="form-control" required="true"
                                     items="${lista_NCM}"
                                     itemLabel="codigoNCM"
                                     itemValue="id_NCM" style="width:200px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Unidade Padrão</label>
                    </div>
                    <div class="span3" style="text-align: right;">
                        <form:select path="id_UnidadePadrao" multiple="false" id="lista_Unidade"
                                     cssClass="form-control" required="true"
                                     items="${lista_Unidade}"
                                     itemLabel="descricao"
                                     itemValue="id_Unidade" style="width:200px;"/>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Peso Bruto/Peso Líquido</label>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <form:input path="pesoBruto" type="text" maxlength="15"
                                    class="form-control"  name="PesoBruto" placeholder="Peso Bruto"
                                    style="width:85px;"/>
                    </div>
                    <div class="span2" style="text-align: right;">
                        <form:input path="pesoLiquido" type="text" maxlength="15"
                                    class="form-control"  name="pesoLiquido" placeholder="Peso Líquido"
                                    style="width:85px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Anvisa</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:input id="Anvisa" path="numeroAnvisa" type="text"
                                    maxlength="15" class="form-control"  name="Anvisa"
                                    placeholder="Anvisa" style="width:188px;"/>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Validade Anvisa</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:input id="ValidadeAnvisa" path="validadeAnvisa" type="text"
                                    maxlength="15" class="form-control"  name="ValidadeAnvisa"
                                    placeholder="Validade Anvisa" style="width:188px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Controla Lote</label>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <form:checkbox path="controlaLote"/>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Ativo Compra</label>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <form:checkbox path="ativoCompra" checked="true"/>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Ativo Venda</label>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <form:checkbox path="ativoVenda" checked="true"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/produto_lista"/>">Cancelar</a>
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
    //$("#listaGrupo").trigger("change");

    jQuery(function($){
        $( document ).ready(function() {
            var id_grupo = $("#listaGrupo").val();
            var id_subgrupo = $("#id_SubGrupoProduto_imput").val();

            if( id_subgrupo.length <= 0 ) return;
            $.getJSON("${pageContext.request.contextPath}/json_listasubgrupoproduto/"+id_grupo,
                    function(result){
                        $('#listaSubGrupo').empty();
                        $.each(result, function(i){
                            if (result[i].id_SubGrupoProduto == id_subgrupo)
                            {
                                $("#listaSubGrupo").append('<option value="' + result[i].id_SubGrupoProduto + '" selected="selected">' + result[i].descricao + '</option>');
                            }
                            else
                            {
                                $("#listaSubGrupo").append('<option value="' + result[i].id_SubGrupoProduto + '">' + result[i].descricao + '</option>');
                            }
                        });
                    });
        });

        $("#listaGrupo").change(function(){
            var id_grupo = $(this).val();
            if( id_grupo.length <= 0 ) return;
            $.getJSON("${pageContext.request.contextPath}/json_listasubgrupoproduto/"+id_grupo,
                    function(result){
                        $('#listaSubGrupo').empty();
                        $.each(result, function(i){
                            $("#listaSubGrupo").append('<option value="' + result[i].id_SubGrupoProduto + '">' + result[i].descricao + '</option>');
                        });
                    });
        });
    });

    $("#listaSubGrupo").change(function(){
        $("#id_SubGrupoProduto_imput").val($("#listaSubGrupo").val());
    });

    jQuery(function($){
        $("#Cnpj").mask("99.999.999/9999-99",{placeholder:" "});
        $("#Telefone1").mask("(999) 9999-9999",{placeholder:" "});
        $("#Telefone2").mask("(999) 9999-9999",{placeholder:" "});
        $("#Fax").mask("(999) 9999-9999",{placeholder:" "});
        $("#Cep").mask("99999-999",{placeholder:" "});
        $("#ValidadeAnvisa").mask("99/99/9999",{placeholder:" "});
    });
</script>


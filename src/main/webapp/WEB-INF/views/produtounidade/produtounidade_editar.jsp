<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/produtounidade_update" var="gravar"/>
        <form:form class="form-signin" modelAttribute="produtounidade" id="FormCadastro" action="${gravar}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 35%;">Editar Unidade do Produto</a>
                    </div>
                </div>
            </div>

            <div class="navbar">
                <div class="navbar-inner">
                    <br>
                    <div class="row-fluid">
                        <div class="span1" style="text-align: left;">
                            <label style="font-size: 16px; margin-top:6px;">Produto</label>
                        </div>
                        <div class="span1" style="text-align: left;">
                            <form:input path="id_Produto" id="id_Produto" type="text" value="${produto.id_Produto}"
                                        readonly="true" maxlength="50" class="form-control"
                                        name="ID" placeholder="ID" style="width:50px;"/>
                        </div>
                        <div class="span2" style="text-align: left;">
                            <input id="Referencia" type="text" value="${produto.referencia}"
                                   readonly="true" maxlength="50" class="form-control"
                                   name="Referencia" placeholder="Referencia" style="width:125px;"/>
                        </div>
                        <div class="span8" style="text-align: left;">
                            <input type="text" maxlength="150" readonly="true" value="${produto.descricao}"
                                   class="form-control"  name="Descricao" placeholder="Descrição"
                                   style="width:550px;"/>
                        </div>
                    </div>

                    <div class="row-fluid">
                        <div class="span1" style="text-align: left;">
                            <label style="font-size: 16px; margin-top:6px;">Unidade</label>
                        </div>
                        <div class="span1" style="text-align: left;">
                            <form:input path="id_ProdutoUnidade" id="id_ProdutoUnidade" type="hidden" maxlength="50" class="form-control" name="id_ProdutoUnidade" style="width:80px;" />
                            <form:input path="unidadePrincipal" id="unidadePrincipal" type="hidden" maxlength="50" class="form-control" name="unidadePrincipal" style="width:80px;" />
                            <form:input path="id_Unidade" id="id_Unidade" type="text" value="${unidade.id_Unidade}"
                                        readonly="true" maxlength="50" class="form-control"
                                        name="ID" placeholder="ID" style="width:50px;"/>
                        </div>
                        <div class="span2" style="text-align: left;">
                            <input id="Sigla" type="text" value="${unidade.sigla}"
                                   readonly="true" maxlength="50" class="form-control"
                                   name="Sigla" placeholder="Sigla" style="width:125px;"/>
                        </div>
                        <div class="span8" style="text-align: left;">
                            <input type="text" maxlength="150" readonly="true" value="${unidade.descricao}"
                                   class="form-control"  name="DescricaoUnidade" placeholder="Descrição Unidade"
                                   style="width:550px;"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Cód.Barras</label>
                    </div>
                    <div class="span4" style="text-align: left;">
                        <form:input path="codigoBarras" type="text" maxlength="15" required="true"
                                    class="form-control"  name="CodigoBarras" placeholder="Codigo de Barras"
                                    style="width:150px;"/>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Fator Conversão</label>
                    </div>
                    <div class="span4" style="text-align: right;">
                        <form:input path="fatorConversao" type="text" maxlength="15"
                                    class="form-control"  name="FatorConversao" placeholder="FatorConversao"
                                    style="width:150px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Peso Bruto</label>
                    </div>
                    <div class="span4" style="text-align: left;">
                        <form:input path="pesoBruto" type="text" maxlength="15" required="true"
                                    class="form-control"  name="pesoBruto" placeholder="Peso Bruto"
                                    style="width:150px;"/>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Peso Líquido</label>
                    </div>
                    <div class="span4" style="text-align: right;">
                        <form:input path="pesoLiquido" type="text" maxlength="15"
                                    class="form-control"  name="pesoLiquido" placeholder="Peso Líquido"
                                    style="width:150px;"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/produtounidade_lista/${produto.id_Produto}"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>


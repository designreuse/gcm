<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/produtolote_insert" var="gravar"/>
        <form:form class="form-signin" modelAttribute="produtolote" id="FormCadastro" action="${gravar}" method="POST">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 35%;">Novo Lote do Produto</a>
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
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="row-fluid">
                        <div class="span3" style="text-align: left;">
                            <label style="font-size: 16px; margin-top:10px;">Lote</label>
                        </div>
                        <div class="span9" style="text-align: left;">
                            <form:input path="numeroLote" type="text" maxlength="10" required="true"
                                        class="form-control"  name="numeroLote" placeholder="Lote"
                                        style="width:150px;"/>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span3" style="text-align: left;">
                            <label style="font-size: 16px; margin-top:10px;">Data de Fabricação</label>
                        </div>
                        <div class="span9" style="text-align: left;">
                            <form:input path="dataFabricacao" type="text" maxlength="10" required="true"
                                        class="form-control datepicker"  name="dataFabricacao" placeholder="Data Fabricacao"
                                        style="width:150px;"/>
                        </div>
                    </div>
                    <div class="row-fluid">
                        <div class="span3" style="text-align: left;">
                            <label style="font-size: 16px; margin-top:10px;">Data de Validade</label>
                        </div>
                        <div class="span9" style="text-align: left;">
                            <form:input path="dataValidade" type="text" maxlength="15" required="true"
                                        class="form-control datepicker"  name="dataValidade" placeholder="Data Validade"
                                        style="width:150px;"/>
                        </div>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/produtolote_lista/${produto.id_Produto}"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

<script>
    $(document).ready(function () {
        $('.datepicker').datepicker({
            format: "dd/mm/yyyy",
            language: "pt-BR"
        });
    });
</script>
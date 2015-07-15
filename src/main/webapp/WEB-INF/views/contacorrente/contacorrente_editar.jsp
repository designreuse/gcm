<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/contacorrente_update" var="gravar"/>
        <form:form class="form-signin" modelAttribute="contacorrente" id="FormCadastro" action="${gravar}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Editar Conta Corrrente</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Empresa</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="id_ContaCorrente" class="form-control" type="hidden"/>
                        <form:select path="id_Empresa" multiple="false" id="Empresa"
                                     cssClass="form-control" required="autofocus"
                                     items="${listaempresa}"
                                     itemLabel="RazaoSocial"
                                     itemValue="id_Empresa" style="width:650px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Banco</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:select path="id_Banco" multiple="false" id="Banco"
                                     cssClass="form-control" required="true"
                                     items="${listabanco}"
                                     itemLabel="Descricao"
                                     itemValue="id_Banco" style="width:650px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Agência</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:input path="numeroAgencia" type="text"  maxlength="10" required="true"
                                    class="form-control maiusculo" name="numeroAgencia" placeholder="Agência"
                                    style="width:100px;" />
                    </div>
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Conta</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:input path="numeroContaCorrente" type="text"  maxlength="20" required="true"
                                    class="form-control maiusculo" name="numeroContaCorrente" placeholder="Conta"
                                    style="width:150px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Limite Crédito</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:input path="limiteCredito" type="number"  maxlength="20" required="false"
                                    class="form-control" name="LimiteCredito" placeholder="Limite Crédito"
                                    style="width:150px;" min="0.00" step="1.00"/>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Saldo</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:input path="saldoConta" type="number"  maxlength="20" required="false" readonly="true"
                                    class="form-control" name="SaldoConta" placeholder="Saldo"
                                    style="width:150px;"  min="0.00" step="1.00"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/contacorrente_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>



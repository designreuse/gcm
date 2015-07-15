<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/ncm_insert" var="gravar"/>
        <form:form class="form-signin" modelAttribute="ncm" id="FormCadastro" action="${gravar}" method="POST">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Novo NCM</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Código</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="codigoNCM" type="text"  maxlength="10" required="autofocus"
                                    class="form-control" name="Codigo" placeholder="Código"
                                    style="width:100px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Descrição</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="descricao" type="text"  maxlength="50" required="true"
                                    class="form-control" name="Descricao" placeholder="Descrição"
                                    style="width:630px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Aliquota IPI</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="aliquotaIPI" type="text"  maxlength="15" required="true"
                                    class="form-control" name="IPI" placeholder="IPI"
                                    style="width:100px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Aliquota II</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="aliquotaII" type="text"  maxlength="15" required="true"
                                    class="form-control" name="II" placeholder="II"
                                    style="width:100px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Situação Tributária PIS</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:select path="id_CSTPIS" multiple="false" id="lista_cstpis"
                                     cssClass="form-control" required="true"
                                     items="${lista_cst}"
                                     itemLabel="descricao"
                                     itemValue="id_CSTPISCOFINS" style="width:640px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Situação Tributária COFINS</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:select path="id_CSTCOFINS" multiple="false" id="lista_cstcofins"
                                     cssClass="form-control" required="true"
                                     items="${lista_cst}"
                                     itemLabel="descricao"
                                     itemValue="id_CSTPISCOFINS" style="width:640px;"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/ncm_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>


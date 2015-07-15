<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/planocontas_insert" var="gravar"/>
        <form:form class="form-signin" modelAttribute="planocontas" id="FormCadastro" action="${gravar}" method="POST">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Novo Plano de Contas</a>
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
                        <form:select path="id_Empresa" multiple="false" id="Empresa"
                                     cssClass="form-control" required="autofocus"
                                     items="${listaempresa}"
                                     itemLabel="RazaoSocial"
                                     itemValue="id_Empresa" style="width:640px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Código Conta</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="codigoConta" type="text"  maxlength="20" required="true"
                                    class="form-control maiusculo" name="CodigoConta" placeholder="Código"
                                    style="width:200px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Descrição</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="descricao" type="text"  maxlength="50" required="true"
                                    class="form-control" name="Descricao" placeholder="Descrição"
                                    style="width:625px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Tipo</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:select path="tipoConta" multiple="false" id="Tipo" style="width:200px;">
                            <option value="C">Crédito</option>
                            <option value="D">Débito</option>
                        </form:select>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Agrupamento</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:select path="agrupamento" multiple="false" id="Agrupamento" style="width:200px;">
                            <option value="A">Analítico</option>
                            <option value="S">Sintético</option>
                        </form:select>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/planocontas_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>



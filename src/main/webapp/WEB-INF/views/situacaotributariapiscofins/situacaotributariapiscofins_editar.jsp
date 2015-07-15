<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/situacaotributariapiscofins_update" var="update"/>
        <form:form class="form-signin" modelAttribute="stPISCOFINS" id="FormCadastro" action="${update}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 30%;">Editar Situação Tributária PIS\COFINS</a>
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
                        <form:input path="id_CSTPISCOFINS" type="text" maxlength="3"
                                    class="form-control maiusculo" name="Id" placeholder="Id"
                                    style="width:80px;" readonly="true" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Código</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="codigoSTPISCOFINS" type="text"  maxlength="10" required="true"
                                    class="form-control" name="Codigo" placeholder="Código"
                                    style="width:80px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Descrição</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="descricao" type="text"  maxlength="50" required="autofocus"
                                    class="form-control" name="Descricao" placeholder="Descrição"
                                    style="width:500px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Isento ou Aliq. Zero</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:checkbox path="isentoAliquotaZero" id="isentoAliquotaZero"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/situacaotributariapiscofins_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>


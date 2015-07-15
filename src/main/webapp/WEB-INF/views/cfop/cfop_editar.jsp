<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/cfop_update" var="gravar"/>
        <form:form class="form-signin" modelAttribute="cfop" id="FormCadastro" action="${gravar}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Editar CFOP</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Código</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:input path="id_CFOP" class="form-control" type="hidden"/>
                        <form:input path="codigoCFOP" type="text"  maxlength="4" required="autofocus"
                                    class="form-control" name="codigoCFOP" placeholder="Código"
                                    style="width:100px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Descrição</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:input path="descricao" type="text"  maxlength="50" required="autofocus"
                                    class="form-control" name="Descricao" placeholder="Descrição"
                                    style="width:625px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Tipo</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:select path="tipo" multiple="false" id="Tipo" style="width:115px;">
                            <c:choose>
                                <c:when test="${cfop.tipo == 'E'}">
                                    <option value="E" selected>Entrada</option>
                                    <option value="S">Saída</option>
                                </c:when>
                                <c:when test="${cfop.tipo == 'S'}">
                                    <option value="E">Entrada</option>
                                    <option value="S" selected>Saída</option>
                                </c:when>
                            </c:choose>

                        </form:select>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Exige Retorno</label>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <form:checkbox class="ckb" path="exigeRetorno" id="ExigeRetorno"/>
                    </div>
                    <div class="span2" style="text-align: right;">
                        <label style="font-size: 16px; margin-top:5px;">Dias Retorno</label>
                    </div>
                    <div class="span2" style="text-align: right;">
                        <form:input path="diasRetorno" type="text"  maxlength="3"
                                    class="form-control" name="DiasRetorno" placeholder="Dias Retorno"
                                    style="width:100px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Ajuste</label>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <form:checkbox class="ckb" path="ajuste" id="ajuste"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/cfop_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>



<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/lancamentoestoque_insert/${TipoEntSai}" var="gravar"/>
        <form:form class="form-signin" modelAttribute="lancamentoestoque" id="FormCadastro" action="${gravar}" method="POST">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <c:choose>
                            <c:when test="${TipoEntSai == 'E'}">
                                <a class="brand" style="font: bold; margin-left: 35%">Editar Entrada por Ajuste</a>
                            </c:when>
                            <c:when test="${TipoEntSai == 'S'}">
                                <a class="brand" style="font: bold; margin-left: 35%;">Editar Saída por Ajuste</a>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Empresa</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:select path="id_Empresa" multiple="false" id="Empresa"
                                     cssClass="form-control" required="autofocus"
                                     items="${listaempresa}"
                                     itemLabel="RazaoSocial"
                                     itemValue="id_Empresa" style="width:710px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">CFOP</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:select path="id_CFOP" multiple="false" id="CFOP"
                                     cssClass="form-control" required="true"
                                     items="${listacfop}"
                                     itemLabel="Descricao"
                                     itemValue="id_CFOP" style="width:710px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Observações</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:textarea id="observacoes" path="observacoes" type="text"
                                       class="form-control" rows="3" cols="70"
                                       name="observacoes" placeholder="Observações" style="width:697px; height:30px"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Status</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:select path="atualizado" multiple="false" id="Atualizado" style="width:130px;" disabled="true">
                            <option value="false" selected>Desatualizado</option>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/lancamentoestoque_lista/${TipoEntSai}"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>



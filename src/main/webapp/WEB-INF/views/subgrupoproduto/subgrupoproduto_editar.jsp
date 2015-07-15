<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link href="resources/css/signin.css" rel="stylesheet" />

<div class="container">
    <div class="container-fluid">
        <c:url value="/subgrupoproduto_update" var="update"/>
        <form:form class="form-signin" modelAttribute="subgrupoproduto" id="FormCadastro" action="${update}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Editar SubGrupo de Produtos</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Id</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="id_SubGrupoProduto"     type="text"          readonly="true"
                                    maxlength="5"    class="form-control" name="id"
                                    placeholder="ID" style="width:80px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Grupo</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:select path="id_GrupoProduto"         multiple="false"       cssClass="form-control"
                                     items="${listagrupoproduto}"  itemLabel="descricao" itemValue="id_GrupoProduto"
                                     style="width:400px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Descrição</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="descricao"       type="text"  maxlength="50"
                                    class="form-control"  name="Descricao" placeholder="Descrição"
                                    style="width:400px;" />
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/subgrupoproduto_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

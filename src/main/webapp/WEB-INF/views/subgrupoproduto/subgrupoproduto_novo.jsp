<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/subgrupoproduto_insert" var="insert"/>
        <form:form class="form-signin" modelAttribute="subgrupoproduto" id="FormCadastro" action="${insert}" method="POST">
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 34%;">Novo SubGrupo de Produtos</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Grupo</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:select path="id_GrupoProduto"         multiple="false"       cssClass="form-control"
                                     items="${listagrupoproduto}"  itemLabel="descricao" itemValue="id_GrupoProduto"
                                     style="width:414px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Descrição</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="descricao" type="text" maxlength="50"
                                    class="form-control"  name="descricao" placeholder="Descrição"
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


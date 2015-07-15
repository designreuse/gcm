<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/usuario_update" var="update"/>
        <form:form class="form-signin" modelAttribute="usuario" id="FormCadastro" action="${update}" method="PUT" autocomplete="off">
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Editar Usuário</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Id</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="id_usuario" type="text" maxlength="50" readonly="true"
                                    class="form-control maiusculo"  name="Id" placeholder="Id"
                                    style="width:80px;" required="true"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Nome</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="nome" type="text" maxlength="50"
                                    class="form-control maiusculo"  name="Nome" placeholder="Nome"
                                    style="width:625px;" required="true"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">E-mail</label>
                    </div>
                    <div class="span9" style="text-align: left;">
                        <form:input path="email" type="text" maxlength="50"
                                    class="form-control minusculo"  name="eMail" placeholder="E-Mail"
                                    style="width:625px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Login</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:input path="login" type="text" maxlength="25" readonly="true"
                                    class="form-control maiusculo"  placeholder="Login"
                                    style="width:200px;" required="true"/>
                    </div>
                    <div class="span3" style="text-align: right;">
                        <label style="font-size: 16px; margin-top:5px;">Senha</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:password path="senha" class="span3" style="width:200px;" required="true" readonly="true"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span3" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Ativo</label>
                    </div>
                    <div class="span3" style="text-align: left;">
                        <form:checkbox path="ativo"/>
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/usuario_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
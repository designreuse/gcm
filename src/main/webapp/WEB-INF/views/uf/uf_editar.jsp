<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<link href="resources/css/signin.css" rel="stylesheet" />

<script language="javascript">
    function somente_numero(campo){
        var digits="0123456789"
        var campo_temp
        for (var i=0;i<campo.value.length;i++){
            campo_temp=campo.value.substring(i,i+1)
            if (digits.indexOf(campo_temp)==-1){
                campo.value = campo.value.substring(0,i);
            }
        }
    }

    function Uppercase(campo) {
        campo.value = campo.value.toUpperCase();
    }

    function LowerCase(campo) {
        campo.value = campo.value.toLowerCase();
    }
</script>

<div class="container">
    <div class="container-fluid">
        <c:url value="/alterar_uf" var="gravarUf"/>
        <form:form class="form-signin" modelAttribute="uf" id="FormCadastro" action="${gravarUf}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Editar UF</a>
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
                        <form:input path="id_Uf"     type="text"          readonly="true"
                                    maxlength="5"    class="form-control" name="id"
                                    placeholder="Id" style="width:80px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">País</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:select path="id_Pais"         multiple="false"       cssClass="form-control"
                                     items="${lista_pais}"  itemLabel="siglapais" itemValue="id_pais"
                                     style="width:95px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Sigla</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="siglaUf"       type="text"  maxlength="2"
                                    class="form-control"  name="sigla" placeholder="Sigla"
                                    style="width:80px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Nome</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="descricao" type="text" maxlength="50"
                                    class="form-control"  name="nome" placeholder="Nome"
                                    style="width:400px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Ibge</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="ibge" type="text" maxlength="5"
                                    class="form-control"  name="Ibge" placeholder="Ibge"
                                    style="width:80px;" onKeyUp="javascript:somente_numero(this);" />
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/uf_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>

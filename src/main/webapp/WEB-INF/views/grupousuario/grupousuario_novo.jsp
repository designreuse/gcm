<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
        <c:url value="/grupousuario_insert" var="inserir"/>
        <form:form class="form-signin" modelAttribute="grupousuario" id="FormCadastro" action="${inserir}" method="POST">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <a class="brand" style="font: bold;  margin-left: 38%;">Novo Grupo de Usuários</a>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;"></div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Descrição</label>
                    </div>
                    <div class="span8" style="text-align: left;">
                        <form:input path="descricao" type="text"  maxlength="50" required="autofocus"
                                    class="form-control maiusculo" name="Nome" placeholder="Descrição"
                                    style="width:500px;" />
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
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/grupousuario_lista"/>">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>


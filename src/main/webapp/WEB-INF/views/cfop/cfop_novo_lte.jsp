<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Novo CFOP</h1>
</div>

<div class="content body">
    <form action="/cfop_insert" method="post">
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="codigoCFOP" style="margin-top: 5px;">Código</label>
                                        <div class="col-md-2">
                                            <input type="text" maxlength="10" style="width:100%;" required="autofocus"
                                                   class="form-control" placeholder="Código" name="codigoCFOP" id="codigoCFOP"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-8">
                                            <input type="text" maxlength="50" style="width:100%;" required="true"
                                                   class="form-control" placeholder="Nome" name="descricao" id="descricao"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="tipo" style="margin-top: 5px;">Tipo</label>
                                        <div class="col-md-2">
                                            <select class="form-control" name="tipo" id="tipo" required="true">
                                                <option value=""></option>
                                                <option value="E">Entrada</option>
                                                <option value="S">Saída</option>
                                            </select>
                                        </div>

                                        <label class="col-md-2 control-label" for="ckbexigeRetorno" style="margin-top: 5px;">Exige Retorno</label>
                                        <div class="col-md-1">
                                            <input type="checkbox" name="ckbexigeRetorno" id="ckbexigeRetorno" style="margin-top: 10px;"/>
                                            <input type="hidden" value="False" name="exigeRetorno" id="exigeRetorno"/>
                                        </div>

                                        <label class="col-md-2 control-label" for="ckbexigeRetorno" style="margin-top: 5px;">Dias Retorno</label>
                                        <div class="col-md-1">
                                            <input type="number" maxlength="3" style="width:100%;" required="true" value="0" min="0" max="999"
                                                   class="form-control" placeholder="Código" name="diasRetorno" id="diasRetorno"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="ckbajuste" style="margin-top: 5px;">Ajuste</label>
                                        <div class="col-md-1">
                                            <input type="checkbox" name="ckbajuste" id="ckbajuste" style="margin-top: 10px;"/>
                                            <input type="hidden" value="False" name="ajuste" id="ajuste"/>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <div class="col-sm-12" style="margin-top: 15px; text-align: center;">
                                <button class="btn btn-primary" type="submit" style="width: 80px;">Salvar</button>
                                <a style="width: 80px" href="<c:url value="/cfop_lista"/>" class="btn btn-primary" title="Cancelar">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<script>
    $("#ckbexigeRetorno").change(function(){
        if($(this).is(':checked')){
            $("#exigeRetorno").val('true');
        }else{
            $("#exigeRetorno").val('false');
        }
    });

    $("#ckbajuste").change(function(){
        if($(this).is(':checked')){
            $("#ajuste").val('true');
        }else{
            $("#ajuste").val('false');
        }
    });
</script>
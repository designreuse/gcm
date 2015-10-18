<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Detalhes do CFOP</h1>
</div>

<div class="content body">
    <form action="#">
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
                                            <input type="hidden" name="id_CFOP" id="id_CFOP" value="${cfop.id_CFOP}"/>

                                            <input type="text" maxlength="10" style="width:100%;" readonly="true" value="${cfop.codigoCFOP}"
                                                   class="form-control" placeholder="Código" name="codigoCFOP" id="codigoCFOP"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-8">
                                            <input type="text" maxlength="50" style="width:100%;" readonly="true" value="${cfop.descricao}"
                                                   class="form-control" placeholder="Nome" name="descricao" id="descricao"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="tipo" style="margin-top: 5px;">Tipo</label>
                                        <div class="col-md-2">
                                            <select class="form-control" name="tipo" id="tipo" required="true" disabled>
                                                <option value=""></option>
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
                                            </select>
                                        </div>

                                        <label class="col-md-2 control-label" for="ckbexigeRetorno" style="margin-top: 5px;">Exige Retorno</label>
                                        <div class="col-md-1">
                                            <c:choose>
                                                <c:when test="${cfop.exigeRetorno == 'true'}">
                                                    <input type="checkbox" name="ckbexigeRetorno" id="ckbexigeRetorno" style="margin-top: 10px;" checked disabled/>
                                                </c:when>
                                                <c:when test="${cfop.exigeRetorno == 'false'}">
                                                    <input type="checkbox" name="ckbexigeRetorno" id="ckbexigeRetorno" style="margin-top: 10px;" disabled/>
                                                </c:when>
                                            </c:choose>
                                            <input type="hidden" name="exigeRetorno" id="exigeRetorno" value="${cfop.exigeRetorno}"/>
                                        </div>

                                        <label class="col-md-2 control-label" for="ckbexigeRetorno" style="margin-top: 5px;">Dias Retorno</label>
                                        <div class="col-md-1">
                                            <input type="number" maxlength="3" style="width:100%;" readonly="true" min="0" max="999"
                                                   class="form-control" placeholder="Código" name="diasRetorno" id="diasRetorno" value="${cfop.diasRetorno}"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="ckbajuste" style="margin-top: 5px;">Ajuste</label>
                                        <div class="col-md-1">
                                            <c:choose>
                                                <c:when test="${cfop.ajuste == 'true'}">
                                                    <input type="checkbox" name="ckbajuste" id="ckbajuste" style="margin-top: 10px;" checked disabled/>
                                                </c:when>
                                                <c:when test="${cfop.ajuste == 'false'}">
                                                    <input type="checkbox" name="ckbajuste" id="ckbajuste" style="margin-top: 10px;" disabled/>
                                                </c:when>
                                            </c:choose>
                                            <input type="hidden" name="ajuste" id="ajuste" value="${cfop.ajuste}"/>
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
                                <a style="width: 80px" href="<c:url value="/cfop_lista"/>" class="btn btn-primary" title="Voltar">Voltar</a>
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
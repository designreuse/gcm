<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Detalhes da Situação Tributária PIS\COFINS</h1>
</div>

<div class="content body">
    <form action="#" method="post">
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="codigoSTPISCOFINS" style="margin-top: 5px;">Código</label>
                                        <div class="col-md-2">
                                            <input type="hidden" name="id_CSTPISCOFINS" id="id_CSTPISCOFINS" value="${stpiscofins.id_CSTPISCOFINS}"/>

                                            <input type="text" maxlength="10" style="width:100%;" readonly="true" value="${stpiscofins.codigoSTPISCOFINS}"
                                                   class="form-control" placeholder="Código" name="codigoSTPISCOFINS" id="codigoSTPISCOFINS"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-6">
                                            <input type="text" maxlength="50" style="width:100%;" readonly="true" value="${stpiscofins.descricao}"
                                                   class="form-control" placeholder="Descrição" name="descricao" id="descricao"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="ckbisento" style="margin-top: 5px;">Isento ou Aliq. Zero</label>
                                        <div class="col-md-6">
                                            <input type="hidden" name="isentoAliquotaZero" id="isentoAliquotaZero" value="${stpiscofins.isentoAliquotaZero}"/>
                                            <c:choose>
                                                <c:when test="${stpiscofins.isentoAliquotaZero == 'true'}">
                                                    <input type="checkbox" name="ckbisento" id="ckbisento" style="margin-top: 10px;" checked disabled/>
                                                </c:when>
                                                <c:when test="${stpiscofins.isentoAliquotaZero == 'false'}">
                                                    <input type="checkbox" name="ckbisento" id="ckbisento" style="margin-top: 10px;" disabled/>
                                                </c:when>
                                            </c:choose>
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
                                <a style="width: 80px" href="<c:url value="/stpiscofins_lista"/>" class="btn btn-primary" title="Voltar">Voltar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>


<script>
    $("#ckbisento").change(function(){
        if($(this).is(':checked')){
            $("#isentoAliquotaZero").val('true');
        }else{
            $("#isentoAliquotaZero").val('false');
        }
    });
</script>
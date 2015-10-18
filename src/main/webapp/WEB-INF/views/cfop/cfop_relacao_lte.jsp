<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Relação do CFOP</h1>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <form class="form-horizontal" action="#">
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-1 control-label" for="codigoCFOP">Código</label>
                                <div class="col-md-2">
                                    <input type="text" value="${cfop.codigoCFOP}" readonly="true"
                                           class="form-control" name="codigoCFOP" id="codigoCFOP"/>
                                </div>

                                <label class="col-md-1 control-label" for="descricao">Descrição</label>
                                <div class="col-md-6">
                                    <input type="text" value="${cfop.descricao}"  readonly="true"
                                           class="form-control" name="descricao" id="descricao"/>
                                </div>
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="height: 320px; overflow:auto;">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <table class="table table-bordered  table-hover table-striped">
                        <thead style="background-color:silver">
                        <tr>
                            <th style="width:80px;">Código</th>
                            <th>Descrição</th>
                            <th style="width:60px;">Relação</th>
                        </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lista}" var="lista">
                                <tr>
                                    <td>${lista.relacaoCFOP.codigoCFOP}</td>
                                    <td>${lista.relacaoCFOP.descricao}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${lista.pertence == true}">
                                                <input type="checkbox" class="ckb" id="${cfop.id_CFOP}" relacao="${lista.id_CFOPRelacao}" checked="true"/>
                                            </c:when>
                                            <c:when test="${lista.pertence != true}">
                                                <input type="checkbox" class="ckb" id="${cfop.id_CFOP}" relacao="${lista.id_CFOPRelacao}" />
                                            </c:when>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
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
</div>



<script>
    $(".ckb").change(function(){
        var  id_cfop = $(this).attr('id');
        var  id_cfoprelacao = $(this).attr('relacao');
        var  pertence = $(this).prop('checked');

        $.getJSON("${pageContext.request.contextPath}/relacaocfop_insert/"+id_cfop+"/"+id_cfoprelacao+"/"+pertence);
    });
</script>
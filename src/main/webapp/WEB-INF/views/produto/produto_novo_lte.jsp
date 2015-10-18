<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Novo Produto</h1>
</div>

<div class="content body">
    <form action="/produto_insert" method="post">
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="referencia" style="margin-top: 5px;">Referência</label>
                                        <div class="col-md-2">
                                            <input type="text" maxlength="10" style="width:100%;" required="autofocus"
                                                   class="form-control" placeholder="Referência" name="referencia" id="referencia"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-8">
                                            <input type="text" maxlength="50" style="width:100%;" required="true"
                                                   class="form-control" placeholder="Descrição" name="descricao" id="descricao"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="id_MarcaProduto" style="margin-top: 5px;">Marca</label>
                                        <div class="col-md-8">
                                            <select class="form-control" name="id_MarcaProduto" id="id_MarcaProduto" required="true">
                                                <option value=""></option>
                                                <c:forEach items="${lista_Marca}" var="p">
                                                    <option value="${p.id_MarcaProduto}">${p.descricao}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="id_GrupoProduto" style="margin-top: 5px;">Grupo</label>
                                        <div class="col-md-3">
                                            <select class="form-control" name="id_GrupoProduto" id="id_GrupoProduto" required="true">
                                                <option value=""></option>
                                                <c:forEach items="${lista_GrupoProduto}" var="p">
                                                    <option value="${p.id_GrupoProduto}">${p.descricao}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-md-1 control-label" for="id_SubGrupoProduto" style="margin-top: 5px;">SubGrupo</label>
                                        <div class="col-md-4">
                                            <select class="form-control" name="id_SubGrupoProduto" id="id_SubGrupoProduto" required="true">
                                                <option value=""></option>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="id_STA" style="margin-top: 5px;">Sit.Tributária A\B</label>
                                        <div class="col-md-1">
                                            <select class="form-control" name="id_STA" id="id_STA" required="true">
                                                <option value=""></option>
                                                <c:forEach items="${lista_STA}" var="p">
                                                    <option value="${p.id_STA}">${p.codigoSTA}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-2">
                                            <select class="form-control" name="id_STB" id="id_STB" required="true">
                                                <option value=""></option>
                                                <c:forEach items="${lista_STB}" var="p">
                                                    <option value="${p.id_STB}">${p.codigoSTB}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-md-1 control-label" for="id_NCM" style="margin-top: 5px;">NCM</label>
                                        <div class="col-md-2">
                                            <select class="form-control" name="id_NCM" id="id_NCM" required="true">
                                                <option value=""></option>
                                                <c:forEach items="${lista_NCM}" var="p">
                                                    <option value="${p.id_NCM}">${p.codigoNCM}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="id_UnidadePadrao" style="margin-top: 5px;">Unidade Padrão</label>
                                        <div class="col-md-3">
                                            <select class="form-control" name="id_UnidadePadrao" id="id_UnidadePadrao" required="true">
                                                <option value=""></option>
                                                <c:forEach items="${lista_Unidade}" var="p">
                                                    <option value="${p.id_Unidade}">${p.descricao}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <label class="col-md-1 control-label" for="pesoBruto" style="margin-top: 5px;">P.Bruto\P.Liq</label>
                                        <div class="col-md-2">
                                            <input type="number" style="width:100%;" required="true" step="0.01"
                                                   class="form-control" placeholder="P.Bruto" name="pesoBruto" id="pesoBruto"/>
                                        </div>
                                        <div class="col-md-2">
                                            <input type="number" style="width:100%;" required="true" step="0.01"
                                                   class="form-control" placeholder="P.Bruto" name="pesoLiquido" id="pesoLiquido"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-2 control-label" for="numeroAnvisa" style="margin-top: 5px;">Anvisa</label>
                                        <div class="col-md-3">
                                            <input type="text" style="width:100%;"
                                                   class="form-control" placeholder="Anvisa" name="numeroAnvisa" id="numeroAnvisa"/>
                                        </div>
                                        <label class="col-md-1 control-label" for="validadeAnvisa" style="margin-top: 5px;">Validade</label>
                                        <div class="col-md-2">
                                            <input type="date"
                                                   class="form-control" placeholder="Validade" name="validadeAnvisa" id="validadeAnvisa"/>
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
                                <a style="width: 80px" href="<c:url value="/produto_lista"/>" class="btn btn-primary" title="Cancelar">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>

<script>
    jQuery(function($){
        $( document ).ready(function() {
            $('#id_SubGrupoProduto').empty();
            $("#id_GrupoProduto").trigger("change");
        });

        $("#id_GrupoProduto").change(function(){
            var id_grupo = $(this).val();
            $('#id_SubGrupoProduto').empty();
            if( id_grupo.length <= 0 ) return;
            $.getJSON("${pageContext.request.contextPath}/json_listasubgrupoproduto/"+id_grupo,
                    function(result){
                        $.each(result, function(i){
                            $("#id_SubGrupoProduto").append('<option value="' + result[i].id_SubGrupoProduto + '">' + result[i].descricao + '</option>');
                        });
                    });
        });

    });


</script>
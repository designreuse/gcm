<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Detalhes Unidade</h1>
</div>

<div class="content body">

            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="sigla" style="margin-top: 5px;">Sigla</label>
                                        <div class="col-md-2">
                                            <input type="hidden" value="${unidade.id_Unidade}"
                                                   class="form-control maiusculo" name="id_Unidade" id="id_Unidade"/>

                                            <input type="text" maxlength="3" style="width:60px;" readonly="true" value="${unidade.sigla}"
                                                   class="form-control maiusculo" placeholder="Sigla" name="sigla" id="sigla" />
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-6">
                                            <input type="text" maxlength="50" style="width:100%;" readonly="true" value="${unidade.descricao}"
                                                   class="form-control maiusculo" placeholder="Nome" name="descricao" id="descricao"/>
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
                                <a style="width: 80px" href="<c:url value="/unidade_lista"/>" class="btn btn-primary" title="Voltar">Voltar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

</div>
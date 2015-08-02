<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Editar País</h1>
</div>

<div class="content body">
    <form action="/alterar_pais" method="post">
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="siglapais" style="margin-top: 5px;">Sigla</label>
                                        <div class="col-md-2">
                                            <input type="hidden" maxlength="3" style="width:60px;"
                                                   class="form-control maiusculo" name="id_pais" id="id_pais" value="${pais.id_pais}"/>

                                            <input type="text" maxlength="3" style="width:60px;" required="autofocus" value="${pais.siglapais}"
                                                   class="form-control maiusculo" placeholder="Sigla" name="siglapais" id="siglapais"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="descricao" style="margin-top: 5px;">Nome</label>
                                        <div class="col-md-6">
                                            <input type="text" maxlength="50" style="width:100%;" required="true" value="${pais.descricao}"
                                                   class="form-control maiusculo" placeholder="Nome" name="descricao" id="descricao"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="ibge" style="margin-top: 5px;">Ibge</label>
                                        <div class="col-md-2">
                                            <input type="text" maxlength="5" style="width:100%;" value="${pais.ibge}"
                                                   class="form-control maiusculo" placeholder="ibge" name="ibge" id="ibge"/>
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
                                <a style="width: 80px" href="<c:url value="/pais_lista"/>" class="btn btn-primary" title="Cancelar">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>
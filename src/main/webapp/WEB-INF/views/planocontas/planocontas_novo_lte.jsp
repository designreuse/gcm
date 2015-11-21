<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Novo Plano de Contas</h1>
</div>

<div class="content body">
    <form action="/planocontas_insert" method="post" >
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="id_Empresa" style="margin-top: 5px;">Empresa</label>
                                        <div class="col-md-8">
                                            <select class="form-control" name="id_Empresa" id="id_Empresa" required="autofocus">
                                                <c:forEach items="${listaempresa}" var="p">
                                                    <option value="${p.id_Empresa}">${p.razaoSocial}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="codigoConta" style="margin-top: 5px;">Código</label>
                                        <div class="col-md-2">
                                            <input type="text" maxlength="20" required="true"
                                                   class="form-control maiusculo" placeholder="Código" name="codigoConta" id="codigoConta"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-8">
                                            <input type="text" maxlength="50" required="true"
                                                   class="form-control" placeholder="Descrição" name="descricao" id="descricao"/>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="tipoConta" style="margin-top: 5px;">Tipo</label>
                                        <div class="col-md-3">
                                            <select class="form-control" name="tipoConta" id="tipoConta" required="true">
                                                <option value=""></option>
                                                <option value="C">Crédito</option>
                                                <option value="D">Débito</option>
                                            </select>
                                        </div>
                                        <label class="col-md-2 control-label" for="agrupamento" style="margin-top: 5px;">Agrupamento</label>
                                        <div class="col-md-3">
                                            <select class="form-control" name="agrupamento" id="agrupamento" required="true">
                                                <option value=""></option>
                                                <option value="A">Analítico</option>
                                                <option value="S">Sintético</option>
                                            </select>
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
                                <a style="width: 80px" href="<c:url value="/planocontas_lista"/>" class="btn btn-primary" title="Cancelar">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>
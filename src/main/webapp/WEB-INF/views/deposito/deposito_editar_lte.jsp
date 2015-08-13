<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Editar Depósito</h1>
</div>

<div class="content body">
    <form action="/deposito_update" method="post" >
        <fieldset>
            <div class="row">
                <div class="col-sm-12">
                    <div class="box box-primary">
                        <div class="box-body">
                            <form class="form-horizontal">
                                <div class="form-group">
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="id_Empresa" style="margin-top: 5px;">Empresa</label>
                                        <div class="col-md-6">
                                            <input type="hidden"value="${deposito.id_Deposito}"
                                                   class="form-control" name="id_Deposito" id="id_Deposito"/>

                                            <select class="form-control" name="id_Empresa" id="id_Empresa" required="autofocus">
                                                <c:forEach items="${listaempresa}" var="p">
                                                    <c:choose>
                                                        <c:when test="${p.id_Empresa != deposito.id_Empresa}">
                                                            <option value="${p.id_Empresa}">${p.razaoSocial}</option>
                                                        </c:when>
                                                        <c:when test="${p.id_Empresa == deposito.id_Empresa}">
                                                            <option value="${p.id_Empresa}" selected="selected">${p.razaoSocial}</option>
                                                        </c:when>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <br>
                                    <div class="row">
                                        <label class="col-md-1 control-label" for="descricao" style="margin-top: 5px;">Descrição</label>
                                        <div class="col-md-6">
                                            <input type="text" maxlength="50" required="true" value="${deposito.descricao}"
                                                   class="form-control maiusculo" placeholder="Descricao" name="descricao" id="descricao"/>
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
                                <a style="width: 80px" href="<c:url value="/deposito_lista"/>" class="btn btn-primary" title="Cancelar">Cancelar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>
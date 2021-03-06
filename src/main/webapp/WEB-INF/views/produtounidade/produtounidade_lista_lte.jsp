<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <h1>Lista de Unidades do Produto</h1>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <label class="col-md-1 control-label" for="id_Produto" style="margin-top: 8px;">Produto</label>
                    <div class="col-md-1">
                        <input type="text" maxlength="10" value="${produto.id_Produto}" readonly="true"
                               class="form-control" name="id_Produto" id="id_Produto"/>
                    </div>
                    <div class="col-md-2">
                        <input type="text" maxlength="10" style="width:100%;" readonly="true" value="${produto.referencia}"
                               class="form-control" placeholder="Referência" name="referencia" id="referencia"/>
                    </div>
                    <div class="col-md-6">
                        <input type="text" maxlength="50" style="width:100%;" readonly="true" value="${produto.descricao}"
                               class="form-control" placeholder="Descrição" name="descricao" id="descricao"/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row" style="height: 320px; overflow:auto;">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <table class="table table-bordered  table-hover table-striped">
                        <thead style="background-color:silver;">
                        <tr>
                            <th style="width:100px;">Operações</th>
                            <th>Unidade</th>
                            <th style="width:40px;">Fator</th>
                            <th style="width:40px;">Principal</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${lista}" var="p">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${p.unidadePrincipal != true}">
                                            <a href="/produtounidade_deleta/${p.id_ProdutoUnidade}/${p.id_Produto}" class="btn btn-default btn-xs" title="Deletar">
                                                <i class="fa fa-trash-o"></i>
                                            </a>
                                        </c:when>
                                        <c:when test="${p.unidadePrincipal == true}">
                                            <a href="#" class="btn btn-default btn-xs" title="Deletar">
                                                <i class="fa fa-ban"></i>
                                            </a>
                                        </c:when>
                                    </c:choose>
                                    <a href="/produtounidade_editar/${p.id_ProdutoUnidade}" class="btn btn-default btn-xs" title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a href="/produtounidade_detalhes/${p.id_ProdutoUnidade}" class="btn btn-default btn-xs" title="Detalhes">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </td>
                                <td>${p.unidade.descricao}</td>
                                <td>${p.fatorConversao}</td>
                                <td>${p.unidadePrincipal}</td>
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
                    <div class="col-sm-9">
                        <vls:paginador pagina="${pagina}"/>
                    </div>
                    <div class="col-sm-3" style="margin-top: 15px; text-align: right;">
                        <a style="width: 80px" href="<c:url value="/produtounidade_novo/${produto.id_Produto}"/>" class="btn btn-primary" title="Novo">
                            <i class="fa fa-plus"></i> Novo
                        </a>
                        <a style="width: 80px" href="<c:url value="/produto_lista"/>" class="btn btn-primary" title="Voltar">
                            <i class="fa fa-reply"></i> Voltar
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
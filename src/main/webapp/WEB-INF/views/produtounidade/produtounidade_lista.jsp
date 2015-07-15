<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <div class="container-fluid">
                <a class="brand" style="font: bold; margin-left: 280px;">Lista de Unidades do Produto</a>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="navbar">
        <div class="navbar-inner">
            <br>
            <div class="row-fluid">
                <div class="span1" style="text-align: left;">
                    <label style="font-size: 16px; margin-top:6px;">Produto</label>
                </div>
                <div class="span1" style="text-align: left;">
                    <input id="id_Produto" type="text" value="${produto.id_Produto}"
                                readonly="true" maxlength="50" class="form-control"
                                name="ID" placeholder="ID" style="width:60px;"/>
                </div>
                <div class="span2" style="text-align: left;">
                    <input id="Referencia" type="text" value="${produto.referencia}"
                           readonly="true" maxlength="50" class="form-control"
                           name="Referencia" placeholder="Referencia" style="width:135px;"/>
                </div>
                <div class="span8" style="text-align: left;">
                    <input type="text" maxlength="150" readonly="true" value="${produto.descricao}"
                           class="form-control"  name="Descricao" placeholder="Descrição"
                           style="width:580px;"/>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="navbar-inner" style="height: 290px; overflow:auto;">
        <br>
        <table class="table table-striped table-bordered table-hover table-condensed active tabelaPaginada" id="example">
            <thead style="background-color:silver">
            <tr>
                <th style="width:60px;"></th>
                <th>Unidade</th>
                <th style="width:40px;">Fator</th>
                <th style="width:40px;">Principal</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${lista}" var="p">
                <tr>
                    <td>
                        <a href="/produtounidade_deleta/${p.id_ProdutoUnidade}/${p.id_Produto}" class="btn btn-mini apagar" title="Deletar">
                            <i class="icon-trash"></i>
                        </a>
                        <a href="/produtounidade_editar/${p.id_ProdutoUnidade}" class="btn btn-mini" title="Editar">
                            <i class="icon-pencil"></i>
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
<br>
<div class="container">
    <div class="navbar-inner">
        <div class="row-fluid">
            <div class="span6">
                <vls:paginador pagina="${pagina}"/>
            </div>
            <div class="span6" style="text-align: right;" >
                <br>
                <a href="<c:url value="/produtounidade_novo/${produto.id_Produto}"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
                <a href="<c:url value="/produto_lista"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-repeat"></span> Voltar</a>
            </div>
        </div>
    </div>
</div>

<!-- Div apenas ocupar um espaço no fim da página-->
<div class="container">
    <div class="container-fluid">
        <div class="row-fluid">
            <div class="span12">
            </div>
        </div>
    </div>
</div>



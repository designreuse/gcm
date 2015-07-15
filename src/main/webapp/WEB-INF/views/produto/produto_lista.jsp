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
                <a class="brand" style="font: bold; margin-left: 330px;">Lista de Produtos</a>
            </div>
        </div>
    </div>
</div>

<div class="container">
    <div class="navbar-inner" style="overflow:auto; height: 350px;">
        <br>
        <table class="table table-striped table-bordered table-hover table-condensed active tabelaPaginada" id="example">
            <thead style="background-color:silver">
            <tr>
                <th style="width:60px;"></th>
                <th style="width:60px;">ID</th>
                <th style="width:80px;">Referência</th>
                <th>Descrição</th>
                <th style="width:60px;">Compra</th>
                <th style="width:60px;">Venda</th>
                <th style="width:60px;"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${produto_lista}" var="p">
                <tr>
                    <td>
                        <a href="/produto_deleta/${p.id_Produto}" class="btn btn-mini apagar" title="Deletar">
                            <i class="icon-trash"></i>
                        </a>
                        <a href="/produto_editar/${p.id_Produto}" class="btn btn-mini" title="Editar">
                            <i class="icon-pencil"></i>
                        </a>
                    </td>
                    <td>${p.id_Produto}</td>
                    <td>${p.referencia}</td>
                    <td>${p.descricao}</td>
                    <td>${p.ativoCompra}</td>
                    <td>${p.ativoVenda}</td>
                    <td>
                        <a href="/produtounidade_lista/${p.id_Produto}" class="btn btn-mini apagar" title="Unidades">
                            <i class="icon-flag"></i>
                        </a>
                        <a href="/produtolote_lista/${p.id_Produto}" class="btn btn-mini" title="Lotes">
                            <i class="icon-tags"></i>
                        </a>
                    </td>
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
                <a href="<c:url value="/produto_novo"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
            </div>
        </div>
    </div>
</div>



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
                <div class="row-fluid">
                    <div class="span1" align="left">

                    </div>
                    <div class="span10">
                        <c:choose>
                            <c:when test="${tipomovimento == 'C'}">
                                <a class="brand" style="font: bold; margin-left: 26%;">Lista Movimento Financeiro - Crédito</a>
                            </c:when>
                            <c:otherwise>
                                <a class="brand" style="font: bold; margin-left: 26%;">Lista Movimento Financeiro - Débito</a>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
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
                <th style="width:40px;">NSU</th>
                <th style="width:40px;">ID</th>
                <th>Razão Social</th>
                <th style="width:40px;">P.Contas</th>
                <th style="width:90px;">Venc.</th>
                <th style="width:40px;">Dias</th>
                <th style="width:60px;">Valor</th>
                <th style="width:60px;">V.Liq.</th>
                <th style="width:30px;">Status</th>
            </tr>
            </thead>
            <tbody>
                <c:forEach items="${lista}" var="p">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${p.statusMovimento == 'P'}">
                                <a href="/movimentofinanceiro_cancelar/${p.id_MovimentoFinanceiro}" class="btn btn-mini apagar" title="Cancelar">
                                    <i class="icon-trash"></i>
                                </a>
                                <a href="/movimentofinanceiro_editar/${p.id_MovimentoFinanceiro}" class="btn btn-mini" title="Editar">
                                    <i class="icon-pencil"></i>
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a href="" class="btn btn-mini apagar" title="Cancelar">
                                    <i class="icon-ban-circle"></i>
                                </a>
                                <a href="" class="btn btn-mini" title="Editar">
                                    <i class="icon-ban-circle"></i>
                                </a>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${p.id_MovimentoFinanceiro}</td>
                    <td>${p.id_Pessoa}</td>
                    <td>${p.pessoa.razaoSocial}</td>
                    <td>${p.planoContas.codigoConta}</td>
                    <td>${p.dataVencimento}</td>
                    <td>${p.qtdDias}</td>
                    <td>${p.valorVencimento}</td>
                    <td>${p.valorLiquido}</td>
                    <td>${p.statusMovimento}</td>
                <tr>
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
                <a href="<c:url value="/movimentofinanceiro_filtros/${tipomovimento}"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-search"></span> Filtrar</a>
                <a href="<c:url value="/movimentofinanceiro_novo/${tipomovimento}"/>" class="btn btn-lg btn-primary" style="width: 70px;"><span class="icon-plus"></span> Novo</a>
            </div>
        </div>
    </div>
</div>
<br>
<br>

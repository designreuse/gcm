<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<div class="content-header">
    <c:choose>
        <c:when test="${tipo == 'CLI'}">
            <h1>Lista de Clientes</h1>
        </c:when>
        <c:when test="${tipo == 'FOR'}">
            <h1>Lista de Fornecedores</h1>
        </c:when>
        <c:when test="${tipo == 'FUN'}">
            <h1>Lista de Funcionários</h1>
        </c:when>
        <c:when test="${tipo == 'VEN'}">
            <h1>Lista de Vendedores</h1>
        </c:when>
        <c:when test="${tipo == 'TRA'}">
            <h1>Lista de Transportadores</h1>
        </c:when>
        <c:when test="${tipo == 'CON'}">
            <h1>Lista de Convênios</h1>
        </c:when>
        <c:when test="${tipo == 'HOS'}">
            <h1>Lista de Hospitais</h1>
        </c:when>
        <c:when test="${tipo == 'MED'}">
            <h1>Lista de Médicos</h1>
        </c:when>
        <c:when test="${tipo == 'ENF'}">
            <h1>Lista de Enfermeiros</h1>
        </c:when>
        <c:when test="${tipo == 'PAC'}">
            <h1>Lista de Paciêntes</h1>
        </c:when>
    </c:choose>
</div>

<div class="content body">
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Filtros</h3>
                </div>
                <div class="box-body">
                    <form class="form-horizontal" action="/pessoa_lista" method="post">
                        <fieldset>
                            <div class="form-group">
                                <div class="row">
                                    <label class="col-md-2 control-label" for="id_Pessoa">Id</label>
                                    <div class="col-md-2">
                                        <input type="text" maxlength="10" value="${filtros.id_Pessoa}"
                                               class="form-control" name="id_Pessoa" placeholder="Id" id="id_Pessoa"/>
                                    </div>
                                    <label class="col-md-1 control-label" for="cpfCnpj">Cnpj\Cpf</label>
                                    <div class="col-md-3">
                                        <input type="text" maxlength="10" value="${filtros.cpfCnpj}"
                                               class="form-control" name="cpfCnpj" placeholder="Cnpj\Cpf" id="cpfCnpj"/>
                                    </div>
                                </div>
                                <br>
                                <div class="row">
                                    <label class="col-md-2 control-label" for="razaoSocial">Razão Social</label>
                                    <div class="col-md-6">
                                        <input type="text" maxlength="50" value="${filtros.razaoSocial}"
                                               class="form-control" name="razaoSocial" placeholder="Razão Social" id="razaoSocial"/>
                                    </div>

                                    <button style="width: 80px" class="btn btn-primary" type="reset">Limpar</button>
                                    <button class="btn btn-primary" type="submit" style="width: 80px;">Pesquisar</button>
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
                        <thead style="background-color:silver;">
                        <tr>
                            <th style="width:100px; text-align:center;">Operações</th>
                            <th style="width:40px;">Id</th>
                            <th style="width:120px;">Cpf/Cnpj</th>
                            <th>Razao Social</th>
                            <th style="width:40px;">Ativo</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${lista}" var="p">
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${p.ativo == 'true'}">
                                            <a href="/pessoa_inativar/${tipo}/${p.id_Pessoa}" class="btn btn-default btn-xs" title="Inativar">
                                                <i class="fa fa-ban"></i>
                                            </a>
                                        </c:when>
                                        <c:when test="${p.ativo != 'true'}">
                                            <a href="/pessoa_ativar/${tipo}/${p.id_Pessoa}" class="btn btn-default btn-xs" title="Ativar">
                                                <i class="fa fa-thumbs-up"></i>
                                            </a>
                                        </c:when>
                                    </c:choose>

                                    <a href="/pessoa_editar/${tipo}/${p.id_Pessoa}" class="btn btn-default btn-xs" title="Editar">
                                        <i class="fa fa-pencil"></i>
                                    </a>
                                    <a href="/pessoa_detalhes/${tipo}/${p.id_Pessoa}" class="btn btn-default btn-xs" title="Detalhes">
                                        <i class="fa fa-eye"></i>
                                    </a>
                                </td>
                                <td>${p.id_Pessoa}</td>
                                <td>${p.cpfCnpj}</td>
                                <td>${p.razaoSocial}</td>
                                <td>${p.ativo}</td>
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
                        <a style="width: 80px" href="<c:url value="/pessoa_novo/${tipo}"/>" class="btn btn-primary" title="Novo">
                            <i class="fa fa-plus"></i> Novo
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
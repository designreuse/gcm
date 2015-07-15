<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<div class="container">
    <div class="container-fluid">
        <c:url value="/lancamentoestoque_update/${TipoEntSai}" var="gravar"/>
        <form:form class="form-signin" modelAttribute="lancamentoestoque" id="FormCadastro" action="${gravar}" method="PUT">

            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <c:choose>
                            <c:when test="${TipoEntSai == 'E'}">
                                <a class="brand" style="font: bold; margin-left: 35%">Editar Entrada por Ajuste</a>
                            </c:when>
                            <c:when test="${TipoEntSai == 'S'}">
                                <a class="brand" style="font: bold; margin-left: 35%;">Editar Saída por Ajuste</a>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>

            <div class="container-fluid navbar navbar-inner">
                <br>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:10px;">Id</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input id="id_LancamentoEstoque" path="id_LancamentoEstoque" type="text"
                                    readonly="true" maxlength="50" class="form-control"
                                    name="ID" placeholder="ID" style="width:80px;" required="true"/>
                    </div>
                    <div class="span1" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Status</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:select path="atualizado" multiple="false" id="Atualizado" style="width:130px;" disabled="true">
                            <option value="false" selected>Desatualizado</option>
                        </form:select>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Data Lançamento</label>
                    </div>
                    <div class="span2" style="text-align: left;">
                        <form:input path="dataLancamento" type="text"  maxlength="10" readonly="true"
                                    class="form-control" name="dataLancamento" placeholder="dataLancamento"
                                    style="width:100px;" />
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Empresa</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:input path="id_Empresa" class="form-control" type="hidden"/>
                        <form:select path="id_Empresa" multiple="false" id="Empresa"
                                     cssClass="form-control" required="autofocus"
                                     items="${listaempresa}" disabled="true"
                                     itemLabel="RazaoSocial"
                                     itemValue="id_Empresa" style="width:710px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">CFOP</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:input path="id_CFOP" class="form-control" type="hidden"/>
                        <form:select path="id_CFOP" multiple="false" id="CFOP"  disabled="true"
                                     cssClass="form-control" required="true"
                                     items="${listacfop}"
                                     itemLabel="Descricao"
                                     itemValue="id_CFOP" style="width:710px;"/>
                    </div>
                </div>
                <div class="row-fluid">
                    <div class="span2" style="text-align: left;">
                        <label style="font-size: 16px; margin-top:5px;">Observações</label>
                    </div>
                    <div class="span10" style="text-align: left;">
                        <form:textarea id="observacoes" path="observacoes" type="text"
                                    class="form-control" rows="3" cols="70"
                                    name="observacoes" placeholder="Observações" style="width:697px; height:30px"/>
                    </div>
                </div>

                <div class="row-fluid" style="overflow:auto; height: 220px;">
                    <table class="table table-striped table-bordered table-hover table-condensed active tabelaPaginada" id="example">
                        <thead style="background-color:silver">
                        <tr>
                            <th style="width:60px; text-align:center">
                                <a href="/lancamentoestoqueitem_novo/${TipoEntSai}/${id_lancamentoestoque}" class="btn btn-mini" title="Novo">
                                    <i class="icon-plus"></i>
                                </a>
                            </th>
                            <th style="width:30px;">Dep.</th>
                            <th style="width:40px;">Código</th>
                            <th style="width:80px;">Referência</th>
                            <th>Produto</th>
                            <th style="width:60px;">Lote</th>
                            <th style="width:50px;">Unidade</th>
                            <th style="width:50px;">Qtd.</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${listaitens}" var="p">
                            <tr>
                                <td>
                                    <a href="/lancamentoestoqueitem_delete/${TipoEntSai}/${id_lancamentoestoque}/${p.id_LancamentoEstoqueItem}" class="btn btn-mini apagar" title="Deletar">
                                        <i class="icon-trash"></i>
                                    </a>
                                    <a href="/lancamentoestoqueitem_editar/${TipoEntSai}/${id_lancamentoestoque}/${p.id_LancamentoEstoqueItem}" class="btn btn-mini" title="Editar">
                                        <i class="icon-pencil"></i>
                                    </a>
                                </td>
                                <td>${p.id_Deposito}</td>
                                <td>${p.produto.id_Produto}</td>
                                <td>${p.produto.referencia}</td>
                                <td>${p.produto.descricao}</td>
                                <td>${p.produtoLote.numeroLote}</td>
                                <td>${p.unidade.sigla}</td>
                                <td>${p.quantidade}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

            </div>
            <div class="navbar">
                <div class="navbar-inner">
                    <div class="container-fluid">
                        <div class="row-fluid">
                            <div class="span3" align="right">
                            </div>
                            <div class="span3" align="right">
                                <form:button class="btn btn-lg btn-primary btn-block" type="submit" style="width: 100px;">Salvar</form:button>
                            </div>
                            <div class="span3">
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value="/lancamentoestoque_lista/${TipoEntSai}"/>">Cancelar</a>
                            </div>
                            <div class="span3" align="right">
                                <a class="btn btn-lg btn-primary btn-block" style="width:100px;" href="<c:url value=""/>">Atualizar</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </form:form>
    </div>
</div>
<br>
<br>


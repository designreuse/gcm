<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="/WEB-INF/tld/custom_tags.tld" prefix="vls"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-header">
    <h1>Lista de Países</h1>
</div>
<div class="content body">
    <!-- Inicio do Box de Filtro -->
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-header with-border">
                    <h3 class="box-title">Filtro</h3>
                </div>
                <div class="box-body">
                    <form class="form-horizontal">
                        <div class="form-group">
                            <label class="col-md-1 control-label" for="sigla-pais">Sigla</label>
                            <div class="col-md-2">
                                <input id="sigla-pais" class="form-control" placeholder="sigla" type="text" />
                            </div>

                            <label class="col-md-1 control-label" for="nome-pais">Nome</label>
                            <div class="col-md-4">
                                <input id="nome-pais" class="form-control" placeholder="Brasil..." type="text" />
                            </div>
                            <button class="btn btn-primary" type="reset">Limpar</button>
                            <button class="btn btn-primary" type="submit">Pesquisar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Fim do Box de Filtro -->

    <!--Inicio do box da tabela -->
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <table class="table table-bordered  table-hover table-striped">
                        <tbody>
                        <tr>
                            <th width="10%" style="text-align: center">Ações</th>
                            <th width=15%">Sigla</th>
                            <th>Nome</th>
                            <th width="15%" style="text-align: center">IBGE</th>
                        </tr>
                        <tr>
                            <td align="center">
                                <a href="#" class="btn btn-default btn-xs" title="Apagar Registro">
                                    <i class="fa fa-trash-o" ></i>
                                </a>
                                <a href="#"  class="btn btn-default btn-xs"  title="Editar Registro">
                                    <i class="fa fa-edit"></i>
                                </a>
                            </td>
                            <td>BRA</td>
                            <td>Brasil</td>
                            <td style="text-align: center">54653</td>
                        </tr>
                        <tr>
                            <td align="center">
                                <a href="#" class="btn btn-default btn-xs" title="Apagar Registro">
                                    <i class="fa fa-trash-o" ></i>
                                </a>
                                <a href="#"  class="btn btn-default btn-xs"  title="Editar Registro">
                                    <i class="fa fa-edit"></i>
                                </a>
                            </td>
                            <td>RUS</td>
                            <td>Rússia</td>
                            <td style="text-align: center">65656</td>
                        </tr>
                        <tr>
                            <td align="center">
                                <a href="#" class="btn btn-default btn-xs" title="Apagar Registro">
                                    <i class="fa fa-trash-o" ></i>
                                </a>
                                <a href="#"  class="btn btn-default btn-xs"  title="Editar Registro">
                                    <i class="fa fa-edit"></i>
                                </a>
                            </td>
                            <td>EUA</td>
                            <td>Estados Unidos</td>
                            <td style="text-align: center">32322</td>
                        </tr>
                        <tr>
                            <td align="center">
                                <a href="#" class="btn btn-default btn-xs" title="Apagar Registro">
                                    <i class="fa fa-trash-o" ></i>
                                </a>
                                <a href="#"  class="btn btn-default btn-xs"  title="Editar Registro">
                                    <i class="fa fa-edit"></i>
                                </a>
                            </td>
                            <td>POR</td>
                            <td>Portugal</td>
                            <td style="text-align: center">34324</td>
                        </tr>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <!--Fim do box da tabela -->

    <!--Inicio do box de Paginacao -->
    <div class="row">
        <div class="col-sm-12">
            <div class="box box-primary">
                <div class="box-body">
                    <ul class="pagination pagination-sm no-margin pull-right">
                        <li><a><<</a></li>
                        <li><a>1</a></li>
                        <li><a>2</a></li>
                        <li><a>3</a></li>
                        <li><a>4</a></li>
                        <li><a>5</a></li>
                        <li><a>>></a></li>

                    </ul>
                </div>
            </div>
        </div>
    </div>
    <!--Fim do box de paginacao -->
</div>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <%--<!-- Sidebar user panel (optional) -->--%>
        <%--<div class="user-panel">--%>
            <%--<div class="pull-left image">--%>
                <%--<img src="/static/lte/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image" />--%>
            <%--</div>--%>
            <%--<div class="pull-left info">--%>
                <%--<p>Joao Carlos</p>--%>
                <%--<!-- Status -->--%>
                <%--<a href="#"><i class="fa fa-circle text-success"></i> Online</a>--%>
            <%--</div>--%>
        <%--</div>--%>

        <!-- search form (Optional) -->
        <form action="#" method="get" class="sidebar-form">
            <div class="input-group">
                <input type="text" name="q" class="form-control" placeholder="Pesquisar no menu..." />
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
            </div>
        </form>
        <!-- /.search form -->

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu">
            <li class="header">MENU</li>
            <!-- Optionally, you can add icons to the links -->
            <li class="active"><a href="#"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
            <%--<li><a href="#"><i class="fa fa-link"></i> <span>Another Link</span></a></li>--%>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>Cadastros</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-circle-o"></i> Geral <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> Paises</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> UF</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Municípios</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-circle-o"></i> Produtos <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> Grupo</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Sub-Grupo</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Marca</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> NCM</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Unidade</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Depósito</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i><b> Produto</b></a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-circle-o"></i> Pessoas <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> Grupo</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Sub-Grupo</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Marca</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> NCM</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Unidade</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Depósito</a></li>
                            <li class="divider"></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i><b> Produto</b></a></li>

                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-money"></i> <span>Comercial</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-shopping-cart"></i> Compras <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="#"><i class="fa fa-circle-o"></i> Cotação de Compras</a></li>
                            <li><a href="#"><i class="fa fa-circle-o"></i> Pedido de Compras</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul><!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
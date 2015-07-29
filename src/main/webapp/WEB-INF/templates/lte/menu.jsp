<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar" style="width: 22%;">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">
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
            <!-- li class="header">MENU</li -->
            <!-- Optionally, you can add icons to the links -->
            <li class="active"><a href="/"><i class="fa fa-home"></i> <span> Home</span></a></li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i> <span>Cadastros</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-globe"></i> Geral <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="<c:url value="/pais_lista"/>"><i class="fa fa-circle-o"></i> 1001 Paises</a></li>
                            <li><a href="<c:url value="/uf_lista"/>"><i class="fa fa-circle-o"></i> 1002 UF</a></li>
                            <li><a href="<c:url value="/municipio_lista"/>"><i class="fa fa-circle-o"></i> 1003 Municípios</a></li>
                            <li class="divider"></li>
                            <li><a href="<c:url value="/empresa_lista"/>"><i class="fa fa-circle-o"></i> 1004 Empresa</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-glass"></i> Produtos <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="<c:url value="/grupoproduto_lista"/>"><i class="fa fa-circle-o"></i> 1101 Grupo</a></li>
                            <li><a href="<c:url value="/subgrupoproduto_lista"/>"><i class="fa fa-circle-o"></i> 1102 Sub-Grupo</a></li>
                            <li><a href="<c:url value="/marcaproduto_lista"/>"><i class="fa fa-circle-o"></i> 1103 Marca</a></li>
                            <li><a href="<c:url value="/ncm_lista"/>"><i class="fa fa-circle-o"></i> 1104 NCM</a></li>
                            <li><a href="<c:url value="/unidade_lista"/>"><i class="fa fa-circle-o"></i> 1107 Unidade</a></li>
                            <li><a href="<c:url value="/deposito_lista"/>"><i class="fa fa-circle-o"></i> 1105 Depósito</a></li>
                            <li class="divider"></li>
                            <li><a href="<c:url value="/produto_lista"/>"><i class="fa fa-circle-o"></i><b> 1106 Produto</b></a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-user"></i> Pessoas <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="<c:url value="/pessoa_lista/CLI"/>"><i class="fa fa-circle-o"></i> 1201 Clientes</a></li>
                            <li><a href="<c:url value="/pessoa_lista/FOR"/>"><i class="fa fa-circle-o"></i> 1202 Fornecedores</a></li>
                            <li><a href="<c:url value="/pessoa_lista/VEN"/>"><i class="fa fa-circle-o"></i> 1203 Vendedores</a></li>
                            <li><a href="<c:url value="/pessoa_lista/FUN"/>"><i class="fa fa-circle-o"></i> 1204 Funcionários</a></li>
                            <li><a href="<c:url value="/pessoa_lista/TRA"/>"><i class="fa fa-circle-o"></i> 1205 Transportadores</a></li>
                            <li><a href="<c:url value="/pessoa_lista/CON"/>"><i class="fa fa-circle-o"></i> 1206 Convênios</a></li>
                            <li><a href="<c:url value="/pessoa_lista/HOS"/>"><i class="fa fa-circle-o"></i> 1207 Hospitais</a></li>
                            <li><a href="<c:url value="/pessoa_lista/MED"/>"><i class="fa fa-circle-o"></i> 1208 Médicos</a></li>
                            <li><a href="<c:url value="/pessoa_lista/ENF"/>"><i class="fa fa-circle-o"></i> 1209 Enfermeiros</a></li>
                            <li><a href="<c:url value="/pessoa_lista/PAC"/>"><i class="fa fa-circle-o"></i> 1210 Pacientes</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a style="color: #dd1144;" href="#"><i class="fa fa-tags"></i> Procedimentos <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> 1301 Procedimento Médico - ANS</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> 1302 Classificação de Doenças - CID</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-th"></i> Fiscal <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="<c:url value="/sta_lista"/>"><i class="fa fa-circle-o"></i> 1401 Situação Tributária A</a></li>
                            <li><a href="<c:url value="/stb_lista"/>"><i class="fa fa-circle-o"></i> 1402 Situaçao Tributária B</a></li>
                            <li><a href="<c:url value="/situacaotributariapiscofins_lista"/>"><i class="fa fa-circle-o"></i> 1405 Situaçao Tributária PIS\COFINS</a></li>
                            <li><a href="<c:url value="/cfop_lista"/>"><i class="fa fa-circle-o"></i> 1403 CFOP</a></li>
                            <li><a href="<c:url value=""/>"><i class="fa fa-circle-o"></i> 1404 Grupo Fiscal</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-briefcase"></i> Financeiro <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="<c:url value="/centrocusto_lista"/>"><i class="fa fa-circle-o"></i> 1501 Centro de Custo</a></li>
                            <li><a href="<c:url value="/planocontas_lista"/>"><i class="fa fa-circle-o"></i> 1502 Plano de Contas</a></li>
                            <li><a href="<c:url value="/banco_lista"/>"><i class="fa fa-circle-o"></i> 1503 Bancos</a></li>
                            <li><a href="<c:url value="/contacorrente_lista"/>"><i class="fa fa-circle-o"></i> 1504 Contas Correntes</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-money"></i> <span style="color: #dd1144;">Comercial</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a style="color: #dd1144;" href="#"><i class="fa fa-shopping-cart"></i> Compras <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Cotação de Compras</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Pedido de Compras</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a style="color: #dd1144;" href="#"><i class="fa fa-usd"></i> Precificação <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Tabela de Preço por Produto</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Precificação Procedimentos - ANS</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a style="color: #dd1144;" href="#"><i class="fa fa-pencil"></i> Pedidos <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Orçamento de Vendas</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Pedidos de Vendas</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Pedidos Diversos (Consignação, Empréstimo, Doações, etc...)</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Retornar Pedidos (Consignação, Empréstimo, Doações)</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-suitcase"></i> <span>Estoque</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a style="color: #dd1144;" href="#"><i class="fa fa-exchange"></i> Inventário <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Preparação para o Inventário</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Lista para Contágem</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Lançar Contágens</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Emitir Divergências entre Contágem</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Apuração do Inventário</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Atualização do Inventário</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-plus"></i> Entradas <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Efetuar Entradas por Compras</a></li>
                            <li><a href="<c:url value="/lancamentoestoque_lista/E"/>"><i class="fa fa-circle-o"></i> Efetuar Entradas Diversas</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-minus"></i> Saídas <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="<c:url value="/lancamentoestoque_lista/S"/>"><i class="fa fa-circle-o"></i> Efetuar Saídas Diversas</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a style="color: #dd1144;" href="#"><i class="fa fa-search"></i> Consultas <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Consultar Saldo por Produto</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Consultar Movimentação por Produto</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-bolt"></i> <span style="color: #dd1144;">Faturamento</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Faturar Pedidos em Aberto</a></li>
                    <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Cancelar Faturamento</a></li>
                    <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Consultar Faturamento</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-suitcase"></i> <span>Financeiro</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-barcode"></i> Contas a Pagar <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="<c:url value="/movimentofinanceiro_filtros/D"/>"><i class="fa fa-circle-o"></i> Lançamentos de Débito</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Liquidação de Débito em Grupo</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Negociação de Débito</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-money"></i> Contas a Receber <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="<c:url value="/movimentofinanceiro_filtros/C"/>"><i class="fa fa-circle-o"></i> Lançamentos de Crédito</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Liquidação de Crédito em Grupo</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Negociação de Credito</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a style="color: #dd1144;" href="#"><i class="fa fa-hdd-o"></i> Conta Corrente <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Lançamento de Tarifas Bancárias</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Extrato de Conta Corrente</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Conciliação Bancária</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-gavel"></i> <span style="color: #dd1144;">Fiscal</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a style="color: #dd1144;" href="#"><i class="fa fa-external-link "></i> NF-e <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Consultar NF-e</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Transmitir NF-e</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Cancelar NF-e</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Inutilizar NF-e</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Reimprimir NF-e</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-bar-chart-o"></i> <span style="color: #dd1144;">Relatórios</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a style="color: #dd1144;" href="#"><i class="fa fa-suitcase"></i> Financeiro <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li>
                                <a style="color: #dd1144;" href="#"><i class="fa fa-money"></i> Contas a Receber <i class="fa fa-angle-left pull-right"></i></a>
                                <ul class="treeview-menu">
                                    <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Imprimir Lançamentos Programados</a></li>
                                    <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Imprimir Lançamentos Liquidados</a></li>
                                    <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Imprimir Lançamentos Cancelados</a></li>
                                </ul>
                            </li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-sitemap"></i> <span>Sistema</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a href="#"><i class="fa fa-user"></i> Usuários <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li><a href="<c:url value="/usuario_lista"/>"><i class="fa fa-circle-o"></i> Cadastro de Usuários</a></li>
                            <li><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Trocar Senha</a></li>
                            <li><a href="<c:url value="/grupousuario_lista"/>"><i class="fa fa-circle-o"></i> Cadastrar Grupos de usuários</a></li>
                        </ul>
                    </li>
                    <li><a href="<c:url value="/logout"/>"><i class="fa fa-circle-o"></i> Sair</a></li>
                </ul>
            </li>
        </ul><!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>
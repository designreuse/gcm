<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>


<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

        <!-- Sidebar Menu -->
        <ul class="sidebar-menu" id="menu">
            <li class="active"><a href="/"><i class="fa fa-home"></i> <span> Home</span></a></li>
            <li class="treeview">
                <a href="#" class="hidden" id ="1">
                    <i class="fa fa-edit"></i> <span>Cadastros</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a href="#" class="hidden" id="10"><i class="fa fa-globe"></i> Geral <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="1001"><a href="<c:url value="/pais_lista"/>"><i class="fa fa-circle-o"></i> 1001 Paises</a></li>
                            <li class="hidden" id="1002"><a href="<c:url value="/uf_lista"/>"><i class="fa fa-circle-o"></i> 1002 UF</a></li>
                            <li class="hidden" id="1003"><a href="<c:url value="/municipio_lista"/>"><i class="fa fa-circle-o"></i> 1003 Municípios</a></li>
                            <li class="hidden" id="1004"><a href="<c:url value="/empresa_lista"/>"><i class="fa fa-circle-o"></i> 1004 Empresa</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="11" href="#"><i class="fa fa-glass"></i> Produtos <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="1101"><a href="<c:url value="/grupoproduto_lista"/>"><i class="fa fa-circle-o"></i> 1101 Grupo</a></li>
                            <li class="hidden" id="1102"><a href="<c:url value="/subgrupoproduto_lista"/>"><i class="fa fa-circle-o"></i> 1102 Sub-Grupo</a></li>
                            <li class="hidden" id="1103"><a href="<c:url value="/marcaproduto_lista"/>"><i class="fa fa-circle-o"></i> 1103 Marca</a></li>
                            <li class="hidden" id="1104"><a href="<c:url value="/ncm_lista"/>"><i class="fa fa-circle-o"></i> 1104 NCM</a></li>
                            <li class="hidden" id="1107"><a href="<c:url value="/unidade_lista"/>"><i class="fa fa-circle-o"></i> 1107 Unidade</a></li>
                            <li class="hidden" id="1105"><a href="<c:url value="/deposito_lista"/>"><i class="fa fa-circle-o"></i> 1105 Depósito</a></li>
                            <li class="hidden" id="1106"><a href="<c:url value="/produto_lista"/>"><i class="fa fa-circle-o"></i> 1106 Produto</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="12" href="#"><i class="fa fa-user"></i> Pessoas <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="1201"><a href="<c:url value="/pessoa_lista/CLI"/>"><i class="fa fa-circle-o"></i> 1201 Clientes</a></li>
                            <li class="hidden" id="1202"><a href="<c:url value="/pessoa_lista/FOR"/>"><i class="fa fa-circle-o"></i> 1202 Fornecedores</a></li>
                            <li class="hidden" id="1203"><a href="<c:url value="/pessoa_lista/VEN"/>"><i class="fa fa-circle-o"></i> 1203 Vendedores</a></li>
                            <li class="hidden" id="1204"><a href="<c:url value="/pessoa_lista/FUN"/>"><i class="fa fa-circle-o"></i> 1204 Funcionários</a></li>
                            <li class="hidden" id="1205"><a href="<c:url value="/pessoa_lista/TRA"/>"><i class="fa fa-circle-o"></i> 1205 Transportadores</a></li>
                            <li class="hidden" id="1206"><a href="<c:url value="/pessoa_lista/CON"/>"><i class="fa fa-circle-o"></i> 1206 Convênios</a></li>
                            <li class="hidden" id="1207"><a href="<c:url value="/pessoa_lista/HOS"/>"><i class="fa fa-circle-o"></i> 1207 Hospitais</a></li>
                            <li class="hidden" id="1208"><a href="<c:url value="/pessoa_lista/MED"/>"><i class="fa fa-circle-o"></i> 1208 Médicos</a></li>
                            <li class="hidden" id="1209"><a href="<c:url value="/pessoa_lista/ENF"/>"><i class="fa fa-circle-o"></i> 1209 Enfermeiros</a></li>
                            <li class="hidden" id="1210"><a href="<c:url value="/pessoa_lista/PAC"/>"><i class="fa fa-circle-o"></i> 1210 Pacientes</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="13" style="color: #dd1144;" href="#"><i class="fa fa-tags"></i> Procedimentos <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="1301"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> 1301 Proc. Médico - ANS</a></li>
                            <li class="hidden" id="1302"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> 1302 Class. de Doenças - CID</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="14" href="#"><i class="fa fa-th"></i> Fiscal <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="1401"><a href="<c:url value="/sta_lista"/>"><i class="fa fa-circle-o"></i> 1401 Situação Tributária A</a></li>
                            <li class="hidden" id="1402"><a href="<c:url value="/stb_lista"/>"><i class="fa fa-circle-o"></i> 1402 Situaçao Tributária B</a></li>
                            <li class="hidden" id="1405"><a href="<c:url value="/stpiscofins_lista"/>"><i class="fa fa-circle-o"></i> 1405 ST PIS\COFINS</a></li>
                            <li class="hidden" id="1403"><a href="<c:url value="/cfop_lista"/>"><i class="fa fa-circle-o"></i> 1403 CFOP</a></li>
                            <li class="hidden" id="1404"><a href="<c:url value=""/>" style="color: #dd1144;"><i class="fa fa-circle-o"></i> 1404 Grupo Fiscal</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="15" href="#"><i class="fa fa-usd"></i> Financeiro <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="1501"><a href="<c:url value="/centrocusto_lista"/>"><i class="fa fa-circle-o"></i> 1501 Centro de Custo</a></li>
                            <li class="hidden" id="1502"><a href="<c:url value="/planocontas_lista"/>"><i class="fa fa-circle-o"></i> 1502 Plano de Contas</a></li>
                            <li class="hidden" id="1503"><a href="<c:url value="/banco_lista"/>"><i class="fa fa-circle-o"></i> 1503 Bancos</a></li>
                            <li class="hidden" id="1504"><a href="<c:url value="/contacorrente_lista"/>"><i class="fa fa-circle-o"></i> 1504 Contas Correntes</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#" class="hidden" id="2">
                    <i class="fa fa-money"></i> <span style="color: #dd1144;">Comercial</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" style="color: #dd1144;" href="#"><i class="fa fa-shopping-cart"></i> Compras <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Cotação de Compras</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Pedido de Compras</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" style="color: #dd1144;" href="#"><i class="fa fa-usd"></i> Precificação <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Tabela de Preço por Produto</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Precificação Procedimentos - ANS</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" style="color: #dd1144;" href="#"><i class="fa fa-pencil"></i> Pedidos <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Orçamento de Vendas</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Pedidos de Vendas</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Pedidos Diversos (Consignação, Empréstimo, Doações, etc...)</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Retornar Pedidos (Consignação, Empréstimo, Doações)</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#" class="hidden" id="3">
                    <i class="fa fa-suitcase"></i> <span style="color: #dd1144;">Estoque</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" style="color: #dd1144;" href="#"><i class="fa fa-exchange"></i> Inventário <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Preparação para o Inventário</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Lista para Contágem</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Lançar Contágens</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Emitir Divergências entre Contágem</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Apuração do Inventário</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Atualização do Inventário</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" href="#" style="color: #dd1144;"><i class="fa fa-plus"></i> Entradas <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Efetuar Entradas por Compras</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Efetuar Entradas Diversas</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" style="color: #dd1144;" href="#"><i class="fa fa-minus"></i> Saídas <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Efetuar Saídas Diversas</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" style="color: #dd1144;" href="#"><i class="fa fa-search"></i> Consultas <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Consultar Saldo por Produto</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Consultar Movimentação por Produto</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#" class="hidden" id="4">
                    <i class="fa fa-bolt"></i> <span style="color: #dd1144;">Faturamento</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Faturar Pedidos em Aberto</a></li>
                    <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Cancelar Faturamento</a></li>
                    <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Consultar Faturamento</a></li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#" class="hidden" id="5">
                    <i class="fa fa-usd"></i> <span>Financeiro</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="51" href="#"><i class="fa fa-barcode"></i> Contas a Pagar <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="5101"><a href="<c:url value="/movimentofinanceiro_lista/D"/>"><i class="fa fa-circle-o"></i> Lançamentos de Débito</a></li>
                            <li class="hidden" id="5102"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Lançamento de Débito em Grupo</a></li>
                            <li class="hidden" id="5103"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Liquidação de Débito em Grupo</a></li>
                            <li class="hidden" id="5104"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Negociação de Débito</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="52" href="#"><i class="fa fa-money"></i> Contas a Receber <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="5201"><a href="<c:url value="/movimentofinanceiro_lista/C"/>"><i class="fa fa-circle-o"></i> Lançamentos de Crédito</a></li>
                            <li class="hidden" id="5202"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Lançamento de Crédito em Grupo</a></li>
                            <li class="hidden" id="5203"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Liquidação de Crédito em Grupo</a></li>
                            <li class="hidden" id="5204"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Negociação de Credito</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="53" style="color: #dd1144;" href="#"><i class="fa fa-sign-out"></i> Integração Bancária <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="5301"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Emitir boletos de Cobrança</a></li>
                            <li class="hidden" id="5302"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Gerar arquivo de remessa</a></li>
                            <li class="hidden" id="5303"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Processar Arquivo de Retorno</a></li>
                        </ul>
                    </li>
                </ul>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="54" style="color: #dd1144;" href="#"><i class="fa fa-hdd-o"></i> Conta Corrente <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="5401"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Lançamento de Tarifas Bancárias</a></li>
                            <li class="hidden" id="5402"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Extrato de Conta Corrente</a></li>
                            <li class="hidden" id="5403"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Conciliação Bancária</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview" id="6">
                <a href="#" class="hidden">
                    <i class="fa fa-gavel"></i> <span style="color: #dd1144;">Fiscal</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" style="color: #dd1144;" href="#"><i class="fa fa-external-link "></i> NF-e <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Consultar NF-e</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Transmitir NF-e</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Cancelar NF-e</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Inutilizar NF-e</a></li>
                            <li class="hidden"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Reimprimir NF-e</a></li>
                        </ul>
                    </li>
                </ul>
            </li>
            <li class="treeview">
                <a href="#" class="hidden" id="7">
                    <i class="fa fa-bar-chart-o"></i> <span>Relatórios</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                    <li>
                        <a class="hidden" id="71" href="#"><i class="fa fa-suitcase"></i> Financeiro <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li>
                                <a class="hidden" id="7101" href="#"><i class="fa fa-money"></i> Contas a Pagar <i class="fa fa-angle-left pull-right"></i></a>
                                <ul class="treeview-menu">
                                    <li id="710101" class="hidden"><a href="<c:url value="/movimentofinanceiro_imprimirprogramacao/D"/>"><i class="fa fa-circle-o"></i> Imprimir Movimento Programados</a></li>
                                    <li id="710102" class="hidden"><a href="#"><i class="fa fa-circle-o"></i> Imprimir Movimento Liquidados</a></li>
                                    <li id="710103" class="hidden"><a href="#"><i class="fa fa-circle-o"></i> Imprimir Movimento Cancelados</a></li>
                                </ul>

                                <a class="hidden" id="7102" href="#"><i class="fa fa-money"></i> Contas a Receber <i class="fa fa-angle-left pull-right"></i></a>
                                <ul class="treeview-menu">
                                    <li id="710201" class="hidden"><a href="<c:url value="/movimentofinanceiro_imprimirprogramacao/C"/>"><i class="fa fa-circle-o"></i> Imprimir Movimento Programados</a></li>
                                    <li id="710202" class="hidden"><a href="#"><i class="fa fa-circle-o"></i> Imprimir Movimento Liquidados</a></li>
                                    <li id="710203" class="hidden"><a href="#"><i class="fa fa-circle-o"></i> Imprimir Movimento Cancelados</a></li>
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
                        <a class="hidden" id="81" href="#"><i class="fa fa-user"></i> Usuários <i class="fa fa-angle-left pull-right"></i></a>
                        <ul class="treeview-menu">
                            <li class="hidden" id="8101"><a href="<c:url value="/usuario_lista"/>"><i class="fa fa-circle-o"></i> Cadastro de Usuários</a></li>
                            <li class="hidden" id="8102"><a style="color: #dd1144;" href="#"><i class="fa fa-circle-o"></i> Trocar Senha</a></li>
                            <li class="hidden" id="8103"><a href="<c:url value="/grupousuario_lista"/>"><i class="fa fa-circle-o"></i> Grupos de Usuários</a></li>
                        </ul>
                    </li>
                    <li><a href="<c:url value="/logout"/>"><i class="fa fa-circle-o"></i> Sair</a></li>
                </ul>
            </li>
        </ul><!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
</aside>

<script>
    $(window).load(function() {
        carregamenu();
    });

    function carregamenu(){
        $.getJSON("${pageContext.request.contextPath}/listatransacoesusuario", function(result){

            cod = '';
            $.each(result, function(i){
                cod = '#'+result[i].codigoTransacao.replace('.','').replace('.','').replace('.','').replace('.','');
                $(cod).attr('class','');
            });

        });
    }
</script>
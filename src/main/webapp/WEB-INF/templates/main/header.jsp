<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.apache.commons.lang3.text.WordUtils" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--@elvariable id="erro" type="java.lang.String"--%>

<%
    // pega as credenciais
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String nome = "rod";
    UserDetails userDetails = (UserDetails) principal;
    nome = userDetails.getUsername();
    nome = WordUtils.capitalizeFully(nome);
%>

<header>
    <div class="navbar navbar-fixed-top">

        <div class="navbar-inner">
            <div class="row-fluid">
                <div class="span9" style="text-align: left;">
                    <div class="btn-group">
                        <a class="btn" id="home" href="<c:url value="/"/>" style="font-size:12px;"><i class="icon-home"></i>&nbsp Home</a>
                    </div>

                    <div class="btn-group">
                        <button class="btn dropdown-toggle" data-toggle="dropdown" style="font-size:12px;">Cadastros
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">

                            <li class="dropdown-submenu" id="geral">
                                <a class="text-left" style="color: #999999"><i class="icon-globe"></i>&nbsp Geral</a>
                                <ul class="dropdown-menu">
                                    <li id="1001"><a style="color: #000000;" href="<c:url value="/pais_lista"/>">1001 Pais</a></li>
                                    <li id="1002"><a style="color: #000000;" href="<c:url value="/uf_lista"/>">1002 UF</a></li>
                                    <li id="1003"><a style="color: #000000;" href="<c:url value="/municipio_lista"/>">1003 Municipio</a></li>
                                    <li class="divider"></li>
                                    <li id="1004"><a style="color: #000000;" href="<c:url value="/empresa_lista"/>">1004 Empresa</a></li>
                                </ul>
                            </li>

                            <li class="dropdown-submenu" id="produtos">
                                <a class="text-left" style="color: #999999"><i class="icon-glass"></i>&nbsp Produtos</a>
                                <ul class="dropdown-menu">
                                    <li id="1101"><a style="color: #000000;" href="<c:url value="/grupoproduto_lista"/>">1101 Grupo</a></li>
                                    <li id="1102"><a style="color: #000000;" href="<c:url value="/subgrupoproduto_lista"/>">1102 SubGrupo</a></li>
                                    <li id="1103"><a style="color: #000000;" href="<c:url value="/marcaproduto_lista"/>">1103 Marca</a></li>
                                    <li id="1104"><a style="color: #000000;" href="<c:url value="/ncm_lista"/>">1104 NCM</a></li>
                                    <li id="1107"><a style="color: #000000;" href="<c:url value="/unidade_lista"/>">1107 Unidades</a></li>
                                    <li id="1105"><a style="color: #000000;" href="<c:url value="/deposito_lista"/>">1105 Depósito</a></li>
                                    <li class="divider"></li>
                                    <li id="1106"><a style="color: #000000;" href="<c:url value="/produto_lista"/>">1106 Produto</a></li>
                                </ul>
                            </li>

                            <li class="dropdown-submenu" id="pessoas">
                                <a class="text-left" style="color: #999999"><i class="icon-user"></i>&nbsp Pessoas</a>
                                <ul class="dropdown-menu">
                                    <li id="1201"><a style="color: #000000;" href="<c:url value="/pessoa_lista/CLI"/>">1201 Clientes</a></li>
                                    <li id="1202"><a style="color: #000000;" href="<c:url value="/pessoa_lista/FOR"/>">1202 Fornecedores</a></li>
                                    <li id="1203"><a style="color: #000000;" href="<c:url value="/pessoa_lista/VEN"/>">1203 Vendedores</a></li>
                                    <li id="1204"><a style="color: #000000;" href="<c:url value="/pessoa_lista/FUN"/>">1204 Funcionários</a></li>
                                    <li id="1205"><a style="color: #000000;" href="<c:url value="/pessoa_lista/TRA"/>">1205 Transportadores</a></li>
                                    <li id="1206"><a style="color: #000000;" href="<c:url value="/pessoa_lista/CON"/>">1206 Convênios</a></li>
                                    <li id="1207"><a style="color: #000000;" href="<c:url value="/pessoa_lista/HOS"/>">1207 Hospitais</a></li>
                                    <li id="1208"><a style="color: #000000;" href="<c:url value="/pessoa_lista/MED"/>">1208 Médicos</a></li>
                                    <li id="1209"><a style="color: #000000;" href="<c:url value="/pessoa_lista/ENF"/>">1209 Enfermeiros</a></li>
                                    <li id="1210"><a style="color: #000000;" href="<c:url value="/pessoa_lista/PAC"/>">1210 Pacientes</a></li>
                                </ul>
                            </li>

                            <li class="dropdown-submenu" id="procedimentos">
                                <a class="text-left" style="color: #999999"><i class="icon-tags"></i>&nbsp Procedimentos</a>
                                <ul class="dropdown-menu">
                                    <li id="1301"><a style="color: #dd1144;" href="<c:url value=""/>">1301 Procedimento Médico - ANS</a></li>
                                    <li id="1302"><a style="color: #dd1144;" href="<c:url value=""/>">1302 Classificação de Doenças - CID</a></li>
                                </ul>
                            </li>

                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #999999"><i class="icon-th"></i>&nbsp Fiscal</a>
                                <ul class="dropdown-menu">
                                    <li id="1401"><a style="color: #000000;" href="<c:url value="/sta_lista"/>">1401 Situação Tributária A</a></li>
                                    <li id="1402"><a style="color: #000000;" href="<c:url value="/stb_lista"/>">1402 Situaçao Tributária B</a></li>
                                    <li id="1405"><a style="color: #000000;" href="<c:url value="/situacaotributariapiscofins_lista"/>">1405 Situaçao Tributária PIS\COFINS</a></li>
                                    <li id="1403"><a style="color: #000000;" href="<c:url value="/cfop_lista"/>">1403 CFOP</a></li>
                                    <li class="divider"></li>
                                    <li id="1404"><a style="color: #dd1144;" href="<c:url value=""/>">1404 Grupo Fiscal</a></li>
                                </ul>
                            </li>

                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #999999"><i class="icon-briefcase"></i>&nbsp Financeiro</a>
                                <ul class="dropdown-menu">
                                    <li id="1501"><a style="color: #000000;" href="<c:url value="/centrocusto_lista"/>">1501 Centro de Custo</a></li>
                                    <li id="1502"><a style="color: #000000;" href="<c:url value="/planocontas_lista"/>">1502 Plano de Contas</a></li>
                                    <li id="1503"><a style="color: #000000;" href="<c:url value="/banco_lista"/>">1503 Bancos</a></li>
                                    <li id="1504"><a style="color: #000000;" href="<c:url value="/contacorrente_lista"/>">1504 Contas Correntes</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                    <div class="btn-group">
                        <button class="btn dropdown-toggle" data-toggle="dropdown" style="color: #dd1144; font-size:12px;">Comercial
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-shopping-cart"></i>&nbsp Compras</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Cotação de Compra</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Pedido de Compra</a></li>
                                </ul>
                            </li>

                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-book"></i>&nbsp Precificação</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Tabela de Preço por Produto</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Precificação dos Procedimentos - ANS</a></li>
                                </ul>
                            </li>

                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-pencil"></i>&nbsp Pedidos</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Orçamento de Vendas</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Pedidos de Vendas</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Pedidos Diversos (Consignação, Empréstimo, Doações, etc...)</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Retornar Pedidos (Consignação, Empréstimo, Doações)</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                    <div class="btn-group">
                        <button class="btn dropdown-toggle" data-toggle="dropdown" style="font-size:12px;">Estoque
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-info-sign"></i>&nbsp Inventário</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Abrir Contágem</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Processar Inventário (Realizar ajuste no estoque)</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #999999"><i class="icon-plus"></i>&nbsp Entradas</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Efetuar Entradas por Compras</a></li>
                                    <li><a style="color: #000000;" href="<c:url value="/lancamentoestoque_lista/E"/>">Efetuar Entradas Diversas</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #999999"><i class="icon-minus"></i>&nbsp Saídas</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #000000;" href="<c:url value="/lancamentoestoque_lista/S"/>">Efetuar Saídas Diversas</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-search"></i>&nbsp Consultas</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Consultar Saldo por Produto</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Consultar Movimentação por Produto</a></li>
                                </ul>
                            </li>

                        </ul>
                    </div>

                    <!--div class="btn-group">
                        <button class="btn dropdown-toggle" data-toggle="dropdown">Cons. Médicas
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                           <li><a style="color: #dd1144;" href="<c:url value=""/>">Painel de Atendimento</a></li>
                           <li><a style="color: #dd1144;" href="<c:url value=""/>">Agendamentos de Consultas</a></li>
                           <li><a style="color: #dd1144;" href="<c:url value=""/>">Atendimento</a></li>
                           <li><a style="color: #dd1144;" href="<c:url value=""/>">Prontuário Médico</a></li>
                        </ul>
                    </div -->

                    <div class="btn-group">
                        <button class="btn dropdown-toggle" data-toggle="dropdown" style="color: #dd1144; font-size:12px;">Faturamento
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a style="color: #dd1144;" href="<c:url value=""/>">Faturar Pedidos em Aberto</a></li>
                            <li class="divider"></li>
                            <li><a style="color: #dd1144;" href="<c:url value=""/>">Cancelar Faturamento</a></li>
                            <li><a style="color: #dd1144;" href="<c:url value=""/>">Consultar Faturamento</a></li>
                        </ul>
                    </div>

                    <div class="btn-group">
                        <button class="btn dropdown-toggle" data-toggle="dropdown" style="font-size:12px;">Financeiro
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #999999"><i class="icon-barcode"></i>&nbsp Contas a Pagar</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #999999;" href="<c:url value="/movimentofinanceiro_filtros/D"/>">Lançamentos de Débito</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Liquidação de Débito em Grupo</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Negociação de Débito</a></li>
                                    <li class="divider"></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Emissão de Cheques</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #999999"><i class="icon-thumbs-up"></i>&nbsp Contas a Receber</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #999999;" href="<c:url value="/movimentofinanceiro_filtros/C"/>">Lançamentos de Crédito</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Liquidação de Crédito em Grupo</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Negociação de Crédito</a></li>
                                    <li class="divider"></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Emitir Boletos de Cobrança</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Gerar Arquivo de Remessa</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Processar Arquivo de Retorno</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-hdd"></i>&nbsp Conta Corrente</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Lançamento de Tarifas Bancárias</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Extrato de Conta Corrente</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Conciliação Bancária</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Extrato Conciliado</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                    <div class="btn-group">
                        <button class="btn dropdown-toggle" data-toggle="dropdown" style="color: #dd1144; font-size:12px;">Fiscal
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li><a style="color: #dd1144;" href="<c:url value=""/>">Emitir Nota Fiscal de Pedidos Faturados</a></li>
                            <li class="divider"></li>
                            <li><a style="color: #dd1144;" href="<c:url value=""/>">Transmitir Nota Fiscal</a></li>
                            <li><a style="color: #dd1144;" href="<c:url value=""/>">Cancelar Nota Fiscal</a></li>
                            <li><a style="color: #dd1144;" href="<c:url value=""/>">Inutilizar Nota Fiscal</a></li>
                            <li><a style="color: #dd1144;" href="<c:url value=""/>">Consultar Notas Emitidas</a></li>
                            <li><a style="color: #dd1144;" href="<c:url value=""/>">ReImprimir Nota Fiscal</a></li>
                        </ul>
                    </div>

                    <div class="btn-group">
                        <button class="btn dropdown-toggle" data-toggle="dropdown" style="color: #dd1144; font-size:12px;">Relatórios
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-user"></i>&nbsp Cadastrais</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Pessoas</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Produtos</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Centro de Custo</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Plano de Contas</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-globe"></i>&nbsp Estoque</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Movimentação de Produotos</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Estoque</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-globe"></i>&nbsp Pedidos</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Pedidos de Compras</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Pedidos de  Vendas</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Pedidos Diversos</a></li>
                                </ul>
                            </li>
                        </ul>
                    </div>

                    <div class="btn-group">
                        <button class="btn dropdown-toggle" data-toggle="dropdown" style="font-size:12px;">Sistema
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #999999"><i class="icon-user"></i>&nbsp Usuários</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #000000;" href="<c:url value="/usuario_lista"/>">Cadastrar Usuário</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Trocar Senha</a></li>
                                    <li><a style="color: #000000;" href="<c:url value="/grupousuario_lista"/>">Cadastrar Grupos de Usuários</a></li>
                                </ul>
                            </li>
                            <li class="dropdown-submenu">
                                <a class="text-left" style="color: #dd1144"><i class="icon-globe"></i>&nbsp Configurações</a>
                                <ul class="dropdown-menu">
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Gerais</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Financeira</a></li>
                                    <li><a style="color: #dd1144;" href="<c:url value=""/>">Faturamento</a></li>
                                </ul>
                            </li>
                            <li class="divider"></li>
                            <li><a style="color: #000000;" href="<c:url value="/logout"/>">Sair</a></li>
                        </ul>
                    </div>
                </div>
                <div class="span2" style="text-align: center;">
                    <a class="label" style="font-size: 14px; margin-top:10px;">Sistema de Gestão Comercial</a>
                </div>
                <div class="span1" style="text-align: center;">
                    <img src="/static/img/Logo.jpg" style="width: 80px;"></i>
                </div>
            </div>
        </div>
    </div>
</header>

<script>

</script>

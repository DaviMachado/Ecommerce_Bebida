package com.les.bebida.core.fachada.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.les.bebida.core.dao.IDAO;
import com.les.bebida.core.dao.impl.ClienteDAO;
import com.les.bebida.core.dao.impl.CupomDAO;
import com.les.bebida.core.dao.impl.EnderecoDAO;
import com.les.bebida.core.dao.impl.EstoqueDAO;
import com.les.bebida.core.dao.impl.ItemCarrinhoDAO;
import com.les.bebida.core.dao.impl.LoginDAO;
import com.les.bebida.core.dao.impl.PedidoDAO;
import com.les.bebida.core.dao.impl.PedidoTrocaDAO;
import com.les.bebida.core.dao.impl.ItemPedidoDAO;
import com.les.bebida.core.dao.impl.ProdutoDAO;
import com.les.bebida.core.dao.impl.VerificaCupomDAO;
import com.les.bebida.core.dao.impl.CarrinhoDAO;
import com.les.bebida.core.dao.impl.CartaoDeCreditoDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Estoque;
import com.les.bebida.core.dominio.ItemCarrinho;
import com.les.bebida.core.dominio.ItemPedido;
import com.les.bebida.core.dominio.Pedido;
import com.les.bebida.core.dominio.PedidoTroca;
import com.les.bebida.core.dominio.Produto;
import com.les.bebida.core.dominio.Carrinho;
import com.les.bebida.core.dominio.CartaoDeCredito;
import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.Cupom;
import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.dominio.VerificaCupom;
import com.les.bebida.core.fachada.IFachada;
import com.les.bebida.core.strategy.IStrategy;
import com.les.bebida.core.strategy.impl.ValidarBairro;
import com.les.bebida.core.strategy.impl.ValidarBandeiraCartao;
import com.les.bebida.core.strategy.impl.ValidarCEP;
import com.les.bebida.core.strategy.impl.ValidarCPF;
import com.les.bebida.core.strategy.impl.ValidarCartaoPedido;
import com.les.bebida.core.strategy.impl.ValidarCategoriaProduto;
import com.les.bebida.core.strategy.impl.ValidarCidade;
import com.les.bebida.core.strategy.impl.ValidarCodigoSegurancaCartao;
import com.les.bebida.core.strategy.impl.ValidarCodigoSistemaCliente;
import com.les.bebida.core.strategy.impl.ValidarCupom;
import com.les.bebida.core.strategy.impl.ValidarDataCadastro;
import com.les.bebida.core.strategy.impl.ValidarDataEntradaSaidaEstoque;
import com.les.bebida.core.strategy.impl.ValidarDataNascimento;
import com.les.bebida.core.strategy.impl.ValidarDataValidadeCartao;
import com.les.bebida.core.strategy.impl.ValidarDescricaoProduto;
import com.les.bebida.core.strategy.impl.ValidarEnderecoPedido;
import com.les.bebida.core.strategy.impl.ValidarEntradaEstoque;
import com.les.bebida.core.strategy.impl.ValidarEstado;
import com.les.bebida.core.strategy.impl.ValidarExistenciaLogin;
import com.les.bebida.core.strategy.impl.ValidarExistenciaLoginAndSenha;
import com.les.bebida.core.strategy.impl.ValidarFormaDePagamento;
import com.les.bebida.core.strategy.impl.ValidarFornecedorEstoque;
import com.les.bebida.core.strategy.impl.ValidarFotoDetalheProduto;
import com.les.bebida.core.strategy.impl.ValidarFotoProduto;
import com.les.bebida.core.strategy.impl.ValidarGrupoPrecificacaoProduto;
import com.les.bebida.core.strategy.impl.ValidarStatusUsuario;
import com.les.bebida.core.strategy.impl.ValidarLogin;
import com.les.bebida.core.strategy.impl.ValidarLoginCliente;
import com.les.bebida.core.strategy.impl.ValidarLogradouro;
import com.les.bebida.core.strategy.impl.ValidarNome;
import com.les.bebida.core.strategy.impl.ValidarNomeCartao;
import com.les.bebida.core.strategy.impl.ValidarNomeLogin;
import com.les.bebida.core.strategy.impl.ValidarNomeProduto;
import com.les.bebida.core.strategy.impl.ValidarNumeroCartao;
import com.les.bebida.core.strategy.impl.ValidarNumeroEndereco;
import com.les.bebida.core.strategy.impl.ValidarPais;
import com.les.bebida.core.strategy.impl.ValidarPrecoCompraProduto;
import com.les.bebida.core.strategy.impl.ValidarPrecoVendaProduto;
import com.les.bebida.core.strategy.impl.ValidarProdutoEstoque;
import com.les.bebida.core.strategy.impl.ValidarQuantidadeEstoque;
import com.les.bebida.core.strategy.impl.ValidarQuantidadeProduto;
import com.les.bebida.core.strategy.impl.ValidarQuantidadeSelecionada;
import com.les.bebida.core.strategy.impl.ValidarSaidaEstoque;
import com.les.bebida.core.strategy.impl.ValidarSenha;
import com.les.bebida.core.strategy.impl.ValidarSenhaIgual;
import com.les.bebida.core.strategy.impl.ValidarSenhaIgualCliente;
import com.les.bebida.core.strategy.impl.ValidarSexo;
import com.les.bebida.core.strategy.impl.ValidarStatusCliente;
import com.les.bebida.core.strategy.impl.ValidarStatusPedido;
import com.les.bebida.core.strategy.impl.ValidarStatusPreferencialCartao;
import com.les.bebida.core.strategy.impl.ValidarStatusProduto;
import com.les.bebida.core.strategy.impl.ValidarTipoCliente;
import com.les.bebida.core.strategy.impl.ValidarTipoEndereco;
import com.les.bebida.core.strategy.impl.ValidarTipoEstoque;
import com.les.bebida.core.strategy.impl.ValidarTipoResidencia;
import com.les.bebida.core.strategy.impl.ValidarTipoUsuario;
import com.les.bebida.core.strategy.impl.ValidarTotalPedido;
import com.les.bebida.core.strategy.impl.ValidarValorCustoEstoque;

/**
 * Classe Fachada
 * 
 * @author Davi Rodrigues
 * @date 15/10/2021
 */
public class Fachada implements IFachada {

	private Resultado resultado;
	private static Map<String, IDAO> daos;

	/* ------------ Declaração de TODAS as Strategy's ------------ */
	ValidarStatusUsuario vStatusUsuario = new ValidarStatusUsuario();
	ValidarStatusCliente vStatusCliente = new ValidarStatusCliente();
	ValidarTipoCliente vTipoCliente = new ValidarTipoCliente();
	ValidarTipoUsuario vTipoUsuario = new ValidarTipoUsuario();
	ValidarLogin vLogin = new ValidarLogin();
	ValidarSenha vSenha = new ValidarSenha();
	ValidarSenhaIgual vSenhaIgual = new ValidarSenhaIgual();
	ValidarNome vNome = new ValidarNome();
	ValidarNomeLogin vNomeLogin = new ValidarNomeLogin();
	ValidarCPF vCPF = new ValidarCPF();
	ValidarDataNascimento vDataNascimento = new ValidarDataNascimento();
	ValidarSexo vSexo = new ValidarSexo();
	ValidarLoginCliente vLoginCliente = new ValidarLoginCliente();
	ValidarSenhaIgualCliente vSenhaIgualCliente = new ValidarSenhaIgualCliente();
	ValidarDataCadastro VDataCadastro = new ValidarDataCadastro();
	ValidarCodigoSistemaCliente vCodigoClienteSys = new ValidarCodigoSistemaCliente();
	ValidarExistenciaLogin vExistenciaLogin = new ValidarExistenciaLogin();
	ValidarExistenciaLoginAndSenha vExistenciaLoginAndSenha = new ValidarExistenciaLoginAndSenha();
	ValidarCEP vCEP = new ValidarCEP();
	ValidarEstado vEstado = new ValidarEstado();
	ValidarCidade vCidade = new ValidarCidade();
	ValidarLogradouro vLogradouro = new ValidarLogradouro();
	ValidarNumeroEndereco vNumeroEndereco = new ValidarNumeroEndereco();
	ValidarBairro vBairro = new ValidarBairro();
	ValidarTipoEndereco vTipoEndereco = new ValidarTipoEndereco();
	ValidarTipoResidencia vTipoResidencia = new ValidarTipoResidencia();
	ValidarPais vPais = new ValidarPais();
	ValidarNomeCartao vNomeCartao = new ValidarNomeCartao();
	ValidarNumeroCartao vNumeroCartao = new ValidarNumeroCartao();
	ValidarDataValidadeCartao vDataValidadeCartao = new ValidarDataValidadeCartao();
	ValidarCodigoSegurancaCartao vCodigoSegurancaCartao = new ValidarCodigoSegurancaCartao();
	ValidarBandeiraCartao vBandeiraCartao = new ValidarBandeiraCartao();
	ValidarStatusPreferencialCartao vStatusPreferencialCartao = new ValidarStatusPreferencialCartao();
	ValidarQuantidadeSelecionada vQuantidadeSelecionada = new ValidarQuantidadeSelecionada();
	ValidarNomeProduto vNomeProduto = new ValidarNomeProduto();
	ValidarDescricaoProduto vDescricaoProduto = new ValidarDescricaoProduto();
	ValidarCategoriaProduto vCategoriaProduto = new ValidarCategoriaProduto();
	ValidarPrecoCompraProduto vPrecoCompraProduto = new ValidarPrecoCompraProduto();
	ValidarPrecoVendaProduto vPrecoVendaProduto = new ValidarPrecoVendaProduto();
	ValidarQuantidadeProduto vQuantidadeProduto = new ValidarQuantidadeProduto();
	ValidarGrupoPrecificacaoProduto vGrupoPrecificacaoProduto = new ValidarGrupoPrecificacaoProduto();
	ValidarFotoProduto vFotoProduto = new ValidarFotoProduto();
	ValidarFotoDetalheProduto vFotoDetalheProduto = new ValidarFotoDetalheProduto();
	ValidarStatusProduto vStatusProduto = new ValidarStatusProduto();
	ValidarProdutoEstoque vProdutoEstoque = new ValidarProdutoEstoque();
	ValidarTipoEstoque vTipoEstoque = new ValidarTipoEstoque();
	ValidarQuantidadeEstoque vQuantidadeEstoque = new ValidarQuantidadeEstoque();
	ValidarValorCustoEstoque vValorCustoEstoque = new ValidarValorCustoEstoque();
	ValidarFornecedorEstoque vFornecedorEstoque = new ValidarFornecedorEstoque();
	ValidarDataEntradaSaidaEstoque vDataEntradaSaidaEstoque = new ValidarDataEntradaSaidaEstoque();
	ValidarEntradaEstoque vEntradaEstoque = new ValidarEntradaEstoque();
	ValidarSaidaEstoque vSaidaEstoque = new ValidarSaidaEstoque();
	ValidarStatusPedido vStatusPedido = new ValidarStatusPedido();
	ValidarTotalPedido vTotalPedido = new ValidarTotalPedido();
	ValidarEnderecoPedido vEnderecoPedido = new ValidarEnderecoPedido();
	ValidarCartaoPedido vCartaoPedido = new ValidarCartaoPedido();
	ValidarCupom vCupom = new ValidarCupom();
	ValidarFormaDePagamento vFormaDePagamentoPedido = new ValidarFormaDePagamento();
	/* ------------------------------------------------------------ */
	
	/* ------------ Declaração das Listas de Strategy's dos Dominios ------------ */
	/* ------------ SALVAR ------------ */
	List<IStrategy> regrasSalvarCliente = new ArrayList<>();
	List<IStrategy> regrasSalvarEndereco = new ArrayList<>();
	List<IStrategy> regrasSalvarLogin = new ArrayList<>();
	List<IStrategy> regrasSalvarCartaoDeCredito = new ArrayList<>();
	List<IStrategy> regrasSalvarProduto = new ArrayList<>();
	List<IStrategy> regrasSalvarEstoque = new ArrayList<>();
	List<IStrategy> regrasSalvarItemCarrinho = new ArrayList<>();
	List<IStrategy> regrasSalvarCarrinho = new ArrayList<>();
	List<IStrategy> regrasSalvarPedido = new ArrayList<>();
	List<IStrategy> regrasSalvarCupom = new ArrayList<>();
	List<IStrategy> regrasSalvarPedidoTroca = new ArrayList<>();
	/* ------------ CONSULTAR ------------ */
	List<IStrategy> regrasConsultarCliente = new ArrayList<>();
	List<IStrategy> regrasConsultarEndereco = new ArrayList<>();
	List<IStrategy> regrasConsultarLogin = new ArrayList<>();
	List<IStrategy> regrasConsultarCartaoDeCredito = new ArrayList<>();
	List<IStrategy> regrasConsultarProduto = new ArrayList<>();
	List<IStrategy> regrasConsultarEstoque = new ArrayList<>();
	List<IStrategy> regrasConsultarItemPedido = new ArrayList<>();
	List<IStrategy> regrasConsultarCupom = new ArrayList<>();
	List<IStrategy> regrasConsultarVerificaCupom = new ArrayList<>();
	List<IStrategy> regrasConsultarPedidoTroca = new ArrayList<>();
	/* ------------ ALTERAR ------------ */
	List<IStrategy> regrasAlterarCliente = new ArrayList<>();
	List<IStrategy> regrasAlterarEndereco = new ArrayList<>();
	List<IStrategy> regrasAlterarLogin = new ArrayList<>();
	List<IStrategy> regrasAlterarCartaoDeCredito = new ArrayList<>();
	List<IStrategy> regrasAlterarProduto = new ArrayList<>();
	List<IStrategy> regrasAlterarCarrinho = new ArrayList<>();
	List<IStrategy> regrasAlterarCupom = new ArrayList<>();
	List<IStrategy> regrasAlterarPedidoTroca = new ArrayList<>();
	/* ------------ EXCLUIR ------------ */
	List<IStrategy> regrasExcluirCliente = new ArrayList<>();
	List<IStrategy> regrasExcluirEndereco = new ArrayList<>();
	List<IStrategy> regrasExcluirLogin = new ArrayList<>();
	List<IStrategy> regrasExcluirCartaoDeCredito = new ArrayList<>();
	List<IStrategy> regrasExcluirProduto = new ArrayList<>();
	List<IStrategy> regrasExcluirCarrinho = new ArrayList<>();
	List<IStrategy> regrasExcluirCupom = new ArrayList<>();
	/* -------------------------------------------------------------------------- */
	
	/* ------------ Declaração dos MAP's das Regras de Negócios dos Dominios ------------ */
	Map<String, List<IStrategy>> regrasCliente = new HashMap<>();
	Map<String, List<IStrategy>> regrasEndereco = new HashMap<>();
	Map<String, List<IStrategy>> regrasLogin = new HashMap<>();
	Map<String, List<IStrategy>> regrasCartaoDeCredito = new HashMap<>();
	Map<String, List<IStrategy>> regrasProduto = new HashMap<>();
	Map<String, List<IStrategy>> regrasEstoque = new HashMap<>();
	Map<String, List<IStrategy>> regrasItemCarrinho = new HashMap<>();
	Map<String, List<IStrategy>> regrasCarrinho = new HashMap<>();
	Map<String, List<IStrategy>> regrasPedido = new HashMap<>();
	Map<String, List<IStrategy>> regrasItemPedido = new HashMap<>();
	Map<String, List<IStrategy>> regrasCupom = new HashMap<>();
	Map<String, List<IStrategy>> regrasVerificaCupom = new HashMap<>();
	Map<String, List<IStrategy>> regrasPedidoTroca = new HashMap<>();
	/* ----------------------------------------------------------------------------------- */
	
	/* ------------ Declaração da Regra de Negócio Geral ------------ */
	Map<String, Map<String, List<IStrategy>>> regrasGeral = new HashMap<>();
	/* --------------------------------------------------------------- */

	// Construtor da Fachada
	public Fachada() {
		// Mapa dos DAO's
		daos = new HashMap<String, IDAO>();

		// Criando instancias dos DAOS a serem utilizados,
		// adicionando cada dado no MAP indexado pelo nome da classe
		daos.put(Cliente.class.getName(), new ClienteDAO());
		daos.put(Endereco.class.getName(), new EnderecoDAO());
		daos.put(Usuario.class.getName(), new LoginDAO());
		daos.put(CartaoDeCredito.class.getName(), new CartaoDeCreditoDAO());
		daos.put(Produto.class.getName(), new ProdutoDAO());
		daos.put(Estoque.class.getName(), new EstoqueDAO());
		daos.put(ItemCarrinho.class.getName(), new ItemCarrinhoDAO());
		daos.put(Carrinho.class.getName(), new CarrinhoDAO());
		daos.put(Pedido.class.getName(), new PedidoDAO());
		daos.put(ItemPedido.class.getName(), new ItemPedidoDAO());
		daos.put(Cupom.class.getName(), new CupomDAO());
		daos.put(VerificaCupom.class.getName(), new VerificaCupomDAO());
		daos.put(PedidoTroca.class.getName(), new PedidoTrocaDAO());
		
		/* ----- Adicionando as Strategy's na lista do Cliente ----- */
		/* ----- SALVAR ----- */
		regrasSalvarCliente.add(vNome);
		regrasSalvarCliente.add(vCPF);
		regrasSalvarCliente.add(vDataNascimento);
		regrasSalvarCliente.add(vSexo);
		regrasSalvarCliente.add(vStatusCliente);
		regrasSalvarCliente.add(vLoginCliente);
		regrasSalvarCliente.add(vSenhaIgualCliente);
		regrasSalvarCliente.add(VDataCadastro);
		regrasSalvarCliente.add(vTipoCliente);
		/* ----- ALTERAR ----- */
		regrasAlterarCliente.add(vNome);
		regrasAlterarCliente.add(vCPF);
		regrasAlterarCliente.add(vDataNascimento);
		regrasAlterarCliente.add(vSexo);
		regrasAlterarCliente.add(vStatusCliente);
		regrasAlterarCliente.add(vLoginCliente);
		regrasAlterarCliente.add(vSenhaIgualCliente);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Endereço ----- */
		/* ----- SALVAR ----- */
		regrasSalvarEndereco.add(vCEP);
		regrasSalvarEndereco.add(vEstado);
		regrasSalvarEndereco.add(vCidade);
		regrasSalvarEndereco.add(vLogradouro);
		regrasSalvarEndereco.add(vNumeroEndereco);
		regrasSalvarEndereco.add(vBairro);
		regrasSalvarEndereco.add(vTipoEndereco);
		regrasSalvarEndereco.add(vTipoResidencia);
		regrasSalvarEndereco.add(vPais);
		regrasSalvarEndereco.add(VDataCadastro);
		/* ----- ALTERAR ----- */
		regrasAlterarEndereco.add(vCEP);
		regrasAlterarEndereco.add(vEstado);
		regrasAlterarEndereco.add(vCidade);
		regrasAlterarEndereco.add(vLogradouro);
		regrasAlterarEndereco.add(vNumeroEndereco);
		regrasAlterarEndereco.add(vBairro);
		regrasAlterarEndereco.add(vTipoEndereco);
		regrasAlterarEndereco.add(vTipoResidencia);
		regrasAlterarEndereco.add(vPais);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Login ----- */
		/* ----- SALVAR ----- */
		regrasSalvarLogin.add(vNomeLogin);
		regrasSalvarLogin.add(vLogin);
		regrasSalvarLogin.add(vSenhaIgual);
		regrasSalvarLogin.add(vExistenciaLogin);
		regrasSalvarLogin.add(vStatusUsuario);
		regrasSalvarLogin.add(VDataCadastro);
		regrasSalvarLogin.add(vTipoUsuario);
		/* ----- CONSULTAR ----- */
		regrasConsultarLogin.add(vLogin);
		regrasConsultarLogin.add(vSenha);
		regrasConsultarLogin.add(vExistenciaLoginAndSenha);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Cartao de Credito ----- */
		/* ----- SALVAR ----- */
		regrasSalvarCartaoDeCredito.add(vNomeCartao);
		regrasSalvarCartaoDeCredito.add(vNumeroCartao);
		regrasSalvarCartaoDeCredito.add(vDataValidadeCartao);
		regrasSalvarCartaoDeCredito.add(vCodigoSegurancaCartao);
		regrasSalvarCartaoDeCredito.add(vBandeiraCartao);
		regrasSalvarCartaoDeCredito.add(vStatusPreferencialCartao);
		regrasSalvarCartaoDeCredito.add(VDataCadastro);
		/* ----- ALTERAR ----- */
		regrasAlterarCartaoDeCredito.add(vNomeCartao);
		regrasAlterarCartaoDeCredito.add(vNumeroCartao);
		regrasAlterarCartaoDeCredito.add(vDataValidadeCartao);
		regrasAlterarCartaoDeCredito.add(vCodigoSegurancaCartao);
		regrasAlterarCartaoDeCredito.add(vBandeiraCartao);
		regrasAlterarCartaoDeCredito.add(vStatusPreferencialCartao);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Produto ----- */
		/* ----- SALVAR ----- */
		regrasSalvarProduto.add(vNomeProduto);
		regrasSalvarProduto.add(vDescricaoProduto);
		regrasSalvarProduto.add(vCategoriaProduto);
		regrasSalvarProduto.add(vPrecoCompraProduto);
		regrasSalvarProduto.add(vPrecoVendaProduto);
		regrasSalvarProduto.add(vQuantidadeProduto);
		regrasSalvarProduto.add(vGrupoPrecificacaoProduto);
		regrasSalvarProduto.add(vFotoProduto);
		regrasSalvarProduto.add(vFotoDetalheProduto);
		regrasSalvarProduto.add(vStatusProduto);
		regrasSalvarProduto.add(VDataCadastro);
		/* ----- ALTERAR ----- */
		regrasAlterarProduto.add(vNomeProduto);
		regrasAlterarProduto.add(vDescricaoProduto);
		regrasAlterarProduto.add(vCategoriaProduto);
		regrasAlterarProduto.add(vPrecoCompraProduto);
		regrasAlterarProduto.add(vPrecoVendaProduto);
		regrasAlterarProduto.add(vQuantidadeProduto);
		regrasAlterarProduto.add(vGrupoPrecificacaoProduto);
		regrasAlterarProduto.add(vFotoProduto);
		regrasAlterarProduto.add(vFotoDetalheProduto);
		regrasAlterarProduto.add(vStatusProduto);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Estoque ----- */
		/* ----- SALVAR ----- */
		regrasSalvarEstoque.add(vProdutoEstoque);
		regrasSalvarEstoque.add(vTipoEstoque);
		regrasSalvarEstoque.add(vQuantidadeEstoque);
		regrasSalvarEstoque.add(vValorCustoEstoque);
		regrasSalvarEstoque.add(vFornecedorEstoque);
		regrasSalvarEstoque.add(vDataEntradaSaidaEstoque);
		regrasSalvarEstoque.add(vEntradaEstoque);
		regrasSalvarEstoque.add(vSaidaEstoque);
		regrasSalvarEstoque.add(VDataCadastro);
		/* ----- CONSULTAR ----- */
		regrasConsultarEstoque.add(vProdutoEstoque);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Carrinho ----- */
		/* ----- SALVAR ----- */
		regrasSalvarCarrinho.add(vQuantidadeSelecionada);
		/* ----- ALTERAR ----- */
		regrasAlterarCarrinho.add(vQuantidadeSelecionada);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Pedido ----- */
		/* ----- SALVAR ----- */
		regrasSalvarPedido.add(vTotalPedido);
		regrasSalvarPedido.add(vEnderecoPedido);
		regrasSalvarPedido.add(vFormaDePagamentoPedido);
		regrasSalvarPedido.add(vCartaoPedido);
		regrasSalvarPedido.add(vStatusPedido);
		regrasSalvarPedido.add(VDataCadastro);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Cupom ----- */
		/* ----- SALVAR ----- */
		regrasSalvarCupom.add(VDataCadastro);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Verifica Cupom ----- */
		/* ----- CONSULTAR ----- */
		regrasConsultarVerificaCupom.add(vCupom);
		/* ---------------------------------------------------------- */

		/* ----- REGRAS DA ENTIDADE CLIENTE ----- */
		/* ----- SALVAR ----- */
		regrasCliente.put("SALVAR", regrasSalvarCliente);
		/* ----- CONSULTAR ----- */
		regrasCliente.put("CONSULTAR", regrasConsultarCliente);
		/* ----- ALTERAR ----- */
		regrasCliente.put("ALTERAR", regrasAlterarCliente);
		/* ----- EXCLUIR ----- */
		regrasCliente.put("EXCLUIR", regrasExcluirCliente);
		/* -------------------------------------- */

		/* ----- REGRAS DA ENTIDADE ENDEREÇO ----- */
		/* ----- SALVAR ----- */
		regrasEndereco.put("SALVAR", regrasSalvarEndereco);
		/* ----- CONSULTAR ----- */
		regrasEndereco.put("CONSULTAR", regrasConsultarEndereco);
		/* ----- ALTERAR ----- */
		regrasEndereco.put("ALTERAR", regrasAlterarEndereco);
		/* ----- EXCLUIR ----- */
		regrasEndereco.put("EXCLUIR", regrasExcluirEndereco);
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE LOGIN (Usuario) ----- */
		/* ----- SALVAR ----- */
		regrasLogin.put("SALVAR", regrasSalvarLogin);
		/* ----- CONSULTAR ----- */
		regrasLogin.put("CONSULTAR", regrasConsultarLogin);
		/* ----- ALTERAR ----- */
		regrasLogin.put("ALTERAR", regrasAlterarLogin);
		/* ----- EXCLUIR ----- */
		regrasLogin.put("EXCLUIR", regrasExcluirLogin);
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE CARTAO DE CREDITO ----- */
		/* ----- SALVAR ----- */
		regrasCartaoDeCredito.put("SALVAR", regrasSalvarCartaoDeCredito);
		/* ----- CONSULTAR ----- */
		regrasCartaoDeCredito.put("CONSULTAR", regrasConsultarCartaoDeCredito);
		/* ----- ALTERAR ----- */
		regrasCartaoDeCredito.put("ALTERAR", regrasAlterarCartaoDeCredito);
		/* ----- EXCLUIR ----- */
		regrasCartaoDeCredito.put("EXCLUIR", regrasExcluirCartaoDeCredito);
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE PRODUTO ----- */
		/* ----- SALVAR ----- */
		regrasProduto.put("SALVAR", regrasSalvarProduto);
		/* ----- CONSULTAR ----- */
		regrasProduto.put("CONSULTAR", regrasConsultarProduto);
		/* ----- ALTERAR ----- */
		regrasProduto.put("ALTERAR", regrasAlterarProduto);
		/* ----- EXCLUIR ----- */
		regrasProduto.put("EXCLUIR", regrasExcluirProduto);
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE ESTOQUE ----- */
		/* ----- SALVAR ----- */
		regrasEstoque.put("SALVAR", regrasSalvarEstoque);
		/* ----- CONSULTAR ----- */
		regrasEstoque.put("CONSULTAR", regrasConsultarEstoque);
		/* ----- ALTERAR ----- */
		/* ----- EXCLUIR ----- */
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE ITEM CARRINHO ----- */
		/* ----- SALVAR ----- */
		regrasItemCarrinho.put("SALVAR", regrasSalvarItemCarrinho);
		/* ----- CONSULTAR ----- */
		/* ----- ALTERAR ----- */
		/* ----- EXCLUIR ----- */
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE CARRINHO ----- */
		/* ----- SALVAR ----- */
		regrasCarrinho.put("SALVAR", regrasSalvarCarrinho);
		/* ----- CONSULTAR ----- */
		/* ----- ALTERAR ----- */
		regrasCarrinho.put("ALTERAR", regrasAlterarCarrinho);
		/* ----- EXCLUIR ----- */
		regrasCarrinho.put("EXCLUIR", regrasExcluirCarrinho);
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE PEDIDO ----- */
		/* ----- SALVAR ----- */
		regrasPedido.put("SALVAR", regrasSalvarPedido);
		/* ----- CONSULTAR ----- */
		/* ----- ALTERAR ----- */
		/* ----- EXCLUIR ----- */
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE ITEM PEDIDO ----- */
		/* ----- SALVAR ----- */
		/* ----- CONSULTAR ----- */
		regrasItemPedido.put("CONSULTAR", regrasConsultarItemPedido);
		/* ----- ALTERAR ----- */
		/* ----- EXCLUIR ----- */
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE CUPOM ----- */
		/* ----- SALVAR ----- */
		regrasCupom.put("SALVAR", regrasSalvarCupom);
		/* ----- CONSULTAR ----- */
		regrasCupom.put("CONSULTAR", regrasConsultarCupom);
		/* ----- ALTERAR ----- */
		regrasCupom.put("ALTERAR", regrasAlterarCupom);
		/* ----- EXCLUIR ----- */
		regrasCupom.put("EXCLUIR", regrasExcluirCupom);
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE VERIFICA CUPOM ----- */
		/* ----- SALVAR ----- */
		/* ----- CONSULTAR ----- */
		regrasVerificaCupom.put("CONSULTAR", regrasConsultarVerificaCupom);
		/* ----- ALTERAR ----- */
		/* ----- EXCLUIR ----- */
		/* --------------------------------------- */
		
		/* ----- REGRAS DA ENTIDADE PEDIDO TROCA ----- */
		/* ----- SALVAR ----- */
		regrasPedidoTroca.put("SALVAR", regrasSalvarPedidoTroca);
		/* ----- CONSULTAR ----- */
		regrasPedidoTroca.put("CONSULTAR", regrasConsultarPedidoTroca);
		/* ----- ALTERAR ----- */
		regrasPedidoTroca.put("ALTERAR", regrasAlterarPedidoTroca);
		/* ----- EXCLUIR ----- */
		/* --------------------------------------- */
		
		/* ----- REGRAS GERAIS ----- */
		regrasGeral.put(Cliente.class.getName(), regrasCliente);
		regrasGeral.put(Endereco.class.getName(), regrasEndereco);
		regrasGeral.put(Usuario.class.getName(), regrasLogin);
		regrasGeral.put(CartaoDeCredito.class.getName(), regrasCartaoDeCredito);
		regrasGeral.put(Produto.class.getName(), regrasProduto);
		regrasGeral.put(Estoque.class.getName(), regrasEstoque);
		regrasGeral.put(ItemCarrinho.class.getName(), regrasItemCarrinho);
		regrasGeral.put(Carrinho.class.getName(), regrasCarrinho);
		regrasGeral.put(Pedido.class.getName(), regrasPedido);
		regrasGeral.put(ItemPedido.class.getName(), regrasItemPedido);
		regrasGeral.put(Cupom.class.getName(), regrasCupom);
		regrasGeral.put(VerificaCupom.class.getName(), regrasVerificaCupom);
		regrasGeral.put(PedidoTroca.class.getName(), regrasPedidoTroca);
		/* -------------------------- */
	}

	/*---SALVAR---*/
	@Override
	public Resultado salvar(EntidadeDominio entidade) {
		resultado = new Resultado();
		// retornar o nome do pacote com o nome da classe desta entidade de dominio
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "SALVAR");

		if (msg == null || msg == "") {
			// Obtém o DAO correspondente ao nome do pacote com o nome da classe,
			// que esta dentro do HashMap do "daos"
			IDAO dao = daos.get(nmClasse);
			try {
				dao.salvar(entidade);

				// cria uma lista para mostrar os clientes salvos
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("Não foi possível Salvar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*---ALTERAR---*/
	@Override
	public Resultado alterar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "ALTERAR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.alterar(entidade);

				// cria uma lista para mostrar os clientes alterados
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("Não foi possível Alterar o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*---EXCLUIR---*/
	@Override
	public Resultado excluir(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "EXCLUIR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				dao.excluir(entidade);

				// cria uma lista para mostrar os clientes excluidos
				List<EntidadeDominio> entidades = new ArrayList<EntidadeDominio>();
				entidades.add(entidade);
				resultado.setEntidades(entidades);
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("Não foi possível Excluir o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	/*---CONSULTAR---*/
	@Override
	public Resultado consultar(EntidadeDominio entidade) {
		resultado = new Resultado();
		String nmClasse = entidade.getClass().getName();

		String msg = executarRegras(entidade, "CONSULTAR");

		if (msg == null || msg == "") {
			IDAO dao = daos.get(nmClasse);
			try {
				resultado.setEntidades(dao.consultar(entidade));
			} catch (Exception e) {
				e.printStackTrace();
				resultado.setMensagem("Não foi possível Consulta o registro!");
			}
		} else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}

	// Método para executar as regras de negocio / Strategy
	private String executarRegras(EntidadeDominio entidade, String operacao) {
		String msg = "";

		// verifica o nome da classe para pegar o MAP de "regrasGeral"
		String nmClasse = entidade.getClass().getName();
		
		// com o nome da classe, ele pega o MAP com as suas respectivas regras de dominio (exemplo: regrasCliente),
		Map<String, List<IStrategy>> regrasDaEntidade = regrasGeral.get(nmClasse);
		
		// depois ele pega a "operação"que deseja ser realizada (exemplo: "salvar")
		List<IStrategy> regrasDaOperacao = regrasDaEntidade.get(operacao);
		
		// para cada "regra" em "regrasDaOperacao", ele irá chamar as Strategy's 
		// que estiver dentro da lista (exemplo: regrasSalvarCliente)
		for (IStrategy regra : regrasDaOperacao) {
			String resultado = regra.validar(entidade);
			if (resultado != null) {
				msg += "- " + resultado + "\n";
			}
		}

		return msg;
	}
}

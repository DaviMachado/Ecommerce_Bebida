package com.les.bebida.core.fachada.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.les.bebida.core.dao.IDAO;
import com.les.bebida.core.dao.impl.ClienteDAO;
import com.les.bebida.core.dao.impl.EnderecoDAO;
import com.les.bebida.core.dao.impl.LoginDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.core.fachada.IFachada;
import com.les.bebida.core.strategy.IStrategy;
import com.les.bebida.core.strategy.impl.ValidarCPF;
import com.les.bebida.core.strategy.impl.ValidarCodigoClienteSys;
import com.les.bebida.core.strategy.impl.ValidarDataNascimento;
import com.les.bebida.core.strategy.impl.ValidarExistenciaLogin;
import com.les.bebida.core.strategy.impl.ValidarExistenciaLoginAndSenha;
import com.les.bebida.core.strategy.impl.ValidarFlgAtivo;
import com.les.bebida.core.strategy.impl.ValidarLogin;
import com.les.bebida.core.strategy.impl.ValidarNome;
import com.les.bebida.core.strategy.impl.ValidarNomeLogin;
import com.les.bebida.core.strategy.impl.ValidarSenha;
import com.les.bebida.core.strategy.impl.ValidarSenhaIgual;

/**
 * Classe Fachada
 * 
 * @author Davi Rodrigues
 * @date 08/08/2021
 */
public class Fachada implements IFachada {

	private Resultado resultado;
	private static Map<String, IDAO> daos;

	/* ------------ Declaração de TODAS as Strategy's ------------ */
	ValidarFlgAtivo vFlgAtivo = new ValidarFlgAtivo();
	ValidarLogin vLogin = new ValidarLogin();
	ValidarSenha vSenha = new ValidarSenha();
	ValidarSenhaIgual vSenhaIgual = new ValidarSenhaIgual();
	ValidarNome vNome = new ValidarNome();
	ValidarNomeLogin vNomeLogin = new ValidarNomeLogin();
	ValidarCPF vCPF = new ValidarCPF();
	ValidarDataNascimento vDataNascimento = new ValidarDataNascimento();
	ValidarCodigoClienteSys vCodigoClienteSys = new ValidarCodigoClienteSys();
	ValidarExistenciaLogin vExistenciaLogin = new ValidarExistenciaLogin();
	ValidarExistenciaLoginAndSenha vExistenciaLoginAndSenha = new ValidarExistenciaLoginAndSenha();
	/* ------------------------------------------------------------ */
	
	/* ------------ Declaração das Listas de Strategy's dos Dominios ------------ */
	/* ------------ SALVAR ------------ */
	List<IStrategy> regrasSalvarCliente = new ArrayList<>();
	List<IStrategy> regrasSalvarEndereco = new ArrayList<>();
	List<IStrategy> regrasSalvarLogin = new ArrayList<>();
	/* ------------ CONSULTAR ------------ */
	List<IStrategy> regrasConsultarCliente = new ArrayList<>();
	List<IStrategy> regrasConsultarEndereco = new ArrayList<>();
	List<IStrategy> regrasConsultarLogin = new ArrayList<>();
	/* ------------ ALTERAR ------------ */
	List<IStrategy> regrasAlterarCliente = new ArrayList<>();
	List<IStrategy> regrasAlterarEndereco = new ArrayList<>();
	/* ------------ EXCLUIR ------------ */
	List<IStrategy> regrasExcluirCliente = new ArrayList<>();
	List<IStrategy> regrasExcluirEndereco = new ArrayList<>();
	/* -------------------------------------------------------------------------- */
	
	/* ------------ Declaração dos MAP's das Regras de Negócios dos Dominios ------------ */
	Map<String, List<IStrategy>> regrasCliente = new HashMap<>();
	Map<String, List<IStrategy>> regrasEndereco = new HashMap<>();
	Map<String, List<IStrategy>> regrasLogin = new HashMap<>();
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
		
		/* ----- Adicionando as Strategy's na lista do Cliente ----- */
		/* ----- SALVAR ----- */
		regrasSalvarCliente.add(vNome);
		regrasSalvarCliente.add(vCPF);
		regrasSalvarCliente.add(vDataNascimento);
		regrasSalvarCliente.add(vFlgAtivo);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Endereço ----- */
//		regrasSalvarEndereco.add(null);
		/* ---------------------------------------------------------- */
		
		/* ----- Adicionando as Strategy's na lista do Login ----- */
		/* ----- SALVAR ----- */
		regrasSalvarLogin.add(vNomeLogin);
		regrasSalvarLogin.add(vLogin);
		regrasSalvarLogin.add(vSenhaIgual);
		regrasSalvarLogin.add(vExistenciaLogin);
		/* ----- CONSULTAR ----- */
		regrasConsultarLogin.add(vLogin);
		regrasConsultarLogin.add(vSenha);
		regrasConsultarLogin.add(vExistenciaLoginAndSenha);
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
		/* --------------------------------------- */
		
		/* ----- REGRAS GERAIS ----- */
		regrasGeral.put(Cliente.class.getName(), regrasCliente);
		regrasGeral.put(Endereco.class.getName(), regrasEndereco);
		regrasGeral.put(Usuario.class.getName(), regrasLogin);
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

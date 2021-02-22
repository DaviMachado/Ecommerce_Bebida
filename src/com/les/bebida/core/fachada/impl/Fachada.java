package com.les.bebida.core.fachada.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.les.bebida.core.dao.IDAO;
import com.les.bebida.core.dao.impl.ClienteDAO;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.fachada.IFachada;
import com.les.bebida.core.strategy.impl.ValidarCPF;
import com.les.bebida.core.strategy.impl.ValidarCodigoClienteSys;
import com.les.bebida.core.strategy.impl.ValidarDataNascimento;
import com.les.bebida.core.strategy.impl.ValidarFlgAtivo;
import com.les.bebida.core.strategy.impl.ValidarLogin;
import com.les.bebida.core.strategy.impl.ValidarNome;
import com.les.bebida.core.strategy.impl.ValidarSenha;

/**
 * Classe Fachada
 * @author Davi Rodrigues
 * @date 21/02/2021
 */
public class Fachada implements IFachada {
	
	private Resultado resultado;
	private static Map<String, IDAO> daos;
	
	// Construtor da Fachada
	public Fachada() {
		// Mapa dos DAO's
		daos = new HashMap<String, IDAO>();
		
		// Criando instancias dos DAOS a serem utilizados,
		// adicionando cada dado no MAP indexado pelo nome da classe
		daos.put(Cliente.class.getName(), new ClienteDAO());
	}
	
	/* ------------ Declaração dos Strategy ------------ */
	ValidarFlgAtivo vFlgAtivo = new ValidarFlgAtivo();
	ValidarLogin vLogin = new ValidarLogin();
	ValidarSenha vSenha = new ValidarSenha();
	ValidarNome vNome = new ValidarNome();
	ValidarCPF vCPF = new ValidarCPF();
	ValidarDataNascimento vDataNascimento = new ValidarDataNascimento();
	ValidarCodigoClienteSys vCodigoClienteSys = new ValidarCodigoClienteSys();
	
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
		}
		else {
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
		}
		else {
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
		}
		else {
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
		}
		else {
			resultado.setMensagem(msg);
		}
		return resultado;
	}
	
	
	// Método para executar as regras de negocio / Strategy
	private String executarRegras (EntidadeDominio entidade, String operacao) {
		String msg = null;
		
		if (("CONSULTAR").equals(operacao)) {
			return msg;
		}
		else if (("SALVAR").equals(operacao)) {
			msg = vNome.validar(entidade, msg);
			msg = vCPF.validar(entidade, msg);
			msg = vDataNascimento.validar(entidade, msg);
			
			return msg;
		}
		else if (("ALTERAR").equals(operacao)) {
			msg = vNome.validar(entidade, msg);
			msg = vCPF.validar(entidade, msg);
			msg = vDataNascimento.validar(entidade, msg);
			
			return msg;
		}
		else if (("EXCLUIR").equals(operacao)) {
			return msg;
		}
		else {
			return msg;
		}
	}

}

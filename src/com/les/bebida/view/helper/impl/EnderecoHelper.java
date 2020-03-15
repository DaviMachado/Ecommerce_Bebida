package com.les.bebida.view.helper.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.les.bebida.core.dominio.Cliente;
import com.les.bebida.core.dominio.Endereco;
import com.les.bebida.core.dominio.EntidadeDominio;
import com.les.bebida.core.dominio.Resultado;
import com.les.bebida.core.dominio.Usuario;
import com.les.bebida.view.helper.IViewHelper;

public class EnderecoHelper implements IViewHelper {
	
	Endereco endereco = null;
	
	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		// Verifica qual operação do botão foi acionada
		String operacao = request.getParameter("operacao");
		
        String cep = null;
        String cidade = null;
        String logradouro = null;
        String numero = null;
        String bairro = null;
        String complemento = null;
        String estado = null;
		
		if (("CONSULTAR").equals(operacao)) {
			endereco = new Endereco();
		}
		
		else if (("SALVAR").equals(operacao)) {
			// Atributos da classe endereço
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			complemento = request.getParameter("complemento");
			estado = request.getParameter("estado");
			
			// Atribuindo os valores capturados do HTML para o endereço
			endereco.setCep(Integer.parseInt(cep));
			endereco.setCidade(cidade);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(Integer.parseInt(numero));
			endereco.setBairro(bairro);
			endereco.setComplemento(complemento);
			endereco.setEstado(estado);
		}
		
		else if (("ALTERAR").equals(operacao)) {
			// Atributos da classe endereço
			cep = request.getParameter("cep");
			cidade = request.getParameter("cidade");
			logradouro = request.getParameter("logradouro");
			numero = request.getParameter("numero");
			bairro = request.getParameter("bairro");
			complemento = request.getParameter("complemento");
			estado = request.getParameter("estado");
			
			// Atribuindo os valores capturados do HTML para o endereço
			endereco.setCep(Integer.parseInt(cep));
			endereco.setCidade(cidade);
			endereco.setLogradouro(logradouro);
			endereco.setNumero(Integer.parseInt(numero));
			endereco.setBairro(bairro);
			endereco.setComplemento(complemento);
			endereco.setEstado(estado);
		}
		
		else if (("EXCLUIR").equals(operacao)) {
			endereco = new Endereco();
			
			cep = request.getParameter("cep");
			
			endereco.setCep(Integer.parseInt(cep));
		}
		
		return endereco;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}

package br.com.encontroFacil.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.model.vo.EnderecoVO;
import br.com.encontroFacil.persistence.interfaces.IDaoContato;

@Service
public class ContatoService implements Serializable {

	private static final long serialVersionUID = 7216378092008210835L;
	
	@Autowired
	private IDaoContato daoContato;

	public void salvarContato(Contato contato){
		daoContato.salvar(contato);
	}
	
	public Contato recuperarContatoUsuario(Usuario usuario){
		if(!daoContato.recuperarContatoUsuario(usuario).isEmpty())
		{
			return daoContato.recuperarContatoUsuario(usuario).get(0);
		}
		return null;
	}

	public EnderecoVO buscarCep(Integer cep)
	{
		// TODO Método para consumir o webService dos correios e recuperar o endereço para o cep informado
		return null;
	}
	
}

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
	
	@Autowired
	private AuxiliarService auxService;

	public void salvarContato(Contato contato){
		daoContato.salvar(contato);
	}
	
	public Contato recuperarContatoUsuario(Usuario usuario){
		Contato contato = new Contato();
		if(!daoContato.recuperarContatoUsuario(usuario).isEmpty())
		{
			contato = daoContato.recuperarContatoUsuario(usuario).get(0);
		}
		return contato;
	}

	public EnderecoVO buscarCep(Integer cep)
	{
		return auxService.recuperarEndereco(cep);
	}
	
}

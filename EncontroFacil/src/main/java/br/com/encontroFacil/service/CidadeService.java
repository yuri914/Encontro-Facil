package br.com.encontroFacil.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encontroFacil.model.Cidade;
import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Estado;
import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.persistence.interfaces.IDaoCidade;

@Service
public class CidadeService implements Serializable {

	private static final long serialVersionUID = 7749217118646594952L;

	@Autowired
	private IDaoCidade daoCidade;

	@Autowired
	private ContatoService serviceContato;

	public List<Cidade> buscarListaCidades(Estado estado)
	{
		return daoCidade.buscarListaCidades(estado);
	}

	public Cidade recuperarCidadeUsuario(Usuario usuario)
	{
		Contato contatoUsuario = serviceContato.recuperarContatoUsuario(usuario);
		Cidade cidadeUsuario = contatoUsuario.getCidade();
		return cidadeUsuario;
	}
}

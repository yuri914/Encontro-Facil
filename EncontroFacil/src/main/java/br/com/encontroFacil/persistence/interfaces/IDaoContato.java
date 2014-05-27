package br.com.encontroFacil.persistence.interfaces;

import java.util.List;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.persistence.generico.GenericoDao;

public interface IDaoContato extends GenericoDao<Contato> {

	public List<Contato> recuperarContatoUsuario(Usuario usuario);
	
}

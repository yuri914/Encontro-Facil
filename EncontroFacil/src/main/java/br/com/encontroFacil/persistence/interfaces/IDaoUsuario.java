package br.com.encontroFacil.persistence.interfaces;

import java.util.List;

import br.com.encontroFacil.model.Cidade;
import br.com.encontroFacil.model.Estado;
import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.persistence.generico.GenericoDao;

public interface IDaoUsuario extends GenericoDao<Usuario> {

	public List<Usuario> buscarTodosGrid(Usuario usuario);
	public List<Usuario> autenticarUsuario(Usuario usuarioLogin);
	public List<Usuario> recuperarListaUsuarioFiltroBanco(String nomePesquisa,
	Cidade cidadePesquisa, Estado estadoPesquisa, Double distMaxima);
}

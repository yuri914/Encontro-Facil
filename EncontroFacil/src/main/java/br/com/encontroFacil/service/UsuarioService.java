package br.com.encontroFacil.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encontroFacil.model.Cidade;
import br.com.encontroFacil.model.Estado;
import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.persistence.interfaces.IDaoUsuario;

@Service("usuarioService")
public class UsuarioService implements Serializable {

	private static final long serialVersionUID = -1905789314329929056L;
	@Autowired
	private IDaoUsuario daoUsuario;
	
	public void salvarUsuario(Usuario usuario){
		daoUsuario.salvar(usuario);
	}
	
	public List<Usuario> buscarListaUsuarioBanco(Usuario usuarioLogado){
		return daoUsuario.buscarTodosGrid(usuarioLogado);
	}
	
	/**
	 * 
	 * Este método tem a função de chamar a camada DAO e buscar o usuario informado na base de dados,
	 * se houver o usuario, trará o usuario até a camada de visão, para ser anexado a sessão como usuario
	 * logado caso seja autenticado.
	 * 
	 * @param usuarioLogin Usuario para autenticação 
	 *
	 * @author Yuri Cavalcante
	 * @return Usuario autenticado ou não
	 *
	 */
	public Usuario autenticarUsuario(Usuario usuarioLogin) {
		Usuario usuarioEncontrado = null;
		List<Usuario> listaUsuario = daoUsuario.autenticarUsuario(usuarioLogin);
		if (listaUsuario.size() != 0){
			usuarioEncontrado = listaUsuario.get(0);
		}
		return usuarioEncontrado;
	}

	@SuppressWarnings("unused")
	public List<Usuario> recuperarListaUsuarioFiltrado(String nomePesquisa,
			Cidade cidadePesquisa, Estado estadoPesquisa, Double distMaxima) {
		
		List<Usuario> listaUsuariosFiltrados = new ArrayList<Usuario>();
		listaUsuariosFiltrados = daoUsuario.recuperarListaUsuarioFiltroBanco(
		nomePesquisa, cidadePesquisa, estadoPesquisa, distMaxima);
		
		return null;
	}
}

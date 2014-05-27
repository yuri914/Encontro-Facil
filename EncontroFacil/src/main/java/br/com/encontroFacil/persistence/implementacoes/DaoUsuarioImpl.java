package br.com.encontroFacil.persistence.implementacoes;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.encontroFacil.model.Cidade;
import br.com.encontroFacil.model.Estado;
import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.persistence.generico.JPAGenericoDao;
import br.com.encontroFacil.persistence.interfaces.IDaoUsuario;

@Repository
public class DaoUsuarioImpl extends JPAGenericoDao<Usuario> implements IDaoUsuario {

	public DaoUsuarioImpl() {
		super(Usuario.class);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodosGrid(Usuario usuarioLogado){
		String jpql = "FROM Usuario u where u.id <> ?";
		return (List<Usuario>) super.buscarPorJpql(jpql, usuarioLogado.getId());
	}
	
	/**
	 * Este método tem a função de verificar se há algum usuario com login e
	 * senha informados e retorna-lo.
	 * 
	 * @author Yuri Cavalcante
	 * @param usuarioLogin Objeto carregado com login e senha para autenticação
	 * @return Lista de Usuarios Encontrados
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> autenticarUsuario(Usuario usuarioLogin) {
		String jpql = " From Usuario u where u.login = ? and u.senha = ?";
		return (List<Usuario>) super.buscarPorJpql(jpql, usuarioLogin.getLogin(), usuarioLogin.getSenha());
	}

	/**
	 * Este método tem a função de retornar uma lista de usuarios encontrados
	 * com os filtros informados.
	 * 
	 * @param nomePesquisa Nome informado pelo usuario para pesquisa
	 * @param cidadePesquisa Cidade informada pelo usuario para pesquisa
	 * @param estadoPesquisa Estado informado pelo usuario para pesquisa
	 * @param distMaxima Distancia máxima entre usuarios para pesquisa
	 * 
	 * @author Yuri Cavalcante
	 * @return Lista de Usuarios Filtrados
	 */
	public List<Usuario> recuperarListaUsuarioFiltroBanco(String nomePesquisa, Cidade cidadePesquisa, Estado estadoPesquisa, Double distMaxima) {
		StringBuilder sb = new StringBuilder();
		sb.append(" From Usuario u where 1=1");

		if (nomePesquisa != null) {
			sb.append(" and u.nome = ");
			sb.append("%'" + nomePesquisa + "'%");
		}
		if (cidadePesquisa != null) {
			sb.append(" and Contato.usuario.id ");
		}
		return null;
	}
}

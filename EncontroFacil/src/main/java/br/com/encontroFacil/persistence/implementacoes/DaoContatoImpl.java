package br.com.encontroFacil.persistence.implementacoes;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Usuario;
import br.com.encontroFacil.persistence.generico.JPAGenericoDao;
import br.com.encontroFacil.persistence.interfaces.IDaoContato;

@Repository
public class DaoContatoImpl extends JPAGenericoDao<Contato> implements IDaoContato, Serializable {
	private static final long serialVersionUID = 1L;

	public DaoContatoImpl() {
		super(Contato.class);
	}

	@SuppressWarnings("unchecked")
	public List<Contato> recuperarContatoUsuario(Usuario usuario) {
		String jpql = "FROM Contato c WHERE c.usuario.id = ?";
		return (List<Contato>) super.buscarPorJpql(jpql, usuario.getId());
	}

}

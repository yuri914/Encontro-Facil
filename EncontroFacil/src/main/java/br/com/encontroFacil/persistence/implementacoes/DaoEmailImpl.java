package br.com.encontroFacil.persistence.implementacoes;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Email;
import br.com.encontroFacil.persistence.generico.JPAGenericoDao;
import br.com.encontroFacil.persistence.interfaces.IDaoEmail;

@Repository
public class DaoEmailImpl extends JPAGenericoDao<Email> implements IDaoEmail, Serializable {
	private static final long serialVersionUID = 1L;

	public DaoEmailImpl() {
		super(Email.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Email> buscarEmail(Contato contato)
	{
		String jpql = "FROM Email e WHERE e.contato.id = ?";
		return (List<Email>) super.buscarPorJpql(jpql, contato.getId());
	}

}

package br.com.encontroFacil.persistence.implementacoes;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Email;
import br.com.encontroFacil.model.Telefone;
import br.com.encontroFacil.persistence.generico.JPAGenericoDao;
import br.com.encontroFacil.persistence.interfaces.IDaoTelefone;

@Repository
public class DaoTelefoneImpl extends JPAGenericoDao<Telefone> implements IDaoTelefone {

	public DaoTelefoneImpl() {
		super(Telefone.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Telefone> buscarTelefones(Contato contato)
	{
		String jpql = "FROM Telefone t WHERE t.contato.id = ?";
		return (List<Telefone>) super.buscarPorJpql(jpql, contato.getId());
	}

}

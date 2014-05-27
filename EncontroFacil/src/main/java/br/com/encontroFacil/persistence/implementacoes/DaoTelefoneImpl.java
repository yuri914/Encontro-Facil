package br.com.encontroFacil.persistence.implementacoes;

import org.springframework.stereotype.Repository;

import br.com.encontroFacil.model.Telefone;
import br.com.encontroFacil.persistence.generico.JPAGenericoDao;
import br.com.encontroFacil.persistence.interfaces.IDaoTelefone;

@Repository
public class DaoTelefoneImpl extends JPAGenericoDao<Telefone> implements IDaoTelefone {

	public DaoTelefoneImpl() {
		super(Telefone.class);
	}

}

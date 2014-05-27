package br.com.encontroFacil.persistence.implementacoes;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.encontroFacil.model.Email;
import br.com.encontroFacil.persistence.generico.JPAGenericoDao;
import br.com.encontroFacil.persistence.interfaces.IDaoEmail;

@Repository
public class DaoEmailImpl extends JPAGenericoDao<Email> implements IDaoEmail, Serializable {
	private static final long serialVersionUID = 1L;

	public DaoEmailImpl() {
		super(Email.class);
	}

}

package br.com.encontroFacil.persistence.implementacoes;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import br.com.encontroFacil.model.Estado;
import br.com.encontroFacil.persistence.generico.JPAGenericoDao;
import br.com.encontroFacil.persistence.interfaces.IDaoEstado;

@Repository
public class DaoEstadoImpl extends JPAGenericoDao<Estado> implements IDaoEstado, Serializable {
	private static final long serialVersionUID = 1L;

	public DaoEstadoImpl() {
		super(Estado.class);
	}

}

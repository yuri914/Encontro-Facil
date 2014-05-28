package br.com.encontroFacil.persistence.implementacoes;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.encontroFacil.model.Cidade;
import br.com.encontroFacil.model.Estado;
import br.com.encontroFacil.persistence.generico.JPAGenericoDao;
import br.com.encontroFacil.persistence.interfaces.IDaoCidade;

@Repository
public class DaoCidadeImpl extends JPAGenericoDao<Cidade> implements IDaoCidade, Serializable {
	private static final long serialVersionUID = 1L;

	public DaoCidadeImpl() {
		super(Cidade.class);
	}

	@SuppressWarnings("unchecked")
	public List<Cidade> buscarListaCidades(Estado estado) {
		String jpql = "From Cidade c where c.estado.id = ?";
		return (List<Cidade>) super.buscarPorJpql(jpql, estado.getId());
	}

}

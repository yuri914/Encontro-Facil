package br.com.encontroFacil.persistence.interfaces;

import java.util.List;

import br.com.encontroFacil.model.Cidade;
import br.com.encontroFacil.model.Estado;
import br.com.encontroFacil.persistence.generico.GenericoDao;

public interface IDaoCidade extends GenericoDao<Cidade> {

	public List<Cidade> buscarListaCidadesBanco(Estado estado);
	
}

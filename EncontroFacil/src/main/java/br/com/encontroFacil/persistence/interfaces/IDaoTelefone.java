package br.com.encontroFacil.persistence.interfaces;

import java.util.List;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Telefone;
import br.com.encontroFacil.persistence.generico.GenericoDao;

public interface IDaoTelefone extends GenericoDao<Telefone> {

	List<Telefone> buscarTelefones(Contato contato);

}

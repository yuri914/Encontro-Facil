package br.com.encontroFacil.persistence.interfaces;

import java.util.List;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Email;
import br.com.encontroFacil.persistence.generico.GenericoDao;

public interface IDaoEmail extends GenericoDao<Email> {

	List<Email> buscarEmail(Contato contato);

}

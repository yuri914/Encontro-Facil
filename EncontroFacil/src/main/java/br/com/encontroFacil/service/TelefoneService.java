package br.com.encontroFacil.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Email;
import br.com.encontroFacil.model.Telefone;
import br.com.encontroFacil.persistence.interfaces.IDaoTelefone;

@Service
public class TelefoneService implements Serializable {

	private static final long serialVersionUID = 4249843387568654730L;
	@Autowired
	private IDaoTelefone daoTelefone;

	public void salvarTelefoneBanco(Telefone telefone)
	{
		daoTelefone.salvar(telefone);
	}
	
	public List<Telefone> buscarTelefones(Contato contato)
	{
		List<Telefone> telefones = new ArrayList<Telefone>();
		if(contato.getId() != null)
		{
			telefones = daoTelefone.buscarTelefones(contato);
		}
		
		if(telefones.isEmpty())
		{
			telefones.add(new Telefone(contato, true));
		}
		return telefones;
	}
}

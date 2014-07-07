package br.com.encontroFacil.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encontroFacil.model.Contato;
import br.com.encontroFacil.model.Email;
import br.com.encontroFacil.persistence.interfaces.IDaoEmail;

@Service
public class EmailService implements Serializable {

	private static final long serialVersionUID = 5605946694743226025L;
	
	@Autowired
	private IDaoEmail daoEmail;

	public void salvarEmailBanco(Email email)
	{
		daoEmail.salvar(email);
	}

	public List<Email> buscarEmails(Contato contato)
	{
		List<Email> emails = new ArrayList<Email>();
		if(contato.getId() != null)
		{
			emails = daoEmail.buscarEmail(contato);
		}
		
		if(emails.isEmpty())
		{
			emails.add(new Email(contato, true));
		}
		return emails;
	}
}

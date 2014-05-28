package br.com.encontroFacil.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.encontroFacil.model.Estado;
import br.com.encontroFacil.persistence.interfaces.IDaoEstado;

@Service
public class EstadoService implements Serializable {

	private static final long serialVersionUID = 6106548160374739189L;
	@Autowired
	private IDaoEstado daoEstado;

	public List<Estado> buscarListaEstados(){
		return daoEstado.buscarTodos();
	}
}

package br.com.encontroFacil.service;

import java.io.Serializable;

import org.springframework.stereotype.Service;

@Service
public class CidadeService implements Serializable {

	private static final long serialVersionUID = 7749217118646594952L;

//	@Autowired
//	private IDaoCidade daoCidade;
//
//	@Autowired
//	private ContatoService serviceContato;
//
//	public List<Cidade> buscarListaCidadesBanco(Estado estado)
//	{
//		return daoCidade.buscarListaCidadesBanco(estado);
//	}
//
//	public Cidade recuperarCidadeUsuario(Usuario usuario)
//	{
//		Contato contatoUsuario = serviceContato.recuperarContatoUsuario(usuario);
//		Cidade cidadeUsuario = contatoUsuario.getCidade();
//		return cidadeUsuario;
//	}
}

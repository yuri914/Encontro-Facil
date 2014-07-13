package br.com.encontroFacil;

import junit.framework.TestCase;
import br.com.encontroFacil.model.Cidade;
import br.com.encontroFacil.service.AuxiliarService;

public class CalculoDistanciaTest extends TestCase {

	public void testDivisao() {
		Cidade cidadeUsuarioGrid = new Cidade();
		cidadeUsuarioGrid.setLatitude(07.21);
		cidadeUsuarioGrid.setLongitude(39.02);
		Cidade cidadeUsuarioSessao = new Cidade();
		cidadeUsuarioSessao.setLatitude(03.43);
		cidadeUsuarioSessao.setLongitude(38.32);
		assertEquals(427.8759129854521, new AuxiliarService().calcularDistanciaKm(cidadeUsuarioGrid, cidadeUsuarioSessao));
	}
	
}

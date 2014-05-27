package br.com.encontroFacil.util.enumeration;

public enum Genero {

	M("Masculino"),
	F("Feminino");
	
	private String descricao;

	private Genero(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}

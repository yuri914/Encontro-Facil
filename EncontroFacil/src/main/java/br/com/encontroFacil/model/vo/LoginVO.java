package br.com.encontroFacil.model.vo;

public class LoginVO {

	private String senhaAntiga;
	private String novaSenha;
	private String confirmacaoNovaSenha;

	public String getSenhaAntiga()
	{
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga)
	{
		this.senhaAntiga = senhaAntiga;
	}

	public String getNovaSenha()
	{
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha)
	{
		this.novaSenha = novaSenha;
	}

	public String getConfirmacaoNovaSenha()
	{
		return confirmacaoNovaSenha;
	}

	public void setConfirmacaoNovaSenha(String confirmacaoNovaSenha)
	{
		this.confirmacaoNovaSenha = confirmacaoNovaSenha;
	}
}

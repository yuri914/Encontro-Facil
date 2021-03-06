package br.com.encontroFacil.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import br.com.encontroFacil.util.enumeration.Genero;

@Entity
@Table(name="tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -4891243863719691177L;

	@Id
	@SequenceGenerator(name="id_generator", sequenceName="id_seq", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="id_generator")
	@Column(name="id")
	private Integer id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="genero")
	@Enumerated(EnumType.STRING)
	private Genero genero;
	
	@Column(name="data_nascimento")
	private Date dataNascimento;
	
	@Column(name="cpf")
	private Long cpf;
	
	@Column(name="login")
	private String login;
	
	@Column(name="senha")
	private String senha;
	
	@Column(name="data_cadastro")
	private Date dataCadastro;

	@Transient
	private Integer idade;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Genero getGenero() {
		return genero;
	}

	public void setGenero(Genero genero) {
		this.genero = genero;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	/**
	 * @author Yuri Cavalcante
	 * Método responsavel por calcular a idade do usuário a partir da data de nascimento.
	 * @return Idade do usuário
	 */
	public Integer getIdade() {
		  Date dataHoje = new Date();    
	      Calendar cal = Calendar.getInstance();    
	          
	      cal.setTime(dataHoje);    
	      int day1 = cal.get(Calendar.DAY_OF_YEAR);    
	      int ano1 = cal.get(Calendar.YEAR);    
	          
	      cal.setTime(getDataNascimento());    
	      int day2 = cal.get(Calendar.DAY_OF_YEAR);    
	      int ano2 = cal.get(Calendar.YEAR);    
	          
	      int idade = ano1 - ano2;    
	          
	      if(day1 < day2)    
	         idade--;  
	             
	      return idade;    
	}
	
}

package br.com.tradeforce.tradeweb.model;


import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("P")
public class Promotor extends Usuario{
	
	private int idade;
	
	@ManyToOne(cascade={CascadeType.PERSIST},fetch = FetchType.EAGER)
	private Empresa empresa;
	
	@ManyToOne(cascade={CascadeType.PERSIST},fetch = FetchType.EAGER)
	private Localizacao localizacao;

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Localizacao getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(Localizacao localizacao) {
		this.localizacao = localizacao;
	}

	
}

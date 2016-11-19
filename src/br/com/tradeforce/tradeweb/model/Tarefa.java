package br.com.tradeforce.tradeweb.model;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Tarefa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToMany
	private List<Mercado> mercados;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Rota> rotas;
	
	@ManyToOne
	private Promotor promotor;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Mercado> getMercados() {
		return mercados;
	}

	public void setMercados(List<Mercado> mercados) {
		this.mercados = mercados;
	}

	public List<Rota> getRotas() {
		return rotas;
	}

	public void setRotas(List<Rota> rotas) {
		this.rotas = rotas;
	}

	public Promotor getPromotor() {
		return promotor;
	}

	public void setPromotor(Promotor promotor) {
		this.promotor = promotor;
	}

	
	
}

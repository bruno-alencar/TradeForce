package br.com.tradeforce.tradeweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Rota {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double preco;
	private int sequencia;
	
	@Fetch(FetchMode.SELECT)
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="instrucoes", joinColumns=@JoinColumn(name="rota_id"))
	private List<String> instrucoes = new ArrayList<String>();
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="polylines", joinColumns=@JoinColumn(name="rota_id"))
	@Column(length=5000)
	private List<String> polylines= new ArrayList<String>();
	
	
	public int getSequencia() {
		return sequencia;
	}
	public void setSequencia(int sequencia) {
		this.sequencia = sequencia;
	}
	public List<String> getInstrucoes() {
		return instrucoes;
	}
	public void setInstrucoes(List<String> instrucoes) {
		this.instrucoes = instrucoes;
	}
	public List<String> getPolylines() {
		return polylines;
	}
	public void setPolylines(List<String> polylines) {
		this.polylines = polylines;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}

	

}

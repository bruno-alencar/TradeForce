package br.com.tradeforce.tradeweb.model;

import java.util.Date;

public class Relatorio {
	private Date data;
	private Long idTarefa;
	private int quantidadeMercados;
	private Double valorTotal;
	
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Long getIdTarefa() {
		return idTarefa;
	}
	public void setIdTarefa(Long idTarefa) {
		this.idTarefa = idTarefa;
	}
	public int getQuantidadeMercados() {
		return quantidadeMercados;
	}
	public void setQuantidadeMercados(int quantidadeMercados) {
		this.quantidadeMercados = quantidadeMercados;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
}

package br.com.tradeforce.tradeweb.to;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.tradeforce.tradeweb.dao.MercadoDao;
import br.com.tradeforce.tradeweb.model.Mercado;

@Component
public class MercadoTo {
	
	@Autowired
	private MercadoDao mercadoDao;
	
	public Mercado consultarPorId(Long id) {
		Mercado mercado = mercadoDao.consultarPorId(id);
		return mercado;
	}
	
	public void inserir(Mercado mercado) {
		mercadoDao.inserir(mercado);
	}
	
	public List<Mercado> listar() {
		return mercadoDao.listar();
	}
	
	public void excluir(Long idMercado) {
		mercadoDao.excluir(idMercado);
	}
	
	public void alterar(Mercado mercado) {
		mercadoDao.alterar(mercado);
	}
}

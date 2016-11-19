package br.com.tradeforce.tradeweb.model;
import java.util.List;

import br.com.tradeforce.tradeweb.model.Mercado;
import br.com.tradeforce.tradeweb.model.Promotor;

public class Auxiliar {
		private Promotor promotor;
		private List<Mercado> mercados;

		public Promotor getPromotor() {
			return promotor;
		}

		public void setPromotor(Promotor promotor) {
			this.promotor = promotor;
		}

		public List<Mercado> getMercados() {
			return mercados;
		}

		public void setMercados(List<Mercado> mercados) {
			this.mercados = mercados;
		}

	}
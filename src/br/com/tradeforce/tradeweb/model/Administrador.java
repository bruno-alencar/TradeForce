package br.com.tradeforce.tradeweb.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Administrador extends Usuario {
private String veiculo;

}

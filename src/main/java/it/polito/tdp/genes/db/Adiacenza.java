package it.polito.tdp.genes.db;

public class Adiacenza {

	private Integer cromosoma1;
	private Integer cromosoma2;
	private Double peso;
	
	
	
	public Adiacenza(Integer cromosoma1, Integer cromosoma2, Double peso) {
		super();
		this.cromosoma1 = cromosoma1;
		this.cromosoma2 = cromosoma2;
		this.peso = peso;
	}
	public Integer getCromosoma1() {
		return cromosoma1;
	}
	public void setCromosoma1(Integer cromosoma1) {
		this.cromosoma1 = cromosoma1;
	}
	public Integer getCromosoma2() {
		return cromosoma2;
	}
	public void setCromosoma2(Integer cromosoma2) {
		this.cromosoma2 = cromosoma2;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
}

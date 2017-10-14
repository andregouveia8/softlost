package pt.softlost.model;

public class Sala {
	private String sala;

	
	//Construtor
	public Sala(String sala) {
		super();
		this.sala = sala;
	}


	
	
	//Getters e Setters
	
	public String getSala() {
		return sala;
	}


	public void setSala(String sala) {
		this.sala = sala;
	}




	@Override
	public String toString() {
		return this.sala;
	}

}

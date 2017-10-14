package pt.softlost.model;

public class Permissoes {

	
	
	private boolean registo = true;
	private boolean reclamacao =  true;
	private boolean importacao = true;
	private boolean listagens = true;
	private boolean doacoes = true ;
	private boolean configuracao = true;
	
	
	
	public Permissoes( boolean registo, boolean reclamacao, boolean importacao, boolean listagens,
			boolean doacoes, boolean configuracao) {
		super();
		this.registo = registo;
		this.reclamacao = reclamacao;
		this.importacao = importacao;
		this.listagens = listagens;
		this.doacoes = doacoes;
		this.configuracao = configuracao;
	}



	public boolean isRegisto() {
		return registo;
	}



	public void setRegisto(boolean registo) {
		this.registo = registo;
	}



	public boolean isReclamacao() {
		return reclamacao;
	}



	public void setReclamacao(boolean reclamacao) {
		this.reclamacao = reclamacao;
	}



	public boolean isImportacao() {
		return importacao;
	}



	public void setImportacao(boolean importacao) {
		this.importacao = importacao;
	}



	public boolean isListagens() {
		return listagens;
	}



	public void setListagens(boolean listagens) {
		this.listagens = listagens;
	}



	public boolean isDoacoes() {
		return doacoes;
	}



	public void setDoacoes(boolean doacoes) {
		this.doacoes = doacoes;
	}



	public boolean isConfiguracao() {
		return configuracao;
	}



	public void setConfiguracao(boolean configuracao) {
		this.configuracao = configuracao;
	}

}



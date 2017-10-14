package pt.softlost.model;

public class Instituicao {

	private String instituicao;
    private int codigo;
    
    
	

	public Instituicao(int codigo, String instituicao) {
		super();
		this.codigo = codigo;
		this.instituicao = instituicao;
		
	}

	public String getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(String instituicao) {
		this.instituicao = instituicao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String toString() {
		return this.instituicao;
	}
	
	
	
	
}

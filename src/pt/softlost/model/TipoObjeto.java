package pt.softlost.model;

public class TipoObjeto {
	
	@Override
	public String toString() {
		return this.tipoObjeto;
	}

	private String tipoObjeto;
	private int codigo;
	private Instituicao i;
    
	
    //Construtor
  
	public TipoObjeto(int codigo, String tipoObjeto,Instituicao i) {
		super();
		this.tipoObjeto = tipoObjeto;
		this.codigo = codigo;
		this.setI(i);
	}



	//Getters e Setters
    

	public String getTipoObjeto() {
		return tipoObjeto;
	}

	public void setTipoObjeto(String tipoObjeto) {
		this.tipoObjeto = tipoObjeto;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}



	public Instituicao getI() {
		return i;
	}



	public void setI(Instituicao i) {
		this.i = i;
	}
  
    

}

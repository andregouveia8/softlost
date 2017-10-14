package pt.softlost.model;

public class Registo {

	@Override
	public String toString() {
		return "Registo [codigo=" + codigo + ", nome=" + nome + ", email="
				+ email + ", data=" + data + ", hora=" + hora + ", cor=" + cor
				+ ", descri��o=" + descri��o + ", estado=" + estado + ", s="
				+ s + ", x=" + x + "]";
	}


	private int codigo;
	private String nome, email, data, hora, cor, descri��o;
	String estado;
	private Sala s;
	private TipoObjeto x;
	
	
	public Registo(int codigo, String nome, String email, Sala s, String data,String hora, TipoObjeto x, String cor, String estado, String descri��o) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.email = email;
		this.data = data;
		this.hora = hora;
		this.cor = cor;
		this.descri��o = descri��o;
		this.estado = estado;
		this.s = s;
		this.x = x;
	}


	//Getter e Setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getDescri��o() {
		return descri��o;
	}

	public void setDescri��o(String descri��o) {
		this.descri��o = descri��o;
	}



	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}


	public String getHora() {
		return hora;
	}


	public void setHora(String hora) {
		this.hora = hora;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public Sala getS() {
		return s;
	}


	public void setS(Sala s) {
		this.s = s;
	}


	public TipoObjeto getX() {
		return x;
	}


	public void setX(TipoObjeto x) {
		this.x = x;
	}
	
	
	
	
	 
	
	
	
	

}

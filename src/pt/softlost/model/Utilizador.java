package pt.softlost.model;

import java.util.ArrayList;

public class Utilizador {

	private String username;
	private String password;
	private String tipoUtilizador;
	
	public static ArrayList<Permissoes> permissoesAcesso = new ArrayList<Permissoes>();
	
	
	public Utilizador(String username, String password, String tipoUtilizador) {
		super();
		this.username = username;
		this.password = password;
		this.tipoUtilizador = tipoUtilizador;
	}

	//Getter e Setters
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getTipoUtilizador() {
		return tipoUtilizador;
	}

	public void setTipoUtilizador(String tipoUtilizador) {
		this.tipoUtilizador = tipoUtilizador;
	}

	public ArrayList<Permissoes> getPermissoesAcesso() {
		return permissoesAcesso;
	}

	public static void setPermissoesAcesso(ArrayList<Permissoes> permissoesAcesso) {
		Utilizador.permissoesAcesso = permissoesAcesso;
	}

	
	

	

	
	
}

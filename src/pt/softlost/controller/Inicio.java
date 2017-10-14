package pt.softlost.controller;

import java.io.IOException;
import pt.softlost.view.LoginFrame;

public class Inicio {

	public static void main(String[] args) throws IOException {
		
		
		
		
		//Carregar os dados do utilizador
		SoftLost.loadUtilizador();
		//Carregar Institui��es
		SoftLost.loadInstitui��es();
		//Carregar Salas
		SoftLost.loadSalas();
		//Carregar Tipos Objetoss
		SoftLost.loadTiposObjetos();
		//Carregar dados Registo
		SoftLost.loadDadosRegisto();
		//Carregar Doa��es
		SoftLost.loadDoa��es();		
		
		
		// 2. Chamar a primeira JFrame
		LoginFrame f = new LoginFrame();
		f.setVisible(true);
		
		
	}

}



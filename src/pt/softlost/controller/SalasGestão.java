 
package pt.softlost.controller;

import pt.softlost.model.Sala;

public class SalasGestão {

	public static boolean adicionarSala(String sala){
		
		
		// Verificar se a sala existe
		boolean res = true;
		for (Sala sala1 : SoftLost.salas) {
			if (sala1.getSala().equals(sala)) {
				res = false;
			}	
		}	
		//Se nao existir add a sala ao array salas
		if (res == true) {
			Sala s1 = new Sala(sala);
			SoftLost.salas.add(s1);
			
		}
		
		
		return res;
		
	}
	
	public static boolean removerSala(String sala){
		// Verificar se a sala existe
		boolean res = false;
		
		
		for (int i = 0; i < SoftLost.salas.size(); i++) {
			//se existir remove a sala do array 
			if (SoftLost.salas.get(i).getSala().equals(sala)) {
				SoftLost.salas.remove(i);
				res = true;
			}
			
		}
		
		return res;
		
	}
	
	public static boolean alterarSala(String sala,String novaSala){
		
		// Verificar se a sala existe e se existir...
		  boolean res = true;
		  
		  Sala s = new Sala(novaSala);
		  
		  for (int i = 0; i < SoftLost.salas.size(); i++) {
			  
			  if (SoftLost.salas.get(i).getSala().equals(novaSala)) {
					res = false;
				} 
			  
		  }
			  
			  if (res == true) {	
				for (int j = 0; j < SoftLost.salas.size(); j++) {                                                               
				if (SoftLost.salas.get(j).getSala().equals(sala)) {
					SoftLost.salas.set(j, s); //...altera o seu nome para o novo nome
					res = true;
				}
		  }
			  }
		  
		  
		  
		
	return res;
	}
	}

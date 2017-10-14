package pt.softlost.controller;

import java.io.IOException;

import pt.softlost.model.Registo;

public class DoaçõesGestão {
		
	//Doar objeto
		public static boolean doarObjeto(int codigo) throws IOException {
			boolean res = false;
			/*//Precorre o array 
			for (int i = 0; i < SoftLost.registos.size(); i++) {
			//Cria um Registo
				Registo reg = SoftLost.registos.get(i);
				 //se o codigo do registo = ao codigo selecionado
				if (reg.getCodigo() == codigo) {
					//remove de registos  e adiciona ao array doações
					SoftLost.registos.remove(i);
					SoftLost.doacoes.add(reg);
					res = true;
					
				}
			}	
				*/
			
			
			for (int i = 0; i < SoftLost.registos.size(); i++) {
				
				if (SoftLost.registos.get(i).getCodigo() == codigo) {
					//remove de registos  e adiciona ao array doações
					SoftLost.registos.remove(i);
					Registo reg = SoftLost.registos.get(i);
					SoftLost.doacoes.add(reg);
					res = true;
					
				}
			}
			
			
			
			return res;
	
}

}
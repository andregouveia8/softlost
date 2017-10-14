package pt.softlost.controller;

public class ReclamaçãoGestão {

	public static boolean reclamacarObjeto(int codigo) {
		boolean res = false;
		for (int i = 0; i < SoftLost.registos.size(); i++) {
			
			if (SoftLost.registos.get(i).getCodigo() == codigo) {
				SoftLost.registos.remove(i);
				res = true;
			}
		}	
				
		
				return res;
	}
	
}





package pt.softlost.controller;

import pt.softlost.model.Instituicao;
import pt.softlost.model.TipoObjeto;

public class TipoObjetoGestão {
	
	public static int buscarCodigoAnterior(){
		
		return SoftLost.tipos.get(SoftLost.tipos.size()-1).getCodigo()+1;
		
	}

	public static boolean adicionarObjeto(String to, Instituicao inst){
		boolean res = true;
		for (TipoObjeto tObjeto : SoftLost.tipos) {
			if (tObjeto.getTipoObjeto().equals(to)) {
				res = false;
			}
		}
		if (res == true) {
			int codigo = buscarCodigoAnterior();
			TipoObjeto t1 = new TipoObjeto(codigo, to, inst);
			SoftLost.tipos.add(t1);
		}
		return res;
	}
	
	public static boolean removerObjeto(String to){
		boolean res = false;
		// Verifica se o objeto exise, se sim vai remover o objeto do array
		for (int i = 0; i < SoftLost.tipos.size(); i++) {
			if (SoftLost.tipos.get(i).getTipoObjeto().equals(to)) {
				SoftLost.tipos.remove(i);
			}
		}
		
		return res;
		
	}
	
	public static boolean alterarObjeto(int cod, String to, Instituicao inst){
		boolean res = false;
			for (int i = 0; i < SoftLost.tipos.size(); i++) {
				TipoObjeto tipo = new TipoObjeto(cod,to,inst);
				if (SoftLost.tipos.get(i).getTipoObjeto().equals(to)) {
					SoftLost.tipos.set(i, tipo);
					res = true;
				}
			}
			
		return res;
	
}
}

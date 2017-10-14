package pt.softlost.controller;

import pt.softlost.model.Registo;
import pt.softlost.model.Sala;
import pt.softlost.model.TipoObjeto;

public class RegistoGestao {
	
	//Método Registo
	
			public static void registo(int cod, String nome, String email,  Object sala, String data, String hora, Object tipoObjeto,String cor, String estado, String descricao)
			{

				//Cria um objeto Sala e um objeto Tipo Objeto
				Sala s = (Sala) sala; 
				TipoObjeto to= (TipoObjeto) tipoObjeto;
				
				//Cria um novo Objeto Registo
				Registo r = new Registo(cod, nome,email,s,data, hora, to,cor,estado,descricao);
		     	
				//Adiciona o Objeto Registo ao Array Registos
				SoftLost.registos.add(r);
		     	
			}

	
			public static int buscarCodigoAnterior(){
				int numRegistos = SoftLost.registos.size();
				if( numRegistos == 0 )
					return 1;
				else				
					return SoftLost.registos.get(numRegistos-1).getCodigo()+1;
				
			}
			
			public static boolean matchesOnlyText(String text) {
			    return text.matches("[^\\d]+"); //Passa para o método matches a regex
			    //Se tiver número na string irá retornar falso
			    //Note o uso de duas \\, uma sendo obrigatória para servir de caracter de escape
			}
			

}

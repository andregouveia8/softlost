package pt.softlost.controller;

import java.io.File;
import java.util.Scanner;

import pt.softlost.model.Registo;
import pt.softlost.model.Sala;
import pt.softlost.model.TipoObjeto;

public class ImportacaoGestao {

	public static void importar(String nomeFicheiro) throws Exception{
		
		//Ordenar os campos
		File importacaoFile = new File(nomeFicheiro);
		
		Scanner inImportação = new Scanner (importacaoFile);
		//Ler linhas todas do ficheiro
		while(inImportação.hasNextLine()){
		
			String line = inImportação.nextLine();
			//Separa os dois campos
			String [] dadosImportacao = line.split("#");
			
			//Separa os campos do 1ºCampo do Split dadosImportaçao
			String [] dadosImportacaoData = dadosImportacao[0].split(" ");
			//Separa os campos do 2ºCampo do Split dadosImportaçao
			String [] dadosImportacaoResto = dadosImportacao[1].split(";");
			
			
			//Ordenação dos campos
			int cod = RegistoGestao.buscarCodigoAnterior();
			String data = dadosImportacaoData[0];
			String hora = dadosImportacaoData[1];
			String nome = dadosImportacaoResto[0];
			String email = dadosImportacaoResto[1];
			
			//Precorre salas para ver se existe
			Sala sala = null; 
			for (Sala s : SoftLost.salas) {
				if (s.getSala().equals(dadosImportacaoResto[2])) {
					sala = s;
				}
			}
			
			//Precorrer Tipo Objeto para ver se existe
			TipoObjeto tipoObjeto = null;
			for (TipoObjeto t  : SoftLost.tipos) {
				if (t.getCodigo() == Integer.parseInt(dadosImportacaoResto[3])) {						
					tipoObjeto = t;
				}
				
			}
			
			
			
			String cor=  dadosImportacaoResto[4];
			String estado = dadosImportacaoResto[5];
			String descricao= dadosImportacaoResto[6];

			//Criar objeto e adiciona ao array
			Registo r = new Registo(cod, nome,email,sala,data,hora, tipoObjeto,cor,estado,descricao);
	     	SoftLost.registos.add(r);


	     	
		}
				
		inImportação.close();
		
		
		
	}



}

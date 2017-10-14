package pt.softlost.controller;

import java.io.File;
import java.io.IOException;
import pt.softlost.model.Instituicao;

public class InstituiçõesGestão {

public static int buscarCodigoAnterior(){
	
		int cod = SoftLost.instituicoes.get(SoftLost.instituicoes.size()-1).getCodigo()+1;

		return cod;
		
	}

	public static boolean adicionarInstituicao(String inst1) throws IOException {
		
		boolean res = true;
		
		//Preocrre instituições
		for (Instituicao inst : SoftLost.instituicoes) {
			if (inst.getInstituicao().equals(inst1)) { 
				//Verifica se existe se existir retorna falso
				res = false;
			}
		}
		if (res == true) {
			//se nao existir
			
			//Cria uma e adiciona ao array
			int codigo = buscarCodigoAnterior();
			Instituicao instituicao = new Instituicao(codigo, inst1);
			SoftLost.instituicoes.add(instituicao);
			
			//cria um ficheiro com o nome da instituição dentro da pasta instituições
			File instituicoesPasta = new File("instituiçoes");
			File[] ficheirosLista = instituicoesPasta.listFiles();
			File novaInstituição = new File(instituicoesPasta + "/" + inst1 + ".txt");
			
			for (int i = 0; i < ficheirosLista.length; i++) {
				if (ficheirosLista[i].isFile()) {
					novaInstituição.createNewFile();
				}
				
				}
			
		}
		return res;
	}

	
	
	public static boolean removerInstituicao(String inst1){
		boolean res = false;
		// Verifica se a instituicao exise, se sim vai remover do array
		for (int i = 0; i < SoftLost.instituicoes.size(); i++) {
			if (SoftLost.instituicoes.get(i).getInstituicao().equals(inst1)) {
				SoftLost.instituicoes.remove(i);
			}
			
		}
		//depois de apagar apaga o ficheiro tb
		File instituicoesPasta = new File("instituiçoes");
		File[] ficheirosLista = instituicoesPasta.listFiles();
		File novaInstituição = new File(instituicoesPasta + "/" + inst1 + ".txt");
		
		for (int i = 0; i < ficheirosLista.length; i++) {
			if (ficheirosLista[i].isFile()) {
				novaInstituição.delete();
			}
		}
		return res;
		
	}
	
	
	public static boolean alterarInstituiçao(String inst1, String inst2){
		
		boolean res = true;
		for (int i = 0; i < SoftLost.instituicoes.size(); i++) {
			
			//Cria um objeto 
			Instituicao inst = new Instituicao(SoftLost.instituicoes.get(i).getCodigo(), inst2);
			//Se existir alguma instituição igual ele troca atraves do set
			if (SoftLost.instituicoes.get(i).getInstituicao().equals(inst1)) {

				SoftLost.instituicoes.set(i, inst);
			}
		}
		
		return res;
		
	}
}

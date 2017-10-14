package pt.softlost.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import pt.softlost.model.Instituicao;
import pt.softlost.model.Permissoes;
import pt.softlost.model.Registo;
import pt.softlost.model.Sala;
import pt.softlost.model.TipoObjeto;
import pt.softlost.model.Utilizador;

public class SoftLost {
	
	//Declaração Variaveis
	
	//Dates
	public static final SimpleDateFormat formatoData = new SimpleDateFormat("dd-MM-yyyy");
	public static String data = formatoData.format(new Date()).toString();
	private static final SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
	public static String hora = formatoHora.format(new Date()).toString();
	public static Date dataInicial = null;
	public static Date dataListagens = null;
	public static Date dataFinal = null;
	
	//ArrayList's
		
	public static ArrayList<Utilizador> users = new ArrayList<Utilizador>();
	public static ArrayList<Registo> registos = new ArrayList<Registo>();	
	public static ArrayList<Registo> doacoes = new ArrayList<Registo>();
	public static ArrayList<Sala> salas = new ArrayList<Sala>();
	public static ArrayList<Instituicao> instituicoes = new ArrayList<Instituicao>();
	public static ArrayList<TipoObjeto> tipos = new ArrayList<TipoObjeto>();
	
	
	//Ficheiros
	
	static File usersFile = new File("dadosLogin.txt");
	static File salaFile = new File("salas.txt");
	static File instituicaoFicheiro = new File("instituiçoes.txt");
	static File TipoObjetoFile = new File("tipoObjeto.txt");
	
	static File registosPasta = new File("registos");
	static File [] listaFicheiros = registosPasta.listFiles();
	
	static String[] fields = data.split("-");
	static File registoFile = new File(registosPasta + "/" + fields[1] + "-" + fields[2] + ".txt");	
		
	public static File instituicoesPasta = new File("instituiçoes");
	public static File[] ficheirosLista = instituicoesPasta.listFiles();	

		
		
		
		
		
		
		
		
		
		
		//Carregar Ficheiros	
		
		public static void loadUtilizador() throws IOException
		{
			if(!usersFile.exists())
			{
				usersFile.createNewFile();
			}
			Scanner inFile = new Scanner(usersFile);
			while (inFile.hasNextLine()) {
				String line = inFile.nextLine();
				String[] dados = line.split("#");
				Utilizador u = new Utilizador(dados[0], dados[1],dados[2]);
				u.getPermissoesAcesso().add(new Permissoes(Boolean.parseBoolean(dados[3]), Boolean.parseBoolean(dados[4]),Boolean.parseBoolean(dados[5]), Boolean.parseBoolean(dados[6]), Boolean.parseBoolean(dados[7]), Boolean.parseBoolean(dados[8])));
				users.add(u);			
			}
			
			inFile.close();
		}
		public static void loadInstituições() throws IOException{
			if(!instituicaoFicheiro.exists())
			{
				instituicaoFicheiro.createNewFile();
			}
			Scanner inInstituicoes = new Scanner(instituicaoFicheiro);
			while(inInstituicoes.hasNextLine()){
				String line = inInstituicoes.nextLine();
				String[] listaInstituicoes = line.split("#");
				Instituicao i = new Instituicao(Integer.parseInt(listaInstituicoes[0]), listaInstituicoes[1]);
			    instituicoes.add(i);
			}
			inInstituicoes.close();
		}		
		public static void loadSalas() throws IOException{
			
			if(!salaFile.exists())
			{
				salaFile.createNewFile();
			}
			Scanner inSalas = new Scanner(salaFile);
			while(inSalas.hasNextLine()){				
				Sala s = new Sala (inSalas.nextLine());
		     	salas.add(s);		     	
			}
			inSalas.close();
			}
		
		
		public static void loadTiposObjetos() throws IOException{
			if(!TipoObjetoFile.exists())
			{
				TipoObjetoFile.createNewFile();
			}
			Scanner inTipoObjeto = new Scanner(TipoObjetoFile);
			while (inTipoObjeto.hasNextLine()) {
			
				String line = inTipoObjeto.nextLine();
				String[] tipoObjetos = line.split("#");
				int codigo = Integer.parseInt(tipoObjetos[0]);
			
				
				Instituicao inst = null;
				for (Instituicao i : instituicoes) {
					if (i.getCodigo()==Integer.parseInt(tipoObjetos[2])) {
						inst = i;
					}
				}
				
				TipoObjeto t = new TipoObjeto(codigo, tipoObjetos[1], inst); 
				tipos.add(t);
			}
			inTipoObjeto.close();
			}		
		
		
		public static void loadDadosRegisto() throws IOException {
			
			
			
			Scanner inRegisto = null;
			
		for (int i = 0; i < listaFicheiros.length; i++) {
			if (listaFicheiros[i].isFile()) {
				 File registoFile = new File(registosPasta + "/" + listaFicheiros[i].getName());
				 if (!registoFile.exists()) {
					 registoFile.createNewFile();
				}
				inRegisto = new Scanner (registoFile);
					while(inRegisto.hasNextLine()){
						String line = inRegisto.nextLine();
						String[] dadosRegisto = line.split("#");
						int cod = Integer.parseInt(dadosRegisto[0].toString());
						String nome = dadosRegisto[1];
						String email = dadosRegisto[2];
						
						
						//Ler a Sala
						//Precorrer o Array salas para verificar se a sala é encontrada
						Sala sala = null;
						for (Sala s : salas) {
							if (s.getSala().equals(dadosRegisto[3])) {
								sala = s;
							}
						}
						
						String data = dadosRegisto[4];
						String hora = dadosRegisto[5];
						
						TipoObjeto to = null;
						for (TipoObjeto t  : tipos) {
							if (t.getCodigo() == Integer.parseInt(dadosRegisto[6])) {						
								to = t;
							}
							
						}
						
									
						
						
						String cor = dadosRegisto[7];
						String estado = dadosRegisto[8];
						String descricao = dadosRegisto[9];
						
						Registo r = new Registo(cod, nome,email,sala,data,hora, to,cor,estado,descricao);
				     	registos.add(r);
						
					}
				
			}	
		
		}
			inRegisto.close();			

		}
		public static void loadDoações() throws FileNotFoundException{
			
			Scanner inDoações;
			for (int i = 0; i < ficheirosLista.length; i++) {
				if (ficheirosLista[i].isFile()) {
					 File doaçõesFile = new File(instituicoesPasta + "/" + ficheirosLista[i].getName());
					 inDoações = new Scanner (doaçõesFile);
						while(inDoações.hasNextLine()){
							String line = inDoações.nextLine();
							String[] dadosDoações = line.split("#");
							int cod = Integer.parseInt(dadosDoações [0].toString());
							String nome = dadosDoações [1];
							String email = dadosDoações [2];
							
							
							//Ler a Sala
							//Precorrer o Array salas para verificar se a sala é encontrada
							Sala sala = null;
							for (Sala s : salas) {
								if (s.getSala().equals(dadosDoações [3])) {
									sala = s;
								}
							}
							
							String data = dadosDoações [4];
							String hora = dadosDoações [5];
							
							TipoObjeto to = null;
							for (TipoObjeto t  : tipos) {
								if (t.getCodigo() == Integer.parseInt(dadosDoações [6])) {						
									to = t;
								}
								
							}
							
										
							
							
							String cor = dadosDoações [7];
							String estado = dadosDoações [8];
							String descricao = dadosDoações [9];
							
							Registo c = new Registo(cod, nome,email,sala,data,hora, to,cor,estado,descricao);
							doacoes.add(c);
						}
				}
			}
		}
		
		
		//Salvar nos Ficheiros
		
		public static void saveUtilizdor() throws FileNotFoundException{
			
			PrintWriter out = new PrintWriter(usersFile);
			for(int i = 0; i< users.size();i++	)
			{
				Utilizador u = users.get(i);
				Permissoes perm = Utilizador.permissoesAcesso.get(i);
				out.println(u.getUsername() + "#" + u.getPassword() + "#" + u.getTipoUtilizador() +  "#"  + Boolean.toString(perm.isRegisto()) + "#" + Boolean.toString(perm.isReclamacao()) + "#" + Boolean.toString(perm.isImportacao()) + "#" + Boolean.toString(perm.isListagens()) + "#" + Boolean.toString(perm.isDoacoes()) + "#" + Boolean.toString(perm.isConfiguracao()));
				
				
			}
			out.close();
		}
		public static void saveSalas() throws FileNotFoundException{
		PrintWriter outSalas = new PrintWriter(salaFile);
			for (Sala s : SoftLost.salas) {
				outSalas.println(s.getSala().toString());	
			}
			
			outSalas.close();
		}
		public static void saveInstituições() throws FileNotFoundException{
			PrintWriter outInstituicoes = new PrintWriter(instituicaoFicheiro);
			for (Instituicao i : SoftLost.instituicoes) {
				outInstituicoes.println(i.getCodigo() + "#" + i.getInstituicao());
			}
			outInstituicoes.close();
		}
		public static void saveTiposObjetos() throws FileNotFoundException{
			PrintWriter outTiposObjetos = new PrintWriter(TipoObjetoFile);
			for (TipoObjeto tp : SoftLost.tipos) {
				outTiposObjetos.println(tp.getCodigo() + "#" + tp.getTipoObjeto() + "#" + tp.getI().getCodigo());
			}
			
			outTiposObjetos.close();
		}
		public static void saveRegistos() throws ParseException, IOException{

			
			
			
			
			for (Registo reg : SoftLost.registos) {
				String[] fields = data.split("-");
				File registoFile = new File(registosPasta + "/" + fields[1] + "-" + fields[2] + ".txt");
				if (!registoFile.exists()) {
					registoFile.createNewFile();
				}
				//FileOutputStream fos = new FileOutputStream(registoFile);
				PrintWriter outRegisto = new PrintWriter(registoFile);
				outRegisto.println(reg.getCodigo() + "#" +  reg.getNome() + "#" + reg.getEmail() +"#"+ reg.getS().getSala()+ "#" + reg.getData() + "#" + reg.getHora() +"#" + reg.getX().getCodigo() +"#"+reg.getCor()+ "#"+ reg.getEstado() + "#" + reg.getDescrição() );
				outRegisto.close();
			}
		}
		public static void saveDoações() throws IOException{
			
			
			PrintWriter outDoaçoes = null;
			
			
			for (Registo reg : SoftLost.doacoes) {
				
				String inst1 = reg.getX().getI().getInstituicao();
				File instFile = new File(instituicoesPasta + "/" + inst1 + ".txt");
				
				
				if(!instFile.exists()) {
					instFile.createNewFile();
					if( outDoaçoes != null ) {
						outDoaçoes.close();
					}
					
				}
				outDoaçoes = new PrintWriter(instituicoesPasta + "/" + inst1 + ".txt");
				outDoaçoes.println(reg.getCodigo() + "#" +  reg.getNome() + "#" + reg.getEmail() +"#"+ reg.getS().getSala()+ "#" + reg.getData() + "#" + reg.getHora() +"#" + reg.getX().getCodigo() +"#"+reg.getCor()+ "#"+ reg.getEstado() + "#" + reg.getDescrição() );
				
			}
			
				outDoaçoes.close();
			
		}
		
		

		


		

		
}

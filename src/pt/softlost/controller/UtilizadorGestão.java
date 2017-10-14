package pt.softlost.controller;

import javax.swing.JOptionPane;

import pt.softlost.model.Permissoes;
import pt.softlost.model.Utilizador;
import pt.softlost.view.PainelPrincipalFrame;

public class UtilizadorGestão {
	
	public static boolean isUser(String username, String password) {
		
		boolean res = false;
		for (Utilizador u : SoftLost.users) {
			if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
				
				res = true;
			}
		}		
		return res;
	}
	
	public static void Permissoes(String utilizador)
	{
		for(int i = 0;i<SoftLost.users.size();i++)
		{
			Utilizador user = SoftLost.users.get(i);
			if(user.getUsername().equals(utilizador))
			{
				Permissoes p = Utilizador.permissoesAcesso.get(i);
				PainelPrincipalFrame.mnRegisto.setVisible(p.isRegisto());
				PainelPrincipalFrame.mnReclamao.setVisible(p.isReclamacao());
				PainelPrincipalFrame.mnListagens.setVisible(p.isListagens());
				PainelPrincipalFrame.mnImportao.setVisible(p.isImportacao());
				PainelPrincipalFrame.mnGesto.setVisible(p.isConfiguracao());
				PainelPrincipalFrame.mnDoaes.setVisible(p.isDoacoes());
			}
		}
	}
	
	public static void adicionarUser(String username, String password1, String password2, String TipoUtilizador, boolean registo, boolean reclamacao, boolean importacao, boolean listagens, boolean doaçoes, boolean gestao)
	{
		boolean res = false;
		
		if(password1.equals(password2))
		{
			for(Utilizador user : SoftLost.users)
			{
				if(user.getUsername().equals(username))
				{
					JOptionPane.showMessageDialog(null, "Utilizador ja existente!!!");
					res = true;
					break;
				}
				
			}
			
			if(res == false)
			{
				Utilizador u = new Utilizador(username, password1,TipoUtilizador);
				u.getPermissoesAcesso().add(new Permissoes(registo, reclamacao, importacao, listagens, doaçoes, gestao));
				SoftLost.users.add(u);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "As passwords não coincidem!!!");
		}
		
	}
	
	public static void removerUser(String username)
	{
		for(int i = 0;i<SoftLost.users.size();i++)
		{
			Utilizador user = SoftLost.users.get(i);
			if(username.equals(user.getUsername()))
			{
				SoftLost.users.remove(i);
				 
			}
		}
	}
	
	public static boolean editarUser(String usernameVelho,String usernameNovo, String password1, String password2,String tipoUtilizador, String nomeUser, boolean registo, boolean reclamacao, boolean importacao, boolean listagens, boolean doaçoes, boolean gestao )
	{
		boolean res = false;
		
		if(password1.equals(password2))
		{
			for(int i = 0; i<SoftLost.users.size();i++)
			{
				Utilizador user = SoftLost.users.get(i);
				if(SoftLost.users.contains(usernameNovo))
				{
					res = true;
					JOptionPane.showMessageDialog(null, "Utilizador ja existente!!!");
					break;
				}
				else if(usernameVelho.equals(user.getUsername()))
				{
					user.setUsername(usernameNovo);
					user.setPassword(password1);
					user.setTipoUtilizador(tipoUtilizador);
					Permissoes permissoes = Utilizador.permissoesAcesso.get(i);
					permissoes.setRegisto(registo);
					permissoes.setReclamacao(reclamacao);
					permissoes.setImportacao(importacao);
					permissoes.setListagens(listagens);
					permissoes.setDoacoes(doaçoes);
					permissoes.setConfiguracao(gestao);
				
				}
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "As passwords não coincidem!!!");
		}
	
	return res;
	
	}

}

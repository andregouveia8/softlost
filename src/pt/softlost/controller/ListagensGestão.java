package pt.softlost.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import pt.softlost.model.Registo;
import pt.softlost.model.Sala;
import pt.softlost.model.TipoObjeto;
import pt.softlost.view.ListagensFrame;

public class ListagensGestão {
	
	private static ArrayList<String> sortObjetos = new ArrayList<String>();

public static void spinnerDate() throws ParseException{
	
	
	
	for (int i = 0; i < SoftLost.registos.size(); i++) {
		
		Registo reg = SoftLost.registos.get(i);
		if (i == 0) {
			
			SoftLost.dataInicial = SoftLost.formatoData.parse(reg.getData());
			SoftLost.dataFinal = SoftLost.formatoData.parse(reg.getData());
			
		}
		else{
			SoftLost.dataListagens  = SoftLost.formatoData.parse(reg.getData());
			if (SoftLost.dataListagens.before(SoftLost.dataInicial)) {
				
				SoftLost.dataInicial = SoftLost.formatoData.parse(reg.getData());
			}
			else if(SoftLost.dataListagens.after(SoftLost.dataFinal)){
				
				SoftLost.dataFinal = SoftLost.formatoData.parse(reg.getData());
			}
		}
	}

	ListagensFrame.spinnerDataI.setValue(SoftLost.dataInicial);
	ListagensFrame.spinnerDataF.setValue(SoftLost.dataFinal);
}

public static void criarTabela()
{
	ListagensFrame.dtm.setRowCount(0);
	ListagensFrame.dtm.setColumnCount(0);
	ListagensFrame.dtm.addColumn("Data");
	ListagensFrame.dtm.addColumn("Hora");
	ListagensFrame.dtm.addColumn("Sala");
	ListagensFrame.dtm.addColumn("Tipo Objeto");
	ListagensFrame.dtm.addColumn("Cor");
	ListagensFrame.dtm.addColumn("Estado");
	
	for(Registo reg : SoftLost.registos)
	{
		ListagensFrame.dtm.addRow(new Object[]{reg.getData(),reg.getHora(),reg.getS(),reg.getX(),reg.getCor(),reg.getEstado()});
	}
}

public static void criarCombobox()
{
	
	
	if(SoftLost.salas.size()>0)
	{
		ListagensFrame.cbModelSala.removeAllElements();
		ListagensFrame.cbModelSala.addElement("");
		for(Sala sl : SoftLost.salas)
		{
			ListagensFrame.cbModelSala.addElement(sl.getSala());
		}
	}
	
	
	if(SoftLost.tipos.size() > 0)
	{
		ListagensFrame.cbModelTipodeobjeto.removeAllElements();
		
		ListagensFrame.cbModelTipodeobjeto.addElement("");
		for(TipoObjeto to : SoftLost.tipos)
		{
			ListagensFrame.cbModelTipodeobjeto.addElement(to.getTipoObjeto().toString());
		}
	}
}

public static void sortearListagem() throws ParseException
{
	criarTabela();
	
	ListagensFrame.dtm.setRowCount(0);
	
	SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	Date datainicial =  (Date) ListagensFrame.spinnerDataI.getValue();
	Date dataCheck = null;
	Date datafinal = (Date) ListagensFrame.spinnerDataF.getValue();
	
	for (Registo reg : SoftLost.registos) 
	{
		dataCheck = formato.parse(reg.getData());
		if ((dataCheck.before(datafinal) || dataCheck.equals(datafinal)) && (dataCheck.after(datainicial) || dataCheck.equals(datainicial))) 
		{
			ListagensFrame.dtm.addRow(new Object[]{reg.getData(), reg.getHora(), reg.getS(), reg.getX(), reg.getCor(), reg.getEstado()});
		}
	}
	
	
	
	
	//TIPO DE OBJETO
	
	if(!(ListagensFrame.cbTipodeobjeto.getSelectedItem().toString().equals("")))
	{
		sortObjetos.clear();
		
		for(int i = 0; i<ListagensFrame.dtm.getRowCount();i++)
		{
			String s1 = new String(ListagensFrame.dtm.getValueAt(i, 0).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 1).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 2).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 3).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 4).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 5).toString());
			sortObjetos.add(s1);
		}
		
		
		
		ListagensFrame.dtm.setRowCount(0);
		
		for (int i = 0; i< sortObjetos.size();i++)
		{
			String Sobj = sortObjetos.get(i);
			String[] fields = Sobj.toString().split("#");
			if(fields[3].equals(ListagensFrame.cbTipodeobjeto.getSelectedItem().toString()))
			{
				
				
				ListagensFrame.dtm.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]});
			}
			
			
		}
	}
	
	//BLOCO
	
	if(!(ListagensFrame.cbSala.getSelectedItem().toString().equals("")))
	{
		sortObjetos.clear();
		
		for(int i = 0; i<ListagensFrame.dtm.getRowCount();i++)
		{
			String s1 = new String(ListagensFrame.dtm.getValueAt(i, 0).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 1).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 2).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 3).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 4).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 5).toString());
			sortObjetos.add(s1);
		}
		
		ListagensFrame.dtm.setRowCount(0);
		
		for (int i = 0; i< sortObjetos.size();i++)
		{
			String Sobj = sortObjetos.get(i);
			String[] fields = Sobj.toString().split("#");
			
			if(fields[2].equals(ListagensFrame.cbSala.getSelectedItem().toString()))
			{
				
				ListagensFrame.dtm.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]});
			}
			
			
		}
	}
	
	
	
	//COR
	
	if(!(ListagensFrame.cbCor.getSelectedItem().toString().equals("")))
	{
		sortObjetos.clear();
		
		for(int i = 0; i<ListagensFrame.dtm.getRowCount();i++)
		{
			String s1 = new String(ListagensFrame.dtm.getValueAt(i, 0).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 1).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 2).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 3).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 4).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 5).toString());
			sortObjetos.add(s1);
		}
		
		ListagensFrame.dtm.setRowCount(0);
		
		for (int i = 0; i< sortObjetos.size();i++)
		{
			String Sobj = sortObjetos.get(i);
			String[] fields = Sobj.toString().split("#");
			
			if(fields[4].equals(ListagensFrame.cbCor.getSelectedItem().toString()))
			{
				
				ListagensFrame.dtm.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]});
			}
			
			
		}
	}
	
	//ESTADO
	
	if(!(ListagensFrame.cbEstado.getSelectedItem().toString().equals("")))
	{
		sortObjetos.clear();
		
		for(int i = 0; i<ListagensFrame.dtm.getRowCount();i++)
		{
			String s1 = new String(ListagensFrame.dtm.getValueAt(i, 0).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 1).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 2).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 3).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 4).toString() + "#" + ListagensFrame.dtm.getValueAt(i, 5).toString());
			sortObjetos.add(s1);
		}
		
		ListagensFrame.dtm.setRowCount(0);
		
		for (int i = 0; i< sortObjetos.size();i++)
		{
			String Sobj = sortObjetos.get(i);
			String[] fields = Sobj.toString().split("#");
			
			if(fields[5].equals(ListagensFrame.cbEstado.getSelectedItem().toString()))
			{
				
				ListagensFrame.dtm.addRow(new String[]{fields[0], fields[1], fields[2], fields[3], fields[4], fields[5]});
			}
			
			
		}
	}
	
}


}

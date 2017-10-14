package pt.softlost.view;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pt.softlost.controller.DoaçõesGestão;
import pt.softlost.controller.SoftLost;
import pt.softlost.model.Registo;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class DoaçõesFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	public static DefaultTableModel dtm;


	public DoaçõesFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DoaçõesFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		setResizable(false);
		setTitle("SoftLost - Sistemas de Perdidos e Achados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 100, 600, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDoaes = new JLabel("Doa\u00E7\u00F5es");
		lblDoaes.setForeground(Color.WHITE);
		lblDoaes.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDoaes.setBounds(10, 0, 264, 61);
		contentPane.add(lblDoaes);
		
		
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			dispose();
			
			}
		});
		btnVoltar.setBounds(495, 353, 89, 23);
		contentPane.add(btnVoltar);
		
		JButton btnDoar = new JButton("Doar");
		btnDoar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int codigo = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
				try {
					DoaçõesGestão.doarObjeto(codigo);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				atualizador();
				try {
					SoftLost.saveDoações();
					JOptionPane.showMessageDialog(null, "Doado com Sucesso!");
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
			}
		});
		btnDoar.setBounds(396, 353, 89, 23);
		contentPane.add(btnDoar);
		
		JLabel label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon("E:\\ESEIG\\1\u00BAAno\\2\u00BASemestre\\Projeto I\\Projeto - SoftLost Sistema de Perdidos e Achados\\Logotipos PNG\\SoftwarePequeno.png"));
		label.setBounds(0, 227, 216, 70);
		contentPane.add(label);
		dtm = new DefaultTableModel();
		
		//colunas
		dtm.addColumn("Código");
		dtm.addColumn("Data Registo");
		dtm.addColumn("Tipo Objeto");
		dtm.addColumn("Descrição");
		dtm.addColumn("Instituição");
		
		for (int i = 0;i< SoftLost.registos.size();i++) {
			Registo reg = SoftLost.registos.get(i);
			try {
				String dataFinal = "";
				Calendar c = Calendar.getInstance();
				c.setTime(SoftLost.formatoData.parse(reg.getData()));
				c.add(Calendar.MONTH,1);
				dataFinal = SoftLost.formatoData.format(c.getTime());
				Date finalData = SoftLost.formatoData.parse(dataFinal);
				
				Date currentDate = SoftLost.formatoData.parse(SoftLost.data);
				
				
				
				if(currentDate.before(finalData) && (reg.getEstado().equals("Bom") || reg.getEstado().equals("Razoável")))
				{
					
					
					
					
					
					dtm.addRow(new Object[]{reg.getCodigo(), reg.getData(), reg.getX().getTipoObjeto(), reg.getDescrição(), reg.getX().getI().getInstituicao()});
					
					
				}
				
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
		}
		
		table = new JTable(dtm);
		table.setBounds(10, 108, 321, 134);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(10, 62);
		scrollPane.setSize(574, 280);
		
		contentPane.add(scrollPane);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(DoaçõesFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
		label_1.setBounds(-27, 0, 654, 477);
		contentPane.add(label_1);
		
	}
	
	public static void atualizador(){
		dtm.setRowCount(0);
		for (int i = 0;i< SoftLost.registos.size();i++) {
			Registo reg = SoftLost.registos.get(i);
			try {
				String dataFinal = "";
				Calendar c = Calendar.getInstance();
				c.setTime(SoftLost.formatoData.parse(reg.getData()));
				c.add(Calendar.MONTH,1);
				dataFinal = SoftLost.formatoData.format(c.getTime());
				Date finalData = SoftLost.formatoData.parse(dataFinal);
				
				Date currentDate = SoftLost.formatoData.parse(SoftLost.data);
				if(currentDate.before(finalData) && (reg.getEstado().equals("Bom") || reg.getEstado().equals("Razoável")))
				{
					
					
					
					
					
					dtm.addRow(new Object[]{reg.getCodigo(), reg.getData(), reg.getX().getTipoObjeto(), reg.getDescrição(), reg.getX().getI().getInstituicao()});
					
					
				}
				
				
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
	}
}
}


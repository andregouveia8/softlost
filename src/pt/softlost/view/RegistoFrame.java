package pt.softlost.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pt.softlost.controller.RegistoGestao;
import pt.softlost.controller.SoftLost;
import pt.softlost.model.Sala;
import pt.softlost.model.TipoObjeto;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class RegistoFrame extends JFrame {

	private JPanel contentPane;
	public static JTextField txtNome;
	public static JTextField txtEmail;
	JTextArea txtAreaDescrição;

	
	public RegistoFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistoFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		setTitle("Registo - SoftLost");
		setResizable(false);
		setBackground(Color.WHITE);
		
		//-----------------------
		//Label's e JPanel
		//-----------------------
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 100, 600, 416);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("Registo Material Perdido");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		label.setBounds(9, 5, 282, 39);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Nome");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.LEFT);
		label_1.setBounds(10, 60, 46, 14);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Email");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_2.setForeground(Color.WHITE);
		label_2.setHorizontalAlignment(SwingConstants.LEFT);
		label_2.setBounds(10, 88, 46, 14);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Tipo");
		label_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_3.setForeground(Color.WHITE);
		label_3.setHorizontalAlignment(SwingConstants.LEFT);
		label_3.setBounds(9, 131, 35, 20);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("Cor ");
		label_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_4.setForeground(Color.WHITE);
		label_4.setHorizontalAlignment(SwingConstants.LEFT);
		label_4.setBounds(9, 156, 35, 20);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("Sala");
		label_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_5.setForeground(Color.WHITE);
		label_5.setBounds(283, 131, 45, 20);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("Estado");
		label_6.setFont(new Font("Tahoma", Font.BOLD, 12));
		label_6.setForeground(Color.WHITE);
		label_6.setHorizontalAlignment(SwingConstants.LEFT);
		label_6.setBounds(283, 156, 45, 20);
		contentPane.add(label_6);
		
		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o");
		lblDescrio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDescrio.setForeground(Color.WHITE);
		lblDescrio.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescrio.setBounds(10, 205, 83, 14);
		contentPane.add(lblDescrio);
		
		txtNome = new JTextField();
		txtNome.setForeground(Color.BLACK);
		txtNome.setBounds(55, 57, 519, 20);
		contentPane.add(txtNome);
		txtNome.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setForeground(Color.BLACK);
		txtEmail.setBounds(55, 85, 519, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------
		// COMBOBOX'S - COR - TIPOOBJETO - SALA - ESTADO
		//-----------------------------------------------------------------------------------------------------------------------------------------------------------
		
		
		
		//---------
		//-- COR --
		//---------
		
		DefaultComboBoxModel<String>cbCor = new DefaultComboBoxModel<String>();
		
		cbCor.addElement("Preto");
		cbCor.addElement("Branco");
		cbCor.addElement("Azul");
		cbCor.addElement("Vermelho");
		cbCor.addElement("Verde");
		cbCor.addElement("Laranja");
		cbCor.addElement("Castanho");
		cbCor.addElement("Cinzento");
		cbCor.addElement("Amarelo");
		cbCor.addElement("Violeta");
		
		JComboBox<String> cBox_Cor = new JComboBox<String>(cbCor);
		cBox_Cor.setForeground(Color.BLACK);
		cBox_Cor.setBounds(55, 156, 219, 20);
		contentPane.add(cBox_Cor);

		//--------------
		//--TIPOOBJETO--
		//--------------
		
		DefaultComboBoxModel<TipoObjeto> cbModelTipo = new DefaultComboBoxModel<TipoObjeto>();
		
		for (TipoObjeto tp : SoftLost.tipos) {
			
			cbModelTipo.addElement(tp);
			
		}
		
		JComboBox<TipoObjeto> cBox_Tipo = new JComboBox<TipoObjeto>(cbModelTipo);
		cBox_Tipo.setForeground(new Color(0, 0, 0));
		cBox_Tipo.setBounds(55, 131, 219, 20);
		contentPane.add(cBox_Tipo);
		
		
		//-----------
		//---SALA----
		//-----------
		
		
		DefaultComboBoxModel<Sala> cbModelSalas = new DefaultComboBoxModel<Sala>();
		
		for (Sala s : SoftLost.salas) {
			
			cbModelSalas.addElement(s);
		}
		
		JComboBox<Sala> cBox_Sala = new JComboBox<Sala>(cbModelSalas);
		cBox_Sala.setForeground(Color.BLACK);
		cBox_Sala.setBounds(329, 131, 245, 20);
		contentPane.add(cBox_Sala);
		
		
		//--------------
		//----ESTADO----
		//--------------
		
		 
		DefaultComboBoxModel<String> estado = new DefaultComboBoxModel<String>();
		
		estado.addElement("Bom");
		estado.addElement("Razoável");
		estado.addElement("Mau");
		
		JComboBox<String> cBox_Estado = new JComboBox<String>(estado);
		cBox_Estado.setForeground(Color.BLACK);
		cBox_Estado.setBounds(329, 156, 245, 20);
		contentPane.add(cBox_Estado);
		
		
		//------------------------------
		//-----------BUTTON'S-----------
		//------------------------------
		
		
		//------------
		//---VOLTAR---
		//------------
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnVoltar.setBackground(Color.WHITE);
		btnVoltar.setForeground(Color.BLACK);
		btnVoltar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				dispose();
			
			}
		});
		btnVoltar.setBounds(485, 343, 89, 23);
		contentPane.add(btnVoltar);
		
		//--------------
		//---REGISTAR---
		//--------------
		
		JButton btnNewButton = new JButton("Registar");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				
				
				
				if (txtNome.getText().isEmpty() && txtEmail.getText().isEmpty() && txtAreaDescrição.getText().isEmpty()) {
					 
					 JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios para efetuar o registo.");
					 
					 
			        }else{
			        	
			        if(!RegistoGestao.matchesOnlyText(txtNome.getText())) {
			            JOptionPane.showMessageDialog(null, "Você não pode inserir números no nome.");
			            txtNome.setText("");
			        }
			        else{
			        	
					int cod = RegistoGestao.buscarCodigoAnterior();
					RegistoGestao.registo(cod, txtNome.getText(),txtEmail.getText(), cBox_Sala.getSelectedItem(),SoftLost.data,SoftLost.hora, cBox_Tipo.getSelectedItem(),cBox_Cor.getSelectedItem().toString(),cBox_Estado.getSelectedItem().toString(),txtAreaDescrição.getText());
					 
					JOptionPane.showMessageDialog(null, "Registo efetuado com sucesso!!");
					
					txtAreaDescrição.setText("");
					txtEmail.setText("");
					txtNome.setText("");
			        }
			        	
					
					
					
					
			     }
				
				
				try {
					SoftLost.saveRegistos();
				} catch (ParseException | IOException e1) {
					
				JOptionPane.showMessageDialog(null, e1.getMessage());
					
				}
				
				
			        }
				
			
		});
		btnNewButton.setBounds(386, 343, 89, 23);
		contentPane.add(btnNewButton);
		
		txtAreaDescrição = new JTextArea();
		txtAreaDescrição.setForeground(Color.BLACK);
		txtAreaDescrição.setBounds(80, 205, 494, 77);
		contentPane.add(txtAreaDescrição);
		
		JLabel label_7 = new JLabel("");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setIcon(new ImageIcon(RegistoFrame.class.getResource("/pt/softlost/res/Software.png")));
		label_7.setBounds(10, 293, 219, 83);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("");
		label_8.setIcon(new ImageIcon(RegistoFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
		label_8.setBounds(0, 0, 594, 387);
		contentPane.add(label_8);
	}
}
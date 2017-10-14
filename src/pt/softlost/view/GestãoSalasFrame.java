package pt.softlost.view;
import java.awt.Dimension;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import pt.softlost.controller.SalasGestão;
import pt.softlost.controller.SoftLost;
import pt.softlost.model.Sala;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.JList;
import javax.swing.border.LineBorder;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;


public class GestãoSalasFrame extends JFrame {

	private JPanel contentPane;
	public static JTextField txtSala;
	public static String [] col = {"Salas"};
	public static DefaultTableModel dtmSala = new DefaultTableModel(col,0);
	public static DefaultListModel<Sala> listModel ;
	private JTextField txtNovaSala;
	
	public GestãoSalasFrame() {
		
		//JFrame Definições
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestãoSalasFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		setTitle("SoftLost - Gest\u00E3o de Salas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 459, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//***********************************
		//************JLABEL'S***************
		//***********************************
		
		
		JLabel lblGestoDeSalas = new JLabel("Gest\u00E3o de Salas");
		lblGestoDeSalas.setForeground(Color.WHITE);
		lblGestoDeSalas.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGestoDeSalas.setBounds(10, 0, 264, 45);
		contentPane.add(lblGestoDeSalas);
		
		JLabel lblSalaAlterada = new JLabel("Altera\u00E7\u00E3o na Sala");
		lblSalaAlterada.setForeground(Color.WHITE);
		lblSalaAlterada.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSalaAlterada.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalaAlterada.setBounds(167, 77, 147, 14);
		contentPane.add(lblSalaAlterada);
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setForeground(Color.WHITE);
		lblSala.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblSala.setBounds(10, 77, 147, 14);
		contentPane.add(lblSala);
		
		
		//***********************************
		//************JBUTTON'S**************
		//***********************************
		
		
		//BTN_Voltar
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dispose(); // Código para apagar a janela
			}
		});
		btnVoltar.setBounds(334, 229, 98, 23);
		contentPane.add(btnVoltar);
		
		//BTN_Alterar
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtSala.getText().isEmpty() || txtNovaSala.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo Sala e Alteração de Sala para poder alterar uma sala.");
		        } else {
		          //Variaveis das JTextFields convertidas para String
				String sala = txtSala.getText().toString();
				String novaSala = (String) txtNovaSala.getText().toString();
				
				if(SalasGestão.alterarSala(sala, novaSala)) {
					
					JOptionPane.showMessageDialog(null, "A sala " + sala +  " foi alterada com sucesso para" + novaSala + " .");
					
					//refresh da JList Salas
					listModel.setSize(0);
					for (Sala s : SoftLost.salas) {
						listModel.addElement(s);
					}
					txtSala.setText("");
					txtNovaSala.setText("");
					try {
						SoftLost.saveSalas();
					} catch (FileNotFoundException e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
				
				} else {
					JOptionPane.showMessageDialog(null, "A sala " + sala +  " nao se encontra registada, ou a sala " + novaSala +" já se encontra registada");
				}
		        }
				
				
								
			
			}
		});
		btnAlterar.setBounds(118, 229, 98, 23);
		contentPane.add(btnAlterar);
		
		//BTN_Remover

		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtSala.getText().isEmpty()) {
		            
					JOptionPane.showMessageDialog(null, "Preencha o campo sala para poder remover uma sala.");
					
		        } else {
		        	//Variavel da JTextField convertida para String
					String sala = txtSala.getText().toString();
					
					if(SalasGestão.removerSala(sala)) {
					
						JOptionPane.showMessageDialog(null, "A sala " + sala +  " foi apagada com sucesso.");
						
						
						//refresh da JList Salas
						listModel.setSize(0);
						for (Sala s : SoftLost.salas) {
							listModel.addElement(s);
						}
						txtSala.setText("");
						txtNovaSala.setText("");
						try {
							SoftLost.saveSalas();
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
					} else {
						JOptionPane.showMessageDialog(null, "A sala " + sala +  " nao se encontra registada.");
					}
		        }
				
			}
		});
		btnRemover.setBounds(226, 229, 98, 23);
		contentPane.add(btnRemover);
		
		//BTN_Inserir

		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				if (txtSala.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha o campo sala para poder adicionar uma sala.");    
		        } else {
		        	//Variavel da JTextField convertida para String
					String sala = txtSala.getText().toString();
					
					if(SalasGestão.adicionarSala(sala)) {
					
						JOptionPane.showMessageDialog(null, "A sala " + sala +  " foi registada com sucesso.");	
						
						//refresh da JList Salas
						listModel.setSize(0);
						for (Sala s : SoftLost.salas) {
							listModel.addElement(s);
						}
						try {
							SoftLost.saveSalas();
						} catch (FileNotFoundException e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
						txtSala.setText("");
						txtNovaSala.setText("");
					} else {
						JOptionPane.showMessageDialog(null, "A sala " + sala +  " já se encontra registada.");
					}
		        }
				
				
				
				
				
				
			
			}
		});
		btnInserir.setBounds(10, 229, 98, 23);
		contentPane.add(btnInserir);
		
		
		
		//*************************************
		//************ JList ******************
		//*************************************
		
		//Carrega o array para a tabela atraves do Ciclo For e do AddElement
		listModel = new DefaultListModel<Sala>();
		for (Sala s : SoftLost.salas) {
			listModel.addElement(s);
		}
		JList<Sala> list = new JList<Sala>(listModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtSala.setText(list.getSelectedValue().toString());
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(307, 98, 120, 128);
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setLocation(334, 56);
		listScroller.setSize(98, 166);
		listScroller.setPreferredSize(new Dimension(250, 80));
		
		
		contentPane.add(listScroller);
		
		
		//*************************************
		//********* JTEXTFIELDS ***************
		//*************************************
		
		txtSala = new JTextField();
		txtSala.setHorizontalAlignment(SwingConstants.CENTER);
		txtSala.setBounds(10, 98, 147, 20);
		contentPane.add(txtSala);
		txtSala.setColumns(10);
		
		txtNovaSala = new JTextField();
		txtNovaSala.setHorizontalAlignment(SwingConstants.CENTER);
		txtNovaSala.setBounds(167, 99, 147, 20);
		contentPane.add(txtNovaSala);
		txtNovaSala.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GestãoSalasFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
		label.setBounds(-73, -11, 825, 370);
		contentPane.add(label);
		
		
		
		
		
	}
}

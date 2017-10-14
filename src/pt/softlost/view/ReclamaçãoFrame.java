package pt.softlost.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.naming.ContextNotEmptyException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

import pt.softlost.controller.ReclamaçãoGestão;
import pt.softlost.controller.SoftLost;
import pt.softlost.model.Registo;
import pt.softlost.model.TipoObjeto;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class ReclamaçãoFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	static DefaultTableModel dtm = new DefaultTableModel();
	public static String[] nomeColunas = {"Código", "Objeto", "Cor", "Estado", "Sala"};
	public static String[][] regs;

	
	public ReclamaçãoFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReclamaçãoFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		
		
			//Defenições da Frame
			setTitle("Reclama\u00E7\u00E3o - SoftLost");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 596, 383);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			
			
			
			
			
			
			//JBUTTON
			
			JButton button = new JButton("Reclamar");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				
					int codigo = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
					System.out.println(codigo);
					if (ReclamaçãoGestão.reclamacarObjeto(codigo)) {
						JOptionPane.showMessageDialog(null, "Objeto Reclamado com Sucesso!");
						atualizaTabelaReclamação();
						
						
					}
					
				
				}
			});
			button.setBounds(382, 315, 89, 23);
			contentPane.add(button);
			
			JButton button_1 = new JButton("Voltar");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			button_1.setBounds(481, 315, 89, 23);
			contentPane.add(button_1);
			
			//COMBOBOX
			
					DefaultComboBoxModel<TipoObjeto> cbModelTipo = new DefaultComboBoxModel<TipoObjeto>();
					
					for (TipoObjeto tp : SoftLost.tipos) {
						
						cbModelTipo.addElement(tp);
						
					}
					
					JComboBox<TipoObjeto> cb_TiposObjetos = new JComboBox<TipoObjeto>(cbModelTipo);
					cb_TiposObjetos.setSelectedIndex(-1);
					cb_TiposObjetos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							if (cb_TiposObjetos.getSelectedIndex() != -1) {
								
								dtm.setRowCount(0);
								dtm.setColumnCount(0);
								
								String objeto = cb_TiposObjetos.getSelectedItem().toString();
								int cont = 0;
								for (int i = 0; i < SoftLost.registos.size(); i++) {
									if (SoftLost.registos.get(i).getX().getTipoObjeto().equals(objeto)) {
										cont++;	
									}
								}
							
								String[][] regs = new String[cont][5];
								String[] nomeColunas = {"Código", "Objeto", "Cor", "Estado", "Sala"};
								
								int c = 0;
								for (int i = 0; i < SoftLost.registos.size(); i++) {
									if (SoftLost.registos.get(i).getX().getTipoObjeto().equals(objeto)) {
										regs[c][0] = Integer.toString(SoftLost.registos.get(i).getCodigo());
										regs[c][1] = SoftLost.registos.get(i).getX().getTipoObjeto();
										regs[c][2] = SoftLost.registos.get(i).getCor().toString();
										regs[c][3] = SoftLost.registos.get(i).getEstado().toString();
										regs[c][4] = SoftLost.registos.get(i).getS().getSala().toString();
									c++;	
									}
								}
								
								for (int j = 0; j < nomeColunas.length; j++) {
									dtm.addColumn(nomeColunas[j]);
								}
								for (int i = 0; i < regs.length; i++) {
									dtm.addRow(regs[i]);
								}
							}
						}
					});
					cb_TiposObjetos.setBounds(115, 67, 103, 20);
					contentPane.add(cb_TiposObjetos);
					
		
					
					//JTABLE
					
					String[] nomeColunas = {"Código", "Objeto", "Cor", "Estado", "Sala"};
					int cont = 0;
					String[][] regs = new String[SoftLost.registos.size()][5];
					for (int i = 0; i < SoftLost.registos.size(); i++) {
						regs[i][0] = Integer.toString(SoftLost.registos.get(i).getCodigo());
						regs[i][1] = SoftLost.registos.get(i).getX().getTipoObjeto().toString();
						regs[i][2] = SoftLost.registos.get(i).getCor().toString();
						regs[i][3] = SoftLost.registos.get(i).getEstado().toString();
						regs[i][4] = SoftLost.registos.get(i).getS().getSala().toString();
						cont++;
					}
					
					dtm = new DefaultTableModel(regs, nomeColunas);
					table = new JTable(dtm);
					
					
					table.setBounds(10, 104, 560, 200);
					table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					table.setPreferredScrollableViewportSize(new Dimension(300, 60));
					table.setFillsViewportHeight(true);
					contentPane.add(table);
					
					
					
					
					//JLABELS
					
					JLabel lblReclamaoDeUm = new JLabel("Reclama\u00E7\u00E3o de um Objeto");
					lblReclamaoDeUm.setForeground(Color.WHITE);
					lblReclamaoDeUm.setFont(new Font("Tahoma", Font.BOLD, 20));
					lblReclamaoDeUm.setBounds(10, 0, 293, 58);
					contentPane.add(lblReclamaoDeUm);
					
					JLabel label_1 = new JLabel("Tipo de Objeto");
					label_1.setForeground(Color.WHITE);
					label_1.setFont(new Font("Tahoma", Font.BOLD, 12));
					label_1.setHorizontalAlignment(SwingConstants.LEFT);
					label_1.setBounds(10, 69, 95, 14);
					contentPane.add(label_1);
					
					JLabel lblObjetosPorReclamar = new JLabel("Objetos por Reclamar: " + cont);
					lblObjetosPorReclamar.setForeground(Color.WHITE);
					lblObjetosPorReclamar.setFont(new Font("Tahoma", Font.BOLD, 12));
					lblObjetosPorReclamar.setBounds(10, 319, 340, 14);
					contentPane.add(lblObjetosPorReclamar);
					
					JLabel label = new JLabel("");
					label.setIcon(new ImageIcon(ReclamaçãoFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
					label.setBounds(-26, -15, 652, 475);
					contentPane.add(label);
			
			
	
			
			
		}
	
	
		public static void atualizaTabelaReclamação(){
			
			
			dtm.setRowCount(0);
			dtm.setColumnCount(0);
			String[] nomeColunas = {"Código", "Objeto", "Cor", "Estado", "Sala"};
			int cont = 0;
			String[][] regs = new String[SoftLost.registos.size()][5];
			for (int i = 0; i < SoftLost.registos.size(); i++) {
				regs[i][0] = Integer.toString(SoftLost.registos.get(i).getCodigo());
				regs[i][1] = SoftLost.registos.get(i).getX().getTipoObjeto().toString();
				regs[i][2] = SoftLost.registos.get(i).getCor().toString();
				regs[i][3] = SoftLost.registos.get(i).getEstado().toString();
				regs[i][4] = SoftLost.registos.get(i).getS().getSala().toString();
				cont++;
			}
			
			for (int j = 0; j < nomeColunas.length; j++) {
				dtm.addColumn(nomeColunas[j]);
			}
			for (int i = 0; i < regs.length; i++) {
				dtm.addRow(regs[i]);
			}
			
		}
	}

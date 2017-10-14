package pt.softlost.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import pt.softlost.controller.ListagensGestão;
import pt.softlost.controller.SoftLost;
import pt.softlost.model.Registo;
import pt.softlost.model.Sala;
import pt.softlost.model.TipoObjeto;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import java.util.Date;
import java.util.Calendar;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

public class ListagensFrame extends JFrame {

	private JPanel contentPane;
	public  static DefaultTableModel dtm;
	private JTable table;
	public static JSpinner spinnerDataI;
	public static JSpinner spinnerDataF;
	
	
	public static JComboBox<String> cbEstado;
	public static JComboBox<String> cbCor;
	public static JComboBox<TipoObjeto> cbTipodeobjeto;
	public static JComboBox<Sala> cbSala;
	public static DefaultComboBoxModel<String> cbModelTipodeobjeto = new DefaultComboBoxModel<String>();
	public static DefaultComboBoxModel<String> cbModelSala = new DefaultComboBoxModel<String>();
	
	
	public ListagensFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ListagensFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		setTitle("Listagens - SoftLost");
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(200, 100, 600, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblListagens = new JLabel("Listagens");
		lblListagens.setForeground(Color.WHITE);
		lblListagens.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblListagens.setBounds(9, 5, 282, 39);
		contentPane.add(lblListagens);
		
		
		//JTable
		
		dtm = new DefaultTableModel();
		
		// Colunas
		dtm.addColumn("Tipo Objeto");
		dtm.addColumn("Estado");
		dtm.addColumn("Descrição");
		
		//Linhas
		
		for (Registo reg : SoftLost.registos) {
			
			dtm.addRow(new Object[]{reg.getX().getTipoObjeto(), reg.getEstado(), reg.getDescrição()});	
		
			
		}
		
		table = new JTable(dtm);
		
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setBounds(10, 108, 321, 134);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setPreferredScrollableViewportSize(new Dimension(500, 50));
		table.setFillsViewportHeight(true);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(10, 120);
		scrollPane.setSize(564, 212);
		
		contentPane.add(scrollPane);
		
		
		
		
		JButton button = new JButton("Voltar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			dispose();
			
			}
		});
		button.setBounds(485, 343, 89, 23);
		contentPane.add(button);
		
		
		

		DefaultComboBoxModel<TipoObjeto> cbModelTipo = new DefaultComboBoxModel<TipoObjeto>();
		
		for (TipoObjeto tp : SoftLost.tipos) {
			
			cbModelTipo.addElement(tp);
			
		}
		
		cbTipodeobjeto = new JComboBox(cbModelTipodeobjeto);
		cbTipodeobjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					ListagensGestão.sortearListagem();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			
			}
		});
		cbTipodeobjeto.setBounds(453, 89, 121, 20);
		contentPane.add(cbTipodeobjeto);
		
		spinnerDataI = new JSpinner();
		spinnerDataI.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
			
			try {
				ListagensGestão.sortearListagem();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
		});
		Date dataI = Calendar.getInstance().getTime();
		spinnerDataI.setModel(new SpinnerDateModel(dataI, null, null, Calendar.DAY_OF_YEAR));
		JSpinner.DateEditor de_spDataI = new JSpinner.DateEditor(spinnerDataI,"dd-MM-yyyy");
		spinnerDataI.setEditor(de_spDataI);
		spinnerDataI.setBounds(267, 31, 89, 23);
		contentPane.add(spinnerDataI);
		
		spinnerDataF = new JSpinner();
		spinnerDataF.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				try {
					ListagensGestão.sortearListagem();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Date dataF = Calendar.getInstance().getTime();
		spinnerDataF.setModel(new SpinnerDateModel(dataF, null, null, Calendar.DAY_OF_YEAR));
		JSpinner.DateEditor de_spDataF = new JSpinner.DateEditor(spinnerDataF,"dd-MM-yyyy");
		spinnerDataF.setEditor(de_spDataF);
		spinnerDataF.setBounds(466, 30, 108, 23);
		contentPane.add(spinnerDataF);
		
		cbCor = new JComboBox<String>();
		
		cbCor.setModel(new DefaultComboBoxModel<String>(new String[] {"","Preto","Branco","Azul","Vermelho","Verde", "Laranja", "Castanho", "Cinzento", "Amarelo", "Violeta"}));
		cbCor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ListagensGestão.sortearListagem();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cbCor.setBounds(306, 89, 121, 20);
		contentPane.add(cbCor);
		
		cbEstado = new JComboBox<String>();
		cbEstado.setModel(new DefaultComboBoxModel<String>(new String[] {"", "Bom", "Mau", "Razo\u00E1vel"}));
		cbEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ListagensGestão.sortearListagem();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cbEstado.setBounds(9, 89, 121, 20);
		contentPane.add(cbEstado);
		
		cbSala = new JComboBox(cbModelSala);
		cbSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ListagensGestão.sortearListagem();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cbSala.setBounds(153, 89, 121, 20);
		contentPane.add(cbSala);
		
		JLabel lblDataDoFim = new JLabel("Data do Fim");
		lblDataDoFim.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDoFim.setForeground(Color.WHITE);
		lblDataDoFim.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataDoFim.setBounds(366, 31, 89, 23);
		contentPane.add(lblDataDoFim);
		
		JLabel lblDataDoIncio = new JLabel("Data do In\u00EDcio");
		lblDataDoIncio.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDataDoIncio.setForeground(Color.WHITE);
		lblDataDoIncio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDataDoIncio.setBounds(170, 32, 89, 23);
		contentPane.add(lblDataDoIncio);
		
		JLabel lblEstado = new JLabel("Estado");
		lblEstado.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblEstado.setBounds(9, 67, 121, 23);
		contentPane.add(lblEstado);
		
		JLabel lblSala = new JLabel("Sala");
		lblSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblSala.setForeground(Color.WHITE);
		lblSala.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblSala.setBounds(153, 66, 121, 23);
		contentPane.add(lblSala);
		
		JLabel lblCor = new JLabel("Cor");
		lblCor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCor.setForeground(Color.WHITE);
		lblCor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCor.setBounds(306, 65, 121, 23);
		contentPane.add(lblCor);
		
		JLabel lblTipoObjeto = new JLabel("Tipo Objeto");
		lblTipoObjeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoObjeto.setForeground(Color.WHITE);
		lblTipoObjeto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoObjeto.setBounds(453, 65, 121, 23);
		contentPane.add(lblTipoObjeto);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(ListagensFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
		label_1.setBounds(-78, -11, 698, 437);
		contentPane.add(label_1);
		
		
		
	}
}

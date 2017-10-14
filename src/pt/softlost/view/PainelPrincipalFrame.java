package pt.softlost.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pt.softlost.controller.ListagensGestão;
import pt.softlost.controller.SoftLost;

public class PainelPrincipalFrame extends JFrame {

	private JPanel contentPane;
	public static JMenu mnRegisto;
	public static JMenu mnImportao;
	public static JMenu mnReclamao;
	public static JMenu mnListagens;
	public static JMenu mnDoaes;
	public static JMenu mnGesto;


	public PainelPrincipalFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(PainelPrincipalFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		setResizable(false);
		setTitle("SoftLost - Perdidos e Achados ");
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(200, 100, 600, 335);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//***************************************************************************************************************************
		
		
		
		//Label's 
		
		//MENU PRINCIPAL
		
		JLabel lblSoftlostPerdidos = new JLabel("SoftLost - Perdidos e Achados");
		lblSoftlostPerdidos.setForeground(Color.WHITE);
		lblSoftlostPerdidos.setBackground(Color.WHITE);
		lblSoftlostPerdidos.setBounds(10, 11, 574, 41);
		lblSoftlostPerdidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblSoftlostPerdidos.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblSoftlostPerdidos);
		
		//NOMEUTILIZADOR
		JLabel lblNomeutilizador = new JLabel(LoginFrame.txtUtilizador.getText()); 
		lblNomeutilizador.setForeground(Color.WHITE);
		lblNomeutilizador.setBounds(283, 149, 230, 14);
		lblNomeutilizador.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNomeutilizador.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNomeutilizador);
		
		//TIPOUTILIZADOR
		JLabel lbl_TipoUtilizador = new JLabel();
		lbl_TipoUtilizador.setForeground(Color.WHITE);
		lbl_TipoUtilizador.setBounds(344, 212, 230, 23);
		lbl_TipoUtilizador.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbl_TipoUtilizador.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lbl_TipoUtilizador);
		
		//BUTTON'S
		
		//Terminar Sessão
		JButton btnTerminarSesso = new JButton("Terminar Sess\u00E3o");
		btnTerminarSesso.setBounds(440, 262, 134, 23);
		btnTerminarSesso.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame p = new LoginFrame();
				p.setVisible(true);
				
				dispose();
				
			try {
				SoftLost.saveInstituições();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				SoftLost.saveSalas();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				SoftLost.saveUtilizdor();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				SoftLost.saveTiposObjetos();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			try {
				SoftLost.saveRegistos();
			} catch (ParseException | IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			
			
			
			
			
			}});
		contentPane.add(btnTerminarSesso);
		
		JLabel label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setIcon(new ImageIcon(PainelPrincipalFrame.class.getResource("/pt/softlost/res/SoftLost_Logos Painel.png")));
		label_2.setBounds(31, 104, 182, 167);
		contentPane.add(label_2);
		
		JLabel label_1 = new JLabel("Bem Vindo ao SoftLost!!!");
		label_1.setBounds(223, 113, 345, 25);
		contentPane.add(label_1);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		//---------------------------
		//JMenuBar e Seu componentes
		//---------------------------

		//***************************************************************************************************************************
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		menuBar.setBounds(94, 63, 414, 30);
		contentPane.add(menuBar);
		
		mnRegisto = new JMenu("Registo");
		mnRegisto.setForeground(Color.BLACK);
		mnRegisto.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnRegisto.addMouseListener(new MouseAdapter() {	
			public void mouseClicked(MouseEvent e) {
			RegistoFrame r = new RegistoFrame();
			r.setVisible(true);}});
		menuBar.add(mnRegisto);
		
		
		
		mnImportao = new JMenu("Importa\u00E7\u00E3o");
		mnImportao.setForeground(Color.BLACK);
		mnImportao.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnImportao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ImportaçaoFrame ifr = new ImportaçaoFrame();
				ifr.setVisible(true);
			}
		});
		menuBar.add(mnImportao);
		
		mnReclamao = new JMenu("Reclama\u00E7\u00E3o");
		mnReclamao.setForeground(Color.BLACK);
		mnReclamao.setFont(new Font("Tahoma", Font.BOLD, 12));
		mnReclamao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				ReclamaçãoFrame f = new ReclamaçãoFrame();
				f.setVisible(true);
			
			}
		});
		
			menuBar.add(mnReclamao);
			
			mnListagens = new JMenu("Listagens");
			mnListagens.setForeground(Color.BLACK);
			mnListagens.setFont(new Font("Tahoma", Font.BOLD, 12));
			mnListagens.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					try
					{	
						
						
						
						ListagensGestão.criarCombobox();
						
						ListagensFrame l = new ListagensFrame();
						l.setVisible(true);
						ListagensGestão.criarTabela();
						ListagensGestão.spinnerDate();
						
					}catch(Exception e)
					{
						
					}
					
					
				
				}
			});
			
					
			
				menuBar.add(mnListagens);
				
				mnDoaes = new JMenu("Doa\u00E7\u00F5es");
				mnDoaes.setForeground(Color.BLACK);
				mnDoaes.setFont(new Font("Tahoma", Font.BOLD, 12));
				mnDoaes.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
					DoaçõesFrame d = new DoaçõesFrame();
					d.setVisible(true);
								}});
				menuBar.add(mnDoaes);
				
				mnGesto = new JMenu("Gest\u00E3o");
				mnGesto.setForeground(Color.BLACK);
				mnGesto.setFont(new Font("Tahoma", Font.BOLD, 12));
				menuBar.add(mnGesto);
				
				JMenuItem mntmGestoUtilizadores = new JMenuItem("Gest\u00E3o Utilizadores");
				mntmGestoUtilizadores.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					
						GestãoUtilizadoresFrame f = new GestãoUtilizadoresFrame();
						f.setVisible(true);}});
				mnGesto.add(mntmGestoUtilizadores);
				
				JMenuItem mntmGestoSalas = new JMenuItem("Gest\u00E3o Salas");
				mntmGestoSalas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestãoSalasFrame f = new GestãoSalasFrame();
						f.setVisible(true);}});
				mnGesto.add(mntmGestoSalas);
				
				JMenuItem mntmGesto = new JMenuItem("Gest\u00E3o Tipo Objetos");
				mntmGesto.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestãoTiposObjetosFrame f = new GestãoTiposObjetosFrame();
						f.setVisible(true);}});
				mnGesto.add(mntmGesto);
				
				JMenuItem mntmGestoInstituies = new JMenuItem("Gest\u00E3o Institui\u00E7\u00F5es");
				mntmGestoInstituies.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						GestaoInstituicoesFrame f = new GestaoInstituicoesFrame();
						f.setVisible(true);}});
				mnGesto.add(mntmGestoInstituies);
				
				JLabel label = new JLabel("");
				label.setIcon(new ImageIcon(PainelPrincipalFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
				label.setBounds(-12, 0, 606, 321);
				contentPane.add(label);
		
		
		
			
		
		
		
	}
		}

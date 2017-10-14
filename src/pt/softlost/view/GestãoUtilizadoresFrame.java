package pt.softlost.view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPasswordField;

import java.awt.Toolkit;
import pt.softlost.controller.SoftLost;
import pt.softlost.controller.UtilizadorGestão;
import pt.softlost.model.Instituicao;
import pt.softlost.model.Utilizador;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;


public class GestãoUtilizadoresFrame extends JFrame {

	
	private JPanel contentPane;
	private JTextField txtUsername;
	JLabel lblInstituiao;
	private JPasswordField txtPassword1;
	private JPasswordField txtPassword2;
	private JTable table;
	
	public JCheckBox chckbxRegisto;
	public JCheckBox chckbxReclamao;
	public JCheckBox chckbxImportao;
	public JCheckBox chckbxGesto;
	public JCheckBox chckbxListagens;
	public JCheckBox chckbxDoaes;
	
	JComboBox<String> cmbTipoUser;
	JComboBox<Instituicao> cbInstituições;
	public static DefaultTableModel tablemodelConfigUser = new DefaultTableModel();
	public static DefaultComboBoxModel<String> tiposUser = new DefaultComboBoxModel<String>();
	private JLabel label;


	public GestãoUtilizadoresFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestãoUtilizadoresFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		
		setTitle("Gest\u00E3o Utilizadores - SoftLost");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 613, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUtilizador = new JLabel("Utilizador");
		lblUtilizador.setForeground(Color.WHITE);
		lblUtilizador.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUtilizador.setBounds(10, 53, 89, 14);
		contentPane.add(lblUtilizador);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(114, 50, 160, 20);
		txtUsername.setColumns(10);
		contentPane.add(txtUsername);
		
		if (!txtUsername.getText().isEmpty()) {
			
			cmbTipoUser.setEnabled(true);
			chckbxRegisto.setEnabled(true);
			chckbxReclamao.setEnabled(true);
			chckbxImportao.setEnabled(true);
			chckbxGesto.setEnabled(true);
			chckbxListagens.setEnabled(true);
			chckbxDoaes.setEnabled(true);
			
		}
		
		JLabel lblGestoDeUtilizadores = new JLabel("Gest\u00E3o de Utilizadores");
		lblGestoDeUtilizadores.setForeground(Color.WHITE);
		lblGestoDeUtilizadores.setBounds(10, 6, 264, 33);
		lblGestoDeUtilizadores.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblGestoDeUtilizadores);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.setBounds(10, 218, 103, 23);
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String username = txtUsername.getText();
				String password1 = txtPassword1.getText();
				String password2 = txtPassword2.getText();
				
				if (txtPassword1.getText().isEmpty() && txtPassword2.getText().isEmpty() && txtUsername.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
				}
				else{
					if (cmbTipoUser.getSelectedItem().toString().equals("Institui\u00E7\u00E3o")) {
						
						UtilizadorGestão.adicionarUser(username, password1, password2,cmbTipoUser.getSelectedItem().toString(), chckbxRegisto.isSelected(), chckbxReclamao.isSelected(), chckbxImportao.isSelected(), chckbxListagens.isSelected(), chckbxDoaes.isSelected(), chckbxGesto.isSelected());

						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
					}
					if (cmbTipoUser.getSelectedItem().toString().equals("Seguran\u00E7a")) {
						
						
						UtilizadorGestão.adicionarUser(username, password1, password2,cmbTipoUser.getSelectedItem().toString(), chckbxRegisto.isSelected(), chckbxReclamao.isSelected(), chckbxImportao.isSelected(), chckbxListagens.isSelected(), chckbxDoaes.isSelected(), chckbxGesto.isSelected());

						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
					}
					if (cmbTipoUser.getSelectedItem().toString().equals("Administrador")) {
						
						UtilizadorGestão.adicionarUser(username, password1, password2,cmbTipoUser.getSelectedItem().toString(), chckbxRegisto.isSelected(), chckbxReclamao.isSelected(), chckbxImportao.isSelected(), chckbxListagens.isSelected(), chckbxDoaes.isSelected(), chckbxGesto.isSelected());

						JOptionPane.showMessageDialog(null, "Inserido com Sucesso");
					}
					
					
					
					tabelaUtilizador();
					txtPassword1.setText("");
					txtPassword2.setText("");
					txtUsername.setText("");	
					
					
				}
				
				
				try {
					SoftLost.saveUtilizdor();
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
			}
		});
		contentPane.add(btnInserir);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBounds(242, 218, 103, 23);
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					
				UtilizadorGestão.removerUser(table.getValueAt(table.getSelectedRow(), 0).toString());
				tabelaUtilizador();
				
				txtPassword1.setText("");
				txtPassword2.setText("");
				txtUsername.setText("");
				chckbxRegisto.setSelected(false);
				chckbxReclamao.setSelected(false);
				chckbxImportao.setSelected(false);
				chckbxGesto.setSelected(false);
				chckbxListagens.setSelected(false);
				chckbxDoaes.setSelected(false);
				try {
					SoftLost.saveUtilizdor();
					JOptionPane.showMessageDialog(null, "Removido com Sucesso");
				} catch (FileNotFoundException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				
				
			}
		});
		contentPane.add(btnRemover);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.setBounds(355, 218, 103, 23);
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				dispose();
			
			}
		});
		contentPane.add(btnVoltar);
		
		txtPassword1 = new JPasswordField();
		txtPassword1.setBounds(114, 81, 160, 20);
		contentPane.add(txtPassword1);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setBounds(10, 84, 67, 14);
		contentPane.add(lblPassword);
		
		txtPassword2 = new JPasswordField();
		txtPassword2.setBounds(114, 112, 160, 20);
		contentPane.add(txtPassword2);
		
		JLabel lblConfirmarPassword = new JLabel("Confirmar Pass.");
		lblConfirmarPassword.setForeground(Color.WHITE);
		lblConfirmarPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblConfirmarPassword.setBounds(10, 115, 89, 14);
		lblConfirmarPassword.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(lblConfirmarPassword);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(471, 50, 116, 191);
		contentPane.add(scrollPane);
		
		table = new JTable(tablemodelConfigUser);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				txtUsername.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
				
			}
		});
		scrollPane.setViewportView(table);
		
	
		
		chckbxRegisto = new JCheckBox("Registo");
		chckbxRegisto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxRegisto.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxRegisto.setBounds(10, 143, 142, 23);
		chckbxRegisto.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(chckbxRegisto);
		
		chckbxReclamao = new JCheckBox("Reclama\u00E7\u00E3o");
		chckbxReclamao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxReclamao.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxReclamao.setBounds(10, 169, 142, 23);
		chckbxReclamao.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(chckbxReclamao);
		
		chckbxImportao = new JCheckBox("Importa\u00E7\u00E3o");
		chckbxImportao.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxImportao.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxImportao.setBounds(154, 143, 156, 23);
		contentPane.add(chckbxImportao);
		
		chckbxGesto = new JCheckBox("Gest\u00E3o");
		chckbxGesto.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxGesto.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxGesto.setBounds(312, 143, 153, 23);
		chckbxGesto.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(chckbxGesto);
		
		chckbxListagens = new JCheckBox("Listagens");
		chckbxListagens.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxListagens.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxListagens.setBounds(154, 169, 156, 23);
		contentPane.add(chckbxListagens);
		
		chckbxDoaes = new JCheckBox("Doa\u00E7\u00F5es");
		chckbxDoaes.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chckbxDoaes.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxDoaes.setBounds(312, 169, 153, 23);
		chckbxDoaes.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(chckbxDoaes);
		

		cmbTipoUser = new JComboBox<String>(new DefaultComboBoxModel<String>(new String[] {"Administrador", "Seguran\u00E7a", "Institui\u00E7\u00E3o"}));
		cmbTipoUser.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cmbTipoUser.setBounds(284, 50, 181, 20);
		cmbTipoUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if (cmbTipoUser.getSelectedItem().toString().equals("Institui\u00E7\u00E3o")) { 
					cbInstituições.setEnabled(true);
					lblInstituiao.setEnabled(true);
					}
				if (!cmbTipoUser.getSelectedItem().toString().equals("Institui\u00E7\u00E3o")) { 
					cbInstituições.setEnabled(false);
					lblInstituiao.setEnabled(false);
					}
			
			}
		});
		contentPane.add(cmbTipoUser);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.setBounds(123, 218, 103, 23);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String usernameVelho =  table.getValueAt(table.getSelectedRow(), 0).toString();
				String usernameNovo = txtUsername.getText();
				String password1 = txtPassword1.getText();
				String password2 = txtPassword2.getText();
				String tipoUser = cmbTipoUser.getSelectedItem().toString();
				
			if (txtPassword1.getText().isEmpty() && txtPassword2.getText().isEmpty() && txtUsername.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
			}
			else{
				
			
			if (cmbTipoUser.getSelectedItem().toString().equals("Institui\u00E7\u00E3o")) {
				
				
				UtilizadorGestão.editarUser(usernameVelho, usernameNovo, password1, password2, tipoUser,cbInstituições.getSelectedItem().toString(), chckbxRegisto.isSelected(), chckbxReclamao.isSelected(), chckbxImportao.isSelected(), chckbxListagens.isSelected(), chckbxDoaes.isSelected(), chckbxGesto.isSelected());

				JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
			}
			if (cmbTipoUser.getSelectedItem().toString().equals("Seguran\u00E7a")) {
				
				
				UtilizadorGestão.editarUser(usernameVelho, usernameNovo, password1, password2, tipoUser,cmbTipoUser.getSelectedItem().toString(), chckbxRegisto.isSelected(), chckbxReclamao.isSelected(), chckbxImportao.isSelected(), chckbxListagens.isSelected(), chckbxDoaes.isSelected(), chckbxGesto.isSelected());

				JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
			}
			if (cmbTipoUser.getSelectedItem().toString().equals("Administrador")) {
				
				
				
				UtilizadorGestão.editarUser(usernameVelho, usernameNovo, password1, password2, tipoUser,cmbTipoUser.getSelectedItem().toString(), chckbxRegisto.isSelected(), chckbxReclamao.isSelected(), chckbxImportao.isSelected(), chckbxListagens.isSelected(), chckbxDoaes.isSelected(), chckbxGesto.isSelected());

				JOptionPane.showMessageDialog(null, "Alterado com Sucesso");
			}
			
			txtPassword1.setText("");
			txtPassword2.setText("");
			txtUsername.setText("");
			}
			try {
				SoftLost.saveUtilizdor();
			} catch (FileNotFoundException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
			
			}
		});
		contentPane.add(btnAlterar);
		
		lblInstituiao = new JLabel("Institui\u00E7ao");
		lblInstituiao.setForeground(Color.WHITE);
		lblInstituiao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstituiao.setBounds(284, 82, 181, 19);
		lblInstituiao.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstituiao.setEnabled(false);
		contentPane.add(lblInstituiao);
		
		DefaultComboBoxModel<Instituicao> cbModelInstituiçoes = new DefaultComboBoxModel<Instituicao>();
		
		for (Instituicao i : SoftLost.instituicoes) {
			
			cbModelInstituiçoes.addElement(i);
		}
		
		
		cbInstituições = new JComboBox<Instituicao>(cbModelInstituiçoes);
		cbInstituições.setBounds(284, 112, 181, 20);
		cbInstituições.setEnabled(false);
		contentPane.add(cbInstituições);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(GestãoUtilizadoresFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
		label.setBounds(0, 0, 668, 252);
		contentPane.add(label);
		
		
		
		tabelaUtilizador();
		
		
		
		//JLIST
		DefaultListModel<String> listModel = new DefaultListModel<String>();
		
		for (Utilizador u: SoftLost.users) {
			listModel.addElement(u.getUsername());
		}
	}

	public static void tabelaUtilizador()
	{
		tablemodelConfigUser.setRowCount(0);
		tablemodelConfigUser.setColumnCount(0);
		tablemodelConfigUser.addColumn("Username");
		
		for(Utilizador user : SoftLost.users)
		{
			GestãoUtilizadoresFrame.tablemodelConfigUser.addRow(new Object[]{user.getUsername()});
		}
	}


	
}

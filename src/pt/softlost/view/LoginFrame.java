package pt.softlost.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import pt.softlost.controller.UtilizadorGestão;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	public static  JTextField txtUtilizador;
	private JPasswordField txtPassword;
	private JButton btnLogin;
	public static JLabel dataLabel;
	private JLabel label;

	
	public LoginFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		setResizable(false);
		setTitle("Login - SoftLost");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 571, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInicioSesso = new JLabel("INICIAR SESS\u00C3O");
		lblInicioSesso.setForeground(Color.WHITE);
		lblInicioSesso.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblInicioSesso.setBounds(21, 11, 290, 42);
		contentPane.add(lblInicioSesso);
		
		JLabel lblUtilizador = new JLabel("Utilizador");
		lblUtilizador.setForeground(Color.WHITE);
		lblUtilizador.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblUtilizador.setHorizontalAlignment(SwingConstants.LEFT);
		lblUtilizador.setBounds(21, 88, 59, 21);
		contentPane.add(lblUtilizador);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPassword.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword.setBounds(21, 131, 74, 21);
		contentPane.add(lblPassword);
		
		txtUtilizador = new JTextField();
		txtUtilizador.setBounds(21, 109, 221, 20);
		contentPane.add(txtUtilizador);
		txtUtilizador.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER){
				
					if (txtUtilizador.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Insira um Nome de Utilizador!");
						
					}
					else{
						if (txtPassword.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Insira uma Palavra Passe!");
						}
						else{
							if (UtilizadorGestão.isUser(txtUtilizador.getText(), txtPassword.getText()) == true) {
						
						PainelPrincipalFrame f = new PainelPrincipalFrame();
						f.setVisible(true);
						dispose();
						UtilizadorGestão.Permissoes(txtUtilizador.getText());
					} else {
						
						txtUtilizador.setText("");
						txtPassword.setText("");
						JOptionPane.showMessageDialog(null, "Nome de Utilizador ou Palavra Passe Incorretos!");
					}
						}
						
					}
					
				}
				
			}
		});
		txtPassword.setEchoChar('*');
		txtPassword.setBounds(21, 151, 221, 20);
		contentPane.add(txtPassword);
		
		
		btnLogin = new JButton("Iniciar Sessão");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
		
				if (txtUtilizador.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Insira um Nome de Utilizador!");
				}
				else{
					if (txtPassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Insira uma Palavra Passe!");
					}
					else{
						if (UtilizadorGestão.isUser(txtUtilizador.getText(), txtPassword.getText()) == true) {
					
					PainelPrincipalFrame f = new PainelPrincipalFrame();
					f.setVisible(true);
					dispose();
					UtilizadorGestão.Permissoes(txtUtilizador.getText());
				} else {
					
					txtUtilizador.setText("");
					txtPassword.setText("");
					JOptionPane.showMessageDialog(null, "Nome de Utilizador ou Palavra Passe Incorretos!");
				}
					}
					
				}
				
				
			}
		});
		btnLogin.setBounds(89, 185, 153, 23);
		contentPane.add(btnLogin);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setIcon(new ImageIcon(LoginFrame.class.getResource("/pt/softlost/res/SoftLost_Logos Painel.png")));
		label.setBounds(252, 36, 303, 194);
		contentPane.add(label);
		
		dataLabel = new JLabel("");
		dataLabel.setIcon(new ImageIcon(LoginFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
		dataLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		dataLabel.setBounds(-26, -56, 707, 479);
		contentPane.add(dataLabel);
	}
}

package pt.softlost.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import pt.softlost.controller.ImportacaoGestao;
import pt.softlost.controller.SoftLost;

import javax.swing.JTable;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class Importa�aoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtEndere�o;
    public File file;
    final JFileChooser fc;
    public static DefaultListModel<String> listModel2 ;
    
	public Importa�aoFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Importa�aoFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		setTitle("Importar - SoftLost");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 454, 150);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtEndere�o = new JTextField(); 
		txtEndere�o.setBounds(10, 70, 315, 20);
		contentPane.add(txtEndere�o);
		txtEndere�o.setColumns(10);


		//JFileChooser
		fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		
		if (returnVal == 0) {
			
			file = fc.getSelectedFile();
			txtEndere�o.setText(file.toString());
			
		}
		
		else {
		dispose();
		}
		
		
		JButton btnNewButton = new JButton("Importar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			//Chamar m�todo de importar
				
				
			String endere�o = txtEndere�o.getText();
				try {
						ImportacaoGestao.importar(endere�o);
				} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e.getMessage());
				}
					
				
					try {
						SoftLost.saveRegistos();
					} catch (ParseException | IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			
			}
		});
		btnNewButton.setBounds(335, 69, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblImportarFicheiro = new JLabel("Importar Ficheiro");
		lblImportarFicheiro.setForeground(Color.WHITE);
		lblImportarFicheiro.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblImportarFicheiro.setBounds(10, 11, 414, 48);
		contentPane.add(lblImportarFicheiro);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Importa�aoFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
		label.setBounds(-72, -20, 577, 147);
		contentPane.add(label);
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}

package pt.softlost.view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JList;

import pt.softlost.controller.SoftLost;
import pt.softlost.controller.TipoObjetoGestão;
import pt.softlost.model.Instituicao;
import pt.softlost.model.TipoObjeto;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.ListSelectionModel;


public class GestãoTiposObjetosFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtObjeto;
	public static DefaultListModel<String> listModel = new DefaultListModel<String>();
	public static JLabel lblNewLabel; 
	
	public GestãoTiposObjetosFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestãoTiposObjetosFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		setTitle("SoftLost - Sistemas de Perdidos e Achados");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 512, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		DefaultComboBoxModel<Instituicao> cbModelInstituiçoes = new DefaultComboBoxModel<Instituicao>();
		
		for (Instituicao i : SoftLost.instituicoes) {
			
			cbModelInstituiçoes.addElement(i);
		}
		
		
		JComboBox<Instituicao> cbInstituições = new JComboBox<Instituicao>(cbModelInstituiçoes);
		cbInstituições.setBounds(10, 136, 264, 20);
		contentPane.add(cbInstituições);
		
		
		JLabel lblGestoDeTipo = new JLabel("Gest\u00E3o de Tipo de Objetos");
		lblGestoDeTipo.setForeground(Color.WHITE);
		lblGestoDeTipo.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGestoDeTipo.setBounds(10, 0, 264, 67);
		contentPane.add(lblGestoDeTipo);
		
		JLabel lblTipoDeObjeto = new JLabel("Tipo de Objeto");
		lblTipoDeObjeto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTipoDeObjeto.setForeground(Color.WHITE);
		lblTipoDeObjeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTipoDeObjeto.setBounds(10, 60, 264, 14);
		contentPane.add(lblTipoDeObjeto);
		
		txtObjeto = new JTextField();
		txtObjeto.setColumns(10);
		txtObjeto.setBounds(10, 78, 264, 20);
		contentPane.add(txtObjeto);
		
		JButton button = new JButton("Inserir");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String to = txtObjeto.getText().toString();
				Instituicao i = (Instituicao) cbInstituições.getSelectedItem();
				if (txtObjeto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Prencha o campo tipo objeto!");
				}
				else{
					if (TipoObjetoGestão.adicionarObjeto(to, i)) {
					
					JOptionPane.showMessageDialog(null, "O Tipo de Objeto " + to +  " foi registado com sucesso.");	
					
					listModel.setSize(0);
					for (TipoObjeto to2 : SoftLost.tipos) {
						listModel.addElement(to2.getTipoObjeto());
					}
					txtObjeto.setText("");
					
					try {
						SoftLost.saveTiposObjetos();
					} catch (FileNotFoundException e1) {
				
						JOptionPane.showMessageDialog(null, e1);
					}
					
				}
				else 
				{
				JOptionPane.showMessageDialog(null, "O Tipo de Objeto " + to +  " ja se encontra registado.");
				}
				}
				
					
				
				
			
			}
		});
		button.setBounds(10, 227, 112, 23);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Remover");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String to = txtObjeto.getText().toString();
				if (txtObjeto.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Prencha o campo tipo objeto!");
				}
				else{
				if (!TipoObjetoGestão.removerObjeto(to)) {
					JOptionPane.showMessageDialog(null, "A Objeto " + to +  " foi eliminado com sucesso.");
					
					
				listModel.setSize(0);
				for (TipoObjeto to2 : SoftLost.tipos) {
					listModel.addElement(to2.getTipoObjeto());
				}
				txtObjeto.setText("");
				
				try {
					SoftLost.saveTiposObjetos();
				} catch (FileNotFoundException e1) {
			
					JOptionPane.showMessageDialog(null, e1);
				}
				}
				else{
					JOptionPane.showMessageDialog(null, "A Objeto " + to +  " nao se encontra registada.");
				}
				}
				
				
			}
		});
		button_1.setBounds(254, 227, 112, 23);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("Voltar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				
				dispose();
			
			}
		});
		button_2.setBounds(376, 227, 112, 23);
		contentPane.add(button_2);
		
		
		//JLIST
		
		
		for (TipoObjeto to : SoftLost.tipos) {
			listModel.addElement(to.getTipoObjeto());
		}
		
		JList<String> list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				txtObjeto.setText(list.getSelectedValue().toString());
				
				int i = list.getSelectedIndex();
				String inst = SoftLost.instituicoes.get(i).getInstituicao();
				lblNewLabel.setText("Associado a : " + inst );
				lblNewLabel.setVisible(true); 
			}
		});
		list.setBounds(300, 60, 188, 158);
		contentPane.add(list);
		
		
		
		
		JLabel lblInstituio = new JLabel("Institui\u00E7\u00E3o");
		lblInstituio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstituio.setForeground(Color.WHITE);
		lblInstituio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstituio.setBounds(10, 109, 264, 20);
		contentPane.add(lblInstituio);
		
		JButton btnAlterar = new JButton("Alterar");
		
		
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			
			String to = (String) txtObjeto.getText().toString();
			Instituicao inst = (Instituicao) cbInstituições.getSelectedItem();
			int codigo = TipoObjetoGestão.buscarCodigoAnterior();
			
			if (txtObjeto.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Prencha o campo tipo objeto!");
			}
			else{
			if (TipoObjetoGestão.alterarObjeto(codigo,to, inst)) {
				
				JOptionPane.showMessageDialog(null, "O Objeto " + to +  " foi alterado com sucesso.");
			
				
			listModel.setSize(0);
			for (TipoObjeto to2 : SoftLost.tipos) {
				listModel.addElement(to2.getTipoObjeto());
			}
			txtObjeto.setText("");
			
			try {
				SoftLost.saveTiposObjetos();
			} catch (FileNotFoundException e1) {
		
				JOptionPane.showMessageDialog(null, e1);
			}
			}
			else{
				
				JOptionPane.showMessageDialog(null, "O Objeto " + to +  " nao se encontra registada.");
			}
			}
			
			}
		});
		btnAlterar.setBounds(132, 227, 112, 23);
		contentPane.add(btnAlterar);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 167, 238, 14);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GestãoTiposObjetosFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
		label.setBounds(-74, -11, 825, 370);
		contentPane.add(label);
		
		
		
	}
}


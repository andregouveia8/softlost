package pt.softlost.view;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JTextField;
import java.awt.Toolkit;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.border.LineBorder;

import pt.softlost.controller.InstituiçõesGestão;
import pt.softlost.controller.SoftLost;
import pt.softlost.model.Instituicao;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;


public class GestaoInstituicoesFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtInstituicao;
	public static DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JTextField txtInstituiçaoAlterada;

	public GestaoInstituicoesFrame() {
		setResizable(false);
		setTitle("SoftLost - Sistema de Perdidos e Achados");
		setIconImage(Toolkit.getDefaultToolkit().getImage(GestaoInstituicoesFrame.class.getResource("/pt/softlost/res/iconAplic.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnInserir = new JButton("Inserir");
		btnInserir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String instituicaoNova = txtInstituicao.getText().toString();
				
				try {
					if(InstituiçõesGestão.adicionarInstituicao(instituicaoNova)) {
					
						JOptionPane.showMessageDialog(null, "A institução " + instituicaoNova +  " foi registada com sucesso.");	
						
						atualizalist();
						
					} else {
						JOptionPane.showMessageDialog(null, "A institução " + instituicaoNova+  " já se encontra registada.");
					}
				} catch (HeadlessException | IOException e1) {
					
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				
				
			}
		});
		btnInserir.setBounds(91, 226, 89, 23);
		contentPane.add(btnInserir);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				String inst = txtInstituicao.getText().toString();
				
				if (!InstituiçõesGestão.removerInstituicao(inst)) {
					
					JOptionPane.showMessageDialog(null, "A Instituição " + inst +  " foi eliminada com sucesso.");
					
					atualizalist();	
				
				}
				else{
					JOptionPane.showMessageDialog(null, "A Instituição " + inst +  " nao se encontra registada.");
				}
				
						
			
			}
		});
		btnRemover.setBounds(289, 226, 89, 23);
		contentPane.add(btnRemover);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			dispose();
			
			}
		});
		btnVoltar.setBounds(385, 226, 89, 23);
		contentPane.add(btnVoltar);
		
		JLabel lblGestoDeInstituies = new JLabel("Gest\u00E3o de Institui\u00E7\u00F5es");
		lblGestoDeInstituies.setForeground(Color.WHITE);
		lblGestoDeInstituies.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblGestoDeInstituies.setBounds(10, 0, 264, 59);
		contentPane.add(lblGestoDeInstituies);
		
		JLabel lblInstituio = new JLabel("Institui\u00E7\u00E3o");
		lblInstituio.setForeground(Color.WHITE);
		lblInstituio.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstituio.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstituio.setBounds(10, 70, 264, 14);
		contentPane.add(lblInstituio);
		
		txtInstituicao = new JTextField();
		txtInstituicao.setHorizontalAlignment(SwingConstants.CENTER);
		txtInstituicao.setColumns(10);
		txtInstituicao.setBounds(10, 95, 264, 20);
		contentPane.add(txtInstituicao);
		
		//JLIST
		
		
		
		for (Instituicao i : SoftLost.instituicoes) {
			listModel.addElement(i.getInstituicao());
		}
		
		JList<String> list = new JList<String>(listModel);
		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtInstituicao.setText(list.getSelectedValue().toString());
			}
		});
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(289, 46, 185, 156);
		contentPane.add(list);
		
		JLabel lblInstituição2 = new JLabel("Altera\u00E7\u00E3o Institui\u00E7\u00E3o");
		lblInstituição2.setForeground(Color.WHITE);
		lblInstituição2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblInstituição2.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstituição2.setBounds(10, 126, 264, 14);
		contentPane.add(lblInstituição2);
		
		txtInstituiçaoAlterada = new JTextField();
		txtInstituiçaoAlterada.setHorizontalAlignment(SwingConstants.CENTER);
		txtInstituiçaoAlterada.setBounds(10, 151, 264, 20);
		contentPane.add(txtInstituiçaoAlterada);
		txtInstituiçaoAlterada.setColumns(10);
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String inst = txtInstituicao.getText().toString();
				String inst2 = txtInstituiçaoAlterada.getText().toString();
				if (txtInstituicao.getText().isEmpty() && txtInstituiçaoAlterada.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha os Campos!");
				}
				else{
					
				
				if (InstituiçõesGestão.alterarInstituiçao(inst, inst2)){
					JOptionPane.showMessageDialog(null, "A Instituição " + inst +  " foi alterada com sucesso.");
					
					atualizalist();
				
				}
				else{
					JOptionPane.showMessageDialog(null, "A Instituição " + inst +  " nao se encontra registada.");
				}
				
				}
			
			}
		});
		btnAlterar.setBounds(190, 226, 89, 23);
		contentPane.add(btnAlterar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(GestaoInstituicoesFrame.class.getResource("/pt/softlost/res/Sem t\u00EDtulo-1.png")));
		label.setBounds(0, -48, 809, 350);
		contentPane.add(label);
	}
	public static void atualizalist(){
		
		listModel.setSize(0);
		for (Instituicao i : SoftLost.instituicoes) {
			listModel.addElement(i.getInstituicao());
		}
	}
}

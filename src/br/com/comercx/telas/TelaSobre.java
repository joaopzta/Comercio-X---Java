package br.com.comercx.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.awt.Font;
import javax.swing.ImageIcon;

public class TelaSobre extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaSobre frame = new TelaSobre();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TelaSobre() {
		setResizable(false);
		setTitle("Sobre o Sistema");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 565, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		this.setVisible(false);
		
		
		JLabel lblEsteProjeto = new JLabel("Software para controle de dados de um com\u00E9rcio gen\u00E9rico");
		lblEsteProjeto.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEsteProjeto.setBounds(26, 61, 408, 23);
		contentPane.add(lblEsteProjeto);
		
		JLabel lblProjetoParaFins = new JLabel("Projeto para fins did\u00E1ticos");
		lblProjetoParaFins.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProjetoParaFins.setBounds(26, 95, 193, 23);
		contentPane.add(lblProjetoParaFins);
		
		JLabel lblDesenvolvidoPorJoo = new JLabel("Desenvolvido por Jo\u00E3o Pedro Zanqueta");
		lblDesenvolvidoPorJoo.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblDesenvolvidoPorJoo.setBounds(26, 225, 277, 23);
		contentPane.add(lblDesenvolvidoPorJoo);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaSobre.class.getResource("/br/com/comercx/icone/owl.png")));
		label.setBounds(368, 158, 138, 131);
		contentPane.add(label);
		
		JLabel lblNomesTotalmenteFictcios = new JLabel("Nomes totalmente fict\u00EDcios");
		lblNomesTotalmenteFictcios.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNomesTotalmenteFictcios.setBounds(26, 129, 193, 23);
		contentPane.add(lblNomesTotalmenteFictcios);
	}
}

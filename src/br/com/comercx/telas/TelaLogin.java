package br.com.comercx.telas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
//import java.awt.Image;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

import br.com.comercx.conexao.*;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

public class TelaLogin extends JFrame {

	Connection con = ModuloConexao.getConection();
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtSenha;
	private JLabel lblStatus;
	
	@SuppressWarnings("deprecation")
	public void logar() {
		String sql = "select * from usuario where login=? and senha=?";
		
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, txtUsuario.getText());
			stmt.setString(2, txtSenha.getText());
			rs = stmt.executeQuery();
			if(rs.next()) {
				String perfil = rs.getString(6);
				if (perfil.equals("admin")) {
					TelaPrincipal p = new TelaPrincipal();
					p.setVisible(true);
					TelaPrincipal.mnVendas.setEnabled(true);
					TelaPrincipal.menCadUsu.setEnabled(true);
					
				} else {
					TelaPrincipal p = new TelaPrincipal();
					p.setVisible(true);
				}
				String user = rs.getString(2);
				TelaPrincipal.lblUsuario.setText(user);
				TelaPrincipal.lblUsuario.setForeground(Color.RED);
				this.dispose();
				ModuloConexao.closeConnection(con, stmt, rs);
			} else JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos!", "ERRO", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	//Constructor
	public TelaLogin() {
		building();

	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        	System.err.println(ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin frame = new TelaLogin();
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
	
	public void building() {
		setResizable(false);
		setTitle("Comercio X - Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 421, 230);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setLocationRelativeTo(null);
		
		//System.out.println(con);
		
		
		JLabel lblUsuario = new JLabel("Usu\u00E1rio:");
		lblUsuario.setBounds(30, 45, 60, 14);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(86, 42, 280, 26);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(30, 95, 60, 14);
		contentPane.add(lblSenha);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setMnemonic(KeyEvent.VK_S);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				logar();
			}
		});
		btnLogin.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					btnLogin.doClick();
				}
			}
		});
		btnLogin.setBounds(277, 135, 89, 36);
		contentPane.add(btnLogin);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(86, 92, 280, 26);
		contentPane.add(txtSenha);
		
		lblStatus = new JLabel("");
		ImageIcon dbok = new ImageIcon(this.getClass().getResource("/br/com/comercx/icone/dbok.png"));
		ImageIcon dberr = new ImageIcon(this.getClass().getResource("/br/com/comercx/icone/dberr.png"));
		if(con != null) {
			lblStatus.setIcon(dbok); 
		}
		else {
			lblStatus.setIcon(dberr);
		}
		lblStatus.setBounds(40, 120, 60, 67);
		contentPane.add(lblStatus);
	}
	
}

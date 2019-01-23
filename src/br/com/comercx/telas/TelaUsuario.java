package br.com.comercx.telas;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import br.com.comercx.DAO.UsuarioDAO;
import br.com.comercx.bean.Usuario;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TelaUsuario extends JInternalFrame {
	private JTextField txt_userID;
	private JTextField txt_userNome;
	private JTextField txt_userLogin;
	private JTextField txt_userSenha;
	private JTextField txt_userFone;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaUsuario frame = new TelaUsuario();
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
	public TelaUsuario() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Cadastro de Usu\u00E1rio");
		setBounds(100, 100, 990, 719);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Dados do Usu\u00E1rio", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(55, 51, 834, 449);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JComboBox cb_userPerfil = new JComboBox();
		cb_userPerfil.setBounds(475, 180, 146, 28);
		panel.add(cb_userPerfil);
		cb_userPerfil.setModel(new DefaultComboBoxModel(new String[] {"admin", "user"}));
		
		JButton btnRead = new JButton("");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int aux = (Integer.parseInt(JOptionPane.showInputDialog(null, "Digite  o ID do usuário que deseja pesquisar", null, JOptionPane.OK_CANCEL_OPTION)));
				Usuario u = new Usuario();
				UsuarioDAO ud = new UsuarioDAO();
				u.setId(aux);
				ud.read(u);
				txt_userID.setText(Integer.toString(u.getId()));
				txt_userNome.setText(u.getNome());
				txt_userFone.setText(u.getFone());
				txt_userLogin.setText(u.getLogin());
				txt_userSenha.setText(u.getSenha());
				cb_userPerfil.setSelectedItem(u.getPerfil());
	 		
			}
		});
		btnRead.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/comercx/icone/read.png")));
		btnRead.setBounds(412, 547, 90, 65);
		getContentPane().add(btnRead);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Usuario u = new Usuario();
				UsuarioDAO ud = new UsuarioDAO();
				
				u.setId(Integer.parseInt(txt_userID.getText()));
				u.setNome(txt_userNome.getText());
				u.setFone(txt_userFone.getText());
				u.setLogin(txt_userLogin.getText());
				u.setSenha(txt_userSenha.getText());
				u.setPerfil(cb_userPerfil.getSelectedItem().toString());
				if(txt_userNome.getText().isEmpty() || txt_userSenha.getText().isEmpty() || txt_userLogin.getText().isEmpty() || cb_userPerfil.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				} else ud.update(u);
			}
		});
		btnUpdate.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/comercx/icone/update.png")));
		btnUpdate.setBounds(542, 547, 90, 65);
		getContentPane().add(btnUpdate);
		
		JButton btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Usuario u = new Usuario();
				UsuarioDAO ud = new UsuarioDAO();
				
				u.setNome(txt_userNome.getText());
				u.setFone(txt_userFone.getText());
				u.setLogin(txt_userLogin.getText());
				u.setSenha(txt_userSenha.getText());
				u.setPerfil(cb_userPerfil.getSelectedItem().toString());
				if(txt_userNome.getText().isEmpty() || txt_userSenha.getText().isEmpty() || txt_userLogin.getText().isEmpty() || cb_userPerfil.getSelectedItem()==null) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				} else ud.create(u);
				
			}
		});
		btnCreate.setIcon(new ImageIcon("C:\\Users\\joaop\\OneDrive\\Imagens\\Icones\\create.png"));
		btnCreate.setBounds(155, 547, 90, 65);
		getContentPane().add(btnCreate);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o cadastro?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(confirma == JOptionPane.YES_OPTION) {
					Usuario u = new Usuario();
					UsuarioDAO ud = new UsuarioDAO();
					if(!txt_userID.getText().equals(null)) {
						u.setId(Integer.parseInt(txt_userID.getText()));
						ud.delete(u);
						limpar();
						cb_userPerfil.setSelectedItem(null);
					} else JOptionPane.showMessageDialog(null, "Id não informado", "Atenção", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/comercx/icone/delete.png")));
		btnDelete.setBounds(280, 547, 90, 65);
		getContentPane().add(btnDelete);
		
		JButton btnClear = new JButton("");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
				cb_userPerfil.setSelectedItem(null);
			}
		});
		btnClear.setIcon(new ImageIcon(TelaUsuario.class.getResource("/br/com/comercx/icone/clear.png")));
		btnClear.setBounds(682, 568, 63, 44);
		getContentPane().add(btnClear);
		
		JLabel lblCampoObrigatrio = new JLabel("* Campo Obrigat\u00F3rio");
		lblCampoObrigatrio.setBounds(783, 23, 117, 14);
		getContentPane().add(lblCampoObrigatrio);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(113, 56, 25, 14);
		panel.add(lblId);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(92, 122, 46, 14);
		panel.add(lblNome);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setBounds(92, 270, 46, 14);
		panel.add(lblLogin);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(92, 340, 46, 14);
		panel.add(lblSenha);
		
		JLabel lblPerfil = new JLabel("Perfil:");
		lblPerfil.setBounds(432, 187, 46, 14);
		panel.add(lblPerfil);
		
		txt_userID = new JTextField();
		txt_userID.setBounds(159, 49, 93, 28);
		panel.add(txt_userID);
		txt_userID.setEnabled(false);
		txt_userID.setColumns(10);
		
		txt_userNome = new JTextField();
		txt_userNome.setBounds(159, 115, 369, 28);
		panel.add(txt_userNome);
		txt_userNome.setColumns(10);
		
		txt_userLogin = new JTextField();
		txt_userLogin.setBounds(159, 263, 209, 28);
		panel.add(txt_userLogin);
		txt_userLogin.setColumns(10);
		
		txt_userSenha = new JTextField();
		txt_userSenha.setBounds(159, 333, 209, 28);
		panel.add(txt_userSenha);
		txt_userSenha.setColumns(10);
		
		JLabel lblFone = new JLabel("Fone:");
		lblFone.setBounds(92, 187, 46, 14);
		panel.add(lblFone);
		txt_userFone = new JTextField();
		txt_userFone.setBounds(159, 180, 209, 28);
		panel.add(txt_userFone);
		txt_userFone.setColumns(10);
		
		JLabel label = new JLabel("*");
		label.setBounds(538, 122, 13, 14);
		panel.add(label);
		
		JLabel label_2 = new JLabel("*");
		label_2.setBounds(378, 270, 13, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setBounds(378, 340, 13, 14);
		panel.add(label_3);
		
		
		
		JLabel label_4 = new JLabel("*");
		label_4.setBounds(631, 187, 13, 14);
		panel.add(label_4);
		
	}
	// funçao que centraliza o frame no desktopPane
	public void setPosicao() {
	    Dimension d = this.getDesktopPane().getSize();
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	private void limpar() {
		txt_userNome.setText(null);
		txt_userFone.setText(null);
		txt_userLogin.setText(null);
		txt_userSenha.setText(null);
		txt_userID.setText(null);
	}
}
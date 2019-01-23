package br.com.comercx.telas;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import br.com.comercx.DAO.ClienteDAO;
import br.com.comercx.DAO.UsuarioDAO;
import br.com.comercx.bean.Cliente;
import br.com.comercx.bean.Usuario;
import br.com.comercx.conexao.ModuloConexao;
import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class TelaCliente extends JInternalFrame {
	private JTextField txt_pesquisar;

	private JTable tbl_Cliente;
	private JTextField txt_cliEnd;
	private JTextField txt_cliEmail;
	private JTextField txt_cliFone;
	private JTextField txt_cliNome;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaCliente frame = new TelaCliente();
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
	public TelaCliente() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Cadastro de Cliente");
		setBounds(100, 100, 990, 719);
		getContentPane().setLayout(null);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				ClienteDAO cd = new ClienteDAO();
				Cliente c = new Cliente();
				if( tbl_Cliente.getSelectedRow()!=-1 ) {
					c.setNome(txt_cliNome.getText());
					c.setFone(txt_cliFone.getText());
					c.setEnd(txt_cliEnd.getText());
					c.setEmail(txt_cliEmail.getText());
					c.setId((int)tbl_Cliente.getValueAt(tbl_Cliente.getSelectedRow(), 0));
					if(txt_cliNome.getText().isEmpty() || txt_cliFone.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					} else {
						cd.update(c);
						readJTable();
					}
				}
			}
		});
		btnUpdate.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/comercx/icone/update.png")));
		btnUpdate.setBounds(461, 583, 90, 65);
		getContentPane().add(btnUpdate);
		
		JButton btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteDAO cd = new ClienteDAO();
				Cliente c = new Cliente();
				c.setNome(txt_cliNome.getText());
				c.setFone(txt_cliFone.getText());
				c.setEnd(txt_cliEnd.getText());
				c.setEmail(txt_cliEmail.getText());
				if(txt_cliNome.getText().isEmpty() || txt_cliFone.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
				} else {
					cd.create(c);
					readJTable();
					limpar();
				}
			}
		});
		btnCreate.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/comercx/icone/create.png")));
		btnCreate.setBounds(238, 583, 90, 65);
		getContentPane().add(btnCreate);
		
		JButton btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				Cliente c = new Cliente();
				ClienteDAO cd = new ClienteDAO();
				if(tbl_Cliente.getSelectedRow() != -1) {
					int ex = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o cadastro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
					if(ex == JOptionPane.YES_OPTION) {
						c.setId((int)tbl_Cliente.getValueAt(tbl_Cliente.getSelectedRow(), 0));
						cd.delete(c);
						readJTable();
						limpar();
					}
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/comercx/icone/delete.png")));
		btnDelete.setBounds(349, 583, 90, 65);
		getContentPane().add(btnDelete);
		
		JLabel label_2 = new JLabel("* Campo Obrigatório");
		label_2.setBounds(786, 30, 117, 14);
		getContentPane().add(label_2);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		button.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/comercx/icone/clear.png")));
		button.setBounds(623, 604, 63, 44);
		getContentPane().add(button);
		
		txt_pesquisar = new JTextField();
		txt_pesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent evt) {
				readJTableNome(txt_pesquisar.getText());
			}
		});
		txt_pesquisar.setBounds(238, 23, 407, 28);
		getContentPane().add(txt_pesquisar);
		txt_pesquisar.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaCliente.class.getResource("/br/com/comercx/icone/search.png")));
		label.setBounds(674, 11, 46, 42);
		getContentPane().add(label);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 62, 878, 209);
		getContentPane().add(scrollPane);
		tbl_Cliente = new JTable();
		tbl_Cliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tbl_Cliente.getSelectedRow() != -1) {
					txt_cliNome.setText(tbl_Cliente.getValueAt(tbl_Cliente.getSelectedRow(), 1).toString());
					txt_cliFone.setText(tbl_Cliente.getValueAt(tbl_Cliente.getSelectedRow(), 2).toString());
					txt_cliEnd.setText(tbl_Cliente.getValueAt(tbl_Cliente.getSelectedRow(), 3).toString());
					txt_cliEmail.setText(tbl_Cliente.getValueAt(tbl_Cliente.getSelectedRow(), 4).toString());
				}
			}
		});
		//Cria a tabela
		tbl_Cliente.setModel(new DefaultTableModel(
					new Object[][] 
							{
						
							}, 
					new String[] { "Id", "Nome", "Fone", "Endereço", "Email", }) {
			/**
			 * EU QUE FIZZZ :D
			**/
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {																
				return false;
			}
		});
		readJTable();
		scrollPane.setViewportView(tbl_Cliente);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Dados do Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(25, 293, 878, 263);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_1 = new JLabel("Nome:");
		label_1.setBounds(194, 46, 39, 14);
		panel.add(label_1);
		
		JLabel label_3 = new JLabel("Endere\u00E7o:");
		label_3.setBounds(187, 150, 66, 14);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Email:");
		label_4.setBounds(201, 203, 46, 14);
		panel.add(label_4);
		
		txt_cliEnd = new JTextField();
		txt_cliEnd.setColumns(10);
		txt_cliEnd.setBounds(257, 143, 406, 28);
		panel.add(txt_cliEnd);
		
		txt_cliEmail = new JTextField();
		txt_cliEmail.setColumns(10);
		txt_cliEmail.setBounds(257, 196, 406, 28);
		panel.add(txt_cliEmail);
		
		JLabel label_5 = new JLabel("Fone:");
		label_5.setBounds(194, 100, 46, 14);
		panel.add(label_5);
		
		txt_cliFone = new JTextField();
		txt_cliFone.setColumns(10);
		txt_cliFone.setBounds(257, 93, 182, 28);
		panel.add(txt_cliFone);
		
		JLabel label_6 = new JLabel("*");
		label_6.setBounds(449, 100, 13, 14);
		panel.add(label_6);
		
		JLabel label_7 = new JLabel("*");
		label_7.setBounds(650, 46, 13, 14);
		panel.add(label_7);
		
		txt_cliNome = new JTextField();
		txt_cliNome.setColumns(10);
		txt_cliNome.setBounds(258, 39, 384, 28);
		panel.add(txt_cliNome);

	}
	// Seta os dados do BD na tabela
	public void readJTable() {
		DefaultTableModel modelo = (DefaultTableModel) tbl_Cliente.getModel();
		ClienteDAO cd = new ClienteDAO();
		
		modelo.setNumRows(0);
		
		for(Cliente c: cd.preencherTabela()) {
			modelo.addRow(new Object[] {
					c.getId(),
					c.getNome(),
					c.getFone(),
					c.getEnd(),
					c.getEmail()
			});
		}
	}
	public void readJTableNome(String n) {
		DefaultTableModel modelo = (DefaultTableModel) tbl_Cliente.getModel();
		ClienteDAO cd = new ClienteDAO();
		
		modelo.setNumRows(0);
		
		for(Cliente c: cd.readNome(n)) {
			modelo.addRow(new Object[] {
					c.getId(),
					c.getNome(),
					c.getFone(),
					c.getEnd(),
					c.getEmail()
			});
		}
		
	}
	public void setPosicao() {
	    Dimension d = this.getDesktopPane().getSize();
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	private void limpar() {
		txt_cliEmail.setText(null);
		txt_cliNome.setText(null);
		txt_cliEnd.setText(null);
		txt_cliFone.setText(null);
	}
}

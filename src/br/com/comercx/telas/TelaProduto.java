package br.com.comercx.telas;

import java.awt.EventQueue;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.comercx.DAO.ClienteDAO;
import br.com.comercx.DAO.ProdutoDAO;
import br.com.comercx.bean.Produto;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.DropMode;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.border.TitledBorder;

public class TelaProduto extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtPreco;
	private JTable tblProduto;
	private JTextField txtSearch;
	private JFormattedTextField txtEan;
	private JFormattedTextField txtData;
	private JTextField txtQtd;
	SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaProduto frame = new TelaProduto();
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
	public TelaProduto() {
		setClosable(true);
		setMaximizable(true);
		setIconifiable(true);
		setBounds(100, 100, 990, 719);
		setTitle("Cadastro de Produto");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "Dados do Produto", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(20, 312, 885, 255);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		MaskFormatter d = null;
		MaskFormatter ean = null;
		try {
			
			d = new MaskFormatter("##/##/####");
			ean = new MaskFormatter("#############");
			d.setPlaceholderCharacter('_');
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro na formatação!" + e, null, JOptionPane.ERROR_MESSAGE);
		}
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(67, 49, 37, 14);
		panel.add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setBounds(128, 42, 385, 28);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
		JLabel lblEan = new JLabel("EAN13:");
		lblEan.setBounds(67, 120, 49, 14);
		panel.add(lblEan);
		
		JLabel lblDataDeValidade = new JLabel("Data de Validade:");
		lblDataDeValidade.setBounds(553, 54, 91, 14);
		panel.add(lblDataDeValidade);
		
		txtData = new JFormattedTextField(d);
		txtData.setBounds(654, 42, 124, 28);
		panel.add(txtData);
		
		JLabel lblPreo = new JLabel("Pre\u00E7o:");
		lblPreo.setBounds(70, 197, 46, 14);
		panel.add(lblPreo);
		
		txtPreco = new JTextField();
		txtPreco.setBounds(128, 190, 246, 28);
		panel.add(txtPreco);
		txtPreco.setColumns(10);
		
		JLabel label_2 = new JLabel("*");
		label_2.setBounds(523, 49, 13, 14);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("*");
		label_3.setBounds(384, 120, 13, 14);
		panel.add(label_3);
		
		txtEan = new JFormattedTextField(ean);
		txtEan.setBounds(128, 113, 246, 28);
		panel.add(txtEan);
		
		JLabel lblQuantidade = new JLabel("Quantidade:");
		lblQuantidade.setBounds(488, 120, 72, 14);
		panel.add(lblQuantidade);
		
		txtQtd = new JTextField();
		txtQtd.setBounds(570, 113, 155, 28);
		panel.add(txtQtd);
		txtQtd.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 98, 885, 203);
		getContentPane().add(scrollPane);	
		tblProduto = new JTable();
		tblProduto.setModel(new DefaultTableModel(
					new Object[][]
						{
						},
					new String[] {"Id","Nome","Preço","Qtd","Ean13","Validade"}) {			
			/**
			 * JOOJ
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int collumn) {
				return false;
			}
				
		});
		tblProduto.addMouseListener(new MouseAdapter() {		
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tblProduto.getSelectedRow()!=-1) {
					txtNome.setText(tblProduto.getValueAt(tblProduto.getSelectedRow(), 1).toString());
					txtPreco.setText(tblProduto.getValueAt(tblProduto.getSelectedRow(), 2).toString());
					txtQtd.setText(tblProduto.getValueAt(tblProduto.getSelectedRow(), 3).toString());
					txtEan.setText(tblProduto.getValueAt(tblProduto.getSelectedRow(), 4).toString());
					txtData.setText(tblProduto.getValueAt(tblProduto.getSelectedRow(), 5).toString());
				}
			}
		});
		scrollPane.setViewportView(tblProduto);
		readJTable();
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String j = txtSearch.getText();
				readJTableName(j);
			}
		});
		txtSearch.setBounds(147, 42, 356, 28);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/comercx/icone/search.png")));
		label.setBounds(528, 28, 59, 52);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("* Campo Obrigat\u00F3rio");
		label_1.setBounds(779, 49, 117, 14);
		getContentPane().add(label_1);
		
		JButton btnUpdate = new JButton("");
		btnUpdate.setBounds(452, 588, 90, 65);
		getContentPane().add(btnUpdate);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produto p = new Produto();
				ProdutoDAO pd = new ProdutoDAO();
				
				if(tblProduto.getSelectedRow() != -1) {
					
					p.setId((int)tblProduto.getValueAt(tblProduto.getSelectedRow(),0));
					p.setNome(txtNome.getText());
					p.setPreco(Double.parseDouble(txtPreco.getText()));
					p.setQtd(Integer.parseInt(txtQtd.getText()));
					p.setEan(txtEan.getText());
					p.setValidade(txtData.getText());
					if(txtNome.getText().isEmpty() || txtEan.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "ATENÇÃO", JOptionPane.WARNING_MESSAGE);
					} else {
						pd.update(p);
						readJTable();
					}
				}
				
			}
		});
		btnUpdate.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/comercx/icone/update.png")));
		
		JButton btnCreate = new JButton("");
		btnCreate.setBounds(229, 588, 90, 65);
		getContentPane().add(btnCreate);
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produto p = new Produto();
				ProdutoDAO pd = new ProdutoDAO();				
				p.setNome(txtNome.getText());
				p.setEan(txtEan.getText());
				p.setPreco(Double.parseDouble(txtPreco.getText()));
				p.setValidade(txtData.getText());
				p.setQtd(Integer.parseInt(txtQtd.getText()));
				if(txtNome.getText().isEmpty() || txtEan.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Atenção", JOptionPane.WARNING_MESSAGE);
				} else pd.create(p);
					readJTable();
					limpar();
				
			}
		});
		btnCreate.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/comercx/icone/create.png")));
		
		JButton btnDelete = new JButton("");
		btnDelete.setBounds(340, 588, 90, 65);
		getContentPane().add(btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Produto p = new Produto();
				ProdutoDAO pd = new ProdutoDAO();
				if(tblProduto.getSelectedRow() != -1) {
					int ex = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir o cadastro?", "ATENÇÃO", JOptionPane.YES_NO_OPTION);
					if(ex == JOptionPane.YES_OPTION) {
						p.setId((int)tblProduto.getValueAt(tblProduto.getSelectedRow(),0));
						pd.delete(p);
						readJTable();
						limpar();
					}
				}
			}
		});
		btnDelete.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/comercx/icone/delete.png")));
		
		JButton btnClean = new JButton("");
		btnClean.setBounds(614, 609, 63, 44);
		getContentPane().add(btnClean);
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpar();
			}
		});
		btnClean.setIcon(new ImageIcon(TelaProduto.class.getResource("/br/com/comercx/icone/clear.png")));
		
	}
	public void readJTable() {
		DefaultTableModel model = (DefaultTableModel) tblProduto.getModel();
		ProdutoDAO pd = new ProdutoDAO();
		model.setNumRows(0);
		for(Produto p: pd.fulfillTable()) {
			
			model.addRow( new Object[] {
					
					p.getId(),
					p.getNome(),
					p.getPreco(),
					p.getQtd(),
					p.getEan(),
					p.getValidade()
					
			});
			
		}
		
	}
	public void readJTableName(String n) {
		DefaultTableModel model = (DefaultTableModel) tblProduto.getModel();
		ProdutoDAO pd = new ProdutoDAO();
		model.setNumRows(0);
		for(Produto p: pd.readNome(n)) {
			
			model.addRow( new Object[] {
					
					p.getId(),
					p.getNome(),
					p.getPreco(),
					p.getQtd(),
					p.getEan(),
					p.getValidade()
					
			});
			
		}
		
	}
	public void setPosicao() {
	    Dimension d = this.getDesktopPane().getSize();
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
	public void limpar() {
		txtNome.setText(null);
		txtEan.setText(null);
		txtPreco.setText(null);
		txtData.setText(null);
		txtQtd.setText(null);
	}
}

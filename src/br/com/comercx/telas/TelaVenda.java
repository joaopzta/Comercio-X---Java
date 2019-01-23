package br.com.comercx.telas;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import br.com.comercx.DAO.ClienteDAO;
import br.com.comercx.DAO.ProdutoDAO;
import br.com.comercx.DAO.VendaDAO;
import br.com.comercx.bean.Cliente;
import br.com.comercx.bean.ItemVenda;
import br.com.comercx.bean.Produto;
import br.com.comercx.bean.Venda;
import br.com.comercx.reports.Relatorio;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Event;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaVenda extends JInternalFrame {
	public static JTextField txtId;
	private static JTable tblCli;
	public static JTextField txtIdProd;
	public static JTextField txtDesc;
	public static JTextField txtQtd;
	public static JTextField txtValUnit;
	public static JTable tblProd;
	public static JTextField txtTotal;
	public static JTextField txtData;
	public static JTextField txtIdCli;
	private static SimpleDateFormat df;
	private double total = 0;
	public static JTextField txtSituac;
	private static JFormattedTextField txtCli;
	private JPanel pnCliente;
	private JPanel pnDadosVenda;
	private JPanel pnProduto;
	private static JButton btnDelete;
	private static JButton btnUpdate;
	private static JButton btnFinalizar;
	private static JButton btnSalvar;
	private static JButton btnRead;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaVenda frame = new TelaVenda();
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
	public TelaVenda() {
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 990, 719);
		setTitle("Operação de Venda");
		getContentPane().setLayout(null);

		pnDadosVenda = new JPanel();
		pnDadosVenda.setBorder(
				new TitledBorder(null, "Dados da Venda", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnDadosVenda.setBounds(30, 11, 208, 233);
		getContentPane().add(pnDadosVenda);
		pnDadosVenda.setLayout(null);

		JLabel lblNumero = new JLabel("Numero:");
		lblNumero.setBounds(10, 52, 64, 14);
		pnDadosVenda.add(lblNumero);

		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setBounds(66, 45, 86, 25);
		pnDadosVenda.add(txtId);
		txtId.setColumns(10);

		JLabel lblData = new JLabel("Data:");
		lblData.setBounds(10, 86, 46, 14);
		pnDadosVenda.add(lblData);

		JLabel lblSituao = new JLabel("Situa\u00E7\u00E3o:");
		lblSituao.setBounds(10, 123, 64, 14);
		pnDadosVenda.add(lblSituao);

		JLabel lblTotal = new JLabel("Total:");
		lblTotal.setBounds(10, 171, 46, 14);
		pnDadosVenda.add(lblTotal);

		txtTotal = new JTextField();
		txtTotal.setText("0");
		txtTotal.setEditable(false);
		txtTotal.setColumns(10);
		txtTotal.setBounds(66, 166, 116, 25);
		pnDadosVenda.add(txtTotal);
		
		
		Date hoje = new Date();
		df = new SimpleDateFormat("dd/MM/yyyy");
		
		txtData = new JTextField(df.format(hoje));
		txtData.setEditable(false);
		txtData.setColumns(10);
		txtData.setBounds(66, 81, 124, 25);
		pnDadosVenda.add(txtData);
		
		txtSituac = new JTextField();
		txtSituac.setText("Aberta");
		txtSituac.setEditable(false);
		txtSituac.setColumns(10);
		txtSituac.setBounds(66, 120, 104, 25);
		pnDadosVenda.add(txtSituac);

		pnCliente = new JPanel();
		pnCliente.setBorder(new TitledBorder(null, "Cliente", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnCliente.setBounds(248, 11, 677, 233);
		getContentPane().add(pnCliente);
		pnCliente.setLayout(null);

		txtCli = new JFormattedTextField();
		txtCli.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				VendaDAO vd = new VendaDAO();
				Cliente c = new Cliente();
				c.setNome(txtCli.getText() + "%");
				tblCli.setModel(DbUtils.resultSetToTableModel(vd.pesquisarCliente(c)));
			}
		});
		txtCli.setBounds(34, 25, 267, 28);
		pnCliente.add(txtCli);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(34, 73, 618, 137);
		pnCliente.add(scrollPane);

		tblCli = new JTable();
		scrollPane.setViewportView(tblCli);
		tblCli.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Nome", "Fone" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int collumn) {
				return false;
			}
		});
		tblCli.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(tblCli.getSelectedRow() != -1) {
					txtIdCli.setText(tblCli.getValueAt(tblCli.getSelectedRow(), 0).toString());
				}
			}
		});
		readJTable();

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/comercx/icone/search.png")));
		label.setBounds(322, 11, 59, 52);
		pnCliente.add(label);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(391, 32, 46, 14);
		pnCliente.add(lblId);
		
		txtIdCli = new JTextField();
		txtIdCli.setEditable(false);
		txtIdCli.setBounds(427, 25, 105, 28);
		pnCliente.add(txtIdCli);
		txtIdCli.setColumns(10);

		pnProduto = new JPanel();
		pnProduto.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Items da Venda", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnProduto.setBounds(37, 255, 888, 347);
		getContentPane().add(pnProduto);
		pnProduto.setLayout(null);

		JLabel lblCdigo = new JLabel("C\u00F3digo:");
		lblCdigo.setBounds(149, 41, 46, 14);
		pnProduto.add(lblCdigo);

		txtIdProd = new JTextField();
		txtIdProd.setEditable(false);
		txtIdProd.setColumns(10);
		txtIdProd.setBounds(194, 34, 86, 28);
		pnProduto.add(txtIdProd);

		JLabel lblDescrio = new JLabel("Descri\u00E7\u00E3o:");
		lblDescrio.setBounds(434, 41, 59, 14);
		pnProduto.add(lblDescrio);

		txtDesc = new JTextField();
		txtDesc.setEditable(false);
		txtDesc.setColumns(10);
		txtDesc.setBounds(503, 34, 206, 28);
		pnProduto.add(txtDesc);

		JLabel lblNewLabel = new JLabel("Qtd:");
		lblNewLabel.setBounds(458, 99, 35, 14);
		pnProduto.add(lblNewLabel);

		txtQtd = new JTextField();
		txtQtd.setColumns(10);
		txtQtd.setBounds(503, 92, 164, 28);
		pnProduto.add(txtQtd);

		JLabel lblValorUnitrio = new JLabel("Valor Unit\u00E1rio:");
		lblValorUnitrio.setBounds(111, 99, 120, 14);
		pnProduto.add(lblValorUnitrio);

		txtValUnit = new JTextField();
		txtValUnit.setEditable(false);
		txtValUnit.setColumns(10);
		txtValUnit.setBounds(194, 92, 153, 28);
		pnProduto.add(txtValUnit);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(69, 141, 741, 195);
		pnProduto.add(scrollPane_1);

		tblProd = new JTable();
		scrollPane_1.setViewportView(tblProd);
		tblProd.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Descrição", "Qtd", "Valor Unitario", "Sub Total" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int collumn) {
				return false;
			}
		});
		readJTable();
		JButton btnConProd = new JButton("");
		btnConProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				TelaConProd tcp = new TelaConProd();
				tcp.setVisible(true);
				TelaPrincipal.desktop.add(tcp);
				tcp.setPosicao();
				try {
					tcp.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
			}
		});
		btnConProd.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/comercx/icone/search.png")));
		btnConProd.setBounds(721, 25, 59, 49);
		pnProduto.add(btnConProd);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ItemVenda iv = new ItemVenda();
				txtTotal.setText("");
				iv.setId(Integer.parseInt(txtIdProd.getText()));
				iv.setDesc(txtDesc.getText());
				iv.setValUnit(Double.parseDouble(txtValUnit.getText()));
				
				if(txtQtd.getText().isEmpty()) 	iv.setQtd(0);
				else 							iv.setQtd(Integer.parseInt(txtQtd.getText()));
				
				double subtot = (iv.getQtd() * iv.getValUnit());
				iv.setSubTot(subtot);
				readItemVenda(iv);
				total += subtot;
				txtTotal.setText(Double.toString(total));
			}
		});
		btnAdicionar.setBounds(691, 92, 89, 28);
		pnProduto.add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(tblProd.getSelectedRow() != -1) {
					((DefaultTableModel) tblProd.getModel()).removeRow(tblProd.getSelectedRow());
				} else JOptionPane.showMessageDialog(null, "Selecione um produto da tabela!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
				calcularTotalESub();
			}
		});
		btnRemover.setBounds(790, 92, 89, 28);
		pnProduto.add(btnRemover);

		btnUpdate = new JButton("");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Venda v = new Venda();
				VendaDAO vd = new VendaDAO();	
				v.setNum(Integer.parseInt(txtId.getText()));
				v.setIdCli(Integer.parseInt(txtIdCli.getText()));
				calcularTotalESub();
				v.setTotal(Double.parseDouble(txtTotal.getText()));
				if( txtIdCli.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Selecione um cliente!", "Atenção", JOptionPane.WARNING_MESSAGE);
				} else {
					vd.updateVenda(v);
					ItemVenda aux = new ItemVenda();
					aux.setIdVenda(Integer.parseInt(txtId.getText()));
					vd.deleteItemVenda(aux);
					for(int i=0; i < tblProd.getRowCount(); i++) {
						ItemVenda iv = new ItemVenda();
						iv.setId((int)tblProd.getValueAt(i, 0));
						iv.setQtd((int)tblProd.getValueAt(i, 2));
						iv.setIdVenda(Integer.parseInt(txtId.getText()));
						iv.setValUnit((double)tblProd.getValueAt(i, 3));
						vd.updateItemVenda(iv);
					}
					
				}
				
			}
		});
		btnUpdate.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/comercx/icone/update.png")));
		btnUpdate.setBounds(562, 613, 90, 65);
		getContentPane().add(btnUpdate);

		JButton btnCreate = new JButton("");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				VendaDAO vd = new VendaDAO();
				txtId.setText(Integer.toString(vd.create().getNum()));
				limpar();
				((DefaultTableModel) tblProd.getModel()).setNumRows(0);
				txtSituac.setText("Aberta");
				liberarCampos();
			}
		});
		btnCreate.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/comercx/icone/create.png")));
		btnCreate.setBounds(339, 613, 90, 65);
		getContentPane().add(btnCreate);

		btnDelete = new JButton("");
		btnDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					VendaDAO vd = new VendaDAO();
					Venda v = new Venda();
					ItemVenda iv = new ItemVenda();
					if(txtId.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Selecione uma venda", TITLE_PROPERTY, JOptionPane.WARNING_MESSAGE);
					}else {
						v.setNum(Integer.parseInt(txtId.getText()));
						iv.setIdVenda(v.getNum());
						int rs = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir a venda?", null, JOptionPane.YES_NO_OPTION);
						if(rs == JOptionPane.YES_OPTION) {
							vd.deleteItemVenda(iv);
							vd.deleteVenda(v);
							limparTudo();
						}
					}
				}
		});
		btnDelete.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/comercx/icone/delete.png")));
		btnDelete.setBounds(450, 613, 90, 65);
		getContentPane().add(btnDelete);

		JButton btnImprimir = new JButton("");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtId.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,"Selecione uma venda", "ERRO", JOptionPane.ERROR_MESSAGE);
				}
				int rs = JOptionPane.showConfirmDialog(null, "Confirma a emissão do relatório de venda?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(rs == JOptionPane.YES_OPTION) {
					Relatorio re = new Relatorio();
					Venda v = new Venda();
					v.setNum(Integer.parseInt(txtId.getText()));
					re.imprimirVenda(v);
				}
			}
		});
		btnImprimir.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/comercx/icone/Print.png")));
		btnImprimir.setBounds(787, 613, 90, 65);
		getContentPane().add(btnImprimir);
		
		btnSalvar = new JButton("");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Venda v = new Venda();
				VendaDAO vd = new VendaDAO();
				ProdutoDAO pd = new ProdutoDAO();
				v.setIdCli(Integer.parseInt(txtIdCli.getText()));
				v.setTotal(Double.parseDouble(txtTotal.getText()));
				v.setSituac(txtSituac.getText());
				if( txtIdCli.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(null, "Selecione um cliente!", "Atenção", JOptionPane.WARNING_MESSAGE);
				} else {
					vd.saveVenda(v);
					for(int i=0; i < tblProd.getRowCount(); i++) {
						ItemVenda iv = new ItemVenda();
						Produto p = new Produto();
						iv.setId((int)tblProd.getValueAt(i, 0));
						iv.setQtd((int)tblProd.getValueAt(i, 2));
						p.setId(iv.getId());
						p.setQtd(iv.getQtd()-p.getQtd());
						pd.atualizarEstoque(p);
						iv.setValUnit((double)tblProd.getValueAt(i, 3));
						vd.saveItemVenda(iv);
					}
				}
			}
		});
		btnSalvar.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/comercx/icone/Save.png")));
		btnSalvar.setBounds(226, 613, 90, 65);
		getContentPane().add(btnSalvar);
		
		btnFinalizar = new JButton("");
		btnFinalizar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int op = JOptionPane.showConfirmDialog(null, "Uma vez fechada, os dados da venda não poderão ser alterados!\n Deseja continuar?", "Aviso", JOptionPane.YES_NO_OPTION);
				if(op == JOptionPane.YES_OPTION) {
					VendaDAO vd = new VendaDAO();
					Venda v = new Venda();
					v.setNum(Integer.parseInt(txtId.getText()));
					txtSituac.setText("Fechada");
					vd.updateSituac(v);
					bloquearCampos();
					tblCli.setEnabled(false);
					tblProd.setEnabled(false);
				}
			}
		});
		btnFinalizar.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/comercx/icone/Lock.png")));
		btnFinalizar.setBounds(113, 613, 90, 65);
		getContentPane().add(btnFinalizar);
		
		btnRead = new JButton("");
		btnRead.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaConVenda cv = new TelaConVenda();
				cv.setVisible(true);
				TelaPrincipal.desktop.add(cv);
				try {
					cv.setSelected(true);
				} catch (PropertyVetoException e) {
					e.printStackTrace();
				}
				cv.setPosicao();
			}
		});
		btnRead.setIcon(new ImageIcon(TelaVenda.class.getResource("/br/com/comercx/icone/read.png")));
		btnRead.setBounds(672, 613, 90, 65);
		getContentPane().add(btnRead);
	}
	public void readJTable() {
		DefaultTableModel modelo = (DefaultTableModel) tblCli.getModel();
		ClienteDAO cd = new ClienteDAO();

		modelo.setNumRows(0);

		for (Cliente c : cd.preencherTabela()) {
			modelo.addRow(new Object[] { c.getId(), c.getNome(), c.getFone(), });
		}

	}
	public void readItemVenda(ItemVenda iv) {
		DefaultTableModel modelo = (DefaultTableModel) tblProd.getModel();
		//modelo.setNumRows(0);
		modelo.addRow(new Object[] { iv.getId(), iv.getDesc(), iv.getQtd(), iv.getValUnit(), iv.getSubTot() });
	}
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
	public void limpar() {
		txtIdCli.setText(null);
		txtDesc.setText(null);
		txtQtd.setText(null);
		txtValUnit.setText(null);
		txtIdProd.setText(null);
		txtCli.setText(null);
	}
	public void limparTudo() {
		txtId.setText(null);
		txtIdCli.setText(null);
		txtDesc.setText(null);
		txtQtd.setText(null);
		txtValUnit.setText(null);
		txtIdProd.setText(null);
		txtCli.setText(null);
		txtTotal.setText(null);
		DefaultTableModel modelo = (DefaultTableModel) tblProd.getModel();
		modelo.setNumRows(0);
	}
	public static void verificarSituac() {
		if(txtSituac.getText().equals("Fechada")) {
			bloquearCampos();
		} else liberarCampos();
	}
	public static void bloquearCampos(){
		txtCli.setEnabled(false);
		txtData.setEnabled(false);
		txtDesc.setEnabled(false);
		txtIdCli.setEnabled(false);
		txtId.setEnabled(false);
		txtIdProd.setEnabled(false);
		txtQtd.setEnabled(false);
		txtSituac.setEnabled(false);
		txtTotal.setEnabled(false);
		txtValUnit.setEnabled(false);
		tblCli.setEnabled(false);
		tblProd.setEnabled(false);
		btnDelete.setEnabled(false);
		btnUpdate.setEnabled(false);
		btnFinalizar.setEnabled(false);
		btnSalvar.setEnabled(false);
	}
	public static void liberarCampos(){
		txtCli.setEnabled(true);
		txtData.setEnabled(true);
		txtDesc.setEnabled(true);
		txtIdCli.setEnabled(true);
		txtId.setEnabled(true);
		txtIdProd.setEnabled(true);
		txtQtd.setEnabled(true);
		txtSituac.setEnabled(true);
		txtTotal.setEnabled(true);
		txtValUnit.setEnabled(true);
		tblCli.setEnabled(true);
		tblProd.setEnabled(true);
		btnDelete.setEnabled(true);
		btnUpdate.setEnabled(true);
		btnFinalizar.setEnabled(true);
		btnSalvar.setEnabled(true);
	}
	public void calcularTotalESub() {
		ItemVenda iven = new ItemVenda();
		double tot = 0;
		for(int i=0; i < tblProd.getRowCount(); i++) {
			iven.setSubTot((double)tblProd.getValueAt(i, 4));
			tot += iven.getSubTot();
		}
		txtTotal.setText(Double.toString(tot));
	}
}







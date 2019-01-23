package br.com.comercx.telas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.comercx.DAO.ConVendaDAO;
import br.com.comercx.bean.Cliente;
import br.com.comercx.bean.ItemVenda;
import br.com.comercx.bean.Venda;
import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TelaConVenda extends JInternalFrame {
	private JTable tblRead;
	private JTextField txtPesquisar;
	private JRadioButton Situacao;
	private JRadioButton CliNome;
	private JRadioButton Data;
	private SimpleDateFormat formato;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConVenda frame = new TelaConVenda();
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
	public TelaConVenda() {
		setTitle("Consultar Vendas");
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 729, 523);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 140, 693, 342);
		getContentPane().add(scrollPane);
		
		tblRead = new JTable();
		tblRead.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tblRead.getSelectedRow() != -1)
					if(e.getClickCount() == 2) {
						ConVendaDAO cv = new ConVendaDAO();
						Venda v = new Venda();
						ItemVenda iv = new ItemVenda();
						v.setNum((int)tblRead.getValueAt(tblRead.getSelectedRow(), 0));
						cv.setarVenda(v);
						TelaVenda.txtId.setText(Integer.toString(v.getNum()));
						TelaVenda.txtSituac.setText(v.getSituac());
						TelaVenda.txtIdCli.setText(Integer.toString(v.getIdCli()));
						TelaVenda.txtTotal.setText(Double.toString(v.getTotal()));
						TelaVenda.txtData.setText(v.getData().toString());
						iv.setIdVenda(Integer.parseInt(TelaVenda.txtId.getText()));
						setarItemVenda(iv);
						TelaVenda.verificarSituac();
						setVisible(false);
					}
			}
		});
		tblRead.setModel(new DefaultTableModel(new Object[][] {}, new String[] {"Venda", "Data", "Total", "Cliente", "Situação"}) {		
			/**
			 * Não deixa o usuario editar uma celula da tabela
			 */
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int coll) {
				return false;
			}			
		});
		scrollPane.setViewportView(tblRead);
		setarTabela();
		
		txtPesquisar = new JTextField();
		txtPesquisar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				if(CliNome.isSelected()) {
					if(txtPesquisar.getText().isEmpty()) setarTabela();
					else {
						ConVendaDAO cv = new ConVendaDAO();
						Venda v = new Venda();
						v.setCliNome(txtPesquisar.getText());
						tblRead.setModel(DbUtils.resultSetToTableModel(cv.readCliente(v)));
					}
				}else
					if(Situacao.isSelected()) {
						if(txtPesquisar.getText().isEmpty()) setarTabela();
						else {
							ConVendaDAO cv = new ConVendaDAO();
							Venda v = new Venda();
							v.setSituac(txtPesquisar.getText());
							tblRead.setModel(DbUtils.resultSetToTableModel(cv.readSituac(v)));
						}
					} else
						if(Data.isSelected()){
							if(txtPesquisar.getText().isEmpty()) setarTabela();
							else {
								ConVendaDAO cv = new ConVendaDAO();
								Venda v = new Venda();
								formato = new SimpleDateFormat("dd/MM/yyyy");
								Date d = new Date();
								try {
									d = formato.parse(txtPesquisar.getText());
									v.setData(d);
								} catch (ParseException e) {
									e.printStackTrace();
								}
								tblRead.setModel(DbUtils.resultSetToTableModel(cv.readData(v)));
							}
						}
			}
		});
		txtPesquisar.setBounds(146, 40, 302, 28);
		getContentPane().add(txtPesquisar);
		txtPesquisar.setColumns(10);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaConVenda.class.getResource("/br/com/comercx/icone/search.png")));
		label.setBounds(469, 26, 59, 52);
		getContentPane().add(label);
		
		Situacao = new JRadioButton("Situa\u00E7\u00E3o");
		Situacao.setBounds(271, 91, 109, 23);
		getContentPane().add(Situacao);
		
		CliNome = new JRadioButton("Nome do Cliente");
		CliNome.setSelected(true);
		CliNome.setBounds(126, 91, 143, 23);
		getContentPane().add(CliNome);
		
		Data = new JRadioButton("Data");
		Data.setBounds(401, 91, 109, 23);
		getContentPane().add(Data);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(Situacao);
		bg.add(CliNome);
		bg.add(Data);
		
	}
	public void setarItemVenda(ItemVenda iv) {
		DefaultTableModel model = (DefaultTableModel) TelaVenda.tblProd.getModel();
		
		ConVendaDAO cv = new ConVendaDAO();
		model.setNumRows(0);
		for (ItemVenda aux: cv.setarItemVenda(iv)) {
			double subtot = aux.getValUnit()*aux.getQtd();
			aux.setSubTot(subtot);
			model.addRow(new Object[] {
					aux.getId(),
					aux.getDesc(),
					aux.getQtd(),
					aux.getValUnit(),
					aux.getSubTot()
			});
		}
	}
	public void setarTabela() {
		DefaultTableModel model = (DefaultTableModel) tblRead.getModel();
		ConVendaDAO cv = new ConVendaDAO();
		model.setNumRows(0);
		for(Venda v: cv.popularTabela()) {
			model.addRow(new Object[]
							{
								v.getNum(),
								v.getData(),
								v.getTotal(),
								v.getCliNome(),
								v.getSituac()
							}
					);
		}
	}
	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}

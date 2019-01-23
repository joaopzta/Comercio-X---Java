package br.com.comercx.telas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import br.com.comercx.DAO.ProdutoDAO;
import br.com.comercx.bean.Produto;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaConProd extends JInternalFrame {
	private JTable tblProd;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaConProd frame = new TelaConProd();
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
	public TelaConProd() {
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 633, 465);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 113, 597, 311);
		getContentPane().add(scrollPane);

		tblProd = new JTable();
		tblProd.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "Id", "Nome", "Preço", "Qtd", "Ean13", "Validade" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int collumn) {
				return false;
			}
		});
		tblProd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tblProd.getSelectedRow()!=-1)
					if(e.getClickCount() == 2)
					{
						TelaVenda.txtIdProd.setText(tblProd.getValueAt(tblProd.getSelectedRow(), 0).toString());
						TelaVenda.txtDesc.setText(tblProd.getValueAt(tblProd.getSelectedRow(), 1).toString());
						TelaVenda.txtValUnit.setText(tblProd.getValueAt(tblProd.getSelectedRow(), 2).toString());
						setVisible(false);
					}
			}
		});
		scrollPane.setViewportView(tblProd);
		readJTable();

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				String j = txtSearch.getText();
				readJTableName(j);
			}
		});
		txtSearch.setBounds(64, 44, 364, 30);
		getContentPane().add(txtSearch);
		txtSearch.setColumns(10);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(TelaConProd.class.getResource("/br/com/comercx/icone/search.png")));
		lblNewLabel.setBounds(454, 28, 57, 63);
		getContentPane().add(lblNewLabel);

	}

	public void readJTableName(String n) {
		DefaultTableModel model = (DefaultTableModel) tblProd.getModel();
		ProdutoDAO pd = new ProdutoDAO();
		model.setNumRows(0);
		for (Produto p : pd.readNome(n)) {

			model.addRow(new Object[] {

					p.getId(), p.getNome(), p.getPreco(), p.getQtd(), p.getEan(), p.getValidade()

			});

		}
	}

	public void readJTable() {
		DefaultTableModel model = (DefaultTableModel) tblProd.getModel();
		ProdutoDAO pd = new ProdutoDAO();
		model.setNumRows(0);
		for (Produto p : pd.fulfillTable()) {

			model.addRow(new Object[] {

					p.getId(), p.getNome(), p.getPreco(), p.getQtd(), p.getEan(), p.getValidade()

			});

		}

	}

	public void setPosicao() {
		Dimension d = this.getDesktopPane().getSize();
		this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2);
	}
}

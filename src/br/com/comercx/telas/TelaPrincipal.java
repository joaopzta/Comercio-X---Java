package br.com.comercx.telas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.DesktopPaneUI;

import br.com.comercx.reports.Relatorio;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;

import javax.swing.UIManager;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.util.Date;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TelaPrincipal extends JFrame {

	private JPanel contentPane;
	public static javax.swing.JMenuItem menCadUsu;
	public static javax.swing.JMenu mnVendas;
	public static javax.swing.JLabel lblUsuario;
	public static javax.swing.JDesktopPane desktop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal frame = new TelaPrincipal();
					frame.setVisible(false);
				}
				catch (Exception e) {		
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the frame.
	 */
	public TelaPrincipal() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				JLabel lblData = new JLabel("");
				Date data = new Date();
				DateFormat formatar = DateFormat.getDateInstance((DateFormat.SHORT));
				lblData.setText(formatar.format(data));
				lblData.setFont(new Font("Tahoma", Font.BOLD, 17));
				lblData.setBounds(1067, 194, 210, 127);
				contentPane.add(lblData);
			}
		});
		
		setTitle("Comercio X 1.0 - Principal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 780);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1274, 21);
		contentPane.add(menuBar);

		JMenu mnCadastros = new JMenu("Cadastros");
		menuBar.add(mnCadastros);

		JMenuItem menCadCli = new JMenuItem("Cliente");
		menCadCli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				TelaCliente tc = new TelaCliente();
				tc.setVisible(true);
				desktop.add(tc);
				tc.setPosicao();
			}
		});
		menCadCli.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.ALT_MASK));
		mnCadastros.add(menCadCli);

		menCadUsu = new javax.swing.JMenuItem("Usu\u00E1rio");
		menCadUsu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaUsuario tu = new TelaUsuario();
				tu.setVisible(true);
				desktop.add(tu);
				tu.setPosicao();
			}
		});
		menCadUsu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.ALT_MASK));
		mnCadastros.add(menCadUsu);
		menCadUsu.setEnabled(false);
		

		JMenuItem menCadProd = new JMenuItem("Produto");
		menCadProd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaProduto tp = new TelaProduto();
				tp.setVisible(true);
				desktop.add(tp);
				tp.setPosicao();
			}
		});
		menCadProd.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		mnCadastros.add(menCadProd);

		mnVendas = new javax.swing.JMenu("Opera\u00E7\u00E3o");
		mnVendas.setEnabled(false);
		menuBar.add(mnVendas);

		JMenuItem menOpVenda = new JMenuItem("Venda");
		menOpVenda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaVenda tv = new TelaVenda();
				tv.setVisible(true);
				desktop.add(tv);
				tv.setPosicao();
			}
		});
		menOpVenda.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.ALT_MASK));
		mnVendas.add(menOpVenda);

		JMenu mnRelatrios = new JMenu("Relat\u00F3rios");
		menuBar.add(mnRelatrios);

		JMenuItem menRelVen = new JMenuItem("Vendas");
		menRelVen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int confirmar = JOptionPane.showConfirmDialog(null, "Confirma a emissão do relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(confirmar == JOptionPane.YES_OPTION) {
					Relatorio re = new Relatorio();
					re.imprimirVendaTotal();	
				}
			}
		});
		mnRelatrios.add(menRelVen);

		JMenuItem menRelVenPer = new JMenuItem("Vendas p/ periodo");
		menRelVenPer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaRelData trd = new TelaRelData();
				trd.setVisible(true);
				desktop.add(trd);
				trd.setPosicao();
			}
		});
		mnRelatrios.add(menRelVenPer);

		JMenuItem menRelEst = new JMenuItem("Produtos em estoque");
		menRelEst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int confirmar = JOptionPane.showConfirmDialog(null, "Confirma a emissão do relatório?", "Atenção", JOptionPane.YES_NO_OPTION);
				if(confirmar == JOptionPane.YES_OPTION) {
					Relatorio re = new Relatorio();
					re.imprimirProdutos();	
				}
			}
		});
		mnRelatrios.add(menRelEst);

		JMenu menAjuda = new JMenu("Ajuda");
		menuBar.add(menAjuda);

		JMenuItem menSobre = new JMenuItem("Sobre");
		menSobre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaSobre sobre = new TelaSobre();
				sobre.setVisible(true);
			}
		});
		menSobre.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_MASK));
		menAjuda.add(menSobre);

		JMenu mnOpes = new JMenu("Op\u00E7\u00F4es");
		menuBar.add(mnOpes);

		JMenuItem menSair = new JMenuItem("Sair");
		menSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja finalizar o sistema?", "Atenção",JOptionPane.YES_NO_OPTION);
				if(sair == JOptionPane.YES_NO_OPTION) System.exit(0);
			}
		});
		menSair.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_MASK));
		mnOpes.add(menSair);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(TelaPrincipal.class.getResource("/br/com/comercx/icone/shop.png")));
		label.setBounds(1067, 461, 207, 210);
		contentPane.add(label);

		lblUsuario = new javax.swing.JLabel("");
		lblUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsuario.setBounds(1067, 130, 171, 42);
		contentPane.add(lblUsuario);
		
		desktop = new JDesktopPane();
		desktop.setBackground(new Color(51, 102, 153));
		desktop.setBounds(0, 22, 990, 719);
		contentPane.add(desktop);
	}
	
}

package br.com.comercx.telas;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import br.com.comercx.bean.Venda;
import br.com.comercx.reports.Relatorio;

import javax.swing.JFormattedTextField;
import java.awt.Font;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaRelData extends JInternalFrame {
	
	private JFormattedTextField txtData1;
	private JFormattedTextField txtData2;
	private MaskFormatter m;
	private SimpleDateFormat formato;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelData frame = new TelaRelData();
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
	public TelaRelData() {
		setTitle("Vendas por Periodo");
		setMaximizable(true);
		setClosable(true);
		setIconifiable(true);
		setBounds(100, 100, 457, 284);
		getContentPane().setLayout(null);
		
		JLabel lblDe = new JLabel("De:");
		lblDe.setBounds(91, 95, 46, 14);
		getContentPane().add(lblDe);
		
		JLabel lblAt = new JLabel("At\u00E9:");
		lblAt.setBounds(91, 157, 46, 14);
		getContentPane().add(lblAt);
		
		try {
			m = new MaskFormatter("##/##/####");
			m.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		txtData1 = new JFormattedTextField(m);
		txtData1.setBounds(120, 88, 194, 28);
		getContentPane().add(txtData1);
		
		txtData2 = new JFormattedTextField(m);
		txtData2.setBounds(120, 150, 194, 28);
		getContentPane().add(txtData2);
		
		JLabel lblInformeOPerodo = new JLabel("Informe o per\u00EDodo de vendas");
		lblInformeOPerodo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInformeOPerodo.setBounds(121, 26, 233, 21);
		getContentPane().add(lblInformeOPerodo);
		
		JButton btnNewButton = new JButton("Gerar Relat\u00F3rio");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Venda v1 = new Venda();
				Venda v2 = new Venda();
				formato = new SimpleDateFormat("dd/MM/yyyy");
				Date d1 = new Date();
				Date d2 = new Date();
				try {
					if(!txtData1.getText().isEmpty() || !txtData2.getText().isEmpty()) {
						d1 = formato.parse(txtData1.getText());
						d2 = formato.parse(txtData2.getText());
						v1.setData(d1);
						v2.setData(d2);
						Relatorio re = new Relatorio();
						re.imprimirVendasPorPeriodo(v1, v2);
					} else JOptionPane.showMessageDialog(null, "Preencha os campos!", "Atenção", JOptionPane.WARNING_MESSAGE);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(178, 189, 136, 43);
		getContentPane().add(btnNewButton);

	}
	public void setPosicao() {
	    Dimension d = this.getDesktopPane().getSize();
	    this.setLocation((d.width - this.getSize().width) / 2, (d.height - this.getSize().height) / 2); 
	}
}

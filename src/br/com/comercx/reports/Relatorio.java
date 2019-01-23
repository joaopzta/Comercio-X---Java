package br.com.comercx.reports;

import java.sql.*;
import java.util.HashMap;

import javax.swing.JOptionPane;

import br.com.comercx.bean.Venda;
import br.com.comercx.conexao.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class Relatorio {
	
	
	public void imprimirProdutos() {
		Connection con = ModuloConexao.getConection();
		try {
			JasperPrint print = JasperFillManager.fillReport("D:/Projetos/Reports/comercx/ProdutosEstoque.jasper", null, con);
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			ModuloConexao.closeConnection(con);
		}
	}
	public void imprimirVendaTotal() {
		Connection con = ModuloConexao.getConection();
		try {
			JasperPrint print = JasperFillManager.fillReport("D:/Projetos/Reports/comercx/VendaTotal.jasper", null, con);
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			ModuloConexao.closeConnection(con);
		}
	}
	
	public void imprimirVenda(Venda v) {
		
		Connection con = ModuloConexao.getConection();
		
		try {
			HashMap<String, Object> filtro = new HashMap<String, Object>();
			filtro.put("idvenda", v.getNum());
			JasperPrint print = JasperFillManager.fillReport("D:/Projetos/Reports/comercx/Venda.jasper",filtro,con);
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			ModuloConexao.closeConnection(con);
		}
		
	}
	public void imprimirVendasPorPeriodo(Venda v1, Venda v2) {
		
		Connection con = ModuloConexao.getConection();
		
		try {
			HashMap<String, Object> filtro = new HashMap<String, Object>();
			filtro.put("data1", v1.getData());
			filtro.put("data2", v2.getData());
			JasperPrint print = JasperFillManager.fillReport("D:/Projetos/Reports/comercx/VendasPorPeriodo.jasper",filtro,con);
			JasperViewer.viewReport(print, false);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		} finally {
			ModuloConexao.closeConnection(con);
		}
		
	}

}

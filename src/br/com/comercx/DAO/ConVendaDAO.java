package br.com.comercx.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.comercx.bean.Cliente;
import br.com.comercx.bean.ItemVenda;
import br.com.comercx.bean.Venda;
import br.com.comercx.conexao.ModuloConexao;

public class ConVendaDAO {
	
	
	public void setarVenda(Venda v) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("select * from venda where idvenda=?");
			stmt.setInt(1, v.getNum());
			rs = stmt.executeQuery();
			while(rs.next()) {
				v.setNum(rs.getInt(1));
				v.setData(rs.getDate(2));
				v.setTotal(rs.getDouble(3));
				v.setIdCli(rs.getInt(4));
				v.setSituac(rs.getString(5));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt, rs);
		}
	}
	 
	public List<ItemVenda> setarItemVenda(ItemVenda iv) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select iv.idprod, p.nomeprod, iv.qtditem, iv.valunititem from itemvenda iv\r\n" + 
				"join venda v\r\n" + 
				"on v.idvenda = iv.idvenda\r\n" + 
				"join produto p\r\n" + 
				"on p.idprod = iv.idprod where iv.idvenda=?";
		List<ItemVenda> ivL = new ArrayList<>();
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, iv.getIdVenda());
			rs = stmt.executeQuery();
			while(rs.next()) {
				ItemVenda aux = new ItemVenda();
				aux.setId(rs.getInt(1));
				aux.setDesc(rs.getString(2));
				aux.setQtd(rs.getInt(3));
				aux.setValUnit(rs.getDouble(4));
				ivL.add(aux);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt, rs);
		} return ivL;
	}
	
	@SuppressWarnings("finally")
	public List<Venda> popularTabela() {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Venda> vL = new ArrayList<>();
		String sql = "select venda.idvenda, venda.datavenda, venda.totvenda, cliente.nomecli, venda.situacvenda from venda, cliente where venda.idcliente = cliente.idcliente";
		
		try {
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Venda v = new Venda();
				v.setNum(rs.getInt(1));
				v.setData(rs.getDate(2));
				v.setTotal(rs.getDouble(3));
				v.setCliNome(rs.getString(4));
				v.setSituac(rs.getString(5));
				vL.add(v);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt, rs);
		} return vL;
		
	}
	
	@SuppressWarnings("finally")
	public ResultSet readCliente(Venda v) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select venda.idvenda as Venda, venda.datavenda as DataVenda, venda.totvenda as Total, cliente.nomecli as Cliente, venda.situacvenda as Situação from venda, cliente where venda.idcliente = cliente.idcliente and cliente.nomecli like ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%"+v.getCliNome()+"%");
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			return rs;
		}
	}
	@SuppressWarnings("finally")
	public ResultSet readSituac(Venda v) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select venda.idvenda as Venda, venda.datavenda as DataVenda, venda.totvenda as Total, cliente.nomecli as Cliente, venda.situacvenda as Situação from venda, cliente where venda.idcliente = cliente.idcliente and venda.situacvenda like ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, v.getSituac()+"%");
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			return rs;
		}
	}
	@SuppressWarnings("finally")
	public ResultSet readData(Venda v) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "select venda.idvenda as Venda, venda.datavenda as DataVenda, venda.totvenda as Total, cliente.nomecli as Cliente, venda.situacvenda as Situação from venda, cliente where venda.idcliente = cliente.idcliente and venda.datavenda like ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%"+v.getData()+"%");
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			return rs;
		}
	}

}

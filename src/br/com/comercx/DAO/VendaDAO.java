package br.com.comercx.DAO;

import br.com.comercx.bean.Cliente;
import br.com.comercx.bean.ItemVenda;
import br.com.comercx.bean.Produto;
import br.com.comercx.bean.Venda;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.comercx.conexao.*;
import br.com.comercx.telas.TelaVenda;

public class VendaDAO {
	
	@SuppressWarnings("finally")
	public ResultSet pesquisarCliente(Cliente c) {
		
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.prepareStatement("select idcliente as Id, nomecli as Nome, fonecli as Fone from cliente where nomecli like ?");
			stmt.setString(1, c.getNome());
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			return rs;
		}
	}
	
	public void saveVenda(Venda v) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		String sql = "insert into venda(totvenda, idcliente, situacvenda) values(?,?,?)";	
		//salvar dados da venda
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, v.getTotal());
			stmt.setInt(2, v.getIdCli());
			stmt.setString(3, v.getSituac());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Venda salva com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	}
	public void saveItemVenda(ItemVenda iv) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		String sql = "insert into itemvenda(idprod, idvenda, valunititem, qtditem) values(?,?,?,?)";		
		//salvar dados da venda
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, iv.getId());
			stmt.setInt(2, create().getNum()-1);
			stmt.setDouble(3, iv.getValUnit());
			stmt.setInt(4, iv.getQtd());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	}
	public void updateVenda(Venda v) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		String sql = "update venda set totvenda=?, idcliente=? where idvenda=?";	
		//atualizar dados da venda
		try {
			stmt = con.prepareStatement(sql);
			stmt.setDouble(1, v.getTotal());
			stmt.setInt(2, v.getIdCli());
			stmt.setInt(3, v.getNum());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Venda atualizada com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	}
	public void deleteItemVenda(ItemVenda iv) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		String sql = "delete from itemvenda where idvenda=?";		
		//deleta items da venda
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, iv.getIdVenda());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	}
	public void deleteVenda(Venda v) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		String sql = "delete from venda where idvenda=?";		
		//deleta items da venda
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, v.getNum());
			int ap = stmt.executeUpdate();
			if(ap > 0) {
				JOptionPane.showMessageDialog(null,"Registro excluido com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	}
	
	public void updateItemVenda(ItemVenda iv) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		String sql = "insert into itemvenda(idprod, idvenda, qtditem, valunititem) values(?,?,?,?)";		
		//autualiza items da venda
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, iv.getId());
			stmt.setInt(2, iv.getIdVenda());
			stmt.setInt(3, iv.getQtd());
			stmt.setDouble(4, iv.getValUnit());
			stmt.executeUpdate();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	}
	public Venda create() {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Venda v = new Venda();
		try {
			stmt = con.prepareStatement("select max(idvenda) as id from venda");
			rs = stmt.executeQuery();
			rs.next();
			v.setNum(rs.getInt("id")+1);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt, rs);
		}
		return v;
	}
	public void updateSituac(Venda v) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update venda set situacvenda='Fechada' where idvenda=?");
			stmt.setInt(1, v.getNum());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
	
	
	






	

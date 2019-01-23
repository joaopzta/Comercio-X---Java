package br.com.comercx.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.comercx.bean.Produto;
import br.com.comercx.conexao.ModuloConexao;

public class ProdutoDAO {
	
	public void create(Produto p) {
 
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("insert into produto(nomeprod, ean13, validade, precoprod, qtd) values(?,?,?,?,?)");
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getEan());
			stmt.setString(3, p.getValidade());
			stmt.setDouble(4, p.getPreco());
			stmt.setInt(5, p.getQtd());
			
			int a = stmt.executeUpdate();
			
			if(a > 0) JOptionPane.showMessageDialog(null, "Salvo com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(null, "Falha ao cadastrar o usuário!", null, JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	
	}
	public void update(Produto p) {
		
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("update produto set nomeprod=?, validade=?, precoprod=?, qtd=? where idprod=?");
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getValidade());
			stmt.setDouble(3, p.getPreco());
			stmt.setInt(4, p.getQtd());
			stmt.setInt(5, p.getId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
		
	}
	public void delete(Produto p) {
		
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("delete from produto where idprod=?");
			stmt.setInt(1, p.getId());
			
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Excluido com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
		
	}	
	public void atualizarEstoque(Produto p) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("update produto set qtd=? where idprod=?");
			stmt.setInt(1, p.getQtd());
			stmt.setInt(2, p.getId());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Produto> fulfillTable(){
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> pL = new ArrayList<>();
		
		try {
			
			stmt = con.prepareStatement("select * from produto");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("idprod"));
				p.setNome(rs.getString("nomeprod"));
				p.setPreco(rs.getDouble("precoprod"));
				p.setQtd(rs.getInt("qtd"));
				p.setEan(rs.getString("ean13"));
				p.setValidade(rs.getString("validade"));
				pL.add(p);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt, rs);
		}
		return pL;	
	}
	public List<Produto> readNome(String n){
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Produto> pL = new ArrayList<>();
		
		try {
			
			stmt = con.prepareStatement("select * from produto where nomeprod like ?");
			stmt.setString(1, "%"+n+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Produto p = new Produto();
				p.setId(rs.getInt("idprod"));
				p.setNome(rs.getString("nomeprod"));
				p.setPreco(rs.getDouble("precoprod"));
				p.setQtd(rs.getInt("qtd"));
				p.setEan(rs.getString("ean13"));
				p.setValidade(rs.getString("validade"));
				pL.add(p);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt, rs);
		}
		return pL;	
	}
	

}






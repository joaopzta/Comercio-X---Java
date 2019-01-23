package br.com.comercx.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.comercx.bean.Cliente;
import br.com.comercx.conexao.*;

public class ClienteDAO {
	
	public void create(Cliente c) {
		
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("insert into cliente (nomecli, endcli, fonecli, emailcli) values(?,?,?,?)");
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getEnd());
			stmt.setString(3, c.getFone());
			stmt.setString(4, c.getEmail());
			
			int a = stmt.executeUpdate();
			
			if(a > 0) JOptionPane.showMessageDialog(null, "Salvo com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(null, "Falha ao cadastrar o usuário!", null, JOptionPane.ERROR_MESSAGE);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	}
	
	public void update(Cliente c) {
		
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("update cliente set nomecli=?, endcli=?, fonecli=?, emailcli=? where idcliente=?");
			stmt.setString(1, c.getNome());
			stmt.setString(2, c.getEnd());
			stmt.setString(3, c.getFone());
			stmt.setString(4, c.getEmail());
			stmt.setInt(5, c.getId());
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	}
	public void delete(Cliente c) {
		
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		
		try {
			
			stmt = con.prepareStatement("delete from cliente where idcliente=?");
			stmt.setInt(1, c.getId());
			stmt.executeUpdate();
			
			JOptionPane.showMessageDialog(null, "Excluido com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);	
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
	}
	
	@SuppressWarnings("finally")
	public ResultSet search(String n) {
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//ResultSet temp = null;
		try {
			stmt = con.prepareStatement("select * from cliente where nomecli like ?");
			stmt.setString(1, n);
			rs = stmt.executeQuery();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			return rs;
		}
		
	}
	public List<Cliente> preencherTabela() {
		
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Cliente> listacli = new ArrayList<>();
		
		try {
			
			stmt = con.prepareStatement("select * from cliente");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("idcliente"));
				c.setNome(rs.getString("nomecli"));
				c.setFone(rs.getString("fonecli"));
				c.setEnd(rs.getString("endcli"));
				c.setEmail(rs.getString("emailcli"));
				listacli.add(c);
			}
			
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt, rs);
		}
		
		return listacli;
	}
	public List<Cliente> readNome(String n) {
		
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Cliente> listacli = new ArrayList<>();
		
		try {
			
			stmt = con.prepareStatement("select * from cliente where nomecli like ?");
			stmt.setString(1, "%"+n+"%");
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setId(rs.getInt("idcliente"));
				c.setNome(rs.getString("nomecli"));
				c.setFone(rs.getString("fonecli"));
				c.setEnd(rs.getString("endcli"));
				c.setEmail(rs.getString("emailcli"));
				listacli.add(c);
			}
			
		} catch (SQLException e){
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt, rs);
		}
		
		return listacli;
	}

}






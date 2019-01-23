package br.com.comercx.DAO;

import java.sql.*;

import javax.swing.JOptionPane;

import br.com.comercx.bean.Usuario;
import br.com.comercx.conexao.*;

public class UsuarioDAO {

	public void create(Usuario u) {

		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("insert into usuario(nome, fone, login, senha, perfil) values (?, ?, ?, ?, ?)");
			stmt.setString(1, u.getNome());
			stmt.setString(2, u.getFone());
			stmt.setString(3, u.getLogin());
			stmt.setString(4, u.getSenha());
			stmt.setString(5, u.getPerfil());

			int a = stmt.executeUpdate();
			if(a > 0) JOptionPane.showMessageDialog(null, "Salvo com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(null, "Não ao cadastrar o usuário!", null, JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}

	}

	public void read(Usuario u) {

		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.prepareStatement("select * from usuario where iduser=?");
			stmt.setInt(1, u.getId());
			rs = stmt.executeQuery();
			if (rs.next()) {
				u.setId(rs.getInt(1));
				u.setNome(rs.getString(2));
				u.setFone(rs.getString(3));
				u.setLogin(rs.getString(4));
				u.setSenha(rs.getString(5));
				u.setPerfil(rs.getString(6));
			} else JOptionPane.showMessageDialog(null, "Usuário não existe", "Atenção", JOptionPane.WARNING_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt, rs);
		}

	}
	
	public void update(Usuario u) {

		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;

		try {
			stmt = con.prepareStatement("update usuario set nome=?, fone=?, login=?, senha=?, perfil=? where iduser=?");
			stmt.setString(1, u.getNome());
			stmt.setString(2, u.getFone());
			stmt.setString(3, u.getLogin());
			stmt.setString(4, u.getSenha());
			stmt.setString(5, u.getPerfil());
			stmt.setInt(6, u.getId());
			
			stmt.executeUpdate();

			JOptionPane.showMessageDialog(null, "Dados alterados com sucesso!", null, JOptionPane.INFORMATION_MESSAGE);
			//JOptionPane.showMessageDialog(null, "Não ao cadastrar o usuário!", null, JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}

	}
	
	public void delete(Usuario u) {
		
		Connection con = ModuloConexao.getConection();
		PreparedStatement stmt = null;
		try {
			
			stmt = con.prepareStatement("delete from usuario where iduser=?");
			stmt.setInt(1, u.getId());
			stmt.executeUpdate();
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERRO: " + e, "Atenção", JOptionPane.ERROR_MESSAGE);
		} finally {
			ModuloConexao.closeConnection(con, stmt);
		}
		
	}

}

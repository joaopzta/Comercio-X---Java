package br.com.comercx.conexao;

import java.sql.*;

public class ModuloConexao {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/dbcomercx?useTimezone=true&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASS = "";

	public static Connection getConection() {

		try {

			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASS);

		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	public static void closeConnection(Connection con) {

		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void closeConnection(Connection con, PreparedStatement stmt) {

		closeConnection(con);
		
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		
		closeConnection(con, stmt);

		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs, PreparedStatement aux) {
		
		closeConnection(con, stmt, rs);
		
		try {
			if (aux != null) {
				aux.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

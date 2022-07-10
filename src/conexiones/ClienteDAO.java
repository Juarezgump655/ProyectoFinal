package conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.Cliente;

public class ClienteDAO {
	PreparedStatement ps;
	ResultSet rs;
	Connection con;
	Conexion ingresar = new Conexion();
	Cliente sc = new Cliente();
	Object[][] listar;

	public Object[][] listar() {
		String sql = "Select * from cliente";
		int filas = 0;
		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				filas++;
			}
			listar = new Object[filas][5];
			int y = 0;
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				listar[y][0] = rs.getInt(1);
				listar[y][1] = rs.getString(2);
				listar[y][2] = rs.getString(3);
				listar[y][3] = rs.getString(4);
				listar[y][4] = rs.getString(5);
				y++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		/*
		 * for (int i = 0; i < listar.length; i++) { System.out.println(listar[i][0]); }
		 */
		return listar;
	}

	public void crear(String nombre, int NIT, String correo, String genero) {

		String sql = "INSERT INTO cliente(nombre, NIT, correo, genero)values(?,?,?,?)";

		try {

			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setInt(2, NIT);
			ps.setString(3, correo);
			ps.setString(4, genero);
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void modificar(Cliente s) {
		String sql = "update cliente set nombre=?, NIT=?, correo=?, genero=? where codigo=?";

		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setString(1, s.getNombre());
			ps.setInt(2, s.getNit());
			ps.setString(3, s.getCorreo());
			ps.setString(4, s.getGenero());
			ps.setInt(5, s.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void eliminar(int codigo) {

		String sql = "delete from cliente where codigo=?";

		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}
}

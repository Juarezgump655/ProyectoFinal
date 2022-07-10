package conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.Vendedor;

public class VendedoresDAO {
	PreparedStatement ps;
	ResultSet rs;

	Conexion ingresar = new Conexion();
	Connection con= ingresar.Conectar();
	Vendedor sc = new Vendedor();
	Object[][] listar;

	public Object[][] listar() {
		String sql = "Select * from vendedores";
		int filas = 0;
		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				filas++;
			}
			listar = new Object[filas][6];
			int y = 0;
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				listar[y][0] = rs.getInt(1);
				listar[y][1] = rs.getString(2);
				listar[y][2] = rs.getInt(3);
				listar[y][3] = rs.getInt(4);
				listar[y][4] = rs.getString(5);
				listar[y][5] = rs.getString(6);
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
	public void crear(String nombre, int caja, int ventas, String genero, String Paswword) {

		String sql = "INSERT INTO vendedores(nombre, caja, ventas, genero, Paswword)values(?,?,?,?,?)";

		try {

			ps = con.prepareStatement(sql);
			ps.setString(1, nombre);
			ps.setInt(2, caja);
			ps.setInt(3, ventas);
			ps.setString(4, genero);
			ps.setString(5, Paswword);
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void modificar(Vendedor s) {
		String sql = "update vendedores set nombre=?, caja=?, ventas=?, genero=?, Paswword=? where codigo=?";

		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setString(1, s.getNombre());
			ps.setInt(2, s.getCaja());
			ps.setInt(3, s.getVentas());
			ps.setString(4, s.getGenero());
			ps.setString(5, s.getPaswword());
			ps.setInt(6, s.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void eliminar(int codigo) {

		String sql = "delete from vendedores where codigo=?";

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

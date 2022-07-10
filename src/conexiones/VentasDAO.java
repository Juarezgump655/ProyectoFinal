package conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.Producto;
import modelo.Ventas;

public class VentasDAO {
	PreparedStatement ps;
	ResultSet rs;
	Connection con;
	Conexion ingresar = new Conexion();
	Ventas sc = new Ventas();
	Object[][] listar;
	Ventas p = new Ventas();

	public Object[][] listar() {
		String sql = "Select * from ventas";
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
				listar[y][1] = rs.getInt(2);
				listar[y][2] = rs.getString(3);
				listar[y][3] = rs.getString(4);
				listar[y][4] = rs.getDouble(5);
				y++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return listar;
	}
	public Object[][] listarf(int noFacturas) {
		String sql = "Select * from ventas WHERE Nofactura=?  ";
		int filas = 0;
		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setInt(1, noFacturas);
			rs = ps.executeQuery();
			while (rs.next()) {
				filas++;
			}

			listar = new Object[filas][5];
			int y = 0;
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setInt(1, noFacturas);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				listar[y][0] = rs.getInt(1);
				listar[y][1] = rs.getInt(2);
				listar[y][2] = rs.getString(3);
				listar[y][3] = rs.getString(4);
				listar[y][4] = rs.getDouble(5);
				y++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return listar;
	}
	
	
	public Object[][] listarNIT(int NIT) {
		String sql = "Select * from ventas WHERE NIT=?  ";
		int filas = 0;
		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setInt(1, NIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				filas++;
			}

			listar = new Object[filas][5];
			int y = 0;
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setInt(1, NIT);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				listar[y][0] = rs.getInt(1);
				listar[y][1] = rs.getInt(2);
				listar[y][2] = rs.getString(3);
				listar[y][3] = rs.getString(4);
				listar[y][4] = rs.getDouble(5);
				y++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return listar;
	}
	
	public Object[][] listarNombre(String nombre) {
		String sql = "Select * from ventas WHERE Nombre=?  ";
		int filas = 0;
		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			while (rs.next()) {
				filas++;
			}

			listar = new Object[filas][5];
			int y = 0;
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				listar[y][0] = rs.getInt(1);
				listar[y][1] = rs.getInt(2);
				listar[y][2] = rs.getString(3);
				listar[y][3] = rs.getString(4);
				listar[y][4] = rs.getDouble(5);
				y++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return listar;
	}
	public Object[][] listarFecha(String Fecha) {
		String sql = "Select * from ventas WHERE Fecha=?  ";
		int filas = 0;
		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setString(1, Fecha);
			rs = ps.executeQuery();
			while (rs.next()) {
				filas++;
			}

			listar = new Object[filas][5];
			int y = 0;
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setString(1, Fecha);
			rs = ps.executeQuery();
			while (rs.next()) {
				
				listar[y][0] = rs.getInt(1);
				listar[y][1] = rs.getInt(2);
				listar[y][2] = rs.getString(3);
				listar[y][3] = rs.getString(4);
				listar[y][4] = rs.getDouble(5);
				y++;
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		return listar;
	}
}

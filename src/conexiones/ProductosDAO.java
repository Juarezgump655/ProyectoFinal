package conexiones;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import conexion.Conexion;
import modelo.Producto;
import modelo.Sucursales;

public class ProductosDAO {
	PreparedStatement ps;
	ResultSet rs;
	Connection con;
	Conexion ingresar = new Conexion();
	Producto sc = new Producto();
	Object[][] listar;
	Producto p = new Producto();

	public Object[][] listar() {
		String sql = "Select * from productos";
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
				listar[y][3] = rs.getInt(4);
				listar[y][4] = rs.getDouble(5);
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

	public void crear(String nombre, String descripcion, int cantidad, double precio) {

		String sql = "INSERT INTO productos(nombre, descripcion, cantidad, precio)values(?,?,?,?)";

		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);

			ps.setString(1, nombre);
			ps.setString(2, descripcion);
			ps.setInt(3, cantidad);
			ps.setDouble(4, precio);
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void modificar(Producto s) {
		String sql = "update productos set nombre=?, descripcion=?, cantidad=?, precio=? where codigo=?";

		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setString(1, s.getNombre());
			ps.setString(2, s.getDescripcion());
			ps.setInt(3, s.getCantidad());
			ps.setDouble(4, s.getPrecio());
			ps.setInt(5, s.getCodigo());
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public void eliminar(int codigo) {

		String sql = "delete from productos where codigo=?";

		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			ps.setInt(1, codigo);
			ps.executeUpdate();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

	}

	public Producto agregarProducto(int codigo, int cantidad) {

		String sql = "select * from productos";

		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				if (codigo == rs.getInt(1)) {

					if (cantidad < rs.getInt(4)) {
						p.setCodigo(rs.getInt(1));
                		p.setNombre(rs.getString(2));            
                		p.setDescripcion(rs.getString(3));
                		p.setCantidad(rs.getInt(4));
                		p.setPrecio(rs.getDouble(5));
						return p;
					}else {
						
						JOptionPane.showMessageDialog(null, "LA CANTIDAD SOBREPASA NUESTRO NUMERO DE EXISTENCIAS \n"
								+ "CANTIDAD EN INVENTARIO:" + rs.getInt(4));
					}

				}
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}

		return null;
	}

}

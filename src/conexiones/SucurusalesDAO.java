package conexiones;
import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.Sucursales;
public class SucurusalesDAO {
	PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion ingresar = new Conexion();
    Sucursales sc = new Sucursales();
	Object [][] listar;
	
    public Object[][] listar() {
		String sql = "Select * from sucursales";
		int filas=0;
		try {
			con = ingresar.Conectar();
			ps = con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			while(rs.next()) {
				filas++;
			}
			listar = new Object[filas][5];
			int y=0;
			 con = ingresar.Conectar();
	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery();
			while(rs.next()) {
				listar[y][0] = rs.getInt(1);
				listar[y][1] = rs.getString(2);
				listar[y][2] = rs.getString(3);
				listar[y][3] = rs.getString(4);
				listar[y][4] = rs.getInt(5);
				y++;
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		/*
		  for (int i = 0; i < listar.length; i++) {
				System.out.println(listar[i][0]);
			}
		*/
		return listar; 
	}
    public void crear(String nombre, String direccion, String correo, int telefono) {

        String sql = "INSERT INTO sucursales(nombre,direccion,correo,telefono)values(?,?,?,?)";

        try {
            con = ingresar.Conectar();
            ps = con.prepareStatement(sql);

            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, correo);
            ps.setInt(4, telefono);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    public void modificar(Sucursales s) {
    	String sql = "update sucursales set nombre=?, direccion=?, correo=?, telefono=? where codigo=?";
        
    	try {
    		con = ingresar.Conectar();
            ps = con.prepareStatement(sql);            
            ps.setString(1, s.getNombre());
            ps.setString(2, s.getDireccion());
            ps.setString(3, s.getCorreo());
            ps.setInt(4, s.getTelefonos());
            ps.setInt(5, s.getCodigo());
            ps.executeUpdate();
    	}catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }
    
    public void eliminar(int codigo) {

        String sql = "delete from sucursales where codigo=?";

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

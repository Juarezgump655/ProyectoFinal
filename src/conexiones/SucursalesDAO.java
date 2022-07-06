/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

import conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import modelo.Sucursales;

/**
 *
 * @author ajuar
 */
public class SucursalesDAO {

    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion ingresar = new Conexion();
    Sucursales sc = new Sucursales();

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

    public void modificar(String nombre, String direccion, String correo, int telefono, int codigo) {

        String sql = "update sucursales nombre=?, direccion=?, correo=?, telefono=? WHERE codigo=?";

        try {
            con = ingresar.Conectar();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, correo);
            ps.setInt(4, telefono);
            ps.setInt(5, codigo);
        } catch (Exception e) {
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

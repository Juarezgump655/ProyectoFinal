package Procesos;

import conexion.Conexion;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Inicio {

    //iniciamos nuestros atributos de la vista 
    String Nombre1;
    JFrame inicio = new JFrame();
    JPanel p1 = new JPanel();
    JLabel label1 = new JLabel("INICIO DE SESION");
    JLabel label2 = new JLabel("Usuario");
    JLabel label3 = new JLabel("Contraseñia");
    JTextField txtUsuario = new JTextField();
    JPasswordField txtContraseña = new JPasswordField();
    JButton btnIniciar = new JButton("INICIAR SESION");
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Conexion ingresar = new Conexion();
    //cargamos el codigo de colores y los asignamos a una variable
    Color color1 = new Color(0x516FFF);
    Object[][] Vendedores = new Object[400][6];

    public static void main(String[] args) {
        Inicio p1 = new Inicio();
        try {
            p1.ejecutar();
        } catch (ClassNotFoundException e) {

            e.printStackTrace();
        }

    }

    //cargamos nuestros atributos a traves de este netodo
    public void frame() throws ClassNotFoundException {
        inicio.setLocationRelativeTo(null);
        inicio.setUndecorated(true);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
        inicio.setTitle("INICIO DE SESION");

        // x y ancho y alto
        inicio.setBounds(700, 300, 500, 500);
        inicio.setVisible(true);
        inicio.setResizable(false);

        inicio.add(p1);
        p1.setSize(800, 500);
        p1.setLayout(null);
        p1.setVisible(true);
        p1.setBackground(Color.white);

        // carga de archivo .data para la presistencia de datos
        try {
            ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_Vendedores.dat"));
            Vendedores = (Object[][]) recuperar.readObject();
            recuperar.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "NO EXISTEN VENDEDORES");
        }

    }

    //cargamos nuestros atributos label a traves de este netodo
    public void label() {
        p1.add(label1);
        label1.setFont(new Font("Roboto Black", Font.PLAIN, 22));
        label1.setBounds(150, 22, 300, 93);

        p1.add(label2);
        label2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        label2.setBounds(120, 120, 125, 93);

        p1.add(label3);
        label3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        label3.setBounds(120, 270, 125, 93);

        p1.add(txtUsuario);
        txtUsuario.setBounds(120, 190, 200, 25);
        txtUsuario.setFont(new Font("Roboto Light", Font.PLAIN, 22));

        p1.add(txtContraseña);
        txtContraseña.setBounds(120, 340, 200, 25);
        txtContraseña.setFont(new Font("Roboto Light", Font.PLAIN, 22));

    }

    //cargamos nuestros atributos button a traves de este netodo
    public void button() {

        btnIniciar.setBounds(140, 400, 200, 50);
        btnIniciar.setFont(new Font("Roboto Medium", Font.PLAIN, 20));

        btnIniciar.setForeground(Color.white);
        btnIniciar.setBackground(color1);
        p1.add(btnIniciar);

        // Funcionalidad
        ActionListener ingresar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (verificarInicio(txtUsuario.getText(), txtContraseña.getText())) {
                    JOptionPane.showMessageDialog(null, "BIENVENIDO");
                    inicio.setVisible(false);
                    MenuPrincipal mp = new MenuPrincipal();
                    mp.ejecutar();
                } else try {
                    if (verificar(txtUsuario.getText(), txtContraseña.getText())) {
                        LoginVendedores lv = new LoginVendedores();
                        lv.setNombre(txtUsuario.getText());
                        JOptionPane.showMessageDialog(null, "BIENVENIDO");
                        inicio.setVisible(false);
                        try {
                            lv.ejecutar();
                        } catch (ClassNotFoundException e1) {

                            e1.printStackTrace();
                        }

                    } else {
                        JOptionPane.showMessageDialog(null, "DATOS EQUIVOCADOS O EL USUARIO NO EXISTE");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };

        // Acci�n del evento
        btnIniciar.addActionListener(ingresar);
        JButton cerrarProgra = new JButton("X");
        cerrarProgra.setBounds(0, 0, 50, 50);
        cerrarProgra.setForeground(Color.BLACK);
        cerrarProgra.setBackground(Color.WHITE);
        cerrarProgra.setFont(new Font("Roboto black", Font.PLAIN, 22));
        p1.add(cerrarProgra);

        ActionListener funcionCerrar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        //accion evento
        cerrarProgra.addActionListener(funcionCerrar);

    }

    //iniciamos todos nuestros metodos donde cargamos los atributos
    public void ejecutar() throws ClassNotFoundException {
        button();
        frame();
        label();

    }

    //verificadores para ver que tipo de usuario es el que entra a la aplicacion
    public boolean verificarInicio(String nombreUsuario, String contraseñiaUsuario) {

        Boolean Nombre = false;
        Boolean Contra = false;
        if ("Admin".equals(nombreUsuario)) {
            Nombre = true;
        }
        if ("Admin".equals(contraseñiaUsuario)) {
            Contra = true;
        }
        if (Nombre == true && Contra == true) {
            return true;
        } else {
            return false;
        }

    }

    /*
    public boolean verificarInicioV(String nombreUsuario, String contraseñiaUsuario) {
        boolean login = false;

        for (int i = 0; i < Vendedores.length; i++) {
            if (nombreUsuario.equals(Vendedores[i][1]) && contraseñiaUsuario.equals(Vendedores[i][5])) {
                login = true;
                break;
            } else {
                login = false;
            }
        }
        return login;
    }
     */

    public boolean verificar(String correo, String contraseña) throws SQLException {
        boolean pase = false;
        con = ingresar.Conectar();
        String sql = "SELECT nombre, Paswword FROM vendedores WHERE nombre=?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            rs = ps.executeQuery();

            if (rs.next()) {

                if (contraseña.equals(rs.getString(2))) {
                    Nombre1 = rs.getString(1);
                    pase = true;
                }

            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return pase;

    }
}

package Procesos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MenuPrincipal {
	JFrame Principal = new JFrame();
	JTabbedPane pestañas = new JTabbedPane();
	JPanel productos = new JPanel();
	JPanel clientes = new JPanel();
	JPanel sucursales = new JPanel();
	JPanel vendedores = new JPanel();
	MenuSucursales ms = new MenuSucursales();
	MenuProductos mp = new MenuProductos();
	MenuCLientes mc = new MenuCLientes();
	MenuVendedores mv = new MenuVendedores();
	Color color1 = new Color(0x516FFF);

	private void frame() throws ClassNotFoundException {
		Principal.setLocationRelativeTo(null);
		Principal.setUndecorated(true);
		Principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la
		
		// barra de arriba al programa
		Principal.setTitle("Hoteles GT-Usuarios");
		Principal.setBackground(Color.WHITE);

		sucursales.setLayout(null);
		productos.setLayout(null);
		clientes.setLayout(null);
		vendedores.setLayout(null);
		// x y ancho y alto
		Principal.setBounds(500, 200, 1000, 700);
		Principal.setVisible(true);
		Principal.setResizable(false);
		JButton cerrarSesion = new JButton ("Cerrar Sesion");
		cerrarSesion.setBounds(825, 650, 150, 40);
		cerrarSesion.setForeground(Color.white);
		cerrarSesion.setBackground(color1);
		cerrarSesion.setFont(new Font("Roboto light", Font.PLAIN, 16));
		
		JButton cerrarProgra = new JButton ("X");
		cerrarProgra.setBounds(950, 0, 50, 50);
		cerrarProgra.setForeground(Color.BLACK);
		cerrarProgra.setBackground(Color.WHITE);
		cerrarProgra.setFont(new Font("Roboto black", Font.PLAIN, 22));
		Principal.add(cerrarProgra);	
		Principal.add(cerrarSesion);
		Principal.add(pestañas);
		
		ActionListener funcionCerrar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		};

		//Acción del evento
		cerrarProgra.addActionListener(funcionCerrar);
		
		
		ActionListener funcionCerrarapp = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					Principal.setVisible(false);
				
					Inicio log = new Inicio();
					try {
						log.ejecutar();
					} catch (ClassNotFoundException e1) {
					
						e1.printStackTrace();
					}
					
			}
		};

		//Acción del evento
		cerrarSesion.addActionListener(funcionCerrarapp);

		productos.setBackground(Color.WHITE);
		clientes.setBackground(Color.WHITE);
		sucursales.setBackground(Color.WHITE);
		vendedores.setBackground(Color.WHITE);

		pestañas.add("Sucursales", sucursales);
		pestañas.add("Clientes", clientes);
		pestañas.add("Vendedores", vendedores);
		pestañas.add("Productos", productos);

		// añadimos los atributos al panel sucursales de la clase menuSucursales
		ms.ejecutar();
		sucursales.add(ms.sp);
		sucursales.add(ms.crear);
		sucursales.add(ms.actualizar);
		sucursales.add(ms.carga);
		sucursales.add(ms.eliminnar);
		sucursales.add(ms.pdf);

		// añadimos los atributos al panel productos de la clase menuProductos
		mp.ejecutar();
		productos.add(mp.sp);
		productos.add(mp.crear);
		productos.add(mp.actualizar);
		productos.add(mp.carga);
		productos.add(mp.eliminnar);
		productos.add(mp.pdf);

		// añadimos los atributos al panel clientes de la clase menuClientes
		mc.ejecutar();
		clientes.add(mc.sp);
		clientes.add(mc.crear);
		clientes.add(mc.actualizar);
		clientes.add(mc.carga);
		clientes.add(mc.eliminnar);
		clientes.add(mc.pdf);

		// añadimos los atributos al panel vendedores de la clase menuVendedores
		mv.ejecutar();
		vendedores.add(mv.sp);
		vendedores.add(mv.crear);
		vendedores.add(mv.actualizar);
		vendedores.add(mv.carga);
		vendedores.add(mv.eliminnar);
		vendedores.add(mv.pdf);
		
		

	}

	public void ejecutar() {
		try {
			frame();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	/*
	public void cerrarxd() {
		sucursales.setVisible(false);
	}
	*/
	

}

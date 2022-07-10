package Procesos;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import conexion.Conexion;
import conexiones.SucurusalesDAO;
import modelo.Sucursales;

public class MenuSucursales {

	// botones
	JButton crear = new JButton("Crear");
	JButton actualizar = new JButton("Actualizar");
	JButton eliminnar = new JButton("Eliminar");
	JButton carga = new JButton("Carga");
	JButton pdf = new JButton("Realizar PDF");
	JTable tabla;
	JScrollPane sp;
	PreparedStatement ps;
	ResultSet rs;
	Connection con;
	Conexion ingresar = new Conexion();
	// color

	Color color1 = new Color(0x516FFF);

	// Matriz
	Object[][] sucursales;

	private void botones() {
		crear.setBounds(475, 60, 200, 75);
		crear.setForeground(Color.white);
		crear.setBackground(color1);
		crear.setFont(new Font("Roboto medium", Font.PLAIN, 32));

		actualizar.setBounds(475, 250, 200, 75);
		actualizar.setForeground(Color.white);
		actualizar.setBackground(color1);
		actualizar.setFont(new Font("Roboto medium", Font.PLAIN, 32));

		eliminnar.setBounds(750, 60, 200, 75);
		eliminnar.setForeground(Color.white);
		eliminnar.setBackground(color1);
		eliminnar.setFont(new Font("Roboto medium", Font.PLAIN, 32));

		carga.setBounds(750, 250, 200, 75);
		carga.setForeground(Color.white);
		carga.setBackground(color1);
		carga.setFont(new Font("Roboto medium", Font.PLAIN, 32));

		pdf.setBounds(550, 450, 310, 75);
		pdf.setForeground(Color.white);
		pdf.setBackground(color1);
		pdf.setFont(new Font("Roboto medium", Font.PLAIN, 32));

		ActionListener ingresar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crear();
			}
		};

		// Acción del evento
		crear.addActionListener(ingresar);

		ActionListener funcion_carga = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					carga_masiva();
				} catch (IOException | ParseException e1) {
					e1.printStackTrace();
				}

			}
		};

		// Acción del evento
		carga.addActionListener(funcion_carga);

		ActionListener funcion_Eliminar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					eliminar();
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				}

			}
		};

		// Acción del evento
		eliminnar.addActionListener(funcion_Eliminar);

		//
		ActionListener modificarS = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modificar();

			}
		};

		// Acción del evento
		actualizar.addActionListener(modificarS);

		ActionListener generarPdf = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					generar_pdf();
				} catch (FileNotFoundException e1) {

					e1.printStackTrace();
				} catch (DocumentException e1) {

					e1.printStackTrace();
				}

			}
		};

		// Acción del evento
		pdf.addActionListener(generarPdf);

	}

	private void table() throws ClassNotFoundException {
		String[] datos = { "Codigo", "Nombre", "Direccion", "Correo", "Telefonos" };

		SucurusalesDAO sf = new SucurusalesDAO();
		sucursales = sf.listar();
		tabla = new JTable(sucursales, datos);
		sp = new JScrollPane(tabla);
		sp.setBounds(30, 30, 400, 600);

	}

	private void crear() {

		JFrame crearSucursal = new JFrame();
		JPanel p1 = new JPanel();
		crearSucursal.setLocationRelativeTo(null);
		crearSucursal.setUndecorated(true);
		crearSucursal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
		crearSucursal.setTitle("MENU PRINCIPAL");
		crearSucursal.setBackground(Color.WHITE);

		crearSucursal.setLayout(null);
		// x y ancho y alto
		crearSucursal.setBounds(750, 250, 500, 600);
		crearSucursal.setVisible(true);
		crearSucursal.setResizable(false);
		crearSucursal.add(p1);
		p1.setSize(500, 600);
		p1.setLayout(null);
		p1.setVisible(true);
		p1.setBackground(Color.white);

		JLabel l0 = new JLabel("Crear Sucursal");
		JLabel l1 = new JLabel("Codigo");
		JLabel l2 = new JLabel("Nombre");
		JLabel l3 = new JLabel("Direccion");
		JLabel l4 = new JLabel("Correo");
		JLabel l5 = new JLabel("Telefono");

		JTextField T1 = new JTextField();
		JTextField T2 = new JTextField();
		JTextField T3 = new JTextField();
		JTextField T4 = new JTextField();
		JTextField T5 = new JTextField();

		JButton B1 = new JButton("CREAR");

		l0.setFont(new Font("Roboto Black", Font.PLAIN, 22));
		l0.setBounds(175, 10, 250, 25);
		p1.add(l0);

		l1.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		l1.setBounds(75, 90, 100, 25);
		p1.add(l1);

		l2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		l2.setBounds(75, 190, 100, 25);
		p1.add(l2);

		l3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		l3.setBounds(75, 290, 100, 25);
		p1.add(l3);

		l4.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		l4.setBounds(75, 390, 100, 25);
		p1.add(l4);

		l5.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		l5.setBounds(75, 490, 100, 25);
		p1.add(l5);

		T1.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		T1.setBounds(200, 90, 200, 25);
		p1.add(T1);

		T2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		T2.setBounds(200, 190, 200, 25);
		p1.add(T2);

		T3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		T3.setBounds(200, 290, 200, 25);
		p1.add(T3);

		T4.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		T4.setBounds(200, 390, 200, 25);
		p1.add(T4);

		T5.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		T5.setBounds(200, 490, 200, 25);
		p1.add(T5);
		T1.setEditable(false);
		JButton B2 = new JButton("CANCELAR");
		B1.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
		B1.setForeground(Color.white);
		B1.setBackground(color1);
		B1.setBounds(50, 540, 150, 50);
		p1.add(B1);
		
		B2.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
		B2.setForeground(Color.white);
		B2.setBackground(color1);
		B2.setBounds(250, 540, 150, 50);
		p1.add(B2);
		
		ActionListener cancelar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearSucursal.setVisible(false);
				MenuCLientes mu = new MenuCLientes();
				try {
					mu.ejecutar();
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		};
		// Acción del evento
		B2.addActionListener(cancelar);
		ActionListener crearSucursales = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SucurusalesDAO sf = new SucurusalesDAO();
				sf.crear(T2.getText(), T3.getText(), T4.getText(), Integer.parseInt(T5.getText()));
				JOptionPane.showMessageDialog(null, "Se ha agregado Correctamente");
				crearSucursal.setVisible(false);
			}

		};

		// Acción del evento
		B1.addActionListener(crearSucursales);

		ActionListener modificar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modificar();

			}
		};

		// Acción del evento
		actualizar.addActionListener(modificar);

	}

	public void ejecutar() throws ClassNotFoundException {
		botones();
		table();
	}

	private String leerarchivo() {

		JPanel c1 = new JPanel();
		JFileChooser fc = new JFileChooser();
		int op = fc.showOpenDialog(c1);
		String content = "";
		if (op == JFileChooser.APPROVE_OPTION) {

			File pRuta = fc.getSelectedFile();
			String ruta = pRuta.getAbsolutePath();
			File archivo = null;
			FileReader fr = null;
			BufferedReader br = null;

			try {
				archivo = new File(ruta);
				fr = new FileReader(archivo);
				br = new BufferedReader(fr);
				String linea = "";

				while ((linea = br.readLine()) != null) {

					content += linea + "\n";
				}
				return content;

			} catch (FileNotFoundException ex) {
				String resp = (String) JOptionPane.showInputDialog(null, "No se encontro el archivo");
			} catch (IOException ex) {
				String resp = (String) JOptionPane.showInputDialog(null, "No se pudo abrir el archivo");
			} finally {
				try {
					if (null != fr) {
						fr.close();
					}
				} catch (Exception e2) {
					String resp = (String) JOptionPane.showInputDialog(null, "No se encontro el archivo");
					return "";
				}

			}
			return content;

		}
		return null;
	}

	private void carga_masiva() throws FileNotFoundException, IOException, ParseException {

		int x = 0;
		int y = 0;

		String archivo_retorno = leerarchivo();

		JsonParser parse = new JsonParser();
		JsonArray matriz = parse.parse(archivo_retorno).getAsJsonArray();

		for (int i = 0; i < matriz.size(); i++) {
			JsonObject objeto = matriz.get(i).getAsJsonObject();
			SucurusalesDAO sd = new SucurusalesDAO();
			sd.crear(objeto.get("nombre").getAsString(), objeto.get("direccion").getAsString(),
					objeto.get("correo").getAsString(), objeto.get("telefono").getAsInt());
		}

	}

	private void eliminar() throws ClassNotFoundException {
		int posicion = tabla.getSelectedRow();

		if (posicion != -1) {
			SucurusalesDAO sd = new SucurusalesDAO();
			sd.eliminar(Integer.parseInt(sucursales[posicion][0].toString()));

		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una persona");
		}
		table();

	}

	private void modificar() {

		int seleccionar = tabla.getSelectedRow();
		if (seleccionar != -1) {
			JFrame crearSucursal = new JFrame();
			JPanel p1 = new JPanel();
			crearSucursal.setLocationRelativeTo(null);
			crearSucursal.setUndecorated(true);
			crearSucursal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
			crearSucursal.setTitle("MENU PRINCIPAL");
			crearSucursal.setBackground(Color.WHITE);

			crearSucursal.setLayout(null);
			// x y ancho y alto
			crearSucursal.setBounds(750, 250, 500, 600);
			crearSucursal.setVisible(true);
			crearSucursal.setResizable(false);
			crearSucursal.add(p1);
			p1.setSize(500, 600);
			p1.setLayout(null);
			p1.setVisible(true);
			p1.setBackground(Color.white);

			JLabel l0 = new JLabel("Crear Sucursal");
			JLabel l1 = new JLabel("Codigo");
			JLabel l2 = new JLabel("Nombre");
			JLabel l3 = new JLabel("Direccion");
			JLabel l4 = new JLabel("Correo");
			JLabel l5 = new JLabel("Telefono");

			JTextField T1 = new JTextField();
			JTextField T2 = new JTextField();
			JTextField T3 = new JTextField();
			JTextField T4 = new JTextField();
			JTextField T5 = new JTextField();

			JButton B1 = new JButton("CREAR");

			l0.setFont(new Font("Roboto Black", Font.PLAIN, 22));
			l0.setBounds(175, 10, 250, 25);
			p1.add(l0);

			l1.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			l1.setBounds(75, 90, 100, 25);
			p1.add(l1);

			l2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			l2.setBounds(75, 190, 100, 25);
			p1.add(l2);

			l3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			l3.setBounds(75, 290, 100, 25);
			p1.add(l3);

			l4.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			l4.setBounds(75, 390, 100, 25);
			p1.add(l4);

			l5.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			l5.setBounds(75, 490, 100, 25);
			p1.add(l5);

			T1.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			T1.setBounds(200, 90, 200, 25);
			p1.add(T1);

			T2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			T2.setBounds(200, 190, 200, 25);
			p1.add(T2);

			T3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			T3.setBounds(200, 290, 200, 25);
			p1.add(T3);

			T4.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			T4.setBounds(200, 390, 200, 25);
			p1.add(T4);

			T5.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			T5.setBounds(200, 490, 200, 25);
			p1.add(T5);

			T1.setText(sucursales[seleccionar][0] + "");
			T2.setText(sucursales[seleccionar][1] + "");
			T3.setText(sucursales[seleccionar][2] + "");
			T4.setText(sucursales[seleccionar][3] + "");
			T5.setText(sucursales[seleccionar][4] + "");
			T1.setEditable(false);
			B1.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
			B1.setForeground(Color.white);
			B1.setBackground(color1);
			B1.setBounds(180, 540, 150, 50);
			p1.add(B1);

			ActionListener modificarSucursales = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Sucursales objeto = new Sucursales();
					objeto.setCodigo(Integer.parseInt(T1.getText()));
					objeto.setNombre(T2.getText());
					objeto.setDireccion(T3.getText());
					objeto.setTelefonos(Integer.parseInt(T5.getText()));
					objeto.setCorreo(T4.getText());

					SucurusalesDAO sf = new SucurusalesDAO();
					sf.modificar(objeto);

					crearSucursal.setVisible(false);
				}

			};

			// Acción del evento
			B1.addActionListener(modificarSucursales);
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una persona");
		}

	}

	private void generar_pdf() throws FileNotFoundException, DocumentException {

		FileOutputStream gen = new FileOutputStream("Sucursales.pdf");
		Document documento = new Document();

		PdfWriter.getInstance(documento, gen);
		documento.open();

		Paragraph parrafo = new Paragraph("LISTADO DE SUCURSALES");
		parrafo.setAlignment(1);
		documento.add(parrafo);
		documento.add(new Paragraph("\n"));

		for (int i = 0; i < sucursales.length; i++) {

			if (sucursales[i][0] == null) {
				break;
			} else {
				documento.add(new Paragraph(
						"----------------------------------------------------------------------------------------------------------------------------------"));
				documento.add(new Paragraph("|| " + "CODIGO: " + sucursales[i][0] + "  ||  " + "NOMBRE: "
						+ sucursales[i][1] + "  ||  " + "DIRECCION: " + sucursales[i][2] + "  ||  " + "CORREO: "
						+ sucursales[i][3] + "  ||  " + "TELEFONO: " + sucursales[i][4] + "  ||"));
				documento.add(new Paragraph(
						"----------------------------------------------------------------------------------------------------------------------------------"));
				documento.add(new Paragraph("\n\n"));
			}

		}
		documento.close();
		JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
		try {
			File sucursales_doc = new File("Sucursales.pdf");
			Desktop.getDesktop().open(sucursales_doc);
		} catch (Exception e) {
		}
	}

}

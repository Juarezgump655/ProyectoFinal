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
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import modelo.Producto;
import modelo.Sucursales;

public class MenuProductos {

	// botones
	JButton crear = new JButton("Crear");
	JButton actualizar = new JButton("Actualizar");
	JButton eliminnar = new JButton("Eliminar");
	JButton carga = new JButton("Carga");
	JButton pdf = new JButton("Realizar PDF");
	JTable tabla;
	JScrollPane sp;
	// color
	Color color1 = new Color(0x516FFF);

	// Matriz
	Object[][] productos = new Object[200][5];
	
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

		// Acci�n del evento
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

		// Acci�n del evento
		carga.addActionListener(funcion_carga);

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

		// Acci�n del evento
		pdf.addActionListener(generarPdf);

		ActionListener funcion_Eliminar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eliminar();

			}
		};

		// Acci�n del evento
		eliminnar.addActionListener(funcion_Eliminar);

		//
		ActionListener modificarS = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				modificar();

			}
		};

		// Acci�n del evento
		actualizar.addActionListener(modificarS);
	}

	private void table() throws ClassNotFoundException {
		String[] datos = { "Codigo", "Nombre", "Descripcion", "Cantidad", "Precio" };

		// carga
		try {
			ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_productos.dat"));
			productos = (Object[][]) recuperar.readObject();
			recuperar.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al recuperar datos");
		}
		tabla = new JTable(productos, datos);

		sp = new JScrollPane(tabla);
		sp.setBounds(30, 30, 400, 600);

	}

	private void crear() {

		JFrame crearProductos = new JFrame();
		JPanel p1 = new JPanel();
		crearProductos.setLocationRelativeTo(null);
		crearProductos.setUndecorated(true);
		crearProductos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
		crearProductos.setTitle("MENU PRODUCTOS");
		crearProductos.setBackground(Color.WHITE);

		crearProductos.setLayout(null);
		// x y ancho y alto
		crearProductos.setBounds(750, 250, 500, 600);
		crearProductos.setVisible(true);
		crearProductos.setResizable(false);
		crearProductos.add(p1);
		p1.setSize(500, 600);
		p1.setLayout(null);
		p1.setVisible(true);
		p1.setBackground(Color.white);

		JLabel l0 = new JLabel("Crear Producto");
		JLabel l1 = new JLabel("Codigo");
		JLabel l2 = new JLabel("Nombre");
		JLabel l3 = new JLabel("Descripcion");
		JLabel l4 = new JLabel("Cantidad");
		JLabel l5 = new JLabel("Precio");

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
		l3.setBounds(75, 290, 125, 25);
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

		B1.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
		B1.setForeground(Color.white);
		B1.setBackground(color1);
		B1.setBounds(180, 540, 150, 50);
		p1.add(B1);

		ActionListener crearProducto = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				T1.getText().isEmpty();

				if (T1.getText().isEmpty() || T2.getText().isEmpty()  || T3.getText().isEmpty()  || T4.getText().isEmpty() 
						|| T5.getText().isEmpty() ) {

				
						JOptionPane.showMessageDialog(null, "DATOS INCORRECTOS");

					 

				} else {
					Producto s = new Producto(Integer.parseInt(T1.getText()), T2.getText(), T3.getText(),
							Integer.parseInt(T4.getText()), Double.parseDouble(T5.getText()));

					for (int i = 0; i < productos.length; i++) {
						if (productos[i][0] == null) {

							productos[i][0] = s.getCodigo();
							productos[i][1] = s.getNombre();
							productos[i][2] = s.getDescripcion();
							productos[i][3] = s.getCantidad();
							productos[i][4] = s.getPrecio();

							// guardar
							try {
								ObjectOutputStream tabla = new ObjectOutputStream(
										new FileOutputStream("tabla_productos.dat"));
								tabla.writeObject(productos);
								tabla.close();
							} catch (IOException xd) {
							}
							crearProductos.setVisible(false);
							break;

						}
					}

				}

			}
		};

		// Acci�n del evento
		B1.addActionListener(crearProducto);

	}

	private void eliminar() {
		int posicion = tabla.getSelectedRow();

		if (posicion != -1) {

			for (int i = posicion; i < productos.length; i++) {
				if (i == 199) {
					productos[i][0] = null;
					productos[i][1] = null;
					productos[i][2] = null;
					productos[i][3] = null;
					productos[i][4] = null;
					break;
				} else if (productos[i][0] != null) {
					productos[i][0] = productos[i + 1][0];
					productos[i][1] = productos[i + 1][1];
					productos[i][2] = productos[i + 1][2];
					productos[i][3] = productos[i + 1][3];
					productos[i][4] = productos[i + 1][4];
				}

			}

			try {
				ObjectOutputStream tabla = new ObjectOutputStream(new FileOutputStream("tabla_productos.dat"));
				tabla.writeObject(productos);
				tabla.close();
			} catch (IOException s) {
			}

		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una persona");
		}

		tabla.clearSelection();
	}

	private void modificar() {

		int seleccionar = tabla.getSelectedRow();
		if (seleccionar != -1) {

			JFrame crearProductos = new JFrame();
			JPanel p1 = new JPanel();
			crearProductos.setLocationRelativeTo(null);
			crearProductos.setUndecorated(true);
			crearProductos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
			crearProductos.setTitle("MENU PRODUCTOS");
			crearProductos.setBackground(Color.WHITE);

			crearProductos.setLayout(null);
			// x y ancho y alto
			crearProductos.setBounds(750, 250, 500, 600);
			crearProductos.setVisible(true);
			crearProductos.setResizable(false);
			crearProductos.add(p1);
			p1.setSize(500, 600);
			p1.setLayout(null);
			p1.setVisible(true);
			p1.setBackground(Color.white);

			JLabel l0 = new JLabel("Crear Producto");
			JLabel l1 = new JLabel("Codigo");
			JLabel l2 = new JLabel("Nombre");
			JLabel l3 = new JLabel("Descripcion");
			JLabel l4 = new JLabel("Cantidad");
			JLabel l5 = new JLabel("Precio");

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
			l3.setBounds(75, 290, 125, 25);
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

			T1.setText(productos[seleccionar][0] + "");
			T2.setText(productos[seleccionar][1] + "");
			T3.setText(productos[seleccionar][2] + "");
			T4.setText(productos[seleccionar][3] + "");
			T5.setText(productos[seleccionar][4] + "");

			B1.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
			B1.setForeground(Color.white);
			B1.setBackground(color1);
			B1.setBounds(180, 540, 150, 50);
			p1.add(B1);

			ActionListener modificarProductos = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					Producto sucursal = new Producto(Integer.parseInt(T1.getText()), T2.getText(), T3.getText(),
							Integer.parseInt(T4.getText()), Integer.parseInt(T5.getText()));

					productos[seleccionar][0] = sucursal.getCodigo();
					productos[seleccionar][1] = sucursal.getNombre();
					productos[seleccionar][2] = sucursal.getDescripcion();
					productos[seleccionar][3] = sucursal.getCantidad();
					productos[seleccionar][4] = sucursal.getPrecio();

					// cargar
					try {
						ObjectOutputStream tabla = new ObjectOutputStream(new FileOutputStream("tabla_productos.dat"));
						tabla.writeObject(productos);
						tabla.close();
					} catch (IOException s) {
					}

					crearProductos.setVisible(false);
				}

			};

			// Acci�n del evento
			B1.addActionListener(modificarProductos);
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una persona");
		}

	}

	public void ejecutar() {
		botones();
		try {
			table();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}
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

		for (int i = 0; i < productos.length; i++) {
			if (productos[i][0] == null) {
				y = i;
				break;
			}
		}

		x = y;

		for (int i = 0; i < matriz.size(); i++) {

			if (productos[productos.length - 1][0] == null) {
				JsonObject objeto = matriz.get(i).getAsJsonObject();
				Producto p = new Producto(objeto.get("codigo").getAsInt(), objeto.get("nombre").getAsString(),
						objeto.get("descripcion").getAsString(), objeto.get("cantidad").getAsInt(),
						objeto.get("precio").getAsDouble());

				productos[x][0] = p.getCodigo();
				productos[x][1] = p.getNombre();
				productos[x][2] = p.getDescripcion();
				productos[x][3] = p.getCantidad();
				productos[x][4] = p.getPrecio();

				x++;

			} else {
				JOptionPane.showMessageDialog(null, "Tu documento excede tu maximo permitido,por favor modificalo.");
				break;

			}
		}
		try {
			ObjectOutputStream tabla = new ObjectOutputStream(new FileOutputStream("tabla_productos.dat"));
			tabla.writeObject(productos);
			tabla.close();
		} catch (IOException s) {
		}
	}

	private void generar_pdf() throws FileNotFoundException, DocumentException {

		FileOutputStream gen = new FileOutputStream("Productos.pdf");
		Document documento = new Document();

		PdfWriter.getInstance(documento, gen);
		documento.open();

		Paragraph parrafo = new Paragraph("LISTADO DE PRODUCTOS");
		parrafo.setAlignment(1);
		documento.add(parrafo);
		documento.add(new Paragraph("\n"));

		for (int i = 0; i < productos.length; i++) {
			
			if (productos[i][0] == null) {
				break;
			} else {
				documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
				documento.add(new Paragraph("|| "+"CODIGO: " + productos[i][0] + "  ||  " + "NOMBRE: " + productos[i][1] + "  ||  "
						+ "DESCRIPCION: " + productos[i][2] + "  ||  " + "CANTIDAD: " + productos[i][3] + "  ||  " + "PRECIO: "
						+ productos[i][4] + "  ||" ));
				documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
				documento.add(new Paragraph("\n\n"));
			}

		}
		documento.close();
		JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
		try {
			File sucursales_doc = new File("Productos.pdf");
			Desktop.getDesktop().open(sucursales_doc);
		} catch (Exception e) {
		}
	}

	
}

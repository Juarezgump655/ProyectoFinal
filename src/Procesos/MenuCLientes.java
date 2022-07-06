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

import modelo.Cliente;
import modelo.Producto;
import modelo.Sucursales;

public class MenuCLientes {

	// botones
	JButton crear = new JButton("Crear");
	JButton actualizar = new JButton("Actualizar");
	JButton eliminnar = new JButton("Eliminar");
	JButton carga = new JButton("Carga");
	JButton pdf = new JButton("Realizar PDF");
	int suma = 0;
	JTable tabla;
	JScrollPane sp;
	// color
	Color color1 = new Color(0x516FFF);

	// Matriz
	Object[][] clientes = new Object[100][5];
	 
	//cargamos nuestros atributos botones a traves de este netodo
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		};

		// accion
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

	
	//cargamos nuestra tabla a traves de este netodo
	private void table() throws ClassNotFoundException {
		//titulos de la tabla
		String[] datos = { "Codigo", "Nombre", "NIT", "Correo", "Genero" };

		// carga
		try {
			ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_clientes.dat"));
			clientes = (Object[][]) recuperar.readObject();
			recuperar.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al recuperar datos");
		}

		tabla = new JTable(clientes, datos);

		sp = new JScrollPane(tabla);
		sp.setBounds(30, 30, 400, 600);

	}
	//crear cliente
	public void crear() {

		JFrame crearClientes = new JFrame();
		JPanel p1 = new JPanel();
		crearClientes.setLocationRelativeTo(null);
		crearClientes.setUndecorated(true);
		crearClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
		crearClientes.setTitle("MENU Cliente");
		crearClientes.setBackground(Color.WHITE);

		crearClientes.setLayout(null);
		// x y ancho y alto
		crearClientes.setBounds(750, 250, 500, 600);
		crearClientes.setVisible(true);
		crearClientes.setResizable(false);
		crearClientes.add(p1);
		p1.setSize(500, 600);
		p1.setLayout(null);
		p1.setVisible(true);
		p1.setBackground(Color.white);

		JLabel l0 = new JLabel("Crear Cliente");
		JLabel l1 = new JLabel("Codigo");
		JLabel l2 = new JLabel("Nombre");
		JLabel l3 = new JLabel("NIT");
		JLabel l4 = new JLabel("Correo");
		JLabel l5 = new JLabel("Genero");

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

		ActionListener crearCliente = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Cliente s = new Cliente(Integer.parseInt(T1.getText()), T2.getText(), Integer.parseInt(T3.getText()),
						T4.getText(), T5.getText());

				for (int i = 0; i < clientes.length; i++) {
					if (clientes[i][0] == null) {

						clientes[i][0] = s.getCodigo();
						clientes[i][1] = s.getNombre();
						clientes[i][2] = s.getNit();
						clientes[i][3] = s.getCorreo();
						clientes[i][4] = s.getGenero();

						// guardar
						try {
							ObjectOutputStream tabla = new ObjectOutputStream(
									new FileOutputStream("tabla_clientes.dat"));
							tabla.writeObject(clientes);
							tabla.close();
						} catch (IOException xd) {
						}
						crearClientes.setVisible(false);
						break;

					}
				}

			}
		};

		// Acci�n del evento
		B1.addActionListener(crearCliente);

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

		for (int i = 0; i < clientes.length; i++) {
			if (clientes[i][0] == null) {
				y = i;
				break;
			}
		}

		x = y;

		for (int i = 0; i < matriz.size(); i++) {

			if (clientes[clientes.length-1][0] == null) {
				JsonObject objeto = matriz.get(i).getAsJsonObject();
				Cliente p = new Cliente(objeto.get("codigo").getAsInt(), objeto.get("nombre").getAsString(),
						objeto.get("nit").getAsInt(), objeto.get("correo").getAsString(),
						objeto.get("genero").getAsString());

				clientes[x][0] = p.getCodigo();
				clientes[x][1] = p.getNombre();
				clientes[x][2] = p.getNit();
				clientes[x][3] = p.getCorreo();
				clientes[x][4] = p.getGenero();

				x++;

			} else {
				JOptionPane.showMessageDialog(null, "Tu documento excede tu maximo permitido,por favor modificalo.");
				break;

			}
		}
		try {
			ObjectOutputStream tabla = new ObjectOutputStream(new FileOutputStream("tabla_clientes.dat"));
			tabla.writeObject(clientes);
			tabla.close();
		} catch (IOException s) {
		}
	}

	private void generar_pdf() throws FileNotFoundException, DocumentException {

		FileOutputStream gen = new FileOutputStream("Clientes.pdf");
		Document documento = new Document();

		PdfWriter.getInstance(documento, gen);
		documento.open();

		Paragraph parrafo = new Paragraph("LISTADO DE CLIENTES");
		parrafo.setAlignment(1);
		documento.add(parrafo);
		documento.add(new Paragraph("\n"));

		for (int i = 0; i < clientes.length; i++) {

			if (clientes[i][0] == null) {
				break;
			} else {
				documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
				documento.add(new Paragraph("|| "+"CODIGO: " + clientes[i][0] + "  ||  " + "NOMBRE: " + clientes[i][1] + "  ||  "
						+ "NIT: " + clientes[i][2] + "  ||  " + "CORREO: " + clientes[i][3] + "  ||  " + "GENERO: "
						+ clientes[i][4]+ "  ||" ));
				documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
				documento.add(new Paragraph("\n\n"));
			}

		}
		documento.close();
		JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
		try {
			File sucursales_doc = new File("Clientes.pdf");
			Desktop.getDesktop().open(sucursales_doc);
		} catch (Exception e) {
		}
	}
	
	private void eliminar() {
		int posicion = tabla.getSelectedRow();

		if (posicion != -1) {

			for (int i = posicion; i < clientes.length; i++) {
				if (i == 99) {
					clientes[i][0] = null;
					clientes[i][1] = null;
					clientes[i][2] = null;
					clientes[i][3] = null;
					clientes[i][4] = null;
					break;
				} else if (clientes[i][0] != null) {
					clientes[i][0] = clientes[i + 1][0];
					clientes[i][1] = clientes[i + 1][1];
					clientes[i][2] = clientes[i + 1][2];
					clientes[i][3] = clientes[i + 1][3];
					clientes[i][4] = clientes[i + 1][4];
				}

			}

			try {
				ObjectOutputStream tabla = new ObjectOutputStream(new FileOutputStream("tabla_clientes.dat"));
				tabla.writeObject(clientes);
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

			JFrame crearClientes = new JFrame();
			JPanel p1 = new JPanel();
			crearClientes.setLocationRelativeTo(null);
			crearClientes.setUndecorated(true);
			crearClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
			crearClientes.setTitle("MENU Cliente");
			crearClientes.setBackground(Color.WHITE);

			crearClientes.setLayout(null);
			// x y ancho y alto
			crearClientes.setBounds(750, 250, 500, 600);
			crearClientes.setVisible(true);
			crearClientes.setResizable(false);
			crearClientes.add(p1);
			p1.setSize(500, 600);
			p1.setLayout(null);
			p1.setVisible(true);
			p1.setBackground(Color.white);

			JLabel l0 = new JLabel("Crear Cliente");
			JLabel l1 = new JLabel("Codigo");
			JLabel l2 = new JLabel("Nombre");
			JLabel l3 = new JLabel("NIT");
			JLabel l4 = new JLabel("Correo");
			JLabel l5 = new JLabel("Genero");

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
			
			T1.setText(clientes[seleccionar][0] + "");
			T2.setText(clientes[seleccionar][1] + "");
			T3.setText(clientes[seleccionar][2] + "");
			T4.setText(clientes[seleccionar][3] + "");
			T5.setText(clientes[seleccionar][4] + "");

			B1.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
			B1.setForeground(Color.white);
			B1.setBackground(color1);
			B1.setBounds(180, 540, 150, 50);
			p1.add(B1);
			
			ActionListener modificarCliente = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					Cliente cliente = new Cliente(Integer.parseInt(T1.getText()), T2.getText(), Integer.parseInt(T3.getText()),
							T4.getText(), T5.getText());

					clientes[seleccionar][0] = cliente.getCodigo();
					clientes[seleccionar][1] = cliente.getNombre();
					clientes[seleccionar][2] = cliente.getNit();
					clientes[seleccionar][3] = cliente.getCorreo();
					clientes[seleccionar][4] = cliente.getGenero();

					// cargar
					try {
						ObjectOutputStream tabla = new ObjectOutputStream(new FileOutputStream("tabla_clientes.dat"));
						tabla.writeObject(clientes);
						tabla.close();
					} catch (IOException s) {
					}

					crearClientes.setVisible(false);
				}

			};

			// Acci�n del evento
			B1.addActionListener(modificarCliente);
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una persona");
		}
	}
}

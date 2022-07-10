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

import conexiones.ClienteDAO;
import conexiones.ProductosDAO;
import conexiones.SucurusalesDAO;
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
	Object[][] clientes;
	 
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

		// Acción del evento
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

		// Acción del evento
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

		// Acción del evento
		pdf.addActionListener(generarPdf);
		ActionListener funcion_Eliminar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				eliminar();

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
	}

	
	//cargamos nuestra tabla a traves de este netodo
	private void table() throws ClassNotFoundException {

		String[] datos = { "Codigo", "Nombre", "NIT", "Correo", "Genero" };

	
		ClienteDAO sf = new ClienteDAO();
		clientes = sf.listar();
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
		ActionListener crearCliente = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ClienteDAO sf = new ClienteDAO();
			    sf.crear(T2.getText(), Integer.parseInt(T3.getText()),
						T4.getText(), T5.getText());
			    JOptionPane.showMessageDialog(null, "Se ha agregado Correctamente");
				
				crearClientes.setVisible(false);
			}
		};

		// Acción del evento
		B1.addActionListener(crearCliente);

		ActionListener cancelar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				crearClientes.setVisible(false);
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
			ClienteDAO sd = new ClienteDAO();
			sd.crear(objeto.get("nombre").getAsString(), objeto.get("nit").getAsInt(), 
					objeto.get("correo").getAsString(), objeto.get("genero").getAsString());
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
			ClienteDAO sd = new ClienteDAO();
			sd.eliminar(Integer.parseInt(clientes[posicion][0].toString()));
			

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
			T1.setEditable(false);
			B1.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
			B1.setForeground(Color.white);
			B1.setBackground(color1);
			B1.setBounds(180, 540, 150, 50);
			p1.add(B1);
			
			ActionListener modificarCliente = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Cliente objeto = new Cliente();
					objeto.setCodigo(Integer.parseInt(T1.getText()));
					objeto.setNombre(T2.getText());
					objeto.setNit(Integer.parseInt(T3.getText()));
					objeto.setCorreo(T4.getText());
					objeto.setGenero(T5.getText());
					
					ClienteDAO sf = new ClienteDAO();
					sf.modificar(objeto);
				

					crearClientes.setVisible(false);
				}

			};

			// Acción del evento
			B1.addActionListener(modificarCliente);
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una persona");
		}
	}
}

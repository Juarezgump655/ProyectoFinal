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
import javax.swing.JPasswordField;
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

import modelo.Sucursales;
import modelo.Vendedor;

public class MenuVendedores {

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
	Object[][] Vendedores = new Object[400][6];
 

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

		//Acci�n del evento
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
		String[] datos = { "Codigo", "Nombre", "Caja", "Ventas", "Genero" };

		// carga
		try {
			ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_Vendedores.dat"));
			Vendedores = (Object[][]) recuperar.readObject();
			recuperar.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error al recuperar datos");
		}

		tabla = new JTable(Vendedores, datos);

		sp = new JScrollPane(tabla);
		sp.setBounds(30, 30, 400, 600);

	}

	private void crear() {

		JFrame crearVendedores = new JFrame();
		JPanel p1 = new JPanel();
		crearVendedores.setLocationRelativeTo(null);
		crearVendedores.setUndecorated(true);
		crearVendedores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
		crearVendedores.setTitle("MENU VENDEDORES");
		crearVendedores.setBackground(Color.WHITE);

		crearVendedores.setLayout(null);
		// x y ancho y alto
		crearVendedores.setBounds(750, 150, 500, 725);
		crearVendedores.setVisible(true);
		crearVendedores.setResizable(false);
		crearVendedores.add(p1);
		p1.setSize(500, 725);
		p1.setLayout(null);
		p1.setVisible(true);
		p1.setBackground(Color.white);

		JLabel l0 = new JLabel("Crear Vendedor");
		JLabel l1 = new JLabel("Codigo");
		JLabel l2 = new JLabel("Nombre");
		JLabel l3 = new JLabel("Caja");
		JLabel l4 = new JLabel("Ventas");
		JLabel l5= new JLabel("Genero");
		JLabel l6 = new JLabel("Password");
		
		JTextField T1 = new JTextField();
		JTextField T2 = new JTextField();
		JTextField T3 = new JTextField();
		JTextField T4 = new JTextField();
		JTextField T5 = new JTextField();
		JPasswordField T6 = new JPasswordField();
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
		
		l6.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		l6.setBounds(75, 590, 100, 25);
		p1.add(l6);

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

		T6.setFont(new Font("Roboto Light", Font.PLAIN, 22));
		T6.setBounds(200, 590, 200, 25);
		p1.add(T6);
		
		B1.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
		B1.setForeground(Color.white);
		B1.setBackground(color1);
		B1.setBounds(180, 640, 150, 50);
		p1.add(B1);

		ActionListener crearVendedor = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Vendedor s = new Vendedor(Integer.parseInt(T1.getText()), T2.getText(), Integer.parseInt(T3.getText()),
						Integer.parseInt(T4.getText()), T5.getText(), T6.getText());
				
					for (int i = 0; i < Vendedores.length; i++) {
					if (Vendedores[i][0] == null) {

						Vendedores[i][0] = s.getCodigo();
						Vendedores[i][1] = s.getNombre();
						Vendedores[i][2] = s.getCaja();
						Vendedores[i][3] = s.getVentas();
						Vendedores[i][4] = s.getGenero();
						Vendedores[i][5] = s.getPaswword();
						// guardar
						try {
							ObjectOutputStream tabla = new ObjectOutputStream(
									new FileOutputStream("tabla_Vendedores.dat"));
							tabla.writeObject(Vendedores);
							tabla.close();
						} catch (IOException xd) {
						}
						crearVendedores.setVisible(false);
						break;

					}
				}

			}
		};

		// Acci�n del evento
		B1.addActionListener(crearVendedor);
		
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

		for (int i = 0; i < Vendedores.length; i++) {
			if (Vendedores[i][0] == null) {
				y = i;
				break;
			}
		}

		x = y;

		for (int i = 0; i < matriz.size(); i++) {

			if (Vendedores[Vendedores.length - 1][0] == null) {
				JsonObject objeto = matriz.get(i).getAsJsonObject();
				Vendedor p = new Vendedor(objeto.get("codigo").getAsInt(), objeto.get("nombre").getAsString(),
						objeto.get("caja").getAsInt(), objeto.get("ventas").getAsInt(),
						objeto.get("genero").getAsString(),objeto.get("password").getAsString());

				Vendedores[x][0] = p.getCodigo();
				Vendedores[x][1] = p.getNombre();
				Vendedores[x][2] = p.getCaja();
				Vendedores[x][3] = p.getVentas();
				Vendedores[x][4] = p.getGenero();
				Vendedores[x][5] = p.getPaswword();
				x++;

			} else {
				JOptionPane.showMessageDialog(null, "Tu documento excede tu maximo permitido,por favor modificalo.");
				break;

			}
		}
		try {
			ObjectOutputStream tabla = new ObjectOutputStream(new FileOutputStream("tabla_Vendedores.dat"));
			tabla.writeObject(Vendedores);
			tabla.close();
		} catch (IOException s) {
		}
	}
	private void generar_pdf() throws FileNotFoundException, DocumentException {

		FileOutputStream gen = new FileOutputStream("Vendedores.pdf");
		Document documento = new Document();

		PdfWriter.getInstance(documento, gen);
		documento.open();

		Paragraph parrafo = new Paragraph("LISTADO DE VENDEDORES");
		parrafo.setAlignment(1);
		documento.add(parrafo);
		documento.add(new Paragraph("\n"));

		for (int i = 0; i < Vendedores.length; i++) {

			if (Vendedores[i][0] == null) {
				break;
			} else {
				documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
				documento.add(new Paragraph("|| "+"CODIGO: " + Vendedores[i][0] + "  ||  " + "NOMBRE: " + Vendedores[i][1] + "  ||  "
						+ "CAJA: " + Vendedores[i][2] + "  ||  " + "VENTAS: " + Vendedores[i][3] + "  ||  " + "GENERO: "
						+ Vendedores[i][4] +"  ||  " + "PASSWORD: "+ Vendedores[i][5] + "  ||"));
				documento.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
				documento.add(new Paragraph("\n\n"));
			}

		}
		documento.close();
		JOptionPane.showMessageDialog(null, "El archivo se creo correctamente");
		try {
			File sucursales_doc = new File("Vendedores.pdf");
			Desktop.getDesktop().open(sucursales_doc);
		} catch (Exception e) {
		}
	}
	
	
	private void eliminar() {
		int posicion = tabla.getSelectedRow();

		if (posicion != -1) {

			for (int i = posicion; i < Vendedores.length; i++) {
				if (i == 399) {
					Vendedores[i][0] = null;
					Vendedores[i][1] = null;
					Vendedores[i][2] = null;
					Vendedores[i][3] = null;
					Vendedores[i][4] = null;
					Vendedores[i][5] = null;
					break;
				} else if (Vendedores[i][0] != null) {
					Vendedores[i][0] = Vendedores[i + 1][0];
					Vendedores[i][1] = Vendedores[i + 1][1];
					Vendedores[i][2] = Vendedores[i + 1][2];
					Vendedores[i][3] = Vendedores[i + 1][3];
					Vendedores[i][4] = Vendedores[i + 1][4];
					Vendedores[i][5] = Vendedores[i + 1][5];
				}

			}

			try {
				ObjectOutputStream tabla = new ObjectOutputStream(new FileOutputStream("tabla_Vendedores.dat"));
				tabla.writeObject(Vendedores);
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

			JFrame crearVendedores = new JFrame();
			JPanel p1 = new JPanel();
			crearVendedores.setLocationRelativeTo(null);
			crearVendedores.setUndecorated(true);
			crearVendedores.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
			crearVendedores.setTitle("MENU VENDEDORES");
			crearVendedores.setBackground(Color.WHITE);

			crearVendedores.setLayout(null);
			// x y ancho y alto
			crearVendedores.setBounds(750, 150, 500, 725);
			crearVendedores.setVisible(true);
			crearVendedores.setResizable(false);
			crearVendedores.add(p1);
			p1.setSize(500, 725);
			p1.setLayout(null);
			p1.setVisible(true);
			p1.setBackground(Color.white);

			JLabel l0 = new JLabel("Crear Vendedor");
			JLabel l1 = new JLabel("Codigo");
			JLabel l2 = new JLabel("Nombre");
			JLabel l3 = new JLabel("Caja");
			JLabel l4 = new JLabel("Ventas");
			JLabel l5= new JLabel("Genero");
			JLabel l6 = new JLabel("Password");
			
			JTextField T1 = new JTextField();
			JTextField T2 = new JTextField();
			JTextField T3 = new JTextField();
			JTextField T4 = new JTextField();
			JTextField T5 = new JTextField();
			JPasswordField T6 = new JPasswordField();
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
			
			l6.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			l6.setBounds(75, 590, 100, 25);
			p1.add(l6);

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

			T6.setFont(new Font("Roboto Light", Font.PLAIN, 22));
			T6.setBounds(200, 590, 200, 25);
			p1.add(T6);
			
			T1.setText(Vendedores[seleccionar][0] + "");
			T2.setText(Vendedores[seleccionar][1] + "");
			T3.setText(Vendedores[seleccionar][2] + "");
			T4.setText(Vendedores[seleccionar][3] + "");
			T5.setText(Vendedores[seleccionar][4] + "");
			T6.setText(Vendedores[seleccionar][5] + "");
			
			B1.setFont(new Font("Roboto Medium", Font.PLAIN, 22));
			B1.setForeground(Color.white);
			B1.setBackground(color1);
			B1.setBounds(180, 650, 150, 50);
			B1.setVisible(true);
			p1.add(B1);
			
			ActionListener modificarVendedor = new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {

					Vendedor vendedor = new Vendedor(Integer.parseInt(T1.getText()), T2.getText(), Integer.parseInt(T3.getText()),
							Integer.parseInt(T4.getText()), T5.getText(), T6.getText());

					Vendedores[seleccionar][0] = vendedor.getCodigo();
					Vendedores[seleccionar][1] = vendedor.getNombre();
					Vendedores[seleccionar][2] = vendedor.getCaja();
					Vendedores[seleccionar][3] = vendedor.getVentas();
					Vendedores[seleccionar][4] = vendedor.getGenero();
					Vendedores[seleccionar][5] = vendedor.getPaswword();
					
					// cargar
					try {
						ObjectOutputStream tabla = new ObjectOutputStream(new FileOutputStream("tabla_Vendedores.dat"));
						tabla.writeObject(Vendedores);
						tabla.close();
					} catch (IOException s) {
					}

					crearVendedores.setVisible(false);
				}

			};

			// Acci�n del evento
			B1.addActionListener(modificarVendedor);
			
			
		} else {
			JOptionPane.showMessageDialog(null, "Debe seleccionar una persona");
		}
	}
}

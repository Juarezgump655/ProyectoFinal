package Procesos;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import modelo.Cliente;

public class LoginVendedores {

    private static final String String = null;
    JFrame Principal = new JFrame();
    JTabbedPane pestañas = new JTabbedPane();
    Color color1 = new Color(0x516FFF);
    JPanel nuevaVenta = new JPanel();
    JPanel selecionarCliente = new JPanel();
    JPanel agregarProducto = new JPanel();
    JPanel listadoGeneral = new JPanel();
    JPanel ventas = new JPanel();
    JTable tabla;
    JScrollPane sp;

    JTextField T1 = new JTextField();
    JTextField T2 = new JTextField();
    JTextField T3 = new JTextField();
    JTextField T4 = new JTextField();
    JTextField T5 = new JTextField();


    String[] resultados = new String[100];
    JComboBox Cl = new JComboBox(resultados);
    JTable tablaN;
    JScrollPane spN;
    int total = 0;
    int no = 0;
    private String nombre;
    Object[][] venta = new Object[1000][6];
    Object[][] productos = new Object[10][6];
    Object[][] listaproductos = new Object[10][6];
    Object[][] clientes = new Object[100][5];

    public static void main(String[] args) throws ClassNotFoundException {
        LoginVendedores lv = new LoginVendedores();
        lv.ejecutar();
    }

    private void frame() throws ClassNotFoundException {
        // iniciamos nuestros atributos
        Principal.setLocationRelativeTo(null);
        Principal.setUndecorated(true);
        Principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // le quitamos la barra de arriba al programa
        Principal.setTitle("MODULO DE VENDEDORES");
        Principal.setBackground(Color.WHITE);

        nuevaVenta.setLayout(null);
        ventas.setLayout(null);
        selecionarCliente.setLayout(null);
        agregarProducto.setLayout(null);
        listadoGeneral.setLayout(null);
        Principal.setBounds(500, 200, 1000, 700);
        Principal.setVisible(true);
        Principal.setResizable(false);

        JButton cerrarProgra = new JButton("X");
        cerrarProgra.setBounds(950, 0, 50, 50);
        cerrarProgra.setForeground(Color.BLACK);
        cerrarProgra.setBackground(Color.WHITE);
        cerrarProgra.setFont(new Font("Roboto black", Font.PLAIN, 22));
        Principal.add(cerrarProgra);
        ActionListener funcionCerrarapp = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        };

        // Accion del evento
        cerrarProgra.addActionListener(funcionCerrarapp);

        JLabel label1 = new JLabel();
        label1.setText("!Bienvenido " + nombre + "!");
        label1.setForeground(Color.white);
        label1.setFont(new Font("Roboto light", Font.PLAIN, 22));
        label1.setBounds(725, 40, 250, 25);

        JButton cerrarSesion = new JButton("Cerrar Sesion");
        cerrarSesion.setBounds(825, 650, 150, 40);
        cerrarSesion.setForeground(Color.white);
        cerrarSesion.setFont(new Font("Roboto light", Font.PLAIN, 16));
        cerrarSesion.setBackground(color1);
        cerrarSesion.setBorder(null);
        Principal.add(label1);
        Principal.add(cerrarSesion);
        Principal.add(pestañas);

        // funcion que nos regresa al login al dar en el boton cerrar Sesion
        ActionListener funcionCerrar = new ActionListener() {

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

        // Acci�n del evento
        cerrarSesion.addActionListener(funcionCerrar);

        // configuracion para a�adir los pane de Nueva Venta
        nuevaVenta.setBackground(color1);
        selecionarCliente.setBounds(50, 50, 900, 250);
        selecionarCliente.setBackground(Color.WHITE);
        agregarProducto.setBounds(50, 325, 900, 300);
        agregarProducto.setBackground(Color.WHITE);
        nuevaVenta.add(agregarProducto);
        nuevaVenta.add(selecionarCliente);

        // configuracion para a�adir los pane Ventas
        listadoGeneral.setBounds(50, 50, 900, 575);
        listadoGeneral.setBackground(Color.WHITE);
        ventas.setBackground(color1);
        ventas.add(listadoGeneral);

        // a�adir pesta�as
        pestañas.add("Nueva Venta", nuevaVenta);
        pestañas.add("Ventas", ventas);

        // carga de nuestros productos
        try {
            ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_productos.dat"));
            productos = (Object[][]) recuperar.readObject();
            recuperar.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar datos");
        }

        // carga
        try {
            ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_clientes.dat"));
            clientes = (Object[][]) recuperar.readObject();
            recuperar.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar datos");
        }

    }

    private void panelVentas() {

        // Panel de Ventas
        JTextField TNo = new JTextField();
        JTextField Textnombre = new JTextField();
        JTextField TextNit = new JTextField();
        JTextField Fecha = new JTextField();

        JLabel LNo = new JLabel("No. Factura");
        JLabel Lnombre = new JLabel("Nombre");
        JLabel LNit = new JLabel("NIT");
        JLabel LF = new JLabel("Fecha");
        JLabel l5 = new JLabel("  Listado General");
        JLabel l4 = new JLabel("Filtrado por:");
        JLabel l6 = new JLabel("Filtrados:");

        TNo.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        TNo.setBounds(250, 40, 200, 25);
        listadoGeneral.add(TNo);

        Textnombre.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        Textnombre.setBounds(250, 80, 200, 25);
        listadoGeneral.add(Textnombre);

        TextNit.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        TextNit.setBounds(575, 40, 200, 25);
        listadoGeneral.add(TextNit);

        Fecha.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        Fecha.setBounds(575, 80, 200, 25);
        listadoGeneral.add(Fecha);

        // label
        LNo.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        LNo.setBounds(125, 40, 150, 25);
        listadoGeneral.add(LNo);

        Lnombre.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        Lnombre.setBounds(150, 80, 100, 25);
        listadoGeneral.add(Lnombre);

        LNit.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        LNit.setBounds(525, 40, 100, 25);
        listadoGeneral.add(LNit);

        LF.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        LF.setBounds(510, 80, 100, 25);
        listadoGeneral.add(LF);

        l5.setFont(new Font("Roboto mediun", Font.PLAIN, 18));
        l5.setBounds(1, 1, 150, 25);
        l5.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        listadoGeneral.add(l5);

        l4.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        l4.setBounds(20, 40, 100, 25);
        listadoGeneral.add(l4);

        Font font = l4.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        l4.setFont(font.deriveFont(attributes));

        l6.setFont(new Font("Roboto light", Font.PLAIN, 18));
        l6.setBounds(20, 175, 100, 25);
        listadoGeneral.add(l6);

        Font font1 = l6.getFont();
        Map<TextAttribute, Object> attributes1 = new HashMap<>(font.getAttributes());
        attributes1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        l6.setFont(font.deriveFont(attributes1));

        // botones
        JButton B1 = new JButton("Aplicar filtro");
        B1.setBounds(260, 130, 500, 25);
        B1.setForeground(Color.white);
        B1.setBackground(color1);
        B1.setFont(new Font("Roboto light", Font.PLAIN, 22));
        listadoGeneral.add(B1);

    }

    public void ejecutar() throws ClassNotFoundException {
        panelNueva();
        panelVentas();
        try {
            tableNueva();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        frame();
        try {
            table();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void panelNueva() {
        Calendar calendar = new GregorianCalendar();
        String fecha = "" + calendar.get(Calendar.DAY_OF_MONTH) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-"
                + calendar.get(Calendar.YEAR);

        JButton B1 = new JButton("Aplicar filtro");
        JButton B2 = new JButton("Nuevo Cliente");
        JButton B3 = new JButton("Agregar");

        JLabel l0 = new JLabel("Nombre");
        JLabel l1 = new JLabel("Correo");
        JLabel l2 = new JLabel("NIT");
        JLabel l3 = new JLabel("Genero");
        JLabel l4 = new JLabel("Filtrado por:");
        JLabel l5 = new JLabel("  Seleccionar Cliente");
        JLabel l6 = new JLabel("Filtrados:");
        JLabel l7 = new JLabel("Cliente:");
        JLabel labelFecha = new JLabel("Fecha: " + fecha);

        JLabel No = new JLabel("No. " + no);

        // text field
        T1.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        T1.setBounds(245, 50, 200, 25);
        selecionarCliente.add(T1);

        T2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        T2.setBounds(245, 90, 200, 25);
        selecionarCliente.add(T2);

        T3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        T3.setBounds(595, 50, 200, 25);
        selecionarCliente.add(T3);

        T4.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        T4.setBounds(595, 90, 200, 25);
        selecionarCliente.add(T4);

        // label
        l0.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        l0.setBounds(155, 50, 100, 25);
        selecionarCliente.add(l0);

        l1.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        l1.setBounds(155, 90, 100, 25);
        selecionarCliente.add(l1);

        l2.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        l2.setBounds(535, 50, 100, 25);
        selecionarCliente.add(l2);

        l3.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        l3.setBounds(515, 90, 100, 25);
        selecionarCliente.add(l3);

        l4.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        l4.setBounds(20, 50, 100, 25);
        selecionarCliente.add(l4);

        Font font = l4.getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        l4.setFont(font.deriveFont(attributes));

        l5.setFont(new Font("Roboto mediun", Font.PLAIN, 18));
        l5.setBounds(1, 1, 200, 25);
        l5.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        selecionarCliente.add(l5);

        l6.setFont(new Font("Roboto light", Font.PLAIN, 18));
        l6.setBounds(20, 175, 200, 25);
        selecionarCliente.add(l6);

        Cl.setFont(new Font("Roboto light", Font.PLAIN, 18));
        Cl.setBounds(235, 175, 200, 25);
        selecionarCliente.add(Cl);

        Font font1 = l6.getFont();
        Map<TextAttribute, Object> attributes1 = new HashMap<>(font.getAttributes());
        attributes1.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        l6.setFont(font.deriveFont(attributes1));

        l7.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        l7.setBounds(155, 175, 100, 25);
        selecionarCliente.add(l7);

        B1.setBounds(300, 130, 400, 25);
        B1.setForeground(Color.white);
        B1.setBackground(color1);
        B1.setFont(new Font("Roboto light", Font.PLAIN, 22));
        selecionarCliente.add(B1);

        B2.setBounds(595, 175, 200, 25);
        B2.setForeground(Color.white);
        B2.setBackground(color1);
        B2.setFont(new Font("Roboto light", Font.PLAIN, 18));
        selecionarCliente.add(B2);

        // Panel agregar productos
        JLabel l8 = new JLabel("    Agregar Productos");
        JLabel l9 = new JLabel("Codigo");
        JLabel l10 = new JLabel("Cantidad");
        JLabel LT = new JLabel("Total: ");

        T5.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        T5.setBounds(670, 250, 200, 25);
        T5.setEditable(false);
        T5.setText(total + " ");
        agregarProducto.add(T5);

        JTextField T6 = new JTextField();
        JTextField T7 = new JTextField();
        JButton B4 = new JButton("VENDER");

        T6.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        T6.setBounds(150, 50, 200, 25);
        agregarProducto.add(T6);

        T7.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        T7.setBounds(500, 50, 200, 25);
        agregarProducto.add(T7);

        l8.setFont(new Font("Roboto mediun", Font.PLAIN, 18));
        l8.setBounds(1, 1, 200, 25);
        l8.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        agregarProducto.add(l8);

        l9.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        l9.setBounds(75, 50, 200, 25);
        agregarProducto.add(l9);

        l10.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        l10.setBounds(410, 50, 200, 25);
        agregarProducto.add(l10);

        LT.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        LT.setBounds(600, 250, 200, 25);
        agregarProducto.add(LT);

        labelFecha.setFont(new Font("Roboto Light", Font.PLAIN, 18));
        labelFecha.setBounds(410, 5, 300, 25);
        agregarProducto.add(labelFecha);

        No.setFont(new Font("Roboto Light", Font.PLAIN, 22));
        No.setBounds(750, 5, 200, 25);
        agregarProducto.add(No);

        B3.setBounds(730, 50, 150, 25);
        B3.setForeground(Color.white);
        B3.setBackground(color1);
        B3.setFont(new Font("Roboto light", Font.PLAIN, 18));
        agregarProducto.add(B3);

        B4.setBounds(75, 250, 400, 25);
        B4.setForeground(Color.white);
        B4.setBackground(color1);
        B4.setFont(new Font("Roboto light", Font.PLAIN, 18));
        agregarProducto.add(B4);

        ActionListener funcionAgregar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        // Acci�n del evento
        B3.addActionListener(funcionAgregar);

        ActionListener funcionCrear = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        // Acci�n del evento
        B2.addActionListener(funcionCrear);

        ActionListener ingresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                crear();
            }
        };
        B2.addActionListener(ingresar);

        ActionListener filtrar = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    filtro();
                } catch (ClassNotFoundException e1) {

                    e1.printStackTrace();
                }
            }

        };

        B1.addActionListener(filtrar);
    }

    private void table() throws ClassNotFoundException {
        String[] datos = {"No.Factura", "NIT", "Nombre", "Fecha", "Total", "Acciones"};

        // carga
        try {
            ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_Ventas.dat"));
            venta = (Object[][]) recuperar.readObject();
            recuperar.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al recuperar datos");
        }

        tabla = new JTable(venta, datos);

        sp = new JScrollPane(tabla);
        sp.setBounds(30, 200, 850, 375);

        listadoGeneral.add(sp);

    }

    private void tableNueva() throws ClassNotFoundException {

        String[] datos = {"Codigo", "Nombre", "Cantidad", "Precio", "Subtotal"};

        tablaN = new JTable(listaproductos, datos);

        spN = new JScrollPane(tablaN);
        spN.setBounds(60, 90, 800, 150);

        agregarProducto.add(spN);

    }

    // metodo para obtener el nombre del vendedor
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    private void agregarProducto(String codigo, String cantidad) {

    }

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

    private void filtro() throws ClassNotFoundException {
        int x = 0;

        if (T1.getText().isEmpty() != false) {
            if (T2.getText().isEmpty() != false) {
                if (T3.getText().isEmpty() != false) {
                    if (T4.getText().isEmpty() != false) {
                        JOptionPane.showMessageDialog(null, "Por favor llena un filtro");

                    } else {
                        // aplicamos filtro de Genero
                        x = 4;
                    }
                } else {
                    // aplicamos filtro de NIT
                    x = 3;
                }
            } else {
                // aplicamos filtro de correo
                x = 2;
            }

        } else {
            // aplicamos filtro de nombre
            x = 1;
        }

        switch (x) {
            case 1:
                filtro_nombre(T1.getText());
                break;
            case 2:
                filtroCorreo(T2.getText());

                break;
            case 3:
                filtronit(Integer.parseInt(T3.getText()));
                break;
            case 4:
                filtrogenero(T4.getText());
                break;
            default:
                break;

        }
    }

    private void filtro_nombre(String nombre) throws ClassNotFoundException {
        vacia();
        // carga
        try {
            ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_clientes.dat"));
            clientes = (Object[][]) recuperar.readObject();
            recuperar.close();
        } catch (IOException e) {
        }
        int contador = 0;
        boolean respuesta = false;
        for (int i = 0; i < clientes.length; i++) {
            if (nombre.equals(clientes[i][1])) {
                resultados[contador] = nombre;
                contador++;
                respuesta = true;
            }

        }
        if (respuesta == true) {
            Cl.setVisible(false);
            Cl = new JComboBox(resultados);
            Cl.setFont(new Font("Roboto light", Font.PLAIN, 18));
            Cl.setBounds(235, 175, 200, 25);
            selecionarCliente.add(Cl);

        } else {
            if (T2.getText().isEmpty() != false) {
                if (T3.getText().isEmpty() != false) {
                    if (T4.getText().isEmpty() != false) {
                        JOptionPane.showMessageDialog(null, "Por favor llena un filtro");

                    } else {
                        filtrogenero(T4.getText());
                    }
                } else {
                    filtronit(Integer.parseInt(T3.getText()));
                }
            } else {
                filtroCorreo(T2.getText());
            }

        }

    }

    private void filtroCorreo(String correo) throws ClassNotFoundException {
        vacia();
        // carga
        try {
            ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_clientes.dat"));
            clientes = (Object[][]) recuperar.readObject();
            recuperar.close();
        } catch (IOException e) {
        }
        int contador = 0;
        boolean respuesta = false;
        for (int i = 0; i < clientes.length; i++) {
            if (correo.equals(clientes[i][3])) {
                resultados[contador] = clientes[i][1] + " ";
                contador++;
                respuesta = true;
            }

        }
        if (respuesta == true) {
            Cl.setVisible(false);
            Cl = new JComboBox(resultados);
            Cl.setFont(new Font("Roboto light", Font.PLAIN, 18));
            Cl.setBounds(235, 175, 200, 25);
            selecionarCliente.add(Cl);

        } else {

            if (T3.getText().isEmpty() != false) {
                if (T4.getText().isEmpty() != false) {
                    JOptionPane.showMessageDialog(null, "Por favor llena un filtro");

                } else {
                    filtrogenero(T4.getText());
                }
            } else {
                filtronit(Integer.parseInt(T3.getText()));
            }

        }

    }

    private void filtronit(int nit) throws ClassNotFoundException {
        vacia();
        // carga
        try {
            ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_clientes.dat"));
            clientes = (Object[][]) recuperar.readObject();
            recuperar.close();
        } catch (IOException e) {
        }
        int contador = 0;
        boolean respuesta = false;
        for (int i = 0; i < clientes.length; i++) {
            if ((nit + "").equals(clientes[i][2] + "")) {
                resultados[contador] = clientes[i][1] + " ";
                contador++;
                respuesta = true;
            }
        }
        if (respuesta == true) {
            Cl.setVisible(false);
            Cl = new JComboBox(resultados);
            Cl.setFont(new Font("Roboto light", Font.PLAIN, 18));
            Cl.setBounds(235, 175, 200, 25);
            selecionarCliente.add(Cl);

        } else {

            if (T4.getText().isEmpty() != false) {
                JOptionPane.showMessageDialog(null, "Por favor llena un filtro");

            } else {
                filtrogenero(T4.getText());
            }

        }

    }

    private void filtrogenero(String genero) throws ClassNotFoundException {
        vacia();
        // carga
        try {
            ObjectInputStream recuperar = new ObjectInputStream(new FileInputStream("tabla_clientes.dat"));
            clientes = (Object[][]) recuperar.readObject();
            recuperar.close();
        } catch (IOException e) {
        }
        int contador = 0;
        boolean respuesta = false;
        for (int i = 0; i < clientes.length; i++) {
            if (genero.equals(clientes[i][4])) {
                resultados[contador] = clientes[i][1] + " ";
                contador++;
                respuesta = true;
            }

        }
        if (respuesta == true) {
            Cl.setVisible(false);
            Cl = new JComboBox(resultados);
            Cl.setFont(new Font("Roboto light", Font.PLAIN, 18));
            Cl.setBounds(235, 175, 200, 25);
            selecionarCliente.add(Cl);

        } else {

            JOptionPane.showMessageDialog(null, "Por favor llena un filtro");

        }

    }

    private void vacia() {
        for (int i = 0; i < clientes.length; i++) {
            resultados[i] = "";
        }
    }
}

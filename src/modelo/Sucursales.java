package modelo;

public class Sucursales {
	private int codigo;
	private String nombre, direccion, correo;
	private int telefonos;
	
	public Sucursales(int codigo, String nombre, String direccion, String correo, int telefonos) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.direccion = direccion;
		this.correo = correo;
		this.telefonos = telefonos;
	}
    public Sucursales() {
        
    }

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getTelefonos() {
		return telefonos;
	}

	public void setTelefonos(int telefonos) {
		this.telefonos = telefonos;
	}

	
	
}

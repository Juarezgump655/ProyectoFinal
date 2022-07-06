package modelo;

public class Cliente {
	private int codigo;
	private String nombre;
	private int nit;
	private String correo, genero;
	
	public Cliente(int codigo, String nombre, int nit, String correo, String genero) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.nit = nit;
		this.correo = correo;
		this.genero = genero;
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

	public int getNit() {
		return nit;
	}

	public void setNit(int nit) {
		this.nit = nit;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	
	
	
}

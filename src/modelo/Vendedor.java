package modelo;

public class Vendedor {
	private int codigo;
	private String nombre;
	private int caja, ventas;
	private String genero, paswword;
	
	public Vendedor(int codigo, String nombre, int caja, int ventas, String genero, String paswword) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.caja = caja;
		this.ventas = ventas;
		this.genero = genero;
		this.paswword = paswword;
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

	public int getCaja() {
		return caja;
	}

	public void setCaja(int caja) {
		this.caja = caja;
	}

	public int getVentas() {
		return ventas;
	}

	public void setVentas(int ventas) {
		this.ventas = ventas;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPaswword() {
		return paswword;
	}

	public void setPaswword(String paswword) {
		this.paswword = paswword;
	}
	
	
	
	
}

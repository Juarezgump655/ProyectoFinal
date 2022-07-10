package modelo;

public class Ventas {
	private int NoFactura, NIT ;
	private String nombre, fecha;
	private int total;
	
	
	
	public Ventas() {
	}
	public int getNoFactura() {
		return NoFactura;
	}
	public void setNoFactura(int noFactura) {
		NoFactura = noFactura;
	}
	public int getNIT() {
		return NIT;
	}
	public void setNIT(int nIT) {
		NIT = nIT;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
}

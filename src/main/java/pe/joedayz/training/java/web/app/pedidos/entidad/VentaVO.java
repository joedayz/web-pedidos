package pe.joedayz.training.java.web.app.pedidos.entidad;

import java.io.Serializable;

public class VentaVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String cliente;
	private double total;
	
	public VentaVO() {
		
	}
	

	public VentaVO(String cliente, double total) {
		super();
		this.cliente = cliente;
		this.total = total;
	}



	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	
	
}

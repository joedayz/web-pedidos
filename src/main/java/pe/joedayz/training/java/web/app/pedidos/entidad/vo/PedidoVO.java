package pe.joedayz.training.java.web.app.pedidos.entidad.vo;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="VIE_PEDIDO")
public class PedidoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PEDIDO")
	private long idPedido;

	@Column(name="CLIENTE")
	private String cliente;

	@Column(name="FECHA_ATENCION")
	private String fechaAtencion;

	@Column(name="TOTAL")
	private double total;

	public PedidoVO() {
	}

	public String getCliente() {
		return this.cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getFechaAtencion() {
		return this.fechaAtencion;
	}

	public void setFechaAtencion(String fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public long getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public double getTotal() {
		return this.total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
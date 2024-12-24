package pe.joedayz.training.java.web.app.pedidos.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


@NamedStoredProcedureQueries(
		{
				@NamedStoredProcedureQuery(
					name="cliente.buscarXRuc", 
					procedureName="PKG_CLIENTE.SP_BUSCAR_X_RUC",
					resultClasses= Cliente.class,
					parameters={
								@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_C_CURSOR", type=void.class ),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_RUC", type=String.class)
						}					
				)
		}
		)
@Entity
public class Cliente extends GenericEntidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_CLIENTE")
	private long idCliente;

	@Column(name="RUC")
	private String ruc;
	
	@Column(name="RAZON_SOCIAL")
	private String razonSocial;

	@Column(name="DIRECCION")
	private String direccion;


	//bi-directional many-to-one association to Pedido
	
	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos;

	public Cliente() {
	}

	public long getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}


	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getRazonSocial() {
		return this.razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRuc() {
		return this.ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public List<Pedido> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido addPedido(Pedido pedido) {
		getPedidos().add(pedido);
		pedido.setCliente(this);

		return pedido;
	}

	public Pedido removePedido(Pedido pedido) {
		getPedidos().remove(pedido);
		pedido.setCliente(null);

		return pedido;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", direccion=" + direccion
				+ ", razonSocial=" + razonSocial
				+ ", ruc=" + ruc + ", pedidos=" + pedidos + "]";
	}
	
	

}
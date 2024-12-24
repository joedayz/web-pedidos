package pe.joedayz.training.java.web.app.pedidos.entidad;

import java.io.Serializable;

import javax.persistence.*;


@NamedStoredProcedureQueries(
		{
				@NamedStoredProcedureQuery(
						name="pedidoItem.insert", 
						procedureName="PKG_PEDIDO.SP_INSERTAR_ITEM",
						parameters={
									@StoredProcedureParameter(mode=ParameterMode.OUT, name="P_ID_PEDIDO_ITEM", type=Long.class ),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PEDIDO", type=Long.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PRODUCTO", type=Long.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CANTIDAD", type=Long.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_PRECIO", type=Double.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_IMPORTE", type=Double.class),
									
									// Auditoria
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_TIPO", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Integer.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)


							}					
				) 
		}
	)
@Entity
@Table(name="PEDIDO_ITEM")
public class PedidoItem extends GenericEntidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PEDIDOITEM")
	private long idPedidoitem;

	@Column(name="CANTIDAD")
	private Long cantidad;

	@Column(name="IMPORTE")
	private double importe;

	@Column(name="PRECIO")
	private double precio;

	//bi-directional many-to-one association to Pedido
	@ManyToOne
	@JoinColumn(name="ID_PEDIDO")
	private Pedido pedido;

	//bi-directional many-to-one association to Producto
	@ManyToOne
	@JoinColumn(name="ID_PRODUCTO")
	private Producto producto;

	public PedidoItem() {
		this.setPedido(new Pedido());
		this.setProducto(new Producto());
	}

	public long getIdPedidoitem() {
		return this.idPedidoitem;
	}

	public void setIdPedidoitem(long idPedidoitem) {
		this.idPedidoitem = idPedidoitem;
	}

	public Long getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}


	public double getImporte() {
		return this.importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public double getPrecio() {
		return this.precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Pedido getPedido() {
		return this.pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public void ejecutarCalculos(){
		this.setImporte(this.getCantidad()* this.getPrecio());
	}
}
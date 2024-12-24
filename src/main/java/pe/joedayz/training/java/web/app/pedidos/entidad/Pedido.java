package pe.joedayz.training.java.web.app.pedidos.entidad;

import java.io.Serializable;

import javax.persistence.*;



import java.util.ArrayList;
import java.util.List;
import pe.joedayz.training.java.web.app.pedidos.entidad.vo.PedidoVO;

@NamedStoredProcedureQueries(
		{
			@NamedStoredProcedureQuery(
				name="pedido.insert", 
				procedureName="PKG_PEDIDO.SP_INSERTAR",
				parameters={
							@StoredProcedureParameter(mode=ParameterMode.OUT, name="P_ID_PEDIDO", type=Long.class ),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_CLIENTE", type=Long.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_FECHA_ATENCION", type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_IMPORTE", type=Double.class),
							
							// Auditoria
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_TIPO", type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Integer.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)

					}				
					),
		
			@NamedStoredProcedureQuery(
				name="pedido.findByLikeObjectVO", 
				procedureName="PKG_PEDIDO.SP_BUSCAR_X_CRITERIOS",
				resultClasses= PedidoVO.class,
				parameters={
							@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_C_CURSOR", type=void.class ),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CLIENTE", type=String.class)
				}					
					),
			@NamedStoredProcedureQuery(
							name="pedido.findByLikeObject", 
							procedureName="PKG_PEDIDO.SP_BUSCAR_X_ID",
							resultClasses= Pedido.class,
							parameters={
										@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_C_CURSOR", type=void.class ),
										@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_PEDIDO_ID", type=String.class)
								}					
			)
			
		}			
	)

@Entity
public class Pedido extends GenericEntidad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PEDIDO")
	private long idPedido;

	@Column(name="FECHA_ATENCION")
	private String fechaAtencion;

	@Column(name="FECHA_REGISTRO")
	private String fechaRegistro;

	@Column(name="IMPORTE")
	private double importe;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="ID_CLIENTE")
	private Cliente cliente;

	//bi-directional many-to-one association to PedidoItem
	@OneToMany(mappedBy="pedido")
	private List<PedidoItem> pedidoItems;
	

	public Pedido() {
		super();
		this.setCliente(new Cliente());
		this.setPedidoItems(new ArrayList<PedidoItem>());
		this.setImporte(0.0);
	}

	public long getIdPedido() {
		return this.idPedido;
	}

	public void setIdPedido(long idPedido) {
		this.idPedido = idPedido;
	}

	public String getFechaAtencion() {
		return this.fechaAtencion;
	}

	public void setFechaAtencion(String fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public String getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PedidoItem> getPedidoItems() {
		return this.pedidoItems;
	}

	public void setPedidoItems(List<PedidoItem> pedidoItems) {
		this.pedidoItems = pedidoItems;
	}

	public PedidoItem addPedidoItem(PedidoItem pedidoItem) {
		getPedidoItems().add(pedidoItem);
		pedidoItem.setPedido(this);

		return pedidoItem;
	}

	public PedidoItem removePedidoItem(PedidoItem pedidoItem) {
		getPedidoItems().remove(pedidoItem);
		pedidoItem.setPedido(null);

		return pedidoItem;
	}

	public void ejecutarCalculos(){
		Double tmpImporte=0.0;
		for (PedidoItem pedidoItem : pedidoItems) {
			tmpImporte+=pedidoItem.getImporte();
		}
		this.setImporte(tmpImporte);
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

}
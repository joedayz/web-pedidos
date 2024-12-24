package pe.joedayz.training.java.web.app.pedidos.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;


@NamedStoredProcedureQueries(
	{
		@NamedStoredProcedureQuery(
				name="producto.findByLikeObject", 
				procedureName="PKG_PRODUCTO.SP_BUSCARXCRITERIOS",
				resultClasses= Producto.class,
				parameters={
							@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_C_CURSOR", type=void.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class)
						}					
				),
		@NamedStoredProcedureQuery(
				name="producto.findByObject", 
				procedureName="PKG_PRODUCTO.SP_BUSCARXID",
				resultClasses= Producto.class,
				parameters={
							@StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_C_CURSOR", type=void.class),
							@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PRODUCTO", type=Integer.class)
							}					
				),
		@NamedStoredProcedureQuery(
				name="producto.insert", 
				procedureName="PKG_PRODUCTO.SP_INSERTAR",
				parameters={
								@StoredProcedureParameter(mode=ParameterMode.OUT, name="P_ID_PRODUCTO", type=Integer.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_DESCRIPCION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_PRECIO", type=Double.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_STOCK", type=Integer.class),
								
								
								// Auditoria
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_TIPO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Integer.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)
								
						}					
				),
			@NamedStoredProcedureQuery(
						name="producto.update", 
						procedureName="PKG_PRODUCTO.SP_ACTUALIZAR",
						parameters={
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PRODUCTO", type=Integer.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_DESCRIPCION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_PRECIO", type=Double.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_STOCK", type=Integer.class),
										
										// Auditoria
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_TIPO", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Integer.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
								@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)
										
								}					
						),
			@NamedStoredProcedureQuery(
							name="producto.delete", 
							procedureName="PKG_PRODUCTO.SP_ELIMINAR",
							parameters={
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PRODUCTO", type=Integer.class),
											
											// Auditoria
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_TIPO", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Integer.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
									@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)
											
									}					
							),
			@NamedStoredProcedureQuery(
							name="producto.actualizarStock", 
							procedureName="PKG_PRODUCTO.SP_ACTUALIZAR_STOCK",
							parameters={
										@StoredProcedureParameter(mode=ParameterMode.IN,name="P_ID_PRODUCTO", type=Integer.class ),
										@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_STOCK", type=Integer.class),
										@StoredProcedureParameter(mode=ParameterMode.IN,  name="P_TIPO", type=Integer.class)		
										}					
							)

		}
)

@Entity
public class Producto extends GenericEntidad{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_PRODUCTO")
	private int idProducto;
	
	@Column(name="NOMBRE")
	private String nombre;
	
	@Column(name="DESCRIPCION")
	private String descripcion;
	
	@Column(name="PRECIO")
	private double precio;

	@Column(name="STOCK")
	private int stock;
	
	public Producto() {
		super();
		this.init();
	}

	private void init(){
		
	}
	
	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre
				+ ", descripcion=" + descripcion + ", precio=" + precio
				+ ", stock=" + stock + "]";
	}



}

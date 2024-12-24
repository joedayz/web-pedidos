package pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.joedayz.training.java.web.app.pedidos.entidad.Pedido;
import pe.joedayz.training.java.web.app.pedidos.entidad.PedidoItem;
import pe.joedayz.training.java.web.app.pedidos.entidad.Producto;
import pe.joedayz.training.java.web.app.pedidos.entidad.vo.PedidoVO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.PedidoDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.ProductoDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;


@Transactional
@Repository("pedidoDAO")
public class PedidoDAOImpl implements PedidoDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private ProductoDAO productoDAO;

	public PedidoDAOImpl() {
		
	}

	@Override
	public boolean insert(Pedido pedido) throws PersistenciaExcepcion {
		boolean sw=false;
		try {
			sw=this.insertCabecera(pedido);
			
			if (sw) {
				
				System.out.println("Exito de iserci�n de cabecera " + pedido.getIdPedido());
				
				for (PedidoItem item : pedido.getPedidoItems()) {
					item.setPedido(pedido);
					sw=this.insertItem(item);
					if (sw) {
						System.out.println("Exito de iserci�n de detalle " + item.getIdPedidoitem());						
						Producto producto= new Producto();
						producto.setIdProducto(item.getProducto().getIdProducto());
						producto.setStock(Integer.valueOf(item.getCantidad().toString()));
						
						sw=this.getProductoDAO().actualizarStock(producto, 1);
						
						if (!sw) {
							sw=false;
							break;
						}else{
							System.out.println("Exito al actualizar stock ");
						}
					}else{
						sw=false;
						break;
					}
				}
			}
		} catch (Exception e) {
			throw new PersistenciaExcepcion(e);
		}
		return sw;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoVO> findByLikeObjectVO(Pedido pedido) throws PersistenciaExcepcion {
		List<PedidoVO> lstPedidoVO = null;	
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("pedido.findByLikeObjectVO");
			
			spq.setParameter("P_CLIENTE", pedido.getCliente().getRazonSocial());
			
			
			if (spq.execute()) {
				lstPedidoVO = (List<PedidoVO>)spq.getOutputParameterValue("P_C_CURSOR");
			}
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcepcion(e);
		}
		return lstPedidoVO;

	}

	@Override
	public boolean insertCabecera(Pedido pedido) throws PersistenciaExcepcion {
		Object idPedido= null;
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("pedido.insert");
			
			spq.setParameter("P_ID_CLIENTE", pedido.getCliente().getIdCliente());
			spq.setParameter("P_FECHA_ATENCION", pedido.getFechaAtencion());
			spq.setParameter("P_IMPORTE", pedido.getImporte());
			
			// Auditoria
			
			spq.setParameter("P_AUD_TIPO", pedido.getAudTipo());
			spq.setParameter("P_AUD_IDUSUARIO", pedido.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", pedido.getAudSesion());
			spq.setParameter("P_AUD_IP", pedido.getAudIP());
			
			spq.execute(); 
			
			idPedido = spq.getOutputParameterValue(1);
			System.err.println("idPedido "+idPedido);
			if (idPedido!=null) {
				pedido.setIdPedido(Integer.valueOf(idPedido.toString()));
				sw=true;
			}
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			sw=false;
			throw new PersistenciaExcepcion(e);
		}
		return sw;
		
	}


	@Override
	public boolean insertItem(PedidoItem item) throws PersistenciaExcepcion {
		Object idPedidoItem= null;
		boolean sw=false;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("pedidoItem.insert");
			
			spq.setParameter("P_ID_PEDIDO", item.getPedido().getIdPedido());
			spq.setParameter("P_ID_PRODUCTO", item.getProducto().getIdProducto());
			spq.setParameter("P_CANTIDAD", item.getCantidad());
			spq.setParameter("P_PRECIO", item.getPrecio());
			spq.setParameter("P_IMPORTE", item.getImporte());
			
			// Auditoria
			
			spq.setParameter("P_AUD_TIPO", item.getAudTipo());
			spq.setParameter("P_AUD_IDUSUARIO", item.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", item.getAudSesion());
			spq.setParameter("P_AUD_IP", item.getAudIP());
			
			spq.execute(); 
			
			idPedidoItem = spq.getOutputParameterValue(1);
			if (idPedidoItem!=null) {
				item.setIdPedidoitem(Integer.valueOf(idPedidoItem.toString()));
				sw=true;
			}
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			sw=false;
			throw new PersistenciaExcepcion(e);
		}
		return sw;
		
	}


	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

	@Override
	public boolean update(Pedido t) throws PersistenciaExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Pedido t) throws PersistenciaExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pedido findByObject(Pedido t) throws PersistenciaExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findByLikeObject(Pedido t) throws PersistenciaExcepcion {
		// TODO Auto-generated method stub
		return null;
	}
}

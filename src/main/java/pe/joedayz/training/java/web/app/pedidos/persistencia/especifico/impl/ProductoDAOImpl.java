package pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.joedayz.training.java.web.app.pedidos.entidad.Producto;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.ProductoDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;

@Transactional
@Repository("productoDAO")
public class ProductoDAOImpl implements ProductoDAO {
	
	@PersistenceContext
	private EntityManager em;

	public ProductoDAOImpl() {
		
	}

	@Override
	public boolean insert(Producto producto) throws PersistenciaExcepcion {
		boolean sw=true;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.insert");
			
			spq.setParameter("P_NOMBRE", producto.getNombre());
			spq.setParameter("P_DESCRIPCION", producto.getDescripcion());
			spq.setParameter("P_PRECIO", producto.getPrecio());
			spq.setParameter("P_STOCK", producto.getStock());
			// Auditoria
			
			spq.setParameter("P_AUD_TIPO", producto.getAudTipo());
			spq.setParameter("P_AUD_IDUSUARIO", producto.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", producto.getAudSesion());
			spq.setParameter("P_AUD_IP", producto.getAudIP());
			
			spq.execute();
			
		    Object id = spq.getOutputParameterValue(1); //P_ID_PRODUCTO
		    if (id!=null) {
		    	producto.setIdProducto(Integer.valueOf(id.toString()));
			}else{
				sw=false;
			}
			
			em.close();
			
		} catch (Exception e) {
			sw=false;
			throw new PersistenceException(e);
		}
		return sw;
	}

	@Override
	public boolean update(Producto producto) throws PersistenciaExcepcion {

		boolean sw=true;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.update");
			
			spq.setParameter("P_ID_PRODUCTO", producto.getIdProducto());
			spq.setParameter("P_NOMBRE", producto.getNombre());
			spq.setParameter("P_DESCRIPCION", producto.getDescripcion());
			spq.setParameter("P_PRECIO", producto.getPrecio());
			spq.setParameter("P_STOCK", producto.getStock());
			
			// Auditoria
			
			spq.setParameter("P_AUD_TIPO", producto.getAudTipo());
			spq.setParameter("P_AUD_IDUSUARIO", producto.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", producto.getAudSesion());
			spq.setParameter("P_AUD_IP", producto.getAudIP());
			
			spq.execute();
			
			em.close();
			
		} catch (Exception e) {
			sw=false;
			throw new PersistenceException(e);
		}
		return sw;
	}

	@Override
	public boolean delete(Producto producto) throws PersistenciaExcepcion {
		boolean sw=true;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.delete");
			
			spq.setParameter("P_ID_PRODUCTO", producto.getIdProducto());
			
			// Auditoria
			
			spq.setParameter("P_AUD_TIPO", producto.getAudTipo());
			spq.setParameter("P_AUD_IDUSUARIO", producto.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", producto.getAudSesion());
			spq.setParameter("P_AUD_IP", producto.getAudIP());
			
			spq.execute();
			
			em.close();
			
		} catch (Exception e) {
			sw=false;
			throw new PersistenceException(e);
		}
		return sw;
	}

	@Override
	public Producto findByObject(Producto producto) throws PersistenciaExcepcion {

		Producto oProducto = null;	
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.findByObject");
			
			spq.setParameter("P_ID_PRODUCTO", producto.getIdProducto());
			
			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<Producto> lstProducto= (List<Producto>)spq.getOutputParameterValue("P_C_CURSOR");
				if (lstProducto!=null && lstProducto.size()>0) {
					oProducto=lstProducto.get(0);
				}
			}
			em.close();
			
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return oProducto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> findByLikeObject(Producto producto) throws PersistenciaExcepcion {
		List<Producto> lstProducto = null;	
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.findByLikeObject");
			
			spq.setParameter("P_NOMBRE", producto.getNombre());
			
			if (spq.execute()) {
				lstProducto = (List<Producto>)spq.getOutputParameterValue("P_C_CURSOR");
			}
			em.close();
			
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return lstProducto;
	}
	
	@Override
	public boolean actualizarStock(Producto producto, int tipo)
			throws PersistenciaExcepcion {
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("producto.actualizarStock");
			
			spq.setParameter("P_ID_PRODUCTO", producto.getIdProducto());
			spq.setParameter("P_STOCK", producto.getStock());
			spq.setParameter("P_TIPO", tipo);
			
			spq.execute();
			
			em.close();
			
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcepcion(e);
		}
	}
}

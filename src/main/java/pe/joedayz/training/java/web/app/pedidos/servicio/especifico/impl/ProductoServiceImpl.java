package pe.joedayz.training.java.web.app.pedidos.servicio.especifico.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.training.java.web.app.pedidos.entidad.Producto;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.ProductoDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.ProductoService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;

@Service("productoService")
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoDAO productoDAO; // IoC y DDI

	public ProductoServiceImpl() {

	}

	@Override
	public boolean insert(Producto producto) throws ServicioExcepcion {
		try {

			return this.getProductoDAO().insert(producto);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
	}

	@Override
	public boolean update(Producto producto) throws ServicioExcepcion {
		try {
			return this.getProductoDAO().update(producto);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
	}

	@Override
	public boolean delete(Producto producto) throws ServicioExcepcion {

		try {
			return this.getProductoDAO().delete(producto);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
	}

	@Override
	public Producto findByObject(Producto producto) throws ServicioExcepcion {
		try {
			return this.getProductoDAO().findByObject(producto);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
	}

	@Override
	public List<Producto> findByLikeObject(Producto producto)
			throws ServicioExcepcion {

		try {

			return this.getProductoDAO().findByLikeObject(producto);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}

	}

	public ProductoDAO getProductoDAO() {
		return productoDAO;
	}

	public void setProductoDAO(ProductoDAO productoDAO) {
		this.productoDAO = productoDAO;
	}

}

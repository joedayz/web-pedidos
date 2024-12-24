package pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf;


import pe.joedayz.training.java.web.app.pedidos.entidad.Producto;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;
import pe.joedayz.training.java.web.app.pedidos.persistencia.generico.GenericDAO;

public interface ProductoDAO extends GenericDAO<Producto> {

	public boolean actualizarStock(Producto producto, int tipo) throws PersistenciaExcepcion;

}

package pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf;


import pe.joedayz.training.java.web.app.pedidos.entidad.Cliente;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;
import pe.joedayz.training.java.web.app.pedidos.persistencia.generico.GenericDAO;

public interface ClienteDAO extends GenericDAO<Cliente> {
	
	public Cliente buscarXRuc(Cliente cliente)throws PersistenciaExcepcion;

}

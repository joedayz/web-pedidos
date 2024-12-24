package pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf;


import pe.joedayz.training.java.web.app.pedidos.entidad.Cliente;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;
import pe.joedayz.training.java.web.app.pedidos.servicio.generico.GenericService;

public interface ClienteService extends GenericService<Cliente> {
	
	public Cliente buscarXRuc(Cliente cliente) throws ServicioExcepcion;

}

package pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf;


import pe.joedayz.training.java.web.app.pedidos.entidad.Usuario;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;
import pe.joedayz.training.java.web.app.pedidos.servicio.generico.GenericService;

public interface UsuarioService extends GenericService<Usuario> {

  public Usuario validarAcceso(Usuario usuario) throws ServicioExcepcion;

}

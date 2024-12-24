package pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf;


import pe.joedayz.training.java.web.app.pedidos.entidad.Usuario;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;
import pe.joedayz.training.java.web.app.pedidos.persistencia.generico.GenericDAO;

public interface UsuarioDAO extends GenericDAO<Usuario> {

  public Usuario validarAcceso(Usuario usuario) throws PersistenciaExcepcion;

}

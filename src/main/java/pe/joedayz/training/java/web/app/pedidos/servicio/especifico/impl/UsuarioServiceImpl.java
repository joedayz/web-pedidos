package pe.joedayz.training.java.web.app.pedidos.servicio.especifico.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.training.java.web.app.pedidos.entidad.Usuario;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.UsuarioDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.UsuarioService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

  @Autowired
  private UsuarioDAO usuarioDAO; // IoC y DDI

  public UsuarioServiceImpl() {

  }

  @Override
  public boolean insert(Usuario usuario) throws ServicioExcepcion {
    try {

      return this.getUsuarioDAO().insert(usuario);

    } catch (PersistenciaExcepcion e) {
      throw new ServicioExcepcion(e);
    }
  }

  @Override
  public boolean update(Usuario usuario) throws ServicioExcepcion {
    try {
      return this.getUsuarioDAO().update(usuario);

    } catch (PersistenciaExcepcion e) {
      throw new ServicioExcepcion(e);
    }
  }

  @Override
  public boolean delete(Usuario usuario) throws ServicioExcepcion {

    try {
      return this.getUsuarioDAO().delete(usuario);

    } catch (PersistenciaExcepcion e) {
      throw new ServicioExcepcion(e);
    }
  }

  @Override
  public Usuario findByObject(Usuario usuario) throws ServicioExcepcion {
    try {
      return this.getUsuarioDAO().findByObject(usuario);

    } catch (PersistenciaExcepcion e) {
      throw new ServicioExcepcion(e);
    }
  }

  @Override
  public List<Usuario> findByLikeObject(Usuario usuario)
      throws ServicioExcepcion {

    try {

      return this.getUsuarioDAO().findByLikeObject(usuario);

    } catch (PersistenciaExcepcion e) {
      throw new ServicioExcepcion(e);
    }

  }

  @Override
  public Usuario validarAcceso(Usuario usuario) throws ServicioExcepcion {
    try {
      return this.getUsuarioDAO().validarAcceso(usuario);

    } catch (PersistenciaExcepcion e) {
      throw new ServicioExcepcion(e);
    }
  }

  public UsuarioDAO getUsuarioDAO() {
    return usuarioDAO;
  }

  public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
    this.usuarioDAO = usuarioDAO;
  }


}

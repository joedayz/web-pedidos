package pe.joedayz.training.java.web.app.pedidos.servicio.generico;

import java.util.List;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;

public interface GenericService<T> {

  public boolean insert(T t) throws ServicioExcepcion;

  public boolean update(T t) throws ServicioExcepcion;

  public boolean delete(T t) throws ServicioExcepcion;

  public T findByObject(T t) throws ServicioExcepcion;

  public List<T> findByLikeObject(T t) throws ServicioExcepcion;

}

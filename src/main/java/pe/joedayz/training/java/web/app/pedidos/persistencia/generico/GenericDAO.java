package pe.joedayz.training.java.web.app.pedidos.persistencia.generico;

import java.util.List;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;

// Interface par�metrica

public interface GenericDAO<T> {

  public boolean insert(T t) throws PersistenciaExcepcion;

  public boolean update(T t) throws PersistenciaExcepcion;

  public boolean delete(T t) throws PersistenciaExcepcion;

  public T findByObject(T t) throws PersistenciaExcepcion;

  public List<T> findByLikeObject(T t) throws PersistenciaExcepcion;

}

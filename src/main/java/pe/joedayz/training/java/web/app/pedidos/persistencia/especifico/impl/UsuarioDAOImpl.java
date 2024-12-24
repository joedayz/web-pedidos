package pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.joedayz.training.java.web.app.pedidos.entidad.Usuario;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.UsuarioDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;


@Transactional
@Repository("usuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO {

  @PersistenceContext
  private EntityManager em;

  public UsuarioDAOImpl() {

  }

  @Override
  public boolean insert(Usuario t) throws PersistenciaExcepcion {

    return false;
  }

  @Override
  public boolean update(Usuario t) throws PersistenciaExcepcion {

    return false;
  }

  @Override
  public boolean delete(Usuario t) throws PersistenciaExcepcion {

    return false;
  }

  @Override
  public Usuario findByObject(Usuario t) throws PersistenciaExcepcion {

    return null;
  }

  @Override
  public List<Usuario> findByLikeObject(Usuario t)
      throws PersistenciaExcepcion {
    return null;
  }

  @Override
  public Usuario validarAcceso(Usuario usuario) throws PersistenciaExcepcion {
    Usuario oUsuario = null;
    try {
      StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("usuario.validarAcceso");

      spq.setParameter("P_USUARIO", usuario.getUsuario());
      spq.setParameter("P_CLAVE", usuario.getClave());

      if (spq.execute()) {
        @SuppressWarnings("unchecked")
        List<Usuario> lstUsuario= (List<Usuario>)spq.getOutputParameterValue("P_C_CURSOR");
        if (lstUsuario!=null && lstUsuario.size()>0) {
          oUsuario=lstUsuario.get(0);
        }
      }
      em.close();

    } catch (Exception e) {
      throw new PersistenceException(e);
    }
    return oUsuario;
  }

}


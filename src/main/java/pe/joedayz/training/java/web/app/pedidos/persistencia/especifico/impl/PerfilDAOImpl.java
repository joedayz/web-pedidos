package pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.joedayz.training.java.web.app.pedidos.entidad.Perfil;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.PerfilDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;


@Transactional
@Repository("perfilDAO")
public class PerfilDAOImpl implements PerfilDAO {
	
	@PersistenceContext
	private EntityManager em;

	public PerfilDAOImpl() {
		
	}

	@Override
	public boolean insert(Perfil perfil) throws PersistenciaExcepcion {
		boolean sw=true;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfil.insert");
			
			spq.setParameter("P_NOMBRE", perfil.getNombre());
			
			// Auditoria
			
			spq.setParameter("P_AUD_TIPO", perfil.getAudTipo());
			spq.setParameter("P_AUD_IDUSUARIO", perfil.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", perfil.getAudSesion());
			spq.setParameter("P_AUD_IP", perfil.getAudIP());
			
			spq.execute();
			
		    Object id = spq.getOutputParameterValue(1); //P_ID_PERFIL
		    if (id!=null) {
		    	perfil.setIdPerfil(Integer.valueOf(id.toString()));
			}else{
				sw=false;
			}
			
			em.close();
			
		} catch (Exception e) {
			sw=false;
			throw new PersistenceException(e);
		}
		return sw;
	}

	@Override
	public boolean update(Perfil perfil) throws PersistenciaExcepcion {

		boolean sw=true;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfil.update");
			
			spq.setParameter("P_ID_PERFIL", perfil.getIdPerfil());
			spq.setParameter("P_NOMBRE", perfil.getNombre());
			
			// Auditoria
			
			spq.setParameter("P_AUD_TIPO", perfil.getAudTipo());
			spq.setParameter("P_AUD_IDUSUARIO", perfil.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", perfil.getAudSesion());
			spq.setParameter("P_AUD_IP", perfil.getAudIP());
			
			spq.execute();
			
			em.close();
			
		} catch (Exception e) {
			sw=false;
			throw new PersistenceException(e);
		}
		return sw;
	}

	@Override
	public boolean delete(Perfil perfil) throws PersistenciaExcepcion {
		boolean sw=true;
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfil.delete");
			
			spq.setParameter("P_ID_PERFIL", perfil.getIdPerfil());
			
			// Auditoria
			
			spq.setParameter("P_AUD_TIPO", perfil.getAudTipo());
			spq.setParameter("P_AUD_IDUSUARIO", perfil.getAudIdUsuario());
			spq.setParameter("P_AUD_SESION", perfil.getAudSesion());
			spq.setParameter("P_AUD_IP", perfil.getAudIP());
			
			spq.execute();
			
			em.close();
			
		} catch (Exception e) {
			sw=false;
			throw new PersistenceException(e);
		}
		return sw;
	}

	@Override
	public Perfil findByObject(Perfil perfil) throws PersistenciaExcepcion {

		Perfil oPerfil = null;	
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfil.findByObject");
			
			spq.setParameter("P_ID_PERFIL", perfil.getIdPerfil());
			
			if (spq.execute()) {
				List<Perfil> lstPerfil= (List<Perfil>)spq.getOutputParameterValue("P_C_CURSOR");
				if (lstPerfil!=null && lstPerfil.size()>0) {
					oPerfil=lstPerfil.get(0);
				}
			}
			em.close();
			
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return oPerfil;
	}

	@Override
	public List<Perfil> findByLikeObject(Perfil perfil) throws PersistenciaExcepcion {
		List<Perfil> lstPerfil = null;	
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("perfil.findByLikeObject");
			
			spq.setParameter("P_NOMBRE", perfil.getNombre());
			
			if (spq.execute()) {
				lstPerfil = (List<Perfil>)spq.getOutputParameterValue("P_C_CURSOR");
			}
			em.close();
			
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
		return lstPerfil;
	}

}

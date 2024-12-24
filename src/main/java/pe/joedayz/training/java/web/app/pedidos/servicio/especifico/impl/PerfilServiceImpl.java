package pe.joedayz.training.java.web.app.pedidos.servicio.especifico.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.training.java.web.app.pedidos.entidad.Perfil;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.PerfilDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.PerfilService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;

@Service("perfilService")
public class PerfilServiceImpl implements PerfilService {

	@Autowired
	private PerfilDAO perfilDAO; // IoC y DDI

	public PerfilServiceImpl() {

	}

	@Override
	public boolean insert(Perfil perfil) throws ServicioExcepcion {
		try {

			return this.getPerfilDAO().insert(perfil);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
	}

	@Override
	public boolean update(Perfil perfil) throws ServicioExcepcion {
		try {
			return this.getPerfilDAO().update(perfil);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
	}

	@Override
	public boolean delete(Perfil perfil) throws ServicioExcepcion {

		try {
			return this.getPerfilDAO().delete(perfil);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
	}

	@Override
	public Perfil findByObject(Perfil perfil) throws ServicioExcepcion {
		try {
			return this.getPerfilDAO().findByObject(perfil);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
	}

	@Override
	public List<Perfil> findByLikeObject(Perfil perfil)
			throws ServicioExcepcion {

		try {

			return this.getPerfilDAO().findByLikeObject(perfil);

		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}

	}

	public PerfilDAO getPerfilDAO() {
		return perfilDAO;
	}

	public void setPerfilDAO(PerfilDAO perfilDAO) {
		this.perfilDAO = perfilDAO;
	}

}

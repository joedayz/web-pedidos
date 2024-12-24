package pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.joedayz.training.java.web.app.pedidos.entidad.Cliente;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.ClienteDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;

@Transactional
@Repository("clienteDAO")
public class ClienteDAOImpl 
			implements ClienteDAO {
	
	@PersistenceContext
	private EntityManager em;

	public ClienteDAOImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean insert(Cliente t) throws PersistenciaExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cliente t) throws PersistenciaExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cliente t) throws PersistenciaExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente findByObject(Cliente t) throws PersistenciaExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findByLikeObject(Cliente t)
			throws PersistenciaExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente buscarXRuc(Cliente cliente) throws PersistenciaExcepcion {
		Cliente oCliente = null;	
		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("cliente.buscarXRuc");
			
			spq.setParameter("P_RUC", cliente.getRuc());
			
			if (spq.execute()) {
				@SuppressWarnings("unchecked")
				List<Cliente> lstCliente = (List<Cliente>)spq.getOutputParameterValue("P_C_CURSOR");
				if (lstCliente!=null) {
					if (lstCliente.size()>0) {
						oCliente=lstCliente.get(0);
					}
				}
			}
			em.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenciaExcepcion(e);
		}
		return oCliente;

	}


}

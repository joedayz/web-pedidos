package pe.joedayz.training.java.web.app.pedidos.servicio.especifico.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.training.java.web.app.pedidos.entidad.Cliente;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.ClienteDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.ClienteService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService {
	
	
	@Autowired
	private ClienteDAO clienteDAO;

	public ClienteServiceImpl() {
		
	}

	public ClienteDAO getClienteDAO() {
		return clienteDAO;
	}

	public void setClienteDAO(ClienteDAO clienteDAO) {
		this.clienteDAO = clienteDAO;
	}


	@Override
	public Cliente buscarXRuc(Cliente cliente) throws ServicioExcepcion {
		Cliente oCliente=null;
		try {
			oCliente=this.getClienteDAO().buscarXRuc(cliente);
		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
		return oCliente;
	}

	@Override
	public boolean insert(Cliente t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Cliente t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Cliente t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente findByObject(Cliente t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cliente> findByLikeObject(Cliente t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}

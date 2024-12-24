package pe.joedayz.training.java.web.app.pedidos.servicio.especifico.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.joedayz.training.java.web.app.pedidos.entidad.Pedido;
import pe.joedayz.training.java.web.app.pedidos.entidad.PedidoItem;
import pe.joedayz.training.java.web.app.pedidos.entidad.vo.PedidoVO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf.PedidoDAO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.PedidoService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;

@Service("pedidoService")
public class PedidoServiceImpl implements PedidoService {
	
	
	@Autowired
	private PedidoDAO pedidoDAO;

	public PedidoServiceImpl() {
	}

	@Override
	public boolean insert(Pedido pedido) throws ServicioExcepcion {
		boolean sw=false;
		try {
			sw=this.getPedidoDAO().insert(pedido);
		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
		return sw;
	}



	@Override
	public List<PedidoVO> findByLikeObjectVO(Pedido pedido)
			throws ServicioExcepcion {
		List<PedidoVO> lstPedidoVO=null;
		try {
			lstPedidoVO=this.getPedidoDAO().findByLikeObjectVO(pedido);
		} catch (PersistenciaExcepcion e) {
			throw new ServicioExcepcion(e);
		}
		return lstPedidoVO;
	}

	@Override
	public boolean insertCabecera(Pedido pedido) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertItem(PedidoItem item) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	public PedidoDAO getPedidoDAO() {
		return pedidoDAO;
	}

	public void setPedidoDAO(PedidoDAO pedidoDAO) {
		this.pedidoDAO = pedidoDAO;
	}

	@Override
	public boolean update(Pedido t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Pedido t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pedido findByObject(Pedido t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Pedido> findByLikeObject(Pedido t) throws ServicioExcepcion {
		// TODO Auto-generated method stub
		return null;
	}

}

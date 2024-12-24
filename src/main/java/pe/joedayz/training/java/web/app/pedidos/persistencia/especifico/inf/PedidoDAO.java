package pe.joedayz.training.java.web.app.pedidos.persistencia.especifico.inf;

import java.util.List;
import pe.joedayz.training.java.web.app.pedidos.entidad.Pedido;
import pe.joedayz.training.java.web.app.pedidos.entidad.PedidoItem;
import pe.joedayz.training.java.web.app.pedidos.entidad.vo.PedidoVO;
import pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion.PersistenciaExcepcion;
import pe.joedayz.training.java.web.app.pedidos.persistencia.generico.GenericDAO;

public interface PedidoDAO  extends GenericDAO<Pedido> {

	public boolean insertCabecera(Pedido pedido) throws PersistenciaExcepcion;
	
	public boolean insertItem(PedidoItem item) throws PersistenciaExcepcion;
	
	public List<PedidoVO>  findByLikeObjectVO(Pedido pedido) throws PersistenciaExcepcion;
	
}

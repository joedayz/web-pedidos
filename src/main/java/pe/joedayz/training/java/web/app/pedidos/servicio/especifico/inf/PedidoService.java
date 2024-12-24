package pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf;

import java.util.List;
import pe.joedayz.training.java.web.app.pedidos.entidad.Pedido;
import pe.joedayz.training.java.web.app.pedidos.entidad.PedidoItem;
import pe.joedayz.training.java.web.app.pedidos.entidad.vo.PedidoVO;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;
import pe.joedayz.training.java.web.app.pedidos.servicio.generico.GenericService;

public interface PedidoService extends GenericService<Pedido> {
	
	public boolean insertCabecera(Pedido pedido) throws ServicioExcepcion;
	
	public boolean insertItem(PedidoItem item) throws ServicioExcepcion;

	public List<PedidoVO> findByLikeObjectVO(Pedido pedido)throws ServicioExcepcion;
}

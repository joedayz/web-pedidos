package pe.joedayz.training.java.web.app.pedidos.presentacion;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pe.joedayz.training.java.web.app.pedidos.entidad.Cliente;
import pe.joedayz.training.java.web.app.pedidos.entidad.Pedido;
import pe.joedayz.training.java.web.app.pedidos.entidad.PedidoItem;
import pe.joedayz.training.java.web.app.pedidos.entidad.Producto;
import pe.joedayz.training.java.web.app.pedidos.entidad.vo.PedidoVO;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.ClienteService;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.PedidoService;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.ProductoService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;


@Controller(value="pedidoMB")
@Scope(value="session")
public class PedidoMB extends GenericMB{


	private Pedido pedido;
	private PedidoItem pedidoItem;
	
	private List<PedidoVO> 	lstPedidoVO;
	
	private Cliente cliente;
	
	private Producto producto;
	private List<Producto> 	lstProducto;
	
	
	@Autowired
	protected ClienteService clienteService;
	
	@Autowired
	protected ProductoService productoService;
	
	@Autowired
	protected PedidoService pedidoService;
	
	
	public PedidoMB() {
		
	}

	@PostConstruct
	public void init(){
		this.inicializar();
		this.getCliente().setRuc("20544987306");
	}
	
	public String nuevo(){
		this.inicializar();
		return "pedido_registro";
	}
	
	public void limpiar(){
		this.inicializar();
	}
	
	private void inicializar(){
		this.setPedido(new Pedido());
		this.setCliente(new Cliente());
		this.setProducto(new Producto());
		this.setLstProducto(new ArrayList<Producto>());		
		
	}
	public void buscar(){		
		try {
			
			List<PedidoVO> oLstPedidoVO;
			
			oLstPedidoVO= this.getPedidoService().findByLikeObjectVO(this.getPedido());
			
			for (PedidoVO pedidoVO : oLstPedidoVO) {
				System.out.println(pedidoVO);
			}
			
			this.setLstPedidoVO(oLstPedidoVO);
		} catch (Exception e) {
			
		}
			
	}

	
	public void buscarClienteXRuc(){		
		try {
			
			Cliente oCliente= this.getClienteService().buscarXRuc(this.getCliente());
			if (oCliente==null) {
				oCliente= new Cliente();
				super.msgInfo("No existe cliente con el RUC ingresado");
			}
			
			this.setCliente(oCliente);
		} catch (Exception e) {
		}
			
	}
	
	public void buscarProducto(){		
		try {
			
			List<Producto> oLstProducto;
			
			oLstProducto= this.getProductoService().findByLikeObject(this.getProducto());
			
			for (Producto producto : oLstProducto) {
				System.out.println(producto);
			}
			
			this.setLstProducto(oLstProducto);
		} catch (Exception e) {
			
		}
			
	}
	
	public void actualizarDetalle(){
		for (PedidoItem item : this.getPedido().getPedidoItems()) {
			item.ejecutarCalculos(); // Sub Totales
		}
		this.getPedido().ejecutarCalculos(); // Total
	}
	
	public void agregarProducto(Producto producto){
		
		PedidoItem pedidoItem= new PedidoItem();
		pedidoItem.setProducto(producto);
		pedidoItem.setPrecio(producto.getPrecio());
		pedidoItem.setCantidad(1L);
		pedidoItem.ejecutarCalculos();
		this.getPedido().getPedidoItems().add(pedidoItem);	
		this.getPedido().ejecutarCalculos();
	}


	public String cancelar(){
		return "pedido_listado";
	}	
	public void grabar(){
		
		this.getPedido().setCliente(this.getCliente());
		
		boolean sw=false;
		if (this.getPedido().getIdPedido()>0) {
			// Actualizaci�n
			try {
				sw=this.getPedidoService().update(this.getPedido());
				if (sw) {
					super.msgInfo("Pedido actualizado satisfactoriamente");
					System.err.println("MB "+this.getPedido().getIdPedido());
				} else {
					super.msgAlert("Alerta al actualizar Pedido");
				}
			} catch (ServicioExcepcion e) {
				e.printStackTrace();
			}
		} else {
			// Inserci�n
			try {
				sw=this.getPedidoService().insert(this.getPedido());
				if (sw) {
					super.msgInfo("Pedido creado satisfactoriamente");
					System.err.println("MB "+this.getPedido().getIdPedido());
				} else {
					super.msgAlert("Alerta al crear Pedido");
				}
			} catch (ServicioExcepcion e) {
				super.msgAlert("Error al crear Pedido "+ e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
	
	public void exportExcel(){
		
        try {

            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=Listado_Normas.xls");

            HSSFWorkbook workbook = new HSSFWorkbook();

            if (lstPedidoVO != null) {
                workbook = exportExcelFormato(lstPedidoVO);
            }
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.close();
            FacesContext.getCurrentInstance().responseComplete();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private HSSFWorkbook exportExcelFormato(List<PedidoVO> lstPedidoVO) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Listado de Pedidos");

        int countRow = 0;

        // CABECERA
        Row row = sheet.createRow(countRow);

        Cell cell = row.createCell(0);
        cell.setCellValue("Item");
        super.setStyleLisCabecera(workbook, cell);

        cell = row.createCell(1);
        cell.setCellValue("Pedido");
        super.setStyleLisCabecera(workbook, cell);

        cell = row.createCell(2);
        cell.setCellValue("Cliente");
        super.setStyleLisCabecera(workbook, cell);

        cell = row.createCell(3);
        cell.setCellValue("Fecha Atenci�n");
        super.setStyleLisCabecera(workbook, cell);
        
        cell = row.createCell(4);
        cell.setCellValue("Total");
        super.setStyleLisCabecera(workbook, cell);

        // LISTADO
        int item = 0;

        for (PedidoVO pedidoVO: lstPedidoVO) {

            countRow++;
            item++;

            row = sheet.createRow(countRow);

            cell = row.createCell(0);
            cell.setCellValue(item);

            // PEDIDO
            cell = row.createCell(1);
            cell.setCellValue(pedidoVO.getIdPedido());

            // CLIENTE
            cell = row.createCell(2);
            cell.setCellValue(pedidoVO.getCliente());

            // FECHA ATENCION
            cell = row.createCell(3);
            cell.setCellValue(pedidoVO.getFechaAtencion());

            // TOTAL
            cell = row.createCell(4);
            cell.setCellValue(pedidoVO.getTotal());
        }

        return workbook;
    }


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	public PedidoItem getPedidoItem() {
		return pedidoItem;
	}


	public void setPedidoItem(PedidoItem pedidoItem) {
		this.pedidoItem = pedidoItem;
	}


	public List<PedidoVO> getLstPedidoVO() {
		return lstPedidoVO;
	}

	public void setLstPedidoVO(List<PedidoVO> lstPedidoVO) {
		this.lstPedidoVO = lstPedidoVO;
	}

	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public ClienteService getClienteService() {
		return clienteService;
	}


	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}



	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public PedidoService getPedidoService() {
		return pedidoService;
	}

	public void setPedidoService(PedidoService pedidoService) {
		this.pedidoService = pedidoService;
	}

	public List<Producto> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<Producto> lstProducto) {
		this.lstProducto = lstProducto;
	}




	
}
		


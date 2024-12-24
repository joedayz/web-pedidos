package pe.joedayz.training.java.web.app.pedidos.presentacion;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pe.joedayz.training.java.web.app.pedidos.entidad.Producto;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.ProductoService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;


@Controller("productoMB")
public class ProductoMB extends GenericMB {
	
	private Producto producto;
	private List<Producto> lstProducto;
	
	@Autowired
	private ProductoService productoService;
	
	public ProductoMB() {
		
	}
	
	public void buscar(){
		try {
			System.out.println(this.getProducto());
			lstProducto= this.getProductoService().findByLikeObject(this.getProducto());
			
		/*	for (Producto producto : lstProducto) {
				System.out.println(producto);
			}*/
			
		} catch (ServicioExcepcion e) {
			e.printStackTrace();
		}
	}
	
	public String nuevo(){
		this.setProducto(new Producto());
		return "producto_registro";
	}
		
	public void grabar(){
		boolean sw;
		super.setAuditoria(this.getProducto());
		if (this.getProducto().getIdProducto()>0) {
			try {
				sw=this.getProductoService().update(this.getProducto());
				if (sw) {
					System.out.println("Exito de actualizaci�n");
				} else {
					System.out.println("Error de actualizaci�n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				sw=this.getProductoService().insert(this.getProducto());
				if (sw) {
					System.out.println("Exito de inserci�n");
				} else {
					System.out.println("Error de inserci�n");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String modificar(Producto producto){
		try {
			Producto oProducto= this.getProductoService().findByObject(producto);
			System.out.println("modificar " +oProducto);
			this.setProducto(oProducto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "producto_registro";
	}
	
	public void eliminar(Producto producto){
		try {
			boolean sw=this.getProductoService().delete(producto);
			if (sw) {
				System.out.println("Exito de eliminaci�n");
			} else {
				System.out.println("Error de eliminaci�n");
			}
			
		} catch (Exception e) {
			
		}
	}

	@PostConstruct
	public void inti(){
		this.setProducto(new Producto());
		this.setLstProducto(new ArrayList<Producto>());
	}
	
	public ProductoService getProductoService() {
		return productoService;
	}

	public void setProductoService(ProductoService productoService) {
		this.productoService = productoService;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<Producto> getLstProducto() {
		return lstProducto;
	}

	public void setLstProducto(List<Producto> lstProducto) {
		this.lstProducto = lstProducto;
	}

	
	public void exportExcel() {

	        try {

	            HttpServletResponse response = super.getResponse();

	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment; filename=Listado_Productoes.xls");

	            HSSFWorkbook workbook = new HSSFWorkbook();

	            if (lstProducto != null) {
	                workbook = exportExcelFormato(lstProducto);

	            }

	            OutputStream out = response.getOutputStream();
	            workbook.write(out);
	            out.close();
	            FacesContext.getCurrentInstance().responseComplete();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private HSSFWorkbook exportExcelFormato(List<Producto> lstProducto) {
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("Listado de Productos");

	        int countRow = 0;

	        // CABECERA
	        
	        Row row = sheet.createRow(countRow);

	        Cell cell = row.createCell(0);
	        cell.setCellValue("Item");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(1);
	        cell.setCellValue("Codigo");
	        super.setStyleLisCabecera(workbook, cell);

	        cell = row.createCell(2);
	        cell.setCellValue("Nombre");
	        super.setStyleLisCabecera(workbook, cell);
	        
	        cell = row.createCell(3);
	        cell.setCellValue("Decripcion");
	        super.setStyleLisCabecera(workbook, cell);
	        
	        cell = row.createCell(4);
	        cell.setCellValue("Precio");
	        super.setStyleLisCabecera(workbook, cell);
	        
	        cell = row.createCell(5);
	        cell.setCellValue("Stock");
	        super.setStyleLisCabecera(workbook, cell);
	        
	        
	        // LISTADO ( DETALLE)
	        
	        int item = 0;

	        for (Producto producto : lstProducto) {

	            countRow++;
	            item++;

	            row = sheet.createRow(countRow);

	            cell = row.createCell(0);
	            cell.setCellValue(item);

	            // CODIGO
	            cell = row.createCell(1);
	            cell.setCellValue(producto.getIdProducto());

	            // NOMBRE
	            cell = row.createCell(2);
	            cell.setCellValue(producto.getNombre());
	            
	            // DESCRIPCION
	            cell = row.createCell(3);
	            cell.setCellValue(producto.getDescripcion());
	            
	            // PRECIO
	            cell = row.createCell(4);
	            cell.setCellValue(producto.getPrecio());
	            
	            // STOCK
	            cell = row.createCell(5);
	            cell.setCellValue(producto.getStock());

	        }

	        return workbook;
	    }
	
	    public void exportPDF() {

	        HttpServletResponse httpServletResponse = super.getResponse();
	        //httpServletResponse.addHeader("Content-disposition", "attachment; filename="+this.getSijDocumentoVO().getNumeroNorma().replaceAll(" ","_")+".pdf");	
	        httpServletResponse.addHeader("Content-disposition", "attachment; filename=producto_listado_rpt.pdf");
	        ServletOutputStream servletStream;
	        try {
	            servletStream = httpServletResponse.getOutputStream();
	            JasperPrint jasperPrint = this.buildReport();
	            try {
	                JasperExportManager.exportReportToPdfStream(jasperPrint, servletStream);

	            } catch (JRException e) {
	                e.printStackTrace();
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        FacesContext.getCurrentInstance().responseComplete();
	    }

	    
	    @SuppressWarnings("unchecked")
		private JasperPrint buildReport() {
	        JasperPrint jasperPrint = null;
	        ServletContext sc=(ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext();
	        String path="/reportes/";
	        
	        String realPath = sc.getRealPath("/")+path;
	        String reporte = realPath + "producto_listado_rpt.jasper";
	        
	        @SuppressWarnings("rawtypes")
			Map map = new HashMap();
	        String pathSO=sc.getRealPath("/");
	        String logo =pathSO+ "resources/img/netun_ogo.png";
	        
	        map.put("prm_logo_izquierda", logo);
	        map.put("prm_usuario", "Lucas Carpio");
			map.put("prm_filtro", "Situacion: Bloqueados");
			map.put("prm_sige", "� 2017 - Sistema de Pedidos (SIPE) v1.0");

	        try {
	            jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(lstProducto));
	        } catch (JRException e) {
	            e.printStackTrace();
	        }

	        return jasperPrint;
	    }
	    
}

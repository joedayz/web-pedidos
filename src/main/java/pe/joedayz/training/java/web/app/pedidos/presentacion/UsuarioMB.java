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
import pe.joedayz.training.java.web.app.pedidos.entidad.Usuario;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.UsuarioService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;


@Controller("usuarioMB")
public class UsuarioMB extends GenericMB {
	
	private Usuario usuario;
	private List<Usuario> lstUsuario;
	
	@Autowired
	private UsuarioService usuarioService;
	
	public UsuarioMB() {
		
	}
	
	public void buscar(){
		try {
			System.out.println(this.getUsuario());
			lstUsuario= this.getUsuarioService().findByLikeObject(this.getUsuario());
			
		/*	for (Usuario Usuario : lstUsuario) {
				System.out.println(Usuario);
			}*/
			
		} catch (ServicioExcepcion e) {
			e.printStackTrace();
		}
	}
	
	public String nuevo(){
		this.setUsuario(new Usuario());
		return "usuario_registro";
	}
		
	public void grabar(){
		boolean sw;
		super.setAuditoria(this.getUsuario());
		if (this.getUsuario().getIdUsuario()>0) {
			try {
				sw=this.getUsuarioService().update(this.getUsuario());
				if (sw) {
					System.out.println("Exito de actualizaci�n");
				} else {
					System.out.println("Error de actualizaci�n");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else{
			try {
				sw=this.getUsuarioService().insert(this.getUsuario());
				if (sw) {
					System.out.println("Exito de inserci�n");
				} else {
					System.out.println("Error de inserci�n");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	
	public String modificar(Usuario usuario){
		try {
			Usuario oUsuario= this.getUsuarioService().findByObject(usuario);
			this.setUsuario(oUsuario);
		} catch (Exception e) {
			
		}
		return "usuario_registro";
	}
	
	public void eliminar(Usuario usuario){
		try {
			boolean sw=this.getUsuarioService().delete(usuario);
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
		this.setUsuario(new Usuario());
		this.setLstUsuario(new ArrayList<Usuario>());
	}
	
	

	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getLstUsuario() {
		return lstUsuario;
	}

	public void setLstUsuario(List<Usuario> lstUsuario) {
		this.lstUsuario = lstUsuario;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public void exportExcel() {

	        try {

	            HttpServletResponse response = super.getResponse();

	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment; filename=Listado_Usuarioes.xls");

	            HSSFWorkbook workbook = new HSSFWorkbook();

	            if (lstUsuario != null) {
	                workbook = exportExcelFormato(lstUsuario);

	            }

	            OutputStream out = response.getOutputStream();
	            workbook.write(out);
	            out.close();
	            FacesContext.getCurrentInstance().responseComplete();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private HSSFWorkbook exportExcelFormato(List<Usuario> lstUsuario) {
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("Listado de Usuarios");

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

	        // LISTADO ( DETALLE)
	        
	        int item = 0;

	        for (Usuario Usuario : lstUsuario) {

	            countRow++;
	            item++;

	            row = sheet.createRow(countRow);

	            cell = row.createCell(0);
	            cell.setCellValue(item);

	            // CODIGO
	            cell = row.createCell(1);
	            cell.setCellValue(Usuario.getIdUsuario());

	            // NOMBRE
	            cell = row.createCell(2);
	            cell.setCellValue(Usuario.getNombre());

	        }

	        return workbook;
	    }
	
	    public void exportPDF() {

	        HttpServletResponse httpServletResponse = super.getResponse();
	        //httpServletResponse.addHeader("Content-disposition", "attachment; filename="+this.getSijDocumentoVO().getNumeroNorma().replaceAll(" ","_")+".pdf");	
	        httpServletResponse.addHeader("Content-disposition", "attachment; filename=Usuario_listado_rpt.pdf");
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
	        String path="WEB-INF\\classes\\pe\\edu\\galaxy\\training\\java\\web\\app\\pedidos\\presentacion\\reportes\\";
	        
	        String realPath = sc.getRealPath("/")+path;
	        String reporte = realPath + "usuario_listado_rpt.jasper";
	        
	        @SuppressWarnings("rawtypes")
			Map map = new HashMap();
	        String pathSO=sc.getRealPath("/");
	        String logo =pathSO+ "resources\\img\\galaxy-training-logo.png";
	        
	        map.put("prm_logo_izquierda", logo);
	        map.put("prm_usuario", "Lucas Carpio");
			map.put("prm_filtro", "Situacion: Bloqueados");
			map.put("prm_sige", "� 2017 - Sistema de Pedidos (SIPE) v1.0");

	        try {
	            jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(lstUsuario));
	        } catch (JRException e) {
	            e.printStackTrace();
	        }

	        return jasperPrint;
	    }
	    
}

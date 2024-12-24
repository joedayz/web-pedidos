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
import pe.joedayz.training.java.web.app.pedidos.entidad.Perfil;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.PerfilService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;


@Controller("perfilMB")
public class PerfilMB extends GenericMB {
	
	private Perfil perfil;
	private List<Perfil> lstPerfil;
	
	@Autowired
	private PerfilService perfilService;
	
	public PerfilMB() {
		
	}
	
	public void buscar(){
		try {
			System.out.println(this.getPerfil());
			lstPerfil= this.getPerfilService().findByLikeObject(this.getPerfil());
			
		/*	for (Perfil perfil : lstPerfil) {
				System.out.println(perfil);
			}*/
			
		} catch (ServicioExcepcion e) {
			e.printStackTrace();
		}
	}
	
	public String nuevo(){
		this.setPerfil(new Perfil());
		return "perfil_registro";
	}
		
	public void grabar(){
		boolean sw;
		super.setAuditoria(this.getPerfil());
		if (this.getPerfil().getIdPerfil()>0) {
			try {
				sw=this.getPerfilService().update(this.getPerfil());
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
				sw=this.getPerfilService().insert(this.getPerfil());
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
	
	public String modificar(Perfil perfil){
		try {
			Perfil oPerfil= this.getPerfilService().findByObject(perfil);
			this.setPerfil(oPerfil);
		} catch (Exception e) {
			
		}
		return "perfil_registro";
	}
	
	public void eliminar(Perfil perfil){
		try {
			boolean sw=this.getPerfilService().delete(perfil);
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
		this.setPerfil(new Perfil());
		this.setLstPerfil(new ArrayList<Perfil>());
	}
	
	public PerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Perfil> getLstPerfil() {
		return lstPerfil;
	}

	public void setLstPerfil(List<Perfil> lstPerfil) {
		this.lstPerfil = lstPerfil;
	}

	
	public void exportExcel() {

	        try {

	            HttpServletResponse response = super.getResponse();

	            response.setContentType("application/vnd.ms-excel");
	            response.setHeader("Content-Disposition", "attachment; filename=Listado_Perfiles.xls");

	            HSSFWorkbook workbook = new HSSFWorkbook();

	            if (lstPerfil != null) {
	                workbook = exportExcelFormato(lstPerfil);

	            }

	            OutputStream out = response.getOutputStream();
	            workbook.write(out);
	            out.close();
	            FacesContext.getCurrentInstance().responseComplete();

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    private HSSFWorkbook exportExcelFormato(List<Perfil> lstPerfil) {
	        HSSFWorkbook workbook = new HSSFWorkbook();
	        HSSFSheet sheet = workbook.createSheet("Listado de Perfiles");

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

	        for (Perfil perfil : lstPerfil) {

	            countRow++;
	            item++;

	            row = sheet.createRow(countRow);

	            cell = row.createCell(0);
	            cell.setCellValue(item);

	            // CODIGO
	            cell = row.createCell(1);
	            cell.setCellValue(perfil.getIdPerfil());

	            // NOMBRE
	            cell = row.createCell(2);
	            cell.setCellValue(perfil.getNombre());

	        }

	        return workbook;
	    }
	
	    public void exportPDF() {

	        HttpServletResponse httpServletResponse = super.getResponse();
	        //httpServletResponse.addHeader("Content-disposition", "attachment; filename="+this.getSijDocumentoVO().getNumeroNorma().replaceAll(" ","_")+".pdf");	
	        httpServletResponse.addHeader("Content-disposition", "attachment; filename=perfil_listado_rpt.pdf");
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
	        String reporte = realPath + "perfil_listado_rpt.jasper";
	        
	        @SuppressWarnings("rawtypes")
			Map map = new HashMap();
	        String pathSO=sc.getRealPath("/");
	        String logo =pathSO+ "resources/img/netun_logo.png";
	        
	        map.put("prm_logo_izquierda", logo);
	        map.put("prm_usuario", "Lucas Carpio");
			map.put("prm_filtro", "Situacion: Bloqueados");
			map.put("prm_sige", "� 2017 - Sistema de Pedidos (SIPE) v1.0");

	        try {
	            jasperPrint = JasperFillManager.fillReport(reporte, map, new JRBeanCollectionDataSource(lstPerfil));
	        } catch (JRException e) {
	            e.printStackTrace();
	        }

	        return jasperPrint;
	    }
	    
}

package pe.joedayz.training.java.web.app.pedidos.presentacion;


import java.io.IOException;
import java.util.Properties;


import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import pe.joedayz.training.java.web.app.pedidos.entidad.GenericEntidad;
import pe.joedayz.training.java.web.app.pedidos.entidad.Usuario;
import pe.joedayz.training.java.web.app.pedidos.utilitario.Net;
/**
 @author josediaz   
 **/
public class GenericMB {
  private final String FILE_NAME_RESOURCE="config.properties";

  public GenericMB() {

  }

  protected void setAuditoria(GenericEntidad ge){
    // Asignar usuario
    HttpSession session= this.getRequest().getSession();
    if (session!=null) {
      Object obj=session.getAttribute("usuario");
      if (obj!=null) {
        Usuario usuario=(Usuario)obj;
        ge.setAudIdUsuario(usuario.getIdUsuario());
      }
      Object ID= session.getAttribute("ID");
      if (ID!=null) {
        ge.setAudSesion(ID.toString());
      }
    }

    // IP
    String IP= Net.getClientIPAddres(this.getRequest());
    ge.setAudIP(IP);

  }


  protected void msgInfo(String msg){
    FacesMessage message = null;
    message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", msg);
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  protected void msgAlert(String msg){
    FacesMessage message = null;
    message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", msg);
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  protected void msgError(String msg){
    FacesMessage message = null;
    message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", msg);
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  protected HttpServletRequest getRequest(){
    FacesContext fctx = FacesContext.getCurrentInstance();
    ExternalContext ectx = fctx.getExternalContext();
    HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
    return request;
  }

  protected HttpServletResponse getResponse(){
    FacesContext fctx = FacesContext.getCurrentInstance();
    ExternalContext ectx = fctx.getExternalContext();
    HttpServletResponse response = (HttpServletResponse) ectx.getResponse();
    return response;
  }

  /*
   * Formato de Exportaci�n de Excel
   */

  protected void setStyleFormat(HSSFWorkbook workbook, Cell cell) {
    HSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 10);
    font.setFontName("Arial");
    font.setColor(IndexedColors.BLACK.getIndex());
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    font.setItalic(false);

    CellStyle newCellStyle = workbook.createCellStyle();
    newCellStyle.cloneStyleFrom(cell.getCellStyle());

//		newCellStyle.setFillBackgroundColor(IndexedColors.DARK_BLUE.getIndex());
//		newCellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
    newCellStyle.setAlignment(CellStyle.ALIGN_LEFT);
    newCellStyle.setFont(font);

    cell.setCellStyle(newCellStyle);
  }

  protected void setStyleLisCabecera(HSSFWorkbook workbook, Cell cell) {
    HSSFFont font = workbook.createFont();
    font.setFontHeightInPoints((short) 10);
    font.setFontName("Arial");
    font.setColor(IndexedColors.WHITE.getIndex());
    font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
    font.setItalic(false);

    CellStyle newCellStyle = workbook.createCellStyle();
    newCellStyle.cloneStyleFrom(cell.getCellStyle());

    newCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
    newCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    newCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
    newCellStyle.setBorderTop((short) 1); // single line border
    newCellStyle.setBorderBottom((short) 1); // single line border
    newCellStyle.setBorderRight((short) 1);
    newCellStyle.setBorderLeft((short) 1);
    newCellStyle.setFont(font);

    cell.setCellStyle(newCellStyle);
  }


  protected String getStringJSF(String key) {
    ClassLoader cl = Thread.currentThread().getContextClassLoader();
    Properties properties = new Properties();
    try {
      properties.load(cl.getResourceAsStream(FILE_NAME_RESOURCE));
      return properties.getProperty(key);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return "";
  }
}

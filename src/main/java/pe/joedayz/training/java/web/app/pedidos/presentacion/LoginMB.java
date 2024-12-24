package pe.joedayz.training.java.web.app.pedidos.presentacion;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import pe.joedayz.training.java.web.app.pedidos.entidad.Usuario;
import pe.joedayz.training.java.web.app.pedidos.servicio.especifico.inf.UsuarioService;
import pe.joedayz.training.java.web.app.pedidos.servicio.excepcion.ServicioExcepcion;
import pe.joedayz.training.java.web.app.pedidos.utilitario.Encrypt;


@Controller("loginMB")
@Scope(value="session")
public class LoginMB extends GenericMB{

  @Autowired
  private UsuarioService usuarioService;

  private Usuario usuario;

  public LoginMB() {

  }

  @PostConstruct
  public void init(){
    this.setUsuario(new Usuario());
  }

  public String validarAcceso(){
    String page="index";
    String key=super.getStringJSF("Encrypt.key");
    Encrypt.init(key);

    try {
      String claveEncripatada=Encrypt.encrypt(this.getUsuario().getClave());
      System.err.println("claveEncripatada "+claveEncripatada);
      this.getUsuario().setClave(claveEncripatada);
      try {
        Usuario oUsuario=this.getUsuarioService().validarAcceso(this.getUsuario());
        if (oUsuario!=null){
          super.msgInfo("Bienvenido"+ oUsuario.getNombre());

          HttpSession session = super.getRequest().getSession(true);
          session.setAttribute("ID", session.getId());
          session.setAttribute("usuario",oUsuario);
          page="panel";
        }else{
          super.msgAlert("Usuario y/o clave incorrecto");
        }

      } catch (ServicioExcepcion e) {
        this.msgError("Error al validar usuario " + e.getMessage());
        e.printStackTrace();
      }

    } catch (UnsupportedEncodingException e1) {
      e1.printStackTrace();
    } catch (GeneralSecurityException e1) {
      e1.printStackTrace();
    }

    return page;
  }

  public String cerrarSesion() {

    HttpSession session = super.getRequest().getSession();
    session.removeAttribute("ID");
    session.removeAttribute("usuario");
    session.invalidate();

    return "index";
  }

  public Usuario getUsuarioActivo(){
    Usuario oUsuario=new Usuario();
    HttpSession session= super.getRequest().getSession();
    if (session!=null) {
      Object obj=session.getAttribute("usuario");
      if (obj!=null) {
        oUsuario=(Usuario)obj;
      }
    }
    return oUsuario;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public UsuarioService getUsuarioService() {
    return usuarioService;
  }

  public void setUsuarioService(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

}

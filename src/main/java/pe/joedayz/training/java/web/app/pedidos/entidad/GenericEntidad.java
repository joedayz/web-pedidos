package pe.joedayz.training.java.web.app.pedidos.entidad;

import java.io.Serializable;

import javax.persistence.Column;

public class GenericEntidad implements Serializable{

  private static final long serialVersionUID = 1L;

  @Column(name="ESTADO")
  private String estado;

  // Auditoria

  @Column(name="AUD_TIPO")
  private String audTipo;

  @Column(name="AUD_IDUSUARIO")
  private Integer audIdUsuario;

  @Column(name="AUD_SESION")
  private String audSesion;

  @Column(name="AUD_IP")
  private String audIP;


  // Constructor predeterminado
  public GenericEntidad() {
    super();
  }

  // Getteres/Setters
  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public String getAudTipo() {
    return audTipo;
  }

  public void setAudTipo(String audTipo) {
    this.audTipo = audTipo;
  }

  public Integer getAudIdUsuario() {
    return audIdUsuario;
  }

  public void setAudIdUsuario(Integer audIdUsuario) {
    this.audIdUsuario = audIdUsuario;
  }

  public String getAudSesion() {
    return audSesion;
  }

  public void setAudSesion(String audSesion) {
    this.audSesion = audSesion;
  }

  public String getAudIP() {
    return audIP;
  }

  public void setAudIP(String audIP) {
    this.audIP = audIP;
  }



  // toString()

}


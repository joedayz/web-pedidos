package pe.joedayz.training.java.web.app.pedidos.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

@NamedStoredProcedureQueries(
    {
        @NamedStoredProcedureQuery(
            name="usuario.validarAcceso",
            procedureName="PKG_USUARIO.SP_VALIDAR_ACCESO",
            resultClasses= Usuario.class,
            parameters={
                @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_C_CURSOR", type=void.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_USUARIO", type=String.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_CLAVE", type=String.class)
            }
        )
    }
)

@Entity
public class Usuario extends GenericEntidad {

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name="ID_USUARIO")
  private int 	idUsuario;

  @Column(name="USUARIO")
  private String 	usuario;

  @Column(name="CLAVE")
  private String 	clave;

  @Column(name="NOMBRE")
  private String 	nombre;

  private Perfil  perfil;	// Relaci�n de 1

  public Usuario() {
    super();
    this.init(); // Llamada 1
  }

  public Usuario(String usuario, String clave) {
    super();
    this.usuario = usuario;
    this.clave = clave;
    this.init();// Llamada 2
  }


  private void init(){
    this.setPerfil(new Perfil());
  }

  public int getIdUsuario() {
    return idUsuario;
  }

  public void setIdUsuario(int idUsuario) {
    this.idUsuario = idUsuario;
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getClave() {
    return clave;
  }

  public void setClave(String clave) {
    this.clave = clave;
  }

  public Perfil getPerfil() {
    return perfil;
  }

  public void setPerfil(Perfil perfil) {
    this.perfil = perfil;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  @Override
  public String toString() {
    return "Usuario [idUsuario=" + idUsuario + ", usuario=" + usuario
        + ", clave=" + clave + ", nombre=" + nombre + ", perfil="
        + perfil + "]";
  }

}
package pe.joedayz.training.java.web.app.pedidos.entidad;

import java.util.ArrayList;
import java.util.List;
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
            name="perfil.findByLikeObject",
            procedureName="PKG_PERFIL.SP_BUSCARXCRITERIOS",
            resultClasses= Perfil.class,
            parameters={
                @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_C_CURSOR", type=void.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class)
            }
        ),
        @NamedStoredProcedureQuery(
            name="perfil.findByObject",
            procedureName="PKG_PERFIL.SP_BUSCARXID",
            resultClasses= Perfil.class,
            parameters={
                @StoredProcedureParameter(mode=ParameterMode.REF_CURSOR,name="P_C_CURSOR", type=void.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PERFIL", type=Integer.class)
            }
        ),
        @NamedStoredProcedureQuery(
            name="perfil.insert",
            procedureName="PKG_PERFIL.SP_INSERTAR",
            parameters={
                @StoredProcedureParameter(mode=ParameterMode.OUT, name="P_ID_PERFIL", type=Integer.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),

                // Auditoria
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_TIPO", type=String.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Integer.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)

            }
        ),
        @NamedStoredProcedureQuery(
            name="perfil.update",
            procedureName="PKG_PERFIL.SP_ACTUALIZAR",
            parameters={
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PERFIL", type=Integer.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_NOMBRE", type=String.class),

                // Auditoria
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_TIPO", type=String.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Integer.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)

            }
        ),
        @NamedStoredProcedureQuery(
            name="perfil.delete",
            procedureName="PKG_PERFIL.SP_ELIMINAR",
            parameters={
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_ID_PERFIL", type=Integer.class),

                // Auditoria
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_TIPO", type=String.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IDUSUARIO", type=Integer.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_SESION", type=String.class),
                @StoredProcedureParameter(mode=ParameterMode.IN,  name="P_AUD_IP", type=String.class)

            }
        )
    }
)

@Entity
public class Perfil extends GenericEntidad{

  private static final long serialVersionUID = 1L;

  @Id
  @Column(name="ID_PERFIL")
  private int idPerfil;

  @Column(name="NOMBRE")
  private String nombre;

  private List<Usuario> usuarios;

  public Perfil() {
    super();
    this.init();
  }

  public Perfil(int idPerfil, String nombre) {
    super();
    this.idPerfil = idPerfil;
    this.nombre = nombre;
    this.init();
  }

  private void init(){
    this.setUsuarios(new ArrayList<Usuario>());
  }

  public int getIdPerfil() {
    return idPerfil;
  }

  public void setIdPerfil(int idPerfil) {
    this.idPerfil = idPerfil;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }



  public List<Usuario> getUsuarios() {
    return usuarios;
  }

  public void setUsuarios(List<Usuario> usuarios) {
    this.usuarios = usuarios;
  }
  @Override
  public String toString() {
    return "Perfil [idPerfil=" + idPerfil + ", nombre=" + nombre + "]";
  }
}

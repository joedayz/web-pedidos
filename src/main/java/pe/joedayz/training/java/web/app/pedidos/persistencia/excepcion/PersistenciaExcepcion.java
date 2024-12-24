package pe.joedayz.training.java.web.app.pedidos.persistencia.excepcion;

public class PersistenciaExcepcion extends Exception {

  private static final long serialVersionUID = 1L;

  public PersistenciaExcepcion() {

  }
  public PersistenciaExcepcion(String arg0) {
    super(arg0);
  }

  public PersistenciaExcepcion(Throwable arg0) {
    super(arg0);
  }

  public PersistenciaExcepcion(String arg0, Throwable arg1) {
    super(arg0, arg1);
  }

  public PersistenciaExcepcion(String arg0, Throwable arg1, boolean arg2,
      boolean arg3) {
    super(arg0, arg1, arg2, arg3);
  }

}

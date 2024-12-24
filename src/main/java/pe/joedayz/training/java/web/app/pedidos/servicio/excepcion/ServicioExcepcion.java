package pe.joedayz.training.java.web.app.pedidos.servicio.excepcion;

public class ServicioExcepcion extends Exception{

  private static final long serialVersionUID = 1L;

  public ServicioExcepcion() {

  }

  public ServicioExcepcion(Throwable cause) {
    super(cause);

  }

  public ServicioExcepcion(String message) {
    super(message);
  }

  public ServicioExcepcion(String message, Throwable cause) {
    super(message, cause);
  }

  public ServicioExcepcion(String message, Throwable cause,
      boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

}

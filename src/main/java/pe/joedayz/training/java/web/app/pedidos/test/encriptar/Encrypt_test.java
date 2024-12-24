package pe.joedayz.training.java.web.app.pedidos.test.encriptar;


import pe.joedayz.training.java.web.app.pedidos.utilitario.Encrypt;

public class Encrypt_test {

	public Encrypt_test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
	
		Encrypt.init("J4V4...***");
		
		try {
			System.out.println(Encrypt.encrypt("ABC"));
			//lbde4Xna1tQ=
			//blHhKOiUJ+E=
			//qcRfipp8yBo=
			
		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}

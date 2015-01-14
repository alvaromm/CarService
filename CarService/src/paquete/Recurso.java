package paquete;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//Esta clase solo devuelve una instancia de la clase Todo
@Path("/todo")
public class Recurso {


	//Lo que sigue se puede utilizar para comprobar la integracion con el navegador que utilices
	@GET
	@Produces({ MediaType.TEXT_XML })
	public Coche getHTML() {
		Coche coche = new Coche();
		coche.setCaballos (265);
		coche.setCilindros(6);
		coche.setLongitud (189);
		coche.setMarca    ("Acura");
		coche.setModelo   ("MDX");
		coche.setOrigen   ("Asiatico");
		coche.setPeso     (4451);
		coche.setPrecio   (36945);
		coche.setTamMotor (3.5);
		coche.setTipo     ("SUV");
		coche.setTraccion ("All");
		
		//CocheDB.existeCoche("MDX");
		//CocheDB.insertar(coche);
		CocheDB.mostrarBD();
		return coche;
	}

}

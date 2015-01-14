package paquete;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

//Esta clase solo devuelve una instancia de la clase coche
@Path("/coches")
public class Recurso {


	/**
	 * 
	 * @return Devuelve la lista de los coches que hay en formato XML.
	 */
	@GET
	@Produces({ MediaType.TEXT_XML })
	public List<Coche> getCochesXML() {
		List<Coche> listaCoches = new ArrayList<Coche>();
		
		listaCoches.addAll(CochesDao.instance.getModel().values());
//		Coche coche = new Coche();
//		Coche coche2 = new Coche("Acura", "RSX Type S 2dr", "Sedan", "Asia", "Front", 23820, 2, 4, 200, 2778, 172);
//		coche.setCaballos (265);
//		coche.setCilindros(6);
//		coche.setLongitud (189);
//		coche.setMarca    ("Acura");
//		coche.setModelo   ("MDX");
//		coche.setOrigen   ("Asiatico");
//		coche.setPeso     (4451);
//		coche.setPrecio   (36945);
//		coche.setTamMotor (3.5);
//		coche.setTipo     ("SUV");
//		coche.setTraccion ("All");
		
//		CocheDB.existeCoche("MDX");
//		CocheDB.insertar(coche);
//		CocheDB.insertar(coche2);
//		CocheDB.mostrarBD();
		return listaCoches;
	}
	
	/**
	 *  
	 * @return Devolvera la lista de los "coches" en las aplicaciones.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Coche> getCoches() {
		List<Coche> coches = new ArrayList<Coche>();
		coches.addAll(CochesDao.instance.getModel().values());
		return coches;
	}
	
	/**
	 *  Devuelve el numero de "coches" que hay.
	 * 		Utilizar http://localhost:8080/mio.com.jersey.todo/rest/todos/count
	 * 		para obtener el numero total de registros
	 */
	@GET
	@Path("/numCoches")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		int count = CochesDao.instance.getModel().size();
		return String.valueOf(count);
	}
	
	/**
	 * Este método se encarga de crear un nuevo coche.
	 * @param id
	 * @param nombre
	 * @param apellidos
	 * @param servletResponse
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void newCoche(
			@FormParam("precio")    int 	precio,  
			@FormParam("cilindros") int 	cilindros,  
			@FormParam("caballos")  int 	caballos,  
			@FormParam("tamMotor") double 	tamMotor,  
			@FormParam("peso")     double 	peso,   
			@FormParam("longitud") double 	longitud,  
			@FormParam("marca")    String 	marca,  	 
			@FormParam("modelo")   String 	modelo,   
			@FormParam("tipo")     String 	tipo,  	 
			@FormParam("origen")   String 	origen,   
			@FormParam("traccion") String 	traccion,
			@Context HttpServletResponse servletResponse) throws IOException {
		Coche coche = new Coche(marca, modelo, tipo, origen, traccion, precio, tamMotor, cilindros, caballos, peso, longitud);

		//CochesDao.instance.getModel().put(id, coche);

		//servletResponse.sendRedirect("../crear_coche.html");
		System.out.println(coche);
		//System.out.println("Se ha enviado un coche: " + nombre + " | " + apellidos + " | ");
	}
	

}

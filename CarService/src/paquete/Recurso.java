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
	@Path("/xml")
	@Produces({ MediaType.TEXT_XML })
	public static List<Coche> getCochesXML() {
		List<Coche> listaCoches = new ArrayList<Coche>();
		
		listaCoches.addAll(CochesDao.instance.getModel().values());
//		Coche coche = new Coche();
		Coche coche2 = new Coche("Acura", "RSX Type S 2dr", "Sedan", "Asia", "Front", 23820, 2, 4, 200, 2778, 172);
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

		CocheDB.insertar(coche2);
		System.out.println("pene");
//		CocheDB.mostrarBD();
		return listaCoches;
	}
	
	/**
	 *  
	 * @return Devolvera la lista de los "coches" en las aplicaciones.
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public static List<Coche> getCoches() {
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
	public static String getCount() {
		int count = CochesDao.instance.getModel().size();
		return String.valueOf(count);
	}
	
	/**
	 * Este m�todo se encarga de crear un nuevo coche.
	 * @param precio : Precio del coche.
	 * @param cilindros : N�mero de cilindros que tiene el coche.
	 * @param caballos : Caballos del coche.
	 * @param tamMotor : Tama�o del motor del coche.
	 * @param peso : Peso del coche en kg.
	 * @param longitud : Longitud del coche en m.
	 * @param marca : Marca del coche.
	 * @param modelo : Modelo del coche.
	 * @param tipo : Tipo de coche 'turismo', ...
	 * @param origen : Origen del coche.
	 * @param traccion : Tracci�n del coche 'delantera', ...
	 * @param servletResponse : Servlet que nos va a proporcionar la respesta.
	 * @throws IOException : Lanza una excepci�n si existe alg�n problema de entrada/salida de datos.
	 */
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public static void newCoche(
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
		
		// Creamos el objeto coche.
		Coche coche = new Coche(marca, modelo, tipo, origen, traccion, precio, tamMotor, cilindros, caballos, peso, longitud);

		//servletResponse.sendRedirect("../crear_coche.html");
		System.out.println(coche);
		
		// Insertamos el coche en la base de datos.
		CocheDB.insertar(coche);
	}
	
	@POST
	@Path("/eliminarCoche")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public static void eliminarCoche(
		@FormParam("param") String 	id,
		@Context HttpServletResponse servletResponse) throws IOException {
	
		// Creamos el objeto coche.
		CocheDB.eliminar(CocheDB.getCoche(Integer.parseInt(id)));
		System.out.println("Id del coche eliminado: " + id);
		servletResponse.sendRedirect("../../mostrarTablaCoches.jsp");
	}
	
	
	@POST
	@Path("/modificarCoche")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public static void modificarCoche(
		@FormParam("id") 		int 	id,
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
			// Creamos el objeto coche.
			Coche c = new Coche();
			
			c = CocheDB.getCoche(id);
			c.setPrecio(precio);
			c.setCilindros(cilindros);
			c.setCaballos(caballos);
			c.setTamMotor(tamMotor);
			c.setPeso(peso);
			c.setLongitud(longitud);
			c.setMarca(marca);
			c.setModelo(modelo);
			c.setTipo(tipo);
			c.setOrigen(origen);
			c.setTraccion(traccion);
			
			CocheDB.actualizar(id, c);
			
			System.out.println("Id del coche modificado: " + id);
		
		servletResponse.sendRedirect("../../mostrarTablaCoches.jsp");
	}
	
	
	/**
	 * 
	 * @return Devuelve el inventario de coches para mostrarlo con HTML.
	 */
	@GET
	@Path("/getInventario")
	@Produces({MediaType.TEXT_HTML})
	public static String getInventarioHTML() {
		String inventarioHTML = "";
		List<Coche> lc;
		
		// Obtenemos la lista de los coches.
		lc = CocheDB.getCoches();		
		
		// Ahora insertamos cada uno de los coches que hay.
		for(Coche c : lc) {
			// Inicio de linea.
			inventarioHTML = inventarioHTML + "<tr>";
			// Cada uno de los atributos.
			inventarioHTML = inventarioHTML
					//+ "<td>" + c.getCarId() 	+ 	"</td>"
					+ "<td>" + c.getMarca() 	+   "</td>"
			        + "<td>" + c.getModelo() 	+   "</td>"
			        + "<td>" + c.getTipo() 		+   "</td>"
			        + "<td>" + c.getOrigen() 	+   "</td>"
			        + "<td>" + c.getTraccion() 	+   "</td>"
					+ "<td>" + Integer.toString(c.getPrecio() 	) +   "</td>"
			        + "<td>" + Integer.toString(c.getCilindros()) +   "</td>"
			        + "<td>" + Integer.toString(c.getCaballos()	) +    "</td>"
			        + "<td>" + Double.toString(c.getTamMotor()	) +    "</td>"
			        + "<td>" + Double.toString(c.getPeso()		) +    "</td>"
			        + "<td>" + Double.toString(c.getLongitud() )  +    "</td>" 
			        + "<td><form action=\"rest/coches/eliminarCoche\" method=\"POST\">"
			        + "<input type=\"hidden\" name=\"param\" value=\""+c.getCarId()+"\" />"
			        + "<input class=\"button\" type=\"submit\" value=\"Eliminar\" />"
			        + "</form>"
			        + "<form action=\"modificarCoche.jsp\" method=\"POST\">"
			        + "<input type=\"hidden\" name=\"param\" value=\""+c.getCarId()+"\" />"
			        + "<input class=\"button\" type=\"submit\" value=\"Modificar\" />"
			        + "</form></td>";
			// Final de l�nea.
			inventarioHTML += "</tr>";
		}
		
		return inventarioHTML;
	}
	

}

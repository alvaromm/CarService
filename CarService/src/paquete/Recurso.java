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
	 * @param precio : Precio del coche.
	 * @param cilindros : Número de cilindros que tiene el coche.
	 * @param caballos : Caballos del coche.
	 * @param tamMotor : Tamaño del motor del coche.
	 * @param peso : Peso del coche en kg.
	 * @param longitud : Longitud del coche en m.
	 * @param marca : Marca del coche.
	 * @param modelo : Modelo del coche.
	 * @param tipo : Tipo de coche 'turismo', ...
	 * @param origen : Origen del coche.
	 * @param traccion : Tracción del coche 'delantera', ...
	 * @param servletResponse : Servlet que nos va a proporcionar la respesta.
	 * @throws IOException : Lanza una excepción si existe algún problema de entrada/salida de datos.
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
		
		// Creamos el objeto coche.
		Coche coche = new Coche(marca, modelo, tipo, origen, traccion, precio, tamMotor, cilindros, caballos, peso, longitud);

		//servletResponse.sendRedirect("../crear_coche.html");
		System.out.println(coche);
		
		// Insertamos el coche en la base de datos.
		CocheDB.insertar(coche);
	}
	
	/**
	 * 
	 * @return Devuelve el inventario de coches para mostrarlo con HTML.
	 */
	@GET
	@Path("/getInventario")
	@Produces({MediaType.TEXT_HTML})
	public String getInventarioHTML() {
		String inventarioHTML = "";
		List<Coche> lc;
		
		// Obtenemos la lista de los coches.
		lc = CocheDB.getCoches();
		
		// Cabecera del HTML.
		inventarioHTML = "<!DOCTYPE html>"
				+ "<html><head><meta charset=\"UTF-8\">"
				+ "<style type=\"text/css\">"					// Estilo de la página.
				+ ".EstiloTabla {     margin:0px;padding:0px;     width:100%;     box-shadow: 10px 10px 5px #888888;     border:1px solid #000000;          -moz-border-radius-bottomleft:0px;     -webkit-border-bottom-left-radius:0px;     border-bottom-left-radius:0px;          -moz-border-radius-bottomright:0px;     -webkit-border-bottom-right-radius:0px;     border-bottom-right-radius:0px;          -moz-border-radius-topright:0px;     -webkit-border-top-right-radius:0px;     border-top-right-radius:0px;          -moz-border-radius-topleft:0px;     -webkit-border-top-left-radius:0px;     border-top-left-radius:0px; }.EstiloTabla table{     border-collapse: collapse;         border-spacing: 0;     width:100%;     height:100%;     margin:0px;padding:0px; }.EstiloTabla tr:last-child td:last-child {     -moz-border-radius-bottomright:0px;     -webkit-border-bottom-right-radius:0px;     border-bottom-right-radius:0px; } .EstiloTabla table tr:first-child td:first-child {     -moz-border-radius-topleft:0px;     -webkit-border-top-left-radius:0px;     border-top-left-radius:0px; } .EstiloTabla table tr:first-child td:last-child {     -moz-border-radius-topright:0px;     -webkit-border-top-right-radius:0px;     border-top-right-radius:0px; }.EstiloTabla tr:last-child td:first-child{     -moz-border-radius-bottomleft:0px;     -webkit-border-bottom-left-radius:0px;     border-bottom-left-radius:0px; }.EstiloTabla tr:hover td{      } .EstiloTabla tr:nth-child(odd){ background-color:#aaffff; } .EstiloTabla tr:nth-child(even)    { background-color:#ffffff; }.EstiloTabla td{     vertical-align:middle;               border:1px solid #000000;     border-width:0px 1px 1px 0px;     text-align:left;     padding:7px;     font-size:10px;     font-family:Arial;     font-weight:normal;     color:#000000; }.EstiloTabla tr:last-child td{     border-width:0px 1px 0px 0px; }.EstiloTabla tr td:last-child{     border-width:0px 0px 1px 0px; }.EstiloTabla tr:last-child td:last-child{     border-width:0px 0px 0px 0px; } .EstiloTabla tr:first-child td{         background:-o-linear-gradient(bottom, #00ffff 5%, #0096bc 100%);    background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #00ffff), color-stop(1, #0096bc) );     background:-moz-linear-gradient( center top, #00ffff 5%, #0096bc 100% );     filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#00ffff\", endColorstr=\"#0096bc\");  background: -o-linear-gradient(top,#00ffff,0096bc);      background-color:#00ffff;     border:0px solid #000000;     text-align:center;     border-width:0px 0px 1px 1px;     font-size:14px;     font-family:Arial;     font-weight:bold;     color:#ffffff; } .EstiloTabla tr:first-child:hover td{     background:-o-linear-gradient(bottom, #00ffff 5%, #0096bc 100%);    background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #00ffff), color-stop(1, #0096bc) );     background:-moz-linear-gradient( center top, #00ffff 5%, #0096bc 100% );     filter:progid:DXImageTransform.Microsoft.gradient(startColorstr=\"#00ffff\", endColorstr=\"#0096bc\");  background: -o-linear-gradient(top,#00ffff,0096bc);      background-color:#00ffff; } .EstiloTabla tr:first-child td:first-child{     border-width:0px 0px 1px 0px; } .EstiloTabla tr:first-child td:last-child{     border-width:0px 0px 1px 1px; }"
				+ "</style>"
				+ "<div class=\"EstiloTabla\" >"
				+ "   <table>";
		// Ahora insertamos los elementos de la tabla.
		// Insetamos la primera fila que tiene cada uno de los nombres de los atributos.
		inventarioHTML = inventarioHTML + "<tr>";
		inventarioHTML = inventarioHTML
				+ "<td>Id </td>        "
				+ "<td>Marca </td>      "
		        + "<td>Modelo </td>     "
		        + "<td>Tipo </td>       "
		        + "<td>Origen </td>     "
		        + "<td>Tracción </td>   "
				+ "<td>Precio </td>    "
		        + "<td>Cilindros </td> "
		        + "<td>Caballos </td>  "
		        + "<td>Tamaño motor </td>  "
		        + "<td>Peso </td>      "
		        + "<td>Longitud </td>  ";
		inventarioHTML = inventarioHTML + "</tr>";
		
		// Ahora insertamos cada uno de los coches que hay.
		for(Coche c : lc) {
			// Inicio de linea.
			inventarioHTML = inventarioHTML + "<tr>";
			// Cada uno de los atributos.
			inventarioHTML = inventarioHTML
					+ "<td>" + c.getCarId() 	+ 	"</td>"
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
			        + "<td>" + Double.toString(c.getLongitud() )  +    "</td>" ;
			// Final de línea.
			inventarioHTML = inventarioHTML + "</tr>";
		}
		
		// Cierre de la cabecera.
		inventarioHTML = inventarioHTML 
				+ "   </table>"
				+ "</div>"
				+ "<title>Insert title here</title>"
				+ "</head>"
				+ "<body></body></html>";
		
		return inventarioHTML;
	}

}

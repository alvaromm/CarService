package paquete;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class CocheDB {
	// -----------------------------------------------------------
	//                          Atributos
	// -----------------------------------------------------------
	// Atributos para la base de datos.
	private static EntityManagerFactory factoria;
	private static final String PERSISTENCE_UNIT_NAME = "persistencia";
	static{
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	// -----------------------------------------------------------
	//                          Métodos
	// -----------------------------------------------------------	
	
	/**
	 * Añade un coche a la base de datos.
	 * @param coche : Coche que se va a añadir.
	 * */
	public static void insertar(Coche coche) {
		EntityManager em = factoria.createEntityManager();
		
		// Comenzar una nueva transaccion local de tal forma que pueda persistir
		// como una nueva entidad.
		em.getTransaction().begin();
		em.persist(coche);
		
		// Ahora hay que hacer "commit" de la transaccion, lo que causara que la
		// entidad se almacene en la base de datos.
		em.getTransaction().commit();
		
		// Ahora hay que cerrar el EntityManager o perderemos nuestras entradas.
		em.close();
		System.out.println("Coche insertado.");
	}
	
	/**
	 * Actualiza el coche de la base de datos.
	 * @param usuario : Coche que se va a actualizar. 
	 * 		Se busca por el identificador, y se procede
	 * 		a actuzliar el nombre y el email.
	 * */
	public static void actualizar(int idCocheAntiguo, Coche nuevoCoche) {
		Coche coche_modificado = new Coche();
		
		EntityManager em = factoria.createEntityManager();
		
		// Buscamos el coche en la base de datos.
		coche_modificado = em.find(Coche.class, idCocheAntiguo);
		
		// Comenzamos una transacción.
		em.getTransaction().begin();
		
		// Realizamos los cambios.
		coche_modificado.setCaballos (nuevoCoche.getCaballos() );
		coche_modificado.setCilindros(nuevoCoche.getCilindros());
		coche_modificado.setLongitud (nuevoCoche.getLongitud() );
		coche_modificado.setMarca    (nuevoCoche.getMarca()    );
		coche_modificado.setModelo   (nuevoCoche.getModelo()   );
		coche_modificado.setOrigen   (nuevoCoche.getOrigen()   );
		coche_modificado.setPeso     (nuevoCoche.getPeso()     );
		coche_modificado.setPrecio   (nuevoCoche.getPrecio()   );
		coche_modificado.setTamMotor (nuevoCoche.getTamMotor() );
		coche_modificado.setTipo     (nuevoCoche.getTipo()     );
		coche_modificado.setTraccion (nuevoCoche.getTraccion() );
		
		// Hacemos permanentes los cambios.
		em.getTransaction().commit();
		
		// Cerramos el gestor.
		em.close();
		
		System.out.println("Coche modificado.");
	}
	
	/**
	 * Elimina un coche de la base de datos.
	 * @param coche : Coche que se va a eliminar.
	 * 	(Es necesario que el 'id' del coche esté en la base de datos)
	 * */
	public static void eliminar(Coche coche) {
		// Para esto, nos crearemos un gestor de entidades "fresco"
		EntityManager em = factoria.createEntityManager();
		
		// Buscamos el usuario en la base de datos.
		coche = em.find(Coche.class, coche.getCarId());
		// Comenzamos la transacción.
		em.getTransaction().begin();
		// Borramos el usuario.
		em.remove(coche);
		// La hacemos permanente.
		em.getTransaction().commit();

		// Cerramos el gestor.
		em.close();
		System.out.println("Coche eliminado.");
	}
	
	/**
	 * Selecciona un coche dado el modelo.
	 * 
	 * @param modelo : Indica el modelo del coche que se va a devolver.
	 * @return Devuelve un objeto Coche con el 'modelo' pasado por 
	 * 			parámetro que se encuentra en la base de datos.
	 * 			Si no existe devuelve 'null'.
	 * */
	@SuppressWarnings("unchecked")
	public static Coche seleccionarCoche(String modelo) {
		Coche c;
		List<Coche> listaCoches; 
		
		EntityManager em = factoria.createEntityManager();
		
		// Hacemos la consulta.
		Query q = em.createQuery("SELECT u FROM Coche u WHERE u.modelo='" + modelo + "'");
		// Anotamos el resultado.
		listaCoches = q.getResultList();
		if(listaCoches.size() != 0)
			c = listaCoches.get(0);
		else 
			return null;
		
		// Cerramos el gestor.
		em.close();
		
		return c;
	}
	
	/**
	 * Busca si existe un coche según el modelo.
	 * @param modelo : Modelo que se va a buscar.
	 * */
	@SuppressWarnings("unchecked")
	public static boolean existeCoche(String modelo) {
		EntityManager em = factoria.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		// Comenzar una nueva transaccion local de tal manera que podamos hacer
		// persitente una nueva entidad
		// Ahora me creare la consulta necesaria eliminar la persona de nombre y
		// apellidos que indicare despues
		Query q = em.createQuery("SELECT u FROM Coche u WHERE u.modelo = :modelo");
		// Ahora asigno los parametros
		q.setParameter("modelo", modelo);
		// Ahora utilizo el metodo: "getSingleResult()" para obtener a la
		// persona que me interesa y los metodos: "remove(persona)" y "commit()"
		// para eliminarla de la entidad y confirmar la eliminacion,
		// respectivamente
		List<Coche> lu = q.getResultList();
		em.close();     
		
		if(lu.size() == 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Obtener todos los coches de la base de datos.
	 * 
	 * @return Devuelve la lista de los coches con sus atributos que
	 * 			hay en la base de datos.
	 * */
	@SuppressWarnings("unchecked")
	public static List<Coche> getCoches(){
		EntityManager em = factoria.createEntityManager();
		
		// Hacemos la consulta.
		Query q = em.createQuery("SELECT u FROM Coche u");
		List<Coche> lc = q.getResultList();
		
		// Cerramos el gestor.
		em.close();
		
		return lc;
	}

	/**
	 * Muestra todos los coches de la base de datos.
	 * */
	@SuppressWarnings("unchecked")
	public static void mostrarBD() {
		// Abrimos el gestor de etnidad.
		EntityManager em = factoria.createEntityManager();

		// Leemos las entradas existentes y escribimos en la consola.
		Query q = em.createQuery("SELECT t from Coche t");
		
		// Creamos una lista con todos los Usuarios a la que le asignamos
		//  el resultado de la consulta en la base de datos.
		List <Coche> listaCoches = q.getResultList();
		for (Coche u: listaCoches) {
			System.out.println(u);
		}
		System.out.println("Tamaño: " + listaCoches.size());
	}
}


package tutorialP3;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class UsuarioDB {
	private static EntityManagerFactory factoria;
	private static final String PERSISTENCE_UNIT_NAME = "persistencia";

	static{
		factoria = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}
	
	public static void insertar(Usuario usuario) {
		EntityManager em = factoria.createEntityManager();
		// Comenzar una nueva transaccion local de tal forma que pueda persistir
		// como una nueva entidad
		em.getTransaction().begin();
		em.persist(usuario);
		// Ahora hay que hacer "commit" de la transaccion, lo que causara que la
		// entidad se almacene en la base de datos
		em.getTransaction().commit();
		// Ahora hay que cerrar el EntityManager o perderemos nuestras entradas.
		em.close();
		System.out.println("Usuario insertado.");
	}
	
	// Actualiza un usuario determinado por su e-mail con nuevos datos
	public static void actualizar(String mailUsuarioAntiguo, Usuario nuevoUsuario) {
		EntityManager em = factoria.createEntityManager();
		// Comenzar una nueva transaccion local de tal forma que pueda persistir
		// como una nueva entidad
		em.getTransaction().begin();
		// Seleccionamos al usuario antiguo
		Query q = em.createQuery("SELECT u from Usuario u where u.email = :email");
		q.setParameter("email", mailUsuarioAntiguo);
		Usuario u = (Usuario) q.getSingleResult();
		// Eliminamos el usuario
		em.remove(u);
		// Creamos otro
		Usuario uNuevo = new Usuario(nuevoUsuario.getApellidos(),
									nuevoUsuario.getEmail(),
									nuevoUsuario.getNombre());
		// Ahora hay que hacer "commit" de la transaccion, lo que causara que la
		// entidad se almacene en la base de datos
		em.persist(uNuevo);
		em.getTransaction().commit();
		// Ahora hay que cerrar el EntityManager o perderemos nuestras entradas.
		em.close();
		System.out.println("Usuario modificado.");
	}
	
	public static void eliminar(Usuario usuario) {
		// Para esto, nos crearemos un gestor de entidades "fresco"
		EntityManager em = factoria.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		// Comenzar una nueva transaccion local de tal manera que podamos hacer
		// persitente una nueva entidad
		em.getTransaction().begin();
		// Ahora me creare la consulta necesaria eliminar la persona de nombre y
		// apellidos que indicare despues
		Query q = em.createQuery("SELECT u from Usuario u where u.email = :email");
		q.setParameter("email", usuario.getEmail());
		em.remove((Usuario) q.getSingleResult());
		// Acordarse de cerrar el gestor de entidades
		em.getTransaction().commit();
		em.close();
		System.out.println("Usuario eliminado.");
	}
	
	public static Usuario seleccionarUsuario(String email) {
		EntityManager em = factoria.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		// Comenzar una nueva transaccion local de tal manera que podamos hacer
		// persitente una nueva entidad
		// Ahora me creare la consulta necesaria eliminar la persona de nombre y
		// apellidos que indicare despues
		Query q = em.createQuery("SELECT m FROM Usuario m WHERE m.email = :email");
		// Ahora asigno los parametros
		q.setParameter("email", email);
		// Ahora utilizo el metodo: "getSingleResult()" para obtener a la
		// persona que me interesa y los metodos: "remove(persona)" y "commit()"
		// para eliminarla de la entidad y confirmar la eliminacion,
		// respectivamente
		Usuario u = (Usuario) q.getSingleResult();
		em.close();     
		
		return u;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean existeEmail(String email) {
		EntityManager em = factoria.createEntityManager();
		// Begin a new local transaction so that we can persist a new entity
		// Comenzar una nueva transaccion local de tal manera que podamos hacer
		// persitente una nueva entidad
		// Ahora me creare la consulta necesaria eliminar la persona de nombre y
		// apellidos que indicare despues
		Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email");
		// Ahora asigno los parametros
		q.setParameter("email", email);
		// Ahora utilizo el metodo: "getSingleResult()" para obtener a la
		// persona que me interesa y los metodos: "remove(persona)" y "commit()"
		// para eliminarla de la entidad y confirmar la eliminacion,
		// respectivamente
		List<Usuario> lu = q.getResultList();
		em.close();     
		
		if(lu.size() == 1){
			return true;
		}
		else{
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static List<Usuario> getUsuarios(){
		EntityManager em = factoria.createEntityManager();
		Query q = em.createQuery("SELECT u FROM Usuario u");
		List<Usuario> lu = q.getResultList();
		em.close();
		return lu;
	}
}


package paquete;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta clase implementa la clase Coche, donde tenemos 
 * 	definidos todos los atributos que la define.
 * */
@XmlRootElement
@Entity
public class Coche implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int    carId;
	private int    precio, cilindros, caballos;
	private double tamMotor, peso, longitud;
	private String marca, modelo, tipo, origen, traccion;

	
	// -----------------------------------------------------------
	//                          Métodos.
	// -----------------------------------------------------------	
	/**
	 * Constructor de la clase.
	 * Inicializa todos los valores al valor 0 ó vacío.
	 * */
	public Coche(){
		this.precio 	= 0;
		this.cilindros 	= 0;
		this.caballos 	= 0;
		this.tamMotor 	= 0;
		this.peso 		= 0;
		this.longitud 	= 0;
		this.marca 		= "";
		this.modelo 	= "";
		this.tipo 		= "";
		this.origen 	= "";
		this.traccion 	= "";
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
//		this.precio = precio;
	}

	public int getCilindros() {
		return cilindros;
	}

	public void setCilindros(int cilindros) {
		this.cilindros = cilindros;
	}

	public int getCaballos() {
		return caballos;
	}

	public void setCaballos(int caballos) {
		this.caballos = caballos;
	}

	public double getTamMotor() {
		return tamMotor;
	}

	public void setTamMotor(double tamMotor) {
		this.tamMotor = tamMotor;
	}

	public double getPeso() {
		return peso;
	}

	public void setPeso(double peso) {
		this.peso = peso;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getTraccion() {
		return traccion;
	}

	public void setTraccion(String traccion) {
		this.traccion = traccion;
	}
	
	@Override
	public String toString() {
		String coche = String.format(
				"[%-3d |%10s |%-10s |%10s |%10s "
				+ "|%7s |%3d |%3f |%2d |%3d "
				+ "|%2f |%2f]", 
				this.carId, this.marca, this.modelo, this.tipo, this.origen, 
				this.traccion, this.precio, this.tamMotor, this.cilindros, this.caballos, 
				this.peso, this.longitud);
		
		return coche;
	}
}

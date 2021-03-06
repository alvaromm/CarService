<%@ page import="paquete.Recurso" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles.css">
<title>CarService</title>
</head>

<header>
	<a href="/CarService" id="logo"></a>
	<nav>
		<a href="#" id="menu-icon"></a>
		<ul>
			<li><a href="buscarCoche.html">Buscar coche</a></li>
			<li><a href="crearCoche.html">Registrar coche</a></li>
		</ul>
	</nav>
</header>

<body>

	<% String marca=request.getParameter("marca");
	   String modelo=request.getParameter("modelo"); 
	   List<String> s = Recurso.buscarCoche(marca, modelo); 
	%>

<p> Resultados de la búsqueda: <%= s.get(1) %></p>

<div class="EstiloTabla" >
	<table>
	<tr>
		<!--   <td>Id</td>  -->
		<td>Marca</td>
		<td>Modelo</td>
		<td>Tipo</td>
		<td>Origen</td>
		<td>Tracción</td>
		<td>Precio</td>
		<td>Cilindros</td>
		<td>Caballos</td>
		<td>Tamaño motor</td>
		<td>Peso</td>
		<td>Longitud</td>
		<td>Opciones</td>
	</tr>
	
	<%= s.get(0) %>
	
	</table>
</div>


</body>
</html>


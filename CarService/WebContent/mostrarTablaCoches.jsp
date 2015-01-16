<%@ page import="paquete.Recurso" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="style.css">
<title>Insert title here</title>
</head>

<body>
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
	
	<%=Recurso.getInventarioHTML()%>
	
	</table>
	</div>
</body>
</html>
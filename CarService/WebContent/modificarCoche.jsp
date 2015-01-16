<%@ page import="paquete.Coche" %>
<%@ page import="paquete.CocheDB" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="style.css">
<title>Formulario Web para modificar un recurso</title>
</head>

<body>
	<form action="rest/coches/modificarCoche" method="POST" class="basic-grey">

	<% 
		int id = Integer.parseInt(request.getParameter("param")); 
		Coche c = new Coche();
		c = CocheDB.getCoche(id);
	%>

		<fieldset>
			<legend>Modificar coche</legend>
			<label> <span>Marca</span> <input type="text" name="marca"
				value=<%=c.getMarca()%> />
			</label> <label> <span>Modelo</span> <input type="text" name="modelo"
				value=<%=c.getModelo()%> />
			</label> <label> <span>Tipo</span> <input type="text" name="tipo"
				value=<%=c.getTipo()%> />
			</label> <label> <span>Origen</span> <input type="text" name="origen"
				value=<%=c.getTipo()%> />
			</label> <label> <span>Tracción</span> <input type="text"
				name="traccion" value=<%=c.getTraccion()%> />
			</label> <label> <span>Tamaño del motor</span> <input
				type="number" name="tamMotor" value=<%=c.getTamMotor()%> />
			</label> <label> <span>Peso</span> <input type="number"
				name="peso" value=<%=c.getPeso()%> />
			</label> <label> <span>Longitud</span> <input type="number"
				name="longitud" value=<%=c.getLongitud()%> />
			</label> <label> <span>Precio</span> <input type="number"
				name="precio" value=<%=c.getPrecio()%> />
			</label> <label> <span>Cilindros</span> <input type="number"
				name="cilindros" value=<%=c.getCilindros()%> />
			</label> <label> <span>Caballos</span> <input type="number"
				name="caballos" value=<%=c.getCaballos()%> />

			</label> <label>
			<input type="hidden" name="id" value=<%=id%> /> 
			<input class="button" type="submit" value="Modificar" /> </label>

		</fieldset>

	</form>
</body>
</html>
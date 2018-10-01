package com.atos.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.modelo.IGestion_Usuarios;
import com.atos.spring.Acceso_ApplicationContext;

/**
 * Servlet implementation class Login_Servlet
 */
public class Login_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest peticion, HttpServletResponse respuesta)
			throws ServletException, IOException {
		// AUXILIARES PARA LA REALIZACION DEL PROCESO
		boolean valido = true;
		String salida = "jsp/login.jsp";
		String error_clave = "";
		String error_nombre = "";
		// 1º CAPTURA DE INFORMACION DE LA PETICION ENVIADA
		String nombre_usuario = peticion.getParameter("nombre_usuario");
		String clave_usuario = peticion.getParameter("clave_usuario");

		// 2º CONVERSION
		// ****** NO ES NECESARIA EN ESTE CASO

		// 3º VALIDACION
		if (nombre_usuario.equals("")) {
			// ERROR DE NOMBRE VACIO
			valido = false;
			error_nombre = "El nombre es obligatorio";
			// error_nombre = rb.getString("login.error.nombrevacio");
		} else if (nombre_usuario.length() > 20) {
			// ERROR DE TAMAÑO EN NOMBRE
			valido = false;
			error_nombre = "Nombre demasiado grande, maximo 20 caracteres";
			// String error2 = rb.getString("login.error.nombrelargo");
			// String tabla2[] = { String.valueOf(nombre_usuario.length()) };
			// error_nombre = MessageFormat.format(error2, tabla2);
			// error_nombre = rb.getString("login.error.nombrelargo");
		}
		if (clave_usuario.equals("")) {
			// ERROR DE CLAVE VACIA
			valido = false;
			error_clave = "La clave es obligatoria";
			// error_clave = rb.getString("login.error.clavevacio");
		} else if (clave_usuario.length() > 10) {
			// ERROR DE TAMAÑO DE CLAVE
			valido = false;
			error_clave = "El maximo de caracteres son 10";
			// String error1 = rb.getString("login.error.clavelargo");
			// String tabla1[] = { String.valueOf(clave_usuario.length()) };
			// error_clave = MessageFormat.format(error1, tabla1);
		}

		// 4º LOGICA/NEGOCIO
		if (valido) {
			// ACCESO AL MODELO
			IGestion_Usuarios gestion_Usuario = Acceso_ApplicationContext.getBean(IGestion_Usuarios.class);
			Usuarios usuario = gestion_Usuario.consultar_PorNombre(nombre_usuario);
			// LOGICA DEL PROCESO DE LOGIN
			if (usuario != null) {
				if (usuario.getPassword().equals(clave_usuario)) {
					// CREDENCIALES CORRECTAS
					// PROXIMA PAGINA EN LA NAVEGACION
					salida = "jsp/menu.jsp";
					// INICIAMOS EL SEGUIMIENTO DE LA SESION
					peticion.getSession().setAttribute("usuario", usuario);
				} else {
					// INCORRECTO - CLAVE ERRONEA
					error_clave = "La clave no es correcta para este usuario";
					// error_clave = rb.getString("login.error.claveerronea");
				}
			} else {
				// ERROR EL NOMBRE DE USUARIO NO ES CORRECTO
				error_nombre = "No existe ningun usuario con ese nombre";
				// error_nombre = rb.getString("login.error.nombreerroneo");
			}
			// 5º NAVEGACION
			RequestDispatcher rqd = peticion.getRequestDispatcher(salida);
			rqd.forward(peticion, respuesta);
		}

	}
}

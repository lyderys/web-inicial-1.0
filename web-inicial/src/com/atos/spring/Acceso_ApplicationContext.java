package com.atos.spring;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * Forma de acceso al objeto ApplicationContext deSpring casteado a
 * webappliacationContex de forma que sea accesible desde cualquier parte del
 * programa.
 * 
 * @author Juan Antonio Solves Garcia.
 * @version 2.0
 * @since 24-5-2018.
 * 
 */
@Component("acceso_spring")
@Lazy(false)
@Scope("singleton")
public class Acceso_ApplicationContext implements ApplicationContextAware {

	private static ApplicationContext ctx;
	private static WebApplicationContext wctx;

	/**
	 * Recibimos de forma automatica (al crearse el applicationcontext) cuando
	 * se crea el bean.<br/>
	 * Al incorporar la interface ese comportamiento se realiza el paso 4/5 del
	 * ciclo de vida de los bean.
	 * 
	 */
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		if (ctx instanceof WebApplicationContext) {
			Acceso_ApplicationContext.wctx = (WebApplicationContext) ctx;
		}
		if (ctx instanceof ApplicationContext) {
			Acceso_ApplicationContext.ctx = ctx;
		}
	}

	/**
	 * Nos permite acceder al WebapplicationContext directamente en cualquier
	 * clase de aplicacion.
	 * 
	 * @return
	 */
	public static ApplicationContext getContextoSpring() {
		return ctx;
	}

	public static WebApplicationContext getContextoWebSpring() {
		return wctx;
	}

	/**
	 * Proceso de conveniencia para la obtencion directa del bean.
	 * 
	 * @param objeto
	 *            Id o nombre del objeto que spring tiene que pasar.
	 * @return Objeto creado por spring.
	 */
	public static Object getBean(String objeto) {
		Object salida = null;
		if (ctx != null) {
			salida = ctx.getBean(objeto);
		}
		if (wctx != null) {
			salida = wctx.getBean(objeto);
		}
		return salida;
	}

	/**
	 * Proceso de conveniencia para la obtencion del bean. En este caso solo
	 * necesitamos dar el class del bean, sin necesidad de añadir el casteo de
	 * la clase.
	 * 
	 * @param spring_bean
	 *            Class del bean solicitado.
	 * @return Objeto pedido.
	 */
	public static <T> T getBean(Class<T> spring_bean) {
		if (ctx != null) {
			return ctx.getBean(spring_bean);
		}
		if (wctx != null) {
			return wctx.getBean(spring_bean);
		}
		return null;
	}

	/**
	 * Nos permite acceder al servletcontext en cualquier clase de la aplicacion
	 * de forma estatica.
	 * 
	 * @return
	 */
	public static ServletContext getServletContext() {
		ServletContext servletContext = wctx.getServletContext();
		return servletContext;
	}

}

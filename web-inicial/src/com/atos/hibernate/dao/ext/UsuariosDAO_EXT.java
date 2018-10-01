package com.atos.hibernate.dao.ext;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.atos.hibernate.Usuarios;
import com.atos.hibernate.dao.UsuariosDAO;

@Repository("usuario_dao")
@Scope("prototype")
public class UsuariosDAO_EXT  extends UsuariosDAO {

	public Usuarios consultar_ConRol(String nombre_usuario) {
		// APERTURA DE CRITERIA PARA LA CONSULTA
		Criteria consulta = getCurrentSession().createCriteria(Usuarios.class);
		// MODO DE RESOLUCION DE CARGA VAGA
		consulta.setFetchMode("roles", FetchMode.JOIN);
		// CONDICIONES DE LA CONSULTA
		consulta.add(Restrictions.idEq(nombre_usuario));
		// SE EJECUTA LA CONSULTA
		Usuarios usuario = (Usuarios) consulta.uniqueResult();
		return usuario;
	}

	public Usuarios consultar_Tareas(String nombre_usuario) {
		// APERTURA DE CRITERIA PARA LA CONSULTA
		Criteria consulta = getCurrentSession().createCriteria(Usuarios.class);
		// MODO DE RESOLUCION DE CARGA VAGA
		consulta.setFetchMode("roles", FetchMode.JOIN);
		consulta.setFetchMode("roles.tareases", FetchMode.JOIN);
		// CONDICIONES DE LA CONSULTA
		consulta.add(Restrictions.idEq(nombre_usuario));
		// TRATAMIENTO DEL PRODUCTO CARTESIANO DE LA CONSULTA
		consulta.setResultTransformer(consulta.DISTINCT_ROOT_ENTITY);
		// SE EJECUTA LA CONSULTA
		Usuarios usuario = (Usuarios) consulta.uniqueResult();
		return usuario;
	}

}

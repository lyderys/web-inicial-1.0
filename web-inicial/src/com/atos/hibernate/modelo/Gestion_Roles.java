package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Roles;
import com.atos.hibernate.dao.RolesDAO;

@Component("gestion_roles")
@Scope("prototype")
public class Gestion_Roles implements IGestion_Roles {

	private RolesDAO roles_dao;

	// *********** CONSULTAS

	@Override
	@Transactional(readOnly = true)
	public Roles consultar_PorClave(Byte codigo_rol) {
		return roles_dao.findById(codigo_rol);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Roles> consultar_Todos() {
		return roles_dao.findAll();
	}

	// ************ OPERACIONES CRUD

	@Override
	@Transactional
	public void alta_Rol(Roles rol_nuevo) {
		roles_dao.save(rol_nuevo);
	}

	@Override
	@Transactional
	public void baja_Rol(Roles rol) {
		roles_dao.delete(rol);
	}

	@Override
	@Transactional
	public void modificacion_Rol(Roles rol) {
		roles_dao.attachDirty(rol);
	}

	// ** ACCESORES PARA SPRING
	public RolesDAO getRoles_dao() {
		return roles_dao;
	}

	public void setRoles_dao(RolesDAO roles_dao) {
		this.roles_dao = roles_dao;
	}

}

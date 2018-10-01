package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Roles;

public interface IGestion_Roles {

	Roles consultar_PorClave(Byte codigo_rol);

	void modificacion_Rol(Roles rol);

	void baja_Rol(Roles rol);

	void alta_Rol(Roles rol_nuevo);

	List<Roles> consultar_Todos();

}
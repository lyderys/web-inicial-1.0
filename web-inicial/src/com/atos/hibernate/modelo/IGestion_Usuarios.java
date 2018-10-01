package com.atos.hibernate.modelo;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.atos.hibernate.Usuarios;

public interface IGestion_Usuarios {

	// ***************** CONSULTAS
	public Usuarios consultar_PorNombre(String nombre_usuario);

	public List<Usuarios> consultar_Todos();

	//************ CRUD
	public void alta_Usuario(Usuarios usuario);

	public void baja_Usuario(Usuarios usuario);

	public void modificacion_Usuario(Usuarios usuario);

	public abstract Usuarios consultar_Tareas(String nombre_usuario);

	public abstract Usuarios consultar_ConRol(String nombre_usuario);

}
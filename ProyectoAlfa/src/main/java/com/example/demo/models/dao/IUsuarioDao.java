package com.example.demo.models.dao;

import java.util.List;

import com.example.demo.models.entity.Usuario;


public interface IUsuarioDao {

	//devueve todos los usuarios
	public List<Usuario> findAll();
	//guarda un usuario 
	public void save(Usuario usuario);
	//busca usuario por id
	public Usuario findOne(Long id);
	
}

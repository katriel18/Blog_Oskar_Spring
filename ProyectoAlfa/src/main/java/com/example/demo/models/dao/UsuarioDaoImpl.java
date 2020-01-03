package com.example.demo.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Usuario;

@Repository
public class UsuarioDaoImpl implements IUsuarioDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Usuario> findAll() {
		
		return em.createQuery("from Usuario").getResultList();
	}

	
	@Transactional
	@Override
	public void save(Usuario usuario) {
		
		if(usuario.getId()!=null && usuario.getId()>0) {
			
			em.merge(usuario);//actualiza datos existentes
		}else {
			//usuario.asigarFechaInicio();
			em.persist(usuario);//crear nuevo cliente
		}
		
		
		
		
	}


	@Override
	public Usuario findOne(Long id) {
		
		return em.find(Usuario.class, id);
	}

}

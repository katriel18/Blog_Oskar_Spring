package com.example.demo.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.models.dao.IUsuarioDao;
import com.example.demo.models.entity.Usuario;

@Controller
public class UsuarioController {
	
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@GetMapping({"/index","/"})
	public String index() {
		
		
			return "/index";
			
	}
	
	@RequestMapping(value="/listar",method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Usuarios de la Comunidad Hack Space");
		model.addAttribute("usuarios",usuarioDao.findAll());
		return "listar";
		
	}
	@RequestMapping(value="/formulario")
	public String crear(Map<String,Object> model) {
		//usuario con atributos vacios
		Usuario usuario=new Usuario();
		usuario.setEmail("estrellita@gmail.com");
		
		model.put("tit", "inscripcion");
		model.put("titulo", "Formulario de Inscripcion");
		model.put("usuario",usuario);
		return "formulario";
		
	}
	@RequestMapping(value="/formulario",method=RequestMethod.POST)
	public String guardar(Usuario u) {
		
		usuarioDao.save(u);
		
		return "redirect:listar";
		
	}
	

	/*
	@RequestMapping(value="/formulario/{id}")
	public String editar(@PathVariable(value="id") Long id,Map<String,Object> model) {
		
		Usuario usuario=null;
		
		if(id>0) {
			//usuario con atributos ya llenados
			usuario=usuarioDao.findOne(id);
		}else {
			return "redirect:/listar";
		}
		model.put("titulo", "Editar Usuario");
		model.put("usuario",usuario);
		return "formulario";
		
	}
*/
	@RequestMapping(value="/perfil/{id}")
	public String verPerfil(@PathVariable(value="id") Long id,Map<String,Object> model) {
		
		Usuario usuario=null;
		
		if(id>0) {
			//usuario con atributos ya llenados
			usuario=usuarioDao.findOne(id);
		}else {
			return "redirect:/listar";
		}
		
		model.put("titulo", "Perfil de Usuario");
		model.put("usuario",usuario);
		return "/perfil";
		
	}
	
	@RequestMapping(value="/formulario/{id}")
	public String editarPerfil(@PathVariable(value="id") Long id,Map<String,Object> model) {
		
		Usuario usuario=null;
		usuario=usuarioDao.findOne(id);
		model.put("tit", "editar");
		model.put("titulo", "Editar Usuario");
		model.put("usuario",usuario);
		return "/formulario";
		
	}
	
	
	
}

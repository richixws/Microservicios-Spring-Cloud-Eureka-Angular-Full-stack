package com.developer.pe.controllers;


import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.developer.pe.services.CommonService;

@CrossOrigin({"http://localhost:4200"})
public class CommonController<E,S extends CommonService<E>> {

	
	@Autowired
    protected S service;
	
	
	@GetMapping()
	public ResponseEntity<?> listarAlumnos(){
		
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@GetMapping("/pagina")
	public ResponseEntity<?> listarAlumnos(Pageable pageable){
		
		return ResponseEntity.ok().body(service.findAll(pageable));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> verAlumno(@PathVariable("id") Long id){
		
		Optional<E> oEntity=service.findById(id);
		if(!oEntity.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(oEntity);
	}
	
	@PostMapping
	public ResponseEntity<?> saveAlumno(@Valid @RequestBody E entity, BindingResult result){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		E entityDb=service.save(entity);
		return new  ResponseEntity<>(entityDb,HttpStatus.CREATED);
		
	}
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAlumno(@PathVariable("id") Long id){
		
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	//metodo de validacion de errores
	protected ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores=new HashMap<>();
		result.getFieldErrors().forEach(err ->{
		   errores.put(err.getField(), "el campo "+err.getField()+" "+err.getDefaultMessage());
		});
		return ResponseEntity.badRequest().body(errores);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.developer.pe.controller;


import java.io.IOException;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.commons.alumnos.models.entity.Alumno;
import com.developer.pe.controllers.CommonController;
import com.developer.pe.service.AlumnoService;

@RestController
@RequestMapping("/api")
public class AlumnoController extends CommonController<Alumno,AlumnoService>{

	
	@PutMapping("{id}")	
	public ResponseEntity<?> updateAlumno(@Valid @RequestBody Alumno alumno, BindingResult result,@PathVariable("id") Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> alumnoId=service.findById(id);
		if (!alumnoId.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnotemp=alumnoId.get();
		alumnotemp.setNombre(alumno.getNombre());
		alumnotemp.setEmail(alumno.getEmail());
		alumnotemp.setApellido(alumno.getApellido());
		
		Alumno alumnoUpd=service.save(alumnotemp);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoUpd);
		
	}
	
	@GetMapping("/buscar/{term}")
	public ResponseEntity<?> filtrar(@PathVariable("term") String term){
		return ResponseEntity.ok(service.findByNombreOrApellido(term));
	}

	
	@PostMapping("/crear-con-foto")
	public ResponseEntity<?> crearFoto(@Valid  Alumno alumno, BindingResult result, @RequestParam MultipartFile archivo) throws IOException {
		
		if(!archivo.isEmpty()) {
			alumno.setFoto(archivo.getBytes());
		}
		return super.saveAlumno(alumno, result);
	}
	
	@PutMapping("/editar-con-foto/{id}")
	public ResponseEntity<?> editarConFoto(@Valid Alumno alumno, BindingResult result,@PathVariable("id") Long id,
											@RequestParam MultipartFile archivo) throws IOException{
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Alumno> alumnoId=service.findById(id);
		
		if (!alumnoId.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Alumno alumnotemp=alumnoId.get();
		alumnotemp.setNombre(alumno.getNombre());
		alumnotemp.setEmail(alumno.getEmail());
		alumnotemp.setApellido(alumno.getApellido());
		
		if(!archivo.isEmpty()) {
			alumnotemp.setFoto(archivo.getBytes());
		}
		
		Alumno alumnoUpd=service.save(alumnotemp);
		return ResponseEntity.status(HttpStatus.CREATED).body(alumnoUpd);
		
	}
	
	//ver imagen del alumno
	@GetMapping("/uploads/img/{id}")
	public ResponseEntity<?> verFoto(@PathVariable Long id){
		
	    Optional<Alumno> o=service.findById(id);
		
		if (o.isEmpty() || o.get().getFoto() == null) {
			return ResponseEntity.notFound().build();
		}
		
		Resource imagen=new ByteArrayResource(o.get().getFoto());
		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imagen);
			
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

package com.developer.pe.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.commons.alumnos.models.entity.Alumno;
import com.developer.commons.examenes.models.entity.Examen;
import com.developer.pe.controllers.CommonController;
import com.developer.pe.models.entity.Curso;
import com.developer.pe.service.CursoService;

@RestController
public class CursoController extends CommonController<Curso, CursoService> {

	
	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid  @RequestBody Curso curso,BindingResult result, @PathVariable Long id){
		
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Curso> o=this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso c=o.get();
		c.setNombre(curso.getNombre());
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(c));
	}
	
	@PutMapping("/{id}/asignar-alumnos")
	public ResponseEntity<?> asignarAlumnos(@RequestBody List<Alumno> alumnos ,@PathVariable Long id){
		
		Optional<Curso> o=this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso=o.get();
		
		alumnos.forEach(a -> {
		    dbCurso.addAlumno(a);	
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@PutMapping("/{id}/eliminar-alumno")
	public ResponseEntity<?> eliminarAlumno(@RequestBody Alumno alumno, @PathVariable Long id){
		
		Optional<Curso> o=this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso=o.get();
		
		dbCurso.removeAlumno(alumno);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	@GetMapping("/alumno/{id}")
	public ResponseEntity<?> buscarPorAlumnoId(@PathVariable("id") Long id){
	    
		Curso curso=service.findCursoByAlumnoId(id);
		
		if(curso != null) {
			List<Long> examenesIds=service.obtenerExamenesIdsConRespuestasAlumno(id);
			List<Examen> examenes=curso.getExamenes().stream().map( e ->{
				if(examenesIds.contains(e.getId())) {
					e.setRespondido(true);
				}
				return e;
			}).collect(Collectors.toList());
			
			curso.setExamenes(examenes);
		}
		
		return ResponseEntity.ok(curso);
	}
	
	@PutMapping("/{id}/asignar-examenes")
	public ResponseEntity<?> asignarExamenes(@RequestBody List<Examen> examenes ,@PathVariable Long id){
		
		Optional<Curso> o=this.service.findById(id);
		if(!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso=o.get();
		
		examenes.forEach(e -> {
		    dbCurso.addExamen(e);
		});
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}	
	
	@PutMapping("/{id}/eliminar-examen")
	public ResponseEntity<?> eliminarExamen(@RequestBody Examen examen, @PathVariable Long id){
		
		Optional<Curso> o=this.service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Curso dbCurso=o.get();
		
		dbCurso.removeExamen(examen);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(this.service.save(dbCurso));
	}
	
	
	
	
	
	
}

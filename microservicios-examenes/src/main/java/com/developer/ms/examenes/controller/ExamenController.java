package com.developer.ms.examenes.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.developer.commons.examenes.models.entity.Examen;
import com.developer.ms.examenes.services.ExamenService;
import com.developer.pe.controllers.CommonController;

@RestController
public class ExamenController extends CommonController<Examen, ExamenService>{

	@PutMapping("/{id}")
	public ResponseEntity<?> editar(@Valid @RequestBody Examen examen,BindingResult result, @PathVariable Long id){
		
		if(result.hasErrors()) {
			return this.validar(result);
		}
		
		Optional<Examen> o=service.findById(id);
		if (!o.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		Examen examenDb=o.get();
		examenDb.setNombre(examen.getNombre());
		
		examenDb.getPreguntas()
				.stream()
				.filter(pdb -> !examen.getPreguntas().contains(pdb))
				.forEach(examenDb::removePregunta);
		
		/**
		List<Pregunta> eliminadas=new ArrayList<>();
		examenDb.getPreguntas().forEach(pbd->{
		   if (!examen.getPreguntas().contains(pbd)) {
		       eliminadas.add(pbd);
		   }  	
		});
		
		eliminadas.forEach(p -> {
			examenDb.removePregunta(p);
		});
		**/
		examenDb.setPreguntas(examen.getPreguntas());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(examenDb));
	}
	
	@GetMapping("/filtrar/{term}")
	public ResponseEntity<?> buscarPorNombre(@PathVariable String term){
		
		List<Examen> e=service.findByNombre(term);
		
		return ResponseEntity.status(HttpStatus.OK).body(e); 
	}
	
	@GetMapping("/asignaturas")
    public ResponseEntity<?> listarAsignaturas(){
		return ResponseEntity.status(HttpStatus.OK).body(service.findAllAsignaturas());
	}
	
	
}

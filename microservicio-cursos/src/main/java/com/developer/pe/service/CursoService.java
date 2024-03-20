package com.developer.pe.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.developer.pe.models.entity.Curso;
import com.developer.pe.services.CommonService;

public interface CursoService extends CommonService<Curso> {

	
	public Curso findCursoByAlumnoId(Long id);
	
	
	public List<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId);
}

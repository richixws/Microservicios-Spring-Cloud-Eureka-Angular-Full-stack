package com.developer.pe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.developer.pe.clients.RespuestaFeignClient;
import com.developer.pe.models.entity.Curso;
import com.developer.pe.respository.CursoRepository;
import com.developer.pe.service.CursoService;
import com.developer.pe.services.CommonServiceImpl;

@Service
public class CursoServiceImpl extends CommonServiceImpl<Curso, CursoRepository> implements CursoService{

	@Autowired
	private RespuestaFeignClient client;
	
	
	@Override
	@Transactional(readOnly = true)
	public Curso findCursoByAlumnoId(Long id) {
		return repository.findCursoByAlumnoId(id);
		
	}

	@Override
	public List<Long> obtenerExamenesIdsConRespuestasAlumno(Long alumnoId) {
         
		client.obtenerExamenesIdsConRespuestasAlumno(alumnoId);
		return null;
	}

	
}

package com.developer.pe.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commons.alumnos.models.entity.Alumno;
import com.developer.pe.repository.AlumnoRepository;
import com.developer.pe.service.AlumnoService;
import com.developer.pe.services.CommonServiceImpl;

@Service
public class AlumnoServiceImpl extends CommonServiceImpl<Alumno, AlumnoRepository> implements AlumnoService {

	
	
	@Override
	@Transactional(readOnly = true)
	public List<Alumno> findByNombreOrApellido(String term) {
		return repository.findByNombreOrApellido(term);
	}
    
	

}

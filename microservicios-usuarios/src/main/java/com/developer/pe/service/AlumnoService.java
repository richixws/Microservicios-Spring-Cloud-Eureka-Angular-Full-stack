package com.developer.pe.service;

import java.util.List;
import java.util.Optional;

import com.commons.alumnos.models.entity.Alumno;
import com.developer.pe.services.CommonService;

public interface AlumnoService extends CommonService<Alumno> {
	
	
	public List<Alumno> findByNombreOrApellido(String term);
}

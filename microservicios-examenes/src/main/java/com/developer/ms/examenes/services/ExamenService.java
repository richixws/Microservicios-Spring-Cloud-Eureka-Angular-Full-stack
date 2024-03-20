package com.developer.ms.examenes.services;

import java.util.List;

import com.developer.commons.examenes.models.entity.Asignatura;
import com.developer.commons.examenes.models.entity.Examen;
import com.developer.pe.services.CommonService;

public interface ExamenService extends CommonService<Examen> {

	public List<Examen> findByNombre(String term);
	
	public List<Asignatura> findAllAsignaturas();
	
}

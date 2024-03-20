package com.developer.ms.examenes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.developer.commons.examenes.models.entity.Asignatura;
import com.developer.commons.examenes.models.entity.Examen;
import com.developer.ms.examenes.repository.AsignaturaRepository;
import com.developer.ms.examenes.repository.ExamenRepository;
import com.developer.pe.services.CommonServiceImpl;

@Service
public class ExamenServiceImpl extends CommonServiceImpl<Examen, ExamenRepository> implements ExamenService{

	@Autowired
	private AsignaturaRepository asignaturaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Examen> findByNombre(String term) {
		return repository.findByNombre(term);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Asignatura> findAllAsignaturas() {
		
		return asignaturaRepository.findAll();
	}

}

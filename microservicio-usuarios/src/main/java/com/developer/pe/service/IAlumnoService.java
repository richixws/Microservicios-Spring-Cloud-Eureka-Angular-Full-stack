package com.developer.pe.service;

import java.util.List;
import java.util.Optional;

import com.developer.pe.entity.Alumno;

public interface IAlumnoService {
	
	public Iterable<Alumno> findAll();	
	public Optional<Alumno> findById(Long id);
	public Alumno save(Alumno alumno);
	public void deleteById(Long id);

}

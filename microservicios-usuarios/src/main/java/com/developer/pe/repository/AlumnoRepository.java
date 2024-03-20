package com.developer.pe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.commons.alumnos.models.entity.Alumno;


public interface AlumnoRepository extends JpaRepository<Alumno, Long> {

	@Query("select a from Alumno a where a.nombre like %?1% or a.apellido like %?1%")
	public List<Alumno> findByNombreOrApellido(String term);
	
}

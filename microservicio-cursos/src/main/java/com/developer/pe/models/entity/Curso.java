package com.developer.pe.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;

import com.commons.alumnos.models.entity.Alumno;
import com.developer.commons.examenes.models.entity.Examen;

@Entity
@Table(name = "cursos")
public class Curso {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	private String nombre;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createAt;
	
	@OneToMany(fetch = FetchType.LAZY)
	private List<Alumno> alumnos;
	 
	@ManyToMany(fetch = FetchType.LAZY)
	private List<Examen> examenes;

	@PrePersist
	public void prePersist() {
		this.createAt=new Date();
	}
	
	public Curso() {
		this.alumnos=new ArrayList<>(); 
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public List<Examen> getExamenes() {
		return examenes;
	}

	public void setExamenes(List<Examen> examenes) {
		this.examenes = examenes;
	}

	//metodo para agregar alumno
	public void addAlumno(Alumno alumno) {
		this.alumnos.add(alumno);
	}
	//metodo para eliminar alumno
	public void removeAlumno(Alumno alumno) {
		this.alumnos.remove(alumno);
	}
	//metodo para agregar examen
	public void addExamen(Examen examen) {
		this.examenes.add(examen);
	}
	//metodo para eliminar examen
	public void removeExamen(Examen examen) {
		this.examenes.remove(examen);
	}
	

}

package com.developer.pe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



public class CommonServiceImpl<E, R extends JpaRepository<E,Long>> implements CommonService<E> {

	@Autowired
	protected R  repository;
	
	@Override
	@Transactional(readOnly = true)
	public List<E> findAll() {
	    
		return (List<E>) repository.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<E> findById(Long id) {
	    
		Optional<E> alumno=repository.findById(id);
	    return alumno;
	}

	@Override
	@Transactional
	public E save(E entity) {
		
		return repository.save(entity);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		   repository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Page<E> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return repository.findAll(pageable);
	}

}

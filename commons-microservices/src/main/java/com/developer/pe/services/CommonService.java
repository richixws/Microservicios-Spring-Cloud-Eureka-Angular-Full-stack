package com.developer.pe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommonService<E> {
	
	public List<E> findAll();
	public Page<E> findAll(Pageable pageable);
	public Optional<E> findById(Long id);
	public E save(E entity);
	public void delete(Long id);
	

}

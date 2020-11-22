package ar.ucc.edu.ArqSW.Parcial.common.dao;

import java.io.Serializable;
import java.util.List;

import ar.ucc.edu.ArqSW.Parcial.common.exception.BadRequestException;
import ar.ucc.edu.ArqSW.Parcial.common.exception.EntityNotFoundException;

public interface GenericDao<E, ID extends Serializable> {
	
	public void insert(E entity) throws BadRequestException;
	
	public void saveOrUpdate(E entity);

	public void update(E entity);

	public void remove(E entity);

	public E load(ID key) throws EntityNotFoundException;

	public List<E> getAll();
	
}

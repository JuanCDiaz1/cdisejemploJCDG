package com.cdisejemploJCDG.springboot.app.models.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cdisejemploJCDG.springboot.app.models.entity.Cuenta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

//CuentaDaoImpl_significa_Cuenta_Dao_Implementation
@Repository
public class CuentaDaoImpl implements ICuentaDao {
 
	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Cuenta").getResultList();
	}


	@Override
	@Transactional
	public void save(Cuenta cuenta) {
		if(cuenta.getId() != null&&cuenta.getId()>0){
			em.merge(cuenta);
		}else {
			em.persist(cuenta);
		}
	}


	@Override
	@Transactional
	public Cuenta findOne(Long id) {
		return em.find(Cuenta.class, id);

	}


	@Override
	@Transactional
	public void delete(Long id) {
		em.remove(findOne(id));
		
	}

	
}

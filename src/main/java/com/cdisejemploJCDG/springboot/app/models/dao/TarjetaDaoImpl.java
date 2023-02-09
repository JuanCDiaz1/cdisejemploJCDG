package com.cdisejemploJCDG.springboot.app.models.dao;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.cdisejemploJCDG.springboot.app.models.entity.Tarjeta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class TarjetaDaoImpl implements ITarjetaDao {

	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Tarjeta> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Tarjeta").getResultList();
	}

	@Override
	@Transactional
	public void save(Tarjeta tarjeta) {
		if(tarjeta.getId() != null&&tarjeta.getId()>0){
			em.merge(tarjeta);
		}else {
			em.persist(tarjeta);
		}
	}
	@Override
	@Transactional(readOnly=true)
	public Tarjeta findOne(Long id) {
		return em.find(Tarjeta.class, id);
	}
	
	@Override
	@Transactional
	public void delete(Long id) {
		 em.remove(findOne(id));
	}


	
	
	
	
	
	
	
}

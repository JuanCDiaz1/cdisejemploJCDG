package com.cdisejemploJCDG.springboot.app.models.dao;

import java.util.List;

import com.cdisejemploJCDG.springboot.app.models.entity.Cuenta;
import com.cdisejemploJCDG.springboot.app.models.entity.Tarjeta;

public interface ICuentaDao {
	
 public List<Cuenta> findAll();
 
 public void save  (Cuenta cuenta);
 
 public Cuenta findOne(Long id);
 
 public void delete(Long id);
}

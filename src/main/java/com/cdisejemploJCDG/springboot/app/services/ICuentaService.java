package com.cdisejemploJCDG.springboot.app.services;

import java.util.List;

import com.cdisejemploJCDG.springboot.app.models.entity.Cuenta;

public interface ICuentaService {
public Cuenta getById(Long id, List<Cuenta> lista); 
	
}

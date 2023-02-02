package com.cdisejemploJCDG.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name="cuentas")
public class Cuenta implements Serializable{

	private static final long serialVersionUID = 8411871487468601509L;
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
   @Column
   @NotEmpty
   private String nombre;
	   
   
   @Column
   @NonNull
   private double Saldo;
   
   @Column(name="numero_telefono")
   private String numeroTelefono;
   
   @Column(name="dia_creacion")
   @NonNull
   @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern="yyyy-MM-dd")
   private Date diaCreacion;

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

public double getSaldo() {
	return Saldo;
}

public void setSaldo(double saldo) {
	Saldo = saldo;
} 

public String getNumeroTelefono() {
	return numeroTelefono;
}

public void setNumeroTelefono(String numeroTelefono) {
	this.numeroTelefono = numeroTelefono;
}

public Date getDiaCreacion() {
	return diaCreacion;
}

public void setDiaCreacion(Date diaCreacion) {
	this.diaCreacion = diaCreacion;
}

public static long getSerialversionuid() {
	return serialVersionUID;
}
}

package com.cdisejemploJCDG.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table (name="tarjetas")
public class Tarjeta implements Serializable {

	private static final long serialVersionUID = -3849618957302312185L;

	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="numero_tarjeta", nullable =false, length=16)
	private String  numeroTarjeta;
	
	@Column(name="icv", nullable =false, length=3)
	@NotEmpty
	private String  icv;
	
	@Column(name="vencimiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyy-MM-dd")
	private Date vencimiento;
	
	@JoinColumn(name="cuenta", referencedColumnName="id", nullable= false)
	@ManyToOne(optional= false, fetch=FetchType.LAZY)
	private Cuenta cuenta;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getIcv() {
		return icv;
	}

	public void setIcv(String icv) {
		this.icv = icv;
	}

	public Date getVencimiento() {
		return vencimiento;
	}

	public void setVencimiento(Date vencimiento) {
		this.vencimiento = vencimiento;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}

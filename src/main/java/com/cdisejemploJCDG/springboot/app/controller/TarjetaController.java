package com.cdisejemploJCDG.springboot.app.controller;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cdisejemploJCDG.springboot.app.editors.CuentaPropertyEditor;
import com.cdisejemploJCDG.springboot.app.errors.DataBaseBancoException;
import com.cdisejemploJCDG.springboot.app.models.dao.ICuentaDao;
import com.cdisejemploJCDG.springboot.app.models.dao.ITarjetaDao;
import com.cdisejemploJCDG.springboot.app.models.entity.Cuenta;
import com.cdisejemploJCDG.springboot.app.models.entity.Tarjeta;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("tarjeta")

public class TarjetaController {

	@Autowired
	private ITarjetaDao tarjetaDao;
	
	@Autowired
	private ICuentaDao cuentaDao;
	
	@Autowired
	private CuentaPropertyEditor cuentaEditor;
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Cuenta.class, "cuenta", cuentaEditor);
	}
	
	@RequestMapping(value = "/tarjetas-lista", method = RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo", "Lista de tarjetas");
		model.addAttribute("tarjetas", tarjetaDao.findAll());
		return "tarjetas-lista";
	}
	
	@RequestMapping(value = "/formtarjeta")
	public String crear(Map<String, Object> model, Model modelList) {
		Tarjeta tarjeta =  new Tarjeta();
		List<Cuenta> listaCuentas = cuentaDao.findAll();
		model.put("tarjeta", tarjeta);
		modelList.addAttribute("listaCuentas", listaCuentas);
		model.put("titulo", "Llenar los datos de una tarjeta");
		return "formtarjeta";
	}
	 
	@RequestMapping(value= "/formtarjeta/{id}")
	public String editar(@PathVariable(value="id")Long id, Map<String, Object> model) {
		Tarjeta tarjeta = null;
		if (id!=null&&id>0) {
			tarjeta=tarjetaDao.findOne(id);
		} else {
			return "index";
			}
		model.put("tarjeta", tarjeta);
		model.put("titulo", "Editar tarjeta");
				
	   return "formtarjeta";
      }
	@RequestMapping(value = "/formtarjeta", method = RequestMethod.POST)
	public String guardar(@Valid Tarjeta tarjeta, BindingResult result, Model model, SessionStatus status, RedirectAttributes flash) {

		if(result.hasErrors()) {
			model.addAttribute("titulo", "Llene correctamente los campos");
			model.addAttribute("result", result.hasErrors());
			model.addAttribute("mensaje", "Error al enviar los datos, por favor escriba correctamente los campos");
			return "formtarjeta";
		} else {
			model.addAttribute("result", false);
		}
		Cuenta cuenta = cuentaDao.findOne(tarjeta.getCuenta().getId());
		List<Tarjeta> tarjetas = cuenta.getTarjetas();
		if(tarjetas.size() < 2 ) {
			tarjetas.add(tarjeta);
			cuenta.setTarjetas(tarjetas);
		} else { 
			flash.addFlashAttribute("mensaje", "La cuenta no puede tener mas de 2 tarjetas");
			return "redirect:formtarjeta";
		}
		model.addAttribute("titulo", "Formulario de tarjeta");
		model.addAttribute("mensaje", "Se envio la informacion correctamente");
		try {
			tarjetaDao.save(tarjeta);
			cuentaDao.save(cuenta);
		} catch (DataBaseBancoException e) {
			e.printStackTrace();
			flash.addFlashAttribute("mensaje", e.getMessage());
		}
		status.setComplete();	

		return "redirect:formtarjeta";
	}
	
	
	@RequestMapping(value= "/eliminar/{id}")
	public String eliminar(@PathVariable(value= "id")Long id) {
	if(id!=null&&id>0) {
		tarjetaDao.delete(id);
	}
	return "redirect:/tarjetas-lista";
		
	}
	 
   }


package com.davidFontes.projetoMetas.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davidFontes.projetoMetas.entities.Venda;
import com.davidFontes.projetoMetas.services.SmsService;
import com.davidFontes.projetoMetas.services.VendaService;

@RestController
@RequestMapping("vendas")
public class VendaController {

	@Autowired
	private VendaService vendaService;
	
	@Autowired
	private SmsService smsService;
	
	@GetMapping
	public ResponseEntity<List<Venda>> buscaTodos(){
		
		return ResponseEntity.ok(vendaService.buscaTodos());
	}
	
	@GetMapping("page")
	public ResponseEntity<Page<Venda>> buscaTodosComPaginacao(
			@RequestParam(value="dataMin", defaultValue = "") String min, 
			@RequestParam(value="dataMax", defaultValue = "") String max,
			Pageable pageable){
		
		return ResponseEntity.ok(vendaService.buscaTodosComPaginacao(min, max, pageable));
	}
	
	@GetMapping("/{id}/notificacao")
	public void notificacaoDeSMS(@PathVariable Long id) {
		smsService.enviaSms(id);
	}
}

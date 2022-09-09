package com.davidFontes.projetoMetas.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.davidFontes.projetoMetas.entities.Venda;
import com.davidFontes.projetoMetas.repositories.VendaRepository;

@Service
public class VendaService {

	// otNjnS6IU1imOkfvnnppH1100S65bNJeyfS97krI = Twilio

	@Autowired
	private VendaRepository repo;

	public Venda buscaPorId(Long id) {
		return repo.findById(id).get();
	}

	public List<Venda> buscaTodos() {
		return repo.findAll();
	}

	public Page<Venda> buscaTodosComPaginacao(String min, String max, Pageable pageable) {

		LocalDate hoje = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

		LocalDate dataMin = min.equals("") ? hoje.minusYears(1) : LocalDate.parse(max);
		LocalDate dataMax = max.equals("") ? hoje : LocalDate.parse(max);

		System.out.println(dataMin);

		return repo.buscaVendas(dataMin, dataMax, pageable);
	}
}

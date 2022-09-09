package com.davidFontes.projetoMetas.repositories;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.davidFontes.projetoMetas.entities.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

	@Query("SELECT obj FROM Venda obj WHERE obj.data BETWEEN :min AND :max ORDER BY obj.total DESC")
	Page<Venda> buscaVendas(LocalDate min, LocalDate max, Pageable pageable);
}

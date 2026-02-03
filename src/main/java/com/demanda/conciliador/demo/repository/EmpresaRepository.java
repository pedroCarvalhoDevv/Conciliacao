package com.demanda.conciliador.demo.repository;

import com.demanda.conciliador.demo.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    boolean existsByIdMateus(String idMateus);

    Optional<Empresa> findByIdMateus(String idMateus);
}
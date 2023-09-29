package com.example.demo.repository;

import com.example.demo.model.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PessoaFisicaRepository extends JpaRepository<PessoaFisica, Long> {
    Optional<PessoaFisica> findByCpf(String cpf);
}

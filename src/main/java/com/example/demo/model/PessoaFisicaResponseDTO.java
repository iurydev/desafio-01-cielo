package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PessoaFisicaResponseDTO extends PessoaFisicaRequestDTO {
    TipoCliente tipoCliente;
    LocalDateTime dataCadastro;
    private Long id;

    public PessoaFisicaResponseDTO(String nome, String email, String cpf, String mcc, TipoCliente tipoCliente, LocalDateTime dataCadastro, Long id) {
        super(nome, email, cpf, mcc);
        this.tipoCliente = tipoCliente;
        this.dataCadastro = dataCadastro;
        this.id = id;
    }

    public PessoaFisicaResponseDTO() {

    }
}

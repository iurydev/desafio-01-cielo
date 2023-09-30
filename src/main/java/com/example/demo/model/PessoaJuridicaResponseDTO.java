package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PessoaJuridicaResponseDTO extends PessoaJuridicaRequestDTO {
    TipoCliente tipoCliente;
    LocalDateTime dataCadastro;
    private Long id;

    public PessoaJuridicaResponseDTO(String razaoSocial, String cnpj, String nomeContato, String cpfContato, String emailContato, String mcc, TipoCliente tipoCliente, LocalDateTime dataCadastro, Long id) {
        super(razaoSocial, cnpj, nomeContato, cpfContato, emailContato, mcc);
        this.tipoCliente = tipoCliente;
        this.dataCadastro = dataCadastro;
        this.id = id;
    }

    public PessoaJuridicaResponseDTO() {
    }
}

package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class PessoaFisica extends Cliente {
    @Column()
    String nome;
    @Column()
    String email;
    @Column()
    String cpf;

    public PessoaFisica(TipoCliente tipoCliente, String mcc, String nome, String email, String cpf) {
        super(tipoCliente, mcc);
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public PessoaFisica() {
    }

    public static PessoaFisica fromDTO(PessoaFisicaRequestDTO pessoaFisicaRequestDTO) {
        PessoaFisica pessoaFisica = new PessoaFisica();
        pessoaFisica.setMcc(pessoaFisicaRequestDTO.getMcc());
        pessoaFisica.setNome(pessoaFisicaRequestDTO.getNome());
        pessoaFisica.setEmail(pessoaFisicaRequestDTO.getEmail());
        pessoaFisica.setCpf(pessoaFisicaRequestDTO.getCpf());
        return pessoaFisica;
    }

    public PessoaFisicaResponseDTO toDTO() {
        PessoaFisicaResponseDTO pessoaFisicaResponseDTO = new PessoaFisicaResponseDTO();
        pessoaFisicaResponseDTO.setId(this.getId());
        pessoaFisicaResponseDTO.setMcc(this.getMcc());
        pessoaFisicaResponseDTO.setNome(this.getNome());
        pessoaFisicaResponseDTO.setEmail(this.getEmail());
        pessoaFisicaResponseDTO.setCpf(this.getCpf());
        pessoaFisicaResponseDTO.setDataCadastro(this.getDataCadastro());
        pessoaFisicaResponseDTO.setTipoCliente(this.getTipoCliente());
        return pessoaFisicaResponseDTO;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

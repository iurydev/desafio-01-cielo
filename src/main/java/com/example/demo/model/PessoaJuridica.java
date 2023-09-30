package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;


@Getter
@Entity
public class PessoaJuridica extends Cliente {

    @Column(name = "razao_social")
    String razaoSocial;
    @Column()
    String cnpj;
    @Column(name = "nome_contato")
    String nomeContato;
    @Column(name = "cpf_contato")
    String cpfContato;
    @Column(name = "email_contato")
    String emailContato;

    public PessoaJuridica(TipoCliente tipoCliente, String mcc, String razaoSocial, String cnpj, String nomeContato, String cpfContato, String emailContato) {
        super(tipoCliente, mcc);
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeContato = nomeContato;
        this.cpfContato = cpfContato;
        this.emailContato = emailContato;
    }

    public PessoaJuridica() {
    }

    public static PessoaJuridica fromDTO(PessoaJuridicaRequestDTO pessoaJuridicaRequestDTO) {
        PessoaJuridica pessoaJuridica = new PessoaJuridica();
        pessoaJuridica.setMcc(pessoaJuridicaRequestDTO.getMcc());
        pessoaJuridica.setRazaoSocial(pessoaJuridicaRequestDTO.getRazaoSocial());
        pessoaJuridica.setCnpj(pessoaJuridicaRequestDTO.getCnpj());
        pessoaJuridica.setCpfContato(pessoaJuridicaRequestDTO.getCpfContato());
        pessoaJuridica.setEmailContato(pessoaJuridicaRequestDTO.getEmailContato());
        pessoaJuridica.setNomeContato(pessoaJuridicaRequestDTO.getNomeContato());
        return pessoaJuridica;
    }

    public PessoaJuridicaResponseDTO toDTO() {
        PessoaJuridicaResponseDTO pessoaJuridicaResponseDTO = new PessoaJuridicaResponseDTO();
        pessoaJuridicaResponseDTO.setId(this.getId());
        pessoaJuridicaResponseDTO.setMcc(this.getMcc());
        pessoaJuridicaResponseDTO.setDataCadastro(this.getDataCadastro());
        pessoaJuridicaResponseDTO.setTipoCliente(this.getTipoCliente());
        pessoaJuridicaResponseDTO.setCnpj(this.getCnpj());
        pessoaJuridicaResponseDTO.setCpfContato(this.getCpfContato());
        pessoaJuridicaResponseDTO.setEmailContato(this.getEmailContato());
        pessoaJuridicaResponseDTO.setNomeContato(this.getNomeContato());
        pessoaJuridicaResponseDTO.setRazaoSocial(this.getRazaoSocial());
        return pessoaJuridicaResponseDTO;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public void setCpfContato(String cpfContato) {
        this.cpfContato = cpfContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }
}

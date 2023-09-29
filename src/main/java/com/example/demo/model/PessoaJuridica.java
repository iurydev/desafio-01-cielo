package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;


@Entity
public class PessoaJuridica extends Cliente {

    @Column(name = "razao_social")
    @NotEmpty(message = "Campo RAZÃO SOCIAL é obrigatória")
    @Length(max = 50, message = "A Razão Social deve ter no máximo 50 caracteres")
    String razaoSocial;
    @Column()
    @NotEmpty(message = "Campo CNPJ é obrigatório")
    @CNPJ(message = "CNPJ inválido")
    String cnpj;
    @Column(name = "nome_contato")
    @NotEmpty(message = "Campo NOME DO CONTATO é obrigatório")
    @Length(max = 50, message = "O Nome do contato deve ter no máximo 50 caracteres")
    String nomeContato;
    @Column(name = "cpf_contato")
    @NotEmpty(message = "Campo CPF DO CONTATO é obrigatório")
    @Pattern(regexp = "^[0-9]{11}$", message = "CPF do contato inválido")
    String cpfContato;
    @Column(name = "email_contato")
    @NotEmpty(message = "Campo EMAIL DO CONTATO é obrigatório")
    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email do contato inválido")
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

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeContato() {
        return nomeContato;
    }

    public void setNomeContato(String nomeContato) {
        this.nomeContato = nomeContato;
    }

    public String getCpfContato() {
        return cpfContato;
    }

    public void setCpfContato(String cpfContato) {
        this.cpfContato = cpfContato;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }
}

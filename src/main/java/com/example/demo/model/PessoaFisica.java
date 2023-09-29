package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

@Getter
@Entity
public class PessoaFisica extends Cliente {
    @Column()
    @NotEmpty(message = "Campo NOME é obrigatório")
    @Length(max = 50, message = "Campo nome deve ter no máximo 50 caracteres")
    String nome;
    @Column()
    @NotEmpty(message = "Campo EMAIL é obrigatório")
    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email inválido")
    String email;
    @Column()
    @NotEmpty(message = "Campo CPF é obrigatório")
    @Pattern(regexp = "^[0-9]{11}$", message = "CPF inválido")
    String cpf;

    public PessoaFisica(TipoCliente tipoCliente, String mcc, String nome, String email, String cpf) {
        super(tipoCliente, mcc);
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
    }

    public PessoaFisica() {
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

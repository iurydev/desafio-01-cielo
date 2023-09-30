package com.example.demo.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class PessoaFisicaRequestDTO {
    @NotEmpty(message = "Campo NOME é obrigatório")
    @Length(max = 50, message = "Campo nome deve ter no máximo 50 caracteres")
    private String nome;

    @NotEmpty(message = "Campo EMAIL é obrigatório")
    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email inválido")
    private String email;

    @NotEmpty(message = "Campo CPF é obrigatório")
    @Pattern(regexp = "^[0-9]{11}$", message = "CPF inválido")
    private String cpf;

    @NotEmpty(message = "O MCC é obrigatório.")
    @Size(max = 4, message = "O MCC deve ter no máximo 4 caracteres.")
    String mcc;

    public PessoaFisicaRequestDTO(String nome, String email, String cpf, String mcc) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.mcc = mcc;
    }

    public PessoaFisicaRequestDTO() {}
}

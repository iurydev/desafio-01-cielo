package com.example.demo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

@Getter
@Setter
public class PessoaJuridicaRequestDTO {
    @NotEmpty(message = "Campo RAZÃO SOCIAL é obrigatória")
    @Length(max = 50, message = "A Razão Social deve ter no máximo 50 caracteres")
    String razaoSocial;
    @NotEmpty(message = "Campo CNPJ é obrigatório")
    @CNPJ(message = "CNPJ inválido")
    String cnpj;
    @NotEmpty(message = "Campo NOME DO CONTATO é obrigatório")
    @Length(max = 50, message = "O Nome do contato deve ter no máximo 50 caracteres")
    String nomeContato;
    @NotEmpty(message = "Campo CPF DO CONTATO é obrigatório")
    @Pattern(regexp = "^[0-9]{11}$", message = "CPF do contato inválido")
    String cpfContato;
    @NotEmpty(message = "Campo EMAIL DO CONTATO é obrigatório")
    @Email(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", message = "Email do contato inválido")
    String emailContato;
    @NotEmpty(message = "O MCC é obrigatório.")
    @Size(max = 4, message = "O MCC deve ter no máximo 4 caracteres.")
    String mcc;

    public PessoaJuridicaRequestDTO(String razaoSocial, String cnpj, String nomeContato, String cpfContato, String emailContato, String mcc) {
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.nomeContato = nomeContato;
        this.cpfContato = cpfContato;
        this.emailContato = emailContato;
        this.mcc = mcc;
    }

    public PessoaJuridicaRequestDTO(){}
}

package com.example.demo.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "CLIENTE")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners(AuditingEntityListener.class)
public abstract class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "tipo_cliente")
    @Enumerated(EnumType.STRING)
    TipoCliente tipoCliente;
    @Column(name = "data_cadastro")
    @CreatedDate
    LocalDateTime dataCadastro;
    @NotEmpty(message = "O MCC é obrigatório.")
    @Size(max = 4, message = "O MCC deve ter no máximo 4 caracteres.")
    String mcc;

    protected Cliente(TipoCliente tipoCliente, String mcc) {
        this.uuid = gerarUuid();
        this.tipoCliente = tipoCliente;
        this.mcc = mcc;
    }

    protected Cliente() {
        this.uuid = gerarUuid();
    }

    private UUID gerarUuid() {
        return UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }


}

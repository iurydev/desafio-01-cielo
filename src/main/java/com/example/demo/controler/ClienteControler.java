package com.example.demo.controler;

import com.example.demo.model.Cliente;
import com.example.demo.model.PessoaFisica;
import com.example.demo.model.PessoaJuridica;
import com.example.demo.repository.PessoaFisicaRepository;
import com.example.demo.repository.PessoaJuridicaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cliente")

public class ClienteControler {
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public ClienteControler(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAll() {
        List<Cliente> clientes = new ArrayList<>();
        List<PessoaFisica> ClientesPf = pessoaFisicaRepository.findAll();
        List<PessoaJuridica> ClientesPj = pessoaJuridicaRepository.findAll();
        clientes.addAll(ClientesPf);
        clientes.addAll(ClientesPj);
        return new ResponseEntity<>(clientes,HttpStatus.OK);
    }
}

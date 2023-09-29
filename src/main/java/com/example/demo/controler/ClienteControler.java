package com.example.demo.controler;

import com.example.demo.model.Cliente;
import com.example.demo.model.PessoaFisica;
import com.example.demo.model.PessoaJuridica;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.PessoaFisicaRepository;
import com.example.demo.repository.PessoaJuridicaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")

public class ClienteControler {
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final        ClienteRepository clienteRepository ;

    public ClienteControler(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaFisicaRepository pessoaFisicaRepository, ClienteRepository clienteRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.clienteRepository = clienteRepository;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Cliente>> consultar(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            return new ResponseEntity("Cliente não encontrado!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Cliente>>(cliente, HttpStatus.OK);

    }
    @GetMapping
    public ResponseEntity<List<Cliente>> consultarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        List<PessoaFisica> ClientesPf = pessoaFisicaRepository.findAll();
        List<PessoaJuridica> ClientesPj = pessoaJuridicaRepository.findAll();
        clientes.addAll(ClientesPf);
        clientes.addAll(ClientesPj);
        return new ResponseEntity<>(clientes,HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            return new ResponseEntity<>("Cliente não encontrado!", HttpStatus.NOT_FOUND);
        }
        clienteRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

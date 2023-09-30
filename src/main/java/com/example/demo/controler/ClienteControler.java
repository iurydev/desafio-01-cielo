package com.example.demo.controler;

import com.example.demo.model.Cliente;
import com.example.demo.model.PessoaFisica;
import com.example.demo.model.PessoaJuridica;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.PessoaFisicaRepository;
import com.example.demo.repository.PessoaJuridicaRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@Tag(name = "Pré-cadastros", description = "Operações para (consulta) e (exclusão) de clientes")
public class ClienteControler {
    private final PessoaFisicaRepository pessoaFisicaRepository;
    private final PessoaJuridicaRepository pessoaJuridicaRepository;
    private final        ClienteRepository clienteRepository ;

    public ClienteControler(PessoaJuridicaRepository pessoaJuridicaRepository, PessoaFisicaRepository pessoaFisicaRepository, ClienteRepository clienteRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
        this.clienteRepository = clienteRepository;
    }

    @Operation(
            summary = "Retornar um cliente pelo ID",
            description = "Retorna um Objeto (Cliente)")
    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Consultar cliente", notes = "Retorna os dados de um cliente via ID")
    public ResponseEntity<Optional<Cliente>> consultar(@ApiParam(value = "ID do cliente", required = true) @PathVariable Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isEmpty()) {
            return new ResponseEntity("Cliente não encontrado!", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Optional<Cliente>>(cliente, HttpStatus.OK);

    }

    @Operation(
            summary = "Retornar todos os clientes cadastrados no sistema",
            description = "Retorna um array de objetos do tipo (Cliente)")
    @GetMapping
    public ResponseEntity<List<Cliente>> consultarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        List<PessoaFisica> ClientesPf = pessoaFisicaRepository.findAll();
        List<PessoaJuridica> ClientesPj = pessoaJuridicaRepository.findAll();
        clientes.addAll(ClientesPf);
        clientes.addAll(ClientesPj);
        return new ResponseEntity<>(clientes,HttpStatus.OK);
    }

    @Operation(
            summary = "Delete um cliente pelo ID",
            description = "")
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

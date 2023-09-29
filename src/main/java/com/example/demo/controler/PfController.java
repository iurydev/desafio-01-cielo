package com.example.demo.controler;
import com.example.demo.model.PessoaFisica;
import com.example.demo.repository.PessoaFisicaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/cliente/pf")

public class PfController {
    private final PessoaFisicaRepository pessoaFisicaRepository;

    public PfController(PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<PessoaFisica>> getById(@PathVariable Long id) {
        Optional<PessoaFisica> pessoaFisica;
        try {
            pessoaFisica = pessoaFisicaRepository.findById(id);
            return new ResponseEntity<Optional<PessoaFisica>>(pessoaFisica, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<PessoaFisica>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PessoaFisica>> getAll() {
        List<PessoaFisica> listaPf;
        listaPf = pessoaFisicaRepository.findAll();
        return new ResponseEntity<>(listaPf, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> save(@Valid @RequestBody PessoaFisica pessoaFisica) {
        pessoaFisicaRepository.save(pessoaFisica);
        return new ResponseEntity<>(pessoaFisica, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<PessoaFisica>> deleteById(@PathVariable Long id) {
        try {
            pessoaFisicaRepository.deleteById(id);
            return new ResponseEntity<Optional<PessoaFisica>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<PessoaFisica>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaFisica> update(@PathVariable Long id, @RequestBody PessoaFisica novosDadosPf) {
        return pessoaFisicaRepository.findById(id).map(pessoaFisica -> {
            pessoaFisica.setNome((novosDadosPf.getNome()));
            pessoaFisica.setCpf((novosDadosPf.getCpf()));
            pessoaFisica.setEmail((novosDadosPf.getEmail()));
            pessoaFisica.setMcc((novosDadosPf.getMcc()));
            PessoaFisica dadosPfAtualizados = pessoaFisicaRepository.save(pessoaFisica);
            return ResponseEntity.ok().body(dadosPfAtualizados);
        }).orElse(ResponseEntity.notFound().build());
    }
}

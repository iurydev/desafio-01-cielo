package com.example.demo.controler;
import com.example.demo.model.PessoaJuridica;
import com.example.demo.repository.PessoaJuridicaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/cliente/pj")

public class PjController {
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public PjController(PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<PessoaJuridica>> consultar(@PathVariable Long id) {
        Optional<PessoaJuridica> pessoaJuridica;
        try {
            pessoaJuridica = pessoaJuridicaRepository.findById(id);
            return new ResponseEntity<Optional<PessoaJuridica>>(pessoaJuridica, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<PessoaJuridica>>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<PessoaJuridica>> consultarTodos() {
        List<PessoaJuridica> listaPj;
        listaPj = pessoaJuridicaRepository.findAll();
        return new ResponseEntity<>(listaPj, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> cadastrar(@Valid  @RequestBody PessoaJuridica pessoaJuridica) {
        pessoaJuridicaRepository.save(pessoaJuridica);
        return new ResponseEntity<>(pessoaJuridica, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<PessoaJuridica>> exclusao(@PathVariable Long id) {
        try {
            pessoaJuridicaRepository.deleteById(id);
            return new ResponseEntity<Optional<PessoaJuridica>>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Optional<PessoaJuridica>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PessoaJuridica> alteracao(@PathVariable Long id, @RequestBody PessoaJuridica novosDadosPj) {
        return pessoaJuridicaRepository.findById(id).map(pessoaJuridica -> {
            pessoaJuridica.setCnpj((novosDadosPj.getCnpj()));
            pessoaJuridica.setCpfContato((novosDadosPj.getCpfContato()));
            pessoaJuridica.setEmailContato((novosDadosPj.getEmailContato()));
            pessoaJuridica.setNomeContato((novosDadosPj.getNomeContato()));
            pessoaJuridica.setRazaoSocial((novosDadosPj.getRazaoSocial()));
            PessoaJuridica dadosPjAtualizados = pessoaJuridicaRepository.save(pessoaJuridica);
            return ResponseEntity.ok().body(dadosPjAtualizados);
        }).orElse(ResponseEntity.notFound().build());
    }
}


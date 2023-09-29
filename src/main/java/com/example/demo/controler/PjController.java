package com.example.demo.controler;
import com.example.demo.model.PessoaJuridica;
import com.example.demo.model.TipoCliente;
import com.example.demo.repository.PessoaJuridicaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente/pj")

public class PjController {
    private final PessoaJuridicaRepository pessoaJuridicaRepository;

    public PjController(PessoaJuridicaRepository pessoaJuridicaRepository) {
        this.pessoaJuridicaRepository = pessoaJuridicaRepository;
    }

    @PostMapping()
    public ResponseEntity<Optional<PessoaJuridica>> cadastrar(@Valid  @RequestBody PessoaJuridica pessoaJuridica, HttpServletRequest request) {
        // NOTE: Verifica existência do cliente
        Optional<PessoaJuridica> verificacaoCliente = pessoaJuridicaRepository.findByCnpj(pessoaJuridica.getCnpj());
        if (verificacaoCliente.isPresent()) {
            return new ResponseEntity("Cliente já cadastrado com o mesmo CNPJ.", HttpStatus.CONFLICT);
        }
        // NOTE: Verifica tipo do cliente
        String tipoClienteString = (String) request.getAttribute("tipoCliente");
        TipoCliente tipoCliente = TipoCliente.valueOf(tipoClienteString.toUpperCase());
        pessoaJuridica.setTipoCliente(tipoCliente);
        pessoaJuridicaRepository.save(pessoaJuridica);
        return new ResponseEntity(pessoaJuridica, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> alterar( @PathVariable Long id, @RequestBody @Valid PessoaJuridica novosDadosPj, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map((ObjectError t) -> t.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        Optional<PessoaJuridica> pessoaJuridica = pessoaJuridicaRepository.findById(id);
        if (pessoaJuridica.isEmpty()) {
            return new ResponseEntity("Cliente não encontrado!", HttpStatus.NOT_FOUND);
        }

        PessoaJuridica pessoaJuridicaAtualizada = pessoaJuridica.get();
        pessoaJuridicaAtualizada.setNomeContato(novosDadosPj.getNomeContato());
        pessoaJuridicaAtualizada.setEmailContato(novosDadosPj.getEmailContato());
        pessoaJuridicaAtualizada.setMcc(novosDadosPj.getMcc());
        pessoaJuridicaAtualizada.setCnpj(novosDadosPj.getCnpj());
        pessoaJuridicaAtualizada.setRazaoSocial(novosDadosPj.getRazaoSocial());
        pessoaJuridicaAtualizada.setCpfContato(novosDadosPj.getCpfContato());
        pessoaJuridicaRepository.saveAndFlush(pessoaJuridicaAtualizada);
        return ResponseEntity.ok().body(pessoaJuridicaAtualizada);
    }
}


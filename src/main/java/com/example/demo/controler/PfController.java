package com.example.demo.controler;
import com.example.demo.model.PessoaFisica;
import com.example.demo.model.TipoCliente;
import com.example.demo.repository.PessoaFisicaRepository;
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
@RequestMapping("/cliente/pf")

public class PfController {
    private final PessoaFisicaRepository pessoaFisicaRepository;

    public PfController(PessoaFisicaRepository pessoaFisicaRepository) {
        this.pessoaFisicaRepository = pessoaFisicaRepository;
    }

    @PostMapping()
    public ResponseEntity<Optional<PessoaFisica>> cadastrar(@Valid @RequestBody PessoaFisica pessoaFisica,  HttpServletRequest request) {
        // NOTE: Verifica existência do cliente
        Optional<PessoaFisica> verificacaoCliente = pessoaFisicaRepository.findByCpf(pessoaFisica.getCpf());
        if (verificacaoCliente.isPresent()) {
            return new ResponseEntity("Cliente já cadastrado com o mesmo CPF!", HttpStatus.CONFLICT);
        }
        // NOTE: Verifica tipo do cliente
        String tipoClienteString = (String) request.getAttribute("tipoCliente");
        TipoCliente tipoCliente = TipoCliente.valueOf(tipoClienteString.toUpperCase());
        pessoaFisica.setTipoCliente(tipoCliente);

        pessoaFisicaRepository.save(pessoaFisica);
        return new ResponseEntity(pessoaFisica, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> alterar(@PathVariable Long id, @RequestBody @Valid PessoaFisica novosDadosPf, BindingResult bindingResult) {
        //NOTE: Verifica erros de validação nos campos
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getAllErrors()
                    .stream()
                    .map((ObjectError t) -> t.getDefaultMessage())
                    .collect(Collectors.toList());

            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        Optional<PessoaFisica> pessoaFisica = pessoaFisicaRepository.findById(id);
        if (pessoaFisica.isEmpty()) {
            return new ResponseEntity("Cliente não encontrado!", HttpStatus.NOT_FOUND);
        }

        PessoaFisica pessoaFisicaAtualizada = pessoaFisica.get();
        pessoaFisicaAtualizada.setNome(novosDadosPf.getNome());
        pessoaFisicaAtualizada.setEmail(novosDadosPf.getEmail());
        pessoaFisicaAtualizada.setMcc(novosDadosPf.getMcc());
        pessoaFisicaRepository.saveAndFlush(pessoaFisicaAtualizada);
        return ResponseEntity.ok().body(pessoaFisicaAtualizada);
    }
}

package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaModel;
import br.com.senai.api.model.input.PessoaInput;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;
    private PessoaAssembler pessoaAssembler;

    @GetMapping()
    public List<PessoaModel> listar() {
        return pessoaService.listar();
    }

    @GetMapping("/nome/{pessoaNome}")
    public List<PessoaModel> listarPorNome(@PathVariable String pessoaNome) {
        return pessoaService.listarPorNome(pessoaNome);
    }

    @GetMapping("/nome/containing/{nomeContaining}")
    public List<PessoaModel> listarNomeContaining(@PathVariable String nomeContaining) {
        return pessoaService.listarNomeContaining(nomeContaining);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<PessoaModel> procurar(@PathVariable long pessoaId){
        return pessoaService.procurar(pessoaId);
    }

    //
    @PostMapping
    public PessoaModel cadastrar(@Valid @RequestBody PessoaInput pessoaInput) {
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInput);
        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);

        return pessoaAssembler.toModel(pessoa);
    }

    //passar pessoainput
    @PutMapping("/{pessoaId}")
    public PessoaModel editar(@Valid @RequestBody PessoaInput pessoaInput, @PathVariable long pessoaId){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInput);
        ResponseEntity<Pessoa> pessoaResponseEntity = pessoaService.editar(pessoaId, novaPessoa);

        return pessoaAssembler.toModel(pessoaResponseEntity.getBody());
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> remover(@Valid @PathVariable long pessoaId) {

        pessoaService.excluir(pessoaId);

        return ResponseEntity.noContent().build();
    }
}

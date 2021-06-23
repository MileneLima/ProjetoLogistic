package br.com.senai.api.controller;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.input.PessoaIdInputDTO;
import br.com.senai.api.model.input.PessoaInputDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.service.PessoaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    public List<PessoaDTO> listar() {
        return pessoaService.listar();
    }

    @GetMapping("/nome/{pessoaNome}")
    public List<PessoaDTO> listarPorNome(@PathVariable String pessoaNome) {
        return pessoaService.listarPorNome(pessoaNome);
    }

    @GetMapping("/nome/containing/{nomeContaining}")
    public List<PessoaDTO> listarNomeContaining(@PathVariable String nomeContaining) {
        return pessoaService.listarNomeContaining(nomeContaining);
    }

    @GetMapping("/{pessoaId}")
    public ResponseEntity<PessoaDTO> procurar(@PathVariable long pessoaId){
        return pessoaService.procurar(pessoaId);
    }

    @PostMapping
    public PessoaDTO cadastrar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO) {
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        novaPessoa.getUsuario().setSenha(
                new BCryptPasswordEncoder().encode(pessoaInputDTO.getUsuario().getSenha()));

        Pessoa pessoa = pessoaService.cadastrar(novaPessoa);

        return pessoaAssembler.toModel(pessoa);
    }

    @PutMapping("/{pessoaId}")
    public PessoaDTO editar(@Valid @RequestBody PessoaInputDTO pessoaInputDTO, @PathVariable long pessoaId){
        Pessoa novaPessoa = pessoaAssembler.toEntity(pessoaInputDTO);
        ResponseEntity<Pessoa> pessoaResponseEntity = pessoaService.editar(pessoaId, novaPessoa);

        return pessoaAssembler.toModel(pessoaResponseEntity.getBody());
    }

    @DeleteMapping("/{pessoaId}")
    public ResponseEntity<Pessoa> remover(@Valid @PathVariable long pessoaId) {

        pessoaService.excluir(pessoaId);

        return ResponseEntity.noContent().build();
    }
}

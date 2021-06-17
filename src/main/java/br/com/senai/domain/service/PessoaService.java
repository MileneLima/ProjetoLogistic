package br.com.senai.domain.service;

import br.com.senai.domain.exception.NegocioException;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.repository.PessoaRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@AllArgsConstructor
@Service
public class PessoaService {

    private PessoaRepository pessoaRepository;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa){
        boolean emailvalidation = pessoaRepository.findByEmail(pessoa.getEmail())
                .isPresent();

        if(emailvalidation) {
            throw new NegocioException("Já existe uma pessoa com este e-mail cadastrado.");
        }
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public void excluir(Long pessoaId) {
        pessoaRepository.deleteById(pessoaId);
    }

    public Pessoa buscar(Long pessoaId) {
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new NegocioException("Pessoa não encontrada."));
    }

    public List<Pessoa> listarNomeContaining(String nomeContaining) {
        return pessoaRepository.findByNomeContaining(nomeContaining);
    }

    public List<Pessoa> listarPorNome(String pessoaNome) {
        return pessoaRepository.findByNome(pessoaNome);
    }

    public List<Pessoa> listar() {
        return pessoaRepository.findAll();
    }

    public ResponseEntity<Pessoa> editar(
           Long pessoaId,
           Pessoa pessoa
    ){
        if(!pessoaRepository.existsById(pessoaId)) {
            return ResponseEntity.notFound().build();
        }

        pessoa.setId(pessoaId);
        pessoa = pessoaRepository.save(pessoa);

        return ResponseEntity.ok(pessoa);
    }

    public ResponseEntity<Pessoa> remover(Long pessoaId){

        if(!pessoaRepository.existsById(pessoaId)) {
            return ResponseEntity.notFound().build();
        }
        excluir(pessoaId);

        return ResponseEntity.noContent().build();
    }

}

package br.com.senai.domain.service;

import br.com.senai.api.assembler.PessoaAssembler;
import br.com.senai.api.model.PessoaDTO;
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
    private PessoaAssembler pessoaAssembler;

    @Transactional
    public Pessoa cadastrar(Pessoa pessoa){
//        boolean emailvalidation = pessoaRepository.findByEmail(pessoa.getEmail())
//                .isPresent();
//
//        if(emailvalidation) {
//            throw new NegocioException("Já existe uma pessoa com este e-mail cadastrado.");
//        }
        return pessoaRepository.save(pessoa);
    }

    @Transactional
    public ResponseEntity<Object> excluir(Long pessoaId){

        if(!pessoaRepository.existsById(pessoaId)){
            return ResponseEntity.notFound().build();
        }

        pessoaRepository.deleteById(pessoaId);
        return ResponseEntity.ok(pessoaId);
    }

    public Pessoa buscar(Long pessoaId){
        return pessoaRepository.findById(pessoaId).orElseThrow(()
                -> new NegocioException("Pessoa não encontrada."));
    }

    public ResponseEntity<PessoaDTO> procurar(Long pessoaId){
        return pessoaRepository.findById(pessoaId).map(entrega ->
                ResponseEntity.ok(pessoaAssembler.toModel(entrega))
        ).orElse(ResponseEntity.notFound().build());
    }

    public List<PessoaDTO> listarNomeContaining(String nomeContaining) {
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNomeContaining(nomeContaining));
    }

    public List<PessoaDTO> listarPorNome(String pessoaNome) {
        return pessoaAssembler.toCollectionModel(pessoaRepository.findByNome(pessoaNome));
    }

    public List<PessoaDTO> listar() {

        return pessoaAssembler.toCollectionModel(pessoaRepository.findAll());
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


}

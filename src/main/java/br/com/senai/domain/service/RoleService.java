package br.com.senai.domain.service;

import br.com.senai.api.assembler.RoleAssembler;
import br.com.senai.api.model.PessoaDTO;
import br.com.senai.api.model.RoleDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.Role;
import br.com.senai.domain.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@Service
public class RoleService {

    private RoleRepository roleRepository;
    private RoleAssembler roleAssembler;

    @Transactional
    public Role cadastrar(Role role){
        return roleRepository.save(role);
    }

    @Transactional
    public ResponseEntity<Object> excluir(@Valid String roleId){

        if(!roleRepository.existsById(roleId)){
            return ResponseEntity.notFound().build();
        }

        roleRepository.deleteById(roleId);
        return ResponseEntity.ok(roleId);
    }

    public List<RoleDTO> listarR() {

        return roleAssembler.toCollectionModel(roleRepository.findAll());
    }

}

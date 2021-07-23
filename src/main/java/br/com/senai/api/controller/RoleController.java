package br.com.senai.api.controller;

import br.com.senai.api.assembler.RoleAssembler;
import br.com.senai.api.model.RoleDTO;
import br.com.senai.api.model.input.RoleInputDTO;
import br.com.senai.domain.model.Role;
import br.com.senai.domain.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas/role")
public class RoleController {

    private RoleService roleService;
    private RoleAssembler roleAssembler;

    @GetMapping()
    public List<RoleDTO> listarR(){
        return roleService.listarR();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public RoleDTO cadastrar(@Valid @RequestBody RoleInputDTO roleInputDTO){
        Role newRole = roleAssembler.toEntity(roleInputDTO);
        Role role = roleService.cadastrar(newRole);

        return roleAssembler.toModel(role);
    }
    @DeleteMapping("/{roleId}")
    public ResponseEntity<Role> remover(@Valid @PathVariable String roleId){
        roleService.excluir(roleId);
        return ResponseEntity.noContent().build();
    }
}

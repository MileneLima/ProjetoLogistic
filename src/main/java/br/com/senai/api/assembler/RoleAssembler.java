package br.com.senai.api.assembler;

import br.com.senai.api.model.RoleDTO;
import br.com.senai.api.model.input.PessoaInputDTO;
import br.com.senai.api.model.input.RoleInputDTO;
import br.com.senai.domain.model.Pessoa;
import br.com.senai.domain.model.Role;
import br.com.senai.domain.service.RoleService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class RoleAssembler {

    private ModelMapper modelMapper;

    public RoleDTO toModel(Role role){
        return modelMapper.map(role, RoleDTO.class);
    }
    public List<RoleDTO> toCollectionModel(List<Role> roles){
        return roles.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Role toEntity(RoleInputDTO roleInput){

        return modelMapper.map(roleInput, (Type) Role.class);
    }

}

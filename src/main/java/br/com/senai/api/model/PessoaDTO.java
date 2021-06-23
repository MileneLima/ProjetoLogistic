package br.com.senai.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PessoaDTO {

    private String nome;

    private UsuarioDTO usuario;

    private String telefone;
}

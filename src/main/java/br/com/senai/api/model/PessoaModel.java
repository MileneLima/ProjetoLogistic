package br.com.senai.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PessoaModel {

    @NotNull
    private long id;
    private String nome;
    private String email;
    private String telefone;
}

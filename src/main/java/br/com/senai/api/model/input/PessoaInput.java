package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PessoaInput {

    @NotNull
    private String nome;

    @NotNull
    private String email;

    @NotNull
    private  String telefone;

}
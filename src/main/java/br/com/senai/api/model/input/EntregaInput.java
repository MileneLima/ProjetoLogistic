package br.com.senai.api.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaInput {

    @NotNull
    @Valid
    private PessoaInput pessoa;

    @NotNull
    @Valid
    private DestinatarioInput destinatario;

    @NotNull
    private BigDecimal taxa;
}

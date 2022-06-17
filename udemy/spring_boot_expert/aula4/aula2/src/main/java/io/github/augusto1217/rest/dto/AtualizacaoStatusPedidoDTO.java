package io.github.augusto1217.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AtualizacaoStatusPedidoDTO implements Serializable {

    private static final long serialVersionUID = -3279458269416156753L;

    private String novoStatus;

}

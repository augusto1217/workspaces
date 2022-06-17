package io.github.augusto1217.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErros {

    @Getter
    private List<String> erros;

    public ApiErros(String message) {
        this.erros = Arrays.asList(message);
    }

    public ApiErros(List<String> erros) {
        this.erros = erros;
    }
}

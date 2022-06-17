package io.github.augusto1217.rest.controller;

import java.util.List;
import io.github.augusto1217.exception.PedidoNaoEncontradoException;
import io.github.augusto1217.exception.RegraNegocioException;
import io.github.augusto1217.rest.ApiErros;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErros(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErros hanglePedidoNotFoundException(PedidoNaoEncontradoException ex){
        return new ApiErros(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErros hangleMethodNotValidException(MethodArgumentNotValidException ex){
        List<String> erros = ex.getBindingResult()
                                .getAllErrors()
                                .stream()
                                .map(erro -> erro.getDefaultMessage())
                                .collect(Collectors.toList());
        return new ApiErros(erros);
    }

}

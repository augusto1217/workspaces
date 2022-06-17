package io.github.augusto1217.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException() {
        super("Usuário ou senha inválida");
    }
}

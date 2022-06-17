package io.github.augusto1217.rest.controller;

import io.github.augusto1217.domain.entity.Usuario;
import io.github.augusto1217.exception.SenhaInvalidaException;
import io.github.augusto1217.rest.dto.CrendenciaisDTO;
import io.github.augusto1217.rest.dto.TokenDTO;
import io.github.augusto1217.services.impl.UsuarioServiceImpl;
import io.github.augusto1217.services.security.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceImpl usuarioService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvar(@RequestBody @Valid Usuario usuario) {
        String senhaCriptografada = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(senhaCriptografada);
        return usuarioService.salvar(usuario);
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CrendenciaisDTO crendenciais) {
        try {
            Usuario usuario = new Usuario();
            usuario.setLogin(crendenciais.getLogin());
            usuario.setPassword(crendenciais.getSenha());

            usuarioService.autenticar(usuario);

            String token = jwtService.gerarToken(usuario);

            return new TokenDTO(usuario.getLogin(), token);

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

}

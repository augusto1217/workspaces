package io.github.augusto1217.domain.repository;

import io.github.augusto1217.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioDao extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

}

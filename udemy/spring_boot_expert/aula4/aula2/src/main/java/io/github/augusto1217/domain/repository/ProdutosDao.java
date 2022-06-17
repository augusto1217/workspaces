package io.github.augusto1217.domain.repository;

import io.github.augusto1217.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosDao extends JpaRepository<Produto, Integer> {
}

package io.github.com.augusto.localizacao.domain.repository;

import io.github.com.augusto.localizacao.domain.entity.Cidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    List<Cidade> findByNome(String nome);
    List<Cidade> findByNomeStartingWith(String nome);
    List<Cidade> findByNomeEndingWith(String nome);
    List<Cidade> findByNomeContaining(String nome);

    List<Cidade> findByHabitantes(Long qtdHabitantes);
    List<Cidade> findByHabitantesLessThan(Long qtdHabitantes);
    List<Cidade> findByHabitantesLessThanEqual(Long qtdHabitantes);
    List<Cidade> findByHabitantesGreaterThan(Long qtdHabitantes);

    List<Cidade> findByHabitantesLessThanAndNomeLike(Long qtdHabitantes, String nome);
    List<Cidade> findByHabitantesGreaterThanAndNomeLike(Long qtdHabitantes, String nome);

    @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
    List<Cidade> findByNomeLike(String nome, Sort sort);

    @Query("select c from Cidade c where upper(c.nome) like upper(?1)")
    Page<Cidade> findByNomeLikePage(String nome, Pageable pageable);

}

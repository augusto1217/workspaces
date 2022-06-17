package io.github.com.augusto.localizacao.services;

import io.github.com.augusto.localizacao.domain.entity.Cidade;
import io.github.com.augusto.localizacao.domain.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Transactional
    private Cidade salvarCidade(Cidade cidade){
        return cidadeRepository.save(cidade);
    }

    public void setDadosCidade(){
        var city = new Cidade(0L,"Brasília",620000L);
        salvarCidade(city);
        city = new Cidade(1L,"João Pessoa",90000000L);
        salvarCidade(city);
        city = new Cidade(2L,"Fortaleza",65000000L);
        salvarCidade(city);
        city = new Cidade(3L,"Porto Velho",650540000L);
        salvarCidade(city);
        city = new Cidade(4L,"Rio de Janeiro",650540000L);
        salvarCidade(city);
        city = new Cidade(5L,"Natal",50540000L);
        salvarCidade(city);
        city = new Cidade(6L,"Palmas",10540000L);
        salvarCidade(city);
        city = new Cidade(7L,"Porto Alegre",650540000L);
        salvarCidade(city);
    }

    public void listarTodasCidades(){
        cidadeRepository.findAll().forEach(System.out::println);
    }

    public void listarCidadesPorNome(String nome){
        cidadeRepository.findByNome(nome).forEach(System.out::println);
    }

    public void listarCidadesPorNomeInicio(String nome){
        cidadeRepository.findByNomeStartingWith(nome).forEach(System.out::println);
    }

    public void listarCidadesPorNomeFim(String nome){
        cidadeRepository.findByNomeEndingWith(nome).forEach(System.out::println);
    }

    public void listarCidadesPorNomeContem(String nome){
        cidadeRepository.findByNomeContaining(nome).forEach(System.out::println);
    }

    public void listarCidadesPorNomeLike(String nome){
        cidadeRepository.findByNomeLike(nome, Sort.by("habitantes")).forEach(System.out::println);
    }

    public void listarCidadesPorNomeLikeCase(String nome){
        cidadeRepository.findByNomeLike(nome, Sort.by("habitantes")).forEach(System.out::println);
    }

    public void listarCidadesPorQtdHabitantes(Long qtd){
        cidadeRepository.findByHabitantes(qtd).forEach(System.out::println);
    }

    public void listarCidadesPorQtdHabitantesLess(Long qtd){
        cidadeRepository.findByHabitantesLessThan(qtd).forEach(System.out::println);
    }

    public void listarCidadesPorQtdHabitantesLessEqual(Long qtd){
        cidadeRepository.findByHabitantesLessThanEqual(qtd).forEach(System.out::println);
    }

    public void listarCidadesPorQtdHabitantesGreater(Long qtd){
        cidadeRepository.findByHabitantesGreaterThan(qtd).forEach(System.out::println);
    }

    public void listarCidadesPorQtdHabitantesLessAndNomeLike(Long qtd, String nome){
        cidadeRepository.findByHabitantesLessThanAndNomeLike(qtd,nome).forEach(System.out::println);
    }

    public void listarCidadesPorQtdHabitantesGreaterAndNomeLike(Long qtd, String nome){
        cidadeRepository.findByHabitantesGreaterThanAndNomeLike(qtd, nome).forEach(System.out::println);
    }

    public void listarCidadesPorNomeLikePage(String nome){
        Pageable pageable = PageRequest.of(0,3);
        cidadeRepository.findByNomeLikePage(nome, pageable).forEach(System.out::println);
    }

    public List<Cidade> filtroDinamico(Cidade cidade){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
        Example<Cidade> example = Example.of(cidade, matcher);
        return cidadeRepository.findAll(example);
    }


}

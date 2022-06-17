package io.github.augusto1217.rest.controller;

import io.github.augusto1217.domain.entity.Produto;
import io.github.augusto1217.domain.repository.ProdutosDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutosDao produtosDao;

    @RequestMapping(value = "/hello/{nome}", method = RequestMethod.GET)
    public String helloProduto(@PathVariable("nome") String nomeProduto) {
        return String.format("Hello %s", nomeProduto);
    }

    private ResponseStatusException getMessageNotFound() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
    }

    @GetMapping("/{id}")
    public Produto getProdutosById(@PathVariable Integer id) {
        return produtosDao
                .findById(id)
                .orElseThrow(() -> getMessageNotFound());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto) {
        return produtosDao.save(produto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        produtosDao.findById(id)
                .map(produto -> {
                    produtosDao.delete(produto);
                    return produtosDao;
                }).orElseThrow(() -> getMessageNotFound());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Produto update(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
        return produtosDao
                .findById(id)
                .map(produtoExistente -> {
                    produto.setId(produtoExistente.getId());
                    return produtosDao.save(produto);
                }).orElseThrow(() -> getMessageNotFound());
    }

    @GetMapping("/")
    public List<Produto> find(Produto filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                    ExampleMatcher.StringMatcher.CONTAINING
                );
        Example<Produto> example = Example.of(filter, matcher);
        return produtosDao.findAll(example);
    }

}

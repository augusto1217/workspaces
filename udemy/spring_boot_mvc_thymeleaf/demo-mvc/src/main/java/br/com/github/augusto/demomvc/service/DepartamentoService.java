package br.com.github.augusto.demomvc.service;

import java.util.List;

import br.com.github.augusto.demomvc.domain.Departamento;

public interface DepartamentoService {
     
    void salvar(Departamento departamento);
    void editar(Departamento departamento);
    void excluir(Long id);
    Departamento buscarPorId(Long id);
    List<Departamento> buscarTodos();
    boolean departamentoTemCargos(Long id);
    
}

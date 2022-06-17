package br.com.github.augusto.demomvc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.github.augusto.demomvc.dao.DepartamentoDao;
import br.com.github.augusto.demomvc.domain.Departamento;
import br.com.github.augusto.demomvc.service.DepartamentoService;

@Service
@Transactional(readOnly = false)
public class DepartamentoServiceImp implements DepartamentoService {

    @Autowired
    private DepartamentoDao dao;

    @Override
    public void salvar(Departamento departamento) {
        dao.save(departamento);        
    }

    @Override
    public void editar(Departamento departamento) {
        dao.update(departamento);
    }

    @Override
    public void excluir(Long id) {
        dao.delete(id);        
    }

    @Override
    @Transactional(readOnly = true)
    public Departamento buscarPorId(Long id) {        
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Departamento> buscarTodos() {        
        return dao.findAll();
    }

    @Override
    public boolean departamentoTemCargos(Long id) {
        if(buscarPorId(id).getCargos().isEmpty()) {
            return false;
        }
        return true;
    }
    
}

package br.com.github.augusto.demomvc.dao.impl;

import org.springframework.stereotype.Repository;

import br.com.github.augusto.demomvc.dao.AbastractDao;
import br.com.github.augusto.demomvc.dao.DepartamentoDao;
import br.com.github.augusto.demomvc.domain.Departamento;

@Repository
public class DepartamentoDaoImp extends AbastractDao<Departamento, Long> implements DepartamentoDao {
    
}

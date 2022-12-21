package com.unab.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unab.app.models.Cliente;
@Repository
public interface IClienteDAO extends PagingAndSortingRepository<Cliente, Long>{

}

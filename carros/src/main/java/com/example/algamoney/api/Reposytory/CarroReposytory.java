package com.example.algamoney.api.Reposytory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.algamoney.api.domain.Carro;
@Repository
public interface CarroReposytory extends CrudRepository<Carro, Long>{

	Iterable<Carro> findByTipo(String tipo);

}

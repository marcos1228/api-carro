package com.example.algamoney.api.Reposytory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.algamoney.api.domain.Carro;
@Repository
public interface CarroReposytory extends JpaRepository<Carro, Long>{

	List<Carro> findByTipo(String tipo);

}

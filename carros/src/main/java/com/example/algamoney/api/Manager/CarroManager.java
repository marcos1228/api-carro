package com.example.algamoney.api.Manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.example.algamoney.api.Reposytory.CarroReposytory;
import com.example.algamoney.api.domain.Carro;

/**
 * Camada de manager
 * 
 * @author Marcos Barbosa Evangelista email: evangelistamarcos99@gmail.com
 */
@Component
public class CarroManager {
	@Autowired
	private CarroReposytory carroReposytory;
	private String message;

	/**
	 * método que retorna uma lista de carros
	 * 
	 * @return
	 */
	public Iterable<Carro> getCarroFake() {
		return carroReposytory.findAll();
	}

	/**
	 * método que buscar um carro pelo id
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Carro> getCarroById(long id) {
		return carroReposytory.findById(id);
	}

	/**
	 * método que buscar um carro pelo tipo
	 * 
	 * @param tipo
	 * @return
	 */
	public List<Carro> getCarroByTipo(String tipo) {
		return carroReposytory.findByTipo(tipo);
	}

	public Carro save(Carro carro) {
		return carroReposytory.save(carro);
	}

	public Carro update(Carro carro, Long id) {
		// Assert.notNull(id, message:"Não foi possivel atualizar o registro");
		// Buscando o carro no Banco de dados
		Optional<Carro> optional = getCarroById(id);
		if (optional.isPresent()) {
			Carro db = optional.get();

			// copiando as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());
			carroReposytory.save(db);
			return db;
		} else {
			throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	}

	public void delete(Long id) {
		Optional<Carro> carro = getCarroById(id);
		if(carro.isPresent()) {
			carroReposytory.deleteById(id);
		}

	}
}

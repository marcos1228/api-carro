package com.example.algamoney.api.Manager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.algamoney.api.Reposytory.CarroReposytory;
import com.example.algamoney.api.domain.Carro;
import com.example.algamoney.api.domain.dto.CarroDTO;

/**
 * Camada de manager
 * 
 * @author Marcos Barbosa Evangelista email: evangelistamarcos99@gmail.com
 */
@Component
public class CarroManager {
	@Autowired
	private CarroReposytory carroReposytory;

	/**
	 * método que retorna uma lista de carros
	 * 
	 * @return
	 */
	public List<CarroDTO> getCarros() {
		// Utilizando Lambda
		return carroReposytory.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());

		// Utlização padrão
//		List<Carro> carros = carroReposytory.findAll();
//		List<CarroDTO> list = new ArrayList<>();
//		for (Carro c : carros) {
//			list.add(new CarroDTO(c));
//
//		}
//		return list;
	}

	/**
	 * método que buscar um carro pelo id, por padrão o método findById retorna um
	 * Optional, mas precisamos converte para DTO, duas formas de fazer isso
	 * 
	 * @param id
	 * @return
	 */
	public Optional<CarroDTO> getCarroById(long id) {
		/* Utilizando lambda */
		return carroReposytory.findById(id).map(CarroDTO::create);

		/* Maneira tradicional */
		// Optional<Carro> carro = carroReposytory.findById(id);
//		
//		if(carro.isPresent()) {
//			return Optional.of(new CarroDTO(carro.get()));
//		} else {
//			return null;
//		}
	}

	/**
	 * método que buscar um carro pelo tipo
	 * 
	 * @param tipo
	 * @return
	 */
	public List<CarroDTO> getCarroByTipo(String tipo) {
		// Utilização padrão
//		List<Carro> carros = carroReposytory.findAll();
//		List<CarroDTO> list = new ArrayList<>();
//		for (Carro c : carros) {
//			list.add(create CarroDTO());
//		}
//		return list;
//		
		/* Utilizando Lambda */
		return carroReposytory.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
	}

	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId());
		return CarroDTO.create(carroReposytory.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id);
		// Buscando o carro no Banco de dados
		Optional<Carro> optional = carroReposytory.findById(id);
		if (optional.isPresent()) {
			Carro db = optional.get();
			// copiando as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id: " + db.getId());
			carroReposytory.save(db);
			// Atualiza carro
			return CarroDTO.create(db);
		} else {

			return null;
			// throw new RuntimeException("Não foi possivel atualizar o registro");
		}
	}

	/**
	 * @param id
	 * @return
	 */
	public void delete(Long id) {
		carroReposytory.deleteById(id);
	}
}

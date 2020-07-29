package com.example.algamoney.api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoney.api.Manager.CarroManager;
import com.example.algamoney.api.domain.Carro;

/**
 * RestController Carros 
 * @author Marcos Barbosa Evangelista email evangelistamarcos99@gmail.com
 */

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {
	@Autowired
	private CarroManager carroManager;

	/**
	 * @return uma lista de carros
	 */
	@GetMapping
	public Iterable<Carro> get() {
		return carroManager.getCarroFake();
	}

	/**
	 * @param id
	 * @return retorna um carro pelo id
	 */
	@GetMapping("/{id}")
	public Optional<Carro> get(@PathVariable("id") Long id) {
		return carroManager.getCarroById(id);
	}

	/**
	 * 
	 * @param tipo
	 * @return retorna uma lista de tipo de carro
	 */
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		return carroManager.getCarroByTipo(tipo);
	}
	
	/**
	 * 
	 * @param carro
	 * @return retorna um carro salvo
	 */
	@PostMapping
	public String post(@RequestBody Carro carro) {
	Carro c =	carroManager.save(carro);
	return "carro salvo com sucesso: " + c.getId();
		
	}
	/**
	 * 
	 * @param id
	 * @param carro
	 * @return Atualizar um carro 
	 */
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = carroManager.update(carro, id);
		
		return "Carro Atualizado Com sucesso" + c.getId();	
		}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable ("id") Long id) {
		carroManager.delete(id);
		return "Carro deletado com sucesso";
		
	}
	
}

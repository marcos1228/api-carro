package com.example.algamoney.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.algamoney.api.Manager.CarroManager;
import com.example.algamoney.api.domain.Carro;
import com.example.algamoney.api.domain.dto.CarroDTO;

/**
 * RestController Carros
 * 
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
	@GetMapping("/lista/carros")
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(carroManager.getCarros());
		// return new ResponseEntity<>(carroManager.getCarroFake(), HttpStatus.OK);
	}

	/**
	 * @param id
	 * @return retorna um carro pelo id
	 */
	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<CarroDTO> carro = carroManager.getCarroById(id);
		if (carro.isPresent()) {
			return ResponseEntity.ok(carro.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * 
	 * @param tipo
	 * @return retorna uma lista de tipo de carro
	 */
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<CarroDTO>> getCarrosByTipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = carroManager.getCarroByTipo(tipo);
		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);

	}

	/**
	 * 
	 * @param carro
	 * @return retorna um carro salvo
	 */
	@PostMapping("/salvando")
	public ResponseEntity post(@RequestBody Carro carro) {
		try {
			CarroDTO c = carroManager.insert(carro);

			URI location = getUri(c.getId());

			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
	}

	/**
	 * 
	 * @param id
	 * @param carro
	 * @return Atualizar um carro
	 */
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		carro.setId(id);
		CarroDTO c = carroManager.update(carro, id);
		return c != null ? ResponseEntity.ok(c) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		boolean ok = carroManager.delete(id);
		return ok ?
				ResponseEntity.ok().build() : 
					ResponseEntity.notFound().build();

}
}


package com.example.algamoney.api.exception;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.algamoney.api.domain.dto.CarroDTO;

@RestControllerAdvice
public class ExceptionConfig {
	
	@ExceptionHandler({EmptyResultDataAccessException.class})
	public ResponseEntity<CarroDTO> erroNotFound(Exception ex) {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler({IllegalArgumentException.class})
	
	public ResponseEntity<CarroDTO> errorBadReuest(Exception ex) {
		return ResponseEntity.badRequest().build();
	}
}

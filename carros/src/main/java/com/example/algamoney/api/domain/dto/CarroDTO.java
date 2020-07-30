package com.example.algamoney.api.domain.dto;

import org.modelmapper.ModelMapper;

import com.example.algamoney.api.domain.Carro;

public class CarroDTO {
	private Long id;
	private String nome;
	private String tipo;
	
	/**
	 * Construtor que recebe um carro como parametro
	 * @param c
	 * @return 
	 */
//	public CarroDTO(Carro c) {
//		this.id = c.getId();
//		this.nome = c.getNome();
//		this.tipo = c.getTipo();
//		
//	}

	/*Copiando o objeto Carro para CarroDTO, utizando a lib modelmapper*/
	public static  CarroDTO create(Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return  modelMapper.map(c, CarroDTO.class);
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarroDTO other = (CarroDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CarroDTO [id=" + id + ", nome=" + nome + ", tipo=" + tipo + "]";
	}
	
	
	
}

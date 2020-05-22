package br.com.stoom.restaddress.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@NotNull
	private String streetName;
	
	@NotNull
	private Long number;
	private String complement;
	
	@NotNull
	@NotEmpty
	private String neighbourhood;
	
	@NotNull
	@NotEmpty
	private String city;
	
	@NotNull
	@NotEmpty
	private String state;
	
	@NotNull
	@NotEmpty
	private String country;
	
	@NotNull
	@NotEmpty
	private String zipcode;
	private String latitude;
	private String longitude;
}

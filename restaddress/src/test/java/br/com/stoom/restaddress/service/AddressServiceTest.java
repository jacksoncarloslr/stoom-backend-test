package br.com.stoom.restaddress.service;



import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.stoom.restaddress.model.Address;

@SpringBootTest
public class AddressServiceTest {

	@Autowired
	private AddressService addressService;
	
	
	
	@Test
	public void createAddressTest() {
		
		Address address = addressBuilder();
		
		ResponseEntity<Address> response = this.addressService.createAddress(address);
		
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		
	}

	@Test
	public void deleteAddressTest() {
		
		Address address = addressBuilder();
		ResponseEntity<Address> response = addressService.createAddress(address);
		
		Address addressCreated = response.getBody();
		
		ResponseEntity<?> responseEntity = addressService.deleteAddress(addressCreated.getId());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}
	
	@Test
	public void updateAddressTest() {
		
		Address address = addressBuilder();
		address.setNumber(2845l);
		ResponseEntity<Address> response = this.addressService.createAddress(address);
		Address addressCreated = response.getBody();
		
		Address newAddress = addressBuilder();
		newAddress.setNumber(2870l);
		
		ResponseEntity<?> addressResponseEntity = this.addressService.updateAddress(newAddress, addressCreated.getId());
		address = (Address) addressResponseEntity.getBody();
		
		
		ResponseEntity<Address> updatedAddressResponseEntity = this.addressService.findAddressBy(address.getId());
		Address updatedAddress = updatedAddressResponseEntity.getBody();
		
		assertEquals(2870l, updatedAddress.getNumber());
	}
	
	@Test
	public void listAllAddressesTest() {
		List<Address> addressesList= this.addressService.listAllAddresses();
		
		assertThat(!addressesList.isEmpty());
	}
	
	private Address addressBuilder() {
		Address address = new Address();
		address.setCity("Guarulhos");
		address.setComplement("Torre 1");
		address.setCountry("Brasil");
		address.setNumber(123l);
		address.setNeighbourhood("Ponte Grande");
		address.setStreetName("AV. Guarulhos");
		address.setZipcode("07031000");
		address.setState("SP");
		return address;
	}
	
}

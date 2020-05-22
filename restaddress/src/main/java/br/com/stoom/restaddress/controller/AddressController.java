package br.com.stoom.restaddress.controller;

import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.stoom.restaddress.model.Address;
import br.com.stoom.restaddress.service.AddressService;

@RestController
@RequestMapping("/address")
public class AddressController {

	@Autowired
	private AddressService addressService;
	
	@PostMapping
	public ResponseEntity<Address> createAddress(@RequestBody @Valid Address address) throws URISyntaxException {
		
		return this.addressService.createAddress(address);
		
	}
	
	@GetMapping
	public List<Address> listAllAddresses(){
		return this.addressService.listAllAddresses();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Address> findAddressBy(@PathVariable Long id) {
		return this.addressService.findAddressBy(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateAddress(@Valid @RequestBody Address address, @PathVariable Long id) {
		return this.addressService.updateAddress(address, id);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
		return this.addressService.deleteAddress(id);
	}
	
}

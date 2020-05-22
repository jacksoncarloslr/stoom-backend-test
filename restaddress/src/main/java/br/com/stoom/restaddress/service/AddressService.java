package br.com.stoom.restaddress.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;

import br.com.stoom.restaddress.model.Address;
import br.com.stoom.restaddress.repository.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public ResponseEntity<Address> createAddress(Address address){
		
		validLatitudeAndLongitude(address);
		
		this.addressRepository.save(address);
		
		return new ResponseEntity<Address>(address, HttpStatus.CREATED);
		
	}

	private void validLatitudeAndLongitude(Address address) {
		if(address.getLatitude() == null && address.getLongitude() == null) {
			
			GeoApiContext context = new GeoApiContext.Builder().apiKey("AIzaSyBm3xh9oZP1ksMWcMzVaZQevWlrtb8tIgc").build();
			GeocodingResult[] results = null;
			
			try {
				results =  GeocodingApi.geocode(context, fullAddress(address)).await();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			LatLng location = results[0].geometry.location;
			
			address.setLatitude(String.valueOf(location.lat));
			address.setLongitude(String.valueOf(location.lng));
			
		}
	}

	private String fullAddress(Address address) {
		return new StringBuilder()
				.append(address.getNumber())
				.append("")
				.append(address.getStreetName())
				.append("")
				.append(address.getCity())
				.append("")
				.append(address.getState())
				.append("")
				.append(address.getZipcode()).toString();
				
				
	}

	public List<Address> listAllAddresses() {
		return this.addressRepository.findAll();
	}
	
	public ResponseEntity<?> updateAddress(Address newAddress, Long id) {
		
		 return this.addressRepository.findById(id).map(address -> {
			address.setCity(newAddress.getCity());
			address.setComplement(newAddress.getComplement());
			address.setCountry(newAddress.getCountry());
			address.setLatitude(newAddress.getLatitude());
			address.setLongitude(newAddress.getLongitude());
			address.setNeighbourhood(newAddress.getNeighbourhood());
			address.setNumber(newAddress.getNumber());
			address.setState(newAddress.getState());
			address.setStreetName(newAddress.getStreetName());
			address.setZipcode(newAddress.getZipcode());
			
			validLatitudeAndLongitude(address);
			
			this.addressRepository.save(address);
			return ResponseEntity.ok().body(address);
			
		}).orElse(ResponseEntity.notFound().build());
		
		
	}
	
	
	public ResponseEntity<?> deleteAddress(Long id) {
		return this.addressRepository.findById(id).map(address ->{
			this.addressRepository.deleteById(id);
			return ResponseEntity.ok().build();
			
		}).orElse(ResponseEntity.notFound().build());
	}

	public ResponseEntity<Address> findAddressBy(Long id) {
		return this.addressRepository.findById(id).map(address -> 
		ResponseEntity.ok().body(address)).orElse(ResponseEntity.notFound().build());
	}
	
}

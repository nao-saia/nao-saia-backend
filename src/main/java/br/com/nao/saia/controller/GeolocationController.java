package br.com.nao.saia.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.nao.saia.dto.AddressDTO;
import br.com.nao.saia.dto.GeoLocationDTO;
import br.com.nao.saia.service.GeolocationService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("geolocation")
public class GeolocationController {

	private GeolocationService geolocationService;

	public GeolocationController(GeolocationService geolocationService) {
		this.geolocationService = geolocationService;
	}

	@GetMapping("/cep/{zipCode}")
	public Mono<AddressDTO> getAddressFromZipCode(@PathVariable @NotBlank final String zipCode) {
		return this.geolocationService.getAddressFromZipCode(zipCode);
	}

	@GetMapping("/location")
	public Mono<AddressDTO> getAddressFromLocation(@RequestParam("lat") double latitude,
												   @RequestParam("lon") double longitude) {
		return this.geolocationService.getAddressFromGeoLocation(new GeoLocationDTO(latitude, longitude));
	}

}

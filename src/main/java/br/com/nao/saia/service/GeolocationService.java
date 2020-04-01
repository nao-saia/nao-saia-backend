package br.com.nao.saia.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.nao.saia.converter.AddressConverter;
import br.com.nao.saia.dto.AddressDTO;
import br.com.nao.saia.dto.GeoLocationDTO;
import reactor.core.publisher.Mono;

@Service
public class GeolocationService {

	@Value("${cep.aberto.url}")
	private String cepAbertoUrl;
	@Value("${cep.aberto.token}")
	private String cepAbertoToken;
	private WebClient client;

	private CityService cityService;

	public GeolocationService(CityService cityService) {
		this.cityService = cityService;
	}

	@PostConstruct
	public void initialize() {
		this.client = WebClient.create(cepAbertoUrl);
	}

	@SuppressWarnings({ "rawtypes" })
	public Mono<AddressDTO> getAddressFromZipCode(final String zipCode) {
		Mono<Map> mono = client.get()
			  .uri(String.format("/cep?cep=%s", zipCode))
			  .header("Authorization", cepAbertoToken)
			  .retrieve()
			  .bodyToMono(Map.class);

		return mono.flatMap(addressFunction);
	}

	@SuppressWarnings({ "rawtypes" })
	public Mono<AddressDTO> getAddressFromGeoLocation(final GeoLocationDTO geoLocation) {
		Mono<Map> mono = client.get()
				.uri("/nearest?lat=" + geoLocation.getLatitude() + "&lng=" + geoLocation.getLongitude())
				.header("Authorization", cepAbertoToken)
				.retrieve()
				.bodyToMono(Map.class);

		return mono.flatMap(addressFunction);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Function<Map, Mono<AddressDTO>> addressFunction = address -> {
		Map<String, Object> cityMap = (Map) address.get("cidade");
		return cityService.getCityThroughCityMap(cityMap)
				.flatMap(city -> {
					Map<String, Object> completeAddress = new HashMap<>(address);
					completeAddress.computeIfAbsent("cityName", f -> city);
					return Mono.just(AddressConverter.fromMapToAddressDTO(completeAddress));
				});
	};

}

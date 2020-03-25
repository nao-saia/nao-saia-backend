package br.com.nao.saia.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.nao.saia.dto.GeoLocationDTO;
import reactor.core.publisher.Mono;

@Service
public class GeolocationService {

	@Value("${cep.aberto.url}")
	private String cepAbertoUrl;
	@Value("${cep.aberto.token}")
	private String cepAbertoToken;
	private WebClient client;

	@PostConstruct
	public void initialize() {
		this.client = WebClient.create(cepAbertoUrl);
	}

	public Mono<GeoLocationDTO> getCoordinatesFromZipCode(final String zipCode) {
		return client.get()
			  .uri("/cep?cep=" + zipCode)
			  .header("Authorization", cepAbertoToken)
			  .retrieve()
			  .bodyToMono(GeoLocationDTO.class);
	}

}

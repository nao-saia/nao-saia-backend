package br.com.nao.saia.service;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.nao.saia.converter.AddressConverter;
import br.com.nao.saia.dto.AddressDTO;
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Mono<AddressDTO> getAddressFromZipCode(final String zipCode) {
		Mono<Map> mono = client.get()
			  .uri("/cep?cep=" + zipCode)
			  .header("Authorization", cepAbertoToken)
			  .retrieve()
			  .bodyToMono(Map.class);
		return mono.flatMap(address -> Mono.just(AddressConverter.fromMapToAddressDTO(address)));
	}

}

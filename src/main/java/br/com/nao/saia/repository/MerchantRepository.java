package br.com.nao.saia.repository;

import java.util.UUID;

import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.com.nao.saia.model.Merchant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MerchantRepository extends ReactiveMongoRepository<Merchant, UUID> {

	Mono<Merchant> findByCnpj(String cnpj);

	Flux<Merchant> findByCategoriesIn(String category);

	Flux<Merchant> findByAddressCity(String city);

	Flux<Merchant> findByAddressState(String city);

	Flux<Merchant> findByAddressLocationNear(Point point, Distance distance);

	Flux<Merchant> findByUserId(UUID userId);
}

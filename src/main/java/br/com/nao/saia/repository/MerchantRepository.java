package br.com.nao.saia.repository;

import br.com.nao.saia.model.Merchant;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface MerchantRepository extends ReactiveMongoRepository<Merchant, UUID> {

	Mono<Merchant> findByCnpj(String cnpj);

	Flux<Merchant> findByAddressLocationNear(Point point, Distance distance);

	Flux<Merchant> findByUserId(UUID userId);
}

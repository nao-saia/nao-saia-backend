package br.com.nao.saia.repository;

import br.com.nao.saia.model.Merchant;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface MerchantRepository extends ReactiveMongoRepository<Merchant, UUID> {

	Flux<Merchant> findByCategoriesIn(String category);

	Flux<Merchant> findByAddressCity(String city);

	Flux<Merchant> findByAddressState(String city);

	Flux<Merchant> findByAddressLocationNear(Point point, Distance distance);
}

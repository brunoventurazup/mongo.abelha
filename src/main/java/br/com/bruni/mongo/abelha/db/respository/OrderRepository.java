package br.com.bruni.mongo.abelha.db.respository;

import br.com.bruni.mongo.abelha.db.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface OrderRepository extends MongoRepository<Order, String> {
}

package app.adapters.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import app.adapters.order.entity.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Long>{
  public OrderEntity findByOrderId(Long orderId);
}

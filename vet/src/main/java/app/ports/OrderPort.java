package app.ports;

import app.domain.models.Order;

public interface OrderPort {
  public Order save(Order order);
  public Order findByOrderId(Long orderId);
}

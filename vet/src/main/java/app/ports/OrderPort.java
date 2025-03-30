package app.ports;

import java.util.List;

import app.domain.models.Order;

public interface OrderPort {
  public void save(Order order);
  public List<Order> getAllOrders(); // Consultar al listado de ordenes
}

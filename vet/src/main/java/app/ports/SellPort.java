package app.ports;

import java.util.List;

import app.domain.models.Order;

public interface SellPort {
  // Consultar al listado de ordenes
  public List<Order> getAllOrders();
}
package app.ports;

import java.util.List;

import app.domain.models.Order;

public interface OrderPort {
  public void save(OrderPort orderPort);
  
  // Consultar al listado de ordenes
  public List<Order> getAllOrders();
  
  // Crear orden
  public Order saveOrder(Order order); 
  
  // Anular orden (No se deben eliminar)
  public Order updateOrder(); // Todo como se edita?
  
}

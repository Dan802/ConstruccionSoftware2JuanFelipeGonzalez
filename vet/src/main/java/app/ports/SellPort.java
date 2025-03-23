package app.ports;

import app.domain.models.Order;

public interface SellPort {
  // Consultar al listado de ordenes
  public Order searchAllOrder();

  // Suministrar medicamentos
  public void supplyMedicine();

  // Vender medicina
  public void sellMedicine();
}

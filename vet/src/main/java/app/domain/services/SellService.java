package app.domain.services;

import app.domain.models.Invoice;
import app.domain.models.Order;
import app.ports.SellPort;

public class SellService implements SellPort{

  public Invoice generateInvoice() {
    return null; // todo
  }

  @Override
  public Order searchAllOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchAllOrder'");
  }

  @Override
  public void supplyMedicine() { // Supply cuenta como venta? 
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'supplyMedicine'");
  }

  @Override
  public void sellMedicine() {
    generateInvoice();
  }
  
}

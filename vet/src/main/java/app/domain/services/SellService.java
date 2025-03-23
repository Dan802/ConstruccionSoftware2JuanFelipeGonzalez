package app.domain.services;

import java.util.List;

import app.domain.models.Invoice;
import app.domain.models.Order;
import app.ports.InvoicePort;
import app.ports.SellPort;

public class SellService implements SellPort {

  public Invoice generateInvoice() {
    return null; // todoNull
  }

  // Suministrar medicamentos
  public void supplyMedicine (Order order) {
    generateInvoice();
  }

  // Vender otro tipo de productor
  public void sellMedicine() {
    generateInvoice();
  }

  @Override
  public List<Order> getAllOrders() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
  }

  @Override
  public void save(InvoicePort invoicePort) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }
}

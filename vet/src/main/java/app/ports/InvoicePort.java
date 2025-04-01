package app.ports;

import app.domain.models.Invoice;

public interface InvoicePort {
  public Invoice save(Invoice invoice);
}

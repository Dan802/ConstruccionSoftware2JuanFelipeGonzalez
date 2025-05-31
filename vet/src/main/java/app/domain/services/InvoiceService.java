package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.invoice.InvoiceAdapter;
import app.domain.models.Invoice;
import app.domain.models.Order;
import app.domain.models.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class InvoiceService {
  @Autowired
  private InvoiceAdapter invoiceAdapter;
  
  public void saveInvoice(Order order, Long milisecondsDate, int count, double price) {
    Invoice invoice = new Invoice();
    invoice.setCount(count);
    invoice.setDateCreated(milisecondsDate);
    invoice.setOrderId(order);
    invoice.setPetId(order.getPetId());
    invoice.setPrice(price);
    invoice.setProductName(order.getMedicine().getMedicine());
    
    Invoice savedInvoice = invoiceAdapter.save(invoice, order);
  }

  public void saveInvoice(Long milisecondsDate, int count, double price, String productName, Pet pet) {
    Invoice invoice = new Invoice();
    invoice.setCount(count);
    invoice.setDateCreated(milisecondsDate);
    invoice.setOrderId(null);
    invoice.setOwnerId(pet.getDocumentOwner());
    invoice.setPetId(pet);
    invoice.setPrice(price);
    invoice.setProductName(productName);

    Invoice savedInvoice = invoiceAdapter.save(invoice);
  }
}

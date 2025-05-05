package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.invoice.InvoiceAdapter;
import app.domain.models.Invoice;
import app.domain.models.Order;
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
    
    Invoice savedInvoice = invoiceAdapter.save(invoice);

    System.out.println("\nSu factura es: ");
    System.out.println("\n1. Id de la factura: " + savedInvoice.getInvoiceId());  
    System.out.println("2. Mascota: " + savedInvoice.getPetId().getName());  
    System.out.println("3. Dueño: " + savedInvoice.getOwnerId().getName());  
    System.out.println("4. Id de la orden: " + savedInvoice.getOrderId().getOrderId());  
    System.out.println("5. Nombre del medicamento: " + savedInvoice.getProductName());  
    System.out.println("6. Precio: " + savedInvoice.getPrice());  
    System.out.println("7. Cantidad: " + savedInvoice.getCount());  
    System.out.println("8. Fecha de creación: " + savedInvoice.getDateCreated());  
  }

  
}

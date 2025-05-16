package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.inputs.utils.Utils;
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
    printInvoice(savedInvoice);
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
    printInvoice(savedInvoice);
  }

  public void printInvoice(Invoice invoice) {
    String orderId = invoice.getOrderId() != null ? invoice.getOrderId().getOrderId().toString() : "NA";

    System.out.println("\nSu factura es: ");
    System.out.println("1. Id de la factura: " + invoice.getInvoiceId());  
    System.out.println("2. Mascota: " + invoice.getPetId().getName());  
    System.out.println("3. Dueño: " + invoice.getOwnerId().getName()); 
    System.out.println("4. Id de la orden: " + orderId);  
    System.out.println("5. Nombre del medicamento: " + invoice.getProductName());  
    System.out.println("6. Precio: " + invoice.getPrice());  
    System.out.println("7. Cantidad: " + invoice.getCount());  
    System.out.println("8. Fecha de creación: " + Utils.mstoDate(invoice.getDateCreated()));  
  }
}

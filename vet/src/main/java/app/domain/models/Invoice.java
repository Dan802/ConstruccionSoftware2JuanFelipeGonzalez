package app.domain.models;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Invoice { // Factura
  private long invoiceId; // Id factura
  private Pet petId;  // Id mascota
  private Person ownerId; // Id dueño
  private Order orderId; // Id orden
  private String productName; // Nombre del producto
  private double price; // Valor
  private int count; // Cantidad
  private Date dateCreated; // Fecha
  
  public Invoice(long invoiceId, Pet petId, Person ownerId, Order orderId, String productName, double price, int count,
      Date dateCreated) {
    this.invoiceId = invoiceId;
    this.petId = petId;
    this.ownerId = ownerId;
    this.orderId = orderId;
    this.productName = productName;
    this.price = price;
    this.count = count;
    this.dateCreated = dateCreated;
  }
}

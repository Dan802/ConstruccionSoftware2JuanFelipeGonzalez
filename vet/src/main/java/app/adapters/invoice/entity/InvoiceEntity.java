package app.adapters.invoice.entity;

import app.adapters.order.entity.OrderEntity;
import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="invoice")
@Getter
@Setter
public class InvoiceEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="invoice_id")
  private long invoiceId;
  
  @JoinColumn(name="pet_id")
  @ManyToOne // MUCHAS facturas pueden tener la misma mascota
  private PetEntity petId;

  @JoinColumn(name="person_id")
  @ManyToOne // MUCHAS facturas pueden tener al mismo dueño
  private PersonEntity ownerId; 
  
  @JoinColumn(name="order_id")
  @OneToOne
  private OrderEntity orderId; 

  @Column(name="product_name")
  private String productName; 
  
  @Column(name="price")
  private double price; 
  
  @Column(name="count")
  private int count; 
  
  @Column(name="date_created")
  private Long dateCreated; 
}

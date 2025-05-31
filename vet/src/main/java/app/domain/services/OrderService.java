package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.Exceptions.NotFoundException;
import app.adapters.order.OrderAdapter;
import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class OrderService {
  
  @Autowired
  private OrderAdapter orderAdapter;  
  
  public void createOrder(MedicalRecord meRe) {
    System.out.println("\nNota: Se creará una nueva orden, ambas ordenes quedan en el sistema");

    saveOrder( null, meRe.getPetId(), meRe.getPetId().getDocumentOwner(), meRe.getVetDocument(), meRe , null);
  }
  
  public Order saveOrder(Long orderId, Pet pet, Person owner, Person vet, MedicalRecord meRe, Long ms) {
    if(ms== null) {
      ms = System.currentTimeMillis();
    } 

    Order order = new Order(orderId, pet, owner, vet, meRe, ms);
    order = orderAdapter.save(order);
    System.out.println("\nOrder saved successfully");
    
    return order;
  }

  public Order searchOrder(Long orderId) throws Exception {
    Order order = orderAdapter.findByOrderId(orderId);

    if(order == null) {
      throw new NotFoundException("Order not found");
    }

    return order;
  }
}

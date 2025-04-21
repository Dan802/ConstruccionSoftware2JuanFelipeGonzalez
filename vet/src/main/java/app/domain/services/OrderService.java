package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    System.out.println("\nSe ha guardado la orden exitosamente");
    
    return order;
  }

  public Order searchOrder(Long orderId) throws Exception {
    Order order = orderAdapter.findByOrderId(orderId);

    if(order == null) {
      throw new Exception("\nNo se encontró la orden");
    }

    printOrder(order);
    return order;
  }
  
  public void printOrder(Order order) {
    String orderPrint = 
      "\n1. Order Id: " + order.getOrderId() +
      "\n2. Mascota: " + order.getPetId().getName() +
      "\n3. Dueño: " + order.getDocumentOwner().getName()+
      "\n4. Veterinario: " + order.getDocumentVet().getName() +
      "\n5. Medicinas recetadas: " + order.getMedicine().getMedicine() +
      // ToDo metodo pa convertir ms a fecha
      "\n6. Fecha de creación: " + order.getCreatedDate() +
      "\n7. ¿Orden Cancelada? " + (order.getMedicine().isOrderCancellation() ? "Si" : "No");

    System.out.println(orderPrint);
  }
}

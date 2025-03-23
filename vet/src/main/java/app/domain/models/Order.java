package app.domain.models;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {
  private long orderId;
  private Pet petId; // Id mascota
  private Person documentOwner; // Cedula dueño
  private Person documentVet; // Cedula vet que ordena
  private String medicineName; // Nombre medicamento
  private Date createdDate; // Fecha de generacion
  
  public Order(long orderId, Pet petId, Person documentOwner, Person documentVet, String medicineName,
      Date createdDate) {
    this.orderId = orderId;
    this.petId = petId;
    this.documentOwner = documentOwner;
    this.documentVet = documentVet;
    this.medicineName = medicineName;
    this.createdDate = createdDate;
  }
}

package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {
  private Long orderId;
  private MedicalRecord medicalRecordId;
  private Pet petId; // Id mascota
  private Person documentOwner; // Cedula dueño
  private Person documentVet; // Cedula vet que ordena
  private MedicalRecord medicine; // Nombre medicamento
  private Long createdDate; // Fecha de generacion
  
  public Order(Long orderId, MedicalRecord medicalRecordId, Pet petId, Person documentOwner, Person documentVet,
      MedicalRecord medicine, Long createdDate) {
    this.orderId = orderId;
    this.medicalRecordId = medicalRecordId;
    this.petId = petId;
    this.documentOwner = documentOwner;
    this.documentVet = documentVet;
    this.medicine = medicine;
    this.createdDate = createdDate;
  }
}

package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Order {
  private Long orderId;
  private Pet petId; // Id mascota
  private Person documentOwner; // Cedula dueño
  private Person documentVet; // Cedula vet que ordena
  private MedicalRecord medicine; // Nombre medicamento
  private Long createdDate; // Fecha de generacion
  
  public Order(Long orderId, Pet petId, Person documentOwner, Person documentVet, MedicalRecord medicine,
      Long createdDate) {
    this.orderId = orderId;
    this.petId = petId;
    this.documentOwner = documentOwner;
    this.documentVet = documentVet;
    this.medicine = medicine;
    this.createdDate = createdDate;
  }

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Pet getPetId() {
    return petId;
  }

  public void setPetId(Pet petId) {
    this.petId = petId;
  }

  public Person getDocumentOwner() {
    return documentOwner;
  }

  public void setDocumentOwner(Person documentOwner) {
    this.documentOwner = documentOwner;
  }

  public Person getDocumentVet() {
    return documentVet;
  }

  public void setDocumentVet(Person documentVet) {
    this.documentVet = documentVet;
  }

  public MedicalRecord getMedicine() {
    return medicine;
  }

  public void setMedicine(MedicalRecord medicine) {
    this.medicine = medicine;
  }

  public Long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Long createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public String toString() {
    return "Order{" +
        "\norderId=" + orderId +
        ",\npetId=" + petId +
        ",\ndocumentOwner=" + documentOwner +
        ",\ndocumentVet=" + documentVet +
        ",\nmedicine=" + medicine +
        ",\ncreatedDate=" + createdDate +
        '}';
  }
}

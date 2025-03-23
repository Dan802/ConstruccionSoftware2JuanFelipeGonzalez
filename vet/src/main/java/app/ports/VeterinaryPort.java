package app.ports;

import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;

public interface VeterinaryPort {
  // Crea los dueños de las mascotas
  public Person createPetOwner();
  // Crear mascota
  public Pet createPet();

  // Crear la historia clinica
  public MedicalRecord createMedicalRecord();
  // Consultar historia clinica
  public MedicalRecord searchMedicalHistory(Pet petId);
  // Editar historia clinica
  public MedicalRecord editMedicalHistory(Pet petId);

  // Recetar medicamentos
  public Order prescribeMedications(Order order);
  // Consultar al listado de ordenes
  public Order searchAllOrder();
  // Crear orden
  public Order createOrder(); // todo recetar y crear no es lo mismo? 
  // Anular orden (No se deben eliminar)
  public Order cancelOrder();
}

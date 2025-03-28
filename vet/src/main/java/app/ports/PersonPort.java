package app.ports;

import java.util.List;

import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;

public interface PersonPort {
  public boolean existPerson(long document);
  public Person savePerson(Person person);
  public Person findByDocument(Long document);
  
  // Esto solo lo hacen los veterinarios
    // Crear los dueños de las mascotas
    public Person savePetOwner(Person petOwner);
    // Crear mascota
    public Pet savePet(Pet pet);

    // Crear la historia clinica
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);
    // Consultar historia clinica
    public MedicalRecord findByPetId(Pet petId);
    // Editar historia clinica
    public MedicalRecord updateMedicalHistory(Pet petId); // Todo como se edita? 

    // Consultar al listado de ordenes
    public List<Order> getAllOrders();
    // Crear orden
    public Order saveOrder(Order order); 
    // Anular orden (No se deben eliminar)
    public Order updateOrder(); // Todo como se edita?
  }

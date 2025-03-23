package app.domain.services;

import java.util.List;

import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.ports.VeterinaryPort;

public class VeterinaryService implements VeterinaryPort {

  public Person createOwner(){
    return null;
  }
  public Pet createPet(){
    return null;
  }
  

  @Override
  public Person savePetOwner(Person petOwner) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'savePetOwner'");
  }
  @Override
  public Pet savePet(Pet pet) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'savePet'");
  }
  @Override
  public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveMedicalRecord'");
  }
  @Override
  public MedicalRecord findByPetId(Pet petId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByPetId'");
  }
  @Override
  public MedicalRecord updateMedicalHistory(Pet petId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'setMedicalHistory'");
  }
  @Override
  public List<Order> getAllOrders() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
  }
  @Override
  public Order saveOrder(Order order) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'saveOrder'");
  }
  @Override
  public Order updateOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateOrder'");
  }
}

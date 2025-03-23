package app.domain.services;

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
  public Person createPetOwner() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createPetOwner'");
  }
  @Override
  public Order prescribeMedications(Order order) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'prescribeMedications'");
  }
  @Override
  public Order searchAllOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchAllOrder'");
  }
  @Override
  public Order createOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createOrder'");
  }
  @Override
  public Order cancelOrder() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'cancelOrder'");
  }
  @Override
  public MedicalRecord createMedicalRecord() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'createMedicalRecord'");
  }
  @Override
  public MedicalRecord searchMedicalHistory(Pet petId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'searchMedicalHistory'");
  }
  @Override
  public MedicalRecord editMedicalHistory(Pet petId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'editMedicalHistory'");
  }
}

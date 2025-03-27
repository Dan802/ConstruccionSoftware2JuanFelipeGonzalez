package app.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.ports.PersonPort;
import app.ports.VeterinaryPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class VeterinaryService implements VeterinaryPort{

  @Autowired
  public PersonPort personPort;

  public void registerPetOwner(Person person) throws Exception{
    if(personPort.existPerson(person.getDocument())){
      throw new Exception("Ya existe una persona con esa cedula");
    }
    personPort.savePerson(person);
  }

  @Override
  public Person savePetOwner(Person petOwner) {
    Person person = personPort.savePerson(petOwner);
    return person;
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
    throw new UnsupportedOperationException("Unimplemented method 'updateMedicalHistory'");
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

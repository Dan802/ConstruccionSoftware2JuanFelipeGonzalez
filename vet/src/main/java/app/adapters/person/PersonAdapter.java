package app.adapters.person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.person.entity.PersonEntity;
import app.adapters.person.repository.PersonRepository;
import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.ports.PersonPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class PersonAdapter implements PersonPort {

  @Autowired
  private PersonRepository personRepository;
  
  @Override
  public boolean existPerson(long document) {
    return personRepository.existsByDocument(document);
  }

  @Override
  public Person savePerson(Person person) {
    PersonEntity personEntity = new PersonEntity();
    personEntity.setDocument(person.getDocument());
		personEntity.setName(person.getName());
    personEntity.setAge(person.getAge());
    personEntity.setRole(person.getRole());
		personRepository.save(personEntity);
    return person;
  }

  @Override
  public Person findByDocument(Long document) {
    PersonEntity personEntity = personRepository.findByDocument(document);
    if(personEntity == null) return null;
    return personAdapter(personEntity);
  }

  private Person personAdapter(PersonEntity personEntity) {
		Person person = new Person();
		person.setDocument(personEntity.getDocument());
		person.setName(personEntity.getName());
		person.setAge(personEntity.getAge());
		person.setRole(personEntity.getRole());
		return person;
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

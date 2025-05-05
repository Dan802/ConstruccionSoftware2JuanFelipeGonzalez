package app.adapters.pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.login.entity.LoginEntity;
import app.adapters.person.PersonAdapter;
import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.adapters.pet.repository.PetRepository;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.ports.PetPort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Service
public class PetAdapter implements PetPort{
  
  @Autowired
  private PetRepository petRepository;

  @Override
  public void save(Pet pet) {
    PersonEntity personEntity = new PersonEntity();
    personEntity.setDocument(pet.getDocumentOwner().getDocument());
    personEntity.setName(pet.getDocumentOwner().getName());
    personEntity.setAge(pet.getDocumentOwner().getAge());
    personEntity.setRole(pet.getDocumentOwner().getRole());

    PetEntity petEntity = new PetEntity();
    petEntity.setName(pet.getName());
    petEntity.setDocumentOwner(personEntity);
    petEntity.setAge(pet.getAge());
    petEntity.setSpecie(pet.getSpecie());
    petEntity.setRace(pet.getRace());
    petEntity.setDescription(pet.getDescription());
    petEntity.setWeight(pet.getWeight());
		petRepository.save(petEntity);
  }

  @Override
  public Pet findByPetId(Long petId) {
    PetEntity petEntity = petRepository.findByPetId(petId);
		if(petEntity == null) return null;

   return petAdapter(petEntity); 
  }

  public Pet petAdapter(PetEntity petEntity) {
    
    Person person = new Person();
    person.setAge(petEntity.getDocumentOwner().getAge());
    person.setDocument(petEntity.getDocumentOwner().getDocument());
    person.setName(petEntity.getDocumentOwner().getName());
    person.setRole(petEntity.getDocumentOwner().getRole());
    
    Pet pet = new Pet();
    pet.setAge(petEntity.getAge());
    pet.setDescription(petEntity.getDescription());
    pet.setDocumentOwner(person);
    pet.setName(petEntity.getName());
    pet.setPetId(petEntity.getPetId());
    pet.setRace(petEntity.getRace());
    pet.setSpecie(petEntity.getSpecie());
    pet.setWeight(petEntity.getWeight());

    return pet;
  }
}

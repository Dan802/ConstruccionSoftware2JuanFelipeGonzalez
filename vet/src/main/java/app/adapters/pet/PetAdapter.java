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
  private PersonAdapter personAdapter;
  @Autowired
  private PetRepository petRepository;

  @Override
  public void save(Pet pet) {
    PetEntity petEntity = new PetEntity();
    petEntity.setName(pet.getName());
    petEntity.setDocumentOwner(personEntityAdapter(pet.getDocumentOwner()));
    petEntity.setAge(pet.getAge());
    petEntity.setSpecie(pet.getSpecie());
    petEntity.setRace(pet.getRace());
    petEntity.setDescription(pet.getDescription());
    petEntity.setWeight(pet.getWeight());
		petRepository.save(petEntity);
  }

  private PersonEntity personEntityAdapter(Person person) {
    PersonEntity personEntity = new PersonEntity();
    personEntity.setDocument(person.getDocument());
    personEntity.setName(person.getName());
    personEntity.setAge(person.getAge());
    personEntity.setRole(person.getRole());
    return personEntity;
  }

  @Override
  public Pet findByPetId(Long petId) {
    PetEntity petEntity = petRepository.findByPetId(petId);
		if(petEntity == null) return null;

   return petAdapter(petEntity); 
  }

  public Pet petAdapter(PetEntity petEntity) {
    Pet pet = new Pet();
    Person person = personAdapter.personAdapter(petEntity.getDocumentOwner());
    
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

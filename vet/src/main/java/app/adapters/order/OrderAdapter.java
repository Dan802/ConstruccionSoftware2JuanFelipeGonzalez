package app.adapters.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.medicalRecord.entity.MedicalRecordEntity;
import app.adapters.order.entity.OrderEntity;
import app.adapters.order.repository.OrderRepository;
import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.ports.OrderPort;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@Service
@Getter
@Setter
@NoArgsConstructor
public class OrderAdapter implements OrderPort {

  @Autowired
  private OrderRepository orderRepository;

  @Override
  public Order save(Order order) {
    PersonEntity ownerEntity = new PersonEntity();
    ownerEntity.setAge(order.getDocumentOwner().getAge());
    ownerEntity.setDocument(order.getDocumentOwner().getDocument());
    ownerEntity.setName(order.getDocumentOwner().getName());
    ownerEntity.setRole(order.getDocumentOwner().getRole());

    PersonEntity vetEntity = new PersonEntity();
    vetEntity.setAge(order.getDocumentVet().getAge());
    vetEntity.setDocument(order.getDocumentVet().getDocument());
    vetEntity.setName(order.getDocumentVet().getName());
    vetEntity.setRole(order.getDocumentVet().getRole());

    PetEntity petEntity = new PetEntity();
    petEntity.setAge(order.getPetId().getAge());
    petEntity.setDescription(order.getPetId().getDescription());
    petEntity.setDocumentOwner(ownerEntity);
    petEntity.setName(order.getPetId().getName());
    petEntity.setPetId(order.getPetId().getPetId());
    petEntity.setRace(order.getPetId().getRace());
    petEntity.setSpecie(order.getPetId().getSpecie());
    petEntity.setWeight(order.getPetId().getWeight());

    MedicalRecordEntity meReEntity = new MedicalRecordEntity();
    meReEntity.setDate(order.getMedicine().getDate());
    meReEntity.setVetDocument(vetEntity);
    meReEntity.setPetId(petEntity);
    meReEntity.setAllergyMedications(order.getMedicine().getAllergyMedications());
    meReEntity.setDiagnosis(order.getMedicine().getDiagnosis());
    meReEntity.setDoseMedication(order.getMedicine().getDoseMedication());
    meReEntity.setMedicine(order.getMedicine().getMedicine());
    meReEntity.setOrderCancellation(order.getMedicine().isOrderCancellation()); 
    meReEntity.setProcedures(order.getMedicine().getProcedures());
    meReEntity.setProcedureDetail(order.getMedicine().getProcedureDetail());
    meReEntity.setReason(order.getMedicine().getReason());
    meReEntity.setSymptoms(order.getMedicine().getSymptoms());
    meReEntity.setVaccinationHistory(order.getMedicine().getVaccinationHistory());
    
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setCreatedDate(order.getCreatedDate());
    orderEntity.setDocumentOwner(ownerEntity);
    orderEntity.setDocumentVet(vetEntity);
    orderEntity.setMedicine(meReEntity);
    orderEntity.setPet(petEntity);

    return orderAdapter(orderRepository.save(orderEntity));
  }

  @Override
  public Order findByOrderId(Long orderId) {
    OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
    if(orderEntity == null) return null;
    return orderAdapter(orderEntity);
  }

  private Order orderAdapter(OrderEntity orderEntity) {
    Person ownerPerson = new Person();
    ownerPerson.setAge(orderEntity.getDocumentOwner().getAge());
    ownerPerson.setDocument(orderEntity.getDocumentOwner().getDocument());
    ownerPerson.setName(orderEntity.getDocumentOwner().getName());
    ownerPerson.setRole(orderEntity.getDocumentOwner().getRole());

    Person vetPerson = new Person();
    vetPerson.setAge(orderEntity.getDocumentVet().getAge());
    vetPerson.setDocument(orderEntity.getDocumentVet().getDocument());
    vetPerson.setName(orderEntity.getDocumentVet().getName());
    vetPerson.setRole(orderEntity.getDocumentVet().getRole());

    Pet pet = new Pet();
    pet.setAge(orderEntity.getPet().getAge());
    pet.setDescription(orderEntity.getPet().getDescription());
    pet.setDocumentOwner(ownerPerson);
    pet.setName(orderEntity.getPet().getName());
    pet.setPetId(orderEntity.getPet().getPetId());
    pet.setRace(orderEntity.getPet().getRace());
    pet.setSpecie(orderEntity.getPet().getSpecie());
    pet.setWeight(orderEntity.getPet().getWeight());

    MedicalRecord meRe = new MedicalRecord();
    meRe.setDate(orderEntity.getMedicine().getDate());
    meRe.setVetDocument(vetPerson);
    meRe.setPetId(pet);
    meRe.setAllergyMedications(orderEntity.getMedicine().getAllergyMedications());
    meRe.setDiagnosis(orderEntity.getMedicine().getDiagnosis());
    meRe.setDoseMedication(orderEntity.getMedicine().getDoseMedication());
    meRe.setMedicine(orderEntity.getMedicine().getMedicine());
    meRe.setOrderCancellation(orderEntity.getMedicine().isOrderCancellation()); 
    meRe.setProcedures(orderEntity.getMedicine().getProcedures());
    meRe.setProcedureDetail(orderEntity.getMedicine().getProcedureDetail());
    meRe.setReason(orderEntity.getMedicine().getReason());
    meRe.setSymptoms(orderEntity.getMedicine().getSymptoms());
    meRe.setVaccinationHistory(orderEntity.getMedicine().getVaccinationHistory());

    Order order = new Order();
    order.setCreatedDate(orderEntity.getCreatedDate());
    order.setDocumentOwner(ownerPerson);
    order.setDocumentVet(vetPerson);
    order.setMedicine(meRe);
    order.setOrderId(orderEntity.getOrderId());
    order.setPetId(pet);
    
    return order;
  }
}

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
  public void save(Order order) {
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
    meReEntity.setDate(order.getMedicalRecordId().getDate());
    meReEntity.setVetDocument(vetEntity);
    meReEntity.setPetId(petEntity);
    meReEntity.setAllergyMedications(order.getMedicalRecordId().getAllergyMedications());
    meReEntity.setDiagnosis(order.getMedicalRecordId().getDiagnosis());
    meReEntity.setDoseMedication(order.getMedicalRecordId().getDoseMedication());
    meReEntity.setMedicine(order.getMedicalRecordId().getMedicine());
    meReEntity.setOrdenId(order.getMedicalRecordId().getOrdenId());
    meReEntity.setOrderCancellation(order.getMedicalRecordId().isOrderCancellation()); 
    meReEntity.setProcedures(order.getMedicalRecordId().getProcedures());
    meReEntity.setProcedureDetail(order.getMedicalRecordId().getProcedureDetail());
    meReEntity.setReason(order.getMedicalRecordId().getReason());
    meReEntity.setSymptoms(order.getMedicalRecordId().getSymptoms());
    meReEntity.setVaccinationHistory(order.getMedicalRecordId().getVaccinationHistory());
    
    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setCreatedDate(order.getCreatedDate());
    orderEntity.setDocumentOwner(ownerEntity);
    orderEntity.setDocumentVet(vetEntity);
    orderEntity.setMedicalRecordId(meReEntity);
    orderEntity.setMedicine(meReEntity);
    orderEntity.setPet(petEntity);

    orderRepository.save(orderEntity);
  }

  @Override
  public Order findByOrderId(Long orderId) {
    OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
    if(orderEntity == null) return null;
    return orderAdapter(orderEntity);
  }

  public Order orderAdapter(OrderEntity orderEntity) {
    Person ownerEntity = new Person();
    ownerEntity.setAge(orderEntity.getDocumentOwner().getAge());
    ownerEntity.setDocument(orderEntity.getDocumentOwner().getDocument());
    ownerEntity.setName(orderEntity.getDocumentOwner().getName());
    ownerEntity.setRole(orderEntity.getDocumentOwner().getRole());

    Person vetEntity = new Person();
    vetEntity.setAge(orderEntity.getDocumentVet().getAge());
    vetEntity.setDocument(orderEntity.getDocumentVet().getDocument());
    vetEntity.setName(orderEntity.getDocumentVet().getName());
    vetEntity.setRole(orderEntity.getDocumentVet().getRole());

    Pet petEntity = new Pet();
    petEntity.setAge(orderEntity.getPet().getAge());
    petEntity.setDescription(orderEntity.getPet().getDescription());
    petEntity.setDocumentOwner(ownerEntity);
    petEntity.setName(orderEntity.getPet().getName());
    petEntity.setPetId(orderEntity.getPet().getPetId());
    petEntity.setRace(orderEntity.getPet().getRace());
    petEntity.setSpecie(orderEntity.getPet().getSpecie());
    petEntity.setWeight(orderEntity.getPet().getWeight());

    MedicalRecord meReEntity = new MedicalRecord();
    meReEntity.setDate(orderEntity.getMedicalRecordId().getDate());
    meReEntity.setVetDocument(vetEntity);
    meReEntity.setPetId(petEntity);
    meReEntity.setAllergyMedications(orderEntity.getMedicalRecordId().getAllergyMedications());
    meReEntity.setDiagnosis(orderEntity.getMedicalRecordId().getDiagnosis());
    meReEntity.setDoseMedication(orderEntity.getMedicalRecordId().getDoseMedication());
    meReEntity.setMedicine(orderEntity.getMedicalRecordId().getMedicine());
    meReEntity.setOrdenId(orderEntity.getMedicalRecordId().getOrdenId());
    meReEntity.setOrderCancellation(orderEntity.getMedicalRecordId().isOrderCancellation()); 
    meReEntity.setProcedures(orderEntity.getMedicalRecordId().getProcedures());
    meReEntity.setProcedureDetail(orderEntity.getMedicalRecordId().getProcedureDetail());
    meReEntity.setReason(orderEntity.getMedicalRecordId().getReason());
    meReEntity.setSymptoms(orderEntity.getMedicalRecordId().getSymptoms());
    meReEntity.setVaccinationHistory(orderEntity.getMedicalRecordId().getVaccinationHistory());

    Order order = new Order();
    order.setCreatedDate(orderEntity.getCreatedDate());
    order.setDocumentOwner(ownerEntity);
    order.setDocumentVet(vetEntity);
    order.setMedicalRecordId(meReEntity);
    order.setOrderId(orderEntity.getOrderId());
    order.setPetId(petEntity);
    return order;
  }
}

package app.adapters.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.medicalRecord.entity.MedicalRecordEntity;
import app.adapters.order.entity.OrderEntity;
import app.adapters.order.repository.OrderRepository;
import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.domain.models.Order;
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
  public List<Order> getAllOrders() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getAllOrders'");
  }
}

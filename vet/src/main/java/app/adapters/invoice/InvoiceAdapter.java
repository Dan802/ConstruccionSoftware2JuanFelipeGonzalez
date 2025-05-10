package app.adapters.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.invoice.entity.InvoiceEntity;
import app.adapters.invoice.repository.InvoiceRepository;
import app.adapters.medicalRecord.entity.MedicalRecordEntity;
import app.adapters.medicalRecord.repository.MedicalRecordRepository;
import app.adapters.order.entity.OrderEntity;
import app.adapters.order.repository.OrderRepository;
import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.entity.PetEntity;
import app.domain.models.Invoice;
import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.ports.InvoicePort;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@NoArgsConstructor
public class InvoiceAdapter implements InvoicePort {
  @Autowired
  private InvoiceRepository invoiceRepository;
  @Autowired 
  private OrderRepository orderRepository;
  @Autowired
  private MedicalRecordRepository meReRepository;

  @Override
  public Invoice save(Invoice invoice) {
    PersonEntity ownerEntity = new PersonEntity();
    ownerEntity.setAge(invoice.getOwnerId().getAge());
    ownerEntity.setDocument(invoice.getOwnerId().getDocument());
    ownerEntity.setName(invoice.getOwnerId().getName());
    ownerEntity.setRole(invoice.getOwnerId().getRole());

    PetEntity petEntity = new PetEntity();
    petEntity.setAge(invoice.getPetId().getAge());
    petEntity.setDescription(invoice.getPetId().getDescription());
    petEntity.setDocumentOwner(ownerEntity);
    petEntity.setName(invoice.getPetId().getName());
    petEntity.setPetId(invoice.getPetId().getPetId());
    petEntity.setRace(invoice.getPetId().getRace());
    petEntity.setSpecie(invoice.getPetId().getSpecie());
    petEntity.setWeight(invoice.getPetId().getWeight());

    PersonEntity vetEntity = new PersonEntity();
    MedicalRecordEntity meReEn = new MedicalRecordEntity();
    OrderEntity orderEntity = new OrderEntity();

    if(invoice.getOrderId() != null) {
      vetEntity.setAge(invoice.getOrderId().getDocumentVet().getAge());
      vetEntity.setDocument(invoice.getOrderId().getDocumentVet().getDocument());
      vetEntity.setName(invoice.getOrderId().getDocumentVet().getName());
      vetEntity.setRole(invoice.getOrderId().getDocumentVet().getRole());

      meReEn.setAllergyMedications(invoice.getOrderId().getMedicine().getAllergyMedications());
      meReEn.setDate(invoice.getOrderId().getMedicine().getDate());
      meReEn.setDiagnosis(invoice.getOrderId().getMedicine().getDiagnosis());
      meReEn.setDoseMedication(invoice.getOrderId().getMedicine().getDoseMedication());
      meReEn.setMedicine(invoice.getOrderId().getMedicine().getMedicine());
      meReEn.setOrderCancellation(invoice.getOrderId().getMedicine().isOrderCancellation());
      meReEn.setPetId(petEntity);
      meReEn.setProcedureDetail(invoice.getOrderId().getMedicine().getProcedureDetail());
      meReEn.setProcedures(invoice.getOrderId().getMedicine().getProcedures());
      meReEn.setReason(invoice.getOrderId().getMedicine().getReason());
      meReEn.setSymptoms(invoice.getOrderId().getMedicine().getSymptoms());
      meReEn.setVaccinationHistory(invoice.getOrderId().getMedicine().getVaccinationHistory());

      orderEntity.setCreatedDate(invoice.getOrderId().getCreatedDate());
      orderEntity.setDocumentOwner(ownerEntity);
      orderEntity.setDocumentVet(vetEntity);
      orderEntity.setMedicine(meReEn);
      orderEntity.setOrderId(invoice.getOrderId().getOrderId());
      orderEntity.setPet(petEntity);
    }

    InvoiceEntity invoiceEntity = new InvoiceEntity();
    invoiceEntity.setCount(invoice.getCount());
    invoiceEntity.setDateCreated(invoice.getDateCreated());
    invoiceEntity.setInvoiceId(invoice.getInvoiceId());
    invoiceEntity.setOrderId((invoice.getOrderId()) != null ? orderEntity : null);
    invoiceEntity.setOwnerId(ownerEntity);
    invoiceEntity.setPetId(petEntity);
    invoiceEntity.setPrice(invoice.getPrice());
    invoiceEntity.setProductName(invoice.getProductName());

    return invoiceAdapter(invoiceRepository.save(invoiceEntity));
  }

  /**
   * Guarda la factura CON una orderId
   * @param invoice
   * @param order 
   * @return
   */
  public Invoice save(Invoice invoice, Order order ) {
    PersonEntity ownerEntity = new PersonEntity();
    ownerEntity.setAge(invoice.getOrderId().getDocumentOwner().getAge());
    ownerEntity.setDocument(invoice.getOrderId().getDocumentOwner().getDocument());
    ownerEntity.setName(invoice.getOrderId().getDocumentOwner().getName());
    ownerEntity.setRole(invoice.getOrderId().getDocumentOwner().getRole());

    PersonEntity vetEntity = new PersonEntity();
    vetEntity.setAge(invoice.getOrderId().getDocumentVet().getAge());
    vetEntity.setDocument(invoice.getOrderId().getDocumentVet().getDocument());
    vetEntity.setName(invoice.getOrderId().getDocumentVet().getName());
    vetEntity.setRole(invoice.getOrderId().getDocumentVet().getRole());

    PetEntity petEntity = new PetEntity();
    petEntity.setAge(invoice.getOrderId().getPetId().getAge());
    petEntity.setDescription(invoice.getOrderId().getPetId().getDescription());
    petEntity.setDocumentOwner(ownerEntity);
    petEntity.setName(invoice.getOrderId().getPetId().getName());
    petEntity.setPetId(invoice.getOrderId().getPetId().getPetId());
    petEntity.setRace(invoice.getOrderId().getPetId().getRace());
    petEntity.setSpecie(invoice.getOrderId().getPetId().getSpecie());
    petEntity.setWeight(invoice.getOrderId().getPetId().getWeight());

    MedicalRecordEntity meReEn = new MedicalRecordEntity();
    meReEn.setAllergyMedications(invoice.getOrderId().getMedicine().getAllergyMedications());
    meReEn.setDate(invoice.getOrderId().getMedicine().getDate());
    meReEn.setDiagnosis(invoice.getOrderId().getMedicine().getDiagnosis());
    meReEn.setDoseMedication(invoice.getOrderId().getMedicine().getDoseMedication());
    meReEn.setMedicine(invoice.getOrderId().getMedicine().getMedicine());
    meReEn.setOrderCancellation(invoice.getOrderId().getMedicine().isOrderCancellation());
    meReEn.setPetId(petEntity);
    meReEn.setProcedureDetail(invoice.getOrderId().getMedicine().getProcedureDetail());
    meReEn.setProcedures(invoice.getOrderId().getMedicine().getProcedures());
    meReEn.setReason(invoice.getOrderId().getMedicine().getReason());
    meReEn.setSymptoms(invoice.getOrderId().getMedicine().getSymptoms());
    meReEn.setVaccinationHistory(invoice.getOrderId().getMedicine().getVaccinationHistory());

    OrderEntity orderEntity = new OrderEntity();
    orderEntity.setCreatedDate(invoice.getOrderId().getCreatedDate());
    orderEntity.setDocumentOwner(ownerEntity);
    orderEntity.setDocumentVet(vetEntity);
    orderEntity.setMedicine(meReEn);
    orderEntity.setOrderId(invoice.getOrderId().getOrderId());
    orderEntity.setPet(petEntity);

    InvoiceEntity invoiceEntity = new InvoiceEntity();
    invoiceEntity.setCount(invoice.getCount());
    invoiceEntity.setDateCreated(invoice.getDateCreated());
    invoiceEntity.setInvoiceId(invoice.getInvoiceId());
    invoiceEntity.setOrderId(orderEntity);
    invoiceEntity.setOwnerId(ownerEntity);
    invoiceEntity.setPetId(petEntity);
    invoiceEntity.setPrice(invoice.getPrice());
    invoiceEntity.setProductName(invoice.getProductName());

    return invoiceAdapter(invoiceRepository.save(invoiceEntity));
  }

  /**
   * Guarda la factura SIN una orden seteada
   * @param invoiceEntity
   * @return
   */
  public Invoice invoiceAdapter(InvoiceEntity invoiceEntity) {
    Person owner = new Person();
    owner.setAge(invoiceEntity.getOwnerId().getAge());
    owner.setDocument(invoiceEntity.getOwnerId().getDocument());
    owner.setName(invoiceEntity.getOwnerId().getName());
    owner.setRole(invoiceEntity.getOwnerId().getRole());

    Pet pet = new Pet();
    pet.setAge(invoiceEntity.getPetId().getAge());
    pet.setDescription(invoiceEntity.getPetId().getDescription());
    pet.setDocumentOwner(owner);
    pet.setName(invoiceEntity.getPetId().getName());
    pet.setPetId(invoiceEntity.getPetId().getPetId());
    pet.setRace(invoiceEntity.getPetId().getRace());
    pet.setSpecie(invoiceEntity.getPetId().getSpecie());
    pet.setWeight(invoiceEntity.getPetId().getWeight());
    
    MedicalRecord meRe = new MedicalRecord();
    Person vet = new Person();
    Order order = new Order();

    if(invoiceEntity.getOrderId() != null) {
    
      meRe.setAllergyMedications(invoiceEntity.getOrderId().getMedicine().getAllergyMedications());
      meRe.setDate(invoiceEntity.getOrderId().getMedicine().getDate());
      meRe.setDiagnosis(invoiceEntity.getOrderId().getMedicine().getDiagnosis());
      meRe.setDoseMedication(invoiceEntity.getOrderId().getMedicine().getDoseMedication());
      meRe.setMedicine(invoiceEntity.getOrderId().getMedicine().getMedicine());
      meRe.setOrderCancellation(invoiceEntity.getOrderId().getMedicine().isOrderCancellation());
      meRe.setPetId(pet);
      meRe.setProcedureDetail(invoiceEntity.getOrderId().getMedicine().getProcedureDetail());
      meRe.setProcedures(invoiceEntity.getOrderId().getMedicine().getProcedures());
      meRe.setReason(invoiceEntity.getOrderId().getMedicine().getReason());
      meRe.setSymptoms(invoiceEntity.getOrderId().getMedicine().getSymptoms());
      meRe.setVaccinationHistory(invoiceEntity.getOrderId().getMedicine().getVaccinationHistory());
      
      vet.setAge(invoiceEntity.getOrderId().getDocumentVet().getAge());
      vet.setDocument(invoiceEntity.getOrderId().getDocumentVet().getDocument());
      vet.setName(invoiceEntity.getOrderId().getDocumentVet().getName());
      vet.setRole(invoiceEntity.getOrderId().getDocumentVet().getRole());

      order.setCreatedDate(invoiceEntity.getOrderId().getCreatedDate());
      order.setDocumentOwner(owner);
      order.setDocumentVet(vet);
      order.setMedicine(meRe);
      order.setOrderId(invoiceEntity.getOrderId().getOrderId());
      order.setPetId(pet);
    }

    Invoice invoice = new Invoice();
    invoice.setCount(invoiceEntity.getCount());
    invoice.setDateCreated(invoiceEntity.getDateCreated());
    invoice.setInvoiceId(invoiceEntity.getInvoiceId());
    invoice.setOrderId(invoiceEntity.getOrderId() != null ? order : null);
    invoice.setOwnerId(owner);
    invoice.setPetId(pet);
    invoice.setPrice(invoiceEntity.getPrice());
    invoice.setProductName(invoiceEntity.getProductName());
    return invoice;
  }
}

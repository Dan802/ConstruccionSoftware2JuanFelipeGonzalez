package app.adapters.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.adapters.invoice.entity.InvoiceEntity;
import app.adapters.invoice.repository.InvoiceRepository;
import app.adapters.medicalRecord.repository.MedicalRecordRepository;
import app.adapters.order.OrderAdapter;
import app.adapters.order.entity.OrderEntity;
import app.adapters.order.repository.OrderRepository;
import app.adapters.person.PersonAdapter;
import app.adapters.person.entity.PersonEntity;
import app.adapters.pet.PetAdapter;
import app.adapters.pet.entity.PetEntity;
import app.domain.models.Invoice;
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
  private PetAdapter petAdapter;
  @Autowired
  private PersonAdapter personAdapter;
  @Autowired
  private OrderAdapter orderAdapter;
  @Autowired
  private InvoiceRepository invoiceRepository;
  @Autowired 
  private OrderRepository orderRepository;
  @Autowired
  private MedicalRecordRepository meReRepository;


  @Override
  public Invoice save(Invoice invoice) {
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

    OrderEntity orderEntity = orderRepository.findByOrderId(invoice.getOrderId().getOrderId());

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

  public Invoice invoiceAdapter(InvoiceEntity invoiceEntity) {
    Invoice invoice = new Invoice();
    invoice.setCount(invoiceEntity.getCount());
    invoice.setDateCreated(invoiceEntity.getDateCreated());
    invoice.setInvoiceId(invoiceEntity.getInvoiceId());
    invoice.setOrderId(orderAdapter.orderAdapter(invoiceEntity.getOrderId()));
    invoice.setOwnerId(personAdapter.personAdapter(invoiceEntity.getOwnerId()));
    invoice.setPetId(petAdapter.petAdapter(invoiceEntity.getPetId()));
    invoice.setPrice(invoiceEntity.getPrice());
    invoice.setProductName(invoiceEntity.getProductName());
    return invoice;
  }
}

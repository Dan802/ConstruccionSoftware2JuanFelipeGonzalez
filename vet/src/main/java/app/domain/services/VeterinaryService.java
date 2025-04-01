package app.domain.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.adapters.inputs.utils.SimpleValidator;
import app.adapters.inputs.utils.Utils;
import app.adapters.medicalRecord.MedicalRecordAdapter;
import app.adapters.person.PersonAdapter;
import app.domain.models.MedicalRecord;
import app.domain.models.Order;
import app.domain.models.Person;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class VeterinaryService {

  @Autowired
  private PersonAdapter personAdapter;
  @Autowired 
  private SimpleValidator simpleValidator;
  @Autowired
  private MedicalRecordAdapter meReAdapter;

  public void registerPetOwner(Person person) throws Exception{
    if(personAdapter.existPerson(person.getDocument())){
      throw new Exception("Ya existe una persona con esa cedula");
    }
    personAdapter.save(person);
  }

  public Person savePetOwner(Person petOwner) {
    Person person = personAdapter.save(petOwner);
    return person;
  }

  public void saveMedicalRecord(MedicalRecord medicalRecord) {
    meReAdapter.save(medicalRecord);
    System.out.println("\nLa orden ha sido anulada correctamente.");
  }

  public void printMedicalRecord(MedicalRecord meRe) {
    String meRePrint = 
      "\n1. Id de la historia clínica: " + meRe.getDate() +
      "\n2. Médico que lo atendió: " + meRe.getVetDocument().getName() +
      "\n3. Mascota atendida: " + meRe.getPetId().getName() +
      "\n4. Motivo de consulta: " + meRe.getReason() +
      "\n5. Sintomatologia: " + meRe.getSymptoms() +
      "\n6. Diagnostico: " + meRe.getDiagnosis() +
      "\n7. Procedimiento (opcional): " + simpleValidator.isNull(meRe.getProcedures())  +
      "\n8. Medicamento (opcional): " + simpleValidator.isNull(meRe.getMedicine()) +
      "\n9. Dosis de medicamento (opcional): " + simpleValidator.isNull(meRe.getDoseMedication()) +
      "\n10. Id para la orden: " + meRe.getOrdenId() +
      "\n11. Historial de vacunación: " + meRe.getVaccinationHistory() +
      "\n12. Medicamentos a los que presenta alergia: " + meRe.getAllergyMedications() +
      "\n13. Detalle del procedimiento: " + meRe.getProcedureDetail() +
      "\n14. ¿Orden anulada? " + (meRe.isOrderCancellation() ? "Si" : "No");

    System.out.println(meRePrint);
  }

  public void printOrder(Order order) {
    String orderPrint = 
      "\n1. Order Id: " + order.getOrderId() +
      "\n2. Id Historia clinica: " + order.getMedicalRecordId().getDate() +
      "\n3. Mascota: " + order.getPetId().getName() +
      "\n4. Dueño: " + order.getDocumentOwner().getName()+
      "\n5. Veterinario: " + order.getDocumentVet().getName() +
      "\n6. Medicinas recetadas: " + order.getMedicalRecordId().getMedicine() +
      "\n7. Fecha de creación: " + + order.getCreatedDate();

    System.out.println(orderPrint);
  }
}

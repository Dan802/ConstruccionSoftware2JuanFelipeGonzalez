package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord { //Historia Clínica
  private Long date;
  private Person vetDocument; // Médico que lo atendió
  private Pet petId; // Mascota a quien le hacemos el registo
  private String reason; // Motivo de consulta
  private String symptoms; // Sintomatologia
  private String diagnosis; // Diagnostico
  private String procedures; // Procedimiento
  private String medicine; // Medicamento
  private String doseMedication;  // Dosis de medicamento
  private String vaccinationHistory; // Historial de vacunación
  private String allergyMedications; // Medicamentos a los que presenta alergia
  private String procedureDetail; // Detalle del procedimiento
  private boolean orderCancellation; // Anulación orden
  
  public MedicalRecord(Long date, Person vetDocument, Pet petId, String reason, String symptoms, String diagnosis,
      String procedures, String medicine, String doseMedication, String vaccinationHistory, String allergyMedications,
      String procedureDetail, boolean orderCancellation) {
    this.date = date;
    this.vetDocument = vetDocument;
    this.petId = petId;
    this.reason = reason;
    this.symptoms = symptoms;
    this.diagnosis = diagnosis;
    this.procedures = procedures;
    this.medicine = medicine;
    this.doseMedication = doseMedication;
    this.vaccinationHistory = vaccinationHistory;
    this.allergyMedications = allergyMedications;
    this.procedureDetail = procedureDetail;
    this.orderCancellation = orderCancellation;
  }
}
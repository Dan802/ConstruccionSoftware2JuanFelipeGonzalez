package app.domain.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MedicalRecord { //Historia Clínica
  private long medicalRecordId;
  private Person veterinary; // Médico que lo atendió
  private String reason; // Motivo de consulta
  private String symptoms; // Sintomatologia
  private String diagnosis; // Diagnostico
  private String procedure; // Procedimiento
  private String medicine; // Medicamento
  private String doseMedication;  // Dosis de medicamento
  private Order ordenId; // Se usará para la autorización de venta de medicamentos
  private String vaccinationHistory; // Historial de vacunación
  private String allergyMedications; // Medicamentos a los que presenta alergia
  private String procedureDetail; // Detalle del procedimiento
  private boolean orderCancellation; // Anulación orden
  
  public MedicalRecord(long medicalRecordId, Person veterinary, String reason, String symptoms, String diagnosis,
      String procedure, String medicine, String doseMedication, Order ordenId, String vaccinationHistory,
      String allergyMedications, String procedureDetail, boolean orderCancellation) {
    this.medicalRecordId = medicalRecordId;
    this.veterinary = veterinary;
    this.reason = reason;
    this.symptoms = symptoms;
    this.diagnosis = diagnosis;
    this.procedure = procedure;
    this.medicine = medicine;
    this.doseMedication = doseMedication;
    this.ordenId = ordenId;
    this.vaccinationHistory = vaccinationHistory;
    this.allergyMedications = allergyMedications;
    this.procedureDetail = procedureDetail;
    this.orderCancellation = orderCancellation;
  }
}

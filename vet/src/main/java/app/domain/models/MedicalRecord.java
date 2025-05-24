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
  
  public MedicalRecord() {
 
  }

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
  
  public Long getDate() {
    return date;
  }

  public void setDate(Long date) {
    this.date = date;
  }

  public Person getVetDocument() {
    return vetDocument;
  }

  public Pet getPetId() {
    return petId;
  }

  public String getReason() {
    return reason;
  }

  public String getSymptoms() {
    return symptoms;
  }

  public String getDiagnosis() {
    return diagnosis;
  }

  public String getProcedures() {
    return procedures;
  }

  public String getMedicine() {
    return medicine;
  }

  public String getDoseMedication() {
    return doseMedication;
  }

  public String getVaccinationHistory() {
    return vaccinationHistory;
  }

  public String getAllergyMedications() {
    return allergyMedications;
  }

  public String getProcedureDetail() {
    return procedureDetail;
  }

  public boolean getOrderCancellation() {
    return orderCancellation;
  }

  public void setVeterinary(Person veterinary) {
    this.vetDocument = veterinary;
  }

  public void setPet(Pet pet) {
    this.petId = pet;
  } 

  public void setReason(String reason) {
    this.reason = reason;
  } 

  public void setSymptoms(String symptoms) {
    this.symptoms = symptoms;
  } 

  public void setDiagnosis(String diagnosis) {
    this.diagnosis = diagnosis;
  }  

  public void setProcedures(String procedures) {
    this.procedures = procedures;
  }      

  public void setMedicine(String medicine) {
    this.medicine = medicine;
  }

  public void setDoseMedication(String doseMedication) {
    this.doseMedication = doseMedication;
  }

  public void setVaccinationHistory(String vaccinationHistory) {
    this.vaccinationHistory = vaccinationHistory;
  }

  public void setAllergyMedications(String allergyMedications) {
    this.allergyMedications = allergyMedications;
  }

  public void setProcedureDetail(String procedureDetail) {
    this.procedureDetail = procedureDetail;
  }

  public void setOrderCancellation(boolean orderCancellation) {
    this.orderCancellation = orderCancellation;
  }

  @Override
  public String toString() {
    return "MedicalRecord{" +
        "\ndate=" + date +
        ", \nvetDocument=" + vetDocument +
        ", \npetId=" + petId +
        ", \nreason='" + reason + '\'' +
        ", \nsymptoms='" + symptoms + '\'' +
        ", \ndiagnosis='" + diagnosis + '\'' +
        ", \nprocedures='" + procedures + '\'' +
        ", \nmedicine='" + medicine + '\'' +
        ", \ndoseMedication='" + doseMedication + '\'' +
        ", \nvaccinationHistory='" + vaccinationHistory + '\'' +
        ", \nallergyMedications='" + allergyMedications + '\'' +
        ", \nprocedureDetail='" + procedureDetail + '\'' +
        ", \norderCancellation=" + orderCancellation +
        '}';
  }
}
package app.adapters.rest.request;

public class MedicalRecordRequest {
    private long ms;
    private Long vetDocument; 
    private Long petId; 
    private String reason; 
    private String symptoms; 
    private String diagnosis; 
    private String procedures; 
    private String medicine; 
    private String doseMedication;  
    private String vaccinationHistory; 
    private String allergyMedications; 
    private String procedureDetail; 
    private Boolean orderCancellation; 

    // TODO: Delete this
    private String userNameVet;
    private String passwordVet;

    public void setUserNameVet(String userNameVet) {
        this.userNameVet = userNameVet;
    }

    public void setPasswordVet(String passwordVet) {
        this.passwordVet = passwordVet;
    }

    public String getUserNameVet() {
        return userNameVet;
    }

    public String getPasswordVet() {
        return passwordVet;
    }

    public long getMs() {
        return ms;
    }

    public void setMs(long ms) {    
        this.ms = ms;
    }

    public void setVetDocument(Long vetDocument) {
        this.vetDocument = vetDocument;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
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

    public void setOrderCancellation(Boolean orderCancellation) {
        this.orderCancellation = orderCancellation;
    }   

    public Long getVetDocument() {
        return vetDocument;
    }
    
    public Long getPetId() {
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

    public Boolean getOrderCancellation() {
        return orderCancellation;
    }

    @Override
    public String toString() {
        return "MedicalRecordRequest{" +
                "\nms=" + ms +
                ",\nvetDocument=" + vetDocument +
                ",\npetId=" + petId +
                ",\nreason='" + reason + '\'' +
                ",\nsymptoms='" + symptoms + '\'' +
                ",\ndiagnosis='" + diagnosis + '\'' +
                ",\nprocedures='" + procedures + '\'' +
                ",\nmedicine='" + medicine + '\'' +
                ",\ndoseMedication='" + doseMedication + '\'' +
                ",\nvaccinationHistory='" + vaccinationHistory + '\'' +
                ",\nallergyMedications='" + allergyMedications + '\'' +
                ",\nprocedureDetail='" + procedureDetail + '\'' +
                ",\norderCancellation=" + orderCancellation +
                '}';
    }
}

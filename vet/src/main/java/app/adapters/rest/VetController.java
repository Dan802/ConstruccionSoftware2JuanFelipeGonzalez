package app.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


import app.Exceptions.BusinessException;
import app.Exceptions.NotFoundException;
import app.adapters.rest.request.MedicalRecordRequest;
import app.adapters.rest.request.PetOwnerRequest;
import app.adapters.rest.request.PetRequest;
import app.domain.models.MedicalRecord;
import app.domain.models.Person;
import app.domain.models.Pet;
import app.domain.services.OrderService;
import app.domain.services.VeterinaryService;

@RestController
@RequestMapping("/api")
public class VetController {

    @Autowired
    private VeterinaryService veterinaryService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/createPetOwner")
    public ResponseEntity<String> createPetOwner(@RequestBody PetOwnerRequest request) {
        System.out.println(request.toString());
        try {
            veterinaryService.savePetOwner(request.getDocument(), request.getName(), request.getAge());
            return new ResponseEntity<>("Person created successfully", HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createPet")
    public ResponseEntity<String> createPet(@RequestBody PetRequest request) {
        System.out.println(request.toString());
        try {
            veterinaryService.savePet(request.getDocumentOwner(), request.getName(), request.getAge(), request.getSpecie(), request.getRace(), request.getDescription(), request.getWeight());
            return new ResponseEntity<>("Pet created successfully", HttpStatus.CREATED);
        } catch (BusinessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/createMedicalRecord")
    public ResponseEntity<String> createMedicalRecord(@RequestBody MedicalRecordRequest request) {
        try {
            System.out.println("\nREQUEST:" + request.toString() + "\n");
            //Todo: verify if the vet is logged in

            Pet pet = veterinaryService.searchPet(request.getPetId());  
            
            // TODO: should be with cookie's token
            Person veterinary = veterinaryService.existsPerson(request.getVetDocument(), "Veterinary not found");

            MedicalRecord meRe = veterinaryService.saveMedicalRecord(null, veterinary, pet, request.getReason(), request.getSymptoms(), request.getDiagnosis(), request.getProcedures(), request.getMedicine(), request.getDoseMedication(), request.getVaccinationHistory(), request.getAllergyMedications(), request.getProcedureDetail());

            orderService.saveOrder(null, pet, meRe.getPetId().getDocumentOwner(), veterinary, meRe, null);

            return new ResponseEntity<>("Medical record created successfully and order saved", HttpStatus.CREATED);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } 
    }

    @PutMapping("/updateMedicalRecord")
	public ResponseEntity<String> updateMedicalRecord(@RequestBody MedicalRecordRequest request) {

        try {
            // We search the old medical record
            // And only update the fields that are not null
            MedicalRecord oldMedicalRecord = veterinaryService.searchMedicalRecord(request.getMs());

            // If there is a new veterinary, we check if it exists
            Person veterinary = null;
            if(request.getVetDocument() != null) {
                veterinary = veterinaryService.existsPerson(request.getVetDocument(), "Veterinary not found");
                if(veterinary.getRole() != "Veterinario") {
                    throw new BusinessException("Veterinary not found");
                }
            }

            // If there is a new pet, we check if it exists
            Pet pet = null;
            if(request.getPetId() != null) {
                pet = veterinaryService.searchPet(request.getPetId());  
            }

            // We create a new medical record
			MedicalRecord medicalRecord = new MedicalRecord();
            medicalRecord.setDate(request.getMs());
			medicalRecord.setVeterinary(request.getVetDocument()  == null ? oldMedicalRecord.getVetDocument() : veterinary);
            medicalRecord.setPet(request.getPetId() == null ? oldMedicalRecord.getPetId() : pet);
            medicalRecord.setReason(request.getReason() == null ? oldMedicalRecord.getReason() : request.getReason());
            medicalRecord.setSymptoms(request.getSymptoms() == null ? oldMedicalRecord.getSymptoms() : request.getSymptoms());
            medicalRecord.setDiagnosis(request.getDiagnosis() == null ? oldMedicalRecord.getDiagnosis() : request.getDiagnosis());
            medicalRecord.setProcedures(request.getProcedures() == null ? oldMedicalRecord.getProcedures() : request.getProcedures());
            medicalRecord.setMedicine(request.getMedicine() == null ? oldMedicalRecord.getMedicine() : request.getMedicine());
            medicalRecord.setDoseMedication(request.getDoseMedication() == null ? oldMedicalRecord.getDoseMedication() : request.getDoseMedication());
            medicalRecord.setVaccinationHistory(request.getVaccinationHistory() == null ? oldMedicalRecord.getVaccinationHistory() : request.getVaccinationHistory());
            medicalRecord.setAllergyMedications(request.getAllergyMedications() == null ? oldMedicalRecord.getAllergyMedications() : request.getAllergyMedications());
            medicalRecord.setProcedureDetail(request.getProcedureDetail() == null ? oldMedicalRecord.getProcedureDetail() : request.getProcedureDetail());

            veterinaryService.updateMedicalRecord(medicalRecord);   

			return new ResponseEntity("Medical record updated successfully", HttpStatus.ACCEPTED);
        } catch (BusinessException be) {
        return new ResponseEntity(be.getMessage(), HttpStatus.CONFLICT);
		} catch (NotFoundException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
        catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
    
}

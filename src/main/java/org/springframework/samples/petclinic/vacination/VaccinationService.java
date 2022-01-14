package org.springframework.samples.petclinic.vacination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.user.AuthoritiesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VaccinationService {

    private VaccinationRepository vaccinationRepository;

    @Autowired
    public VaccinationService(VaccinationRepository vaccinationRepository){
        this.vaccinationRepository = vaccinationRepository;
    }

    @Transactional
    public List<Vaccination> getAll(){
        return vaccinationRepository.findAll();
    }

    @Transactional
    public List<Vaccine> getAllVaccines(){
        return vaccinationRepository.findAllVaccines();
    }

    public Vaccine getVaccine(String typeName) {
        return vaccinationRepository.findVaccineByName(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleVaccinationException.class)
    public Vaccination save(Vaccination p) throws UnfeasibleVaccinationException {
        Pet pet = p.getVaccinatedPet();
        PetType petType = pet.getType();
        if(!petType.equals(p.getVaccine().getPetType())){
            throw new UnfeasibleVaccinationException();
        }

        return vaccinationRepository.save(p);
    }

    
}

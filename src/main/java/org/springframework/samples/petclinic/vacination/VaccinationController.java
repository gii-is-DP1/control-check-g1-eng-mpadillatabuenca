package org.springframework.samples.petclinic.vacination;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Collection;

@Controller
public class VaccinationController {

    private final String FORM_VIEW = "vaccination/createOrUpdateVaccinationForm";

    private final VaccinationService vaccinationService;
    private final PetService petService;

    @Autowired
    public VaccinationController(VaccinationService vaccinationService, PetService petService){
        this.vaccinationService = vaccinationService;
        this.petService = petService;
    }

    @GetMapping("vaccination/create")
    public String initCreationForm(ModelMap model){
        Vaccination vaccination = new Vaccination();
        Collection<Vaccine> vaccines = vaccinationService.getAllVaccines();
        Collection<Pet> pets = petService.findAllPets();
        model.put("vaccination", vaccination);
        model.put("vaccines", vaccines);
        model.put("pets",pets);
        return FORM_VIEW;
    }

    @PostMapping("vaccination/create")
    public String processCreationForm(@Valid Vaccination vaccination, BindingResult result, ModelMap model){
        if (result.hasErrors()) {
            model.put("vaccination", vaccination);
            return FORM_VIEW;
        }
        else {
            try{
                this.vaccinationService.save(vaccination);
            }catch(UnfeasibleVaccinationException ex){
                result.rejectValue("vaccine", "La mascota seleccionada no puede recibir la vacuna especificada");
                return FORM_VIEW;
            }
            return "welcome";
        }
    }
    
}

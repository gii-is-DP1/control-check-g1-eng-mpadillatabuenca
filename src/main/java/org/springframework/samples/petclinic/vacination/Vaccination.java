package org.springframework.samples.petclinic.vacination;

import java.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vaccination extends BaseEntity {

    @DateTimeFormat(pattern = "yyyy/MM/dd")
    @NotNull
    LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccinated_pet_id")
    private Pet vaccinatedPet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vaccine_id")
    private Vaccine vaccine;
}

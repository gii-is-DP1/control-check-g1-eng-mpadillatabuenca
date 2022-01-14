package org.springframework.samples.petclinic.vacination;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.pet.Pet;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Vaccine extends BaseEntity {

    @Size(min = 3, max = 50)
    @Column(unique = true)
    String name;

    @DecimalMin("0.0")
    Double price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pet_type_id")
    private PetType petType;
}
